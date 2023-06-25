/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import services.ProduitService;
import entities.Produit;
import entities.panier;

import java.sql.SQLException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import services.PanierService;
import utils.Statics;
import HabHub.Listen;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import static utils.Statics.imageDirectory;



/**
 * FXML Controller class
 *
 * @author Ed
 */
public class ProduitFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      @FXML
    private ImageView ProdImage;

    @FXML
    private Label nameLabel;
    @FXML
    private Label Price;
    
   
     @FXML
    private ImageView EvalImage;
    

    @FXML
    private Button AjPanier;
    
     private Produit produit;
     
      @FXML
    private void click(MouseEvent mouseEvent) {
        Listen.onClickListener(produit);
    }
    
 private Listen Listen;
 
 
 @FXML
    void AddItemToCart(ActionEvent event) {
        /*  int quantity = 1;
        String req = "INSERT INTO `panier` (  idProduit, idUtilisateur,quantite)  "
                + "VALUES ( ?, ?, ?) ";
        PanierService ps = new PanierService();
        panier pa = new panier(chosenProduit, Statics.currentIndividu.getIdIndividu() , (Integer.parseInt(quantity.getText())) );
         ps.ajouterPa(pa );*/

    }
         
         
    public void setData(Produit produit,Listen myListener) {
        this.produit = produit;
        this.Listen=myListener;
        nameLabel.setText(produit.getNom());
        String s = Float.toString(produit.getPrix())+"DNT";
        Price.setText(s);
       
       
        System.out.println(produit.getImage());
        File sourceimage = new File(imageDirectory+produit.getImage());
                    Image image;
        try {
            image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
            Image PImg=image;
            ProdImage.setImage(PImg);
        } catch (IOException ex) {
            Logger.getLogger(BusinessFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        
        System.out.println(this.produit.getIdCategorie());
        
       /* if (produit.getIdCategorie()== 5){
                Image Im = new Image(getClass().getResourceAsStream("../assets/img/revue/5.png"));
        EvalImage.setImage(Image);
        }
      
        if (produit.getIdCategorie()== 4){
                Image Im = new Image(getClass().getResourceAsStream("../assets/img/revue/4.png"));
        EvalImage.setImage(Image);
        }
        if (produit.getIdCategorie()== 3){
                Image Im = new Image(getClass().getResourceAsStream("../assets/img/revue/3.png"));
        EvalImage.setImage(Image);
        }
        
        if (produit.getIdCategorie()== 2){
                Image Im = new Image(getClass().getResourceAsStream("../assets/img/revue/2.png"));
        EvalImage.setImage(Image);
        }
        
         if (produit.getIdCategorie()== 1){
                Image Im = new Image(getClass().getResourceAsStream("../assets/img/revue/1.png"));
        EvalImage.setImage(Image);
        }
        
        else {
             Image Im = new Image(getClass().getResourceAsStream("../assets/img/revue/0.png"));
        EvalImage.setImage(Image);
        }
        */
        
        
        
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
