package esfe.presentacion;

import esfe.dominio.User;

import javax.swing.*;

public class MainForm extends JFrame {

    // Usuario autenticado
    private User userAutenticado;

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

        JMenuItem itemRecursos =
                new JMenuItem("Recursos");

        menuInventario.add(itemRecursos);

        JMenuItem itemCategorias =
                new JMenuItem("Categorías");

        menuInventario.add(itemCategorias);

        JMenuItem itemTipos =
                new JMenuItem("Tipos de Recursos");

        menuInventario.add(itemTipos);

        //================== PRÉSTAMOS ==================

        JMenu menuPrestamos =
                new JMenu("Préstamos");

        menuBar.add(menuPrestamos);

        JMenuItem itemGestionPrestamos =
                new JMenuItem("Gestionar préstamos");

        menuPrestamos.add(itemGestionPrestamos);

        //================== USUARIOS ==================

        JMenu menuUsuarios =
                new JMenu("Usuarios");

        menuBar.add(menuUsuarios);

        JMenuItem itemAdministrarUsuarios =
                new JMenuItem("Administrar usuarios");

        menuUsuarios.add(itemAdministrarUsuarios);

        // =========================================================================
        // ÁREA MODIFICADA: LLAMADA SINCRONIZADA CON LA CLASE DE USUARIOS
        // =========================================================================
        itemAdministrarUsuarios.addActionListener(e -> {

            // 1. Instanciamos la vista limpia
            GestionUsuariosVista vistaUsuarios = new GestionUsuariosVista();

            // 2. Creamos la ventana flotante de forma segura referenciando la clase superior
            JDialog ventanaFlotante = new JDialog(MainForm.this, "Administrar Usuarios", true);

            // 3. Asignamos tu panel de diseño como contenido de la ventana
            ventanaFlotante.setContentPane(vistaUsuarios.getMainPanel());

            // 4. LÍNEA 145 CORREGIDA: Llamamos al nombre exacto del método sin argumentos
            vistaUsuarios.inicializarTablaYComponentes();

            // 5. Ajustes de tamaño ideales para la pantalla
            ventanaFlotante.setSize(950, 600);
            ventanaFlotante.setLocationRelativeTo(MainForm.this);
            ventanaFlotante.setResizable(true);

            // 6. Volvemos visible la ventana
            ventanaFlotante.setVisible(true);

        });

        //================== NOTIFICACIONES ==================

        JMenu menuNotificaciones =
                new JMenu("Notificaciones");

        menuBar.add(menuNotificaciones);

    }
}
