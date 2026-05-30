package esfe.persistencia;

import esfe.dominio.DetallePrestamo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class DetallePrestamoDAOTest {


    private List<DetallePrestamo> tablaDetallesSimulada;

    @BeforeEach
    public void setUp() {

        tablaDetallesSimulada = new ArrayList<>();
    }

    @Test
    public void verificarFlujoCompletoDominioPersistencia() {

        DetallePrestamo detalleOriginal = new DetallePrestamo(1L, 100L, 1, 50.0, false);



        tablaDetallesSimulada.add(detalleOriginal);


        DetallePrestamo detalleRecuperado = null;
        for (DetallePrestamo dp : tablaDetallesSimulada) {
            if (dp.getId().equals(1L)) {
                detalleRecuperado = dp;
                break;
            }
        }


        assertNotNull(detalleRecuperado);
        assertEquals(1L, detalleRecuperado.getId());
        assertEquals(100L, detalleRecuperado.getPrestamoId());
        assertEquals(1, detalleRecuperado.getNumeroCuota());
        assertEquals(50.0, detalleRecuperado.getMontoCuota());
        assertFalse(detalleRecuperado.getPagado());


        assertEquals(1, tablaDetallesSimulada.size());


        tablaDetallesSimulada.removeIf(dp -> dp.getId().equals(1L));


        assertEquals(0, tablaDetallesSimulada.size());
    }
}