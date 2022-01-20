package com.inteca.credit.credit;

import com.inteca.credit.requestObject.CustomerIdList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
class CreditServiceTest {

    // musiałem dodać @DataJpaTest i @BeoferEach zeby zainicjowało mi repo i serwis ??

    private CreditService creditService;

    @Mock
    private CreditRepository creditRepository;

    @BeforeEach
    public void onSetUp() {
        creditService= new CreditServiceImpl(creditRepository);
    }

    @Test
    void when_save_credit_than_return_object_credit() {
        //given
        Credit credit = new Credit();
        credit.setCreditName("hipoteka");
        when(creditRepository.save(credit)).thenReturn(credit);

        //when
        Credit result = creditService.saveCredit(credit);

        //then
        assertNotNull(result);
        assertEquals(credit.getCreditName(),result.getCreditName());
    }

    @Test
    void when_searching_16_then_return_credit () {
        //given
        Credit credit = new Credit();
        credit.setCustomerId(16L);
        when(creditRepository.findByCustomerId(16L)).thenReturn(credit);

        //when
        Credit result = creditService.findByCustomerId(16L);

        //then
        assertEquals(16L,result.getCustomerId());
    }

    @Test
    void getCustomerId() {
    }

    @Test
    void searchCustomer() {
    }

    @Test
    void createCustomer() {
    }

    @Test
    void when_method_findAllCredits_then_return_list_of_credit() {
        //given
        Credit credit1 = new Credit();
        credit1.setCreditName("credit1");
        Credit credit2 = new Credit();
        credit2.setCreditName("credit2");
        List<Credit> credits = new ArrayList<>();
        credits.add(credit1);
        credits.add(credit2);
        when(creditRepository.findAll()).thenReturn(credits);

        //when
        List<Credit> result = creditService.findAllCredits();

        //then
        assertEquals(credits, result);
    }

    @Test
    void when_method_ListOfCustomersId_than_return_list_of_customers_ids() {
        //given
        Credit credit1 = new Credit();
        credit1.setCustomerId(15L);
        Credit credit2 = new Credit();
        credit2.setCustomerId(16L);
        List<Credit> credits = new ArrayList<>();
        credits.add(credit1);
        credits.add(credit2);

        List <Long> customersIds = new ArrayList<>();
        customersIds.add(15L);
        customersIds.add(16L);

        //when
        CustomerIdList listOfCustomersId = creditService.getListOfCustomersId(credits);

        //then
        assertEquals(customersIds,listOfCustomersId.getCustomersIds());
    }

    @Test
    void grtCustomers() {
    }
}