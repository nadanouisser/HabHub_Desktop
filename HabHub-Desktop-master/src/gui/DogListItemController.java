/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import HabHub.CommunityListener;
import HabHub.DogItemListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import services.AnnonceProprietaireChienService;
import entities.AnnonceProprietaireChien;
import entities.Chien;
import java.sql.SQLException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import services.LikesService;
import utils.Statics;




/**
 * FXML Controller class
 *
 * @author Ed
 */
public class DogListItemController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button likeButton;
    @FXML
    private Button playWithMeButton;

    @FXML
    private ImageView dogImage;
    
    @FXML
    private ImageView likeImage;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private ImageView genderIcon;

    @FXML
    private Label locationLabel;
    
    private boolean clickedPlayWithMeButton=false;
    private boolean clickedLikeButton=false;
    Image liked = new Image(getClass().getResourceAsStream("../assets/img/chien/heartIcon.png"));
    Image notLiked = new Image(getClass().getResourceAsStream("../assets/img/chien/emptyHeartIcon.png"));

    LikesService ls = new LikesService(); 
   

    @FXML
    void likeButtonHandler(ActionEvent event) {
   
        clickedLikeButton = !clickedLikeButton; 
       if (clickedLikeButton) {
            likeImage.setImage(liked);
            ls .ajouterLike(Statics.currentIndividu.getIdIndividu(), chien.getIdChien());
        } else {
            likeImage.setImage(notLiked);
            ls .supprimerLike(Statics.currentIndividu.getIdIndividu(), chien.getIdChien());
        }


    }
    
    


    

  private DogItemListener dogItemListener;
    private Chien chien;

    @FXML
    private void click(MouseEvent mouseEvent) {
        dogItemListener.onClickListener(chien);
    }
  
  
   
   

    public void setData(Chien chien,DogItemListener dogItemListener) throws SQLException {
        this.chien = chien;
        this.dogItemListener=dogItemListener;
        if (chien.isPlayWithMe()){
            playWithMeButton.setVisible(true);
        }
        if (ls.checkLike(Statics.currentIndividu.getIdIndividu(), chien.getIdChien()))
        {   clickedLikeButton=true;
            likeImage.setImage(liked);
        }
        nameLabel.setText(chien.getNom()+",");
        ageLabel.setText(chien.getAge());
        Image dogImg = new Image(getClass().getResourceAsStream("../assets/img/chien/"+chien.getImage()));
        dogImage.setImage(dogImg);
        Image genderImg = new Image(getClass().getResourceAsStream("../assets/img/female.png"));
       
        if ("M".equals(chien.getSexe())) {
            genderImg = new Image(getClass().getResourceAsStream("../assets/img/male.png"));
            
        }
        genderIcon.setImage(genderImg);
        locationLabel.setText(chien.getIndividu().getAdresse());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
