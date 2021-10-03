/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Add Part Controller 
 * Parts can be created and added to Inventory.
 * 
 * @author Aaron Kephart
 * Course:  C482
 * Student ID:  000944803
 */
public class AddPartController implements Initializable {
    
    //define scene/stage controllers for window changes.
    Parent scene;
    Stage stage;
    
    //definitions for text fields / labels / radio buttons
    @FXML
    private TextField txtPartName;
    @FXML
    private TextField txtInv;
    @FXML
    private TextField txtPrice;
    @FXML
    private Label lblCompNameOrMachID;
    @FXML
    private TextField txtlblID;
    @FXML
    private TextField txtCompanyNameOrMachineID;
    @FXML
    private TextField txtMax;
    @FXML
    private TextField txtMin;
    @FXML
    private RadioButton radioInHouse;
    @FXML
    private ToggleGroup type;
    @FXML
    private RadioButton radioOutsourced;

    /**
     * Initializes the controller class setting the initial id to 
     * the size of the array plus one.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int id = Inventory.getAllParts().size() + 1;
        txtlblID.setText(String.valueOf(id));
        
    }    

    /**
     * Update label and prompt text to Company Name on event parameter
     * @param event on radio button change to 'Outsourced' part
     */
    @FXML
    void onActionRadioOutsourced(ActionEvent event) {
            lblCompNameOrMachID.setText("Company Name");
            txtCompanyNameOrMachineID.setPromptText("Company Name");
    }

    /**
     * Update label and prompt text to Machine ID on event parameter
     * @param event on radio button change to 'In-House' part.
     */
   @FXML
    void onActionRadioInHouse(ActionEvent event) {
       lblCompNameOrMachID.setText("Machine ID");
       txtCompanyNameOrMachineID.setPromptText("MachineID");
    }
    
    /**
     * Save button press, where the part object is created and added
     * to Inventory
     * @param event on save button click
     * @throws IOException 
     */
    @FXML
    private void onActionSave(ActionEvent event) throws IOException {
        //get text from all fields
        int id = Integer.parseInt(txtlblID.getText());
        String name = txtPartName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int stock = Integer.parseInt(txtInv.getText());
        int min = Integer.parseInt(txtMin.getText());
        int max = Integer.parseInt(txtMax.getText());
        String companyName = txtCompanyNameOrMachineID.getText();
        
        //prevent max field from having a value below the minimum field
        if (max <= min) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Max/Min Values");
            errorAlert.setContentText("Please set the minimum less than the maximum.  Please correct and save again.");
            errorAlert.showAndWait();
        } 
        else {
        /**
         * Determine if part is in house or outsourced.
         * Dependent on selection, create new in-house or outsourced part.
         */
        if(radioOutsourced.isSelected()) {
            Outsourced outsourcedPart = new Outsourced(id,name,price,stock,min,max,companyName);
            Inventory.addPart(outsourcedPart);
        }
        else if (radioInHouse.isSelected()) {
            InHouse inHousePart = new InHouse(id,name,price,stock,min,max,Integer.parseInt(companyName));
            Inventory.addPart(inHousePart);
        }
        //display main screen
        Parent MainParent = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        Scene MainScene = new Scene(MainParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(MainScene);
        MainScene.getRoot().requestFocus();
        window.show();
        }
    }

    /**
     * On cancel button press, display main screen.
     * @param event cancel button press
     * @throws IOException 
     */
    @FXML
    private void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
}
