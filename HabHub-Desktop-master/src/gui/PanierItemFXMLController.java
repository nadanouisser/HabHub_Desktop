/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Produit;
import entities.panier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.PanierService;
import utils.Statics;
import HabHub.Listen;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import static utils.Statics.imageDirectory;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PanierItemFXMLController implements Initializable {
    
    
        @FXML
    private Button del;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemName;

    @FXML
    private Label itemPrice;

    @FXML
    private Button minus;

    @FXML
    private Label quantity;

    @FXML
    private Button plus;

    @FXML
     public Label TotItemPrice;
    
    private panier panier;
    
  

   
    
     PanierService ps = new PanierService();
    
     
     private ListView<panier> listPaniers;
     
     private Stage stage;
    private Scene scene;
    private Parent parent;

    
    
    @FXML
    void minusQuantity(ActionEvent event) {
        if (Integer.parseInt(quantity.getText()) > 1) {
            quantity.setText(Integer.toString(Integer.parseInt(quantity.getText()) - 1));
            TotItemPrice.setText(Float.toString(Integer.parseInt(quantity.getText()) * (Float.parseFloat(itemPrice.getText()))));
            //sommeprod.setText( Float.toString (Float.parseFloat(itemPrice.getText())  - (Float.parseFloat(sommeprod.getText()))  ));
        }

    }

    @FXML
    void plusQuantity(ActionEvent event) {
         quantity.setText(Integer.toString(Integer.parseInt(quantity.getText()) + 1));
        TotItemPrice.setText(Float.toString(Integer.parseInt(quantity.getText()) * (Float.parseFloat(itemPrice.getText()))));
        //sommeprod.setText( Float.toString (Float.parseFloat(itemPrice.getText()))  +(Float.parseFloat(sommeprod.getText()))  );
    }
    
     @FXML
    public void deleteItem(ActionEvent event) throws SQLException, IOException  {
       
          try {
            ps.deletePanier(panier.getIdPanier());
            switchSceneCart( event);
            System.out.println(panier.getIdPanier());
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
                }

    }

       @FXML
    void switchSceneCart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/PanierFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    
    
     public void setData(panier pa) {
        this.panier = pa;
        
       itemName.setText(pa.getIdProduit().getNom());
       String s = Float.toString(pa.getIdProduit().getPrix());
        itemPrice.setText(s);
        System.out.println(pa.getIdProduit().getImage());
        
         File sourceimage = new File(imageDirectory+pa.getIdProduit().getImage());
                    Image image;
        try {
            image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
            Image PImg=image;
            itemImage.setImage(PImg);
        } catch (IOException ex) {
            Logger.getLogger(DogsMatchupController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        quantity.setText(Integer.toString(pa.getQuantite()));
        TotItemPrice.setText(Float.toString(Integer.parseInt(quantity.getText()) * (Float.parseFloat(itemPrice.getText()))));
        
       
    }
     
     
     
      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
