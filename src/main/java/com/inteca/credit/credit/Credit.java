package com.inteca.credit.credit;

import com.inteca.credit.customer.Customer;
import lombok.Data;


import javax.persistence.*;


@Data
@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String creditName;
    private Double value;

    @ManyToOne
    private Customer customer;
}
