package esfe.persistencia;

import esfe.dominio.MovimientoInventario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovimientoInventarioDAOTest {

    private MovimientoInventarioDAO movimientoDAO;

    @BeforeEach
    void setUp() throws Exception {
        movimientoDAO = new MovimientoInventarioDAO();
    }

    @Test
    public void guardarMovimientoTest() throws Exception {

        MovimientoInventario movimiento =
                new MovimientoInventario();

        movimiento.setIdTipoMovimiento(1);
        movimiento.setCantidad(5);
        movimiento.setFechaMovimiento(new Date());
        movimiento.setObservacion("Entrada inicial desde Test");
        movimiento.setIdRecurso(1);
        movimiento.setIdUsuario(1);

        int resultado = movimientoDAO.guardar(movimiento);

        System.out.println("Movimiento guardado correctamente.");
        System.out.println("Filas afectadas: " + resultado);
        System.out.println("Movimiento registrado en la tabla MovimientosInventario.");

        assertTrue(resultado > 0,
                "El movimiento debería guardarse exitosamente en la base de datos.");
    }
}