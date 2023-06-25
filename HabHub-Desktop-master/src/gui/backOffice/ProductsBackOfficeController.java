/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;


import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ProductsBackOfficeController implements Initializable {

    /**
     * Initializes the controller class.
     */
   

    @FXML
    private Button refreshButton;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Produit> ProductTableView;

    @FXML
    private TableColumn<Produit, Integer> idCol;

    @FXML
    private TableColumn<Produit, String> ProdNameCol;

    @FXML
    private TableColumn<Produit, String> CategorieCol;

    @FXML
    private TableColumn<Produit, String> DescCol;

    @FXML
    private TableColumn<Produit, String> PriceCol;

    @FXML
    private TableColumn<Produit, String> BrandCol;

  
    
    @FXML
    private TextField searchBoxProducts;

     private Stage stage;
    private Scene scene;
    private Parent root;
        @FXML
    void switchBusinessBack(ActionEvent event) throws IOException {
 root = FXMLLoader.load(getClass().getResource("HomeBackOffice.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    void switchDogsBack(ActionEvent event) throws IOException {
 root = FXMLLoader.load(getClass().getResource("chiensBackOffice.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    void switchProductsBack(ActionEvent event) throws IOException {
 root = FXMLLoader.load(getClass().getResource("ProductsBackOffice.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    void switchUsersBack(ActionEvent event) throws IOException {
 root = FXMLLoader.load(getClass().getResource("UsersBackOffice.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    private Produit p;
  

 public ObservableList<Produit> data = FXCollections.observableArrayList();
    ProduitService ps = new ProduitService();
  
/**********************************DOG************************************/
    @FXML
    void refreshProdTable()throws SQLException {
        data.clear();
        data.addAll(ps.afficherproduit());
        System.out.println("refresh Product Table");
        ProductTableView.setItems(data);
     
    }
    
  @FXML
    private void getProductAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("ProductPopUp.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductsBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   @FXML
    void getProductEditView(MouseEvent event) {
        if (ProductTableView.getSelectionModel().getSelectedItem()!=null)
        {
        p = ProductTableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("ProductPopUp.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ProductsBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            ProductPopUpController productPopUpController = loader.getController();
                            productPopUpController.setUpdate(true);
                            productPopUpController.setIdUpdate(p.getIdProduit());
                            /*String prenom="";
                            if (chien.getIndividu()!=null){
                                prenom=chien.getIndividu().getPrenom();
                            }*/
                            productPopUpController.setTextField(p.getNom(),  p.getIdCategorie().getNom(),
                                    p.getDescription(),p.getMarque(),Float.toString(p.getPrix()));
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

    }
    }
    @FXML
    void deleteProd(MouseEvent event) throws SQLException  {

        p = ProductTableView.getSelectionModel().getSelectedItem();
        ps.deleteProduit(p.getIdProduit());
        refreshProdTable();

    }

    

    private void loadProductData() {
        try {
            refreshProdTable();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idCol.setCellValueFactory(new PropertyValueFactory<>("idProduit"));

        ProdNameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
       
        CategorieCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdCategorie().getNom())));
        DescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        BrandCol.setCellValueFactory(new PropertyValueFactory<>("marque"));
       

    }
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadProductData();
      
        //loadAdoptionData();
        
        searchBoxProducts.textProperty().addListener((observable, oldValue, newValue) -> {
             data.clear();
            try {
                data.addAll(ps.RechercheProduit(newValue));
            } catch (SQLException ex) {
                Logger.getLogger(ProductsBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println("testmating");
        ProductTableView.setItems(data);

        });
        
    }

}
