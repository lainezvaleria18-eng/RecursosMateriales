package esfe.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import esfe.dominio.RecuperacionPassword;

public class RecuperacionPasswordDAO {
    private ConnectionManager conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public RecuperacionPasswordDAO() {
        conn = ConnectionManager.getInstance();
    }


    public boolean create(RecuperacionPassword rp) throws SQLException {
        boolean res = false;
        try {
            ps = conn.connect().prepareStatement(
                    "INSERT INTO RecuperacionesPassword (IdUsuario, CodigoRecuperacion, FechaExpiracion, Usado) " +
                            "VALUES (?, ?, ?, 0)"
            );
            ps.setInt(1, rp.getIdUsuario());
            ps.setString(2, rp.getCodigoRecuperacion());
            ps.setTimestamp(3, new java.sql.Timestamp(rp.getFechaExpiracion().getTime()));

            if (ps.executeUpdate() > 0) {
                res = true;
            }
            if (ps != null) { ps.close(); }
        } catch (SQLException ex) {
            throw new SQLException("Error al registrar código de recuperación: " + ex.getMessage(), ex);
        } finally {
            if (ps != null) {
                try { ps.close(); } catch (SQLException e) {}
            }
            ps = null;
            conn.disconnect();
        }
        return res;
    }


    public boolean validarCodigo(String codigo) throws SQLException {
        boolean valido = false;
        try {
            ps = conn.connect().prepareStatement(
                    "SELECT IdRecuperacion FROM RecuperacionesPassword " +
                            "WHERE CodigoRecuperacion = ? AND Usado = 0 AND FechaExpiracion > GETDATE()"
            );
            ps.setString(1, codigo);
            rs = ps.executeQuery();

            if (rs.next()) {
                valido = true;
            }
            if (rs != null) { rs.close(); }
            if (ps != null) { ps.close(); }
        } catch (SQLException ex) {
            throw new SQLException("Error al validar código de recuperación: " + ex.getMessage(), ex);
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