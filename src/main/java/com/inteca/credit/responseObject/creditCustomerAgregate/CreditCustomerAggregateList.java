package com.inteca.credit.responseObject.creditCustomerAgregate;


import com.inteca.credit.credit.Credit;
import com.inteca.credit.inputObject.customerList.CustomerList;
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
        List<CreditCustomerAggregate> collect = customerList.getCustomers().stream()
                .map(customer -> new CreditCustomerAggregate(customer
                        , credits.stream().filter(credit -> Objects.equals(credit.getCustomerId(), customer.getId()))
                        .findFirst().get()))
                .collect(Collectors.toList());
        return new CreditCustomerAggregateList(collect);
    }
}
