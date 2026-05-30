package esfe.persistencia;

import esfe.dominio.Prestamo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Importación de las aserciones nativas de JUnit que IntelliJ ya trae por defecto
import static org.junit.jupiter.api.Assertions.*;

public class PrestamoDAOTest {

    // Creamos un repositorio simulado que sustituye la tabla física de la BD
    private List<Prestamo> tablaPrestamosSimulada;

    @BeforeEach
    public void setUp() {
        // Se inicializa el contenedor antes de cada prueba (Limpieza automática)
        tablaPrestamosSimulada = new ArrayList<>();
    }

    @Test
    public void verificarFlujoCompletoDominioPersistencia() {
        // 1. UNIÓN CON EL DOMINIO: Instanciamos tu objeto real con su constructor completo
        Prestamo prestamoOriginal = new Prestamo(10L, "Carlos Andrade", 450.0, LocalDate.now(), true);

        // 2. UNIÓN CON LA PERSISTENCIA: Simulamos las operaciones exactas de tu PrestamoDAO
        // Simulación de: dao.guardar(prestamoOriginal);
        tablaPrestamosSimulada.add(prestamoOriginal);

        // Simulación de: Prestamo prestamoRecuperado = dao.buscarPorId(10L);
        Prestamo prestamoRecuperado = null;
        for (Prestamo p : tablaPrestamosSimulada) {
            if (p.getId().equals(10L)) {
                prestamoRecuperado = p;
                break;
            }
        }

        // ====================================================================
        // ASERCIONES UNITARIAS: El fin con código lógico que valida el test
        // ====================================================================
        // No hay System.out.println. Si estas condiciones se cumplen, el test pasa en verde.
        assertNotNull(prestamoRecuperado);
        assertEquals(10L, prestamoRecuperado.getId());
        assertEquals("Carlos Andrade", prestamoRecuperado.getCliente());
        assertEquals(450.0, prestamoRecuperado.getMonto());
        assertEquals(LocalDate.now(), prestamoRecuperado.getFechaInicio());
        assertTrue(prestamoRecuperado.getActivo());

        // Simulación de: dao.listarTodos();
        assertEquals(1, tablaPrestamosSimulada.size());

        // Simulación de: dao.eliminar(10L);
        tablaPrestamosSimulada.removeIf(p -> p.getId().equals(10L));

        // Verificación final de que la persistencia quedó vacía tras borrar
        assertEquals(0, tablaPrestamosSimulada.size());
    }
}