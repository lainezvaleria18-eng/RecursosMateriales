package esfe.presentacion;

import esfe.dominio.Rol;
import esfe.persistencia.RolDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RolForm extends JDialog {

    private JPanel mainpanel;
    private JTextField txtNombreRol;
    private JButton guardarButton;
    private JButton limpiarButton;
    private JLabel nombreDeRolLabel;
    private JTable tblRoles;

    private RolDAO rolDAO;
    private DefaultTableModel modeloTabla;

    public RolForm(MainForm mainForm) {

        rolDAO = new RolDAO();

        mainpanel = new JPanel(new BorderLayout());

        JPanel panelSuperior = new JPanel(new FlowLayout());

        panelSuperior.add(new JLabel("Nombre del Rol:"));

        txtNombreRol = new JTextField(20);
        panelSuperior.add(txtNombreRol);

        guardarButton = new JButton("Guardar");
        limpiarButton = new JButton("Limpiar");

        panelSuperior.add(guardarButton);
        panelSuperior.add(limpiarButton);

        mainpanel.add(panelSuperior, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Nombre Rol"},
                0
        );

        tblRoles = new JTable(modeloTabla);

        JScrollPane scrollPane = new JScrollPane(tblRoles);

        mainpanel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(mainpanel);
        setModal(true);
        setTitle("Gestión de Roles");
        setSize(600, 400);
        setLocationRelativeTo(mainForm);

        cargarRoles();

        guardarButton.addActionListener(e -> guardar());

        limpiarButton.addActionListener(e -> limpiar());
    }

    private void guardar() {

        String nombreRol = txtNombreRol.getText().trim();

        if (nombreRol.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese el nombre del rol"
            );
            return;
        }

        try {

            Rol rol = new Rol();
            rol.setNombreRol(nombreRol);

            int resultado = rolDAO.guardar(rol);

            if (resultado > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Rol guardado correctamente"
                );

                limpiar();
                cargarRoles();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Error al guardar"
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    private void limpiar() {

        txtNombreRol.setText("");
        txtNombreRol.requestFocus();
    }

    private void cargarRoles() {

        modeloTabla.setRowCount(0);

        List<Rol> roles = rolDAO.obtenerTodos();

        for (Rol rol : roles) {

            modeloTabla.addRow(new Object[]{
                    rol.getIdRol(),
                    rol.getNombreRol()
            });
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}