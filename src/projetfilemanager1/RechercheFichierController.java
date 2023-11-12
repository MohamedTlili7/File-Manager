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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author midou
 */
public class RechercheFichierController implements Initializable {

    @FXML
    private TextField TextAuteur;
    @FXML
    private TextField TextTitre;
    @FXML
    private Button ButtonAnnuler;
    @FXML
    private TextArea TextTags;
    @FXML
    private Button ButtonAuteur;
    @FXML
    private Label Label1;
    @FXML
    private Label Label2;
    @FXML
    private Button ButtonTitre;
    @FXML
    private Button ButtonTags;
    @FXML
    private TextArea TextResultat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ButtonAnnuler(ActionEvent event) throws IOException {
        ProjetFileManager P = new ProjetFileManager();
        P.changeScene("FichiersFavoris.fxml");
    }

    @FXML
    private void ButtonAuteur(ActionEvent event) {
        ConnexionDB condb = new ConnexionDB();
        try { ResultSet rs = (condb.getstmt()).executeQuery("Select * from favoris"); 
        String chfichier,chnom,chAuteur, chutilisateur=FXMLDocumentController.getutilisateur() ;
        
           while (rs.next()){
               
               chfichier =String.valueOf(rs.getString("fichier"));
               chnom = String.valueOf(rs.getString("nom"));
               chAuteur = String.valueOf(rs.getString("auteur"));
               if (chAuteur.equals(TextAuteur.getText()) && chnom.equals(chutilisateur))
                       {
            TextResultat.setText(String.valueOf(rs.getString("titre"))+"\r\n"+chfichier);
                       }
           }
       
        } catch (SQLException e){
            e.printStackTrace();}
        finally {
            try{
                
                condb.getstmt().close();
                condb.getconDB().close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }    
    

    @FXML
    private void ButtonTitre(ActionEvent event) {
        ConnexionDB condb = new ConnexionDB();
        try { ResultSet rs = (condb.getstmt()).executeQuery("Select * from favoris"); 
        String chfichier,chnom,chTitre, chutilisateur=FXMLDocumentController.getutilisateur() ;
        
           while (rs.next()){
               
               chfichier =String.valueOf(rs.getString("fichier"));
               chnom = String.valueOf(rs.getString("nom"));
               chTitre = String.valueOf(rs.getString("titre"));
               if (chTitre.equals(TextTitre.getText()) && chnom.equals(chutilisateur))
                       {
            TextResultat.setText("Titre : "+String.valueOf(rs.getString("titre"))+"\r\n"+chfichier);
                       }
           }
       
        } catch (SQLException e){
            e.printStackTrace();}
        finally {
            try{
                
                condb.getstmt().close();
                condb.getconDB().close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void ButtonTags(ActionEvent event) {
        ConnexionDB condb = new ConnexionDB();
        try { ResultSet rs = (condb.getstmt()).executeQuery("Select * from favoris"); 
        String chfichier,chnom,chTags, chutilisateur=FXMLDocumentController.getutilisateur() ;
        
           while (rs.next()){
               
               chfichier =String.valueOf(rs.getString("fichier"));
               chnom = String.valueOf(rs.getString("nom"));
               chTags = String.valueOf(rs.getString("tags"));
               if (chTags.contains(TextTags.getText()) && chnom.equals(chutilisateur))
                       {
            TextResultat.setText("Titre : "+String.valueOf(rs.getString("titre"))+"\r\n"+chfichier);
                       }
           }
       
        } catch (SQLException e){
            e.printStackTrace();}
        finally {
            try{
                
                condb.getstmt().close();
                condb.getconDB().close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    
}
