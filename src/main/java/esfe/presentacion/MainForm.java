package esfe.presentacion;

import esfe.dominio.User;

import javax.swing.*;

public class MainForm extends JFrame {

    // Usuario autenticado
    private User userAutenticado;

    private JPanel panelContenido;

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

        crearMenu();
        panelContenido = new JPanel();

        panelContenido.setLayout(new java.awt.BorderLayout());

        setContentPane(panelContenido);
    }

    private void crearMenu() {

        //================== BARRA DE MENÚ ==================

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //================== PERFIL ==================

        JMenu menuPerfil = new JMenu("Perfil");
        menuBar.add(menuPerfil);

        JMenuItem itemCambiarPassword =
                new JMenuItem("Cambiar contraseña");

        menuPerfil.add(itemCambiarPassword);

        itemCambiarPassword.addActionListener(e -> {

            ChangePasswordForm form =
                    new ChangePasswordForm(this);

            form.setVisible(true);

        });

        JMenuItem itemCambiarUsuario =
                new JMenuItem("Cambiar usuario");

        menuPerfil.add(itemCambiarUsuario);

        itemCambiarUsuario.addActionListener(e -> {

            LoginForm login =
                    new LoginForm(this);

            login.setVisible(true);

        });

        JMenuItem itemSalir =
                new JMenuItem("Salir");

        menuPerfil.add(itemSalir);

        itemSalir.addActionListener(e -> System.exit(0));

        //================== DASHBOARD ==================

        JMenu menuDashboard =
                new JMenu("Dashboard");

        menuBar.add(menuDashboard);

        //================== INVENTARIO ==================

        JMenu menuInventario =
                new JMenu("Inventario");

        menuBar.add(menuInventario);

        JMenuItem itemMovimientoInventario =
                new JMenuItem("Movimiento de Inventario");

        menuInventario.add(itemMovimientoInventario);

        itemMovimientoInventario.addActionListener(e -> {

            panelContenido.removeAll();

            panelContenido.add(
                    new MovimientoInventarioForm(),
                    java.awt.BorderLayout.CENTER
            );

            panelContenido.revalidate();

            panelContenido.repaint();

        });

        //================== PRÉSTAMOS ==================

        JMenu menuPrestamos =
                new JMenu("Préstamos");

        menuBar.add(menuPrestamos);

        JMenuItem itemGestionPrestamos =
                new JMenuItem("Gestionar préstamos");

        menuPrestamos.add(itemGestionPrestamos);

        itemGestionPrestamos.addActionListener(e -> {

            panelContenido.removeAll();

            panelContenido.add(
                    new PrestamosForm(),
                    java.awt.BorderLayout.CENTER
            );

            panelContenido.revalidate();

            panelContenido.repaint();

        });

        //================== USUARIOS ==================

        JMenu menuUsuarios =
                new JMenu("Usuarios");

        menuBar.add(menuUsuarios);

        JMenuItem itemAdministrarUsuarios =
                new JMenuItem("Administrar usuarios");

        menuUsuarios.add(itemAdministrarUsuarios);

        // Evento al dar clic en "Administrar usuarios"
        itemAdministrarUsuarios.addActionListener(e -> {

            // 1. Instanciamos tu clase de gestión de usuarios
            GestionUsuariosVista vistaUsuarios = new GestionUsuariosVista();

            // 2. Ejecutamos su método para armar la tabla y eventos
            vistaUsuarios.inicializarTablaYComponentes();

            // 3. Limpiamos el contenedor del contenido blanco actual
            panelContenido.removeAll();

            // 4. Añadimos el componente principal de la vista (el que tiene el .form)
            panelContenido.add(vistaUsuarios.getMainPanel(), java.awt.BorderLayout.CENTER);

            // 5. Le decimos a Java que vuelva a dibujar la pantalla con el nuevo contenido
            panelContenido.revalidate();
            panelContenido.repaint();
        });

        JMenuItem itemRoles =
                new JMenuItem("Gestión de Roles");

        menuUsuarios.add(itemRoles);

        itemRoles.addActionListener(e -> {

            RolForm form = new RolForm(this);

            form.setVisible(true);

        });

        //================== NOTIFICACIONES ==================

        JMenu menuNotificaciones =
                new JMenu("Notificaciones");

        menuBar.add(menuNotificaciones);

    }
}
