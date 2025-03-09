/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.presentation;


/**
 *
 * @author VANESA-ALEXANDER-YONIER
 */

import com.mycompany.parkinglot.access.IVehicleRepository;
import com.mycompany.parkinglot.access.RepositoryFactory;
import com.mycompany.parkinglot.domain.TypeEnum;
import com.mycompany.parkinglot.domain.Vehicle;
import com.mycompany.parkinglot.domain.service.Service;
import com.mycompany.parkinglot.infra.Utilities;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        // Crear repositorio
        IVehicleRepository repository = RepositoryFactory.getInstance().getRepository("default");
        
        // Crear servicio
        Service service = new Service(repository);
        
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n---- SISTEMA DE PARQUEADERO PÚBLICO ----");
            System.out.println("1. Registrar entrada de vehículo");
            System.out.println("2. Calcular tarifa de salida");
            System.out.println("3. Listar vehículos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea
            
            switch (option) {
                case 1:
                    registerVehicle(scanner, service);
                    break;
                case 2:
                    calculateFee(scanner, service);
                    break;
                case 3:
                    listVehicles(service);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Gracias por usar el sistema.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        
        // Cerrar recursos
        scanner.close();
    }
    
    private static void registerVehicle(Scanner scanner, Service service) {
        System.out.println("\n---- REGISTRO DE VEHÍCULO ----");
        System.out.print("Placa del vehículo: ");
        String plate = scanner.nextLine();
        
        System.out.println("Tipo de vehículo:");
        System.out.println("1. Moto");
        System.out.println("2. Carro");
        System.out.println("3. Camión");
        System.out.print("Seleccione una opción: ");
        int typeOption = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea
        
        TypeEnum type;
        switch (typeOption) {
            case 1:
                type = TypeEnum.MOTO;
                break;
            case 2:
                type = TypeEnum.CAR;
                break;
            case 3:
                type = TypeEnum.TRUCK;
                break;
            default:
                System.out.println("Opción no válida. Se asignará como Carro por defecto.");
                type = TypeEnum.CAR;
        }
        
        Vehicle vehicle = Utilities.createVehicle(plate, type);
        boolean result = service.saveVehicle(vehicle);
        
        if (result) {
            System.out.println("Vehículo registrado correctamente.");
        } else {
            System.out.println("Error al registrar el vehículo.");
        }
    }
    
    private static void calculateFee(Scanner scanner, Service service) {
        System.out.println("\n---- CÁLCULO DE TARIFA ----");
        System.out.print("Placa del vehículo: ");
        String plate = scanner.nextLine();
        
        System.out.println("Tipo de vehículo:");
        System.out.println("1. Moto");
        System.out.println("2. Carro");
        System.out.println("3. Camión");
        System.out.print("Seleccione una opción: ");
        int typeOption = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea
        
        TypeEnum type;
        switch (typeOption) {
            case 1:
                type = TypeEnum.MOTO;
                break;
            case 2:
                type = TypeEnum.CAR;
                break;
            case 3:
                type = TypeEnum.TRUCK;
                break;
            default:
                System.out.println("Opción no válida. Se asignará como Carro por defecto.");
                type = TypeEnum.CAR;
        }
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        System.out.print("Fecha y hora de entrada (YYYY-MM-DD HH:MM): ");
        String entryDateStr = scanner.nextLine();
        
        System.out.print("Fecha y hora de salida (YYYY-MM-DD HH:MM): ");
        String exitDateStr = scanner.nextLine();
        
        try {
            Date entryDate = format.parse(entryDateStr);
            Date exitDate = format.parse(exitDateStr);
            
            Vehicle vehicle = Utilities.createVehicle(plate, type);
            long cost = service.calculateParkingCost(vehicle, entryDate.getTime(), exitDate.getTime());
            
            System.out.println("Placa: " + plate);
            System.out.println("Tipo: " + type);
            System.out.println("Entrada: " + entryDateStr);
            System.out.println("Salida: " + exitDateStr);
            System.out.println("Costo: $" + cost);
            
        } catch (ParseException e) {
            System.out.println("Error en el formato de fecha. Use YYYY-MM-DD HH:MM");
        }
    }
    
    private static void listVehicles(Service service) {
        System.out.println("\n---- LISTADO DE VEHÍCULOS ----");
        List<Vehicle> vehicles = service.listVehicles();
        
        if (vehicles.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            System.out.println("Placa\t\tTipo");
            System.out.println("------------------------");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle.getPlate() + "\t\t" + vehicle.getVehicleType());
            }
        }
    }
}
