package org.example.Grit_Academy_Portal.model;

import java.sql.*;
import java.util.LinkedList;

public class MySQLConnector {
    // Use selectQuery with desired query as an argument to select from database
    public static LinkedList<String[]> selectQuery(String query, String user, String password) {
        LinkedList<String[]> queryResult = new LinkedList<String[]>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:13306/gritacademy",
                    user, password);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();

            while (rs.next()) {
                String[] dataRow = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    dataRow[i] = rs.getString(i+1);
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
    public static int insertQuery(String query, String user, String password ) {
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
