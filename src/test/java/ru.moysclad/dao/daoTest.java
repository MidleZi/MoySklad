package ru.moysclad.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.moysclad.MyApplication;
import ru.moysclad.model.Account;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyApplication.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class daoTest {

    @Autowired
    private DAO dao;

    @Test
    public void test() {
        //test get all
        List<Account> accounts = dao.getAllAccount();
        Assert.assertNotNull(accounts);
        Assert.assertEquals(5, accounts.size());

        //test balance
        Account account = dao.balance("00001");
        Assert.assertNotNull(account);
        Assert.assertEquals("00001", account.getId());

        //test create
        Account createTestAccount = new Account();
        createTestAccount.setId("00101");
        dao.create(createTestAccount);
        accounts = dao.getAllAccount();
        Assert.assertEquals(6, accounts.size());


        //test delete
        dao.delete(dao.balance("00005"));
        accounts = dao.getAllAccount();
        Assert.assertEquals(5, accounts.size());


        //test deposit
        Account depositTestAccount = dao.balance("00002");
        depositTestAccount.setSum(15000L);
        Assert.assertNotNull(depositTestAccount);
        Long sumForUpdate = 20000L;
        depositTestAccount.setSum(sumForUpdate);
        dao.deposit(depositTestAccount);
        Account accountAfterDeposit = dao.balance("00002");
        Assert.assertNotNull(accountAfterDeposit);
        Assert.assertEquals(20000L, (long) accountAfterDeposit.getSum());


        //test withdraw
        Account withdrawTestAccount = dao.balance("00003");
        withdrawTestAccount.setSum(15000L);
        Assert.assertNotNull(withdrawTestAccount);
        sumForUpdate = 10000L;
        withdrawTestAccount.setSum(sumForUpdate);
        dao.deposit(withdrawTestAccount);
        Account accountAfterWithdraw = dao.balance("00003");
        Assert.assertNotNull(accountAfterWithdraw);
        Assert.assertEquals(10000L, (long) accountAfterWithdraw.getSum());
    }
}
