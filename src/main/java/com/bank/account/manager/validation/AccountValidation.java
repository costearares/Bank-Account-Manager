package com.bank.account.manager.validation;

public class AccountValidation {

    public boolean checkAccountType(String accountType) {
        String type = accountType.toUpperCase().trim();
        if (type.equals("RON") || type.equals("EUR")) {
            return true;
        }
        System.out.println("Invalid type.");
        return false;
    }

}
