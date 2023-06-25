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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import services.PanierService;
import services.ProduitService;
import utils.Statics;
import HabHub.Listen;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PanierFXMLController implements Initializable {

    
    @FXML
    private GridPane grid;
    
    @FXML
    private Label sommeprod;

    @FXML
    private Label total;

    @FXML
    private Label clearCartClickLabel;

    @FXML
    private Button order;
    
    @FXML
    private Button clear;
    
    
   PanierService ps = new PanierService();
   
   private panier panier;
    
   
    private Stage stage;
    private Scene scene;
    private Parent root;
    
      @FXML
    void switchAdoption(ActionEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("AnnonceAdoptionFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    void switchBoutique(ActionEvent event) throws IOException {
 root = FXMLLoader.load(getClass().getResource("BoutiqueFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    void switchHome(ActionEvent event) throws IOException {
 root = FXMLLoader.load(getClass().getResource("BusinessFXML.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    void switchHub(ActionEvent event) throws IOException {
  if (Statics.currentIndividu.getProprietaireChien())
        {
         root = FXMLLoader.load(getClass().getResource("MyDogs.fxml"));
        }
        else
        { root = FXMLLoader.load(getClass().getResource("CommunityInitialPage.fxml"));
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchProfile(ActionEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("profiledit.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    
    } 
   
    
      public ObservableList<panier> paniers = FXCollections.observableArrayList();
    PanierService pa = new PanierService();

    private ObservableList<panier> getPaniers() {
        List<panier> Panier = new ArrayList<>();

        try {
            Panier = pa.afficheParId(Statics.currentIndividu.getIdIndividu());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        paniers.clear();
        paniers.addAll(Panier);
        //System.out.println(Panier[panier].);
        return paniers;
        

    }
    
  
     @FXML
    void ClearCart(ActionEvent event) throws SQLException, IOException {
         for (int i = 0; i < paniers.size(); i++) {
             ps.deletePanier(paniers.get(i).getIdPanier());
             
         }
         switchSceneCart( event);

    }
    
     @FXML
    void goToStore(ActionEvent event) throws IOException {
        goback(event);

    }
    
      @FXML
    void goback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/BoutiqueFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
      @FXML
    void switchSceneCart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/PanierFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
   
    
    
     private void displayPaniers(ObservableList<panier> paniers) {
        grid.getChildren().clear();
        int column = 0;
        int row = 1;

        try {
         
            for (int i = 0; i < paniers.size(); i++) {
               
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("PanierItemFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PanierItemFXMLController itemController = fxmlLoader.getController();
              
                    itemController.setData(paniers.get(i));
                    
                    //itemController.plusQuantity(sommeprod.setText( Float.toString (Float.parseFloat(sommeprod.getText()) ) + 1 ));
                    //System.out.println(paniers.get(i).getIdPanier());
                    
                    //sommeprod.setText (Integer.toString(Integer.parseInt(itemController.ps.afficherPanier().) ;
                   
                if (column == 1) { 
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_PREF_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_PREF_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
                
                //sommeprod.setText( (Float.parseFloat(paniers.get(i).getClass().getPrix().getText()) )  +    );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayPaniers(getPaniers());
        
         /* FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("PanierItemFXML.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(PanierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

                PanierItemFXMLController itemController = fxmlLoader.getController();
                
       
        
         for (int i = 0; i < paniers.size(); i++) {
              String a = itemController.TotItemPrice.getText();
             System.out.println(a);
             sommeprod.setText (Float.toString( Float.parseFloat(a)) + ( Float.parseFloat(sommeprod.getText())))  ;
         }
         }*/
        
         
        // TODO
    }    
    

}