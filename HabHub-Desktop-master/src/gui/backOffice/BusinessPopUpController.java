/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import entities.Business;
import entities.Utilisateur;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.UserBusinessServices;

/**
 * FXML Controller class
 *
 * @author User
 */
public class BusinessPopUpController implements Initializable {
    
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
    private TextField CityInput;

    @FXML
    private TextField HoursInput;

    @FXML
    private TextArea DescriptionText;

    @FXML
    private Button CancelButton;

    @FXML
    private Button SubmitButton;
    
    private boolean update;
    private int idUpdate;

    UserBusinessServices ubs= new UserBusinessServices();
    
    @FXML
    private void clean() {
        PasswordInput.setText(null);
        BusinessNameInput.setText(null);
        PhoneInput.setText(null);
        LocationInput.setText(null);
        CityInput.setText(null);
        HoursInput.setText(null);
        EmailInput.setText(null);
        DescriptionText.setText(null);

   
    }
    void setTextField(String BusinessNameInput,String PhoneInput,String LocationInput,String CityInput,String HoursInput,String EmailInput,String DescriptionText){
        this.BusinessNameInput.setText(BusinessNameInput);
        this.PhoneInput.setText(PhoneInput);
        this.LocationInput.setText(LocationInput);
        this.CityInput.setText(CityInput);
        this.HoursInput.setText(HoursInput);
        this.EmailInput.setText(EmailInput);
        this.DescriptionText.setText(DescriptionText);

    }
    @FXML
    void submit(MouseEvent event) {
Utilisateur u = new Utilisateur(EmailInput.getText(),PasswordInput.getText(),Integer.parseInt(PhoneInput.getText()),"business");
        Business b = new Business(1,BusinessNameInput.getText(),DescriptionText.getText(),HoursInput.getText(),CityInput.getText(),LocationInput.getText(),"vet",1,1,"");
        
         
        if (update) {
            ubs.UpdateData(idUpdate,b);
        }
        else{
            try {
                ubs.ajouterVet(b, u);
            } catch (SQLException ex) {
                Logger.getLogger(BusinessPopUpController.class.getName()).log(Level.SEVERE, null, ex);
            }

        
        }
        clean();
       
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    void setUpdate(boolean b) {
        this.update = b;

    }
void setIdUpdate(int i) {
        this.idUpdate = i;

    }
    
}
