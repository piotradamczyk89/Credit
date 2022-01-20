package com.inteca.credit.inputObject.customerList;
import lombok.*;

import java.io.Serializable;

@Data

public class Customer implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;

}
