package com.inteca.credit.credit;

import com.inteca.credit.pojoObject.inputObject.InputCreateCredit;

import com.inteca.credit.pojoObject.inputObject.customerList.CustomerList;
import com.inteca.credit.pojoObject.responseObject.CreditId;
import com.inteca.credit.pojoObject.responseObject.creditCustomerAgregate.CreditCustomerAggregateList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
public class CreditController {

    private final CreditService creditService;

    @PostMapping("/create")
    public CreditId createCredit (@RequestBody InputCreateCredit inputCreateCredit) {
        if(!creditService.checkInputData(inputCreateCredit)) {
            return new CreditId(null);
        } else {
            Long customerId = creditService.getCustomerId(inputCreateCredit);
            Credit credit = creditService.saveCredit(new Credit(inputCreateCredit.getCreditName()
                    , inputCreateCredit.getValue(), customerId));
            return new CreditId(credit.getId());
        }
    }

    @GetMapping("/get")
    public CreditCustomerAggregateList getCredits () {
        List<Credit> credits = creditService.findAllCredits();
        CustomerList customerList = creditService.getCustomers(credits);
        return CreditCustomerAggregateList.createAggregateList(credits,customerList);
    }

}
