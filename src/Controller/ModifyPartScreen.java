/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Outsourced;
import Model.Part;
import Model.InHouse;
import Model.Inventory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
 * Modify Part screen, where a part is passed from the main screen and
 * modified.  Part is then replaced in Inventory.
 * 
 * @author Aaron Kephart
 * Course:  C482
 * Student ID:  000944803
 */
public class ModifyPartScreen implements Initializable {
    //declare scene and stage variables for screen changes, and part from main controller.
    Stage stage;
    Parent scene;
    private static Part modifyPart;
    
    //define text fields / labels
    @FXML
    private TextField txtPartName;

    @FXML
    private TextField txtInv;

    @FXML
    private TextField txtPrice;

    @FXML
    private Label lblCompanyNameOrMachineID;

    @FXML
    private TextField txtFieldID;

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
     * Display main menu view
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
     * If in-house is selected, then set label and prompt text to Machine ID
     * @param event 
     */
    @FXML
    void onActionRadioInHouse(ActionEvent event) {
        lblCompanyNameOrMachineID.setText("Machine ID");
        txtCompanyNameOrMachineID.setPromptText("Machine ID");
           
    }
    /**
     * If Outsourced is selected, then set label and prompt text to Machine ID
     * @param event radio button event change
     */
    @FXML
    void onActionRadioOutsourced(ActionEvent event) {
        lblCompanyNameOrMachineID.setText("Company Name");
        txtCompanyNameOrMachineID.setPromptText("Company Name");
    }
    /**
     * Save new part, and replace existing part.
     * @param event
     * @throws IOException 
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        int id = modifyPart.getId();
        String partName = txtPartName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int stock = Integer.parseInt(txtInv.getText());
        int max = Integer.parseInt(txtMax.getText());
        int min = Integer.parseInt(txtMin.getText());
        
         /**
         * Error Checking
         * Prevent the maximum field from having a value below the minimum field
         */
        if (max <= min) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Max/Min Values");
            errorAlert.setContentText("Please set the minimum less than the maximum.  Please correct and save again.");
            errorAlert.showAndWait();
        } else {
            if (radioOutsourced.isSelected()) {
                String companyName = txtCompanyNameOrMachineID.getText();
                Outsourced outsourcedReplacement = new Outsourced(id, partName, price, stock, min, max, companyName);
                Inventory.deletePart(modifyPart);
                Inventory.addPart(outsourcedReplacement);
            } else {
                int machineID = Integer.parseInt(txtCompanyNameOrMachineID.getText());
                InHouse inHouseReplacement = new InHouse(id, partName, price, stock, min, max, machineID);
                Inventory.deletePart(modifyPart);
                Inventory.addPart(inHouseReplacement);
            }

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFieldID.setText(String.valueOf(modifyPart.getId()));
        txtPartName.setText(modifyPart.getName());
        txtInv.setText(String.valueOf(modifyPart.getStock()));
        txtPrice.setText(String.valueOf(modifyPart.getPrice()));
        txtMax.setText(String.valueOf(modifyPart.getMax()));
        txtMin.setText(String.valueOf(modifyPart.getMin()));

        if (modifyPart instanceof Outsourced) {
            Outsourced outsourcedPart = (Outsourced) modifyPart;
            radioOutsourced.setSelected(true);
            lblCompanyNameOrMachineID.setText("Company Name");
            txtCompanyNameOrMachineID.setText(outsourcedPart.getCompanyName());

        } else if (modifyPart instanceof InHouse) {
            InHouse inHousePart = (InHouse) modifyPart;
            radioInHouse.setSelected(true);
            lblCompanyNameOrMachineID.setText("Machine ID");
            txtCompanyNameOrMachineID.setText(String.valueOf(inHousePart.getMachineId()));
        }

    }
    /**
     * Set modify part from the main controller
     * @param part part passed from the main controller
     */
    public static void setModifyPart(Part part) {
        modifyPart = part;
    }
    
}
