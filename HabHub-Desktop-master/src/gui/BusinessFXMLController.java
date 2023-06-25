    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import HabHub.BusinessListener;
import HabHub.ServiceListener;
import entities.Business;
import entities.Individu;
import entities.Reservation;
import entities.Revue;
import entities.ServiceBusiness;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.controlsfx.control.Rating;
import services.ReservationServices;
import services.RevueServices;
import services.ServiceBusinessServices;
import services.UserBusinessServices;
import utils.Statics;
import static utils.Statics.imageDirectory;

/**
 * FXML Controller class
 *
 * @author Ed
 */
public class BusinessFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField searchBox;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView businessImage;

    @FXML
    private Label businessTitleLabel;

    @FXML
    private Label businessLocationLabel;

    @FXML
    private Text businessDecriptionLabel;

    @FXML
    private Label experienceLabel;

    @FXML
    private Label openingHoursLabel;

    @FXML
    private Rating reviewRating;
    @FXML
    private TextArea reviewText;

    @FXML
    private GridPane gridReview;

    @FXML
    private GridPane gridServices;
    @FXML
    private Button groomingButton;
    @FXML
    private Button parkButton;
    @FXML
    private Button bookButton;
    @FXML
    private Button vetButton;
    @FXML
    private Button dogSittingButton;
        @FXML
    private HBox experienceBox;

    @FXML
    private Label serviceIncludeLabel;

    @FXML
    private ComboBox<String> timeComboBox;
    @FXML
    private DatePicker datePicker;

    @FXML
    private HBox dateBox;
    @FXML
    private HBox timeBox;
    @FXML
    private HBox bookBox;

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
    void maps(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI (chosenBusiness.getLocalisation()));
    }
    
    private Business chosenBusiness;
    private BusinessListener businessListener;
    private ServiceListener serviceListener;

    private List<Integer> selectedServices = new ArrayList();
    public ObservableList<Business> businessItems = FXCollections.observableArrayList();
    UserBusinessServices bs = new UserBusinessServices();
    public ObservableList<Revue> revueItems = FXCollections.observableArrayList();
    RevueServices rs = new RevueServices();
    ReservationServices rvs = new ReservationServices();

    public ObservableList<ServiceBusiness> serviceItems = FXCollections.observableArrayList();
    ServiceBusinessServices sbs = new ServiceBusinessServices();
    
    
    @FXML
    void select(ActionEvent event) {
        String selectedTime = timeComboBox.getSelectionModel().getSelectedItem();
        if(selectedTime.equals("select time")){
            bookButton.setDisable(true);
        }else{
            bookButton.setDisable(false);
        }
    }

    public void refreshReviewItems(){
        revueItems.clear();
        revueItems.addAll(getReviewItems(chosenBusiness.getIdBusiness())); 
        System.out.println("ahla");
        displayReviewsItems(revueItems);
    }
    
    @FXML
    public void displayReviewsItems(ObservableList<Revue> revueItems) {
        gridReview.getChildren().clear();

        int column2 = 0;
        int row2 = 1;
        try {
            for (int j = 0; j < revueItems.size(); j++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("revueBusiness.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                RevueBusinessController revueBusinessController = fxmlLoader.getController();
                revueBusinessController.setData(revueItems.get(j));

                if (column2 == 1) {
                    column2 = 0;
                    row2++;
                }

                gridReview.add(anchorPane, column2++, row2); //(child,column,row)
                //set grid width
                gridReview.setMinWidth(Region.USE_PREF_SIZE);
                gridReview.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridReview.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridReview.setMinHeight(Region.USE_PREF_SIZE);
                gridReview.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridReview.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
       @FXML
      void ajouterReservation(ActionEvent event) throws SQLException {
        System.out.println("mehh");
        String selectedTime = timeComboBox.getSelectionModel().getSelectedItem();
        java.util.Date date = java.util.Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(date);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                System.out.println(sqlDate);

        for(int i = 0; i < selectedServices.size(); i++){
            ServiceBusiness rs = new ServiceBusiness(selectedServices.get(i));
            Reservation r = new Reservation(Statics.currentIndividu,rs,date,selectedTime);
            rvs.ajouter(r);
            System.out.println("ajout");
            
        }
        
        datePicker.setValue(null);
        
               
    }
    @FXML
    void submitRatingButton(ActionEvent event) throws SQLException {

        int starNumber = (int) reviewRating.getRating();
        String reviewCommentaire = reviewText.getText();
        System.out.println(reviewRating.getRating());
        Individu i = Statics.currentIndividu;
        // Individu i = new Individu(1);

        Revue r = new Revue(i, chosenBusiness, starNumber, reviewCommentaire);
        //System.out.println(r);
        try {
            rs.ajouterRevueBusiness(r);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        reviewText.clear();
        revueItems.clear();
        revueItems.addAll(getReviewItems(chosenBusiness.getIdBusiness()));

        displayReviewsItems(revueItems);

    }

    private ObservableList<Business> getRechercheBusiness(String type, String input) {
        List<Business> businessesRecherche = new ArrayList<>();
        try {
            businessesRecherche = bs.rechercherBusinessByType(type, input);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        businessItems.clear();
        businessItems.addAll(businessesRecherche);
        return businessItems;
    }

    private ObservableList<Business> getBusinessItems(String businessType) {
        List<Business> BI = new ArrayList<>();

        try {
            BI = bs.afficherBusiness(businessType);
            System.out.println(BI);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        businessItems.clear();
        businessItems.addAll(BI);
        return businessItems;

    }

    private List<Revue> getReviewItems(int businessId) {
        List<Revue> RI = new ArrayList<>();

        try {
            RI = rs.afficherRevueId(businessId);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return RI;

    }

    private List<ServiceBusiness> getServiceItems(int businessId) {
        List<ServiceBusiness> SI = new ArrayList<>();

        try {
            SI = sbs.afficherServicesById(businessId);
           
            System.out.println(SI);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        serviceItems.clear();
        // serviceItems.addAll(SI);
        return SI;

    }

    private void setChosenBusiness(Business b) {
        chosenBusiness = b;
        revueItems.clear();
        gridReview.getChildren().clear();
        timeComboBox.getSelectionModel().selectFirst();

       // Image businessImg = new Image(getClass().getResourceAsStream("../assets/img/business/dynamic/16.png"));

        File sourceimage = new File(imageDirectory+b.getImage());
                    Image image;
        try {
            image = SwingFXUtils.toFXImage(ImageIO.read(sourceimage), null);
            Image businessImg=image;
            businessImage.setImage(businessImg);
        } catch (IOException ex) {
            Logger.getLogger(BusinessFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        businessTitleLabel.setText(b.getTitre());
        businessLocationLabel.setText(b.getVille());
        businessDecriptionLabel.setText(b.getDescription());
        experienceLabel.setText(Integer.toString(b.getExperience()));
        openingHoursLabel.setText(b.getHoraire());
        revueItems.addAll(getReviewItems(b.getIdBusiness()));
        //System.out.println(revueItems);
        int column2 = 0;
        int row2 = 1;
        try {
            for (int i = 0; i < revueItems.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("revueBusiness.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                RevueBusinessController revueBusinessController = fxmlLoader.getController();
                revueBusinessController.setData(revueItems.get(i));

                if (column2 == 1) {
                    column2 = 0;
                    row2++;
                }

                gridReview.add(anchorPane, column2++, row2); //(child,column,row)
                //set grid width
                gridReview.setMinWidth(Region.USE_PREF_SIZE);
                gridReview.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridReview.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridReview.setMinHeight(Region.USE_PREF_SIZE);
                gridReview.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridReview.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //serviceItems.clear();
        gridServices.getChildren().clear();
        serviceItems.addAll(getServiceItems(b.getIdBusiness()));
        //System.out.println(serviceItems);
        int column3 = 0;
        int row3 = 1;

        try {
            serviceListener = new ServiceListener() {
                @Override
                public void onClickListener(ServiceBusiness sBusiness) {
                     
                    if (selectedServices.contains(sBusiness.getIdBusinessServices()))
                    {
                        
                        selectedServices.remove(new Integer(sBusiness.getIdBusinessServices()));

                    } else {
                        selectedServices.add(sBusiness.getIdBusinessServices());
                    }
                    System.out.println(selectedServices);
                }
            };
            if(serviceItems.isEmpty()){
                serviceIncludeLabel.setVisible(false);
            }else{
                serviceIncludeLabel.setVisible(true);
            }
            for (int i = 0; i < serviceItems.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ServicesFXML.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ServicesFXMLController servicesFXMLController = fxmlLoader.getController();
                servicesFXMLController.setData(serviceItems.get(i), serviceListener);

                if (column3 == 1) {
                    column3 = 0;
                    row3++;
                }

                gridServices.add(anchorPane, column3++, row3); //(child,column,row)
                //set grid width
                gridServices.setMinWidth(Region.USE_PREF_SIZE);
                gridServices.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridServices.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridServices.setMinHeight(Region.USE_PREF_SIZE);
                gridServices.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridServices.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayBusiness(ObservableList<Business> businesses) {

        grid.getChildren().clear();
        int column = 0;
        int row = 1;

        try {
            businessListener = new BusinessListener() {
                @Override
                public void onClickListener(Business business) {
                    setChosenBusiness(business);
                }

            };
            for (int i = 0; i < businesses.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("BusinessItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                BusinessItemController itemController = fxmlLoader.getController();
                itemController.setData(businesses.get(i), businessListener);

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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void displayGrooming(ActionEvent event) {
        experienceBox.setVisible(false);
        dateBox.setVisible(true);
        timeBox.setVisible(true);
        bookBox.setVisible(true);
        displayBusiness(getBusinessItems("grooming"));
        System.out.println("groom");
        if (businessItems.size() > 0) {
            setChosenBusiness(businessItems.get(0));
            businessListener = new BusinessListener() {
                @Override
                public void onClickListener(Business business) {
                    setChosenBusiness(business);

                }
            };
        }
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            displayBusiness(getRechercheBusiness("grooming", newValue));

        });
        searchBox.clear();

    }

    @FXML
    void displayVet(ActionEvent event) {
        experienceBox.setVisible(true);
        dateBox.setVisible(true);
        timeBox.setVisible(true);
        bookBox.setVisible(true);
        displayBusiness(getBusinessItems("vet"));
        System.out.println("vet");
        if (businessItems.size() > 0) {
            setChosenBusiness(businessItems.get(0));
            businessListener = new BusinessListener() {
                @Override
                public void onClickListener(Business business) {
                    setChosenBusiness(business);

                }
            };
        }

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            displayBusiness(getRechercheBusiness("vet", newValue));

        });
        searchBox.clear();

    }

    @FXML
    void displayParks(ActionEvent event) {
        experienceBox.setVisible(false);
        dateBox.setVisible(false);
        timeBox.setVisible(false);
        bookBox.setVisible(false);

        displayBusiness(getBusinessItems("park"));
        System.out.println("park");
        if (businessItems.size() > 0) {
            setChosenBusiness(businessItems.get(0));
            businessListener = new BusinessListener() {
                @Override
                public void onClickListener(Business business) {
                    setChosenBusiness(business);

                }
            };
        }

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            displayBusiness(getRechercheBusiness("park", newValue));

        });
        searchBox.clear();

    }

    @FXML
    void displayDogSitting(ActionEvent event) {
        
        experienceBox.setVisible(false);
        dateBox.setVisible(true);
        timeBox.setVisible(true);
        bookBox.setVisible(true);
        displayBusiness(getBusinessItems("dogSitting"));
        System.out.println("dogSitting");
        if (businessItems.size() > 0) {
            setChosenBusiness(businessItems.get(0));
            businessListener = new BusinessListener() {
                @Override
                public void onClickListener(Business business) {
                    setChosenBusiness(business);

                }
            };
        }
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            displayBusiness(getRechercheBusiness("dogSitting", newValue));

        });
        searchBox.clear();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayBusiness(getBusinessItems("grooming"));
        //businessItems.addAll(getBusinessItems());
        if (businessItems.size() > 0) {
            setChosenBusiness(businessItems.get(0));
            businessListener = new BusinessListener() {
                @Override
                public void onClickListener(Business business) {
                    setChosenBusiness(business);

                }
            };
        }
        ObservableList<String> timeList = FXCollections.observableArrayList("select time","9AM-10AM","10AM-11AM","11AM-12PM","12PM-13PM","13PM-14PM","14PM-15PM","15PM-16PM","16PM-17PM");
        timeComboBox.setItems(timeList);
        timeComboBox.getSelectionModel().selectFirst();
        

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {

            System.out.println(newValue);
            displayBusiness(getRechercheBusiness("grooming", newValue));

        });
    }

}
