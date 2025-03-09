/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.domain;

/**
 *
 * @author VANESA-ALEXANDER-YONIER
 */
import java.util.EnumMap;
import java.util.Map;

public class ParkingCostFactory {
    private Map<TypeEnum, IParkingCost> dictionary;
    
    // Singleton
    private static ParkingCostFactory instance;
    
    private ParkingCostFactory() {
        dictionary = new EnumMap<>(TypeEnum.class);
        dictionary.put(TypeEnum.MOTO, new MotoParkingCost());
        dictionary.put(TypeEnum.CAR, new CarParkingCost());
        dictionary.put(TypeEnum.TRUCK, new TruckParkingCost());
    }
    
    public static ParkingCostFactory getInstance() {
        if (instance == null) {
            instance = new ParkingCostFactory();
        }
        return instance;
    }
    
    public IParkingCost getParkingCost(TypeEnum type) {
        return dictionary.get(type);
    }
}
