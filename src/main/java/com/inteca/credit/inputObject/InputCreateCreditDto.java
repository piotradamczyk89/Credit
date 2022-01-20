package com.inteca.credit.inputObject;


import lombok.Data;

import java.io.Serializable;

@Data
public class InputCreateCreditDto implements Serializable {

    private String firstName;
    private String lastName;
    private String pesel;
    private String creditName;
    private Double value;
}
