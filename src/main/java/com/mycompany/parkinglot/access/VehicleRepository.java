/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.access;

/**
 *
 * @author VANESA-ALEXANDER-YONIER
 */
import com.mycompany.parkinglot.domain.TypeEnum;
import com.mycompany.parkinglot.domain.Vehicle;
import com.mycompany.parkinglot.infra.Utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements IVehicleRepository {
    private Connection conn;
    
    public VehicleRepository() {
        initDatabase();
    }
    
    private void initDatabase() {
        try {
            // Conexión a la base de datos SQLite en memoria
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite::memory:");
            
            // Crear tabla de vehículos
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS vehicles (plate TEXT PRIMARY KEY, type TEXT)");
            }
        } catch (ClassNotFoundException | SQLException e) {
            Utilities.errorMessage("Error inicializando la base de datos: " + e.getMessage(), "DB Init Error");
        }
    }
    
    @Override
    public boolean saveVehicle(Vehicle vehicle) {
        try {
            String sql = "INSERT OR REPLACE INTO vehicles (plate, type) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, vehicle.getPlate());
                pstmt.setString(2, vehicle.getType());
                pstmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            Utilities.errorMessage("Error guardando vehículo: " + e.getMessage(), "DB Save Error");
            return false;
        }
    }
    
    @Override
    public List<Vehicle> listVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        
        try {
            String sql = "SELECT plate, type FROM vehicles";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                
                while (rs.next()) {
                    String plate = rs.getString("plate");
                    String typeStr = rs.getString("type");
                    TypeEnum type = TypeEnum.valueOf(typeStr);
                    
                    // Crear el objeto vehículo mediante la fábrica o directamente
                    Vehicle vehicle = Utilities.createVehicle(plate, type);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            Utilities.errorMessage("Error listando vehículos: " + e.getMessage(), "DB List Error");
        }
        
        return vehicles;
    }
    
    // Métodos para conectar y desconectar la base de datos
    public void connect() {
        // Ya se conecta en el constructor
    }
    
    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            Utilities.errorMessage("Error cerrando la conexión: " + e.getMessage(), "DB Close Error");
        }
    }
}
