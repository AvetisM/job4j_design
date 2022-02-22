package ru.job4j.jdbc;

import ru.job4j.io.Config;

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
        Config config = new Config("app.properties");
        config.load();
        properties.put("driver_class", config.value("hibernate.connection.driver_class"));
        properties.put("url", config.value("hibernate.connection.url"));
        properties.put("username", config.value("hibernate.connection.username"));
        properties.put("password", config.value("hibernate.connection.password"));
        try {
            Class.forName(properties.get("driver_class").toString());
            connection = DriverManager.getConnection(
                    properties.get("url").toString(),
                    properties.get("username").toString(),
                    properties.get("password").toString());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "create table if not exists %s()",
                    tableName
            );
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "drop table if exists %s cascade",
                    tableName
            );
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table %s add column %s %s",
                    tableName,
                    columnName,
                    type
            );
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table %s drop column %s",
                    tableName,
                    columnName
            );
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table %s rename column %s to %s",
                    tableName,
                    columnName,
                    newColumnName

            );
            statement.execute(sql);
        }
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

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        TableEditor tableEditor = new TableEditor(properties);

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