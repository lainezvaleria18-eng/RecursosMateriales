package esfe.persistencia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import esfe.dominio.Prestamo;
import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
class PrestamoDAOTest {
    private PrestamoDAO prestamoDAO;
    private static int usuarioPruebaId;
    private static int recursoPruebaId;

    @BeforeEach
    void setUp() {
        prestamoDAO = new PrestamoDAO();
    }

    @Test
    void test1_Create() throws SQLException {
        Prestamo prestamo = new Prestamo();

        // Generamos IDs de prueba aleatorios para simular registros del sistema
        usuarioPruebaId = new Random().nextInt(500) + 1;
        recursoPruebaId = new Random().nextInt(500) + 1;

        prestamo.setIdUsuario(usuarioPruebaId);
        prestamo.setIdRecurso(recursoPruebaId);

        boolean resultado = prestamoDAO.create(prestamo);
        assertTrue(resultado, "El registro del préstamo debió realizarse con éxito en Somee.");
    }

    @Test
    void test2_BuscarPrestamoActivo() throws SQLException {
        if (usuarioPruebaId == 0 || recursoPruebaId == 0) {
            fail("El caso de prueba falló porque test1 no heredó las llaves primarias de control.");
        }

        boolean tienePrestamo = prestamoDAO.buscarPrestamoActivo(usuarioPruebaId, recursoPruebaId);
        assertTrue(tienePrestamo, "El préstamo activo debió ser localizado correctamente para los registros evaluados en Somee.");
    }
}