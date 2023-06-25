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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import services.PanierService;
import services.ProduitService;
import utils.MyDB;
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
 * @author Ed
 */
public class BoutiqueFXMLController implements Initializable {
    
    private Produit chosenProduit;

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

    @FXML
    private TextField searchBox;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView ProdImage;

    @FXML
    private Label nameLabel;

    @FXML
    private Label Price;
    
    
    @FXML
    private Label unit;

    @FXML
    private ImageView genderImage;

    @FXML
    private Label Desc;

    @FXML
    private Label addReview;

    @FXML
    private GridPane gridReview;

    @FXML
    private Button reduce;

    @FXML
    private Label quantity;

    @FXML
    private Button add;

    @FXML
    private Button addtocart;

    @FXML
    private Label totprice;

    public ObservableList<Produit> produits = FXCollections.observableArrayList();
    ProduitService sa = new ProduitService();

    private ObservableList<Produit> getProduits() {
        List<Produit> Produit = new ArrayList<>();

        try {
            Produit = sa.afficherproduit();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        produits.clear();
        produits.addAll(Produit);
        return produits;

    }

    private ObservableList<Produit> getRechercheProduits(String input) {
        List<Produit> ProduitRecherche = new ArrayList<>();

        try {
            ProduitRecherche = sa.RechercheProduit(input);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        produits.clear();
        produits.addAll(ProduitRecherche);
        return produits;

    }

    @FXML
    void switchSceneCart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/PanierFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void increaseQuantity() {
        quantity.setText(Integer.toString(Integer.parseInt(quantity.getText()) + 1));
        totprice.setText(Float.toString(Float.parseFloat(totprice.getText()) + (Float.parseFloat(Price.getText()))));

    }

    public void decreaseQuantity() {
        if (Integer.parseInt(quantity.getText()) > 1) {
            quantity.setText(Integer.toString(Integer.parseInt(quantity.getText()) - 1));
            totprice.setText(Float.toString(Float.parseFloat(totprice.getText()) - (Float.parseFloat(Price.getText()))));
        }

    }
    
    
   
    @FXML
    void insertPanier(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            
        String req = "INSERT INTO `panier` (  idProduit, idUtilisateur,quantite)  "
                + "VALUES ( ?, ?, ?) ";
        PanierService ps = new PanierService();
        panier pa = new panier(chosenProduit, Statics.currentIndividu.getUtilisateur().getIdUtilisateur() , (Integer.parseInt(quantity.getText()))   );
         ps.ajouterPa(pa );
        alert.setContentText("Product Added To Cart");
         alert.show();
        
    }
    
    
   
    
    

    private void setChosenProduit(Produit p) {
        this.chosenProduit = p;
        quantity.setText("1");

       File sourceimage = new File(imageDirectory+p.getImage());
                    Image image;
        try {
            image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
            Image PImg=image;
            ProdImage.setImage(PImg);
        } catch (IOException ex) {
            Logger.getLogger(BoutiqueFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        nameLabel.setText(p.getNom());
        String s = Float.toString(p.getPrix());
        Price.setText(s);
        Desc.setText(p.getDescription());

        totprice.setText(Price.getText());
       
    }

    /*chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
                "    -fx-background-radius: 30;");*/

    private void displayProduits(ObservableList<Produit> produits) {
        grid.getChildren().clear();
        int column = 0;
        int row = 1;

        try {
            Listen = new Listen() {
                @Override
                public void onClickListener(Produit produit) {
                    setChosenProduit(produit);
                }

            };
            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProduitFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ProduitFXMLController produitController = fxmlLoader.getController();
                produitController.setData(produits.get(i), Listen);

                if (column == 6) {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void RechercheProduit(ActionEvent event) {

    }

    private Listen Listen;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        displayProduits(getProduits());
        if (produits.size() > 0) {
            setChosenProduit(produits.get(0));
            Listen = new Listen() {
                @Override
                public void onClickListener(Produit produit) {
                    setChosenProduit(produit);
                }

            };
        }

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            displayProduits(getRechercheProduits(newValue));

        });
    }

}
