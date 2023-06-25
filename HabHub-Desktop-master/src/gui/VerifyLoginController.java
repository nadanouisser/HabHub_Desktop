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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NADA_USER
 */
public class VerifyLoginController implements Initializable {

        private Stage stage;
 private Scene scene;
 private Parent parent;
 
        @FXML
    private TextField code;

    @FXML
    private Button verifyButton;
    
      @FXML
    void entreaccount(ActionEvent event)throws IOException, SQLException  {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/profiledit.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

    }

    @FXML
    void Verify(ActionEvent event)throws IOException, SQLException  {

        
        if( code.getText()=="7789"){
            
            entreaccount(event);
    }
    
         }
    
      @FXML
    void returnEnterMail(ActionEvent event)throws IOException, SQLException  {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/EnterMail.fxml"));
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
