package com.mycompany.parkinglot.infra;

import com.mycompany.parkinglot.domain.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author VANESA-ALEXANDER-YONIER
 */

public class VehicleImpl implements Vehicle {
    private String plate;
    private TypeEnum type;
    
    public VehicleImpl(String plate, TypeEnum type) {
        this.plate = plate;
        this.type = type;
    }

    @Override
    public String getPlate() {
        return plate;
    }

    @Override
    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Override
    public String getType() {
        return type.toString();
    }

    @Override
    public TypeEnum getVehicleType() {
        return type;
    }

    @Override
    public void setVehicleType(TypeEnum type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "Vehicle{" + "plate=" + plate + ", type=" + type + '}';
    }
}
