package br.com.borges.moises.expensetracker.db.repositories;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.borges.moises.expensetracker.model.Account;
import br.com.borges.moises.expensetracker.model.AccountType;

/**
 * Created by Moisés on 17/12/2015.
 */
public class AccountRepositoryTest extends AndroidTestCase {
    private AccountRepository mAccountRepository;
    private Account addedAccount;
    private int id;

    @Before
    public void setUp() throws Exception {
        mAccountRepository = AccountRepository.getAccountRepository(getContext());
        addedAccount = new Account("Wallet", 150.00, AccountType.CASH);
        id = (int) mAccountRepository.addAccount(addedAccount);
    }

    @Test
    public void testGetAccount() throws Exception {
        Account account = mAccountRepository.getAccount(id);
        Assert.assertNotNull(account);
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