/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import entities.Chien;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.ChienService;

/**
 * FXML Controller class
 *
 * @author Ed
 */
public class ChienPopUpController implements Initializable {

    @FXML
    private TextField dogName;
    @FXML
    private TextField dogColor;
    @FXML
    private TextField dogAge;
    @FXML
    private TextField dogOwnerName;
    @FXML
    private TextField dogBreed;
    @FXML
    private TextField dogGroup;
    @FXML
    private TextArea dogDescription;
    @FXML
    private Button CancelButton;
    @FXML
    private Button SubmitButton;

    private boolean update;
    private int idUpdate;
    ChienService cs = new ChienService();
    @FXML
    void submit(MouseEvent event) throws SQLException {
        Chien c = new Chien(dogName.getText(),
        dogAge.getText(),
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
       
                 

    }

    @FXML
    private void clean() {
        dogName.setText(null);
        dogColor.setText(null);
        dogAge.setText(null);
        dogBreed.setText(null);
        dogGroup.setText(null);
        dogDescription.setText(null);

    }
    void setTextField(String dogName, String ownerName, String age, String description,String color,String breed,String group) {

        this.dogName.setText(dogName);
        dogColor.setText(color);
        dogAge.setText(age);
        dogOwnerName.setText(ownerName);
        dogBreed.setText(breed);
        dogGroup.setText(group);
        dogDescription.setText(description);

    }
void setUpdate(boolean b) {
        this.update = b;

    }
void setIdUpdate(int i) {
        this.idUpdate = i;

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
