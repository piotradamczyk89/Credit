package com.inteca.credit.credit;

import com.inteca.credit.inputObject.CustomerId;
import com.inteca.credit.inputObject.InputCreateCreditDto;
import com.inteca.credit.inputObject.customerList.CustomerList;
import com.inteca.credit.requestObject.CreateCustomer;
import com.inteca.credit.requestObject.CustomerIdList;
import com.inteca.credit.requestObject.Pesel;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

public interface CreditService {

    void saveCredit (Credit credit);

    Credit findByCustomerId (Long customerId);

    Long getCustomerId (InputCreateCreditDto inputCreateCreditDto);

    CustomerList searchCustomer(InputCreateCreditDto inputCreateCreditDto);

    CustomerId createCustomer(InputCreateCreditDto inputCreateCreditDto);

    List<Credit> findAllCredits ();

    CustomerIdList getListOfCustomersId (List<Credit> credits);

    CustomerList grtCustomers (List<Credit> credits);


}
