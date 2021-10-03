/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * InHouse class to handle in-house part types.
 * Extends the part superclass.
 * 
 * @author Aaron Kephart
 * Course:  C482
 * Student ID:  000944803
 */
public class InHouse extends Part {

    private int machineId;

//main contructor
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Get machine ID
     *
     * @return machine ID
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Set machine ID
     *
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

}
