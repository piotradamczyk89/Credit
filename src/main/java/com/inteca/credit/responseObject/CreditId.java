package com.inteca.credit.responseObject;

import com.inteca.credit.customer.Customer;
import lombok.Data;

import java.util.List;


@Data
public class CreditId {
    private Long creditId;

    public CreditId(Long creditId) {
        this.creditId = creditId;
    }
}
