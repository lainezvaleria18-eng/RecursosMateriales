package esfe.presentacion;

import esfe.dominio.User;
import esfe.persistencia.UserDAO;
import javax.swing.*;
import java.util.Random;

public class UserWriteForm  extends JDialog {
    private JPanel mainpanel;
    private JPanel cardpanel;
    private JPasswordField txtclave;
    private JTextField textCorreo;
    private JTextField textUsuario;
    private JPasswordField txtconfirmarcontraseña;
    private JButton btnRegistrar;
    private JButton yaTienesCuentaButton;
    private JButton iniciarSesionButton;

    private UserDAO userDAO;
    private MainForm mainForm;

    public UserWriteForm(MainForm mainForm) {
        this.mainForm = mainForm;
        userDAO = new UserDAO();

        setContentPane(mainpanel);
        setModal(true);
        setTitle("Crear Cuenta");
        pack();
        setLocationRelativeTo(mainForm);
        btnRegistrar.addActionListener(e -> registrar());

        yaTienesCuentaButton.addActionListener(e -> {
            dispose();

            LoginForm loginForm = new LoginForm(mainForm);
            loginForm.setVisible(true);
        });

        iniciarSesionButton.addActionListener(e -> {

            dispose();

            LoginForm loginForm = new LoginForm(mainForm);

            loginForm.setVisible(true); }); }

    private void registrar() {

        String usuario = textUsuario.getText().trim();
        String correo = textCorreo.getText().trim();
        String clave = String.valueOf(txtclave.getPassword());
        String confirmar = String.valueOf(txtconfirmarcontraseña.getPassword());

        if (usuario.isEmpty() || correo.isEmpty() || clave.isEmpty() || confirmar.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this, "Complete todos los campos.",
                    "Crear Cuenta",
                    JOptionPane.WARNING_MESSAGE
            );

            return; }

        if (!clave.equals(confirmar)) {

            JOptionPane.showMessageDialog(
                    this, "Las contraseñas no coinciden.",
                    "Crear Cuenta",
                    JOptionPane.WARNING_MESSAGE
            );

            return; }


        try {

            User user = new User();
            user.setCarnet("ADM-" + (new Random().nextInt(900) + 100));
            user.setNombre(usuario);
            user.setUsuario(usuario);
            user.setCorreo(correo);
            user.setClave(clave);
            user.setIdRol(1);
            user.setFotoPerfil("default.png");
            user.setEstado((byte) 1);


            User res = userDAO.create(user);

            if (res != null) {
                JOptionPane.showMessageDialog(
                        this, "Cuenta creada correctamente.",
                        "Crear Cuenta",
                        JOptionPane.INFORMATION_MESSAGE );
                dispose();
                LoginForm loginForm = new LoginForm(mainForm);
                loginForm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el usuario en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}