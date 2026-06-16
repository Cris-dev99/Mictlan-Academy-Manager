package Alumnos;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TonyTonyCris
 */
public class GestionAlumnos extends javax.swing.JFrame {

    int idAlumno;

    public GestionAlumnos() {
        initComponents();
        CargarTabla();
    }

    public static boolean ValidarCampo(JTextField campo) {

        if (campo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Complete el campo"
            );
            campo.setBorder(
                    BorderFactory.createLineBorder(Color.RED, 2)
            );
            campo.requestFocus();
            return false;
        }
        campo.setBorder(UIManager.getBorder("TextField.border"));
        return true;
    }

    public static boolean ValidarTelefono(JTextField campo) {

        String texto = campo.getText().trim();
        if (texto.length() != 10) {
            JOptionPane.showMessageDialog(
                    null,
                    "El teléfono debe tener 10 dígitos"
            );
            campo.setBorder(
                    BorderFactory.createLineBorder(Color.RED, 2)
            );
            campo.requestFocus();
            campo.setText("");
            return false;
        }
        try {
            Long.parseLong(texto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Solo ingrese números"
            );
            campo.setBorder(
                    BorderFactory.createLineBorder(Color.RED, 2)
            );
            campo.requestFocus();
            campo.setText("");
            return false;
        }
        campo.setBorder(
                UIManager.getBorder("TextField.border")
        );
        return true;
    }

    public static boolean ValidarFecha(JTextField campo) {

        String fecha = campo.getText().trim();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        try {
            LocalDate.parse(fecha, formato);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato inválido. Use AAAA/MM/DD");
            campo.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            campo.setText("");
            campo.requestFocus();
            return false;
        }
        campo.setBorder(UIManager.getBorder("TextField.border"));
        return true;
    }

    public void CargarTabla() {

        DefaultTableModel modelo
                = (DefaultTableModel) TablaAlumnos.getModel();

        modelo.setRowCount(0);

        Conexion cn = new Conexion();
        Connection con = cn.conectar();

        try {

            String sql = "SELECT * FROM alumnos";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Object fila[] = {
                    rs.getInt("id_alumno"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("fecha_nacimiento"),
                    rs.getString("estado")

                };

                modelo.addRow(fila);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );
        }
    }

    public static boolean ValidarNombre(JTextField campo) {

        String nombre = campo.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre");
            campo.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            campo.requestFocus();
            return false;
        }
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            JOptionPane.showMessageDialog(null, "El nombre solo puede contener letras");
            campo.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            campo.setText("");
            campo.requestFocus();
            return false;
        }
        campo.setBorder(
                UIManager.getBorder("TextField.border")
        );
        return true;
    }

    public boolean ValidarFormulario() {

        if (!ValidarNombre(txtNombre)) {
            return false;
        }

        if (!ValidarTelefono(txtTelefono)) {
            return false;
        }

        if (!ValidarFecha(txtDoB)) {
            return false;
        }

        if (cmbEstado.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(
                    null,
                    "Seleccione un estado"
            );

            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaAlumnos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDoB = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        cmbEstado = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(350, 45, 800, 730));
        setMinimumSize(new java.awt.Dimension(800, 730));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 730));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 3, 48)); // NOI18N
        jLabel1.setText("Gestion Alumnos");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(190, 0, 400, 80);

        TablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Alumno", "Nombre", "Telefono", "Fecha de nacimiento", "Estado"
            }
        ));
        TablaAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaAlumnosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaAlumnos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 90, 740, 402);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel2.setText("Agregar o editar");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 500, 120, 30);

        jLabel3.setText("Nombre");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 540, 70, 30);
        getContentPane().add(txtNombre);
        txtNombre.setBounds(110, 542, 180, 30);

        jLabel5.setText("Telefono");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 580, 70, 30);
        getContentPane().add(txtTelefono);
        txtTelefono.setBounds(110, 580, 180, 30);

        jLabel4.setText("Fecha de nacimiento");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(400, 540, 120, 30);
        getContentPane().add(txtDoB);
        txtDoB.setBounds(530, 540, 160, 30);

        jLabel7.setText("Estado");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(400, 580, 70, 30);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar);
        btnAgregar.setBounds(160, 630, 140, 30);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(350, 630, 140, 30);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(620, 640, 140, 30);

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un estado", "ACTIVO", "INACTIVO", "SUSPENDIDO", "BETADO" }));
        getContentPane().add(cmbEstado);
        cmbEstado.setBounds(530, 580, 160, 30);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDoB.setText("");
        cmbEstado.setSelectedIndex(0);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (!ValidarFormulario()) {
            return;
        }

        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String DoB = txtDoB.getText();
        String estado = cmbEstado.getSelectedItem().toString();
        if (cmbEstado.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Seleccione un estado",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            cmbEstado.setBorder(
                    BorderFactory.createLineBorder(Color.RED, 2)
            );
            return;
        }

        Conexion cn = new Conexion();
        Connection con = cn.conectar();
        try {

            String verificar
                    = "SELECT * FROM alumnos "
                    + "WHERE nombre = ? "
                    + "AND fecha_nacimiento = ?";

            PreparedStatement psVerificar
                    = con.prepareStatement(verificar);

            psVerificar.setString(1, nombre);
            psVerificar.setString(2, DoB);

            ResultSet rs
                    = psVerificar.executeQuery();

            if (rs.next()) {

                int opcion
                        = JOptionPane.showConfirmDialog(
                                null,
                                "Posible alumno duplicado encontrado\n"
                                + "¿Desea continuar?",
                                "Advertencia",
                                JOptionPane.YES_NO_OPTION
                        );

                if (opcion != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            String sql = "INSERT INTO alumnos "
                    + "(nombre, telefono, fecha_nacimiento, estado) "
                    + "VALUES (?, ?, ?, ?)";

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, DoB);
            ps.setString(4, estado);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Alumno agregado correctamente"
            );

            CargarTabla();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error: " + e.getMessage()
            );
        }

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void TablaAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaAlumnosMouseClicked
        int fila = TablaAlumnos.getSelectedRow();
        idAlumno = Integer.parseInt(TablaAlumnos.getValueAt(fila, 0).toString());
        txtNombre.setText(TablaAlumnos.getValueAt(fila, 1).toString());
        txtTelefono.setText(TablaAlumnos.getValueAt(fila, 2).toString());
        txtDoB.setText(TablaAlumnos.getValueAt(fila, 3).toString());
        cmbEstado.setSelectedItem(TablaAlumnos.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TablaAlumnosMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (!ValidarFormulario()) {
            return;
        }

        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String DoB = txtDoB.getText();
        String estado = cmbEstado.getSelectedItem().toString();

        Conexion cn = new Conexion();
        Connection con = cn.conectar();

        try {

            String verificar
                    = "SELECT * FROM alumnos "
                    + "WHERE nombre = ? "
                    + "AND fecha_nacimiento = ? "
                    + "AND id_alumno != ?";

            PreparedStatement psVerificar
                    = con.prepareStatement(verificar);

            psVerificar.setString(1, nombre);
            psVerificar.setString(2, DoB);
            psVerificar.setInt(3, idAlumno);

            ResultSet rs
                    = psVerificar.executeQuery();

            if (rs.next()) {

                int opcion
                        = JOptionPane.showConfirmDialog(
                                null,
                                "Posible alumno duplicado encontrado\n"
                                + "¿Desea continuar?",
                                "Advertencia",
                                JOptionPane.YES_NO_OPTION
                        );

                if (opcion != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            String sql
                    = "UPDATE alumnos SET "
                    + "nombre = ?, "
                    + "telefono = ?, "
                    + "fecha_nacimiento = ?, "
                    + "estado = ? "
                    + "WHERE id_alumno = ?";

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, DoB);
            ps.setString(4, estado);
            ps.setInt(5, idAlumno);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Alumno actualizado correctamente"
            );

            CargarTabla();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error: " + e.getMessage()
            );
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionAlumnos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaAlumnos;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDoB;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
