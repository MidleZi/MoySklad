package ru.moysclad.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.moysclad.MyApplication;
import ru.moysclad.dao.DAO;
import ru.moysclad.dao.impl.DAOImpl;
import ru.moysclad.model.Account;
import ru.moysclad.service.impl.ServiceImpl;
import ru.moysclad.utils.ResponseViewData;
import ru.moysclad.view.View;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyApplication.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class ServiceTest {

    @Autowired
    ServiceImpl service;
    DAOImpl dao;


    @Test
    public void testCreateDelete(){
        //create
        View view = new View();
        view.name = 00101L;

        service.create(view);

        List<View> accounts = service.getAllAccounts();

        Assert.assertNotNull(accounts);
        Assert.assertFalse(accounts.isEmpty());
        Assert.assertEquals(6, accounts.size());

        //delete

        service.delete(00101L);

        Assert.assertNotNull(accounts);
        Assert.assertFalse(accounts.isEmpty());
        Assert.assertEquals(5, accounts.size());
    }

    @Test
    public void testDeposit(){
        View view = new View();
        view.name = 00001L;
        view.sum = 15000L;

        service.deposit(view);

        Account account = service.balance(00001L);

        Assert.assertNotNull(account);
        Assert.assertEquals( 16000L, (long)account.getSum());
    }

    @Test
    public void testWithdraw(){
        View view = new View();
        view.name = 00004L;
        view.sum = 15000L;

        service.withdraw(view);

        Account account = service.balance(00004L);

        Assert.assertNotNull(account);
        Assert.assertEquals( 15000L, (long)account.getSum());
    }

    @Test
    public void testBalance(){
        Account account = service.balance(00002L);
        Assert.assertNotNull(account);
        Assert.assertEquals(00002L,(long) account.getName());
    }

}
