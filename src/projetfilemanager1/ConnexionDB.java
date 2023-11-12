/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetfilemanager;

import java.sql.*;

/**
 *
 * @author midou
 */
public class ConnexionDB {
    private Connection conDB;
    private Statement stmt;
    
    public ConnexionDB(){
        try{ 
            // chargement du pilote
            Class.forName("com.mysql.jdbc.Driver");
            
            //Connexion à la base de données 
            conDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/filemanagerdb","root","");
           
            
            stmt= conDB.createStatement();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        
        catch (SQLException e){
                e.printStackTrace();
        }
    }
    public Connection getconDB(){
        return conDB;
    }
    public Statement getstmt(){
        return stmt;
    }
}
