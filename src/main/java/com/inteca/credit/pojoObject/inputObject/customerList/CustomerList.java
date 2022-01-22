package com.inteca.credit.pojoObject.inputObject.customerList;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class CustomerList implements Serializable {

    @JsonAlias("searchResult")
    private List<Customer> customers;

}
