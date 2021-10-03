/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Add Product Controller
 * Products can be created and added to Inventory.
 * 
 * @author Aaron Kephart
 * Course:  C482
 * Student ID:  000944803
 */
public class AddProductScreen implements Initializable {
    //Define scene and stage variables
    Stage stage;
    Parent scene;
    
    //Define text fields / labels / tables
    @FXML
    private TextField txtFieldProductName;

    @FXML
    private TextField txtFieldInventory;

    @FXML
    private TextField txtFieldCost;

    @FXML
    private TextField txtFieldID;

    @FXML
    private TextField txtFieldMax;

    @FXML
    private TextField txtFieldMin;

    @FXML
    private TextField txtFieldSearch;

    @FXML
    private TableView<Part> tblViewAvailableParts;

    @FXML
    private TableColumn<Part, Integer> tblAvailablePartsPartID;

    @FXML
    private TableColumn<Part, String> tblAvailablePartsPartName;

    @FXML
    private TableColumn<Part, String> tblAvailablePartsInventory;

    @FXML
    private TableColumn<Part, Double> tblAvailablePartsCost;

    @FXML
    private TableView<Part> tblViewAddedParts;

    @FXML
    private TableColumn<Part, Integer> tblViewAddedPartID;

    @FXML
    private TableColumn<Part, String> tblViewAddedPartName;

    @FXML
    private TableColumn<Part, Integer> tblViewAddedInventory;

    @FXML
    private TableColumn<Part, Double> tblViewAddedCost;

    /**
     * Add part from available parts to added parts table view
     * @param event add button is pressed
     */
    @FXML
    void onActionAddPart(ActionEvent event) {
        tblViewAddedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblViewAddedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblViewAddedInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblViewAddedCost.setCellValueFactory(new PropertyValueFactory<>("price"));
       
        Part selectedPart = tblViewAvailableParts.getSelectionModel().getSelectedItem();
        tblViewAddedParts.getItems().add(selectedPart);
        tblViewAddedParts.refresh();
        
    }
/**
 * Delete part from the added parts table view
 * @param event delete button is pressed
 */
    @FXML
    void onActionDeleteAddedPart(ActionEvent event) {
        tblViewAddedParts.getItems().remove(tblViewAddedParts.getSelectionModel().getSelectedItem());
        tblViewAddedParts.refresh();
    }
/**
 * Display main menu
 * @param event cancel button is pressed
 * @throws IOException 
 */
    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
/**
 * Construct a new product with all parameters retrieved from text fields
 * @param event save button is pressed
 * @throws IOException 
 */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        //retrieve parameters from input data and added parts table
        ObservableList<Part> associatedParts = tblViewAddedParts.getItems();
        int id = Integer.parseInt(txtFieldID.getText());
        String name = txtFieldProductName.getText();
        double price = Double.parseDouble(txtFieldCost.getText());
        int stock = Integer.parseInt(txtFieldInventory.getText());
        int min = Integer.parseInt(txtFieldMin.getText());
        int max = Integer.parseInt(txtFieldMax.getText());

        /**
         * Error Checking
         * Prevent the maximum field from having a value below the minimum field
         * Ensure that the price of a product cannot be less than the cost of the parts
         */
        double sumAddedPartCost = 0;
        for (int i = 0; i < associatedParts.size(); i++) {
            sumAddedPartCost += associatedParts.get(i).getPrice();
        }
        if (sumAddedPartCost > price) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Cost Comparison");
            errorAlert.setContentText("The sum of parts cost cannot exceed the product cost.  Please correct and save again.");
            errorAlert.showAndWait();
        }
        else if (max <= min) {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Max/Min Values");
            errorAlert.setContentText("Please set the minimum less than the maximum.  Please correct and save again.");
            errorAlert.showAndWait();
        } else {
            Product newProduct = new Product(id, name, price, stock, min, max, associatedParts);
            Inventory.addProduct(newProduct);

            Parent MainParent = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            Scene MainScene = new Scene(MainParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(MainScene);
            MainScene.getRoot().requestFocus();
            window.show();
        }
    }
/**
 * Search all available parts and return the results in the table.
 * @param event search button is pressed
 */
    @FXML
    void onActionSearch(ActionEvent event) {
            tblViewAvailableParts.setItems(Inventory.lookupPart(txtFieldSearch.getText()));
            tblViewAvailableParts.refresh();
    }

    /**
     * Initializes the controller class and define available parts table.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFieldID.setText(String.valueOf(Inventory.getAllProducts().size() + 1));
        tblViewAvailableParts.setItems(Inventory.getAllParts());
        tblAvailablePartsPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblAvailablePartsPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblAvailablePartsInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tblAvailablePartsCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
    }    

}
