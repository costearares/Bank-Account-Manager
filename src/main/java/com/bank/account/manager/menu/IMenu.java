package com.bank.account.manager.menu;

import com.bank.account.manager.model.User;

import java.sql.SQLException;

public interface IMenu {

    void chooseOption(User user) throws SQLException;
}
