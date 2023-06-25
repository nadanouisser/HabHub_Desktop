/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import HabHub.BusinessListener;
import entities.Business;
import entities.Revue;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.RevueServices;
import utils.Statics;

/**
 * FXML Controller class
 *
 * @author User
 */
public class RevueBusinessController implements Initializable {

    @FXML
    private ImageView UserImage;

    @FXML
    private Label idIndividuLabel;

    @FXML
    private ImageView starReviewsImage;

    @FXML
    private Text commentaireText;

    @FXML
    private Label datePubLabel;
    @FXML
    private Button deleteButton;

    private Revue revue;
    RevueServices rs = new RevueServices();
     private Stage stage;
 private Scene scene;
 private Parent parent;
 /* void switchSceneProfile(ActionEvent event)throws IOException {
//("../gui/backOffice/HomeBackOffice.fxml
    Parent root = FXMLLoader.load(getClass().getResource("../gui/BusinessFXML.fxml"));

    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    } */

    @FXML
    void deleteReview(ActionEvent event) throws SQLException, IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader();
        rs.deleteRevue(revue.getIdRevue());
        //switchSceneProfile(event);
        /*fxmlLoader.setLocation(getClass().getResource("revueBusiness.fxml"));
        //AnchorPane anchorPane = fxmlLoader.load();

        BusinessFXMLController businessFXMLController = fxmlLoader.getController();
        businessFXMLController.refreshReviewItems();
*/
    }
    
    public void setData(Revue revue) {
        this.revue = revue;

        if (revue.getIndividu().getIdIndividu() == Statics.currentIndividu.getIdIndividu()) {
            deleteButton.setVisible(true);
            deleteButton.setDisable(false);

        }

        idIndividuLabel.setText(revue.getIndividu().getPrenom());
        commentaireText.setText(revue.getCommentaire());
        datePubLabel.setText(revue.getDatePublication().toString());
        Image bImage = new Image(getClass().getResourceAsStream("../assets/img/business/BusinessItem/vetImage.png"));
        UserImage.setImage(bImage);
        Image starImg = new Image(getClass().getResourceAsStream("../assets/img/revue/0.png"));
        if (revue.getNbEtoiles() == 1) {
            starImg = new Image(getClass().getResourceAsStream("../assets/img/revue/1.png"));
        } else if (revue.getNbEtoiles() == 2) {
            starImg = new Image(getClass().getResourceAsStream("../assets/img/revue/2.png"));
        } else if (revue.getNbEtoiles() == 3) {
            starImg = new Image(getClass().getResourceAsStream("../assets/img/revue/3.png"));
        } else if (revue.getNbEtoiles() == 4) {
            starImg = new Image(getClass().getResourceAsStream("../assets/img/revue/4.png"));
        } else if (revue.getNbEtoiles() == 5) {
            starImg = new Image(getClass().getResourceAsStream("../assets/img/revue/5.png"));
        }
        starReviewsImage.setImage(starImg);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
