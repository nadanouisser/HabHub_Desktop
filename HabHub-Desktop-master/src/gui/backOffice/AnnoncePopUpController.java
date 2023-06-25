/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import entities.AnnonceAdoption;
import entities.Chien;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import services.AnnonceAdoptionService;
import services.ChienService;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author Ed
 */
public class AnnoncePopUpController implements Initializable {

    @FXML
    private TextField dogNameLabel;
    @FXML
    private ToggleGroup dogVaccination;

    @FXML
    private ToggleGroup dogGender;
    @FXML
    private TextField ageLabel;
    @FXML
    private TextField colorLabel;
    
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private ComboBox<String> ageComboBox;

    @FXML
    private ComboBox<String> raceComboBox;

    @FXML
    private ComboBox<String> groupeComboBox;
    @FXML
    private Button CancelButton;
    @FXML
    private Button SubmitButton;
    @FXML
    private Label ageMessage;

    private boolean update;
    private int idUpdate;
    
    ChienService cs = new ChienService();
    AnnonceAdoptionService as = new AnnonceAdoptionService();
    
    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
                    }
     void setTextField(String dogName,String age, String description,String color,String breed,String group) {

        dogNameLabel.setText(dogName);
        colorLabel.setText(color);
        ageLabel.setText(age);
        //dogOwnerName.setText(ownerName);
        raceComboBox.setValue(breed);
        groupeComboBox.setValue(group);
        descriptionTextArea.setText(description);

    }
    @FXML
    void submit(MouseEvent event) throws SQLException {
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
        /*if(! update){
        if(dogNameLabel.getText().isEmpty() | ageLabel.getText().isEmpty()|colorLabel.getText().isEmpty()|descriptionTextArea.getText().isEmpty()|!isNumeric(ageLabel.getText())){
            
                Alert alert = new Alert(Alert.AlertType.ERROR);
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
        }*/
            
             
                String nom = dogNameLabel.getText();
                String description = descriptionTextArea.getText();
                String color = colorLabel.getText();
                Statics.currentIndividu.getIdIndividu();
                
                Chien c = new Chien(nom, gender, age, vaccination, description, color, selectedRace, selectedGroupe);
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
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Existing announce");
                alert.setHeaderText(null);
                alert.setContentText("Announcement already exists");
                alert.showAndWait();
                break;
            }
        }
                    if( !verif){
                        if(!update){
                        as.addAnnonceAdoption(a);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Announcement added successfully");
                        alert.showAndWait();
                        }
                        else {
                            AnnonceAdoption b = new AnnonceAdoption(idUpdate,Statics.currentIndividu,nc,nc.getDescription(),Statics.currentIndividu.getAdresse());
                            as.updateAnnonceAdoption(nc,b);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setHeaderText(null);
                            alert.setContentText("Announcement modified  successfully");
                            alert.showAndWait();
                        }
                    }  
             
             
                clean();
                
    }
    
    
    
  void setUpdate(boolean b) {
   this.update = b;

    }
  
  
  
void setIdUpdate(int i) {
        this.idUpdate = i;

    }
@FXML
    private void clean() {
        dogNameLabel.setText(null);
        colorLabel.setText(null);
        ageLabel.setText(null);
        raceComboBox.setValue("Select a race");
        groupeComboBox.setValue("Select a group");
        descriptionTextArea.setText(null);

    }
   
    /**
     * Initializes the controller class.
     */
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
        //raceComboBox.getSelectionModel().selectFirst();
        groupeComboBox.setItems(groupeList);
        //groupeComboBox.getSelectionModel().selectFirst();
        // TODO
    }

        }

        /*Chien c = new Chien(adoptionChienName.getText(),
        adoptionDescription.getText(),
        dogDescription.getText(),
        dogColor.getText(),
        dogBreed.getText(),
        dogGroup.getText());
        if (update) {
            cs.updateChien(idUpdate,c);
        }
        else{
        
        cs.ajouterChienSansProprietaireb(c);
        
        }
        clean();
       
                 

    }*/
    
        

   
    


