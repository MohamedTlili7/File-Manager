/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetfilemanager;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author midou
 */
public class RemplirFichierController implements Initializable {

    @FXML
    private Button ButtonAnnuler;
    @FXML
    private TextField TextAuteur;
    @FXML
    private TextField TextTitre;
    @FXML
    private TextArea TextTags;
    @FXML
    private TextArea TextResume;
    @FXML
    private TextArea TextCommentaires;
    @FXML
    private Button ButtonEnregistrer;
    @FXML
    private Label Label1;
    @FXML
    private Label Label2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void ButtonAnnuler(ActionEvent event)  throws IOException {
        ProjetFileManager M = new ProjetFileManager();
        M.changeScene("FichiersFavoris.fxml");
    }

    @FXML
    private void ButtonEnregistrer(ActionEvent event) {
        ConnexionDB condb = new ConnexionDB();
        int x=1;
       String Titre = TextTitre.getText();
       String Tags = TextTags.getText();
        Label1.setText("");
         Label2.setText("");
        if (Titre.length()<=2) 
        {Label1.setText("3 caractères minimum !"); x=0;}
        else if (Titre.length()>30)
        {Label1.setText("30 caractères maximum !"); x=0;}
        if (Tags.length()<=2) 
        {Label2.setText("3 caractères minimum !"); x=0;}
        else if (Tags.length()>200)
        {Label2.setText("200 caractères maximum !"); x=0;}
        
        
            if (x==1)
            {
        
        try { int nbr_maj= condb.getstmt().executeUpdate("update favoris set auteur ='"+TextAuteur.getText()+"' where fichier='"+FichiersFavorisController.getPath()+"'"); 
         
          nbr_maj= nbr_maj + condb.getstmt().executeUpdate("update favoris set titre ='"+TextTitre.getText()+"' where fichier='"+FichiersFavorisController.getPath()+"'"); 
          nbr_maj= nbr_maj + condb.getstmt().executeUpdate("update favoris set tags ='"+TextTags.getText()+"' where fichier='"+FichiersFavorisController.getPath()+"'");
          nbr_maj= nbr_maj + condb.getstmt().executeUpdate("update favoris set resume ='"+TextResume.getText()+"' where fichier='"+FichiersFavorisController.getPath()+"'");
          nbr_maj= nbr_maj + condb.getstmt().executeUpdate("update favoris set commentaires ='"+TextCommentaires.getText()+"' where fichier='"+FichiersFavorisController.getPath()+"'");
          System.out.println("nombre de lignes mises à jour : "+nbr_maj);
         System.out.println(FichiersFavorisController.getPath());
         
                                    
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
       Alert alert1 = new Alert(Alert.AlertType.INFORMATION);      
             alert1.setTitle("Favoris");                       
             alert1.setHeaderText(null);                            
             alert1.setContentText("Informations Ajoutées !");

             alert1.showAndWait(); 
    }
        
    }
}

