/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetfilemanager;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * FXML Controller class
 *
 * @author midou
 */
public class InformationsGeneralesController implements Initializable {

    @FXML
    private Button ButtonAnnuler;
    @FXML
    private TextArea TextUtilisateurs;
    @FXML
    private Label Label1;
    @FXML
    private Label Label2;
    @FXML
    private TextArea TextNombre;
    @FXML
    private Button ButtonInfo1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ConnexionDB condb = new ConnexionDB();
        try { ResultSet rs = (condb.getstmt()).executeQuery("Select * from users"); 
        String ch1="",chfichier,chnom,chPath = FichiersFavorisController.getPathFromListView(), chutilisateur=FXMLDocumentController.getutilisateur() ;
           while (rs.next()){ 
               ch1= ch1+ String.valueOf(rs.getString("nom")) + "\r\n";
               
           }
       TextUtilisateurs.setText(ch1);
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

    @FXML
    private void ButtonInfo1(ActionEvent event) {
        ConnexionDB condb = new ConnexionDB();
        try { ResultSet rs = (condb.getstmt()).executeQuery("Select * from users"); 
        String ch1="",chfichier,chnom,chPath = FichiersFavorisController.getPathFromListView(), chutilisateur=FXMLDocumentController.getutilisateur() ;
           while (rs.next()){ 
               ch1= ch1+ String.valueOf(rs.getString("nom")) + "\r\n";
               
           }
        try {
                       PrintWriter FSortie = new PrintWriter(new FileWriter("ListeUtilisateurs.txt"));
                       FSortie.println("Liste des utilisateurs :\r\n"+ch1);
                       
                       File file = new File("C:\\Users\\midou\\Documents\\NetBeansProjects\\ProjetFileManager\\ListeUtilisateurs.txt");
                       Desktop desktop = Desktop.getDesktop();  
                       desktop.open(file);
                       FSortie.close();
                      } catch (FileNotFoundException e)
                      {
                          System.out.println("ERR : File.txt inconnu");
                      }
                      catch(IOException e)
                      {
                          System.out.println("ERR de L/E dans File.txt");
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
