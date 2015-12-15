package br.com.borges.moises.expensetracker.db;

/**
 * Created by Moisés on 05/12/2015.
 */
public class DbSchema {

    public static final class ExpenseTable {
        public static final String NAME = "expenses";

        public static final class Columns {
            public static final String ID = "id";
            public static final String DESCRIPTION = "description";
            public static final String AMOUNT = "amount";
        }
    }
}
