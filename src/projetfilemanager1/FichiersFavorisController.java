/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetfilemanager;
import java.awt.Desktop;
import static java.awt.SystemColor.desktop;
import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author midou
 */
public class FichiersFavorisController implements Initializable {
    private static String ch3; //ch3 contient le Path du fichier
    private static String ch4; //ch4 contient aussi le Path du fichier mais celui qui est choisi par l'utilisateur
                               // de la ListView
    @FXML
    private Button ButtonFavoris;
    @FXML
    private Button Buttonlogout;
    @FXML
    private Text TextBienvenue;
    @FXML
    private ListView <String>ListView;
    @FXML
    private Button ButtonMise;
    @FXML
    private Button ButtonInfo;
    @FXML
    private Button ButtonInfo1;
    @FXML
    private Button ButtonRecherche;
    @FXML
    private Button ButtonInfo2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ConnexionDB condb = new ConnexionDB();
        TextBienvenue.setText("Bienvenue "+ FXMLDocumentController.getutilisateur()+" !");
       try { ResultSet rs = (condb.getstmt()).executeQuery("Select * from favoris where nom='"+FXMLDocumentController.getutilisateur()+"'");
           while (rs.next())
           {
               ListView.getItems().add(String.valueOf(rs.getString("fichier")));
           }
       }
       catch (SQLException e){
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

    @FXML
    private void ButtonFavorisDragged(MouseEvent event) {
    }


    @FXML
    private void ButtonFavoris(ActionEvent event) throws IOException {
       String utilisateur = FXMLDocumentController.getutilisateur();
        ConnexionDB condb = new ConnexionDB();
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null); 
        boolean trouve = false; //Pour la recherche d'un fichier favoris déja marqué
        String ch2 = "";
        if (selectedFile!=null){                                         
                                                                    
                                                                 //PrintWriter Fsortie = new PrintWriter (new FileWriter ("File.txt"));                                                        
                                                                 //Fsortie.println(selectedFile.getPath());
                                                                 //Fsortie.close();
          try { ResultSet rs = (condb.getstmt()).executeQuery("Select * from favoris");
         while (rs.next() && !trouve){
             ch2=String.valueOf(rs.getString("fichier"));
             ch3 = selectedFile.getPath();
               ch3 = ch3.replace("\\", "");  //On a du supprimer \\ du Path afin de le comparer avec celui de la BD
             if (ch2.equals(ch3)) 
                 trouve = true;
         }
            
        } catch (SQLException e){
            e.printStackTrace();
        } 
        
        if (trouve==true)
        {
            
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
             alert2.setTitle("Connexion");
             alert2.setHeaderText(null);
             
             alert2.setContentText("Ce fichier est déja marqué comme favoris !");
        
             alert2.showAndWait();
        }                                                        
       if (trouve==false){                                                          
                               ch3 = ch2;                                  
    try { int nbr_maj= condb.getstmt().executeUpdate("Insert into favoris VALUES(id, '"+utilisateur+"'"+", '"+selectedFile.getPath()+"', '', '', '', '', '')"); 
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
        
    }
            
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);      
             alert1.setTitle("Favoris");                       
             alert1.setHeaderText(null);                            
             alert1.setContentText("Marqué comme favoris !");

             alert1.showAndWait();
           
        }
       ch3=ch2;
        ProjetFileManager P = new ProjetFileManager();
        P.changeScene("RemplirFichier.fxml");      //Changement de scène afin de remplir les champs
        }
    }

    @FXML
    private void ButtonlogoutDragged(MouseEvent event) {
    }

    @FXML
    private void ButtonlogoutClicked(ActionEvent event)throws IOException {
        ProjetFileManager M = new ProjetFileManager();
        M.changeScene("FXMLDocument.fxml");
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);      //Changement de scène et affichage d'une nouvelle
             alert1.setTitle("Déconnecté !");                       // fenêtre quand on clique sur le bouton 
             alert1.setHeaderText(null);                            // "Se déconnecter"
             alert1.setContentText("Votre compte à été déconnecté !");

             alert1.showAndWait();
    }
    public static String getPath(){
    return ch3;
}
  public static String getPathFromListView(){
      return ch4;
  }
    

    @FXML
    private void ButtonMise(ActionEvent event) throws IOException {
        ch4 = ListView.getSelectionModel().getSelectedItem();
        if (ch4==null || ch4.isEmpty())
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
             alert2.setTitle("Connexion");
             alert2.setHeaderText(null);
             
             alert2.setContentText("Aucun fichier n'a été sélectionné !");
        
             alert2.showAndWait();
        }
        else {
        ProjetFileManager P = new ProjetFileManager();
        P.changeScene("Modifier.fxml");
        }
        
    }

    @FXML
    private void ButtonInfo(ActionEvent event) throws IOException {
        ch4 = ListView.getSelectionModel().getSelectedItem();
        if (ch4==null || ch4.isEmpty())
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
             alert2.setTitle("Connexion");
             alert2.setHeaderText(null);
             
             alert2.setContentText("Aucun fichier n'a été sélectionné !");
        
             alert2.showAndWait();
        }
        else {
        ProjetFileManager P = new ProjetFileManager();
        P.changeScene("ListageInfo.fxml");
        }
        
    }

    @FXML
    private void ButtonInfo1(ActionEvent event) throws IOException {
        ch4 = ListView.getSelectionModel().getSelectedItem();
        if (ch4==null || ch4.isEmpty())
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
             alert2.setTitle("Connexion");
             alert2.setHeaderText(null);
             
             alert2.setContentText("Aucun fichier n'a été sélectionné !");
        
             alert2.showAndWait();
        }
        else {
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
                      try {
                       PrintWriter FSortie = new PrintWriter(new FileWriter("File.txt"));
                       FSortie.println("Fichier : "+ String.valueOf(rs.getString("fichier")));
                       FSortie.println("Auteur : "+ String.valueOf(rs.getString("auteur")));
                       FSortie.println("Titre : "+ String.valueOf(rs.getString("titre")));
                       FSortie.println("Tags : "+ String.valueOf(rs.getString("tags")));
                       FSortie.println("Resume : "+ String.valueOf(rs.getString("resume")));
                       FSortie.println("Commentaires : "+ String.valueOf(rs.getString("commentaires")));
                       File file = new File("C:\\Users\\midou\\Documents\\NetBeansProjects\\ProjetFileManager\\File.txt");
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

    @FXML
    private void ButtonRecherche(ActionEvent event) throws IOException {
        ProjetFileManager P = new ProjetFileManager();
        P.changeScene("RechercheFichier.fxml");
    }

    @FXML
    private void ButtonInfo2(ActionEvent event) throws IOException {
        if (FXMLDocumentController.getutilisateur().equals("Admin"))
        {
            ProjetFileManager P = new ProjetFileManager();
        P.changeScene("InformationsGenerales.fxml");
        }
        else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
             alert2.setTitle("Accès Refusé");
             alert2.setHeaderText(null);
             
             alert2.setContentText("Vous n'êtes pas Admin !");
        
             alert2.showAndWait();
        }
    }

    
   
}
