package com.inteca.credit.inputObject.customerList;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
public class CustomerList implements Serializable {

    @JsonAlias("searchResult")
    private List<Customer> customers;

}
