package esfe.presentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class GestionUsuariosVista extends JPanel {


    // === COMPONENTES VISUALES ENLAZADOS AL .FORM (NO SE MODIFICAN) ===
    private JPanel mainPanel;
    private JPanel cardpanel;
    private JButton agregarUsuarioButton;
    private JButton buscarButton;
    private JTextField textField1;
    private JTable table1;

    // Componentes lógicos para el control de datos
    private DefaultTableModel modelo;
    private TableRowSorter<DefaultTableModel> sorter;

    // Referencia estática segura para los botones de las celdas
    private static JTable table1Ref;

    public GestionUsuariosVista() {
        // Dejar vacío para el diseñador de IntelliJ
    }

    // =========================================================================
    // INICIALIZACIÓN PRINCIPAL (Segura y compatible con el .form)
    // =========================================================================
    public void inicializarTablaYComponentes() {
        String[] columnas = {"Foto", "Carnet", "Nombre", "RolForm", "Activo", "Acción"};

        modelo = new DefaultTableModel(new Object[][]{}, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo permitimos editar la columna 5 (Acción) para activar los botones
                return column == 5;
            }
        };

        if (table1 != null) {
            table1.setModel(modelo);
            sorter = new TableRowSorter<>(modelo);
            table1.setRowSorter(sorter);

            table1Ref = table1;

            // Inyección de los botones reales en la celda
            table1.getColumn("Acción").setCellRenderer(new PanelAccionesRenderer());
            table1.getColumn("Acción").setCellEditor(new PanelAccionesEditor(this));

            table1.setRowHeight(35);
        }

        configurarEventos();
        cargarDatosSimulados();
    }

    private void configurarEventos() {
        if (buscarButton != null) {
            for (java.awt.event.ActionListener al : buscarButton.getActionListeners()) {
                buscarButton.removeActionListener(al);
            }
            buscarButton.addActionListener(e -> {
                String texto = textField1.getText().trim();
                if (texto.isEmpty()) {
                    if (sorter != null) sorter.setRowFilter(null);
                } else {
                    if (sorter != null) sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
                }
            });
        }

        if (agregarUsuarioButton != null) {
            for (java.awt.event.ActionListener al : agregarUsuarioButton.getActionListeners()) {
                agregarUsuarioButton.removeActionListener(al);
            }
            // "Agregar" también usará ventana emergente para no romper tu pantalla principal
            agregarUsuarioButton.addActionListener(e -> mostrarVentanaAgregar());
        }
    }

    // Redirecciones seguras desde la fila pulsada
    public void ejecutarEditarDesdeFila(int filaModelo) {
        String carnet = modelo.getValueAt(filaModelo, 1).toString();
        String nombre = modelo.getValueAt(filaModelo, 2).toString();
        String rol = modelo.getValueAt(filaModelo, 3).toString();
        String activo = modelo.getValueAt(filaModelo, 4).toString();
        mostrarVentanaEditar(filaModelo, carnet, nombre, rol, activo);
    }

    public void ejecutarEliminarDesdeFila(int filaModelo) {
        String carnet = modelo.getValueAt(filaModelo, 1).toString();
        String nombre = modelo.getValueAt(filaModelo, 2).toString();
        String rol = modelo.getValueAt(filaModelo, 3).toString();
        mostrarVentanaEliminar(filaModelo, carnet, nombre, rol);
    }

    // =========================================================================
    // FORMULARIO EMERGENTE: EDITAR (Cero riesgo de pantalla azul)
    // =========================================================================
    private void mostrarVentanaEditar(int filaModelo, String carnet, String nombre, String rol, String activo) {
        Window padre = SwingUtilities.getWindowAncestor(table1);
        JDialog dialog = new JDialog(padre, "Editar Usuario", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(480, 460);
        dialog.setLocationRelativeTo(padre);

        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(new Color(240, 244, 248));

        JLabel titulo = new JLabel("Editar Usuario", JLabel.LEFT);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(10, 40, 80));
        titulo.setBounds(30, 15, 200, 30);
        formPanel.add(titulo);

        // Campos del Formulario
        String[] labels = {"Carnet", "Nombre", "Correo electrónico", "Contraseña"};
        JTextField txtCarnet = new JTextField(carnet);
        txtCarnet.setEditable(false);
        JTextField txtNombre = new JTextField(nombre);
        JTextField txtCorreo = new JTextField(carnet.toLowerCase() + "@esfe.edu.sv");
        JPasswordField txtContra = new JPasswordField("********");

        JTextField[] fields = { txtCarnet, txtNombre, txtCorreo, txtContra };

        int y = 60;
        for (int i = 0; i < 4; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(new Font("Arial", Font.BOLD, 13));
            lbl.setBounds(40, y, 150, 25);
            formPanel.add(lbl);

            fields[i].setBounds(180, y, 240, 25);
            formPanel.add(fields[i]);
            y += 40;
        }

        JLabel lblRol = new JLabel("RolForm");
        lblRol.setFont(new Font("Arial", Font.BOLD, 13));
        lblRol.setBounds(40, y, 50, 25);
        formPanel.add(lblRol);

        JComboBox<String> cbRol = new JComboBox<>(new String[]{"Admin", "Usuario"});
        cbRol.setSelectedItem(rol);
        cbRol.setBounds(180, y, 120, 25);
        formPanel.add(cbRol);

        JLabel lblEstado = new JLabel("Estado");
        lblEstado.setFont(new Font("Arial", Font.BOLD, 13));
        lblEstado.setBounds(40, y + 40, 60, 25);
        formPanel.add(lblEstado);

        JCheckBox chkActivo = new JCheckBox("Activo", activo.equalsIgnoreCase("Sí"));
        chkActivo.setBackground(new Color(240, 244, 248));
        chkActivo.setBounds(180, y + 40, 80, 25);
        formPanel.add(chkActivo);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(220, 50, 50));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBounds(100, y + 90, 110, 30);
        formPanel.add(btnCancelar);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(10, 80, 180));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBounds(240, y + 90, 110, 30);
        formPanel.add(btnGuardar);

        btnCancelar.addActionListener(e -> dialog.dispose());
        btnGuardar.addActionListener(e -> {
            modelo.setValueAt(txtNombre.getText().trim(), filaModelo, 2);
            modelo.setValueAt(cbRol.getSelectedItem().toString(), filaModelo, 3);
            modelo.setValueAt(chkActivo.isSelected() ? "Sí" : "No", filaModelo, 4);
            dialog.dispose();
        });

        dialog.add(formPanel);
        dialog.setVisible(true);
    }

    // =========================================================================
    // VENTANA EMERGENTE: ELIMINAR
    // =========================================================================
    private void mostrarVentanaEliminar(int filaModelo, String carnet, String nombre, String rol) {
        Window padre = SwingUtilities.getWindowAncestor(table1);
        JDialog dialog = new JDialog(padre, "Eliminar Usuario", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(440, 320);
        dialog.setLocationRelativeTo(padre);

        JPanel delPanel = new JPanel(null);
        delPanel.setBackground(new Color(240, 244, 248));

        JPanel cajaBlanca = new JPanel(null);
        cajaBlanca.setBackground(Color.WHITE);
        cajaBlanca.setBorder(BorderFactory.createLineBorder(new Color(210, 215, 220), 1));
        cajaBlanca.setBounds(20, 20, 380, 240);

        JLabel lblAdv = new JLabel("⚠️ ADVERTENCIA");
        lblAdv.setFont(new Font("Arial", Font.BOLD, 14));
        lblAdv.setBounds(25, 15, 200, 25);
        cajaBlanca.add(lblAdv);

        JLabel lblPregunta = new JLabel("¿Está seguro de eliminar este usuario?");
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 12));
        lblPregunta.setBounds(25, 45, 330, 25);
        cajaBlanca.add(lblPregunta);

        JLabel lblUserIcon = new JLabel("👤 " + nombre);
        lblUserIcon.setFont(new Font("Arial", Font.BOLD, 13));
        lblUserIcon.setBounds(25, 85, 300, 25);
        cajaBlanca.add(lblUserIcon);

        JLabel lblCarnetInfo = new JLabel("Carnet: " + carnet);
        lblCarnetInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCarnetInfo.setBounds(40, 110, 200, 20);
        cajaBlanca.add(lblCarnetInfo);

// Nuevo JLabel para el RolForm del usuario
        JLabel lblRolInfo = new JLabel("RolForm: " + rol);
        lblRolInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblRolInfo.setBounds(40, 130, 200, 20); // Posicionado justo abajo de la línea de carnet
        cajaBlanca.add(lblRolInfo);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(255, 210, 0));
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setBounds(40, 185, 110, 35); // Se ajustó 'y' de 170 a 185 por el nuevo campo
        cajaBlanca.add(btnCancelar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(220, 50, 50));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBounds(210, 185, 110, 35); // Se ajustó 'y' de 170 a 185 por el nuevo campo
        cajaBlanca.add(btnEliminar);

        btnCancelar.addActionListener(e -> dialog.dispose());
        btnEliminar.addActionListener(e -> {
            modelo.removeRow(filaModelo);
            dialog.dispose();
        });

        delPanel.add(cajaBlanca);
        dialog.add(delPanel);
        dialog.setVisible(true);
    }

    // =========================================================================
    // VENTANA EMERGENTE: AGREGAR
    // =========================================================================
    private void mostrarVentanaAgregar() {
        Window padre = SwingUtilities.getWindowAncestor(table1);
        JDialog dialog = new JDialog(padre, "Agregar Usuario", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(450, 400);
        dialog.setLocationRelativeTo(padre);

        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(new Color(240, 244, 248));

        JLabel titulo = new JLabel("Agregar Nuevo Usuario", JLabel.LEFT);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setBounds(30, 20, 250, 30);
        formPanel.add(titulo);

        // Formulario simple de inserción rápido
        JLabel lblC = new JLabel("Carnet:"); lblC.setBounds(40, 70, 100, 25); formPanel.add(lblC);
        JTextField txtC = new JTextField(); txtC.setBounds(150, 70, 220, 25); formPanel.add(txtC);

        JLabel lblN = new JLabel("Nombre:"); lblN.setBounds(40, 110, 100, 25); formPanel.add(lblN);
        JTextField txtN = new JTextField(); txtN.setBounds(150, 110, 220, 25); formPanel.add(txtN);

        JLabel lblR = new JLabel("RolForm:"); lblR.setBounds(40, 150, 100, 25); formPanel.add(lblR);
        JComboBox<String> cbR = new JComboBox<>(new String[]{"Admin", "Usuario"});
        cbR.setBounds(150, 150, 120, 25); formPanel.add(cbR);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(80, 250, 110, 30);
        btnCancelar.setBackground(Color.RED);
        btnCancelar.setForeground(Color.WHITE);
        formPanel.add(btnCancelar);

        JButton btnGuardar = new JButton("Agregar");
        btnGuardar.setBounds(220, 250, 110, 30);
        btnGuardar.setBackground(Color.BLUE);
        btnGuardar.setForeground(Color.WHITE);
        formPanel.add(btnGuardar);

        btnCancelar.addActionListener(e -> dialog.dispose());
        btnGuardar.addActionListener(e -> {
            if(!txtC.getText().trim().isEmpty() && !txtN.getText().trim().isEmpty()){
                modelo.addRow(new Object[]{"[Foto]", txtC.getText().trim(), txtN.getText().trim(), cbR.getSelectedItem().toString(), "Sí", ""});
            }
            dialog.dispose();
        });

        dialog.add(formPanel);
        dialog.setVisible(true);
    }

    private void cargarDatosSimulados() {
        if (modelo != null && modelo.getRowCount() == 0) {
            modelo.addRow(new Object[]{"[Foto]", "U2026001", "Edwin Flores", "Admin", "Sí", ""});
            modelo.addRow(new Object[]{"[Foto]", "20230125", "Juan Pérez", "Bodeguero", "Sí", ""});
            modelo.addRow(new Object[]{"[Foto]", "U2026002", "María López", "Usuario", "Sí", ""});
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    // =========================================================================
    // COMPONENTES DE INTERFAZ DE FILA (RENDERER & EDITOR)
    // =========================================================================
    private static class PanelAccionesRenderer extends JPanel implements TableCellRenderer {
        private final JButton btnEditar = new JButton("Editar");
        private final JButton btnEliminar = new JButton("Eliminar");

        public PanelAccionesRenderer() {
            setLayout(new GridLayout(1, 2, 5, 0));
            setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

            btnEditar.setBackground(new Color(255, 210, 0));
            btnEditar.setForeground(Color.BLACK);
            btnEditar.setFont(new Font("Arial", Font.BOLD, 11));

            btnEliminar.setBackground(new Color(220, 50, 50));
            btnEliminar.setForeground(Color.WHITE);
            btnEliminar.setFont(new Font("Arial", Font.BOLD, 11));

            add(btnEditar);
            add(btnEliminar);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return this;
        }
    }

    private static class PanelAccionesEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel = new JPanel(new GridLayout(1, 2, 5, 0));
        private final JButton btnEditar = new JButton("Editar");
        private final JButton btnEliminar = new JButton("Eliminar");
        private final GestionUsuariosVista vistaPadre;
        private int filaActual;

        public PanelAccionesEditor(GestionUsuariosVista vista) {
            this.vistaPadre = vista;

            panel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
            btnEditar.setBackground(new Color(255, 210, 0));
            btnEditar.setForeground(Color.BLACK);
            btnEditar.setFont(new Font("Arial", Font.BOLD, 11));

            btnEliminar.setBackground(new Color(220, 50, 50));
            btnEliminar.setForeground(Color.WHITE);
            btnEliminar.setFont(new Font("Arial", Font.BOLD, 11));

            panel.add(btnEditar);
            panel.add(btnEliminar);

            btnEditar.addActionListener(e -> {
                int filaModelo = table1Ref.convertRowIndexToModel(filaActual);
                fireEditingStopped();
                vistaPadre.ejecutarEditarDesdeFila(filaModelo);
            });

            btnEliminar.addActionListener(e -> {
                int filaModelo = table1Ref.convertRowIndexToModel(filaActual);
                fireEditingStopped();
                vistaPadre.ejecutarEliminarDesdeFila(filaModelo);
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.filaActual = row;
            panel.setBackground(table.getSelectionBackground());
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }
}
