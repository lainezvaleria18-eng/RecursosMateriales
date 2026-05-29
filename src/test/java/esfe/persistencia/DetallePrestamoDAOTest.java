package esfe.persistencia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import esfe.dominio.DetallePrestamo;
import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
class DetallePrestamoDAOTest {
    private DetallePrestamoDAO detalleDAO;
    private static int prestamoIdCompartido;
    private static final int CANTIDAD_PRUEBA = 3;

    @BeforeEach
    void setUp() {
        detalleDAO = new DetallePrestamoDAO();
    }

    @Test
    void test1_Create() throws SQLException {
        DetallePrestamo dp = new DetallePrestamo();

        // Simulación de llaves primarias externas usando valores aleatorios de control
        prestamoIdCompartido = new Random().nextInt(500) + 1;
        int consumibleId = new Random().nextInt(500) + 1;

        dp.setIdPrestamo(prestamoIdCompartido);
        dp.setIdConsumible(consumibleId);
        dp.setCantidad(CANTIDAD_PRUEBA);
        dp.setObservaciones("Entregado en óptimas condiciones académicas.");

        boolean resultado = detalleDAO.create(dp);
        assertTrue(resultado, "El registro del detalle del préstamo debió realizarse con éxito en Somee.");
    }

    @Test
    void test2_ValidarCantidadDetalle() throws SQLException {
        if (prestamoIdCompartido == 0) {
            fail("El caso de prueba falló porque test1 no heredó el ID de préstamo para la secuencia.");
        }

        // Validamos que se encuentre el registro con al menos la cantidad ingresada
        boolean esValido = detalleDAO.validarCantidadDetalle(prestamoIdCompartido, CANTIDAD_PRUEBA);
        assertTrue(esValido, "El detalle de la cantidad debe ser detectado y validado correctamente en Somee.");
    }
}
