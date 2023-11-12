/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetfilemanager;

import java.sql.*;
import java.sql.SQLException;

/**
 *
 * @author midou
 */ 
public class TestConnexion {
    public static void main(String args[]){
        ConnexionDB condb = new ConnexionDB();
        
        try { ResultSet rs = (condb.getstmt()).executeQuery("Select * from users");
         while (rs.next()){
             System.out.println(rs.getString(1)+ "\t");
             System.out.println(rs.getString(2)+ "\t");
             
         }
            
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                (condb.getstmt()).close();
                (condb.getconDB()).close();
            } catch (SQLException e){
                e.printStackTrace();
            }     
        
    } 
}
}


