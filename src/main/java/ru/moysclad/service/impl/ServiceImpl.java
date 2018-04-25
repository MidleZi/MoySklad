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

            view.id = allOrganization.get(i).getId();
            view.sum = allOrganization.get(i).getSum();


            viewlist.add(view);
        }

        return viewlist;

    }

    @Override
    @Transactional
    public Account balance(String id) {
        logger.info("Account get by name:" + id);
        Account account = dao.balance(id);
        if(account == null) throw new ServiceException("Аккаунта № " + id + " не существует");
        return account;
    }

    @Override
    @Transactional
    public void create(View view) {
        Account account = new Account(view.id);
        logger.info("Create account " + account.toString());
        for (Account acc : dao.getAllAccount()) {
            String accName = acc.getId();
            if (accName.compareTo(account.getId()) == 0)
                throw new ServiceException("Аккаунт № " + account.getId() + " уже существует");
        }
        dao.create(account);
    }

    @Override
    @Transactional
    public void deposit(View view) {
        String id = view.id;
        Account account = dao.balance(id);
        if(account == null) throw new ServiceException("Аккаунта № " + id + " не существует");
        if(view.sum < 0) throw new ServiceException("Невозможно положить отрицтельную сумму!!!");
        account.setSum(account.getSum() + view.sum);
        logger.info("Deposit on account" + account.toString());
        dao.deposit(account);
    }

    @Override
    @Transactional
    public void withdraw(View view){
        String id = view.id;
        Account account = dao.balance(id);
        if(account == null) throw new ServiceException("Аккаунта № " + id + " не существует");
        if(view.sum < 0) throw new ServiceException("Невозможно снять отрицтельную сумму!!!");
        account.setSum(account.getSum() - view.sum);
        if(account.getSum() < 0) throw new ServiceException("На счету недостаточно средств");
        logger.info("Withdraw on account" + account.toString());
        dao.withdraw(account);

    }

    @Override
    @Transactional
    public void delete(String id) {
        logger.info("Account name " + id + " deleted");
        Account account = dao.balance(id);
        if(account == null) throw new ServiceException("Аккаунта № " + id + " не существует");
        if(account.getSum() != 0) throw new ServiceException("На счету есть средства, удаление невозможно");
        dao.delete(account);

    }
}
