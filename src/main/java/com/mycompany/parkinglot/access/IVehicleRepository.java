/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.parkinglot.access;
/**
 *
 * @author VANESA-ALEXANDER-YONIER
 */
import com.mycompany.parkinglot.domain.Vehicle;
import java.util.List;
public interface IVehicleRepository {
    boolean saveVehicle(Vehicle vehicle);
    List<Vehicle> listVehicles();
}