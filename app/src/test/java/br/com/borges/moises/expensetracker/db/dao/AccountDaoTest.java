package br.com.borges.moises.expensetracker.db.dao;

import android.test.AndroidTestCase;

import org.junit.Before;
import org.junit.Test;

import br.com.borges.moises.expensetracker.model.Account;

import static org.junit.Assert.*;

/**
 * Created by Moisés on 17/12/2015.
 */
public class AccountDaoTest extends AndroidTestCase {
    private AccountDao mAccountDao;
    private Account addedAccount;
    private Account deletedACcount;
    @Before
    public void setUp() throws Exception {
        mAccountDao = AccountDao.getAccountDao(getContext());
        addedAccount = new Account();

    }

    @Test
    public void testGetAccount() throws Exception {

    }

    @Test
    public void testGetAccounts() throws Exception {

    }

    @Test
    public void testAddAccount() throws Exception {

    }

    @Test
    public void testDeleteAccount() throws Exception {

    }

    @Test
    public void testUpdateAccount() throws Exception {

    }
}