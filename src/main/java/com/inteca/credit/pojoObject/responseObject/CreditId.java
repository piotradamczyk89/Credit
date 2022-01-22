package com.inteca.credit.pojoObject.responseObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditId implements Serializable {

    private Long creditId;

}
