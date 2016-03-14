package model;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import java.util.List;

import br.com.borges.moises.expensetracker.db.repositories.TransactionRepository;
import br.com.borges.moises.expensetracker.model.Transaction;
import br.com.borges.moises.expensetracker.model.TransactionCategory;

/**
 * Created by Moisés on 07/03/2016.
 */
public class TransactionsRepositoryTest extends AndroidTestCase {

    private TransactionRepository mRepository;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        mRepository = TransactionRepository.getTransactionRepository(context);
    }

    public void testgetTransactions() throws Exception {
        List<Transaction> transactions = mRepository.getTransactions(TransactionCategory.EXPENSE.getValue());
        assertEquals(transactions.size(), 3);
    }
}
