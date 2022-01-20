package com.inteca.credit.requestObject;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomer implements Serializable {

    private String firstName;
    private String lastName;
    private String pesel;
}
