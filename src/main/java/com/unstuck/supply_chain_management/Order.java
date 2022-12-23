package com.unstuck.supply_chain_management;

public class Order {
    public static boolean placeOrder(String email, Product product){
        Database_Connections database_connection=new Database_Connections();
        String query=String.format("INSERT INTO ORDERS (customer_id,product_id) VALUES((SELECT customer_id FROM CUSTOMER where email='%s'),'%s')",email,product.getId());
        int rowCount=0;
        try{
            rowCount=database_connection.executeUpdateQuery(query);
            System.out.println(rowCount);
        }catch (Exception e){}
        return rowCount!=0;
    }
}
