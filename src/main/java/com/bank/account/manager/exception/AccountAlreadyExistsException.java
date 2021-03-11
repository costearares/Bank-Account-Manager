package com.bank.account.manager.exception;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException(String message) {
        super(message);
    }

}
