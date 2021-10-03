/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akephartc482_inventory;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class, where the Inventory System begins.
 * 
 * @author Aaron Kephart
 * Course:  C482
 * Student ID:  000944803
 */
public class AKephartC482_Inventory extends Application {
    
    /**
     * Start the main screen view
     * @param stage stage to set the scene
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Populate some data into the part table.
     * @param args
     */
    public static void main(String[] args) {
        InHouse inhouse1 = new InHouse(1, "InHousePart 1", 20.00 , 5, 3, 10, 5060650);
        Outsourced outsourced1 = new Outsourced(2, "OutSourcedPart 1", 20.00, 5, 3, 10, "ABC Inc.");
        Inventory.addPart(outsourced1);
        Inventory.addPart(inhouse1);
        
        launch(args);
    }
    
}
