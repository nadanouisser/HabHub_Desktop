/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import HabHub.DogItemListener;
import entities.Chien;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import services.LikesService;
import static utils.Statics.imageDirectory;

/**
 * FXML Controller class
 *
 * @author Ed
 */
public class DogBigCardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView dogImage;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ageLabel;
    @FXML
    private Label likeNumberLabel;

    private DogItemListener dogItemListener;
    private Chien chien;
      LikesService ls = new LikesService(); 
    @FXML
    private void click(MouseEvent mouseEvent) {
        dogItemListener.onClickListener(chien);
    }

    public void setData(Chien chien, DogItemListener dogItemListener) throws SQLException, IOException {
        this.chien = chien;
        this.dogItemListener = dogItemListener;
        nameLabel.setText(chien.getNom() + ",");
        ageLabel.setText(chien.getAge());
          File sourceimage = new File(imageDirectory+chien.getImage());
                    Image image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
                    Image dogImg=image;
        dogImage.setImage(dogImg);
        likeNumberLabel.setText(Integer.toString(ls.getLikeByDogId(chien.getIdChien())));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
