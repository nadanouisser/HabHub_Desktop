package gui;

import HabHub.CommunityListener;
import entities.Chien;
import entities.Individu;
import java.io.File;
import services.ChienService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import services.UserIndividuServices;
import utils.Statics;
import static utils.Statics.imageDirectory;

public class AddDogController implements Initializable {

    @FXML
    private Button DogsNextDoor;

    @FXML
    private Button DogsMatchup;

    @FXML
    private Button MissingDogs;

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
    private RadioButton vaccinationTrue;

    @FXML
    private ToggleGroup dogVaccination;

    @FXML
    private RadioButton vaccinationFalse;

    @FXML
    private RadioButton male;

    @FXML
    private Button dogsNextDoorButton;

    @FXML
    private Button dogMatchupButton;

    @FXML
    private Button missingDogsButton;

    @FXML
    private ToggleGroup dogGender;

    @FXML
    private RadioButton female;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private ImageView dogImageView;

    @FXML
    private AnchorPane test;

    private String dogImageName;
    
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

    UserIndividuServices uis = new UserIndividuServices();

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

    @FXML
    void switchSceneCommunity(ActionEvent event) throws IOException {
        System.out.println("cancel");
        if (Statics.currentIndividu.getProprietaireChien()) {
            root = FXMLLoader.load(getClass().getResource("MyDogs.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("CommunityInitialPage.fxml"));
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    ChienService cs = new ChienService();

    public void switchSceneMyDogs(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MyDogs.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ajouterChien(ActionEvent event) throws IOException, SQLException {
        String age;
        String gender;
        boolean vaccination;
        String selectedAge = ageComboBox.getSelectionModel().getSelectedItem();
        String selectedRace = raceComboBox.getSelectionModel().getSelectedItem();
        String selectedGroupe = groupeComboBox.getSelectionModel().getSelectedItem();
        if (selectedAge.equals("Months")) {
            age = ageLabel.getText() + " mo";

        } else {
            age = ageLabel.getText() + " yr";
        }
        RadioButton selectedGender = (RadioButton) dogGender.getSelectedToggle();
        String selectedGenderValue = selectedGender.getText();
        if (selectedGenderValue.equals("Male")) {
            gender = "M";
        } else {
            gender = "F";
        }
        RadioButton selectedVaccination = (RadioButton) dogVaccination.getSelectedToggle();
        String selectedVaccinationValue = selectedVaccination.getText();
        if (selectedVaccinationValue.equals("Yes")) {
            vaccination = true;
        } else {
            vaccination = false;
        }

        String nom = dogNameLabel.getText();
        String description = descriptionTextArea.getText();
        String color = colorLabel.getText();
        Statics.currentIndividu.getIdIndividu();
        String image = Integer.toString(Statics.currentIndividu.getIdIndividu()) + "_" + Integer.toString(uis.getDogsNumberByIdIndividu(Statics.currentIndividu.getIdIndividu()) + 1)+"_"+"hub"
                +".png";
        File file = new File(imageDirectory+ image);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(dogImageView.getImage(), null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Individu i = new Individu(Statics.currentIndividu.getIdIndividu());
        Chien c = new Chien(i, nom, gender, age, vaccination, description, image, color, selectedRace, selectedGroupe);
        cs.ajouterChienProprietaire(c);
        Statics.currentIndividu = uis.findIndividuById(Statics.currentIndividu.getIdIndividu());
        System.out.println(Statics.currentIndividu);
        switchSceneMyDogs(event);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Statics.currentIndividu.getProprietaireChien()) {
            dogsNextDoorButton.setDisable(false);
            missingDogsButton.setDisable(false);
            dogMatchupButton.setDisable(false);
        }

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

}
