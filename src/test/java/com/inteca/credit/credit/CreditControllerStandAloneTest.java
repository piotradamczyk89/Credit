package com.inteca.credit.credit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inteca.credit.pojoObject.inputObject.InputCreateCredit;
import com.inteca.credit.pojoObject.inputObject.customerList.Customer;
import com.inteca.credit.pojoObject.inputObject.customerList.CustomerList;
import com.inteca.credit.pojoObject.responseObject.creditCustomerAgregate.CreditCustomerAggregateList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringRunner.class)
class CreditControllerStandAloneTest {

    private static CreditController creditController;
    private CreditService creditService;

    @BeforeEach
    public void onSetUp() {
        creditService = Mockito.mock(CreditService.class);
        creditController = new CreditController(creditService);

    }

    @Test
    void test_create_credit_action_return_creditId() throws Exception {
        //given
        InputCreateCredit input = new InputCreateCredit("Jan", "Kowalski"
                , "89060855978", "hipoteka", 1600.0);


        ObjectMapper mapper = new ObjectMapper();
        String inputJsonString = "";
        try {
            inputJsonString = mapper.writeValueAsString(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MockMvc mockMvc = standaloneSetup(creditController).build();

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/credit/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJsonString))
        //then
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "  \"creditId\": null\n" +
                        "}"))
                .andDo(print());

    }

    @Test
    void test_get_Credits_action_return_CreditCustomerAggregateList() throws Exception {

        //given

        List<Credit> credits = new ArrayList<>();


        CustomerList customerList = new CustomerList();
        List<Customer> customers = new ArrayList<>();

        customerList.setCustomers(customers);

        CreditCustomerAggregateList aggregateList = CreditCustomerAggregateList.createAggregateList(credits, customerList);

        ObjectMapper mapper = new ObjectMapper();
        String aggregateListJsonString = "";
        try {
            aggregateListJsonString = mapper.writeValueAsString(aggregateList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MockMvc mockMvc = standaloneSetup(creditController).build();

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/credit/get").contentType(MediaType.APPLICATION_JSON))
        //then
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(aggregateListJsonString))
                .andDo(print());
    }



}