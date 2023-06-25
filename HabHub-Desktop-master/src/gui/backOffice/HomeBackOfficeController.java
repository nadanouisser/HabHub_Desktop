/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.backOffice;

import entities.Business;
import entities.ServiceBusiness;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.util.converter.IntegerStringConverter;
import services.ServiceBusinessServices;
import services.UserBusinessServices;

/**
 * FXML Controller class
 *
 * @author User
 */
public class HomeBackOfficeController implements Initializable {

    @FXML
    private TextField searchBox1;
    @FXML
    private Button refreshButton;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Business> GroomingTableView;
    @FXML
    private TableColumn<Business, Integer> GIdCol;
    @FXML
    private TableColumn<Business, String> GBusinessCol;
    @FXML
    private TableColumn<Business, String> GEmailCol;
    @FXML
    private TableColumn<Business, Integer> GPhoneCol;
    @FXML
    private TableColumn<Business, String> GDescriptionCol;
    @FXML
    private TableColumn<Business, String> GLocationCol;
    @FXML
    private TableColumn<Business, String> GCityCol;
    @FXML
    private TableColumn<Business, String> GHoursCol;
    @FXML
    private TextField searchBox11;
    @FXML
    private Button refreshButton1;
    @FXML
    private Button addButton1;
    @FXML
    private Button editButton1;
    @FXML
    private Button deleteButton1;
    @FXML
    private TableView<Business> ParkTableView;
    @FXML
    private TableColumn<Business, Integer> PIdCol;
    @FXML
    private TableColumn<Business, String> PBusinessNameCol;
    @FXML
    private TableColumn<Business, String> PEmailCol;
    @FXML
    private TableColumn<Business, Integer> PPhoneCol;
    @FXML
    private TableColumn<Business, String> PDescriptionCol;
    @FXML
    private TableColumn<Business, String> PLocationCol;
    @FXML
    private TableColumn<Business, String> PCityCol;
    @FXML
    private TableColumn<Business, String> PHoursCol;
    @FXML
    private TextField searchBox12;
    @FXML
    private Button refreshButton2;
    @FXML
    private Button addButton2;
    @FXML
    private Button editButton2;
    @FXML
    private Button deleteButton2;
    @FXML
    private TableView<Business> VetTableView;
    @FXML
    private TableColumn<Business, Integer> VIdCol;
    @FXML
    private TableColumn<Business, String> VBusinessNameCol;
    @FXML
    private TableColumn<Business, String> VEmailCol;
    @FXML
    private TableColumn<Business, Integer> VPhoneCol;
    @FXML
    private TableColumn<Business, String> VDescriptionCol;
    @FXML
    private TableColumn<Business, String> VlocationCol;
    @FXML
    private TableColumn<Business, String> VCity;
    @FXML
    private TableColumn<Business, String> VHoursCol;
    @FXML
    private TextField searchBox13;
    @FXML
    private Button refreshButton3;
    @FXML
    private Button addButton3;
    @FXML
    private Button editButton3;
    @FXML
    private Button editVetButton;
    @FXML
    private Button addVetButton;
    @FXML
    private Button addServiceButton;
     @FXML
    private Button editServiceButton;
    
    @FXML
    private Button deleteButton3;
    @FXML
    private TableView<Business> DogSittingTableView;
    @FXML
    private TableColumn<Business, Integer> DIdCol;
    @FXML
    private TableColumn<Business, String> DBusinessNameCol;
    @FXML
    private TableColumn<Business, String> DEmailCol;
    @FXML
    private TableColumn<Business, Integer> DphoneCol;
    @FXML
    private TableColumn<Business, String> DDescriptionCol;
    @FXML
    private TableColumn<Business, String> DLocationCol;
    @FXML
    private TableColumn<Business, String> DCityCol;
    @FXML
    private TableColumn<Business, String> DHoursCol;
    @FXML
    private TextField searchBox131;
    @FXML
    private Button refreshButton31;
    @FXML
    private Button addButton31;
    @FXML
    private Button editButton31;
    @FXML
    private Button deleteButton31;
    @FXML
    private TableView<ServiceBusiness> ServicesTableView;
    @FXML
    private TableColumn<ServiceBusiness, Integer> IdServicesCol;
    @FXML
    private TableColumn<ServiceBusiness, String> BusinessNameCol;
    @FXML
    private TableColumn<ServiceBusiness, String> ServiceNameCol;
    @FXML
    private TableColumn<ServiceBusiness, String> BusinessEmailCol;
    @FXML
    private TableColumn<ServiceBusiness, Integer> ServicePhoneCol;
    @FXML
    private TableColumn<ServiceBusiness, Float> PriceCol;
    @FXML
    private TextField searchBoxGrooming;
    @FXML
    private TextField searchBoxParks;
   @FXML
    private TextField searchBoxVet;
    @FXML
    private TextField searchBoxServices;
    @FXML
    private TextField searchBoxDogSitting;

    
    
    
    private Business grooming ;
    private Business parks ;
    private Business vet ;
    private Business dogSitting ;
    private ServiceBusiness service ;
    
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


     public ObservableList<Business> groomingData = FXCollections.observableArrayList();
     public ObservableList<Business> parksData = FXCollections.observableArrayList();
     public ObservableList<Business> vetData = FXCollections.observableArrayList();
     public ObservableList<Business> dogSittingData = FXCollections.observableArrayList();
     public ObservableList<ServiceBusiness> servicesData = FXCollections.observableArrayList();
     ServiceBusinessServices sbs = new ServiceBusinessServices(); 
     UserBusinessServices ubs = new UserBusinessServices();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadGroomingData();
        loadParkData();
        loadVetData();
        loadDogSittingData();
        loadServicesData();
        
        searchBoxGrooming.textProperty().addListener((observable, oldValue, newValue) -> {
             groomingData.clear();
            try {
                groomingData.addAll(ubs.rechercherBusinessBack(newValue,"grooming"));
                System.out.println(ubs.rechercherBusinessBack(newValue,"grooming"));
                        System.out.println(groomingData);
                                 System.out.println(newValue);
                
            } catch (SQLException ex) {
                Logger.getLogger(HomeBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println("testgrooming");
        GroomingTableView.setItems(groomingData);

        });
        searchBoxVet.textProperty().addListener((observable, oldValue, newValue) -> {
             vetData.clear();
            try {
                vetData.addAll(ubs.rechercherBusinessBack("vet",newValue));
            } catch (SQLException ex) {
                Logger.getLogger(HomeBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println("testgrooming");
        VetTableView.setItems(vetData);
        });

        searchBoxParks.textProperty().addListener((observable, oldValue, newValue) -> {
             parksData.clear();
            try {
                parksData.addAll(ubs.rechercherBusinessBack("park",newValue));
            } catch (SQLException ex) {
                Logger.getLogger(HomeBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        ParkTableView.setItems(parksData);
        });
        
        searchBoxDogSitting.textProperty().addListener((observable, oldValue, newValue) -> {
             dogSittingData.clear();
            try {
                dogSittingData.addAll(ubs.rechercherBusinessBack("dogsitting",newValue));
            } catch (SQLException ex) {
                Logger.getLogger(HomeBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        DogSittingTableView.setItems(dogSittingData);
        });
        
        searchBoxServices.textProperty().addListener((observable, oldValue, newValue) -> {
             servicesData.clear();
            try {
                servicesData.addAll(sbs.rechercherServicesBack(newValue));
            } catch (SQLException ex) {
                Logger.getLogger(HomeBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        ServicesTableView.setItems(servicesData);
        });
       
    }    
    
/***********************************************/
    @FXML
    private void refreshGroomingTable() throws SQLException {
        groomingData.clear();
        groomingData.addAll(ubs.afficherBusinessJoinUser("grooming"));
        GroomingTableView.setItems(groomingData);
    }
    @FXML
    void deleteGrooming(MouseEvent event) throws SQLException {
        grooming = GroomingTableView.getSelectionModel().getSelectedItem();
        ubs.delete(grooming.getIdBusiness());
        refreshGroomingTable();
    }
    private void loadGroomingData() {
        try {
            refreshGroomingTable();
        } catch (SQLException ex) {
            Logger.getLogger(HomeBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GIdCol.setCellValueFactory(new PropertyValueFactory<>("idBusiness"));
        GBusinessCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        GEmailCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUser().getEmail())));
        GDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        GLocationCol.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        GCityCol.setCellValueFactory(new PropertyValueFactory<>("ville"));
        GHoursCol.setCellValueFactory(new PropertyValueFactory<>("horaire"));
       
    }
/***********************************************/

    @FXML
    private void refreshParksTable() throws SQLException {
        parksData.clear();
        parksData.addAll(ubs.afficherBusinessJoinUser("park"));
        ParkTableView.setItems(parksData);
    }
       @FXML
    void deletePark(MouseEvent event) throws SQLException {
        parks = VetTableView.getSelectionModel().getSelectedItem();
        ubs.delete(parks.getIdBusiness());
        refreshParksTable();
    }
      private void loadParkData() {
        try {
            refreshParksTable();
        } catch (SQLException ex) {
            Logger.getLogger(HomeBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        PIdCol.setCellValueFactory(new PropertyValueFactory<>("idBusiness"));
        PBusinessNameCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        PEmailCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUser().getEmail())));
        PDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        PLocationCol.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        PCityCol.setCellValueFactory(new PropertyValueFactory<>("ville"));
        PHoursCol.setCellValueFactory(new PropertyValueFactory<>("horaire"));
       
    }

    /*********************************************/

    
    @FXML
    private void refreshVeterinaryTable() throws SQLException {
        vetData.clear();
        vetData.addAll(ubs.afficherBusinessJoinUser("vet"));
        VetTableView.setItems(vetData);
    }

    @FXML
    void deleteVet(MouseEvent event) throws SQLException {
    vet = VetTableView.getSelectionModel().getSelectedItem();
        ubs.delete(vet.getIdBusiness());
        refreshVeterinaryTable();
    }
    
  @FXML
    private void getVetAddView(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("BusinessPopUp.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   @FXML
    void getVetEditView(MouseEvent event) {
        if (VetTableView.getSelectionModel().getSelectedItem()!=null)
        {
        vet = VetTableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("BusinessPopUp.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(HomeBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            BusinessPopUpController businessPopUpController  = loader.getController();
                            businessPopUpController.setUpdate(true);
                            businessPopUpController.setIdUpdate(vet.getIdBusiness());
                            /*String prenom="";
                            if (chien.getIndividu()!=null){
                                prenom=chien.getIndividu().getPrenom();
                            }*/
                            businessPopUpController.setTextField(vet.getTitre(),  Integer.toString(vet.getUser().getNumTel()),vet.getLocalisation(),
                                    vet.getVille(),vet.getHoraire(),vet.getUser().getEmail(),vet.getDescription());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();

    }}
      private void loadVetData() {
        try {
            refreshVeterinaryTable();
        } catch (SQLException ex) {
            Logger.getLogger(HomeBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        VIdCol.setCellValueFactory(new PropertyValueFactory<>("idBusiness"));
        VBusinessNameCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        VEmailCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUser().getEmail())));
        VDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        VlocationCol.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        VCity.setCellValueFactory(new PropertyValueFactory<>("ville"));
        VHoursCol.setCellValueFactory(new PropertyValueFactory<>("horaire"));
       
    }
    /*********************************************/

    @FXML
    private void refreshDogSittingTable() throws SQLException {
        dogSittingData.clear();
        dogSittingData.addAll(ubs.afficherBusinessJoinUser("dogsitting"));
        DogSittingTableView.setItems(dogSittingData);
        
    }
 @FXML
    void deleteDogSitting(MouseEvent event) throws SQLException {
        dogSitting = DogSittingTableView.getSelectionModel().getSelectedItem();
        ubs.delete(dogSitting.getIdBusiness());
        refreshDogSittingTable();
    }
    
    private void loadDogSittingData() {
        try {
            refreshDogSittingTable();
        } catch (SQLException ex) {
            Logger.getLogger(HomeBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DIdCol.setCellValueFactory(new PropertyValueFactory<>("idBusiness"));
        DBusinessNameCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        DEmailCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUser().getEmail())));
        DDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        DLocationCol.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        DCityCol.setCellValueFactory(new PropertyValueFactory<>("ville"));
        DHoursCol.setCellValueFactory(new PropertyValueFactory<>("horaire"));
       
    }
/*********************************************/
    @FXML
    private void refreshServicesTable() throws SQLException {
        
        servicesData.clear();
        servicesData.addAll(sbs.afficherServicesBack());
        ServicesTableView.setItems(servicesData);
        
    }
     
    @FXML
    void deleteServices(MouseEvent event) throws SQLException {
        service = ServicesTableView.getSelectionModel().getSelectedItem();
        sbs.delete(service.getIdBusinessServices());
        refreshServicesTable();
    }
    private void loadServicesData() {
        try {
            refreshServicesTable();
        } catch (SQLException ex) {
            Logger.getLogger(HomeBackOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        IdServicesCol.setCellValueFactory(new PropertyValueFactory<>("idBusinessServices"));
        BusinessNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getBusiness().getTitre())));
        ServiceNameCol.setCellValueFactory(new PropertyValueFactory<>("nomService"));
        BusinessEmailCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getBusiness().getUser().getEmail())));

        PriceCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
       
    }
}
