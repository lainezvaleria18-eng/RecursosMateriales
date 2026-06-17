package esfe.presentacion;

import esfe.dominio.Recursos;
import esfe.persistencia.RecursosDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MovimientoInventarioForm extends JPanel {

    private JComboBox cmbTipoMovimiento;
    private JTextField txtCantidad;
    private JTextField txtFecha;
    private JTextField txtObservacion;
    private JTextField txtRecurso;
    private JTextField txtUsuario;
    private JButton btnGuardar;
    private JButton registrarEquipoButton;
    private JButton buscarButton;
    private JTextField textField1;
    private JTable table1;
    private JPanel JPanel;
    private JScrollPane scrollPane;

    public static DefaultTableModel modelo;

    private TableRowSorter<DefaultTableModel> sorter;

    public MovimientoInventarioForm() {

        setLayout(new BorderLayout(10,10));


        Color azulCorporativo = new Color(0, 51, 102);
        setBackground(azulCorporativo);



        JPanel superior = new JPanel(new BorderLayout());
        superior.setBackground(azulCorporativo); // 🌟 Fondo Azul

        JLabel titulo = new JLabel("Inventario de Recursos IT");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);

        registrarEquipoButton = new JButton("Registrar Equipo");

        registrarEquipoButton.addActionListener(e -> {

            RegistrarEquipoForm form =
                    new RegistrarEquipoForm();

            form.setVisible(true);

        });

        superior.add(titulo, BorderLayout.WEST);
        superior.add(registrarEquipoButton, BorderLayout.EAST);

        add(superior, BorderLayout.NORTH);



        JPanel centro = new JPanel(new BorderLayout(10,10));
        centro.setBackground(azulCorporativo);

        JLabel gestion = new JLabel("Gestión de activos informáticos");
        gestion.setFont(new Font("Arial", Font.BOLD, 16));
        gestion.setForeground(Color.WHITE);


        JPanel norteCentroPanel = new JPanel(new BorderLayout());
        norteCentroPanel.setBackground(azulCorporativo);
        norteCentroPanel.add(gestion, BorderLayout.NORTH);

        JPanel buscarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buscarPanel.setBackground(azulCorporativo);

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setForeground(Color.WHITE);
        buscarPanel.add(lblBuscar);

        textField1 = new JTextField(25);

        buscarPanel.add(textField1);

        buscarButton = new JButton("Buscar");

        buscarPanel.add(buscarButton);

        buscarButton.addActionListener(e -> {

            String texto =
                    textField1.getText().trim();

            if (texto.isEmpty()) {

                sorter.setRowFilter(null);

            } else {

                sorter.setRowFilter(
                        RowFilter.regexFilter(
                                "(?i)" + texto
                        )
                );
            }

            if (table1.getRowCount() == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "No se encontró ningún registro."
                );

                sorter.setRowFilter(null);
            }

        });

        norteCentroPanel.add(buscarPanel, BorderLayout.CENTER);
        centro.add(norteCentroPanel, BorderLayout.NORTH);


        String[] columnas = {
                "Código",
                "Equipo",
                "Estado",
                "Ubicación",
                "Acción"
        };

        modelo =
                new DefaultTableModel(columnas, 0);


        table1 = new JTable(modelo);


        table1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table1.setRowHeight(22);

        sorter = new TableRowSorter<>(modelo);

        table1.setRowSorter(sorter);

        table1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int fila = table1.rowAtPoint(e.getPoint());
                int columna = table1.columnAtPoint(e.getPoint());

                if (columna == 4) {

                    String codigo =
                            table1.getValueAt(fila, 0).toString();

                    String nombre =
                            table1.getValueAt(fila, 1).toString();

                    String estado =
                            table1.getValueAt(fila, 2).toString();

                    String ubicacion =
                            table1.getValueAt(fila, 3).toString();

                    Object[] opciones = {
                            "Detalles",
                            "Editar",
                            "Eliminar"
                    };

                    int opcion =
                            JOptionPane.showOptionDialog(
                                    null,
                                    "Seleccione una acción",
                                    "Acciones",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    null,
                                    opciones,
                                    opciones[0]
                            );

                    if(opcion == 0){

                        DetallesEquipoForm detalles =
                                new DetallesEquipoForm(
                                        nombre,
                                        codigo,
                                        estado,
                                        ubicacion
                                );

                        detalles.setVisible(true);


                    }else if(opcion == 1){

                        RegistrarEquipoForm editar =
                                new RegistrarEquipoForm();

                        editar.cargarDatosEditar(
                                codigo,
                                nombre,
                                estado,
                                ubicacion,
                                fila
                        );

                        editar.setVisible(true);
                    }else if(opcion == 2){

                        int confirmar = JOptionPane.showConfirmDialog(
                                null,
                                "¿Desea eliminar este equipo?",
                                "Confirmar",
                                JOptionPane.YES_NO_OPTION
                        );

                        if(confirmar == JOptionPane.YES_OPTION){

                            RecursosDAO dao = new RecursosDAO();

                            boolean eliminado = dao.eliminar(codigo);

                            if(eliminado){

                                modelo.removeRow(fila);

                                JOptionPane.showMessageDialog(
                                        null,
                                        "Equipo eliminado correctamente."
                                );

                            }else{

                                JOptionPane.showMessageDialog(
                                        null,
                                        "No se pudo eliminar el equipo."
                                );

                            }
                        }

                    }
                }
            }
        });

        scrollPane = new JScrollPane(table1);

        scrollPane.getViewport().setBackground(azulCorporativo);
        scrollPane.setBorder(BorderFactory.createLineBorder(azulCorporativo, 1));

        centro.add(scrollPane, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);

        cargarRecursos();
    }

    private void cargarRecursos() {

        try {

            RecursosDAO dao = new RecursosDAO();

            List<Recursos> lista =
                    dao.obtenerTodos();

            modelo.setRowCount(0);

            for (Recursos recurso : lista) {

                String estado;

                switch (recurso.getIdEstadoRecurso()) {

                    case 1:
                        estado = "Disponible";
                        break;

                    case 2:
                        estado = "Prestado";
                        break;

                    case 3:
                        estado = "Agotado";
                        break;

                    case 4:
                        estado = "Mantenimiento";
                        break;

                    default:
                        estado = "Sin estado";
                }

                modelo.addRow(
                        new Object[]{
                                recurso.getCodigoRecurso(),
                                recurso.getNombreRecurso(),
                                estado,
                                recurso.getUbicacion(),
                                "Detalles | Editar | Eliminar"
                        }
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar recursos"
            );
        }
    }
}