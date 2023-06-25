/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import entities.Categorie;
import entities.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Ed
 */
public class ProductPopUpController implements Initializable {

      @FXML
    private TextField ProdName;

    @FXML
    private TextField CategName;

    @FXML
    private TextField PriceProd;

    @FXML
    private TextField BrandProd;

    @FXML
    private TextArea DescProd;

    @FXML
    private Button CancelButton;

    @FXML
    private Button SubmitButton;

    private boolean update;
    private int idUpdate;
    ProduitService ps = new ProduitService();
    @FXML
    void submit(MouseEvent event) throws SQLException {
        Produit p = new Produit(ProdName.getText(),
        Float.parseFloat(PriceProd.getText()),
        BrandProd.getText(),
        DescProd.getText()
                );
       
        if (update) {
            ps.updateProduit (idUpdate, p);
        }
        else{
        
        ps.ajouterp(p);
        
        }
        clean();
       
                 

    }

    @FXML
    private void clean() {
        ProdName.setText(null);
        CategName.setText(null);
        PriceProd.setText(null);
        BrandProd.setText(null);
        DescProd.setText(null);
        

    }
    void setTextField(String ProdName, String CategName, String PriceProd, String BrandProd,String DescProd) {

        this.ProdName.setText(ProdName);
        this.CategName.setText(CategName);
        this.PriceProd.setText(PriceProd);
        this.BrandProd.setText(BrandProd);
        this.DescProd.setText(DescProd);
        

    }
void setUpdate(boolean b) {
        this.update = b;

    }
void setIdUpdate(int i) {
        this.idUpdate = i;

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
