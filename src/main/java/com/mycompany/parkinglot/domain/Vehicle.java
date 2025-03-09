/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.parkinglot.domain;

/**
 *
 * @author VANESA-ALEXANDER-YONIER
 */
public interface Vehicle {
    String getPlate();
    void setPlate(String plate);
    String getType();
    TypeEnum getVehicleType();
    void setVehicleType(TypeEnum type);
    String toString();
}
