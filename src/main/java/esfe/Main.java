package esfe;

import esfe.presentacion.LoginForm;
import esfe.presentacion.MainForm;

import javax.swing.SwingUtilities;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        SwingUtilities.invokeLater(() -> {


            MainForm mainForm = new MainForm();


            mainForm.setVisible(true);


            LoginForm loginForm = new LoginForm(mainForm);

            loginForm.setVisible(true);

        });

    }

}
