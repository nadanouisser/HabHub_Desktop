package gui;

import HabHub.MyListener;
import entities.AnnonceAdoption;
import entities.Chien;
import entities.Individu;
import java.io.File;
import services.ChienService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.AnnonceAdoptionService;
import utils.Statics;
import static utils.Statics.imageDirectory;

public class AddAnnonceAdoptionFXMLController implements Initializable {

    @FXML
    private TextField dogNameLabel;

    @FXML
    private TextField ageLabel;

    @FXML
    private TextField colorLabel;

    @FXML
    private ComboBox<String> ageComboBox;

    @FXML
    private ComboBox<String> raceComboBox;

    @FXML
    private ComboBox<String> groupeComboBox;
    @FXML
    private ToggleGroup dogVaccination;

    @FXML
    private ToggleGroup dogGender;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private ImageView dogImageView;

    @FXML
    private AnchorPane test;
    
    @FXML
    private Label genderLabel;
    
    @FXML
    private Label vaccinationLabel;
    @FXML
    private Label ageMessage;
    @FXML
    private Button addDog;

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
    String insertImage(ActionEvent event) throws SQLException {
        FileChooser fc = new FileChooser();
        String imageFilePath = "";
        String imageName = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {

            Image imageToBeSaved = new Image(f.toURI().toString(), 135, 130, false, true);

            dogImageView.setImage(imageToBeSaved);

        }

        return imageName;
    }

    ChienService cs = new ChienService();
    AnnonceAdoptionService as = new AnnonceAdoptionService();

    public void switchSceneAnnonceAdoption(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AnnonceAdoptionFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    @FXML
    void ajouterChien(ActionEvent event) throws IOException, SQLException {
        String age;
        String gender;
        boolean vaccination = false;
        String selectedAge = ageComboBox.getSelectionModel().getSelectedItem();
        String selectedRace = raceComboBox.getSelectionModel().getSelectedItem();
        String selectedGroupe = groupeComboBox.getSelectionModel().getSelectedItem();
                
            
        if (selectedAge.equals("Months")) {
            age = ageLabel.getText() + " mo";

        } else {
            age = ageLabel.getText() + " yr";
        }
        RadioButton selectedGender = (RadioButton) dogGender.getSelectedToggle();
        String selectedGenderValue;
            
        selectedGenderValue = selectedGender.getText();
        if (selectedGenderValue.equals("Male")){
            gender="M";
            }
            else{
                gender="F";
                }
            
        
        RadioButton selectedVaccination = (RadioButton) dogVaccination.getSelectedToggle();
        String selectedVaccinationValue;
            
        selectedVaccinationValue = selectedVaccination.getText();
        
        if (selectedVaccinationValue.equals("Yes")){
           vaccination=true;
        }
        else{
            vaccination=false;
        }   
        
        if(dogNameLabel.getText().isEmpty() | ageLabel.getText().isEmpty()|colorLabel.getText().isEmpty()|descriptionTextArea.getText().isEmpty()|!isNumeric(ageLabel.getText())){
            
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Fill all blank fields!");
                alert.showAndWait();
                
            
             if(dogNameLabel.getText().isEmpty()){
                dogNameLabel.setStyle("-fx-border-width:2px; -fx-border-color:#d30c0c");}
             
             if(ageLabel.getText().isEmpty()){
                ageLabel.setStyle("-fx-border-width:2px; -fx-border-color:#d30c0c");}
             
             if(!isNumeric(ageLabel.getText())){
                 ageMessage.setStyle("-fx-text-fill:#d30c0c");
                 ageLabel.setStyle("-fx-border-width:2px; -fx-border-color:#d30c0c");}
             
             if(colorLabel.getText().isEmpty()){
                colorLabel.setStyle("-fx-border-width:2px; -fx-border-color:#d30c0c");}
             
             if(descriptionTextArea.getText().isEmpty()){
                descriptionTextArea.setStyle("-fx-border-width:2px; -fx-border-color:#d30c0c");}
             
             
             
                }
            
             else{
                String nom = dogNameLabel.getText();
                String description = descriptionTextArea.getText();
                String color = colorLabel.getText();
                Statics.currentIndividu.getIdIndividu();
                String image = Integer.toString(Statics.currentIndividu.getIdIndividu()) + "_" + Integer.toString(as.getAnnonceAdoptionNumberByIdIndividu(Statics.currentIndividu.getIdIndividu())+ 1)+"_Adoption_"+".png";
                File file = new File(imageDirectory
                + image);
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(dogImageView.getImage(), null), "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        
        Chien c = new Chien(nom, gender, age, vaccination, description, image, color, selectedRace, selectedGroupe);
        cs.ajouterChienSansProprietaire(c);
        Chien nc= cs.fetchAddedChien();
        AnnonceAdoption a = new AnnonceAdoption(Statics.currentIndividu,nc,nc.getDescription(),Statics.currentIndividu.getAdresse());
        List <AnnonceAdoption> allAnnonce = new ArrayList(as.displayAnnonceAdoption());
        boolean verif= false;
        
        for (int i = 0; i < allAnnonce.size(); i++){
         
            String descrip = allAnnonce.get(i).getIdChien().getDescription();
            String couleur = allAnnonce.get(i).getIdChien().getColor();
            String ra = allAnnonce.get(i).getIdChien().getRace();
            String gr = allAnnonce.get(i).getIdChien().getGroupe();
            String aa = allAnnonce.get(i).getIdChien().getAge();
            String name = allAnnonce.get(i).getIdChien().getNom();
            String sex= allAnnonce.get(i).getIdChien().getSexe();
            
            
           
               if(name.equals(a.getIdChien().getNom()) && aa.equals(a.getIdChien().getAge()) && couleur.equals(a.getIdChien().getColor())
                       && ra.equals(a.getIdChien().getRace()) && gr.equals(a.getIdChien().getGroupe()) && descrip.equals(a.getIdChien().getDescription())  && sex.equals(a.getIdChien().getSexe()) )
                {
                verif= true;
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Existing announce");
                alert.setHeaderText(null);
                alert.setContentText("Announcement already exists");
                alert.showAndWait();
                break;
            }
        }
        if( !verif){
            as.addAnnonceAdoption(a);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Announcement added successfully");
            alert.showAndWait();
        } 
             }
    }

 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> ageList = FXCollections.observableArrayList("Months", "Years");
        ObservableList<String> groupeList = FXCollections.observableArrayList("Working", "Herding", "Toy", "Hound", "Sporting", "Non-Sporting", "Terrier");
        ObservableList<String> raceList = FXCollections.observableArrayList(
                "Golden Retriever",
                "Husky",
                "Labrador Retriever",
                "French Bulldog",
                "German Shepherd",
                "English Bulldog",
                "Poodle",
                "Beagle",
                "Rottweiler",
                "French Bulldog");

        ageComboBox.setItems(ageList);
        ageComboBox.getSelectionModel().selectFirst();
        raceComboBox.setItems(raceList);
        raceComboBox.getSelectionModel().selectFirst();
        groupeComboBox.setItems(groupeList);
        groupeComboBox.getSelectionModel().selectFirst();

    }
    /**/

}
