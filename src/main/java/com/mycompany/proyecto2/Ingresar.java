
package com.mycompany.proyecto2;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author ronal
 */
public class Ingresar extends javax.swing.JPanel {

    
    
    Connection con;
    Statement st;
    
    
    
    public Ingresar() {
        initComponents();
        con = MySQL.Conectar(); 
        llenarComboEquipos();
        llenarComboAulas();
        llenarComboTipo();
        
    }


    void llenarComboTipo(){
        try {
            
            String s = "select *from tipo_suceso";
            String equipo = "";
            
            st = con.createStatement();
            ResultSet rs = st.executeQuery(s);
            cboEquipos.removeAll();
            while (rs.next()) {                
                equipo = rs.getString("Tipo");
                cboTipo.addItem(equipo);
            }
        } catch (Exception e) {
            System.out.println("error 1: "+ e.getMessage());
        }
    }
    
    
    void llenarComboEquipos(){
        try {
            
            String s = "select *from componentes";
            String equipo = "";
            
            
            st = con.createStatement();
            ResultSet rs = st.executeQuery(s);
            cboEquipos.removeAll();
            while (rs.next()) {                
                equipo = rs.getString("Equipo");
                cboEquipos.addItem(equipo);
            }
        } catch (Exception e) {
            System.out.println("error 1: "+ e.getMessage());
        }
    }
    
    void llenarComboAulas(){
        try {
            String s = "select *from aulas";
            String aula ="";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(s);
            while (rs.next()) {                
                aula = rs.getString("Nom_aula");
                cboAulas.addItem(aula);
            }
            
        } catch (Exception e) {
            System.out.println("error 1.1 en : "+ e.getMessage());
        }
    }
    
    
    
   void insertarEmpleadoABD(String Nombres, String Apell_pat, String Apell_mat, String DNI, String Telefono) {
    String sql = "INSERT INTO empleados (Nombres, Apell_pat, Apell_mat, DNI, Telefono) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, Nombres);
        pst.setString(2, Apell_pat);
        pst.setString(3, Apell_mat);
        pst.setString(4, DNI);
        pst.setString(5, Telefono);
        int rs = pst.executeUpdate();
        if (rs == 1) {
            JOptionPane.showMessageDialog(this, "El registro se inserto correctamente");
        }
    } catch (SQLException e) {
        System.out.println("Error 5: " + e.getMessage());
    }
}

    

    
    void ingresarReporte2(int Id_equipo, String Num_serie, int Id_aula, int Id_empleado, String Detalle_reporte, java.sql.Date Fecha_registro,int Id_tipo ) {
    try {
        String query = "INSERT INTO detalle_reporte (Id_equipo, Num_serie, Id_aula, Id_empleado, Detalle_reporte, Fecha_registro, Estado_activo, Id_tipo) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, Id_equipo);
        ps.setString(2, Num_serie);
        ps.setInt(3, Id_aula);
        ps.setInt(4, Id_empleado);
        ps.setString(5, Detalle_reporte);
        ps.setDate(6, Fecha_registro);
        ps.setInt(7, 1);
        ps.setInt(8, Id_tipo);

        int rs = ps.executeUpdate();
        if (rs == 1) {
            JOptionPane.showMessageDialog(null, "Ingreso del reporte correcto");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo ingresar el reporte");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al ingresar el reporte: " + e.getMessage());
    }
}

    
  
    
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNumSerie = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDetalleOcurrido = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApellPat = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtApellMat = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        cboEquipos = new javax.swing.JComboBox<>();
        cboAulas = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(204, 255, 204));

        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel5.setText("Apellido paterno");

        jLabel11.setText("Ingrese el numero de serie del componente");

        jLabel6.setText("Apellido materno");

        txtNumSerie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumSerieKeyTyped(evt);
            }
        });

        jLabel8.setText("DNI");

        jLabel12.setText("Detalle de lo ocurrido(Perdida/Robo/Fallo)");

        jLabel9.setText("Telefono");

        txtDetalleOcurrido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDetalleOcurridoKeyTyped(evt);
            }
        });

        jLabel10.setText("DATOS DEL EQUIPO A INGRESAR");

        jLabel13.setText("Ingrese la fecha");

        jLabel14.setText("Ingrese el aula donde sucedio");

        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        jLabel2.setText("RELLENE LOS DATOS DE INGRESO, POR FAVOR");

        txtApellPat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellPatKeyTyped(evt);
            }
        });

        btnIngresar.setText("INGRESAR");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        jLabel3.setText("Ingrese el equipo");

        txtApellMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellMatKeyTyped(evt);
            }
        });

        jLabel1.setText("INGRESE SUS DATOS");

        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });
        txtDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIKeyTyped(evt);
            }
        });

        jLabel4.setText("Nombres");

        cboEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEquiposActionPerformed(evt);
            }
        });

        jLabel7.setText("Ingrese lo sucedido con el equipo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(49, 49, 49)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(jLabel8)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtDNI)
                                .addComponent(txtTelefono)
                                .addComponent(txtApellMat)
                                .addComponent(txtApellPat, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboAulas, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNumSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDetalleOcurrido, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 40, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)
                        .addGap(3, 3, 3)
                        .addComponent(cboEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboAulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDetalleOcurrido, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtApellPat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtApellMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void Limpiar(){
        txtNombres.setText("");
        txtApellPat.setText("");
        txtApellMat.setText("");
        txtDNI.setText("");
        txtTelefono.setText("");
       
        
       
        txtNumSerie.setText("");
        txtDetalleOcurrido.setText("");
        jdcFecha.setCalendar(null);
        
        
    }
    

    
    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String texto = txtNombres.getText();
        if(!(Character.isLetter(c) || c == ' ' )) evt.consume();
        if (texto.length() >= 11) {
        // Impedir escribir más caracteres si se alcanza la longitud máxima
        evt.consume(); // Consumir el evento para evitar que se agregue más texto
        }
    }//GEN-LAST:event_txtNombresKeyTyped

    void validar(){
        if (txtNombres.getText().isEmpty() || txtApellPat.getText().isEmpty() || txtApellMat.getText().isEmpty() || txtDNI.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtNumSerie.getText().isEmpty() || txtDetalleOcurrido.getText().isEmpty() ) {
        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");  
        }
    }
    
    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        // TODO add your handling code here:

        
        validar();
        try {
          
            Empleado Emp1= new Empleado();
            Emp1.setNombre(txtNombres.getText());
            Emp1.setApellido_Pat(txtApellPat.getText());
            Emp1.setApellido_Mat(txtApellMat.getText());
            Emp1.setDNI(txtDNI.getText());
            Emp1.setTelefono(txtTelefono.getText());
            java.util.Date FechaEnUtilDate = jdcFecha.getDate();
            java.sql.Date FechaENSqlDate = new java.sql.Date(FechaEnUtilDate.getTime());
            insertarEmpleadoABD(Emp1.getNombre(), Emp1.getApellido_Pat(), Emp1.getApellido_Mat(), Emp1.getDNI(), Emp1.getTelefono());         
          

          
            System.out.println(contarEmpleados());
            ingresarReporte2(cboEquipos.getSelectedIndex()+1, txtNumSerie.getText(),cboAulas.getSelectedIndex()+1 ,contarEmpleados() , txtDetalleOcurrido.getText(), FechaENSqlDate,cboTipo.getSelectedIndex()+1);
            
            Limpiar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese los valores corretos");
        }

    }//GEN-LAST:event_btnIngresarActionPerformed

    int contarEmpleados() {
        int contador = 0;
        try {
            con = MySQL.Conectar();
            String s = "SELECT COUNT(*) AS total FROM empleados";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(s);
            if (rs.next()) {
                contador = rs.getInt("total");
            }
        } catch (Exception e) {
            System.out.println("Error al contar los empleados: " + e.getMessage());
        }
        return contador+1;
}
    
    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void cboEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEquiposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEquiposActionPerformed

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void txtApellPatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellPatKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String texto = txtApellPat.getText();
        if(!(Character.isLetter(c))) evt.consume();
        if (texto.length() >= 11) {
       
        evt.consume(); 
    }
    }//GEN-LAST:event_txtApellPatKeyTyped

    private void txtApellMatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellMatKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String texto = txtApellMat.getText();
        if(!(Character.isLetter(c))) evt.consume();
        if (texto.length() >= 11) {
        
        evt.consume(); 
        }
    }//GEN-LAST:event_txtApellMatKeyTyped

    private void txtDNIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String texto = txtDNI.getText();
        if(!(Character.isDigit(c))) evt.consume();
        if (texto.length() >= 8) {
        
        evt.consume(); 
        }
    }//GEN-LAST:event_txtDNIKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String texto = txtTelefono.getText();
        if(!(Character.isDigit(c) || c=='-')) evt.consume();
        if (texto.length() >= 11) {
        
        evt.consume(); 
        
        }        
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtNumSerieKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumSerieKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String texto = txtNumSerie.getText();
        if(!(Character.isLetter(c) || c=='-')) evt.consume();
        if (texto.length() >= 11) {
        
        evt.consume(); 
        }
    }//GEN-LAST:event_txtNumSerieKeyTyped

    private void txtDetalleOcurridoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDetalleOcurridoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        String texto = txtDetalleOcurrido.getText();
        if(!(Character.isLetter(c) || c==' ' || c=='-')) evt.consume();
        if (texto.length() >= 50) {
        
        evt.consume(); 
        }
    }//GEN-LAST:event_txtDetalleOcurridoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JComboBox<String> cboAulas;
    private javax.swing.JComboBox<String> cboEquipos;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JTextField txtApellMat;
    private javax.swing.JTextField txtApellPat;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDetalleOcurrido;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNumSerie;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
