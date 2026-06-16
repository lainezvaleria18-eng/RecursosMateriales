package esfe.presentacion;

import esfe.dominio.Prestamo;
import esfe.dominio.Recursos;
import esfe.persistencia.PrestamoDAO;
import esfe.persistencia.RecursosDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class RistrarPrestamoForm extends JDialog {

    private JTextField txtsolicitante;
    private JTextField txtEspecialidad;
    private JTextField txtDevolucion;
    private JTextField txtcategoria;
    private JTextField txtbuscar;
    private JButton btnbuscar;
    private JButton btncancelar;
    private JButton btnsiguiente;
    private JTable table1;
    private JPanel panelPrincipal;
    private JTextField txtfecha;


    public RistrarPrestamoForm(String carnetSolicitante) {

        super((java.awt.Frame) null, "Registrar Nuevo Préstamo", true);

        setContentPane(panelPrincipal);
        setSize(950, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


        if (carnetSolicitante == null || carnetSolicitante.trim().isEmpty()) {
            txtsolicitante.setText("");
            txtEspecialidad.setText("");
            txtcategoria.setText("");
        } else {

            txtsolicitante.setText(carnetSolicitante);
            txtEspecialidad.setText("Ingeniería en Sistemas");
            txtcategoria.setText("Laptops");
        }


        txtsolicitante.setEditable(true);
        txtEspecialidad.setEditable(true);
        txtcategoria.setEditable(true);
        txtfecha.setEditable(true);
        txtDevolucion.setEditable(true);


        String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        txtfecha.setText(fechaActual);
        txtDevolucion.setText(fechaActual);


        String[] columnas = {
                "Código",
                "Equipo",
                "Estado",
                "Ubicación"
        };


        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(modeloTabla);


        btnbuscar.addActionListener(e -> {
            try {
                RecursosDAO daoRecursos = new RecursosDAO();
                List<Recursos> lista = daoRecursos.obtenerTodos();

                DefaultTableModel modeloBusqueda = (DefaultTableModel) table1.getModel();
                modeloBusqueda.setRowCount(0);
                String texto = txtbuscar.getText().trim().toLowerCase();

                for (Recursos recurso : lista) {

                    if (recurso.getIdTipoRecurso() == 1) {

                        boolean coincideCriterio = texto.isEmpty()
                                || recurso.getCodigoRecurso().toLowerCase().contains(texto)
                                || recurso.getNombreRecurso().toLowerCase().contains(texto)
                                || recurso.getMarca().toLowerCase().contains(texto);

                        if (coincideCriterio) {
                            String estadoTexto = "";
                            switch (recurso.getIdEstadoRecurso()) {
                                case 1: estadoTexto = "Disponible"; break;
                                case 2: estadoTexto = "Prestado"; break;
                                case 3: estadoTexto = "Agotado"; break;
                                case 4: estadoTexto = "Mantenimiento"; break;
                                default: estadoTexto = "No definido";
                            }

                            modeloBusqueda.addRow(new Object[]{
                                    recurso.getCodigoRecurso(),
                                    recurso.getNombreRecurso(),
                                    estadoTexto,
                                    recurso.getUbicacion()
                            });
                        }
                    }
                }

                if (modeloBusqueda.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontraron recursos correspondientes al criterio ingresado.", "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al consultar los datos del inventario: " + ex.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            }
        });


        btncancelar.addActionListener(e -> dispose());


        btnsiguiente.addActionListener(e -> {


            int filaSeleccionada = table1.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un equipo de la lista para proceder con el registro.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }


            String estadoEquipo = table1.getValueAt(filaSeleccionada, 2).toString();
            if (!estadoEquipo.equals("Disponible")) {
                JOptionPane.showMessageDialog(this, "El recurso seleccionado no se encuentra disponible para préstamo. Estado actual: " + estadoEquipo, "Operación No Permitida", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (txtsolicitante.getText().trim().isEmpty() || txtfecha.getText().trim().isEmpty() || txtDevolucion.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos de texto del formulario son obligatorios.", "Campos Requeridos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {

                String nombreEquipo = table1.getValueAt(filaSeleccionada, 1).toString();

                Prestamo prestamo = new Prestamo();
                prestamo.setFechaSolicitud(txtfecha.getText().trim());
                prestamo.setFechaDevolucion(txtDevolucion.getText().trim());
                prestamo.setEstado("Pendiente");
                prestamo.setObservaciones("Préstamo registrado para el equipo: " + nombreEquipo);


                prestamo.setIdUsuario(2);
                prestamo.setIdTipoConsumidor(1);


                PrestamoDAO daoPrestamo = new PrestamoDAO();
                boolean guardadoExitoso = daoPrestamo.guardar(prestamo);

                if (guardadoExitoso) {
                    JOptionPane.showMessageDialog(this, "El préstamo ha sido guardado exitosamente en el sistema.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "La base de datos rechazó la solicitud de inserción. Verifique las restricciones de integridad.", "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado al procesar el registro: " + ex.getMessage(), "Excepción Detectada", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}