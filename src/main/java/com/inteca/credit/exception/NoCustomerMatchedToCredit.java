package com.inteca.credit.exception;

public class NoCustomerMatchedToCredit extends RuntimeException{

    public NoCustomerMatchedToCredit(String message) {
        super(message);
    }
}
