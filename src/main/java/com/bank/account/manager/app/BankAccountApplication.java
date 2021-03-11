package com.bank.account.manager.app;

import com.bank.account.manager.menu.Login;

public class BankAccountApplication {

    private static final Login login = new Login();

    public static void main(String[] args) {
        login.initialize();
    }
}
