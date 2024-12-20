package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        final String DATABASE_NAME = "oggy";
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            // ket noi voi database thanh cong
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            System.out.println("Connected to database" + connection);

            //crud - db
            //select

            String sql = "SELECT * FROM pets";
            // interface chuan bi thuc thi chuong trinh
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //resultSet chua du lieu cau lenh vua thuc thi xong
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Timestamp timestamp = resultSet.getTimestamp("date_of_birth");

                System.out.println("ID: " + id + " Name: " + name + " Timestamp: " + timestamp);
            }

            //insert --
            String sqlInsertData = "INSERT INTO pets (name, date_of_birth) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sqlInsertData);

//            preparedStatement.setString(1, "Bird");
//            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));

//            int rowInserted = preparedStatement.executeUpdate();
//            System.out.println("Row inserted: " + rowInserted);

            //update
            String updateSql = "UPDATE pets SET name = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(updateSql);
//            preparedStatement.setString(1, "Fish");
//            preparedStatement.setInt(2, 1);

            int rowUpdated = preparedStatement.executeUpdate();
            System.out.println("Row updated: " + rowUpdated);

            //delete
            String deleteDataSql = "DELETE FROM pets WHERE id = ?";
            preparedStatement = connection.prepareStatement(deleteDataSql);
            preparedStatement.setInt(1, 4);

            int rowDeleted = preparedStatement.executeUpdate();
            System.out.println("Row deleted: " + rowDeleted);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}