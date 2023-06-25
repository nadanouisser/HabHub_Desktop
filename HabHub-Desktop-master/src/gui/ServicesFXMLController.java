/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import HabHub.BusinessListener;
import HabHub.ServiceListener;
import entities.Business;
import entities.ServiceBusiness;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ServicesFXMLController implements Initializable {

    private Boolean clicked = false;
    @FXML
    private Text serviceLabel;

    @FXML
    private Text priceLabel;

    private ServiceBusiness serviceBusiness;
    @FXML
    private AnchorPane serviceAnchor;
    private ServiceListener serviceListener;

    
    @FXML
    void click(MouseEvent mouseEvent) {
        serviceListener.onClickListener(serviceBusiness);
    } 
    @FXML
    void changeStyle(MouseEvent event) {
        clicked = !clicked; 
       if (clicked) {
            serviceAnchor.setStyle("-fx-background-color: #FFEEE8;");
        } else {
            serviceAnchor.setStyle("-fx-background-color: #FFFFFF;");

        }
            
        
    }

    public void setData(ServiceBusiness serviceBusiness,ServiceListener serviceListener) {
        
        this.serviceBusiness = serviceBusiness;
        this.serviceListener =serviceListener;
                
        serviceLabel.setText(serviceBusiness.getNomService());
        priceLabel.setText(Float.toString(serviceBusiness.getPrix()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
