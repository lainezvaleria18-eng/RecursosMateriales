package esfe.persistencia;

import esfe.dominio.Mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MantenimientoDAO {

    private Connection conn;

    public MantenimientoDAO() throws Exception {
        this.conn = ConnectionManager.getInstance().connect();
    }

    public int guardar(Mantenimiento mantenimiento) throws Exception {

        int resultado = 0;

        String sql = """
                INSERT INTO Mantenimientos
                (IdRecurso, Fecha, Tecnico,
                 Observacion, EstadoMantenimiento)
                VALUES (?, ?, ?, ?, ?)
                """;

        try {

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1,
                    mantenimiento.getIdRecurso());

            if (mantenimiento.getFecha() != null) {

                ps.setTimestamp(
                        2,
                        new java.sql.Timestamp(
                                mantenimiento.getFecha().getTime()));
            }
            else {

                ps.setTimestamp(
                        2,
                        new java.sql.Timestamp(
                                System.currentTimeMillis()));
            }

            ps.setString(
                    3,
                    mantenimiento.getTecnico());

            ps.setString(
                    4,
                    mantenimiento.getObservacion());

            ps.setString(
                    5,
                    mantenimiento.getEstadoMantenimiento());

            resultado = ps.executeUpdate();

        }
        catch (SQLException ex) {

            throw new Exception(
                    "Error al guardar mantenimiento: "
                            + ex.getMessage());
        }

        return resultado;
    }
}