package esfe.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import esfe.dominio.DetallePrestamo;

public class DetallePrestamoDAO {
    private ConnectionManager conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public DetallePrestamoDAO() {
        conn = ConnectionManager.getInstance();
    }

    public boolean create(DetallePrestamo dp) throws SQLException {
        boolean res = false;
        try {
            ps = conn.connect().prepareStatement(
                    "INSERT INTO DetallesPrestamos (IdPrestamo, IdConsumible, Cantidad, Observaciones) " +
                            "VALUES (?, ?, ?, ?)"
            );
            ps.setInt(1, dp.getIdPrestamo());
            ps.setInt(2, dp.getIdConsumible());
            ps.setInt(3, dp.getCantidad());
            ps.setString(4, dp.getObservaciones());

            if (ps.executeUpdate() > 0) {
                res = true;
            }
            if (ps != null) { ps.close(); }
        } catch (SQLException ex) {
            throw new SQLException("Error al registrar el detalle del préstamo: " + ex.getMessage(), ex);
        } finally {
            if (ps != null) {
                try { ps.close(); } catch (SQLException e) {}
            }
            ps = null;
            conn.disconnect();
        }
        return res;
    }

    public boolean validarCantidadDetalle(int idPrestamo, int cantidadMinima) throws SQLException {
        boolean valido = false;
        try {
            ps = conn.connect().prepareStatement(
                    "SELECT IdDetallePrestamo FROM DetallesPrestamos " +
                            "WHERE IdPrestamo = ? AND Cantidad >= ?"
            );
            ps.setInt(1, idPrestamo);
            ps.setInt(2, cantidadMinima);
            rs = ps.executeQuery();

            if (rs.next()) {
                valido = true;
            }
            if (rs != null) { rs.close(); }
            if (ps != null) { ps.close(); }
        } catch (SQLException ex) {
            throw new SQLException("Error al validar la cantidad del detalle: " + ex.getMessage(), ex);
        } finally {
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} }
            if (ps != null) { try { ps.close(); } catch (SQLException e) {} }
            rs = null;
            ps = null;
            conn.disconnect();
        }
        return valido;
    }
}
