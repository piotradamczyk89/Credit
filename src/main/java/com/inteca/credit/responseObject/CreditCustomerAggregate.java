package com.inteca.credit.responseObject;


import lombok.Data;

@Data
public class CreditCustomerAggregate {


    private String firstName;
    private String lastName;
    private String pesel;
    private String creditName;
    private Double value;
    private Long creditId;

    public CreditCustomerAggregate(String firstName, String lastName, String pesel, String creditName, Double value, Long creditId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.creditName = creditName;
        this.value = value;
        this.creditId = creditId;
    }
}
