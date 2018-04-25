package ru.moysclad.service;

import ru.moysclad.model.Account;
import ru.moysclad.view.View;

import java.rmi.ServerException;
import java.util.List;

public interface Services {

    List<View> getAllAccounts();

    void create(View view);

    void deposit(View view);

    void withdraw(View view);

    Account balance(Long name);

    void delete(Long name);
}
