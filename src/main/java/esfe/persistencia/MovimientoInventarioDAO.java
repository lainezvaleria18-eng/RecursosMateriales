package esfe.persistencia;

import esfe.dominio.MovimientoInventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovimientoInventarioDAO {

    private Connection conn;

    public MovimientoInventarioDAO() throws Exception {
        this.conn = ConnectionManager.getInstance().connect();
    }

    public int guardar(MovimientoInventario movimiento) throws Exception {

        int resultado = 0;

        String sql = """
                INSERT INTO MovimientosInventario
                (IdTipoMovimiento, Cantidad, FechaMovimiento,
                Observacion, IdRecurso, IdUsuario)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try {

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, movimiento.getIdTipoMovimiento());
            ps.setInt(2, movimiento.getCantidad());

            if (movimiento.getFechaMovimiento() != null) {
                ps.setTimestamp(3,
                        new java.sql.Timestamp(
                                movimiento.getFechaMovimiento().getTime()));
            } else {
                ps.setTimestamp(3,
                        new java.sql.Timestamp(System.currentTimeMillis()));
            }

            ps.setString(4, movimiento.getObservacion());
            ps.setInt(5, movimiento.getIdRecurso());
            ps.setInt(6, movimiento.getIdUsuario());

            resultado = ps.executeUpdate();

        } catch (SQLException ex) {

            throw new Exception("Error al guardar movimiento: "
                    + ex.getMessage());
        }

        return resultado;
    }
}