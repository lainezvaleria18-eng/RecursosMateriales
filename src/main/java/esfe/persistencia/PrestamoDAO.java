package esfe.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import esfe.dominio.Prestamo;

public class PrestamoDAO {
    private ConnectionManager conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public PrestamoDAO() {
        conn = ConnectionManager.getInstance();
    }

    public boolean create(Prestamo prestamo) throws SQLException {
        boolean res = false;
        try {
            ps = conn.connect().prepareStatement(
                    "INSERT INTO Prestamos (IdUsuario, IdRecurso, FechaPrestamo, Estado) " +
                            "VALUES (?, ?, GETDATE(), 'Activo')"
            );
            ps.setInt(1, prestamo.getIdUsuario());
            ps.setInt(2, prestamo.getIdRecurso());

            if (ps.executeUpdate() > 0) {
                res = true;
            }
            if (ps != null) { ps.close(); }
        } catch (SQLException ex) {
            throw new SQLException("Error al registrar el préstamo: " + ex.getMessage(), ex);
        } finally {
            if (ps != null) {
                try { ps.close(); } catch (SQLException e) {}
            }
            ps = null;
            conn.disconnect();
        }
        return res;
    }

    public boolean buscarPrestamoActivo(int idUsuario, int idRecurso) throws SQLException {
        boolean activo = false;
        try {
            ps = conn.connect().prepareStatement(
                    "SELECT IdPrestamo FROM Prestamos " +
                            "WHERE IdUsuario = ? AND IdRecurso = ? AND Estado = 'Activo'"
            );
            ps.setInt(1, idUsuario);
            ps.setInt(2, idRecurso);
            rs = ps.executeQuery();

            if (rs.next()) {
                activo = true;
            }
            if (rs != null) { rs.close(); }
            if (ps != null) { ps.close(); }
        } catch (SQLException ex) {
            throw new SQLException("Error al validar existencia de préstamo activo: " + ex.getMessage(), ex);
        } finally {
            if (rs != null) { try { rs.close(); } catch (SQLException e) {} }
            if (ps != null) { try { ps.close(); } catch (SQLException e) {} }
            rs = null;
            ps = null;
            conn.disconnect();
        }
        return activo;
    }
}
