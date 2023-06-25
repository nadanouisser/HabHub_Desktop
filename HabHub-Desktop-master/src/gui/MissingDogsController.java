/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import HabHub.CommunityListener;
import Utils.SmsApi;
import entities.AnnonceProprietaireChien;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.AnnonceProprietaireChienService;
import utils.Statics;
import static utils.Statics.imageDirectory;

/**
 * FXML Controller class
 *
 * @author Ed
 */
public class MissingDogsController implements Initializable {

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
    private Label dogColorLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label dogRaceLabel;

    @FXML
    private Label dogGroupLabel;

    @FXML
    private Label dogLastSeenOnLabel;

    @FXML
    private Label dogLastSeenInLabel;

    @FXML
    private Text dogStory;

    @FXML
    private Label ownerNameLabel;

    @FXML
    private Label ownerLocationLabel2;

    private CommunityListener communityListener;
    private AnnonceProprietaireChien chosenAnnonce;
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
    SmsApi sms=new SmsApi();
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
    public void switchSceneDogsNextDoor (ActionEvent event) throws IOException
    {
       root = FXMLLoader.load(getClass().getResource("DogsNextDoor.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
     @FXML
    public void switchSceneDogMatchup (ActionEvent event) throws IOException
    {
    root = FXMLLoader.load(getClass().getResource("DogsMatchup.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }
    

    public ObservableList<AnnonceProprietaireChien> data = FXCollections.observableArrayList();
    AnnonceProprietaireChienService sa = new AnnonceProprietaireChienService();

    private ObservableList<AnnonceProprietaireChien> getRechercheAnnonceProprietaireChien(String input, String type) {
        List<AnnonceProprietaireChien> annoncesRecherche = new ArrayList<>();

        try {
            annoncesRecherche = sa.rechercheAnnonceProprietaireChien(input, type);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        data.clear();
        data.addAll(annoncesRecherche);
        return data;

    }

    private ObservableList<AnnonceProprietaireChien> getAnnoncesProprietaireChien(String type) {
        List<AnnonceProprietaireChien> annonces = new ArrayList<>();

        try {
            annonces = sa.afficherAnnonceProprietaireChien(type);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        data.clear();
        data.addAll(annonces);
        return data;

    }

     @FXML
    void sendSms(ActionEvent event) {
        sms.send(chosenAnnonce.getChien().getIndividu().getUtilisateur().getNumTel(), "Mission Accomplished");
    }
    private void setChosenChien(AnnonceProprietaireChien a) {
        chosenAnnonce=a;
        dogNameLabel.setText(a.getChien().getNom());
        Image genderImg = new Image(getClass().getResourceAsStream("../assets/img/female.png"));
        genderLabel.setText("Female");
        if ("M".equals(a.getChien().getSexe())) {
            genderImg = new Image(getClass().getResourceAsStream("../assets/img/male.png"));
            genderLabel.setText("Male");
        }
        genderImage.setImage(genderImg);

        ageLabel.setText(a.getChien().getAge());
        dogStory.setText(a.getChien().getDescription());
        ownerLocationLabel.setText(a.getLocalisation());
        ownerLocationLabel2.setText(a.getLocalisation());
        dogRaceLabel.setText(a.getChien().getRace());
        dogGroupLabel.setText(a.getChien().getGroupe());
        dogColorLabel.setText(a.getChien().getColor());

        dogLastSeenOnLabel.setText(a.getDatePerte().toString());
        dogLastSeenInLabel.setText(a.getLocalisation());
        ownerNameLabel.setText(a.getChien().getIndividu().getPrenom());

       File sourceimage = new File(imageDirectory+a.getChien().getImage());
                    Image image;
        try {
            image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
            Image dogImg=image;
            dogImage.setImage(dogImg);
        } catch (IOException ex) {
            Logger.getLogger(MissingDogsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
        

    }

    private void displayAnnonces(ObservableList<AnnonceProprietaireChien> annonces) {
        grid.getChildren().clear();
        int column = 0;
        int row = 1;

        try {
            communityListener = new CommunityListener() {
                @Override
                public void onClickListener(AnnonceProprietaireChien annonceProprietaireChien) {
                    setChosenChien(annonceProprietaireChien);
                }

            };
            for (int i = 0; i < annonces.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ChienCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ChienCardController chienController = fxmlLoader.getController();
                chienController.setData(annonces.get(i), communityListener);

                if (column == 5) {
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
        displayAnnonces(getAnnoncesProprietaireChien("P"));

        //displayAnnonces(getRechercheAnnonceProprietaireChien("luna","P"));
        if (data.size() > 0) {
            setChosenChien(data.get(0));
            communityListener = new CommunityListener() {
                @Override
                public void onClickListener(AnnonceProprietaireChien annonceProprietaireChien) {
                    setChosenChien(annonceProprietaireChien);
                }

            };
        }

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            displayAnnonces(getRechercheAnnonceProprietaireChien(newValue, "P"));

        });

        //displayAnnonces(annoncesProprietaireChien);
        /* 

            keyboardTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                 
            String searchKeyboard = newValue.toLowerCase();
            annoncesProprietaireChien.addAll(getRechercheAnnonceProprietaireChien(searchKeyboard,"P"));
                
            }*/
    }

}
