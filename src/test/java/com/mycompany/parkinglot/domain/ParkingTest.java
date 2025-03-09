/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.domain;
import com.mycompany.parkinglot.domain.service.Service;
import com.mycompany.parkinglot.access.IVehicleRepository;
import com.mycompany.parkinglot.access.RepositoryFactory;
import com.mycompany.parkinglot.infra.Utilities;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingTest {
    
    public static void main(String[] args) {
        try {
            // Configuramos el repositorio
            IVehicleRepository repository = RepositoryFactory.getInstance().getRepository("default");
            Service service = new Service(repository);
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            
            // Prueba: Moto menos de 1 hora
            System.out.println("Prueba: Moto menos de 1 hora");
            Vehicle moto1 = Utilities.createVehicle("ABC-123", TypeEnum.MOTO);
            Date date1 = formatter.parse("2025-03-08 10:00");
            Date date2 = formatter.parse("2025-03-08 10:45");
            long cost = service.calculateParkingCost(moto1, date1.getTime(), date2.getTime());
            System.out.println("Costo: $" + cost + " (Esperado: $1000)");
            
            // Prueba: Moto más de 1 hora
            System.out.println("\nPrueba: Moto más de 1 hora");
            Vehicle moto2 = Utilities.createVehicle("DEF-456", TypeEnum.MOTO);
            date1 = formatter.parse("2025-03-08 10:00");
            date2 = formatter.parse("2025-03-08 11:30");
            cost = service.calculateParkingCost(moto2, date1.getTime(), date2.getTime());
            System.out.println("Costo: $" + cost + " (Esperado: $1300)");
            
            // Prueba: Carro menos de 1 hora
            System.out.println("\nPrueba: Carro menos de 1 hora");
            Vehicle car1 = Utilities.createVehicle("GHI-789", TypeEnum.CAR);
            date1 = formatter.parse("2025-03-08 10:00");
            date2 = formatter.parse("2025-03-08 10:45");
            cost = service.calculateParkingCost(car1, date1.getTime(), date2.getTime());
            System.out.println("Costo: $" + cost + " (Esperado: $2000)");
            
            // Prueba: Carro más de 1 hora
            System.out.println("\nPrueba: Carro más de 1 hora");
            Vehicle car2 = Utilities.createVehicle("JKL-012", TypeEnum.CAR);
            date1 = formatter.parse("2025-03-08 10:00");
            date2 = formatter.parse("2025-03-08 12:10");
            cost = service.calculateParkingCost(car2, date1.getTime(), date2.getTime());
            System.out.println("Costo: $" + cost + " (Esperado: $3200)");
            
            // Prueba: Camión menos de 12 horas
            System.out.println("\nPrueba: Camión menos de 12 horas");
            Vehicle truck1 = Utilities.createVehicle("MNO-345", TypeEnum.TRUCK);
            date1 = formatter.parse("2025-03-08 10:00");
            date2 = formatter.parse("2025-03-08 15:00");
            cost = service.calculateParkingCost(truck1, date1.getTime(), date2.getTime());
            System.out.println("Costo: $" + cost + " (Esperado: $10000 o $0 si gana el sorteo)");
            
            // Prueba: Camión entre 12 y 24 horas
            System.out.println("\nPrueba: Camión entre 12 y 24 horas");
            Vehicle truck2 = Utilities.createVehicle("PQR-678", TypeEnum.TRUCK);
            date1 = formatter.parse("2025-03-08 10:00");
            date2 = formatter.parse("2025-03-09 02:00");
            cost = service.calculateParkingCost(truck2, date1.getTime(), date2.getTime());
            System.out.println("Costo: $" + cost + " (Esperado: $15000 o $0 si gana el sorteo)");
            
            // Prueba: Camión más de 24 horas
            System.out.println("\nPrueba: Camión más de 24 horas");
            Vehicle truck3 = Utilities.createVehicle("STU-901", TypeEnum.TRUCK);
            date1 = formatter.parse("2025-03-08 10:00");
            date2 = formatter.parse("2025-03-11 11:00");
            cost = service.calculateParkingCost(truck3, date1.getTime(), date2.getTime());
            System.out.println("Costo: $" + cost + " (Esperado: ~$45700 o $0 si gana el sorteo)");
            
            System.out.println("\nPruebas de guardado y listado de vehículos:");
            // Guardamos unos vehículos
            service.saveVehicle(moto1);
            service.saveVehicle(car1);
            service.saveVehicle(truck1);
            
            // Listamos los vehículos guardados
            System.out.println("\nVehículos guardados:");
            for(Vehicle v : service.listVehicles()) {
                System.out.println(v.getPlate() + " - " + v.getType());
            }
            
        } catch (ParseException ex) {
            System.out.println("Error en el formato de fecha: " + ex.getMessage());
        }
    }
}

