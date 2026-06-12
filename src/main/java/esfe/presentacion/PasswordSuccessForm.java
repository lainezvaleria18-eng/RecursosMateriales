package esfe.presentacion;

import javax.swing.*;

public class PasswordSuccessForm extends JDialog {
    private JPanel mainpanel;
    private JPanel cardpanel;
    private JButton btnIniciarSesion;

    private MainForm mainForm;

    public PasswordSuccessForm(MainForm mainForm){
        this.mainForm = mainForm;

        setContentPane(mainpanel);
        setModal(true);
        setTitle("Contraseña actualizada");
        pack();
        setLocationRelativeTo(mainForm);


        btnIniciarSesion.addActionListener(e -> irAlLogin());
    }


    private void irAlLogin() {
        try {
            this.dispose();


            LoginForm login = new LoginForm(mainForm);
            login.setVisible(true);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    "Error al regresar al inicio de sesión: " + ex.getMessage(),
                    "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}