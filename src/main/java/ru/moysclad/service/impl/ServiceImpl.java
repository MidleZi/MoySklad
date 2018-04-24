package ru.moysclad.service.impl;


import ru.moysclad.model.Account;
import ru.moysclad.service.Services;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.moysclad.dao.DAO;
import ru.moysclad.view.View;
import java.rmi.ServerException;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements Services {

    private final Logger logger = LoggerFactory.getLogger(Service.class);
    private final DAO dao;

    @Autowired
    public ServiceImpl(DAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public Account balance(Long name) {
        logger.info("Account get by name:" + name);
        Account account = dao.balance(name);
        if(dao.balance(name) == null) throw new ServiceException("Аккаунта № " + name + " не существует");
        return account;
    }

    @Override
    @Transactional
    public void create(View view) {
        Account account = new Account(view.name, view.sum);
        logger.info("Create account " + account.toString());
        dao.create(account);

    }

    @Override
    @Transactional
    public void deposit(View view) {
        Long name = view.name;
        Account account = dao.balance(name);
        if(account == null) throw new ServiceException("Аккаунта № " + name + " не существует");
        account.setSum(account.getSum() + view.sum);
        logger.info("Deposit on account" + account.toString());
        dao.deposit(account);
    }

    @Override
    @Transactional
    public void withdraw(View view){
        Long name = view.name;
        Account account = dao.balance(name);
        if(name == null) throw new ServiceException("Аккаунта № " + name + " не существует");
        account.setSum(account.getSum() - view.sum);
        if(account.getSum() < 0) throw new ServiceException("На счету недостаточно средств");
        logger.info("Withdraw on account" + account.toString());
        dao.withdraw(account);

    }

    @Override
    @Transactional
    public void delete(Long name) {
        logger.info("Account name " + name + " deleted");
        Account account = dao.balance(name);
        if(account == null) throw new ServiceException("Аккаунта № " + name + " не существует");
        dao.delete(account);

    }
}
