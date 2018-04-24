package ru.moysclad.service;

import ru.moysclad.model.Account;
import ru.moysclad.view.View;

import java.rmi.ServerException;

public interface Services {

    void create(View view);

    void deposit(View view);

    void withdraw(View view);

    Account balance(Long name);

    void delete(Long name);
}
