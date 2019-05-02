package ORMLite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Tutorial:
 * http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_1.html#Getting-Started
 *
 * Requirements:
 * 1. The MySQL Connector/J library must be added to the project. (https://dev.mysql.com/downloads/connector/j/)
 * 2. The ormlite-jdbc-5.1.jar and ormlite-core-5.1.jar must be added to the project. (http://ormlite.com/releases/)
 * 3. A database named "ormliteexample" must be created and hosted at "localhost:3306". The username must be "root" and the password must be blank ("").
 */

public class ORMLite {
    private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/ormliteexample";

    private Dao<Account, String> accountDao;

    public static void main(String[] args) throws Exception {
        new ORMLite().doMain(args);
    }

    private void doMain(String[] args) throws Exception {
        try (JdbcConnectionSource connectionSource = new JdbcConnectionSource(DATABASE_URL, "root", "")) {
            setupDatabase(connectionSource);
            readWriteData();
            System.out.println("\n\nIt seems to have worked\n\n");
        }
    }

    private void setupDatabase(ConnectionSource connectionSource) throws Exception {
        accountDao = DaoManager.createDao(connectionSource, Account.class);

        // if you need to create the table
        TableUtils.createTableIfNotExists(connectionSource, Account.class);
    }

    private void readWriteData() throws Exception {
        Account account1 = new Account();
        account1.setName("Jim Coakley");

        // persist the account object to the database
        accountDao.createIfNotExists(account1);

        // retrieve the account from the database by its id field (name)
        Account account2 = accountDao.queryForId("Jim Coakley");
        System.out.println("Account: " + account2.getName());
    }
}