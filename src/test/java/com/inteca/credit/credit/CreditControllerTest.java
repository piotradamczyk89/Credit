package com.inteca.credit.credit;

import com.inteca.credit.pojoObject.inputObject.InputCreateCredit;
import com.inteca.credit.pojoObject.inputObject.customerList.Customer;
import com.inteca.credit.pojoObject.inputObject.customerList.CustomerList;
import com.inteca.credit.pojoObject.responseObject.CreditId;
import com.inteca.credit.pojoObject.responseObject.creditCustomerAgregate.CreditCustomerAggregateList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CreditControllerTest {

    private CreditService creditService;
    private CreditController creditController;

    @BeforeEach
    public void onSetUp() {
        creditService = Mockito.mock(CreditService.class);
        creditController = new CreditController(creditService);

    }

    @Test
    void createCreditTest() {
        //given
        InputCreateCredit input = new InputCreateCredit("Jan", "Kowalski"
                , "89060855978", "hipoteka", 1600.0);
        Credit credit = new Credit(input.getCreditName(), input.getValue(), 15L);
        Credit creditSaved = new Credit(input.getCreditName(), input.getValue(), 15L);
        creditSaved.setId(5L);

        when(creditService.checkInputData(input)).thenReturn(true);
        when(creditService.getCustomerId(input)).thenReturn(15L);
        when(creditService.saveCredit(credit)).thenReturn(creditSaved);

        CreditId expected = new CreditId(5L);

        //when

        CreditId result = creditController.createCredit(input);

        //then
        assertEquals(expected, result);

    }

    @Test
    void getCreditsTest() {

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

        CreditCustomerAggregateList expected = CreditCustomerAggregateList.createAggregateList(credits, customerList);

        when(creditService.findAllCredits()).thenReturn(credits);
        when(creditService.getCustomers(credits)).thenReturn(customerList);

        //when

        CreditCustomerAggregateList result = creditController.getCredits();

        //then

        assertEquals(expected,result);
    }


}