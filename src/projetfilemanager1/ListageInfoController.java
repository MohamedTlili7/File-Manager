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
public class ListageInfoController implements Initializable {

    @FXML
    private TextField TextAuteur;
    @FXML
    private TextField TextTitre;
    @FXML
    private Button ButtonAnnuler;
    @FXML
    private TextArea TextTags;
    @FXML
    private TextArea TextResume;
    @FXML
    private TextArea TextCommentaires;
    @FXML
    private Label Label1;
    @FXML
    private Label Label2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ConnexionDB condb = new ConnexionDB();
        try { ResultSet rs = (condb.getstmt()).executeQuery("Select * from favoris"); 
        String chfichier,chnom,chPath = FichiersFavorisController.getPathFromListView(), chutilisateur=FXMLDocumentController.getutilisateur() ;
        // ces chaînes sont utilisées afin de préléver les infos de l'utilisateur en correspondance avec 
        // son fichier favoris (Path)
           while (rs.next()){
               chfichier =String.valueOf(rs.getString("fichier"));
               chnom = String.valueOf(rs.getString("nom"));
               if (chfichier.equals(chPath) && chnom.equals(chutilisateur))
                       {
            TextAuteur.setText(String.valueOf(rs.getString("auteur")));
            TextTitre.setText(String.valueOf(rs.getString("titre")));
            TextTags.setText(String.valueOf(rs.getString("tags")));
            TextResume.setText(String.valueOf(rs.getString("resume")));
            TextCommentaires.setText(String.valueOf(rs.getString("commentaires")));
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
    private void ButtonAnnuler(ActionEvent event) throws IOException {
        ProjetFileManager P = new ProjetFileManager();
        P.changeScene("FichiersFavoris.fxml");
    }
    
}
