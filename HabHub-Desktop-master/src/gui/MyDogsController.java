/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import HabHub.CommunityListener;
import HabHub.DogItemListener;
import entities.AnnonceProprietaireChien;
import entities.Chien;
import java.io.File;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.AnnonceProprietaireChienService;
import services.ChienService;
import utils.Statics;
import static utils.Statics.imageDirectory;

/**
 * FXML Controller class
 *
 * @author Ed
 */
public class MyDogsController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    private Label dogColorLabel;

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
    private Button missingButton;

    @FXML
    private Button matingButton;
    
    @FXML
    private Button playingButton;

    @FXML
    private Label ownerLocationLabel2;
    private boolean clickedMating = false;
    private boolean clickedMissing = false;
    private boolean clickedPlaying = false;
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
    AnnonceProprietaireChienService as = new AnnonceProprietaireChienService();
    ChienService cs = new ChienService();
      

    @FXML
    void handleMissing(MouseEvent event) throws SQLException {
        clickedMissing = !clickedMissing;
        if (clickedMissing) {
            missingButton.setStyle("-fx-background-color: #FFEEE8; "
                    + "-fx-border-color:#E0642C;"
                    + "-fx-font-weight:bold;"
                    + "-fx-text-fill:#E0642C;");
            AnnonceProprietaireChien apc = new AnnonceProprietaireChien(chosenDog, "P", Statics.currentIndividu.getAdresse());
            as.ajouterAnnonceProprietaireChien(apc);
        } else {
            missingButton.setStyle("-fx-background-color: #FFFFFF; "
                    + "-fx-border-color:#FFC7AC;"
                    + "-fx-font-weight:regular;"
                    + "-fx-text-fill:black;");
            as.deleteAnnonceProprietaireChienById(chosenDog.getIdChien(),"P");

        }
    }

    @FXML
    void handleMating(MouseEvent event) throws SQLException {
        clickedMating = !clickedMating;
        if (clickedMating) {
            matingButton.setStyle("-fx-background-color: #FFEEE8; "
                    + "-fx-border-color:#E0642C;"
                    + "-fx-font-weight:bold;"
                    + "-fx-text-fill:#E0642C;");
            AnnonceProprietaireChien apc = new AnnonceProprietaireChien(chosenDog, "A", Statics.currentIndividu.getAdresse());
            as.ajouterAnnonceProprietaireChien(apc);
        } else {
            matingButton.setStyle("-fx-background-color: #FFFFFF; "
                    + "-fx-border-color:#FFC7AC;"
                    + "-fx-font-weight:regular;"
                    + "-fx-text-fill:black;");
            as.deleteAnnonceProprietaireChienById(chosenDog.getIdChien(),"A");

        }

    }
    
     @FXML
    void handlePlaying(MouseEvent event) throws SQLException {
        clickedPlaying = !clickedPlaying;
        if (clickedPlaying) {
            playingButton.setStyle("-fx-background-color: #FFEEE8; "
                    + "-fx-border-color:#E0642C;"
                    + "-fx-font-weight:bold;"
                    + "-fx-text-fill:#E0642C;");
            System.out.println(chosenDog.getIdChien());
           cs.playWithMeHandler(chosenDog.getIdChien(), 1);
        } else {
            playingButton.setStyle("-fx-background-color: #FFFFFF; "
                    + "-fx-border-color:#FFC7AC;"
                    + "-fx-font-weight:regular;"
                    + "-fx-text-fill:black;");
            System.out.println(chosenDog.getIdChien());
            cs.playWithMeHandler(chosenDog.getIdChien(),0);

        }

    }

    @FXML
    public void switchSceneDogsMatchup(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DogsMatchup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchSceneDogsNextDoor(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("DogsNextDoor.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchSceneMissingDogs(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MissingDogs.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchSceneAddDog(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddDog.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public ObservableList<Chien> data = FXCollections.observableArrayList();
    

    private ObservableList<Chien> getChienByIndividu() {
        List<Chien> chiens = new ArrayList<>();

        try {
            chiens = cs.findChienByIndividu(Statics.currentIndividu.getIdIndividu());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        data.clear();
        data.addAll(chiens);
        return data;

    }

    private void setChosenChien(Chien a) throws SQLException, IOException {
        if (as.checkDog(a.getIdChien(),"P")){
            missingButton.setStyle("-fx-background-color: #FFEEE8; "
                    + "-fx-border-color:#E0642C;"
                    + "-fx-font-weight:bold;"
                    + "-fx-text-fill:#E0642C;");
            clickedMissing=true;
        }
        else { 
            missingButton.setStyle("-fx-background-color: #FFFFFF; "
                    + "-fx-border-color:#FFC7AC;"
                    + "-fx-font-weight:regular;"
                    + "-fx-text-fill:black;");
            clickedMissing=false;
        }
        if (as.checkDog(a.getIdChien(),"A")){
            matingButton.setStyle("-fx-background-color: #FFEEE8; "
                    + "-fx-border-color:#E0642C;"
                    + "-fx-font-weight:bold;"
                    + "-fx-text-fill:#E0642C;");
            clickedMating=true;
        }
        else { 
            matingButton.setStyle("-fx-background-color: #FFFFFF; "
                    + "-fx-border-color:#FFC7AC;"
                    + "-fx-font-weight:regular;"
                    + "-fx-text-fill:black;");
            clickedMating=false;
        }
        if (a.isPlayWithMe()){
  
            playingButton.setStyle("-fx-background-color: #FFEEE8; "
                    + "-fx-border-color:#E0642C;"
                    + "-fx-font-weight:bold;"
                    + "-fx-text-fill:#E0642C;");
            clickedPlaying=true;
        } else {
            playingButton.setStyle("-fx-background-color: #FFFFFF; "
                    + "-fx-border-color:#FFC7AC;"
                    + "-fx-font-weight:regular;"
                    + "-fx-text-fill:black;");
            clickedPlaying=false;
        }  
        
        chosenDog = a;
        dogNameLabel.setText(a.getNom());

        ageLabel.setText(a.getAge());
        dogStory.setText(a.getDescription());
        genderLabel.setText("Female");
       
        if ("M".equals(a.getSexe())) {
           
            genderLabel.setText("Male");
        }
        dogColorLabel.setText(a.getColor());
        ownerLocationLabel.setText(Statics.currentIndividu.getAdresse());
        ownerNameLabel.setText(Statics.currentIndividu.getPrenom());

      File sourceimage = new File(imageDirectory+a.getImage());
                    Image image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
                    Image dogImg=image;
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
                    try {
                        setChosenChien(chien);
                    } catch (SQLException ex) {
                        Logger.getLogger(MyDogsController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MyDogsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            for (int i = 0; i < chiens.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("DogBigCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                DogBigCardController dogBigCardController = fxmlLoader.getController();
                dogBigCardController.setData(chiens.get(i), dogItemListener);

                if (column == 3) {
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
            displayChiens(getChienByIndividu());
        } catch (SQLException ex) {
            Logger.getLogger(MyDogsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (data.size() > 0) {
            try {
                setChosenChien(data.get(0));
            } catch (SQLException ex) {
                Logger.getLogger(MyDogsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MyDogsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
