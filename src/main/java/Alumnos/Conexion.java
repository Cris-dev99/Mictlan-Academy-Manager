/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alumnos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author calav
 */
public class Conexion {

    Connection con;

    public Connection conectar() {

        try {

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mictlan_admin",
                    "root",
                    ""
            );

            System.out.println("Conexion exitosa");

        } catch (SQLException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }

        return con;
    }
}
