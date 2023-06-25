/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import HabHub.MyListener;
import entities.AnnonceAdoption;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import services.AnnonceAdoptionService;
import utils.Statics;
import static utils.Statics.imageDirectory;


/**
 * FXML Controller class
 *
 * @author Ed
 */
public class AnnonceAdoptionFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private TextField keyboardTexField;
    @FXML
    private ImageView chienImg;

    @FXML
    private Label chienNameLable;

    @FXML
    private Label adoptionLocation;

    @FXML
    private ImageView genderImage;

    @FXML
    private Label chienAge;

    @FXML
    private Label genderColor;

    @FXML
    private Label chienSexe;

    @FXML
    private Text chienDescription;

    @FXML
    private Label adoptionFosterName;

    @FXML
    private Label adoptionFosterAdress;

    @FXML
    private Text ownerDescription;
   @FXML
    private TextField searchBox;
   
    @FXML
    private ComboBox<String> filterComboBox;
    @FXML
    private ComboBox<String> filterComboBoxValue;

    @FXML
    private GridPane grid;
    @FXML
    private Button btnAdd;
    private MyListener myListener;
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
    
    public ObservableList<AnnonceAdoption> data = FXCollections.observableArrayList();
    public ObservableList<String> locations = FXCollections.observableArrayList();
    public ObservableList<String> dogNames = FXCollections.observableArrayList();
    public ObservableList<String> userNames = FXCollections.observableArrayList();
    AnnonceAdoptionService sa = new AnnonceAdoptionService();
    
    private ObservableList<String> getLocaions() {
        HashSet<String> loc = new HashSet<>();

        try {
            loc = sa.afficherLocation();
            
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        locations.addAll(loc);
        return locations;

    }
    
    private ObservableList<String> getDogNames() {
        HashSet<String> dogn = new HashSet<>();

        try {
            dogn = sa.afficherDogName();
            
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        dogNames.addAll(dogn);
        return dogNames;

    }
     private ObservableList<String> getUserNames() {
        HashSet<String> usen = new HashSet<>();

        try {
            usen = sa.afficherUserName();
            
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        userNames.addAll(usen);
        return userNames;

    }
    
    private ObservableList<AnnonceAdoption> getAnnonceAdoption() {
        List<AnnonceAdoption> annonces = new ArrayList<>();

        try {
            annonces = sa.displayAnnonceAdoption();
            
          System.out.println(annonces);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        data.clear();
        data.addAll(annonces);
        return data;

    }
    private ObservableList<AnnonceAdoption> getLocationAnnonceAdoption(String Filter) {
        List<AnnonceAdoption> annonces = new ArrayList<>();

        try {
            annonces = sa.afficherAnnonceLocation(Filter);
            
          System.out.println(annonces);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        data.clear();
        data.addAll(annonces);
        return data;

    }
     private ObservableList<AnnonceAdoption> getDogNameAnnonceAdoption(String Filter) {
        List<AnnonceAdoption> annonces = new ArrayList<>();

        try {
            annonces = sa.afficherAnnonceDogName(Filter);
            
          System.out.println(annonces);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        data.clear();
        data.addAll(annonces);
        return data;

    }
      private ObservableList<AnnonceAdoption> getUserNameAnnonceAdoption(String Filter) {
        List<AnnonceAdoption> annonces = new ArrayList<>();

        try {
            annonces = sa.afficherAnnonceUserName(Filter);
            
          System.out.println(annonces);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        data.clear();
        data.addAll(annonces);
        return data;

    }
       private ObservableList<AnnonceAdoption> getVaccinatedAnnonceAdoption() {
        List<AnnonceAdoption> annonces = new ArrayList<>();
        int i= 1 ;

        try {
            annonces = sa.afficherAnnonceVaccinated(i);
            
          System.out.println(annonces);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        data.clear();
        data.addAll(annonces);
        return data;

    }
       
        private ObservableList<AnnonceAdoption> getNoneVaccinatedAnnonceAdoption() {
        List<AnnonceAdoption> annonces = new ArrayList<>();
        int o=0;

        try {
            annonces = sa.afficherAnnonceNoneVaccinated(o);
            
          System.out.println(annonces);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        data.clear();
        data.addAll(annonces);
        return data;

    }
         private ObservableList<AnnonceAdoption> getMaleAnnonceAdoption() {
        List<AnnonceAdoption> annonces = new ArrayList<>();

        try {
            annonces = sa.afficherAnnonceMale();
            
          System.out.println(annonces);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        data.clear();
        data.addAll(annonces);
        return data;

    }
          private ObservableList<AnnonceAdoption> getFemaleAnnonceAdoption() {
        List<AnnonceAdoption> annonces = new ArrayList<>();

        try {
            annonces = sa.afficherAnnoncefemale();
            
          System.out.println(annonces);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        data.clear();
        data.addAll(annonces);
        return data;

    }
       
    private ObservableList<AnnonceAdoption> getRechercheAnnonceAdoption(String input) {
        List<AnnonceAdoption> annoncesRecherche = new ArrayList<>();

        try {
            annoncesRecherche = sa.rechercheAnnonceAdoption(input);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        data.clear();
        data.addAll(annoncesRecherche);
        return data;

    }
    private void setChosenChien(AnnonceAdoption a) {
        chienNameLable.setText(a.getIdChien().getNom());
        Image genderImg;
        if (a.getIdChien().getSexe().toLowerCase().equals("m")) {
             genderImg = new Image(getClass().getResourceAsStream("../assets/img/male.png"));
            chienSexe.setText("Masculin");
        }
        else{
             genderImg = new Image(getClass().getResourceAsStream("../assets/img/female.png"));
        chienSexe.setText("Feminin");
            
        }
        genderImage.setImage(genderImg);
        chienAge.setText(a.getIdChien().getAge());
        chienDescription.setText(a.getIdChien().getDescription());
        adoptionLocation.setText(a.getLocalisation());
        adoptionFosterName.setText(a.getIdIndividu().getPrenom());
        adoptionFosterAdress.setText(a.getIdIndividu().getAdresse());
        
         File sourceimage = new File(imageDirectory+a.getIdChien().getImage());
                    Image image;
        try {
            image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
            Image dogImg=image;
            chienImg.setImage(dogImg);
        } catch (IOException ex) {
            Logger.getLogger(DogsMatchupController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        /*chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
                "    -fx-background-radius: 30;");*/
    }
    private void displayAnnonces(ObservableList<AnnonceAdoption> annonces) {
        grid.getChildren().clear();
        int column = 0;
        int row = 1;

        try {
            myListener = new MyListener() {
                @Override
                public void onClickListener(AnnonceAdoption annonceAdoption) {
                    setChosenChien(annonceAdoption);
                }

            };
            for (int i = 0; i < annonces.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ChienFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ChienFXMLController chienController = fxmlLoader.getController();
                chienController.setData(annonces.get(i), myListener);

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
    
    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void switchSceneAnnonceChien(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("AddAnnonceAdoptionFXML.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    ObservableList<String> sexes = FXCollections.observableArrayList("Male", "Female");
    ObservableList<String> vaccinations = FXCollections.observableArrayList("Vaccinated", "None vaccinated");
    
    @FXML
    private void setFilterValue(ActionEvent event) throws IOException
    {
        String selectedFilter = filterComboBox.getSelectionModel().getSelectedItem();
        if(selectedFilter.equals("Dog sexe")){
            filterComboBoxValue.setItems(sexes);
        }
        else if (selectedFilter.equals("Dog name")){
            filterComboBoxValue.setItems(dogNames);
        }
        else if (selectedFilter.equals("User name")){
           filterComboBoxValue.setItems(userNames);
        }
        else if (selectedFilter.equals("Location")){
            filterComboBoxValue.setItems(locations);
            
        }
        else{
            filterComboBoxValue.setItems(vaccinations);
        }
        filterComboBoxValue.equals(dogNames);
        //return filterComboBoxValue;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        displayAnnonces(getAnnonceAdoption());
        getLocaions();
        getDogNames();
        getUserNames();
        //setFilterValue();
        
        if (data.size() > 0) {
            setChosenChien(data.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(AnnonceAdoption annonceAdoption) {
                    setChosenChien(annonceAdoption);
                }

            };
        }
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            displayAnnonces(getRechercheAnnonceAdoption(newValue));

        });
         /*try {
            locations.addAll(sa.afficherLocation());
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        try {
            dogNames.addAll(sa.afficherDogName());
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        try {
            userNames.addAll(sa.afficherUserName());
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }*/
        
        
        ObservableList<String> filterList = FXCollections.observableArrayList("Dog sexe", "Dog name","User name","Location","Vaccination");
        filterComboBox.setItems(filterList);
        filterComboBox.setValue("Select filter");
        
        filterComboBoxValue.setOnAction(event->{
            
           String selectedFilterValue = filterComboBoxValue.getSelectionModel().getSelectedItem();
           for(int i=0;i<locations.size();i++){
               if (locations.get(i).equals(selectedFilterValue)){
                   
                    displayAnnonces(getLocationAnnonceAdoption(selectedFilterValue));
               }
           }
            for(int i=0;i<dogNames.size();i++){
               if (dogNames.get(i).equals(selectedFilterValue)){
                   
                    displayAnnonces(getDogNameAnnonceAdoption(selectedFilterValue));
               }
           }
             for(int i=0;i<userNames.size();i++){
               if (userNames.get(i).equals(selectedFilterValue)){
                   
                    displayAnnonces(getUserNameAnnonceAdoption(selectedFilterValue));
               }
           }
              for(int i=0;i<vaccinations.size();i++){
               if (vaccinations.get(i).equals(selectedFilterValue)){
                   if(selectedFilterValue.equals("yes")){
                       
                       displayAnnonces(getVaccinatedAnnonceAdoption());
                   }
                   else{
                       
                       displayAnnonces(getNoneVaccinatedAnnonceAdoption());
                   }
                    
               }
           }
               for(int i=0;i<sexes.size();i++){
               if (sexes.get(i).equals(selectedFilterValue)){
                   if(selectedFilterValue.equals("Male")){
                       displayAnnonces(getMaleAnnonceAdoption());
                   }
                   else {
                       displayAnnonces(getFemaleAnnonceAdoption());
                   }
                   
                    
               }
           }
        });
        
        
        
        
        
       
        
        
        

}
}