package com.unstuck.supply_chain_management;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.*;

public class Login {

    public static byte[] getSHA(String input){
        try{
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){}
        return null;
    }

    public static String getEncryptedPassword(String password){
        try{
            BigInteger number=new BigInteger(1, getSHA(password));
            StringBuilder hexString=new StringBuilder(number.toString(16));
            return hexString.toString();
        }catch (Exception e){}
        return null;
    }

    public boolean customerLogin(String email, String password){
        String query=String.format("SELECT * FROM CUSTOMER WHERE email='%s' and password='%s'",email,password);
        try {
            Database_Connections dbconn = new Database_Connections();
            ResultSet resultSet = dbconn.getQueryTable(query);
            if (resultSet != null && resultSet.next())
                return true;
        }catch (Exception e){}
        return false;
    }

    public static void main(String args[]){
        Login login=new Login();
        System.out.println(login.customerLogin("dachepallinikhik@gmail.com","DNikhil@1207"));
        System.out.println(login.getEncryptedPassword("DNikhil@1207"));
    }
}