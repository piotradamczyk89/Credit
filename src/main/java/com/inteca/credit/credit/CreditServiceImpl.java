package com.inteca.credit.credit;




import com.inteca.credit.inputObject.CustomerId;
import com.inteca.credit.inputObject.InputCreateCreditDto;
import com.inteca.credit.inputObject.customerList.Customer;
import com.inteca.credit.inputObject.customerList.CustomerList;
import com.inteca.credit.requestObject.CreateCustomer;
import com.inteca.credit.requestObject.CustomerIdList;
import com.inteca.credit.requestObject.Pesel;

import lombok.RequiredArgsConstructor;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.List;
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
    public Long getCustomerId(InputCreateCreditDto inputCreateCreditDto) {
        CustomerList customerList = searchCustomer(inputCreateCreditDto);
        Long customerId = null;
        if(customerList.getCustomers().isEmpty()) {
            customerId = createCustomer(inputCreateCreditDto).getCustomerId();
        } else {
            customerId=customerList.getCustomers().get(0).getId();
        }
        return customerId;
    }

    //rest assured,

    @Override
    public CustomerList searchCustomer(InputCreateCreditDto inputCreateCreditDto) {
        String url = customerAddress + "customer/search";
        Pesel pesel = new Pesel(inputCreateCreditDto.getPesel());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomerList> responseEntity = restTemplate.postForEntity(url, pesel, CustomerList.class);
        return responseEntity.getBody();
    }

    @Override
    public CustomerId createCustomer(InputCreateCreditDto inputCreateCreditDto) {
        String url = customerAddress + "customer";
        CreateCustomer createCustomer = new CreateCustomer(inputCreateCreditDto.getFirstName()
                , inputCreateCreditDto.getLastName(), inputCreateCreditDto.getPesel());
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

    @Override
    public CustomerList grtCustomers(List<Credit> credits) {
        CustomerIdList customerIdList = getListOfCustomersId(credits);
        String url = customerAddress + "customer/filtered";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomerList> responseEntity = restTemplate.postForEntity(url, customerIdList, CustomerList.class);
        return responseEntity.getBody();
    }
}
