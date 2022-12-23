package com.unstuck.supply_chain_management;
import java.sql.*;
public class Database_Connections {

    private static final String databaseUrl="jdbc:mysql://localhost:3306/supply_chain_management";
    private static final String userName="root";
    private static final String password="DNikhil@1207";

    public Statement getStatement(){
        Statement statement=null;
        Connection conn;
        try{
            conn=DriverManager.getConnection(databaseUrl,userName,password);
            statement= conn.createStatement();
        }catch (Exception e){}
        return statement;
    }

    public ResultSet getQueryTable(String query){
        Statement statement=getStatement();
        try{
            return statement.executeQuery(query);
        }catch (Exception e){}
        return null;
    }

    public int executeUpdateQuery(String query){
        Statement statement=getStatement();
        try{
            return statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
            System.out.println(e.getLocalizedMessage());
        }
        return 0;
    }

    public static void main(String args[]){
        Database_Connections database_connections=new Database_Connections();
        ResultSet rs=database_connections.getQueryTable("SELECT customer_id, email, last_name, first_name FROM CUSTOMER");
        try{
            while(rs.next()){
                System.out.println(rs.getString("customer_id")+" "+rs.getString("email")+" "+rs.getString("last_name")+" "+rs.getString("first_name"));
            }
        }catch (Exception e){}
    }
}
