package org.example.Grit_Academy_Portal.model;

import java.sql.*;
import java.util.LinkedList;

public class MySQLConnector {

    // Use selectQuery with desired query as an argument to select from database
    public LinkedList<String[]> selectQuery(String query) {
        LinkedList<String[]> queryResult = new LinkedList<String[]>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:13306/gritacademy",
                    "root", "");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();

             // Kanske behöver flyttas in i while-loop

            while (rs.next()) {
                String[] dataRow = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    dataRow[i] = rs.getString(i+1); // rs.getString(i+1) - kanske behövs
                }
                queryResult.add(dataRow);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return queryResult;
    }

    // Use insertQuery with desired query as an argument to insert into database
    public int insertQuery(String query) {
        // affectedRows is used to verify how many rows are created
        int affectedRows = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:13306/gritacademy",
                    "root", "");

            Statement stmt = con.createStatement();
            affectedRows = stmt.executeUpdate(query);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return affectedRows;
    }
}
