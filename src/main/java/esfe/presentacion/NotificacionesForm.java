package esfe.presentacion;

import javax.swing.*;
import java.awt.*;

public class NotificacionesForm extends JPanel {

    private JPanel panelLista;
    private JScrollPane panelNotificaciones;
    private JLabel contenerNotificaciones;
    private JPanel Jpanel;
    private JPanel panelPrincipal;
    private JPanel panel1;

    public NotificacionesForm() {

        setLayout(new BorderLayout(10, 10));


        Color azulCorporativo = new Color(0, 51, 102);
        setBackground(azulCorporativo);



        JPanel superior = new JPanel(new BorderLayout());
        superior.setOpaque(false);

        JLabel titulo = new JLabel("Notificaciones");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE); // Texto Blanco para resaltar sobre el azul

        superior.add(titulo, BorderLayout.WEST);

        add(superior, BorderLayout.NORTH);



        panelLista = new JPanel();
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
        panelLista.setBackground(azulCorporativo);

        JScrollPane scrollPane = new JScrollPane(panelLista);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(azulCorporativo);

        add(scrollPane, BorderLayout.CENTER);

        cargarNotificaciones();
    }

    private void cargarNotificaciones() {

        try {

            esfe.persistencia.NotificacionesDAO dao =
                    new esfe.persistencia.NotificacionesDAO();

            java.util.List<esfe.dominio.Notificaciones> lista =
                    dao.obtenerTodos();

            panelLista.removeAll();

            for (esfe.dominio.Notificaciones n : lista) {



                JPanel tarjeta = new JPanel(new BorderLayout(0, 5));

                tarjeta.setPreferredSize(
                        new Dimension(700, 70)
                );

                tarjeta.setMaximumSize(
                        new Dimension(700, 70)
                );

                tarjeta.setBackground(Color.WHITE);

                tarjeta.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(
                                        new Color(210, 210, 210),
                                        1,
                                        true
                                ),
                                BorderFactory.createEmptyBorder(
                                        10, 15, 10, 15
                                )
                        )
                );



                JPanel contenido = new JPanel(
                        new BorderLayout(10, 0)
                );

                contenido.setOpaque(false);


                JLabel estado = new JLabel("●");
                estado.setFont(
                        new Font("Arial", Font.BOLD, 18)
                );

                estado.setForeground(
                        new Color(52, 152, 219)
                );


                JLabel mensaje = new JLabel(
                        n.getMensaje()
                );

                mensaje.setFont(
                        new Font("Segoe UI", Font.BOLD, 14)
                );

                contenido.add(
                        estado,
                        BorderLayout.WEST
                );

                contenido.add(
                        mensaje,
                        BorderLayout.CENTER
                );


                String fechaTexto = "";

                if (n.getFecha() != null) {
                    fechaTexto = tiempoRelativo(n.getFecha());
                }
                JLabel fecha = new JLabel(fechaTexto);

                fecha.setFont(
                        new Font("Segoe UI", Font.PLAIN, 11)
                );

                fecha.setForeground(
                        new Color(120, 120, 120)
                );

                tarjeta.add(
                        contenido,
                        BorderLayout.CENTER
                );

                tarjeta.add(
                        fecha,
                        BorderLayout.SOUTH
                );



                panelLista.add(
                        Box.createVerticalStrut(10)
                );

                JPanel fila = new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

                fila.setOpaque(false);

                fila.add(tarjeta);

                panelLista.add(fila);
            }

            panelLista.revalidate();
            panelLista.repaint();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar notificaciones: "
                            + e.getMessage()
            );
        }
    }

    private String tiempoRelativo(java.util.Date fecha) {

        if (fecha == null) {
            return "";
        }

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm a");

        return sdf.format(fecha);
    }

    private void createUIComponents() {

    }
}