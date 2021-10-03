package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Main screen controller for the Inventory System, where 
 * parts and products are listed in separate tables
 * and can be added, modified, or deleted.
 * 
 * @author Aaron Kephart
 * Course:  C482
 * Student ID:  000944803
 */
public class MainScreenController implements Initializable {
    //declare part and product objects to pass to other screen controllers.
    private Part part;
    private Product product;

    /**
     * Initializes the controller class.
     */
    Stage stage;
    Parent scene;
    
    //defining text fields / tables / columns
    @FXML
    private TextField txtfieldSearchParts;

    @FXML
    private TableView<Part> tblViewParts;

    @FXML
    private TableColumn<Part, Integer> tblPartsPartID;

    @FXML
    private TableColumn<Part, String> tblPartsPartName;

    @FXML
    private TableColumn<Part, Integer> tblPartsInventoryLvl;

    @FXML
    private TableColumn<Part, Double> tblPartsPricePerUnit;

    @FXML
    private TextField txtfieldSearchProducts;

    @FXML
    private TableView<Product> tblViewProducts;

    @FXML
    private TableColumn<Product, Integer> tblProductsProductID;

    @FXML
    private TableColumn<Product, String> tblProductsProductName;

    @FXML
    private TableColumn<Product, Integer> tblProductsInventoryLvl;

    @FXML
    private TableColumn<Product, Double> tblProductsPricePerUnit;

    /**
     * @param event Navigate to 'Add Part' screen.
     * @throws IOException 
     */
    @FXML
    void onActionAddParts(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * @param event Navigate to 'Add Products' screen.
     * @throws IOException 
     */
    @FXML
    void onActionAddProducts(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/AddProductScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    /**
     * @param event Delete part from inventory.
     */
    @FXML
    void onActionDeleteParts(ActionEvent event) {
        Inventory.deletePart(tblViewParts.getSelectionModel().getSelectedItem());
    }
    
     /**
     * @param event Delete product from inventory.
     */
    @FXML
    void onActionDeleteProducts (ActionEvent event) throws IOException {
        Inventory.deleteProduct(tblViewProducts.getSelectionModel().getSelectedItem());
    }

    /**
     * Pass the selected part into the 'Modify Part' screen for modification.
     * @param event modify button pressed under part screen.
     * @throws IOException 
     */
    @FXML
    void onActionModifyParts(ActionEvent event) throws IOException {
        part = tblViewParts.getSelectionModel().getSelectedItem();
        ModifyPartScreen.setModifyPart(part);
       
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/ModifyPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
     /**
     * Pass the selected product into the 'Modify Product' screen for modification.
     * @param event modify button pressed under product screen.
     * @throws IOException 
     */
    @FXML
    void onActionModifyProducts(ActionEvent event) throws IOException {
        product = tblViewProducts.getSelectionModel().getSelectedItem();
        ModifyProductScreen.setModifyProduct(product);
        
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/ModifyProductScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * Search for parts in Inventory, return results to parts table.
     * @param event search button is pressed above the parts table.
     */
    @FXML
    void onActionSearchParts(ActionEvent event) {
        tblViewParts.setItems(Inventory.lookupPart(txtfieldSearchParts.getText()));
        tblViewParts.refresh();

    }
    /**
     * Search for products in Inventory, return results to products table.
     * @param event search button is pressed above the product table.
     */
    @FXML
    void onActionSearchProducts(ActionEvent event) {
        tblViewProducts.setItems(Inventory.lookupProduct(txtfieldSearchProducts.getText()));
        tblViewProducts.refresh();
    }
    /**
     * Exit the application
     * @param event exit button is pressed
     */
      @FXML
    void onActionExitApplication(ActionEvent event) {
        System.exit(0);
    }
    
    /**
     * Initialize program with table definitions.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblViewParts.setItems(Inventory.getAllParts());
        tblPartsPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblPartsPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblPartsInventoryLvl.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblPartsPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        tblViewProducts.setItems(Inventory.getAllProducts());
        tblProductsProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblProductsProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblProductsInventoryLvl.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblProductsPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
    }    

    
}
