package com.inteca.credit.pojoObject.responseObject.creditCustomerAgregate;


import com.inteca.credit.credit.Credit;
import com.inteca.credit.exception.NoCustomerMatchedToCredit;
import com.inteca.credit.pojoObject.inputObject.customerList.CustomerList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCustomerAggregateList implements Serializable {

    private List<CreditCustomerAggregate> creditCustomerAggregates;

    public static CreditCustomerAggregateList createAggregateList (List<Credit> credits, CustomerList customerList) {

        List<CreditCustomerAggregate> collect = credits.stream().map(credit -> new CreditCustomerAggregate(
                        customerList.getCustomers().stream()
                                .filter(customer -> Objects.equals(customer.getCustomerId(), credit.getCustomerId()))
                                .findFirst()
                                .orElseThrow(() -> new NoCustomerMatchedToCredit("nie znaleziono customera"))
                        , credit))
                .collect(Collectors.toList());
        return new CreditCustomerAggregateList(collect);
    }
}
