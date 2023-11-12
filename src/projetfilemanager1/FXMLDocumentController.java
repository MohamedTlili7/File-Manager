/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetfilemanager;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author midou
 */
public class FXMLDocumentController implements Initializable {
    
    private static String utilisateur; // Utilisé afin d'extraire de nom de l'utilisateur d'après le getter
    @FXML
    private Button Button1;
    @FXML
    private Button Button2;
    @FXML
    private TextField textid;
    @FXML
    private PasswordField textmdp;
    
    //Ceci est le panneau de connexion  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Buttonlogin(ActionEvent event) throws IOException{
        ConnexionDB condb = new ConnexionDB();
        String ch1,ch2;
        boolean trouve=false;
        
        try { ResultSet rs = (condb.getstmt()).executeQuery("Select * from users");
        ch1=textid.getText().trim()+textmdp.getText(); // Combinaison id + mot de passe saisie par l'utilisateur
         while (rs.next() && !trouve){
             
             ch2=String.valueOf(rs.getString("id"))+String.valueOf(rs.getString("mdp")); //Combinaison id + mdp récupérée  
             utilisateur=rs.getString("nom");                                            //de la BD
             if  (ch1.equals(ch2))
             {
             trouve = true;
             System.out.println(rs.getString(1)+ "\t");
             System.out.println(rs.getString(2)+ "\t"); 
             }
         } 
         
         Alert alert1 = new Alert(AlertType.INFORMATION);
             alert1.setTitle("Connexion");
             alert1.setHeaderText(null);
             Alert alert2 = new Alert(AlertType.ERROR);
             alert2.setTitle("Connexion");
             alert2.setHeaderText(null);
          if (trouve ==true)
         {
             System.out.println("Connecté !");
             
             alert1.setContentText("Connexion effectuée avec succès !");

             alert1.showAndWait();
             ProjetFileManager M = new ProjetFileManager();  //Changement de scène à la scène après la connexion
             M.changeScene("FichiersFavoris.fxml");
         } 
          
         else if (trouve==false)
         {  System.out.println("Connexion échouée");
             alert2.setContentText("Connexion échouée !");
        
             alert2.showAndWait();
         }  
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                
                condb.getstmt().close();
                condb.getconDB().close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void Buttonsignup(ActionEvent event) throws IOException {
        ProjetFileManager M = new ProjetFileManager();
        M.changeScene("Inscription.fxml");
    }
    public static String getutilisateur (){ //--> déclarée statique afin d'éviter de crée une instance de la classe
                                            // FXMLDocumentController, juste pour extraire le nom d'utilistaeur.  
        return utilisateur;
    }
}
