/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Outsourced class to handle outsourced part types. Extends the part
 * superclass.
 *
 * @author Aaron Kephart 
 * Course: C482 
 * Student ID: 000944803
 */
public class Outsourced extends Part {

    private String companyName;

    //default constructor
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Retrieve company name
     *
     * @return company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Set company name
     *
     * @param companyName company name to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
