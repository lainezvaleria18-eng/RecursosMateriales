package esfe.presentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ResumendelSistema extends JPanel {

    private JPanel panelPrincipal;
    private JTextField lblEquiposTotales;
    private JTextField lblDisponibles;
    private JTextField lblEnPrestamo;
    private JTextField lblEnReparacion;
    private JTable tblPrestamosRecientes;

    public ResumendelSistema() {
        cargarContadores();
        cargarTablaPrestamos();
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    private void cargarContadores() {
        try {
            int totales = 80;
            int prestados = 13;
            int reparacion = 10;
            int disponibles = totales - prestados - reparacion;

            lblEquiposTotales.setText(String.valueOf(totales));
            lblDisponibles.setText(String.valueOf(disponibles));
            lblEnPrestamo.setText(String.valueOf(prestados));
            lblEnReparacion.setText(String.valueOf(reparacion));

            lblEquiposTotales.setEditable(false);
            lblDisponibles.setEditable(false);
            lblEnPrestamo.setEditable(false);
            lblEnReparacion.setEditable(false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar contadores: " + e.getMessage()
            );
        }
    }

    private void cargarTablaPrestamos() {

        String[] columnas = {
                "ID",
                "Equipo",
                "Usuario",
                "Fecha"
        };

        Object[][] datos = {
                {1, "Laptop Dell", "Juan Pérez", "15/06/2026"},
                {2, "Proyector Epson", "Ana López", "14/06/2026"},
                {3, "Tablet Samsung", "Carlos Ruiz", "13/06/2026"}
        };

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
        tblPrestamosRecientes.setModel(modelo);
    }

    private void createUIComponents() {
    }
}