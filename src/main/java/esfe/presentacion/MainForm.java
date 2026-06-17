package esfe.presentacion;

import esfe.dominio.User;
import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {

    private User userAutenticado;
    private final JPanel panelPrincipal;

    public User getUserAutenticado() {
        return userAutenticado;
    }

    public void setUserAutenticado(User userAutenticado) {
        this.userAutenticado = userAutenticado;
    }

    public MainForm() {
        setTitle("Sistema de Control de Recursos Materiales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        crearMenu();

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        add(panelPrincipal, BorderLayout.CENTER);

        mostrarDashboard();
    }

    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //================== PERFIL ==================
        JMenu menuPerfil = new JMenu("Perfil");
        menuBar.add(menuPerfil);

        JMenuItem itemCambiarPassword = new JMenuItem("Cambiar contraseña");
        menuPerfil.add(itemCambiarPassword);
        itemCambiarPassword.addActionListener(e -> {
            ChangePasswordForm form = new ChangePasswordForm(this);
            form.setVisible(true);
        });

        JMenuItem itemCambiarUsuario = new JMenuItem("Cambiar usuario");
        menuPerfil.add(itemCambiarUsuario);
        itemCambiarUsuario.addActionListener(e -> {
            LoginForm login = new LoginForm(this);
            login.setVisible(true);
        });

        JMenuItem itemSalir = new JMenuItem("Salir");
        menuPerfil.add(itemSalir);
        itemSalir.addActionListener(e -> System.exit(0));

        //================== DASHBOARD ==================
        JMenu menuDashboard = new JMenu("Dashboard");
        menuBar.add(menuDashboard);

        JMenuItem itemVerDashboard = new JMenuItem("Ver Resumen");
        menuDashboard.add(itemVerDashboard);
        itemVerDashboard.addActionListener(e -> mostrarDashboard());

        //================== INVENTARIO ==================
        JMenu menuInventario = new JMenu("Inventario");
        menuBar.add(menuInventario);

        JMenuItem itemMovimientos = new JMenuItem("Movimientos Inventario");
        menuInventario.add(itemMovimientos);
        itemMovimientos.addActionListener(e -> {
            panelPrincipal.removeAll();
            MovimientoInventarioForm form = new MovimientoInventarioForm();
            panelPrincipal.add(form, BorderLayout.CENTER);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        });

        //================== PRÉSTAMOS ==================
        JMenu menuPrestamos = new JMenu("Préstamos");
        menuBar.add(menuPrestamos);

        JMenuItem itemGestionPrestamos = new JMenuItem("Gestionar préstamos");
        menuPrestamos.add(itemGestionPrestamos);
        itemGestionPrestamos.addActionListener(e -> {
            panelPrincipal.removeAll();
            PrestamosForm form = new PrestamosForm();
            panelPrincipal.add(form, BorderLayout.CENTER);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        });

        //================== USUARIOS Y ROLES ==================
        JMenu menuUsuarios = new JMenu("Usuarios");
        menuBar.add(menuUsuarios);

        JMenuItem itemAdministrarUsuarios = new JMenuItem("Administrar usuarios");
        menuUsuarios.add(itemAdministrarUsuarios);
        itemAdministrarUsuarios.addActionListener(e -> {
            panelPrincipal.removeAll();
            GestionUsuariosVista form = new GestionUsuariosVista();
            form.inicializarTablaYComponentes();

            // Si la vista de usuarios no es un JPanel directo, agregamos su panel principal interno
            panelPrincipal.add(form.getMainPanel(), BorderLayout.CENTER);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        });

        JMenuItem itemAdministrarRoles = new JMenuItem("Administrar roles");
        menuUsuarios.add(itemAdministrarRoles);
        itemAdministrarRoles.addActionListener(e -> {
            RolForm form = new RolForm(this);
            form.setVisible(true);
        });

        //================== NOTIFICACIONES ==================
        JMenu menuNotificaciones = new JMenu("Notificaciones");
        menuBar.add(menuNotificaciones);

        JMenuItem itemVerNotificaciones = new JMenuItem("Ver notificaciones");
        menuNotificaciones.add(itemVerNotificaciones);
        itemVerNotificaciones.addActionListener(e -> {
            panelPrincipal.removeAll();
            NotificacionesForm form = new NotificacionesForm();
            panelPrincipal.add(form, BorderLayout.CENTER);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        });
    }

    private void mostrarDashboard() {
        panelPrincipal.removeAll();
        ResumendelSistema form = new ResumendelSistema();

        // Carga el panel raíz del .form diseñado en IntelliJ
        panelPrincipal.add(form.getPanelPrincipal(), BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }
}