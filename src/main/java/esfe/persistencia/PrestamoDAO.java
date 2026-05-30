package esfe.persistencia;

import esfe.dominio.Prestamo; // Importación obligatoria del dominio
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    private final Connection connection;

    public PrestamoDAO(Connection connection) {
        this.connection = connection;
    }

    // Guardar préstamo
    public void guardar(Prestamo prestamo) throws SQLException {
        String sql = "INSERT INTO prestamos (id, cliente, monto, fecha_inicio, activo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, prestamo.getId());
            statement.setString(2, prestamo.getCliente());
            statement.setDouble(3, prestamo.getMonto());
            statement.setDate(4, Date.valueOf(prestamo.getFechaInicio()));
            statement.setBoolean(5, prestamo.getActivo());
            statement.executeUpdate();
        }
    }

    // Buscar por ID
    public Prestamo buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM prestamos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearPrestamo(resultSet);
                }
            }
        }
        return null;
    }

    // Listar todos los registros
    public List<Prestamo> listarTodos() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                prestamos.add(mapearPrestamo(resultSet));
            }
        }
        return prestamos;
    }

    // Eliminar préstamo
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM prestamos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    // Mapeador auxiliar interno
    private Prestamo mapearPrestamo(ResultSet resultSet) throws SQLException {
        Prestamo prestamo = new Prestamo();
        prestamo.setId(resultSet.getLong("id"));
        prestamo.setCliente(resultSet.getString("cliente"));
        prestamo.setMonto(resultSet.getDouble("monto"));
        prestamo.setFechaInicio(resultSet.getDate("fecha_inicio").toLocalDate());
        prestamo.setActivo(resultSet.getBoolean("activo"));
        return prestamo;
    }
}
