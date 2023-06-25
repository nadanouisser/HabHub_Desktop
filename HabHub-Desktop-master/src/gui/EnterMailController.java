/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import MailAPI.JavaMailUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author NADA_USER
 */
public class EnterMailController implements Initializable {

        private Stage stage;
 private Scene scene;
 private Parent parent;
 
    
    @FXML
    private TextField gmail;
    
        @FXML
    void SwitchScene(ActionEvent event) throws IOException, SQLException  {
            Parent root = FXMLLoader.load(getClass().getResource("../gui/verifylogin.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    void SendEmail(ActionEvent event) throws IOException, SQLException  {
   JavaMailUtil u = new JavaMailUtil();
            try {
                JavaMailUtil.sendMail(gmail.getText());
                
            } catch (Exception ex) {
                Logger.getLogger(EnterMailController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ( gmail.getText()==null){
                System.out.println(" gmail not found");}
            else{
                SwitchScene(event);}
   
            
            SwitchScene(event);
    }
    
        @FXML
    void returnsignup(ActionEvent event)throws IOException, SQLException  {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/log_in.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
