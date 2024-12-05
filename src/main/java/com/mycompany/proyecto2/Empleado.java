/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2;

/**
 *
 * @author ronal
 */
public class Empleado {
    private String Nombre;
    private String Apellido_Pat;
    private String Apellido_Mat;
    private String DNI;
    private String Telefono;
    
    public Empleado() {
    }

    
    public Empleado(String Nombre, String Apellido_Pat, String Apellido_Mat, String DNI, String Telefono) {
        this.Nombre = Nombre;
        this.Apellido_Pat = Apellido_Pat;
        this.Apellido_Mat = Apellido_Mat;
        this.DNI = DNI;
        this.Telefono = Telefono;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido_Pat() {
        return Apellido_Pat;
    }

    public void setApellido_Pat(String Apellido_Pat) {
        this.Apellido_Pat = Apellido_Pat;
    }

    public String getApellido_Mat() {
        return Apellido_Mat;
    }

    public void setApellido_Mat(String Apellido_Mat) {
        this.Apellido_Mat = Apellido_Mat;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    
    
    
}
