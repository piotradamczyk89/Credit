package com.inteca.credit.credit;

import com.inteca.credit.pojoObject.inputObject.CustomerId;
import com.inteca.credit.pojoObject.inputObject.InputCreateCredit;
import com.inteca.credit.pojoObject.inputObject.customerList.CustomerList;
import com.inteca.credit.pojoObject.requestObject.CreateCustomer;
import com.inteca.credit.pojoObject.requestObject.CustomerIdList;
import com.inteca.credit.pojoObject.requestObject.Pesel;

import java.util.List;

public interface CreditService {

    Credit saveCredit (Credit credit);

    Credit findByCustomerId (Long customerId);

    Long getCustomerId (InputCreateCredit inputCreateCredit);

    CustomerList searchCustomer(Pesel pesel);

    CustomerId createCustomer(CreateCustomer createCustomer);

    List<Credit> findAllCredits ();

    CustomerIdList getListOfCustomersId (List<Credit> credits);

    CustomerList getCustomers(List<Credit> credits);

    CreateCustomer createObjectCreateCustomer (InputCreateCredit inputCreateCredit);

    Pesel createPesel (InputCreateCredit inputCreateCreditDto);


}
