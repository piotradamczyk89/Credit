package com.inteca.credit.pojoObject.inputObject.customerList;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    private Long customerId;
    private String firstName;
    private String lastName;
    private String pesel;

}
