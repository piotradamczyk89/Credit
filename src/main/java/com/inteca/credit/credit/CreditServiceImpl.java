package com.inteca.credit.credit;


import com.inteca.credit.pojoObject.inputObject.CustomerId;
import com.inteca.credit.pojoObject.inputObject.InputCreateCredit;
import com.inteca.credit.pojoObject.inputObject.customerList.CustomerList;
import com.inteca.credit.pojoObject.requestObject.CreateCustomer;
import com.inteca.credit.pojoObject.requestObject.CustomerIdList;
import com.inteca.credit.pojoObject.requestObject.Pesel;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService{

    @Value("${app.customer.address}")
    private String customerAddress;
    private final CreditRepository creditRepository;

    @Override
    public Credit saveCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    @Override
    public Credit findByCustomerId(Long customerId) {
        return creditRepository.findByCustomerId(customerId);
    }

    @Override
    public Pesel createPesel (InputCreateCredit inputCreateCreditDto) {
        return new Pesel(inputCreateCreditDto.getPesel());
    }

    @Override
    public Boolean checkInputData(InputCreateCredit inputCreateCreditDto) {
        Pattern compiledPattern = Pattern.compile("^\\d{11}$");
        Matcher matcher = compiledPattern.matcher(inputCreateCreditDto.getPesel());
        return matcher.matches()
                && (!inputCreateCreditDto.getCreditName().isEmpty())
                && inputCreateCreditDto.getValue()>0.0;
    }

    @Override
    public CreateCustomer createObjectCreateCustomer (InputCreateCredit inputCreateCredit){
        return new CreateCustomer(inputCreateCredit.getFirstName()
                , inputCreateCredit.getLastName()
                , inputCreateCredit.getPesel());
    }

    @Override
    public Long getCustomerId(InputCreateCredit inputCreateCredit) {
        CustomerList customerList = this.searchCustomer(this.createPesel(inputCreateCredit));
        Long customerId = null;
        if(customerList.getCustomers().isEmpty()) {
            customerId = this.createCustomer(this.createObjectCreateCustomer(inputCreateCredit)).getCustomerId();
        } else {
            customerId=customerList.getCustomers().get(0).getCustomerId();
        }
        return customerId;
    }

    @Override
    public CustomerList searchCustomer(Pesel pesel) {
        String url = customerAddress + "/customer/search";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomerList> responseEntity = restTemplate.postForEntity(url, pesel, CustomerList.class);
        return responseEntity.getBody();
    }

    @Override
    public CustomerId createCustomer(CreateCustomer createCustomer) {
        String url = customerAddress + "/customer";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomerId> responseEntity = restTemplate.postForEntity(url, createCustomer, CustomerId.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Credit> findAllCredits() {
        return creditRepository.findAll();
    }

    @Override
    public CustomerIdList getListOfCustomersId (List<Credit> credits) {
        List<Long> customersIds = credits.stream().map(Credit::getCustomerId).collect(Collectors.toList());
        return new CustomerIdList(customersIds);
    }

    public CustomerList getCustomers(List<Credit> credits) {
        String url = customerAddress + "/customer/filtered";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomerList> responseEntity = restTemplate.postForEntity(url,
                this.getListOfCustomersId(credits), CustomerList.class);
        return responseEntity.getBody();
    }
}
