package esfe.persistencia;

import esfe.dominio.DetallePrestamo; // Importación obligatoria del dominio
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetallePrestamoDAO {
    private final Connection connection;

    public DetallePrestamoDAO(Connection connection) {
        this.connection = connection;
    }

    // Guardar detalle de préstamo
    public void guardar(DetallePrestamo detalle) throws SQLException {
        String sql = "INSERT INTO detalles_prestamos (id, prestamo_id, numero_cuota, monto_cuota, pagado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, detalle.getId());
            statement.setLong(2, detalle.getPrestamoId());
            statement.setInt(3, detalle.getNumeroCuota());
            statement.setDouble(4, detalle.getMontoCuota());
            statement.setBoolean(5, detalle.getPagado());
            statement.executeUpdate();
        }
    }

    // Buscar por ID
    public DetallePrestamo buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM detalles_prestamos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearDetalle(resultSet);
                }
            }
        }
        return null;
    }

    // Listar todos los detalles de un préstamo específico
    public List<DetallePrestamo> listarPorPrestamo(Long prestamoId) throws SQLException {
        List<DetallePrestamo> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalles_prestamos WHERE prestamo_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, prestamoId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    detalles.add(mapearDetalle(resultSet));
                }
            }
        }
        return detalles;
    }

    // Eliminar detalle
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM detalles_prestamos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    // Helper interno para mapear ResultSet a Objeto
    private DetallePrestamo mapearDetalle(ResultSet resultSet) throws SQLException {
        DetallePrestamo detalle = new DetallePrestamo();
        detalle.setId(resultSet.getLong("id"));
        detalle.setPrestamoId(resultSet.getLong("prestamo_id"));
        detalle.setNumeroCuota(resultSet.getInt("numero_cuota"));
        detalle.setMontoCuota(resultSet.getDouble("monto_cuota"));
        detalle.setPagado(resultSet.getBoolean("pagado"));
        return detalle;
    }
}
