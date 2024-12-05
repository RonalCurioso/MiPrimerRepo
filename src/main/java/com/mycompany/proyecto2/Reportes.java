/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.proyecto2;

import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ronal
 */
public class Reportes extends javax.swing.JPanel {

    String estado = "Desactivo";
    
    Connection con;
    Statement st;
    PreparedStatement pst;
    BusquedaPorFecha BF = new BusquedaPorFecha();
    DNI dni2 = new DNI();
    Aula a2 = new Aula();
    Equipo1 e1 = new Equipo1();
    Suceso s1 = new Suceso();
    
    double sumaPerdida =0;
    double sumaRobo = 0; 
    double sumaAveriacion = 0;
    
    
    static CardLayout cardlayout;
    
    
    public Reportes() {
        initComponents();
        mostrarPanelSeleccionado();
        con = MySQL.Conectar();
        llenarTituloTabla();
        
    }

    

    
    void mostrarPorFecha(java.sql.Date fecha1, java.sql.Date fecha2){
        try {
            sumaRobo = 0;
            sumaPerdida = 0;
            sumaAveriacion = 0;
            con = MySQL.Conectar();
            String titulos[] = {"Equipo", "Suceso", "Numero de Serie", "Aula", "Nombre", "DNI", "Telefono", "Fecha Reg.","Detalles","Estado"};
            DefaultTableModel model = new DefaultTableModel(null, titulos);
            String query = "SELECT a.Nom_aula, c.Equipo, dr.Num_serie, dr.Fecha_registro, dr.Estado_activo, dr.Detalle_reporte, emp.Nombres, emp.Apell_pat, emp.Apell_mat, emp.DNI, emp.Telefono, s.Tipo, c.Precio " +
                       "FROM detalle_reporte dr " +
                       "JOIN componentes c ON dr.Id_equipo = c.Id_equipo " +
                       "JOIN aulas a ON dr.Id_aula = a.Id_aula " +
                       "JOIN empleados emp ON dr.Id_empleado = emp.Id_empleado " +
                       "JOIN tipo_suceso s ON dr.Id_tipo = s.Id_tipo " +
                       "WHERE dr.Fecha_registro BETWEEN ? AND ? ";
            
            pst = con.prepareStatement(query);
            pst.setDate(1, fecha1);
            pst.setDate(2, fecha2);
            ResultSet rs = pst.executeQuery(); 
            while (rs.next()) {                
                String fila[] = new String[10];
                if (rs.getInt("Estado_activo")!=0) {
                    estado ="Activo";
                }
                fila[0] = rs.getString("Equipo"); 
                fila[1] = rs.getString("Tipo");    
                fila[2] = rs.getString("Num_serie");      
                fila[3] = rs.getString("Nom_aula");  
                fila[4] = rs.getString("Nombres")+" "+rs.getString("Apell_pat")+" "+rs.getString("Apell_mat");
                fila[5] = rs.getString("DNI");      
                fila[6] = rs.getString("Telefono"); 
                fila[7] = rs.getString("Fecha_registro"); 
                fila[8] = rs.getString("Detalle_reporte");
                fila[9] = estado;
                
                model.addRow(fila);
            }
            tblTable2.setModel(model);
            rs.close();
            pst.close();
            
            //----------------------------------------------------------------------------------------------------------------------------------------------
            
            String query2 = query + " AND dr.Id_tipo = ?";
            pst = con.prepareStatement(query2);      
            pst.setDate(1, fecha1);
            pst.setDate(2, fecha2);
            
            //AGREGAR POR ROBOS
            pst.setInt(3, 1);
            rs = pst.executeQuery();
            while (rs.next()) {                
                sumaRobo += rs.getDouble("Precio");
                
            }
            
            rs.close();
            
            //-------------------------------------------------------------------------
            
            
            //AGREGAR POR PERDIDA
            pst.setInt(3, 2);
            rs = pst.executeQuery();
            while (rs.next()) {                
                sumaPerdida += rs.getDouble("Precio");
                
            }
            
            rs.close();
            
            //-------------------------------------------------------------------------
            
            
            //AGREGAR POR AVERIACIOM
            pst.setInt(3, 3);
            rs = pst.executeQuery();
            while (rs.next()) {                
                sumaAveriacion += rs.getDouble("Precio");
                
            }
            
            rs.close();
            txtSalida.setText("");
            txtSalida.setText("Dinero total perdido por ROBOS: " + sumaRobo + "\nDinero total perdido por PERDIDA: " + sumaPerdida + "\nDinero gastado en equipos que se malograron: " + sumaAveriacion);

            
        } catch (Exception e) {
            System.out.println("Error al mostrar por fechas en: "+ e.getMessage());
        }
        
        
    }
    
    
    void mostrarPorRobo(java.sql.Date fecha1, java.sql.Date fecha2){
        String query = "SELECT a.Nom_aula, c.Equipo, dr.Num_serie, dr.Fecha_registro, dr.Estado_activo, dr.Detalle_reporte, emp.Nombres, emp.Apell_pat, emp.Apell_mat, emp.DNI, emp.Telefono, s.Tipo, c.Precio" +
                       "FROM detalle_reporte dr " +
                       "JOIN componentes c ON dr.Id_equipo = c.Id_equipo " +
                       "JOIN aulas a ON dr.Id_aula = a.Id_aula " +
                       "JOIN empleados emp ON dr.Id_empleado = emp.Id_empleado " +
                       "JOIN tipo_suceso s ON dr.Id_tipo = s.Id_tipo " +
                       "WHERE  dr.Fecha_registro BETWEEN ? AND ? AND s.Tipo = ?";
        
        
        
    }
    

    
    String ParaDNI = "WHERE emp.DNI = ?";
    String ParaAula = "WHERE a.Nom_aula = ?";
    String ParaSuceso = "WHERE s.Tipo = ?";
    String ParaEquipo = "WHERE c.Equipo = ?";
    
    
    
    void mostrarPorCombo(String cambio, String query2){
        try {
            sumaRobo = 0;
            sumaPerdida = 0;
            sumaAveriacion = 0;
            
            con = MySQL.Conectar();
            String titulos[] = {"Equipo", "Suceso", "Numero de Serie", "Aula", "Nombre", "DNI", "Telefono", "Fecha Reg.","Detalles","Estado"};
            DefaultTableModel model = new DefaultTableModel(null, titulos);
            String query = "SELECT a.Nom_aula, c.Equipo, dr.Num_serie, dr.Fecha_registro, dr.Estado_activo, dr.Detalle_reporte, emp.Nombres, emp.Apell_pat, emp.Apell_mat, emp.DNI, emp.Telefono, s.Tipo, c.Precio " +
                       "FROM detalle_reporte dr " +
                       "JOIN componentes c ON dr.Id_equipo = c.Id_equipo " +
                       "JOIN aulas a ON dr.Id_aula = a.Id_aula " +
                       "JOIN empleados emp ON dr.Id_empleado = emp.Id_empleado " +
                       "JOIN tipo_suceso s ON dr.Id_tipo = s.Id_tipo " + query2;
                       
            pst = con.prepareStatement(query);
            pst.setString(1, cambio);
            
            ResultSet rs = pst.executeQuery(); 
            while (rs.next()) {                
                String fila[] = new String[10];
                if (rs.getInt("Estado_activo")!=0) {
                    estado ="Activo";
                }
                fila[0] = rs.getString("Equipo"); 
                fila[1] = rs.getString("Tipo");    
                fila[2] = rs.getString("Num_serie");      
                fila[3] = rs.getString("Nom_aula");  
                fila[4] = rs.getString("Nombres")+" "+rs.getString("Apell_pat")+" "+rs.getString("Apell_mat");
                fila[5] = rs.getString("DNI");      
                fila[6] = rs.getString("Telefono"); 
                fila[7] = rs.getString("Fecha_registro"); 
                fila[8] = rs.getString("Detalle_reporte");
                fila[9] = estado;
                model.addRow(fila);
            }
            tblTable2.setModel(model);
            rs.close();
            pst.close();

            
                        
//----------------------------------------------------------------------------------------------------------------------------------------------
            
            String query3 = query + " AND dr.Id_tipo = ?";
            pst = con.prepareStatement(query3);      
            pst.setString(1, cambio);

            
            //AGREGAR POR ROBOS
            pst.setInt(2, 1);
            rs = pst.executeQuery();
            while (rs.next()) {                
                sumaRobo += rs.getDouble("Precio");
                
            }
            
            rs.close();
            
            //-------------------------------------------------------------------------
            
            
            //AGREGAR POR PERDIDA
            pst.setInt(2, 2);
            rs = pst.executeQuery();
            while (rs.next()) {                
                sumaPerdida += rs.getDouble("Precio");
                
            }
            
            rs.close();
            
            //-------------------------------------------------------------------------
            
            
            //AGREGAR POR AVERIACIOM
            pst.setInt(2, 3);
            rs = pst.executeQuery();
            while (rs.next()) {                
                sumaAveriacion += rs.getDouble("Precio");
                
            }
            
            rs.close();
            
            txtSalida.setText("");
            txtSalida.setText("Dinero total perdido por ROBOS: " + sumaRobo + "\nDinero total perdido por PERDIDA: " + sumaPerdida + "\nDinero gastado en equipos que se malograron: " + sumaAveriacion);

            
        } catch (Exception e) {
            System.out.println("Error al mostrar por DNI: "+ e.getMessage());
        }

    }
    

    
    void llenarTituloTabla(){
        try{
            String titulos[] = {"Equipo", "Suceso", "Numero de Serie", "Aula", "Nombre", "DNI", "Telefono", "Fecha Reg.","Detalles","Estado"};
            DefaultTableModel model = new DefaultTableModel(null, titulos);
            tblTable2.setModel(model);
            
        }catch(Exception e){
            
        }
    }
    
    
    
    void mostrarPanelSeleccionado(){
        
        cardlayout = (CardLayout)content3.getLayout();
        int num = cmbBusqueda.getSelectedIndex()+1;
        switch (num) {
            case 1:           
                llenarPanel(BF);
                break;
            case 2:
                llenarPanel(dni2);               
                break;
            case 3:
                llenarPanel(a2);
                break;
            case 4:
                llenarPanel(e1);
                break;
            case 5:
                llenarPanel(s1);
                break;                   
            
            default:
                throw new AssertionError();
        }
    }
    
     void llenarPanel(JPanel p){
                content3.add(p,"e1"); //agrega el panel p al content2 al contenedor content 2 y le asigna la clave "e1"
                cardlayout.show(content3,"e1"); // Le pide al cardlayout que muestre en el contenedor el panelque tiene la clave asignada de "e1"
                
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbBusqueda = new javax.swing.JComboBox<>();
        content3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTable2 = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSalida = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setText("BUSCAR POR ");

        cmbBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fecha", "DNI ", "Aula", "Componente", "Suceso" }));
        cmbBusqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBusquedaItemStateChanged(evt);
            }
        });

        content3.setBackground(new java.awt.Color(204, 255, 204));
        content3.setLayout(new java.awt.CardLayout());

        tblTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTable2);

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtSalida.setColumns(20);
        txtSalida.setRows(5);
        jScrollPane2.setViewportView(txtSalida);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(content3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnBuscar)
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(content3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnBuscar)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void cmbBusquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBusquedaItemStateChanged
        // TODO add your handling code here:
        mostrarPanelSeleccionado();
        
    }//GEN-LAST:event_cmbBusquedaItemStateChanged

    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        
        int num = cmbBusqueda.getSelectedIndex()+1;
        switch (num) {
            case 1:           
                mostrarPorFecha(BF.retornarFecha1(), BF.retornarFecha2());
                break;
            case 2:
                mostrarPorCombo(dni2.devolverDNI(),ParaDNI);            
                break;
            case 3:
                mostrarPorCombo(a2.devolverAulaNuevaEnString(), ParaAula);
                break;
            case 4:    
                mostrarPorCombo(e1.devolverEquipoNuevoEnString(), ParaEquipo);
                break;                      
            case 5:
                mostrarPorCombo(s1.devolverSucesoNuevoEnString(), ParaSuceso);
                break;
            default:
                throw new AssertionError();
        }
        
        
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cmbBusqueda;
    private javax.swing.JPanel content3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTable2;
    private javax.swing.JTextArea txtSalida;
    // End of variables declaration//GEN-END:variables
}
