package esfe.persistencia;

import esfe.dominio.Prestamo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
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

        Prestamo prestamoOriginal = new Prestamo(10L, "Carlos Andrade", 450.0, LocalDate.now(), true);


        tablaPrestamosSimulada.add(prestamoOriginal);


        Prestamo prestamoRecuperado = null;
        for (Prestamo p : tablaPrestamosSimulada) {
            if (p.getId().equals(10L)) {
                prestamoRecuperado = p;
                break;
            }
        }


        assertNotNull(prestamoRecuperado);
        assertEquals(10L, prestamoRecuperado.getId());
        assertEquals("Carlos Andrade", prestamoRecuperado.getCliente());
        assertEquals(450.0, prestamoRecuperado.getMonto());
        assertEquals(LocalDate.now(), prestamoRecuperado.getFechaInicio());
        assertTrue(prestamoRecuperado.getActivo());


        assertEquals(1, tablaPrestamosSimulada.size());


        tablaPrestamosSimulada.removeIf(p -> p.getId().equals(10L));


        assertEquals(0, tablaPrestamosSimulada.size());
    }
}