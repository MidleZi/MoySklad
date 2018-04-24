package ru.moysclad.dao.impl;

import org.springframework.stereotype.Repository;
import ru.moysclad.dao.DAO;
import ru.moysclad.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DAOImpl implements DAO {

    private final Logger logger = LoggerFactory.getLogger(DAOImpl.class);

    private final EntityManager em;

    @Autowired
    public DAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Account> getAllAccount() {
        TypedQuery<Account> query = em.createNamedQuery("Account.findAll", Account.class);
        List<Account> result = query.getResultList();

        return result;
    }

    @Override
    public void create(Account account) {
        logger.info("save:" + account.toString());
        em.persist(account);
    }

    @Override
    public void deposit(Account account) {
        logger.info("deposit " + account.toString());
        em.merge(account);
    }

    @Override
    public void withdraw(Account account) {
        logger.info("withdraw " + account.toString());
        em.merge(account);
    }

    @Override
    public Account balance(Long name) {
        logger.info("Account get ID:" + name);
        return em.find(Account.class, name);
    }

    @Override
    public void delete(Account account) {
        logger.info("Account name " + account.getName() + " deleted");
        em.remove(account);
    }

}