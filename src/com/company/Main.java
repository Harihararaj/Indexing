package com.company;
import java.sql.*;
public class Main {

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn= null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:reference6.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Statement stm=null;
        try {
            stm=conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ResultSet rs;
        try {
            stm.executeUpdate("CREATE TABLE students(id INT PRIMARY KEY,name VARCHAR)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        insertion(stm,1,"hari");
        insertion(stm,2,"kamal");
        insertion(stm,3,"vijay");
        insertion(stm,4,"ajith");
        insertion(stm,5,"surya");
        insertion(stm,6,"vikram");
        insertion(stm,7,"dhanush");
        insertion(stm,8,"simbu");
        insertion(stm,9,"karthi");
        stm.executeUpdate("CREATE INDEX idx_name ON students(name)");
        printingTableValues(stm);
        rs=stm.executeQuery("SELECT name as n FROM students WHERE id IN (SELECT id FROM students)");
        while(rs.next()) {
            System.out.println(rs.getString("n"));
        }
        stm.executeUpdate("DROP INDEX idx_name");

    }
    static void insertion(Statement stm,int id,String name){
        try {
            stm.executeUpdate("INSERT INTO students VALUES("+id+",'"+name+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }
    static void printingTableValues(Statement stm) throws SQLException {
        ResultSet rs=null;
        rs=stm.executeQuery("SELECT * FROM students");
        while(rs.next()){
            System.out.println(rs.getInt("id")+" "+rs.getString("name"));
        }
    }
}
