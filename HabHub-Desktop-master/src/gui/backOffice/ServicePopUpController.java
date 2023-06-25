/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User
 */


public class ServicePopUpController implements Initializable {
    @FXML
    private TextField EmailInput;

    @FXML
    private PasswordField PasswordInput;

    @FXML
    private TextField BusinessNameInput;

    @FXML
    private TextField PhoneInput;

    @FXML
    private TextField LocationInput;

    @FXML
    private TextField HoursInput;

    @FXML
    private TextArea DescriptionText;

    @FXML
    private Button CancelButton;

    @FXML
    private Button SubmitButton;

    @FXML
    void submit(MouseEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
