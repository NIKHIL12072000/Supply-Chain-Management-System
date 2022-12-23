package com.unstuck.supply_chain_management;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
        private SimpleIntegerProperty id;
        private SimpleDoubleProperty price;
        private SimpleStringProperty name;

        public int getId(){
            return id.get();
        }

        public double getPrice(){
            return price.get();
        }

        public String getName(){
            return name.get();
        }
        Product(int id,String name,double price){
            this.id=new SimpleIntegerProperty(id);
            this.name=new SimpleStringProperty(name);
            this.price=new SimpleDoubleProperty(price);
        }

        public static ObservableList<Product> getAllProduct(){
            Database_Connections database_connections=new Database_Connections();
            ObservableList<Product> productList= FXCollections.observableArrayList();
            String selectedProducts="SELECT * FROM PRODUCT";
            try{
                ResultSet rs=database_connections.getQueryTable(selectedProducts);
                while(rs.next()){
                    productList.add(new Product(rs.getInt("product_id"),rs.getString("product_name"),rs.getDouble("price")));
                    System.out.println(rs.getInt("product_id")+" "+rs.getString("product_name")+" "+rs.getDouble("price"));
                }
            }catch (Exception e){}
            return productList;
        }

        public static void main(String args[]){
            getAllProduct();
        }

}
