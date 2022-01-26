package com.inteca.credit.pojoObject.responseObject.creditCustomerAgregate;

import com.inteca.credit.credit.Credit;
import com.inteca.credit.exception.NoCustomerMatchedToCredit;
import com.inteca.credit.pojoObject.inputObject.customerList.Customer;
import com.inteca.credit.pojoObject.inputObject.customerList.CustomerList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditCustomerAggregateListTest {

    @Test
    void createAggregateListTest_okData() {

        //given
        Credit credit = new Credit("hipoteka", 1600.0, 15L);
        credit.setId(6L);
        Credit credit2 = new Credit("hipoteka", 1500.0, 16L);
        credit2.setId(7L);
        List<Credit> credits = new ArrayList<>();
        credits.add(credit);
        credits.add(credit2);

        CustomerList customerList = new CustomerList();
        Customer customer = new Customer(15L, "Jan", "Kowalski", "78945612378");
        Customer customer2 = new Customer(16L, "Marek", "Kowalski", "12345678912");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer2);
        customerList.setCustomers(customers);

        CreditCustomerAggregate creditCustomerAggregate = new CreditCustomerAggregate(customer, credit);
        CreditCustomerAggregate creditCustomerAggregate2 = new CreditCustomerAggregate(customer2, credit2);
        List<CreditCustomerAggregate> customerAggregates = new ArrayList<>();
        customerAggregates.add(creditCustomerAggregate);
        customerAggregates.add(creditCustomerAggregate2);
        CreditCustomerAggregateList expected = new CreditCustomerAggregateList(customerAggregates);

        //when
        CreditCustomerAggregateList result = CreditCustomerAggregateList.createAggregateList(credits, customerList);

        //then
        assertEquals(expected,result);

    }

    @Test
    void createAggregateListTest_missing_Customer() {

        //given
        Credit credit = new Credit("hipoteka", 1600.0, 15L);
        credit.setId(6L);
        Credit credit2 = new Credit("hipoteka", 1500.0, 16L);
        credit2.setId(7L);
        List<Credit> credits = new ArrayList<>();
        credits.add(credit);
        credits.add(credit2);

        CustomerList customerList = new CustomerList();
        Customer customer = new Customer(15L, "Jan", "Kowalski", "78945612378");
        Customer customer2 = new Customer(17L, "Marek", "Kowalski", "12345678912");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer2);
        customerList.setCustomers(customers);

        CreditCustomerAggregate creditCustomerAggregate = new CreditCustomerAggregate(customer, credit);
        CreditCustomerAggregate creditCustomerAggregate2 = new CreditCustomerAggregate(customer2, credit2);
        List<CreditCustomerAggregate> customerAggregates = new ArrayList<>();
        customerAggregates.add(creditCustomerAggregate);
        customerAggregates.add(creditCustomerAggregate2);



        //then
        assertThrows(NoCustomerMatchedToCredit.class,() -> CreditCustomerAggregateList.createAggregateList(credits,customerList));

    }
}