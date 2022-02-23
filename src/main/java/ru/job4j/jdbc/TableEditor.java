package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("hibernate.connection.driver_class"));
            connection = DriverManager.getConnection(
                    properties.getProperty("hibernate.connection.url"),
                    properties.getProperty("hibernate.connection.username"),
                    properties.getProperty("hibernate.connection.password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "create table if not exists %s()",
                tableName
        );
        executeQuery(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "drop table if exists %s cascade",
                tableName
        );
        executeQuery(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "alter table %s add column %s %s",
                tableName,
                columnName,
                type
        );
        executeQuery(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "alter table %s drop column %s",
                tableName,
                columnName
        );
        executeQuery(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format(
                "alter table %s rename column %s to %s",
                tableName,
                columnName,
                newColumnName

        );
        executeQuery(sql);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public void executeQuery(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("app.properties")) {
            properties.load(fis);
        }

        try (TableEditor tableEditor = new TableEditor(properties)) {

            tableEditor.dropTable("students");

            tableEditor.createTable("students");
            System.out.println(getTableScheme(tableEditor.connection, "students"));
            tableEditor.addColumn("students", "name", "text");
            System.out.println(getTableScheme(tableEditor.connection, "students"));
            tableEditor.renameColumn("students", "name", "surname");
            System.out.println(getTableScheme(tableEditor.connection, "students"));
            tableEditor.dropColumn("students", "surname");
            System.out.println(getTableScheme(tableEditor.connection, "students"));

            tableEditor.dropTable("students");
        }
    }
}