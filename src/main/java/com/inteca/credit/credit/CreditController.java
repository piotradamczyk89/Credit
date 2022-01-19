package com.inteca.credit.credit;
import com.inteca.credit.dto.CreditCustomerDto;
import com.inteca.credit.responseObject.CreditCustomerAggregate;
import com.inteca.credit.responseObject.CreditId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
public class CreditController {

    private final CreditService creditService;

    @PostMapping("/create")
    public CreditId createCredit (@RequestBody CreditCustomerDto inputDto) {
        Credit credit = creditService.generateCredit(inputDto);
        creditService.saveCredit(credit);
        return new CreditId(credit.getId());
    }

    @GetMapping("/get")
    public List<CreditCustomerAggregate> getCredits () {
        List<Credit> credits = creditService.findAllCredits();
        List<Long> listOfCreditsId = creditService.getListOfCreditsId(credits);
        CreditCustomerDto customersWithCredits = creditService.findCustomersWithCredit(listOfCreditsId);
        return customersWithCredits.mapCreditCustomerAggregate(credits);
    }

}
