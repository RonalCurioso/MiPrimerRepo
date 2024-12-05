/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ronal
 */
public class MySQL {
    private static String url = "jdbc:mysql://localhost/proyecto2";
    private static String usuario = "root";
    private static String clave = "";
    private static Connection cn;

    public static Connection Conectar() {
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);
            System.out.println("Cargo Controlador...");
            cn = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conectividad a Base de Datos empresa exitosa...");

        } catch (Exception ex) {
            // Error de Conexión!!!
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        return cn;
    }
}
