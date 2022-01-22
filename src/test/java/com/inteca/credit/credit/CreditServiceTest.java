package com.inteca.credit.credit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inteca.credit.pojoObject.inputObject.CustomerId;
import com.inteca.credit.pojoObject.inputObject.InputCreateCredit;
import com.inteca.credit.pojoObject.inputObject.customerList.Customer;
import com.inteca.credit.pojoObject.inputObject.customerList.CustomerList;
import com.inteca.credit.pojoObject.requestObject.CreateCustomer;
import com.inteca.credit.pojoObject.requestObject.CustomerIdList;
import com.inteca.credit.pojoObject.requestObject.Pesel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
class CreditServiceTest {

    // musiałem dodać @DataJpaTest i @BeoferEach zeby zainicjowało mi repo i serwis ??

    // how to spring boot junit5

    private CreditService creditService;

    @Mock
    private CreditRepository creditRepository;

    @BeforeEach
    public void onSetUp() {
        creditService= new CreditServiceImpl(creditRepository);
    }

    @Test
    void check_if_CustomerList_object_is_converted_to_proper_json () throws JsonProcessingException {
        //given
        CustomerList customerList = new CustomerList();
        Customer customer = new Customer();
        customer.setCustomerId(19423L);
        customer.setFirstName("Jan");
        customer.setLastName("Kowalski");
        customer.setPesel("76051728798");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customerList.setCustomers(customers);
        ObjectMapper mapper = new ObjectMapper();
        String customerListJsonString = "";
        try {
            customerListJsonString = mapper.writeValueAsString(customerList);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String customerListJson = "{\n" +
                "\"customers\": [\n" +
                "{\n" +
                "\"customerId\": 19423,\n" +
                "\"firstName\": \"Jan\",\n" +
                "\"lastName\": \"Kowalski\",\n" +
                "\"pesel\": \"76051728798\"\n" +
                "}\n" +
                "]\n" +
                "}";
        assertEquals(mapper.readTree(customerListJson),mapper.readTree(customerListJsonString));
    }

    @Test
    void check_if_CustomerId_object_is_converted_to_proper_json () throws JsonProcessingException {
        //given
        CustomerId customerId = new CustomerId();
        customerId.setCustomerId(4923L);
        ObjectMapper mapper = new ObjectMapper();
        String customerIdJsonString = "";
        try {
            customerIdJsonString = mapper.writeValueAsString(customerId);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String customerIdJson = "{\n" +
                "\"customerId\" : 4923\n" +
                "}";
        assertEquals(mapper.readTree(customerIdJson),mapper.readTree(customerIdJsonString));
    }

    @Test
    void check_if_CreateCustomer_object_is_converted_to_proper_json () throws JsonProcessingException {
        //given
        CreateCustomer createCustomer = new CreateCustomer();
        createCustomer.setFirstName("Jan");
        createCustomer.setLastName("Kowalski");
        createCustomer.setPesel("76051728798");
        ObjectMapper mapper = new ObjectMapper();
        String createCustomerJsonString = "";
        try {
            createCustomerJsonString = mapper.writeValueAsString(createCustomer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String createCustomerJson = "{\n" +
                "\"firstName\": \"Jan\",\n" +
                "\"lastName\": \"Kowalski\",\n" +
                "\"pesel\": \"76051728798\"\n" +
                "}";
        assertEquals(mapper.readTree(createCustomerJson),mapper.readTree(createCustomerJsonString));
    }

    @Test
    void check_if_CustomerIdList_object_is_converted_to_proper_json () throws JsonProcessingException {
        //given
        CustomerIdList customerIdList = new CustomerIdList();
        List<Long> ids = new ArrayList<>();
        ids.add(1344L);
        ids.add(1434L);
        ids.add(4921L);
        customerIdList.setCustomersIds(ids);
        ObjectMapper mapper = new ObjectMapper();
        String createCustomerIdListJsonString = "";
        try {
            createCustomerIdListJsonString = mapper.writeValueAsString(customerIdList);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String createCustomerIdListJson = "{\n" +
                "\"customersIds\": [1344, 1434, 4921]\n" +
                "}";
        assertEquals(mapper.readTree(createCustomerIdListJson),mapper.readTree(createCustomerIdListJsonString));
    }

    @Test
    void check_if_Pesel_object_is_converted_to_proper_json () throws JsonProcessingException {
        //given
        Pesel pesel = new Pesel();
        pesel.setPesel("76051728798");
        ObjectMapper mapper = new ObjectMapper();
        String createPeselJsonString = "";
        try {
            createPeselJsonString = mapper.writeValueAsString(pesel);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String peselListJson = "{\n" +
                "\"pesel\": \"76051728798\"\n" +
                "}";
        assertEquals(mapper.readTree(peselListJson),mapper.readTree(createPeselJsonString));
    }

    @Test
    void when_save_credit_than_return_object_credit() {
        //given
        creditService= new CreditServiceImpl(creditRepository);
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
    void when_method_searchCustomer_return_empty_list_then_method_getCustomerId_return_new_customerId() {


        // TERAZ TO


        //given
        CustomerList customerList = new CustomerList();
        List<Customer> customers = new ArrayList<>();
        customerList.setCustomers(customers);

        CustomerId customerId = new CustomerId();
        customerId.setCustomerId(25L);

        InputCreateCredit inputCreateCredit = new InputCreateCredit();
        inputCreateCredit.setPesel("95061509521");
        inputCreateCredit.setFirstName("Jan");
        inputCreateCredit.setLastName("Kowalski");


  //      when(creditService.searchCustomer(new Pesel())).thenReturn(customerList);
   //     when(creditService.createCustomer(creditService.createObjectCreateCustomer(inputCreateCredit)))
                .thenReturn(customerId);

        //when
        Long id = creditService.getCustomerId(inputCreateCredit);

        //then
        assertEquals(25L,id);


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
    void when_method_getListOfCustomersId_than_return_list_of_customers_ids() {
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

}