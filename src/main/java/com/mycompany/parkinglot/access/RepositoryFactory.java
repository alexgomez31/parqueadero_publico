/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.access;

/**
 *
 * @author VANESA-ALEXANDER-YONIER
 */
public class RepositoryFactory {
    private static RepositoryFactory instance;
    
    private RepositoryFactory() {
    }
    
    public static RepositoryFactory getInstance() {
        if (instance == null) {
            instance = new RepositoryFactory();
        }
        return instance;
    }
    
    public IVehicleRepository getRepository(String type) {
        if (type.equals("default")) {
            return new VehicleRepository();
        }
        return null;
    }
}
