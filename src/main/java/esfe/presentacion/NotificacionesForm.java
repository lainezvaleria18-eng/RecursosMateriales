package esfe.presentacion;

import javax.swing.*;
import java.awt.*;

public class NotificacionesForm extends JPanel {

    private JPanel panelLista;
    private JScrollPane panelNotificaciones;
    private JLabel contenerNotificaciones;
    private JPanel panelPrincipal;
    private JPanel panel1;

    public NotificacionesForm() {

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));

        //================ PANEL SUPERIOR ================

        JPanel superior = new JPanel(new BorderLayout());
        superior.setOpaque(false);

        JLabel titulo = new JLabel("Notificaciones");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));

        superior.add(titulo, BorderLayout.WEST);

        add(superior, BorderLayout.NORTH);

        //================ PANEL CENTRAL ================

        panelLista = new JPanel();
        panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
        panelLista.setBackground(new Color(245, 245, 245));

        JScrollPane scrollPane = new JScrollPane(panelLista);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(245, 245, 245));

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

                //================ TARJETA =================

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

                //================ CONTENIDO =================

                JPanel contenido = new JPanel(
                        new BorderLayout(10, 0)
                );

                contenido.setOpaque(false);

                // Círculo de estado
                JLabel estado = new JLabel("●");
                estado.setFont(
                        new Font("Arial", Font.BOLD, 18)
                );

                estado.setForeground(
                        new Color(52, 152, 219)
                );

                // Mensaje
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

                // Fecha
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

                //================ CENTRAR TARJETA =================

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
}
