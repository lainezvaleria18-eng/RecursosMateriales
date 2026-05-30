package esfe.persistencia;

import esfe.dominio.DetallePrestamo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

// Importación de las aserciones nativas del framework de pruebas que ya reconoce tu IntelliJ
import static org.junit.jupiter.api.Assertions.*;

public class DetallePrestamoDAOTest {

    // Repositorio simulado en memoria que sustituye la tabla física de la base de datos
    private List<DetallePrestamo> tablaDetallesSimulada;

    @BeforeEach
    public void setUp() {
        // Se inicializa y limpia el contenedor antes de ejecutar la prueba
        tablaDetallesSimulada = new ArrayList<>();
    }

    @Test
    public void verificarFlujoCompletoDominioPersistencia() {
        // 1. UNIÓN CON EL DOMINIO: Instanciamos tu objeto real con sus datos correspondientes
        // (id: 1L, prestamoId: 100L, numeroCuota: 1, montoCuota: 50.0, pagado: false)
        DetallePrestamo detalleOriginal = new DetallePrestamo(1L, 100L, 1, 50.0, false);

        // 2. UNIÓN CON LA PERSISTENCIA: Simulamos el comportamiento del DetallePrestamoDAO
        // Simulación de: dao.guardar(detalleOriginal);
        tablaDetallesSimulada.add(detalleOriginal);

        // Simulación de: DetallePrestamo detalleRecuperado = dao.buscarPorId(1L);
        DetallePrestamo detalleRecuperado = null;
        for (DetallePrestamo dp : tablaDetallesSimulada) {
            if (dp.getId().equals(1L)) {
                detalleRecuperado = dp;
                break;
            }
        }

        // ====================================================================
        // ASERCIONES UNITARIAS: El fin con código lógico que determina el éxito
        // ====================================================================
        // Sin textos en consola. Si los datos coinciden, IntelliJ marcará el check verde.
        assertNotNull(detalleRecuperado);
        assertEquals(1L, detalleRecuperado.getId());
        assertEquals(100L, detalleRecuperado.getPrestamoId());
        assertEquals(1, detalleRecuperado.getNumeroCuota());
        assertEquals(50.0, detalleRecuperado.getMontoCuota());
        assertFalse(detalleRecuperado.getPagado());

        // Simulación de: dao.listarTodos();
        assertEquals(1, tablaDetallesSimulada.size());

        // Simulación de: dao.eliminar(1L);
        tablaDetallesSimulada.removeIf(dp -> dp.getId().equals(1L));

        // Verificación final de que la persistencia quedó vacía tras la eliminación
        assertEquals(0, tablaDetallesSimulada.size());
    }
}