/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.proyecto2;

import java.awt.CardLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ronal
 */
public class Editar extends javax.swing.JPanel {

    
    public String filaG[]= new String[10];
    
    
    String estado= "Desactivo";
    Connection con;
    PreparedStatement pst;
 
    
    static CardLayout cardlayout;
    
    Equipo1 e1 = new Equipo1();
    Suceso s1 = new Suceso();
    Num_serie numS = new Num_serie();
    Aula aul = new Aula();
    Nombre nom = new Nombre();
    DNI dni = new DNI();
    Telefono tel = new Telefono();
    FechaEdit FE = new FechaEdit();
    Detalle_ocur DO = new Detalle_ocur();
    Estado E = new Estado();
    
    
    
    public Editar() {
        initComponents();
        con = MySQL.Conectar();
        
        llenarCombo();
        llenarTituloTabla();
        mostrarPanelSeleccionado();
        llenarTabla();
        txtActual.setEditable(false);
        
        
    }

        
 
    String ParaEquipo = "UPDATE detalle_reporte SET Id_equipo = ? WHERE Num_serie = ?";
    String ParaSuceso = "UPDATE detalle_reporte SET Id_tipo = ? WHERE Num_serie = ?";
    String ParaAula = "UPDATE detalle_reporte SET Id_aula = ? WHERE Num_serie = ?";
    String ParaEstado = "UPDATE detalle_reporte SET Estado_activo = ? WHERE Num_serie = ?";
    
    void verificarEstado(int num){
        if (num==0) {
            num=1;
        }else if(num==1){
            num=0;
        }
    }
    
    void editarCombos(int index, String s){
        try {
                   
            pst = con.prepareStatement(s);
            pst.setInt(1, index);
            pst.setString(2, filaG[2]);
            int rs= pst.executeUpdate();
            if (rs == 1) {
                JOptionPane.showMessageDialog(null, "Editado correctamente");
            }
        } catch (Exception e) {
            System.out.println("Error 8: " + e.getMessage());
        }
    }
    
    String ParaNum_Serie = "UPDATE detalle_reporte SET Num_serie = ? WHERE Num_serie = ?";
    String ParaNombre = "UPDATE empleados SET Nombres = ? , Apell_pat = ?, Apell_mat = ? WHERE DNI = ?";
    String ParaDNI = "UPDATE empleados SET DNI = ? WHERE DNI = ?";
    String ParaTelef = "UPDATE empleados SET Telefono = ? WHERE DNI = ?";
    String ParaDO = "UPDATE detalle_reporte SET Detalle_reporte = ? WHERE Num_serie = ?";
    
    
    
    
    void editarTextos(String nuevoValor, String s, int num){
        try {
                   
            pst = con.prepareStatement(s);
            pst.setString(1, nuevoValor);
            pst.setString(2, filaG[num]);
            int rs= pst.executeUpdate();
            if (rs == 1) {
                JOptionPane.showMessageDialog(null, "Editado correctamente");
            }
        } catch (Exception e) {
            System.out.println("Error 8: " + e.getMessage());
        }
    }
    
    String ParaFecha = "UPDATE detalle_reporte SET Fecha_registro = ? WHERE Num_serie = ?";
    
    void editarFechas(java.sql.Date nuevaFecha, String s, int num){
        try {
                   
            pst = con.prepareStatement(s);
            pst.setDate(1, nuevaFecha);
            pst.setString(2, filaG[num]);
            int rs= pst.executeUpdate();
            if (rs == 1) {
                JOptionPane.showMessageDialog(null, "Editado correctamente");
            }
        } catch (Exception e) {
            System.out.println("Error 8: " + e.getMessage());
        }
    }
    
    
    
    void editarSoloNombre(String nombre, String apellidoP, String apellidoM, String s){
        try {
                   
            pst = con.prepareStatement(s);
            pst.setString(1, nombre);
            pst.setString(2, nombre);
            pst.setString(3, nombre);
            pst.setString(4, filaG[5]);
            int rs= pst.executeUpdate();
            if (rs == 1) {
                JOptionPane.showMessageDialog(null, "Editado correctamente");
            }
        } catch (Exception e) {
            System.out.println("Error 8: " + e.getMessage());
        }
    }
    
    
    
    
    
    

    

    
        
    


    
    public String MostrarValor(int num){
        return filaG[num];
    }

    void llenarPanel(JPanel p, int z){
                content2.add(p,"e1"); //agrega el panel p al content2 al contenedor content 2 y le asigna la clave "e1"
                cardlayout.show(content2,"e1"); // Le pide al cardlayout que muestre en el contenedor el panelque tiene la clave asignada de "e1"
                txtActual.setText(filaG[z]);
    }
    
    void mostrarPanelSeleccionado(){
        
        cardlayout = (CardLayout)content2.getLayout();
        int num = cboValor.getSelectedIndex()+1;
        switch (num) {
            case 1:           
                llenarPanel(e1, 0);
                break;
            case 2:
                llenarPanel(s1, 1);               
                break;
            case 3:
                llenarPanel(numS, 2);
                break;
            case 4:
                llenarPanel(aul, 3);
                break;
            case 5:
                llenarPanel(nom, 4);
                break;
            case 6:
                llenarPanel(dni, 5);
                break;
            case 7:
                llenarPanel(tel, 6);
                break;
            case 8:
                llenarPanel(FE, 7);
                break;
            case 9:
                llenarPanel(DO, 8);
                break;
            case 10:
                llenarPanel(E, 9);
                break;
            
            
                
                
            
            default:
                throw new AssertionError();
        }
    }

    void llenarCombo(){
        cboValor.addItem("Equipo");
        cboValor.addItem("Suceso");
        cboValor.addItem("Num serie");
        cboValor.addItem("Aula");
        cboValor.addItem("Nombre");
        cboValor.addItem("DNI");
        cboValor.addItem("Telefono");
        cboValor.addItem("Fecha de registro");       
        cboValor.addItem("Detalle reporte");       
        cboValor.addItem("Estado");    
    }
 
    void llenarTituloTabla(){
        try{
            String titulos[] = {"Equipo", "Suceso", "Numero de Serie", "Aula", "Nombre", "DNI", "Telefono", "Fecha Reg.","Detalles","Estado"};
            DefaultTableModel model = new DefaultTableModel(null, titulos);
            tblMostrar.setModel(model);
            
        }catch(Exception e){
            
        }
    }
    

    

    
    void llenarTabla(){
        try {
            String titulos[] = {"Equipo", "Suceso", "Numero de Serie", "Aula", "Nombre", "DNI", "Telefono", "Fecha Reg.","Detalles","Estado"};
            DefaultTableModel model = new DefaultTableModel(null, titulos);
            tblMostrar.setModel(model);
            


            String query = "SELECT a.Nom_aula, c.Equipo, dr.Num_serie, dr.Fecha_registro, dr.Estado_activo, dr.Detalle_reporte, emp.Nombres, emp.Apell_pat, emp.Apell_mat, emp.DNI, emp.Telefono, " +
                           "s.Tipo " +
                           "FROM detalle_reporte dr " +
                           "JOIN componentes c ON dr.Id_equipo = c.Id_equipo " +
                           "JOIN aulas a ON dr.Id_aula = a.Id_aula " +
                           "JOIN empleados emp ON dr.Id_empleado = emp.Id_empleado " +
                           "JOIN tipo_suceso s ON dr.Id_tipo = s.Id_tipo";
            
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
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
            
            tblMostrar.setModel(model);
            
            
            
        } catch (Exception e) {
            System.out.println("Error en llenar tabla: "+ e.getMessage());
        }

                    
            
        
                      
    }
    
 
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboValor = new javax.swing.JComboBox<>();
        btnMostrar = new javax.swing.JButton();
        content2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMostrar = new javax.swing.JTable();
        btnCambiar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtActual = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setText("QUÉ VALOR QUIERE EDITAR?");

        cboValor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboValorItemStateChanged(evt);
            }
        });
        cboValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboValorActionPerformed(evt);
            }
        });
        cboValor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboValorPropertyChange(evt);
            }
        });

        btnMostrar.setBackground(new java.awt.Color(204, 255, 255));
        btnMostrar.setText("ACTUALIZAR DATOS");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        content2.setBackground(new java.awt.Color(204, 255, 204));
        content2.setLayout(new java.awt.CardLayout());

        tblMostrar.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMostrarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMostrar);

        btnCambiar.setBackground(new java.awt.Color(204, 255, 255));
        btnCambiar.setText("GUARDAR CAMBIOS");
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });

        jLabel4.setText("Valor actualizado");

        jLabel5.setText("Valor actual");

        jButton1.setText("ACTUALIZAR TABLA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5))
                            .addComponent(txtActual, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(content2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCambiar)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMostrar))))
                        .addContainerGap(68, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(cboValor, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addComponent(jLabel4)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(9, 9, 9)
                        .addComponent(cboValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btnCambiar)
                                .addGap(28, 28, 28)
                                .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(content2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
 
    
    
    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        // TODO add your handling code here:
        int num = cboValor.getSelectedIndex()+1;
        switch (num) {
            case 1:           
                editarCombos(e1.devolverEquipoNuevo(), ParaEquipo);
                
                break;
            case 2:
                editarCombos(s1.devolverSucesoNuevo(), ParaSuceso);
                            
                break;
            case 3:
                editarTextos(numS.retornarNuevoNS(), ParaNum_Serie,2);
                
                break;
            case 4:
                editarCombos(aul.devolverAulaNueva(), ParaAula);  
                break;
            case 5:
                editarSoloNombre(nom.retonarNombres(), nom.retonarApellidoP(), nom.retonarApellidoM(), ParaNombre);
                break;
            case 6:
                editarTextos(dni.devolverDNI(), ParaDNI,5); 
                break;
            case 7:
                editarTextos(tel.retonarTelefonoActual(), ParaTelef,5); 
                break;
            case 8:
                editarFechas(FE.retornarFecha(), ParaFecha, 2);
                break;
            case 9:
                editarTextos(DO.retornarDetallesOcurrido(), ParaDO, 2); 
                break;
            case 10:
                int b1 = E.retonarEstadoNuevo();
                verificarEstado(b1);
                editarCombos(b1, ParaEstado);               
                
                break;           
            default:
                throw new AssertionError();
        }
        
        System.out.println(e1.devolverEquipoNuevo());
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCambiarActionPerformed

    private void tblMostrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMostrarMouseClicked

        if(evt.getButton()==1){
            int fila = tblMostrar.getSelectedRow();
            filaG[0] = String.valueOf(tblMostrar.getValueAt(fila, 0));
            filaG[1] = String.valueOf(tblMostrar.getValueAt(fila, 1));
            filaG[2] = String.valueOf(tblMostrar.getValueAt(fila, 2));
            filaG[3] = String.valueOf(tblMostrar.getValueAt(fila, 3));
            filaG[4] = String.valueOf(tblMostrar.getValueAt(fila, 4));
            filaG[5] = String.valueOf(tblMostrar.getValueAt(fila, 5));
            filaG[6] = String.valueOf(tblMostrar.getValueAt(fila, 6));
            filaG[7] = String.valueOf(tblMostrar.getValueAt(fila, 7));
            filaG[8] = String.valueOf(tblMostrar.getValueAt(fila, 8));
            filaG[9] = String.valueOf(tblMostrar.getValueAt(fila, 9));
            
            
        }
        mostrarPanelSeleccionado();
        
        
        System.out.println(filaG[0]);
        System.out.println(filaG[1]);
        System.out.println(filaG[2]);
        System.out.println(filaG[3]);
        System.out.println(filaG[4]);
        System.out.println(filaG[5]);
        System.out.println(filaG[6]);
        System.out.println(filaG[7]);
        System.out.println(filaG[8]);
        System.out.println(filaG[9]);
        
        
    }//GEN-LAST:event_tblMostrarMouseClicked

    private void cboValorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboValorPropertyChange
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboValorPropertyChange

    private void cboValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboValorActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cboValorActionPerformed

    private void cboValorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboValorItemStateChanged
        // TODO add your handling code here:
        mostrarPanelSeleccionado();
    }//GEN-LAST:event_cboValorItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JComboBox<String> cboValor;
    private javax.swing.JPanel content2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMostrar;
    private javax.swing.JTextField txtActual;
    // End of variables declaration//GEN-END:variables
}
