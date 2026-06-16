package esfe.presentacion;

import esfe.dominio.User;
import esfe.persistencia.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PrestamosForm extends JPanel {
    private JLabel lblTitulo;
    private JButton registrarNuevoPrestamoButton;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnSiguiente;
    private JPanel panelPrincipal;

    public PrestamosForm() {

        setLayout(new BorderLayout());
        setOpaque(true);


        add(panelPrincipal, BorderLayout.CENTER);

        lblTitulo.setText("Préstamos");


        registrarNuevoPrestamoButton.addActionListener(e -> {
            RistrarPrestamoForm form = new RistrarPrestamoForm("");
            form.setVisible(true);
        });


        btnBuscar.addActionListener(e -> {
            String carnet = txtBuscar.getText().trim();
            if (carnet.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese el número de carnet del solicitante.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                UserDAO daoUsuario = new UserDAO();
                List<User> listaUsuarios = daoUsuario.search("");
                User usuarioEncontrado = null;

                for (User u : listaUsuarios) {
                    if (u.getCarnet() != null && u.getCarnet().equalsIgnoreCase(carnet)) {
                        usuarioEncontrado = u;
                        break;
                    }
                }

                if (usuarioEncontrado != null) {
                    if (usuarioEncontrado.getEstado() == 1) {
                        JOptionPane.showMessageDialog(this, "Solicitante verificado con éxito:\nNombre: " + usuarioEncontrado.getNombre(), "Usuario Encontrado", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "El solicitante se encuentra registrado pero está inactivo en el sistema.", "Usuario Inactivo", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El número de carnet ingresado no coincide con ningún usuario en el sistema.", "No Encontrado", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al conectar con la base de datos: " + ex.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            }
        });


        btnSiguiente.addActionListener(e -> {
            String carnet = txtBuscar.getText().trim();
            if (carnet.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el carnet del solicitante para continuar con el préstamo.", "Validación", JOptionPane.WARNING_MESSAGE);
            } else {
                RistrarPrestamoForm form = new RistrarPrestamoForm(carnet);
                form.setVisible(true);
            }
        });
    }
}