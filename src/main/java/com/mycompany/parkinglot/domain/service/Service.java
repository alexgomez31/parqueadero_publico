/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.domain.service;

/**
 *
 * @author VANESA-ALEXANDER-YONIER
 */
import com.mycompany.parkinglot.access.IVehicleRepository;
import com.mycompany.parkinglot.domain.IParkingCost;
import com.mycompany.parkinglot.domain.ParkingCostFactory;
import com.mycompany.parkinglot.domain.Vehicle;
import java.util.List;

public class Service {
    private IVehicleRepository repository;
    
    public Service(IVehicleRepository repository) {
        this.repository = repository;
    }
    
    public boolean saveVehicle(Vehicle vehicle) {
        return repository.saveVehicle(vehicle);
    }
    
    public List<Vehicle> listVehicles() {
        return repository.listVehicles();
    }
    
    public long calculateParkingCost(Vehicle vehicle, long input, long output) {
        IParkingCost calculator = ParkingCostFactory.getInstance().getParkingCost(vehicle.getVehicleType());
        return calculator.calculateCost(vehicle, input, output);
    }
}
