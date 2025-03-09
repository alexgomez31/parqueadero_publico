/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.infra;

/**
 *
 * @author VANESA-ALEXANDER-YONIER
 */
import com.mycompany.parkinglot.domain.TypeEnum;
import com.mycompany.parkinglot.domain.Vehicle;
//import com.mycompany.parkinglot.domain.VehicleImpl;
import javax.swing.JOptionPane;

public class Utilities {
    /**
     * Muestra un mensaje de error
     * @param message mensaje a mostrar
     * @param title título de la ventana
     */
    public static void errorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Redondea un valor a la centena más cercana por encima
     * @param value valor a redondear
     * @return valor redondeado
     */
    public static long roundToNearestHundred(long value) {
        return (long) (Math.ceil(value / 100.0) * 100);
    }
    
    /**
     * Crea un objeto vehículo
     * @param plate placa del vehículo
     * @param type tipo de vehículo
     * @return objeto vehículo
     */
    public static Vehicle createVehicle(String plate, TypeEnum type) {
        return new VehicleImpl(plate, type);
    }
}
