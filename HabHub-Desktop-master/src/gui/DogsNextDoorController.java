/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import HabHub.CommunityListener;
import HabHub.DogItemListener;
import entities.Chien;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ChienService;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author Ed
 */
public class DogsNextDoorController implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML
    private TextField searchBox;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView dogImage;

    @FXML
    private Label dogNameLabel;

    @FXML
    private Label ownerLocationLabel;

    @FXML
    private ImageView genderImage;

    @FXML
    private Label ageLabel;

    @FXML
    private Label colorLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label dogRaceLabel;

    @FXML
    private Label dogGroupLabel;

    @FXML
    private Text dogStory;

    @FXML
    private Label ownerNameLabel;

    @FXML
    private Label ownerLocationLabel2;
    
    private DogItemListener dogItemListener;
    private Chien chosenDog;
     private Stage stage;
    private Scene scene;
    private Parent root;
    
      @FXML
    void switchAdoption(ActionEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("AnnonceAdoptionFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    void switchBoutique(ActionEvent event) throws IOException {
 root = FXMLLoader.load(getClass().getResource("BoutiqueFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    void switchHome(ActionEvent event) throws IOException {
 root = FXMLLoader.load(getClass().getResource("BusinessFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    void switchHub(ActionEvent event) throws IOException {
  if (Statics.currentIndividu.getProprietaireChien())
        {
         root = FXMLLoader.load(getClass().getResource("MyDogs.fxml"));
        }
        else
        { root = FXMLLoader.load(getClass().getResource("CommunityInitialPage.fxml"));
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchProfile(ActionEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("profiledit.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    
    } 
    
    @FXML
    public void switchSceneMyDogs (ActionEvent event) throws IOException
    {
       root = FXMLLoader.load(getClass().getResource("MyDogs.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
     @FXML
    public void switchSceneDogsMatchup (ActionEvent event) throws IOException
    {
       root = FXMLLoader.load(getClass().getResource("DogsMatchup.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
     @FXML
    public void switchSceneMissingDogs (ActionEvent event) throws IOException
    {
    root = FXMLLoader.load(getClass().getResource("MissingDogs.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
   
     public ObservableList<Chien> data = FXCollections.observableArrayList();
    ChienService cs = new ChienService();

    

    private ObservableList<Chien> getChienByLocation() {
        List<Chien> chiens = new ArrayList<>();

        try {
            chiens = cs.findChienByLocation(Statics.currentIndividu.getAdresse());
      
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        data.clear();
        data.addAll(chiens);
        return data;

    }

     private void setChosenChien(Chien a) {

        dogNameLabel.setText(a.getNom());
        Image genderImg = new Image(getClass().getResourceAsStream("../assets/img/female.png"));
        genderLabel.setText("Female");
        if ("M".equals(a.getSexe())) {
            genderImg = new Image(getClass().getResourceAsStream("../assets/img/male.png"));
            genderLabel.setText("Male");
        }
        genderImage.setImage(genderImg);

        ageLabel.setText(a.getAge());
        dogStory.setText(a.getDescription());
        ownerLocationLabel.setText(a.getIndividu().getAdresse());
        ownerLocationLabel2.setText(a.getIndividu().getAdresse());
        dogRaceLabel.setText(a.getRace());
        dogGroupLabel.setText(a.getGroupe());
        colorLabel.setText(a.getColor());

       
        ownerNameLabel.setText(a.getIndividu().getPrenom());

       Image dogImg = new Image(getClass().getResourceAsStream("../assets/img/chien/"+a.getImage()));
       dogImage.setImage(dogImg);

    }



    private void displayChiens(ObservableList<Chien> chiens) throws SQLException {
        grid.getChildren().clear();
        int column = 0;
        int row = 1;

       try {
           dogItemListener = new DogItemListener() {
                @Override
                public void onClickListener(Chien chien) {
                    
                        setChosenChien(chien);
                   
                }
            };
            for (int i = 0; i < chiens.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("DogListItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                DogListItemController dogListItemController = fxmlLoader.getController();
                dogListItemController.setData(chiens.get(i),dogItemListener);

                if (column == 4) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_PREF_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_PREF_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
               displayChiens(getChienByLocation());
           } catch (SQLException ex) {
               Logger.getLogger(DogsNextDoorController.class.getName()).log(Level.SEVERE, null, ex);
           }

     if (data.size() > 0) {
                setChosenChien(data.get(0));
       
        }
        }
   
    
}
