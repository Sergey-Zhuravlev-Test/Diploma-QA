package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBHelper {
    private static final QueryRunner runner = new QueryRunner();
    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");

    @SneakyThrows

    public static Connection getConn() {
        return DriverManager.getConnection(url, user, password);
//    return DriverManager.getConnection("jdbc:postgres://localhost:5432/app", "app", "pass") - for PostgresSQL;
    }

    @SneakyThrows
    public static void clearDB() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
        runner.execute(connection, "DELETE FROM credit_request_entity");
    }

    @SneakyThrows
    public static String getPaymentQuery() {
        var query = "SELECT status FROM payment_entity";
        var status = runner.query(getConn(), query, new ScalarHandler<String>());
        return status;

    }

    @SneakyThrows
    public static String getCreditQuery() {
        var query = "SELECT status FROM credit_request_entity";
        var status = runner.query(getConn(), query, new ScalarHandler<String>());
        return status;

    }

}
