package com.github.braisdom.objsql.example;

import com.github.braisdom.objsql.ConnectionFactory;
import com.github.braisdom.objsql.Databases;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.github.braisdom.objsql.Databases.installConnectionFactory;

public class SQLiteExample {

    private static final File DATABASE_FILE = new File("objective_sql.db");

    private static class SqliteConnectionFactory implements ConnectionFactory {

        private final String fileName;

        public SqliteConnectionFactory(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public Connection getConnection(String dataSourceName) throws SQLException {
            try {
                Class.forName("org.sqlite.JDBC");
                return DriverManager.getConnection("jdbc:sqlite:./" + fileName);
            } catch (SQLException e) {
                throw e;
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
    }

    public static void initializeSchemas() throws SQLException {
        Databases.execute("drop table if exists members;");
        Databases.execute("drop table if exists orders");
        Databases.execute("drop table if exists order_lines");
        Databases.execute("create table members (id INTEGER PRIMARY KEY AUTOINCREMENT, no TEXT, " +
                "name TEXT, gender INTEGER, mobile TEXT, extended_attributes TEXT)");
        Databases.execute("create table orders (id INTEGER PRIMARY KEY AUTOINCREMENT, no TEXT, member_id INTEGER, " +
                "amount REAL, quantity REAL, sales_at TEXT)");
        Databases.execute("create table order_lines (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "order_no TEXT, amount REAL, quantity REAL)");
    }

    public static void main(String[] args) throws SQLException {
        if (DATABASE_FILE.exists()) DATABASE_FILE.delete();

        installConnectionFactory(new SqliteConnectionFactory(DATABASE_FILE.getName()));
        initializeSchemas();

        PersistenceExample.run();
        QueryExample.run();
        RelationExample.run();
    }
}