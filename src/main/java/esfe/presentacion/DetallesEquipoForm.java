package esfe.presentacion;

import javax.swing.*;

public class DetallesEquipoForm extends JFrame {

    private JLabel lblNombre;
    private JLabel lblCodigo;
    private JLabel lblCategoria;
    private JLabel lblEstado;
    private JLabel lblUbicacion;
    private JLabel lblValor;
    private JLabel lblResponsable;
    private JTextArea txtObservaciones;
    private JButton salirButton;
    private JPanel panelPrincipal;

    public DetallesEquipoForm() {

        setTitle("Detalles del Equipo");

        setContentPane(panelPrincipal);

        setSize(500,450);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Datos de ejemplo

        lblNombre.setText("💻 Laptop Dell Latitude 5520");

        lblCodigo.setText("Código: LAP-001");

        lblCategoria.setText("Categoría: Laptop");

        lblEstado.setText("Estado: 🟢 Disponible");

        lblUbicacion.setText("Ubicación: Bodega Central");

        lblValor.setText("Valor: $850");

        lblResponsable.setText("Responsable: Carlos Martínez");

        txtObservaciones.setText(
                "Equipo en buenas condiciones."
        );

        txtObservaciones.setEditable(false);

        salirButton.addActionListener(e -> dispose());

    }

}