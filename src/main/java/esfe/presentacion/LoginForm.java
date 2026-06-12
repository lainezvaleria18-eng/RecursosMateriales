package esfe.presentacion;

import esfe.dominio.User;
import esfe.persistencia.UserDAO;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginForm extends JDialog {

    private JPanel mainpanel;
    private JPanel cardpanel;
    private JTextField textUsuario;
    private JPasswordField txtClave;
    private JButton btnRecuperar;
    private JButton btnCrearCuenta;
    private JButton btnIngresar;

    private UserDAO userDAO;
    private MainForm mainForm;

    public LoginForm(MainForm mainForm){

        this.mainForm = mainForm;
        userDAO = new UserDAO();

        setContentPane(mainpanel);
        setModal(true);
        setTitle("Inicio de Sesión");
        pack();
        setLocationRelativeTo(mainForm);

        btnIngresar.addActionListener(e -> login());

        btnCrearCuenta.addActionListener(e -> {

            dispose();

            UserWriteForm form = new UserWriteForm(mainForm);
            form.setVisible(true);

        });

        btnRecuperar.addActionListener(e -> {

            dispose();

            RecoverPasswordForm form =
                    new RecoverPasswordForm(mainForm);

            form.setVisible(true);

        });

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });

    }

    private void login(){

        try{

            User user = new User();

            user.setUsuario(textUsuario.getText());
            user.setClave(String.valueOf(txtClave.getPassword()));

            User userAut = userDAO.authenticate(user);

            if(userAut != null &&
                    userAut.getIdUsuario() > 0){

                mainForm.setUserAutenticado(userAut);

                JOptionPane.showMessageDialog(null,
                        "Bienvenido " +
                                userAut.getNombre());

                dispose();

            }
            else{

                JOptionPane.showMessageDialog(null,
                        "Usuario o contraseña incorrectos",
                        "Login",
                        JOptionPane.WARNING_MESSAGE);

            }

        }
        catch(Exception ex){

            JOptionPane.showMessageDialog(null,
                    ex.getMessage());

        }

    }

}