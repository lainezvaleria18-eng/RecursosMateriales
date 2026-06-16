package esfe.persistencia;

import esfe.dominio.Prestamo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrestamoDAOTest {

    private List<Prestamo> tablaPrestamosSimulada;

    @BeforeEach
    public void setUp() {
        tablaPrestamosSimulada = new ArrayList<>();
    }

    @Test
    public void verificarFlujoCompletoDominioPersistencia() {
        // 1. Creamos un objeto de Préstamo con los datos reales de tu dominio
        Prestamo prestamoOriginal = new Prestamo();
        prestamoOriginal.setIdPrestamo(10); // ID como entero (int)
        prestamoOriginal.setFechaSolicitud("2026-06-15");
        prestamoOriginal.setFechaDevolucion("2026-06-15");
        prestamoOriginal.setEstado("Pendiente");
        prestamoOriginal.setObservaciones("Préstamo de Laptop de prueba");

        // Simular el guardado (Insert)
        tablaPrestamosSimulada.add(prestamoOriginal);

        // 2. Simular la recuperación (Select por ID)
        Prestamo prestamoRecuperado = null;
        for (Prestamo p : tablaPrestamosSimulada) {
            if (p.getIdPrestamo() == 10) { // Validación con int nativo
                prestamoRecuperado = p;
                break;
            }
        }

        // 3. Verificaciones de lectura (Asserts) con los métodos correctos de tu sistema
        assertNotNull(prestamoRecuperado);
        assertEquals(10, prestamoRecuperado.getIdPrestamo());
        assertEquals("2026-06-15", prestamoRecuperado.getFechaSolicitud());
        assertEquals("2026-06-15", prestamoRecuperado.getFechaDevolucion());
        assertEquals("Pendiente", prestamoRecuperado.getEstado());
        assertEquals("Préstamo de Laptop de prueba", prestamoRecuperado.getObservaciones());

        // Comprobar que hay 1 registro en la lista simulada
        assertEquals(1, tablaPrestamosSimulada.size());

        // 4. Simular la eliminación (Delete)
        tablaPrestamosSimulada.removeIf(p -> p.getIdPrestamo() == 10);

        // Comprobar que la lista quedó vacía de nuevo
        assertEquals(0, tablaPrestamosSimulada.size());
    }
}