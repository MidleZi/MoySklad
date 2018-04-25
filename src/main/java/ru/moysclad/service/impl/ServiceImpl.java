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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<View> getAllAccounts() {

        List<Account> allOrganization = dao.getAllAccount();
        List<View> viewlist = new ArrayList();


        if(allOrganization == null) throw new ServiceException("Сотрудников в базе нет");

        for (int i = 0; i <allOrganization.size() ; i++) {

            View view = new View();

            view.name = allOrganization.get(i).getName();
            view.sum = allOrganization.get(i).getSum();


            viewlist.add(view);
        }
        //logger.info("User get ID:" + id);
        return viewlist;

    }

    @Override
    @Transactional
    public Account balance(Long name) {
        logger.info("Account get by name:" + name);
        Account account = dao.balance(name);
        if(account.getName() == null) throw new ServiceException("Аккаунта № " + name + " не существует");
        return account;
    }

    @Override
    @Transactional
    public void create(View view) {
        Account account = new Account(view.name);
        logger.info("Create account " + account.toString());
        for (Account acc : dao.getAllAccount()) {
            Long accName = acc.getName();
            if (accName.equals(account.getName()))
                throw new ServiceException("Аккаунт № " + account.getName() + " уже существует");
            dao.create(account);
        }
    }

    @Override
    @Transactional
    public void deposit(View view) {
        Long name = view.name;
        Account account = dao.balance(name);
        if(account == null) throw new ServiceException("Аккаунта № " + name + " не существует");
        if(view.sum < 0) throw new ServiceException("Невозможно положить отрицтельную сумму!!!");
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
        if(view.sum < 0) throw new ServiceException("Невозможно снять отрицтельную сумму!!!");
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
        if(account.getSum() != 0) throw new ServiceException("На счету есть средства, удаление невозможно");
        dao.delete(account);

    }
}
