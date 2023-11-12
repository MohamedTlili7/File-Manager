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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author midou
 */
public class InscriptionController implements Initializable {

    @FXML
    private Button Button3;
    @FXML
    private Button Button4;
    @FXML
    private Label Label1;
    @FXML
    private Label Label2;
    @FXML
    private TextField Text1;
    @FXML
    private PasswordField Text2;
    @FXML
    private TextField Text3;
    @FXML
    private Label Label3;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Buttonsignup2(ActionEvent event) {
        int x=1; //afin de vérifier s'il existe aucun problème avec la longueur et qu'on ajouter le compte à la BD
        String id = Text1.getText();
        String mdp = Text2.getText();
        String nom = Text3.getText();
        Label1.setText("");
        Label2.setText("");
        if (id.length()<=2) 
        {Label1.setText("3 caractères minimum !"); x=0;}
        else if (id.length()>15)
        {Label1.setText("15 caractères maximum !"); x=0;}
        if (mdp.length()<=2)
        {Label2.setText("3 caractères minimum !"); x=0;}
        else if (mdp.length()>15)
        {Label2.setText("15 caractères maximum !"); x=0;}
        if (nom.length()<=2) 
        {Label3.setText("3 caractères minimum !"); x=0;}
        else if (nom.length()>15)
        {Label3.setText("20 caractères maximum !"); x=0;}
        
        
        
        if(x==1)
         id=id.trim(); //Suppression des espaces de l'id
          
            
        ConnexionDB condb = new ConnexionDB();
        boolean trouve = false;
        String ch2 ;  // On compare l'id saisi par l'utilisateur à les id existant dans notre BD 
                      // en utilisant ch2 comme conteneur de l'id dans chaque ligne parcourue
        try { ResultSet rs = (condb.getstmt()).executeQuery("Select * from users");
         while (rs.next() && !trouve){
             ch2=String.valueOf(rs.getString("id"));
             
             if (ch2.equals(id)) 
                 trouve = true;
         }
            
        } catch (SQLException e){
            e.printStackTrace();
         } 
        
        if (trouve==true)
        {
            x=0;
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
             alert2.setTitle("Connexion");
             alert2.setHeaderText(null);
             
             alert2.setContentText("Inscription échouée ! Compte déja existant");
        
             alert2.showAndWait();
        } 
        
         if (trouve==false && x==1){
            
        
        try { int nbr_maj= condb.getstmt().executeUpdate("Insert into users VALUES('"+id+"'"+", '"+mdp+"', '"+nom+"')"); 
                 System.out.println("nombre de lignes mises à jour : "+nbr_maj);
                     //On ajoute les valeurs saisis dans notre BD
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                (condb.getstmt()).close();
                (condb.getconDB()).close();
            } catch (SQLException e){
                e.printStackTrace();
            }     
        
    } Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
             alert1.setTitle("Connexion");
             alert1.setHeaderText(null);
             
             
             alert1.setContentText("Inscription effectuée avec succès !");

             alert1.showAndWait();
             
        }
    }

    @FXML
    private void ButtonRetour(ActionEvent event) throws IOException {
        ProjetFileManager P = new ProjetFileManager();
        P.changeScene("FXMLDocument.fxml");
    }
    
}
