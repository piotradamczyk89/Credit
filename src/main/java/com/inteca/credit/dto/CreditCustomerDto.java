package com.inteca.credit.dto;


import com.inteca.credit.credit.Credit;
import com.inteca.credit.customer.Customer;
import com.inteca.credit.responseObject.CreditCustomerAggregate;
import com.inteca.credit.responseObject.CreditId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class CreditCustomerDto {

    private String firstName;
    private String lastName;
    private String pesel;
    private Long customerId;

    private List<Customer> searchResult = null;
    private List<Customer> customers = null;

    private String creditName;
    private Double value = null;


    public void margeToCreateCredit(CreditCustomerDto toMargeDto) {
        if (!toMargeDto.getCreditName().isEmpty()) {
            this.creditName = toMargeDto.getCreditName();
        }
        if (toMargeDto.getValue() != null) {
            this.value = toMargeDto.getValue();
        }
        if (toMargeDto.getCustomerId() != null) {
            this.customerId = toMargeDto.getCustomerId();
        }
        if (toMargeDto.getSearchResult() != null) {
            this.searchResult = toMargeDto.getSearchResult();
        }
    }

    public Credit mapCredit() {
        Credit credit = new Credit();
        credit.setCreditName(creditName);
        credit.setValue(value);
        Customer customer = null;
        if (!searchResult.isEmpty()) {
            customer = new Customer(searchResult.get(0).getId(), searchResult.get(0).getFirstName(),
                    searchResult.get(0).getLastName(), searchResult.get(0).getPesel());
        } else {
            customer = new Customer(customerId, firstName, lastName, pesel);
        }
        credit.setCustomer(customer);
        return credit;
    }

    public List<CreditCustomerAggregate> mapCreditCustomerAggregate (List<Credit> credits) {
        List<CreditCustomerAggregate> aggregates = new ArrayList<>();
        for (Customer customer : customers) {
            for (Credit credit : credits) {
                if(Objects.equals(credit.getCustomer().getId(), customer.getId())) {
                    aggregates.add(new CreditCustomerAggregate(customer.getFirstName(),customer.getLastName()
                            ,customer.getPesel(),credit.getCreditName(),credit.getValue(),credit.getId()));
                }
            }
        }
        return aggregates;
    }

}
