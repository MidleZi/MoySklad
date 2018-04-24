package ru.moysclad.dao;

import ru.moysclad.model.Account;

import java.util.List;

public interface DAO {

    List<Account> getAllAccount();

    void create(Account account);

    void deposit(Account account);

    void withdraw(Account account);

    Account balance(Long name);

    void delete(Account account);
}
