/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory class, handling part/product inventories.
 * 
 * @author Aaron Kephart
 * Course:  C482
 * Student ID:  000944803
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Add a new part
     *
     * @param newPart part to be added to inventory.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Add a new product
     *
     * @param newProduct product to be added to inventory.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Lookup a part by part ID
     *
     * @param partID part ID for which to retrieve the part
     * @return
     */
    public static Part lookupPart(int partID) {
        for (Part part : Inventory.getAllParts()) {
            if (part.getId() == partID) {
                return part;
            }
        }
        return null; //return nothing if part not found.
    }

    /**
     * Lookup a product by product ID
     *
     * @param productID product ID for which to retrieve the product.
     * @return
     */
    public static Product lookupProduct(int productID) {
        for (Product product : Inventory.getAllProducts()) {
            if (product.getId() == productID) {
                return product;
            }
        }
        return null; //return nothing if part not found.
    }

    /**
     * lookupPart method which utilizes a temporary Observable List to store the
     * results. Iterate through all parts, and if part at particular iteration
     * is a substring of the parameter ignoring case, add to the returned
     * results Observable List.
     *
     * @param partName part name as a string to search the entire observable
     * list.
     * @return results
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> results = FXCollections.observableArrayList();
        for (int i = 0; i < Inventory.getAllParts().size(); i++) {
            if (allParts.get(i).getName().toLowerCase().contains(partName.toLowerCase())) {
                results.add(allParts.get(i));
            }
        }
        return results; //return results
    }

    /**
     * lookupProduct method which utilizes a temporary Observable List to store
     * the results. Iterate through all products, and if product at particular
     * iteration is a substring of the parameter ignoring case, add to the
     * returned results Observable List.
     *
     * @param productName
     * @return results
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> results = FXCollections.observableArrayList();
        for (int i = 0; i < Inventory.getAllProducts().size(); i++) {
            if (allProducts.get(i).getName().toLowerCase().contains(productName.toLowerCase())) {
                results.add(allProducts.get(i));
            }
        }
        return results; //return results
    }

    /**
     * Update part class based on parameters
     *
     * @param index index of part
     * @param selectedPart part to update
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Update product class based on parameters
     *
     * @param index index of product
     * @param newProduct product to update
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * Delete part from Inventory.
     *
     * @param selectedPart part to be deleted from inventory
     * @return
     */
    public static boolean deletePart(Part selectedPart) {
        for (Part part : Inventory.getAllParts()) {
            if (part == selectedPart) {
                return Inventory.getAllParts().remove(part);
            }
        }
        return false;
    }

    /**
     * Delete product from Inventory
     *
     * @param selectedProduct product to be deleted from Inventory
     * @return
     */
    public static boolean deleteProduct(Product selectedProduct) {
        for (Product product : Inventory.getAllProducts()) {
            if (product == selectedProduct) {
                return Inventory.getAllProducts().remove(product);
            }
        }
        return false;
    }

    /**
     * Return all parts
     *
     * @return all parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Return all products
     *
     * @return all products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
