package esfe.persistencia;

import esfe.dominio.Notificaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificacionesDAO {

    public int guardar(Notificaciones notificacion) throws Exception {

        int resultado = 0;

        try (Connection conn = ConnectionManager.getInstance().connect()) {

            String sql = """
                    INSERT INTO Notificaciones
                    (Mensaje, Fecha, Leida, IdUsuario)
                    VALUES (?, ?, ?, ?)
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, notificacion.getMensaje());

            if (notificacion.getFecha() != null) {
                ps.setTimestamp(2,
                        new java.sql.Timestamp(
                                notificacion.getFecha().getTime()));
            } else {
                ps.setTimestamp(2,
                        new java.sql.Timestamp(
                                System.currentTimeMillis()));
            }

            ps.setBoolean(3, notificacion.isLeida());
            ps.setInt(4, notificacion.getIdUsuario());

            resultado = ps.executeUpdate();

            System.out.println("Notificación guardada correctamente.");
            System.out.println("Filas afectadas: " + resultado);
            System.out.println("Registro insertado en la tabla Notificaciones.");

        } catch (SQLException ex) {

            throw new Exception(
                    "Error al guardar notificación: "
                            + ex.getMessage());
        }

        return resultado;
    }

    public List<Notificaciones> obtenerTodos() {

        List<Notificaciones> lista = new ArrayList<>();

        String sql = """
                SELECT IdNotificacion,
                       Mensaje,
                       Fecha,
                       Leida,
                       IdUsuario
                FROM Notificaciones
                ORDER BY Fecha DESC
                """;

        try (Connection conn = ConnectionManager.getInstance().connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Notificaciones notificacion = new Notificaciones();

                notificacion.setIdNotificacion(
                        rs.getInt("IdNotificacion"));

                notificacion.setMensaje(
                        rs.getString("Mensaje"));

                notificacion.setFecha(
                        rs.getTimestamp("Fecha"));

                notificacion.setLeida(
                        rs.getBoolean("Leida"));

                notificacion.setIdUsuario(
                        rs.getInt("IdUsuario"));

                lista.add(notificacion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}