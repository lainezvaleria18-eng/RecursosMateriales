package esfe.presentacion;

import esfe.dominio.User;
import esfe.persistencia.UserDAO;
import esfe.persistencia.RecuperacionPasswordDAO;

import javax.swing.*;

public class VerifyCodeForm extends JDialog {
    private JPanel mainpanel;
    private JPanel cardpanel;
    private JButton btnverificarcodigo;
    private JButton btnReenviarCodigo;
    private JTextField txtCod1;
    private JTextField txtCod2;
    private JTextField txtCod3;
    private JTextField txtCod4;
    private JTextField txtCod5;

    private JLabel Verificar;
    private JTextField textCod6;

    private RecuperacionPasswordDAO recuperacionDAO;
    private UserDAO userDAO;
    private MainForm mainForm;

    public VerifyCodeForm(MainForm mainForm){
        this.mainForm = mainForm;

        this.recuperacionDAO = new RecuperacionPasswordDAO();
        this.userDAO = new UserDAO();

        setContentPane(mainpanel);
        setModal(true);
        setTitle("Verificar código");
        pack();
        setLocationRelativeTo(mainForm);

        btnverificarcodigo.addActionListener(e -> verificarCodigo());

        btnReenviarCodigo.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Código reenviado.");
        });
    }

    private void verificarCodigo() {
        String n1 = txtCod1.getText().trim();
        String n2 = txtCod2.getText().trim();
        String n3 = txtCod3.getText().trim();
        String n4 = txtCod4.getText().trim();
        String n5 = txtCod5.getText().trim();
        String n6 = textCod6.getText().trim();

        if (n1.isEmpty() || n2.isEmpty() || n3.isEmpty() || n4.isEmpty() || n5.isEmpty() || n6.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, complete los 6 dígitos del código.",
                    "Verificar Código",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String codigoCompleto = n1 + n2 + n3 + n4 + n5 + n6;

        try {
            boolean esValido = recuperacionDAO.validarCodigo(codigoCompleto);

            if (esValido) {
                JOptionPane.showMessageDialog(this,
                        "Código verificado con éxito.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);


                User usuarioEncontrado = userDAO.obtenerUsuarioPorCodigoRecuperacion(codigoCompleto);

                dispose();


                ChangePasswordForm form = new ChangePasswordForm(mainForm, usuarioEncontrado);
                form.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,
                        "El código ingresado es incorrecto o ha expirado.",
                        "Código Inválido",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al validar el código: " + ex.getMessage(),
                    "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}