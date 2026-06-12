package esfe.presentacion;

import esfe.dominio.RecuperacionPassword;
import esfe.dominio.User;
import esfe.persistencia.RecuperacionPasswordDAO;
import esfe.persistencia.UserDAO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class RecoverPasswordForm  extends JDialog {
    private JPanel mainpanel;
    private JTextField txtCorreoRecuperar;
    private JButton btnEnviarEnlace;
    private JButton btnVolverLogin;
    private JPanel cardpanel;

    private RecuperacionPasswordDAO recuperacionDAO;
    private UserDAO userDAO;
    private MainForm mainForm;

    public RecoverPasswordForm(MainForm mainForm){
        this.mainForm = mainForm;

        this.recuperacionDAO = new RecuperacionPasswordDAO();
        this.userDAO = new UserDAO();

        setContentPane(mainpanel);
        setModal(true);
        setTitle("Recuperar contraseña");
        pack();
        setLocationRelativeTo(mainForm);

        btnEnviarEnlace.addActionListener(e -> enviarCodigo());

        btnVolverLogin.addActionListener(e -> {
            dispose();
            LoginForm login = new LoginForm(mainForm);
            login.setVisible(true);
        });
    }

    private void enviarCodigo() {
        String correo = txtCorreoRecuperar.getText().trim();

        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese su correo electrónico.",
                    "Recuperar Contraseña",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            ArrayList<User> usuarios = userDAO.search("");
            User usuarioEncontrado = null;

            if (usuarios != null) {
                for (User u : usuarios) {
                    if (u.getCorreo() != null && u.getCorreo().equalsIgnoreCase(correo)) {
                        usuarioEncontrado = u;
                        break;
                    }
                }
            }

            if (usuarioEncontrado == null) {
                JOptionPane.showMessageDialog(this,
                        "El correo electrónico ingresado no se encuentra registrado.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            RecuperacionPassword rp = new RecuperacionPassword();
            rp.setIdUsuario(usuarioEncontrado.getIdUsuario());


            String codigoGenerado = String.valueOf(new Random().nextInt(900000) + 100000);
            rp.setCodigoRecuperacion(codigoGenerado);

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, 2);
            rp.setFechaExpiracion(cal.getTime());

            boolean guardado = recuperacionDAO.create(rp);

            if (guardado) {
                JOptionPane.showMessageDialog(this,
                        "Código enviado correctamente al correo: " + correo + "\n\n" +
                                "Tu código de verificación es: " + codigoGenerado,
                        "Recuperación",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();
                VerifyCodeForm verifyCodeForm = new VerifyCodeForm(mainForm);
                verifyCodeForm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo generar el código de recuperación.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error en el sistema: " + ex.getMessage(),
                    "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}