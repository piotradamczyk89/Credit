package com.inteca.credit.pojoObject.inputObject;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputCreateCredit implements Serializable {

    private String firstName;
    private String lastName;
    private String pesel;
    private String creditName;
    private Double value;
}
