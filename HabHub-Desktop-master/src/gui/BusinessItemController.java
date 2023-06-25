/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import HabHub.BusinessListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import entities.Business;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import static utils.Statics.imageDirectory;



/**
 * FXML Controller class
 *
 * @author 
 */
public class BusinessItemController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
     @FXML
    private ImageView businessImage;

    @FXML
    private Label businessTitle;

    @FXML
    private Label villeLabel;

    @FXML
    private Label nbEtoilesLabel;

    @FXML
    private Label horaireLabel;

    @FXML
    private Button bookingButton;

    private Business business;
     
    private BusinessListener businessListener;
    
    @FXML
    void click(MouseEvent mouseEvent) {
        //System.out.println("ahmed clei");
        businessListener.onClickListener(business);
        
    } 
    

    public void setData(Business business,BusinessListener businessListener) {
        this.business = business;
        this.businessListener = businessListener;

        businessTitle.setText(business.getTitre());
        villeLabel.setText(business.getVille());
        horaireLabel.setText(business.getHoraire());
        nbEtoilesLabel.setText("5");
        File sourceimage = new File(imageDirectory+business.getImage());
                    Image image;
        try {
            image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
            Image businessImg=image;
            businessImage.setImage(businessImg);
        } catch (IOException ex) {
            Logger.getLogger(DogsMatchupController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
