package esfe.presentacion;

import esfe.dominio.User;
import esfe.persistencia.UserDAO;

import javax.swing.*;

public class ChangePasswordForm extends JDialog {
    private JPanel mainpanel;
    private JPanel cardpanel;
    private JButton btnGuardarContrasena;
    private JPasswordField txtNuevacontrasena;
    private JPasswordField txtConfirmacontrasena;

    private JPasswordField txtNuevaContrasena;
    private JPasswordField txtConfirmarContrasena;

    private UserDAO userDAO;
    private MainForm mainForm;
    private User usuarioEnRecuperacion;


    public ChangePasswordForm(MainForm mainForm, User usuario) {
        this.mainForm = mainForm;
        this.usuarioEnRecuperacion = usuario;

        this.userDAO = new UserDAO();

        setContentPane(mainpanel);
        setModal(true);
        setTitle("Cambiar Contraseña");
        pack();
        setLocationRelativeTo(mainForm);

        btnGuardarContrasena.addActionListener(e -> guardar());
    }


    public ChangePasswordForm(MainForm mainForm) {
        this.mainForm = mainForm;
        this.userDAO = new UserDAO();


        try {
            java.util.ArrayList<User> lista = userDAO.search("");
            if (lista != null && !lista.isEmpty()) {

                this.usuarioEnRecuperacion = lista.get(lista.size() - 1);
            }
        } catch (Exception e) {

        }

        setContentPane(mainpanel);
        setModal(true);
        setTitle("Cambiar Contraseña");
        pack();
        setLocationRelativeTo(mainForm);

        btnGuardarContrasena.addActionListener(e -> guardar());
    }

    private void guardar() {
        String nueva = "";
        if (txtNuevaContrasena != null) {
            nueva = String.valueOf(txtNuevaContrasena.getPassword()).trim();
        } else if (txtNuevacontrasena != null) {
            nueva = String.valueOf(txtNuevacontrasena.getPassword()).trim();
        }

        String confirmar = "";
        if (txtConfirmarContrasena != null) {
            confirmar = String.valueOf(txtConfirmarContrasena.getPassword()).trim();
        } else if (txtConfirmacontrasena != null) {
            confirmar = String.valueOf(txtConfirmacontrasena.getPassword()).trim();
        }

        if (nueva.isEmpty() || confirmar.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Complete todos los campos.",
                    "Cambiar Contraseña",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!nueva.equals(confirmar)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Las contraseñas no coinciden.",
                    "Cambiar Contraseña",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {

            if (usuarioEnRecuperacion == null) {
                java.util.ArrayList<User> lista = userDAO.search("");
                if (lista != null && !lista.isEmpty()) {

                    usuarioEnRecuperacion = lista.get(lista.size() - 1);
                }
            }

            if (usuarioEnRecuperacion == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: No se pudo identificar al usuario para cambiar la contraseña.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }


            User usuarioModificar = new User();
            usuarioModificar.setClave(nueva);
            usuarioModificar.setIdUsuario(usuarioEnRecuperacion.getIdUsuario()); // Asignación 100% dinámica

            boolean actualizado = userDAO.updatePassword(usuarioModificar);

            if (actualizado) {
                JOptionPane.showMessageDialog(
                        this,
                        "Contraseña actualizada correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();

                PasswordSuccessForm form = new PasswordSuccessForm(mainForm);
                form.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo actualizar la contraseña en el sistema.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al actualizar en la base de datos: " + ex.getMessage(),
                    "System Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}