package com.company;

import java.sql.*;
import java.sql.Statement;

public class DBHandler {
    private Statement stmt;
    public Connection connection;

    public void connect() { // Соединяемся с базой
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            stmt = connection.createStatement();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect () {
        try {
            connection.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertStat (int countMoves) { // Будем возвращать количество вставленных строк
        try {
            String sql = "INSERT INTO stats (count_moves) VALUES (?);";
            PreparedStatement ps = connection.prepareStatement(sql); // Сделаем подготовленный запрос
            ps.setInt(1, countMoves);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}