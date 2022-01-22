package com.inteca.credit.pojoObject.inputObject;


import lombok.Data;

import java.io.Serializable;

@Data
public class InputCreateCredit implements Serializable {

    private String firstName;
    private String lastName;
    private String pesel;
    private String creditName;
    private Double value;
}
