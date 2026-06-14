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

    public DetallesEquipoForm(
            String nombre,
            String codigo,
            String estado,
            String ubicacion) {

        setTitle("Detalles del Equipo");

        setContentPane(panelPrincipal);

        setSize(500, 450);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lblNombre.setText("💻 " + nombre);

        lblCodigo.setText("Código: " + codigo);

        lblCategoria.setText("Categoría: Equipo IT");

        lblEstado.setText("Estado: " + estado);

        lblUbicacion.setText("Ubicación: " + ubicacion);

        lblValor.setText("Valor: No especificado");

        lblResponsable.setText("Responsable: Sistema");

        txtObservaciones.setText(
                "Equipo registrado correctamente."
        );

        txtObservaciones.setEditable(false);

        salirButton.addActionListener(e -> dispose());

    }}