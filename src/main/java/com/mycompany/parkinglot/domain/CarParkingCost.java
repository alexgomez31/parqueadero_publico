/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.domain;

/**
 *
 * @author VANESA-ALEXANDER-YONIER
 */

import com.mycompany.parkinglot.infra.Utilities;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class CarParkingCost implements IParkingCost {
    @Override
    public long calculateCost(Vehicle vehicle, long input, long output) {
        // Convertir timestamps a LocalDateTime
        LocalDateTime startDate = LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(input), ZoneId.systemDefault());
        LocalDateTime endDate = LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(output), ZoneId.systemDefault());
        
        // Calcular la duración en minutos
        long minutes = ChronoUnit.MINUTES.between(startDate, endDate);
        double hours = minutes / 60.0;
        
        // Calcular la tarifa
        long cost;
        if (hours <= 1) {
            cost = 2000; // Tarifa fija para la primera hora
        } else {
            int completeHours = (int) hours;
            double fractionHour = hours - completeHours;
            
            cost = 2000; // Primera hora
            
            if (completeHours > 1) {
                cost += (completeHours - 1) * 1000; // 1000 por cada hora adicional completa
            }
            
            if (fractionHour > 0) {
                cost += Math.round(1000 * fractionHour); // Fracción de hora a prorrata
            }
        }
        
        // Redondear a la centena más cercana por encima
        cost = Utilities.roundToNearestHundred(cost);
        
        return cost;
    }
}
