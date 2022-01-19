package com.inteca.credit.credit;

import com.inteca.credit.dto.CreditCustomerDto;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public interface CreditService {

    void saveCredit (Credit credit);

    Credit generateCredit (CreditCustomerDto inputDto);

    CreditCustomerDto searchCustomer(CreditCustomerDto inputDto);

    CreditCustomerDto createCustomer(CreditCustomerDto inputDto);

    CreditCustomerDto getResultDto(CloseableHttpClient client, HttpPost httpPost, StringEntity params);

    List<Credit> findAllCredits ();

    List<Long> getListOfCreditsId (List<Credit> credits);

    CreditCustomerDto findCustomersWithCredit(List<Long> creditsId);


}
