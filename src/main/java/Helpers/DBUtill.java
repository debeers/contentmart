package Helpers;
import Entities.Registry;

import java.io.IOException;
import java.sql.*;

/**
 * Created by DeBeers on 01.12.2015.
 */
public class DBUtill {

    Connection connection = (Connection) Registry.get("dbConnection");

    public DBUtill(){}

    public DBUtill(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ResultSet getResultSet(String sqlQuery) throws IOException, SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        return resultSet;
    }

    public void executeUpdate(String insertTableSQL) throws SQLException, IOException {
        Statement statement = null;
        try {
            statement = connection.createStatement();

            System.out.println(insertTableSQL);
            statement.executeUpdate(insertTableSQL);

            System.out.println("Record updated!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public String getColumn(String query, String column) {
        Statement statement;
        try {

            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString(column));
                return rs.getString(column);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
