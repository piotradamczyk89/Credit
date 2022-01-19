package com.inteca.credit.credit;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.inteca.credit.customer.Customer;
import com.inteca.credit.dto.CreditCustomerDto;
import lombok.AllArgsConstructor;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreditServiceImpl implements CreditService{

    private final CreditRepository creditRepository;

    @Override
    public void saveCredit(Credit credit) {
        creditRepository.save(credit);
    }

    @Override
    public Credit generateCredit(CreditCustomerDto inputDto) {
        CreditCustomerDto responseDto = searchCustomer(inputDto);
        if (responseDto==null) {
            responseDto = createCustomer(inputDto);
        }
        responseDto.margeToCreateCredit(inputDto);
        return responseDto.mapCredit();
    }

    @Override
    public CreditCustomerDto searchCustomer(CreditCustomerDto inputDto) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8074/customer/search");
        StringEntity params = null;
        try {
            params = new StringEntity("{\n\"pesel\": \"" + inputDto.getPesel() + "\"\n}");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return getResultDto(client, httpPost, params);
    }

    @Override
    public CreditCustomerDto createCustomer(CreditCustomerDto inputDto) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8074/customer");
        StringEntity params = null;
        try {
            params = new StringEntity("{\n\"firstName\": \"" + inputDto.getFirstName() + "\",\n" +
                    "\"lastName\": \"" + inputDto.getLastName() + "\",\n" +
                    "\"pesel\": \"" + inputDto.getPesel() + "\"\n}");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return getResultDto(client, httpPost, params);
    }

    @Override
    public CreditCustomerDto getResultDto(CloseableHttpClient client, HttpPost httpPost, StringEntity params) {
        httpPost.setEntity(params);
        ObjectMapper mapper = new ObjectMapper();
        CreditCustomerDto requestResult = null;
        try {
            requestResult = client.execute(httpPost, httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), CreditCustomerDto.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestResult;
    }

    @Override
    public List<Credit> findAllCredits() {
        return creditRepository.findAll();
    }

    @Override
    public List<Long> getListOfCreditsId(List<Credit> credits) {
       return credits.stream().map(Credit::getId).collect(Collectors.toList());
    }

    @Override
    public CreditCustomerDto findCustomersWithCredit(List<Long> creditsId) {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8074/customer/filtered");
        StringEntity params = null;
        try {
            params = new StringEntity("{\n\"customersIds\": " + creditsId + "\n}");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return getResultDto(client, httpPost, params);
    }
}
