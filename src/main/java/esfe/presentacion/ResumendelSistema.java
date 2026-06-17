package esfe.presentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ResumendelSistema extends JPanel {

    private JPanel panelPrincipal;
    private JTextField JLabelEquiposTotales;
    private JTextField JLabelDisponibles;
    private JTextField JLabelEnPrestamo;
    private JTextField JLabelEnReparacion;
    private JTable tblPrestamosRecientes;

    public ResumendelSistema() {

        System.out.println("panelPrincipal = " + panelPrincipal);
        System.out.println("tblPrestamosRecientes = " + tblPrestamosRecientes);

        cargarContadores();

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

            JLabelEquiposTotales.setText("Equipos Totales: " + totales);
            JLabelDisponibles.setText("Disponibles: " + disponibles);
            JLabelEnPrestamo.setText("En préstamo: " + prestados);
            JLabelEnReparacion.setText("En reparación: " + reparacion);

            JLabelEquiposTotales.setEditable(false);
            JLabelDisponibles.setEditable(false);
            JLabelEnPrestamo.setEditable(false);
            JLabelEnReparacion.setEditable(false);

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