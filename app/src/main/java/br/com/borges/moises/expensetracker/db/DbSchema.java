package br.com.borges.moises.expensetracker.db;


import java.util.Date;

/**
 * Created by Moisés on 05/12/2015.
 */
public class DbSchema {

    private static final String COMMA_SEP = ",";
    private static final String FOREIGN_KEY = "foreign key";
    private static final String REFERENCES = "references";
    private static final String UNIQUE = "unique";
    private static final String DOT = ".";

    // TODO: remove in the future
    public static final class ExpenseTable {
        public static final String NAME = "expenses";

        public static final class Columns {
            public static final String ID = "id";
            public static final String DESCRIPTION = "description";
            public static final String AMOUNT = "amount";
        }
    }

    public static final class UserTable {
        public static final String NAME = "users";

        public static final class Columns {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String EMAIL = "email";
        }

        public static final class Sql {
            public static final String CREATE_TABLE =
                    "create table if not exists " + NAME + "(" +
                    Columns.ID + " integer primary key autoincrement" + COMMA_SEP +
                    Columns.NAME + COMMA_SEP +
                    Columns.EMAIL + ")";

            public static final String DELETE_TABLE = "drop table if exists " + NAME;
        }
    }

    public static final class AccountTable {
        public static final String NAME = "accounts";

        public static final class Columns {
            public static final String ID = "id";
            public static final String ID_WITH_PREFIX = NAME + DOT + ID;
            public static final String TITLE = "title";
            public static final String TITLE_WITH_PREFIX = NAME + DOT + TITLE;
            public static final String OPENING_BALANCE = "opening_balance";
            public static final String OPENING_BALANCE_WITH_PREFIX = NAME + DOT + OPENING_BALANCE;
            public static final String TYPE = "type";
            public static final String TYPE_WITH_PREFIX = NAME + DOT + TYPE;
        }

        public static final class Sql {
            public static final String CREATE_TABLE =
                    "create table if not exists " + NAME + "(" +
                    Columns.ID + " integer primary key autoincrement" + COMMA_SEP +
                    Columns.TITLE + COMMA_SEP +
                    Columns.OPENING_BALANCE + COMMA_SEP +
                    Columns.TYPE + COMMA_SEP +
                    FOREIGN_KEY + "(" + Columns.TYPE + ") " + REFERENCES + " " + AccountTypeTable.NAME + "(" + AccountTypeTable.Columns.ID + "))";

            public static final String DELETE_TABLE = "drop table if exists " + NAME;

            public static final String INSERT_INITIAL_DATA = "insert into " + NAME + " values(?,?,?,?);";

        }
    }

    public static final class AccountTypeTable {
        public static final String NAME = "accounttypes";

        public static final class Columns {
            public static final String ID = "id";
            public static final String DESCRIPTION = "description";
        }

        public static final class Sql {

            public static final String CREATE_TABLE = "create table if not exists " + NAME + "(" +
                    Columns.ID + " integer primary key autoincrement" + COMMA_SEP +
                    Columns.DESCRIPTION + ")";

            public static final String DELETE_TABLE = "drop table if exists " + NAME;

            public static final String INSERT_INITIAL_DATA =
                    "insert into " + NAME + " values(?, ?);";
        }
    }

    public static final class CategoryTable {
        public static final String NAME = "categories";

        public static final class Columns {
            public static final String ID = "id";
            public static final String ID_WITH_PREFIX = NAME + DOT + ID;
            public static final String TYPE = "type";
            public static final String TYPE_WITH_PREFIX = NAME + DOT + TYPE;
            public static final String DESCRIPTION = "description";
            public static final String DESCRIPTTION_WITH_PREFIX = NAME + DOT + DESCRIPTION;
        }

        public static final class Sql {
            public static final String CREATE_TABLE =
                    "create table if not exists " + NAME + "(" +
                            Columns.ID + " integer primary key autoincrement" + COMMA_SEP +
                            Columns.DESCRIPTION + " " + UNIQUE + COMMA_SEP +
                            Columns.TYPE + COMMA_SEP +
                            FOREIGN_KEY + "(" + Columns.TYPE + ") " + REFERENCES + " " + CategoryTypeTable.NAME + "(" + CategoryTypeTable.Columns.ID + "))";

            public static final String DELETE_TABLE = "drop table if exists " + NAME;

            public static final String INSERT_INITIAL_DATA = "insert into " + NAME + " values(?, ?, ?);";

            public static Object[] getInsertParams(int id, String category, int typeId) {
                return new Object[] {id, category,typeId};
            }
        }
    }

    public static final class CategoryTypeTable {
        public static final String NAME = "categorytypes";

        public static final class Columns {
            public static final String ID = "id";
            public static final String ID_WITH_PREFIX = NAME + DOT + ID;
            public static final String DESCRIPTION = "description";
            public static final String DESCRIPTION_WITH_PREFIX = NAME + DOT + DESCRIPTION;
        }

        public static final class Values {
            public static final int EXPENSE_ID = 1;
            public static final int INCOME_ID = 2;
        }

        public static final class Sql {
            public static final String CREATE_TABLE =
                    "create table if not exists " + NAME + "(" +
                            Columns.ID + " integer primary key autoincrement" + COMMA_SEP +
                            Columns.DESCRIPTION + " " + UNIQUE + ")";

            public static final String DELETE_TABLE = "drop table if exists " + NAME;

            public static final String INSERT_INITIAL_DATA = "insert into " + NAME + " values(?, ?);";

            public static Object[] getInsertParams(int id, String description) {
                return new Object[] {id, description};
            }
        }
    }

    public static final class TransactionTable {
        public static final String NAME = "transactions";

        public static final class Columns {
            public static final String ID = "id";
            public static final String ID_WITH_PREFIX = NAME + DOT + ID;
            public static final String DESCRIPTION = "description";
            public static final String DESCRIPTION_WITH_PREFIX = NAME + DOT + DESCRIPTION;
            public static final String AMOUNT = "amount";
            public static final String AMOUNT_WITH_PREFIX = NAME + DOT + AMOUNT;
            public static final String DATE = "date";
            public static final String DATE_WITH_PREFIX = NAME + DOT + DATE;
            public static final String ACCOUNT = "account";
            public static final String ACCOUNT_WITH_PREFIX = NAME + DOT + ACCOUNT;
            public static final String TYPE = "type";
            public static final String TYPE_WITH_PREFIX = NAME + DOT + TYPE;
        }

        public static final class Sql {
            public static final String CREATE_TABLE =
                    "create table if not exists " + NAME + "(" +
                    Columns.ID + " integer primary key autoincrement" + COMMA_SEP +
                    Columns.DESCRIPTION + COMMA_SEP +
                    Columns.ACCOUNT + COMMA_SEP +
                    Columns.AMOUNT + COMMA_SEP +
                    Columns.DATE + COMMA_SEP +
                    Columns.TYPE + COMMA_SEP +
                    FOREIGN_KEY + "(" + Columns.ACCOUNT + ") " + REFERENCES + " " + AccountTable.NAME + "(" + AccountTable.Columns.ID + ")" + COMMA_SEP +
                    FOREIGN_KEY + "(" + Columns.TYPE + ") " + REFERENCES + " " + TransactionCategoryTable.NAME + "(" + TransactionCategoryTable.Columns.ID + "))";

            public static final String DELETE_TABLE = "drop table if exists " + NAME;

            public static final String INSERT_VALUE = "insert into " + NAME + " values(?,?,?,?,?,?);";

            public static Object[] getInsertParams(int id, String details, int accountId, double amount, String date, int typeId) {
                return new Object[] {id, details, accountId,  amount, date, typeId};
            }
        }
    }

    public static final class TransactionCategoryTable {
        public static final String NAME = "transactioncategories";

        public static final class Columns {
            public static final String ID = "id";
            public static final String ID_WITH_PREFIX = TransactionCategoryTable.NAME + DOT + ID;
            public static final String NAME = "name";
            public static final String NAME_WITH_PREFIX = TransactionCategoryTable.NAME + DOT + NAME;
        }

        public static final class Sql {
            public static final String CREATE_TABLE =
                    "create table if not exists " + NAME + "(" +
                            Columns.ID + " integer primary key autoincrement" + COMMA_SEP +
                            Columns.NAME + ")";

            public static final String DELETE_TABLE = "drop table if exists " + NAME;

            public static final String INSERT_VALUES = "insert into " + NAME + " values(?, ?);";

            public static Object[] getInsertParams(int id, String name) {
                return new Object[] {id, name};
            }

        }
    }

    public static final class TransferTable {
        public static final String NAME = "transfers";

        public static final class Columns {
            public static final String ID = "id";
            public static final String ID_WITH_PREFIX = NAME + DOT + ID;
            public static final String TRANSFER_IN = "transfer_id";
            public static final String TRANSFER_IN_WITH_PREFIX = NAME + DOT + TRANSFER_IN;
            public static final String TRANSFER_OUT = "transfer_out";
            public static final String TRANSFER_OUT_WITH_PREFIX = NAME + DOT + TRANSFER_OUT;
        }

        public static final class Sql {
            public static final String CREATE_TABLE =
                    "create table if not exists " + NAME + "(" +
                            Columns.ID + " integer primary key autoincrement" + COMMA_SEP +
                            Columns.TRANSFER_IN + COMMA_SEP +
                            Columns.TRANSFER_OUT + COMMA_SEP +
                            FOREIGN_KEY + "(" + Columns.TRANSFER_IN + ") " + REFERENCES + " " + TransactionTable.NAME + "(" + TransactionTable.Columns.ID + ")" + COMMA_SEP +
                            FOREIGN_KEY + "(" + Columns.TRANSFER_OUT + ") " + REFERENCES + " " + TransactionTable.NAME + "(" + TransactionTable.Columns.ID + "))";

            public static final String DELETE_TABLE = "drop table if exists " + NAME;

            public static final String INSERT_INITIAL_DATA = "insert into " + NAME + "(" + Columns.TRANSFER_IN + COMMA_SEP + Columns.TRANSFER_OUT + ")" + " values(?, ?);";

            public static Object[] getInsertParams(int transferInId, int transferOutId) {
                return new Object[] {transferInId, transferOutId};
            }
        }
    }
}
