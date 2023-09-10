package data;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class SQLHelper {
    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");
    private static final QueryRunner runner = new QueryRunner();

    public static void clearTables() throws SQLException {
        try (Connection conn = getConn()) {
            String deleteOrderEntity = "DELETE FROM order_entity;";
            String deletePaymentEntity = "DELETE FROM payment_entity;";
            String deleteCreditRequestEntity = "DELETE FROM credit_request_entity;";
            runner.update(conn, deleteOrderEntity);
            runner.update(conn, deletePaymentEntity);
            runner.update(conn, deleteCreditRequestEntity);
        }
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
@SneakyThrows
    public static String findPayStatus() {
        String statusSQL = "SELECT status FROM payment_entity;";
        return getData(statusSQL);
    }

    public static String findCreditStatus() throws SQLException {
        String statusSQL = "SELECT status FROM credit_request_entity;";
        return getData(statusSQL);
    }

    private static String getData(String query) throws SQLException {
        try (Connection conn = getConn()) {
            return runner.query(conn, query, new ScalarHandler<>());
        }
    }
}