package br.com.borges.moises.expensetracker.db;

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
            public static final String DESCRIPTION = "description";
            public static final String OPENING_BALANCE = "opening_balance";
            public static final String TYPE = "type";
        }

        public static final class Sql {
            public static final String CREATE_TABLE =
                    "create table if not exists " + NAME + "(" +
                    Columns.ID + " integer primary key autoincrement" + COMMA_SEP +
                    Columns.DESCRIPTION + COMMA_SEP +
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
            public static final int TRANSFER_IN_ID = 3;
            public static final int TRANSFER_OUT_ID = 4;
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
            public static final String DESCRIPTION = "description";
            public static final String AMOUNT = "amount";
            public static final String ACCOUNT = "account";
            public static final String TYPE = "type";
        }

        public static final class Sql {
            public static final String CREATE_TABLE =
                    "create table if not exists " + NAME + "(" +
                    Columns.ID + " integer primary key autoincrement" + COMMA_SEP +
                    Columns.DESCRIPTION + COMMA_SEP +
                    Columns.ACCOUNT + COMMA_SEP +
                    Columns.AMOUNT + COMMA_SEP +
                    Columns.TYPE + ")";

            public static final String DELETE_TABLE = "drop table if exists " + NAME;
        }

        public static final class TypeValues {
            public static final int EXPENSE = 1;
            public static final int INCOME = 2;
            public static final int TRANSFER_IN = 3;
            public static final int TRANSFER_OUT = 4;
        }


    }
}
