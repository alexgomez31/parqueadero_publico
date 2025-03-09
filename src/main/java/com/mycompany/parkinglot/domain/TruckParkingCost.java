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
import java.util.Random;
public class TruckParkingCost implements IParkingCost {
    @Override
    public long calculateCost(Vehicle vehicle, long input, long output) {
        // Sorteo para camioneros
        if (runLottery()) {
            return 0; // Gratis si gana el sorteo
        }
        
        // Convertir timestamps a LocalDateTime
        LocalDateTime startDate = LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(input), ZoneId.systemDefault());
        LocalDateTime endDate = LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(output), ZoneId.systemDefault());
        
        // Calcular la duración en horas
        long minutes = ChronoUnit.MINUTES.between(startDate, endDate);
        double hours = minutes / 60.0;
        
        // Calcular la tarifa
        long cost;
        
        if (hours <= 12) {
            cost = 10000; // Menos de 12 horas
        } else if (hours <= 24) {
            cost = 15000; // Entre 12 y 24 horas
        } else {
            int days = (int) (hours / 24);
            double remainingHours = hours % 24;
            
            cost = days * 15000; // 15000 por día completo
            
            if (remainingHours > 0) {
                cost += Math.round((remainingHours / 24) * 15000); // Fracción del día a prorrata
            }
        }
        
        // Redondear a la centena más cercana por encima
        cost = Utilities.roundToNearestHundred(cost);
        
        return cost;
    }
    
    private boolean runLottery() {
        // Implementación del sorteo: número aleatorio entre 1 y 1000
        Random random = new Random();
        int winningNumber = 500; // Número ganador (fijo para este ejemplo)
        int userNumber = random.nextInt(1000) + 1; // Número aleatorio entre 1 y 1000
        
        return userNumber == winningNumber; // Gana si acierta el número
    }
}