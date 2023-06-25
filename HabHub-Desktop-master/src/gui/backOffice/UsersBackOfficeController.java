/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backoffice;

import entities.Individu;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.UserIndividuServices;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author NADA_USER
 */
public class UsersBackOfficeController implements Initializable {

  
      @FXML
    private TextField searchBoxUserss;

    @FXML
    private Button refreshButton;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Individu> UsersTableView;

  
    @FXML
    private TableColumn<Individu, String> IdIndividu;

    @FXML
    private TableColumn<Individu, String> IdUser;

    @FXML
    private TableColumn<Individu, String> nom;

    @FXML
    private TableColumn<Individu, String> prenom;

    @FXML
    private TableColumn<Individu, String> dateNaiss;

    @FXML
    private TableColumn<Individu, String> sexe;

    @FXML
    private TableColumn<Individu, String> adresse;

    @FXML
    private TableColumn<Individu, String> facebook;

    @FXML
    private TableColumn<Individu, String> insatgram;

    @FXML
    private TableColumn<Individu, String> whatsapp;

    
    
    private Stage stage;
    private Scene scene;
    private Parent root;
        @FXML
    void switchBusinessBack(ActionEvent event) throws IOException {
 root = FXMLLoader.load(getClass().getResource("HomeBackOffice.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    void switchDogsBack(ActionEvent event) throws IOException {
 root = FXMLLoader.load(getClass().getResource("chiensBackOffice.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    void switchProductsBack(ActionEvent event) throws IOException {
 root = FXMLLoader.load(getClass().getResource("ProductsBackOffice.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    void switchUsersBack(ActionEvent event) throws IOException {
 root = FXMLLoader.load(getClass().getResource("UsersBackOffice.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

  

  
      
        private Individu i;
  

 public ObservableList<Individu> data = FXCollections.observableArrayList();
    UserIndividuServices us = new UserIndividuServices();
    
    
    @FXML
    void refreshUsersTable()throws SQLException {
        data.clear();
        data.addAll(us.afficherIndividu());
        System.out.println("refresh Users Table");
        UsersTableView.setItems(data);
     
    }
    
  @FXML
    private void getUsersAddView(MouseEvent event)   {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("UsersPopUp.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UsersBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
      
}      
        
    }
    
   @FXML
    void getUsersEditView(MouseEvent event) {
        if ( UsersTableView.getSelectionModel().getSelectedItem()!=null)
        {
        i= UsersTableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("UsersPopUp.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(UsersBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            UsersPopUpController usersPopUpController = loader.getController();
                            usersPopUpController.setUpdate(true);
                            usersPopUpController.setIdUpdate(i.getIdIndividu());
                            /*String prenom="";
                            if (chien.getIndividu()!=null){
                                prenom=chien.getIndividu().getPrenom();
                            }*/
                            usersPopUpController.setTextField(i.getNom(),  i.getPrenom(),
                                    i.getDateNaissance(),i.getSexe(),i.getAdresse(),i.getFacebook(),i.getInstagram(),i.getWhatsapp());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

    }
    }
    
    
    
    
    @FXML
    void deleteUsers(MouseEvent event) throws SQLException  {

        i = UsersTableView.getSelectionModel().getSelectedItem();
       
        us.delete(i.getIdIndividu());
        refreshUsersTable();

    }

    

    private void loadUsersData() {
        try {
            refreshUsersTable();
        } catch (SQLException ex) {
            Logger.getLogger(UsersBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        IdIndividu.setCellValueFactory(new PropertyValueFactory<>("IdIndividu"));

        //IdUser.setCellValueFactory(new PropertyValueFactory<>("IdUser"));
       adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      //  adresse.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().)));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateNaiss.setCellValueFactory(new PropertyValueFactory<>("dateNaisse"));
                sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        facebook.setCellValueFactory(new PropertyValueFactory<>("facebook"));
               insatgram.setCellValueFactory(new PropertyValueFactory<>("instagram"));
        whatsapp.setCellValueFactory(new PropertyValueFactory<>("whatsapp"));


       

    }
    Utilisateur u ;
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUsersData();
      
        //loadAdoptionData();
        
        searchBoxUserss.textProperty().addListener((observable, oldValue, newValue) -> {
             data.clear();
           try {
                data.addAll(us.findIndividuByName(newValue))
                        
                        
                        ;
            } catch (SQLException exception) {
                Logger.getLogger(UsersBackOfficeController.class.getName()).log(Level.SEVERE, null, exception);
            }
        System.out.println("testmating");
        UsersTableView.setItems(data);

        });
        
    }

}
