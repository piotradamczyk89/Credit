package com.inteca.credit.credit;

import com.inteca.credit.inputObject.InputCreateCreditDto;

import com.inteca.credit.inputObject.customerList.CustomerList;
import com.inteca.credit.requestObject.CustomerIdList;


import com.inteca.credit.responseObject.creditCustomerAgregate.CreditCustomerAggregate;
import com.inteca.credit.responseObject.CreditId;
import com.inteca.credit.responseObject.creditCustomerAgregate.CreditCustomerAggregateList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
public class CreditController {

    private final CreditService creditService;

    @PostMapping("/create")
    public CreditId createCredit (@RequestBody InputCreateCreditDto inputCreateCreditDto) {
        Long customerId = creditService.getCustomerId(inputCreateCreditDto);
        Credit credit = creditService.saveCredit(new Credit(inputCreateCreditDto.getCreditName()
                , inputCreateCreditDto.getValue(), customerId));
        return new CreditId(credit.getId());
    }

    @GetMapping("/get")
    public CreditCustomerAggregateList getCredits () {
        List<Credit> credits = creditService.findAllCredits();
        CustomerList customerList = creditService.grtCustomers(credits);
        return CreditCustomerAggregateList.createAggregateList(credits,customerList);
    }

}
