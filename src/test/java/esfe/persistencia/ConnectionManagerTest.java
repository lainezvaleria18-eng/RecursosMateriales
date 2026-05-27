package esfe.persistencia;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection; // Importa la clase Connection del paquete java.sql para gestionar el enlace.
import java.sql.SQLException; // Importa la clase SQLException para el manejo de errores de base de datos.

import static org.junit.jupiter.api.Assertions.*; // Importa los métodos de aserción como assertNotNull y assertFalse.

/**
 * Clase de prueba unitaria para validar la conexión por medio de JDBC
 * hacia la base de datos RecursosMaterialesDB alojada en Somee.com.
 * * Proyecto: RecursosMateriales
 */
class ConnectionManagerTest {

    // Referencia al gestor de conexión Singleton
    ConnectionManager connectionManager;

    @BeforeEach
    void setUp() throws SQLException {
        // Se ejecuta antes de cada método de prueba.
        // Inicializa el ConnectionManager usando su instancia única.
        connectionManager = ConnectionManager.getInstance();
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Se ejecuta después de cada método de prueba.
        // Cierra la conexión activa para no saturar los recursos remotos de Somee.
        if (connectionManager != null) {
            connectionManager.disconnect();
            connectionManager = null; // Limpieza de seguridad de la referencia.
        }
    }

    @Test
    void connect() throws SQLException {
        // Intenta establecer la comunicación con el servidor remoto en Somee.
        Connection conn = connectionManager.connect();

        // Realiza una aserción para verificar que la conexión establecida no sea nula.
        // Si el driver o las credenciales fallan, la prueba se detendrá aquí.
        assertNotNull(conn, "La conexión no debe ser nula");

        // Realiza una aserción para verificar que la conexión establecida esté abierta.
        // El método isClosed() devuelve true si está cerrada, por lo que assertFalse espera un false.
        assertFalse(conn.isClosed(), "La conexión debe estar abierta");

        // Cierre preventivo del flujo interno de la prueba.
        if (conn != null) {
            conn.close();
        }
    }
}