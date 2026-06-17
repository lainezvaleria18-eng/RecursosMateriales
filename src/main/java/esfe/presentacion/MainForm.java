package esfe.presentacion;

import esfe.dominio.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainForm extends JFrame {


    private User userAutenticado;

    private JPanel panelPrincipal;

    public User getUserAutenticado() {
        return userAutenticado;
    }

    public void setUserAutenticado(User userAutenticado) {
        this.userAutenticado = userAutenticado;
    }

    public MainForm() {


        setUndecorated(true);

        setTitle("Sistema de Control de Recursos Materiales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        crearMenu();

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());


        panelPrincipal.setBackground(new Color(245, 246, 250));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private void crearMenu() {


        JMenuBar menuBar = new JMenuBar();


        menuBar.setBackground(Color.WHITE);
        menuBar.setPreferredSize(new Dimension(0, 45));
        menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(26, 35, 126)));

        setJMenuBar(menuBar);


        Font fuenteMenu = new Font("Segoe UI", Font.BOLD, 14);
        Color colorTextoMenu = new Color(51, 51, 51); // Gris oscuro elegante para las pestañas


        JMenu menuPerfil = new JMenu("Perfil");
        menuPerfil.setFont(fuenteMenu);
        menuPerfil.setForeground(colorTextoMenu);
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

        JMenuItem itemSalir = new JMenuItem("Salir del Sistema");
        menuPerfil.add(itemSalir);
        itemSalir.addActionListener(e -> System.exit(0));


        JMenu menuDashboard = new JMenu("Dashboard");
        menuDashboard.setFont(fuenteMenu);
        menuDashboard.setForeground(colorTextoMenu);
        menuBar.add(menuDashboard);

        JMenuItem itemResumen = new JMenuItem("Resumen del Sistema");
        menuDashboard.add(itemResumen);
        itemResumen.addActionListener(e -> {
            panelPrincipal.removeAll();
            ResumendelSistema form = new ResumendelSistema();
            panelPrincipal.add(form.getPanelPrincipal(), BorderLayout.CENTER);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        });


        JMenu menuInventario = new JMenu("Inventario");
        menuInventario.setFont(fuenteMenu);
        menuInventario.setForeground(colorTextoMenu);
        menuBar.add(menuInventario);


        JMenuItem itemMovimientos = new JMenuItem("Movimientos Inventario");
        menuInventario.add(itemMovimientos);

        itemMovimientos.addActionListener(e -> {
            panelPrincipal.removeAll();


            panelPrincipal.setBackground(new Color(0, 51, 102));

            MovimientoInventarioForm form = new MovimientoInventarioForm();
            panelPrincipal.add(form, BorderLayout.CENTER);

            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        });


        JMenu menuCampanas = new JMenu("Préstamos");
        menuCampanas.setFont(fuenteMenu);
        menuCampanas.setForeground(colorTextoMenu);
        menuBar.add(menuCampanas);

        JMenuItem itemGestionCampanas = new JMenuItem("Gestionar préstamos");
        menuCampanas.add(itemGestionCampanas);
        itemGestionCampanas.addActionListener(e -> {
            panelPrincipal.removeAll();
            PrestamosForm form = new PrestamosForm();


            panelPrincipal.add(form, BorderLayout.CENTER);

            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        });


        JMenu menuUsuarios = new JMenu("Usuarios");
        menuUsuarios.setFont(fuenteMenu);
        menuUsuarios.setForeground(colorTextoMenu);
        menuBar.add(menuUsuarios);

        JMenuItem itemAdministrarUsuarios = new JMenuItem("Administrar usuarios");
        menuUsuarios.add(itemAdministrarUsuarios);
        itemAdministrarUsuarios.addActionListener(e -> {
            panelPrincipal.removeAll();
            GestionUsuariosVista form = new GestionUsuariosVista();
            form.inicializarTablaYComponentes();
            panelPrincipal.add(form.getMainPanel(), BorderLayout.CENTER);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        });


        JMenu menuRoles = new JMenu("Roles");
        menuRoles.setFont(fuenteMenu);
        menuRoles.setForeground(colorTextoMenu);
        menuBar.add(menuRoles);

        JMenuItem itemAdministrarRoles = new JMenuItem("Administrar roles");
        menuRoles.add(itemAdministrarRoles);
        itemAdministrarRoles.addActionListener(e -> {
            panelPrincipal.removeAll();
            RolForm form = new RolForm(this);
            panelPrincipal.add(form.getPanelPrincipal(), BorderLayout.CENTER);
            panelPrincipal.revalidate();
            panelPrincipal.repaint();
        });


        JMenu menuNotificaciones = new JMenu("Notificaciones");
        menuNotificaciones.setFont(fuenteMenu);
        menuNotificaciones.setForeground(colorTextoMenu);
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
}