package com.inteca.credit.responseObject.creditCustomerAgregate;


import com.inteca.credit.credit.Credit;
import com.inteca.credit.inputObject.customerList.Customer;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreditCustomerAggregate implements Serializable {


    private String firstName;
    private String lastName;
    private String pesel;
    private String creditName;
    private Double value;
    private Long creditId;
    private Long customerId;

    public CreditCustomerAggregate( Customer customer, Credit credit) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.pesel = customer.getPesel();
        this.creditName = credit.getCreditName();
        this.value = credit.getValue();
        this.creditId = credit.getId();
        this.customerId = customer.getId();
    }
}
