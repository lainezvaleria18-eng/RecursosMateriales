package esfe.persistencia;

import esfe.dominio.Mantenimiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MantenimientoDAOTest {

    private MantenimientoDAO mantenimientoDAO;

    @BeforeEach
    void setUp() throws Exception {

        mantenimientoDAO =
                new MantenimientoDAO();
    }

    @Test
    public void guardarMantenimientoTest()
            throws Exception {

        Mantenimiento mantenimiento =
                new Mantenimiento();

        mantenimiento.setIdRecurso(1);
        mantenimiento.setFecha(new Date());
        mantenimiento.setTecnico("Josue Garay");
        mantenimiento.setObservacion(
                "Mantenimiento preventivo");
        mantenimiento.setEstadoMantenimiento(
                "Terminado");

        int resultado =
                mantenimientoDAO.guardar(
                        mantenimiento);

        System.out.println(
                "Mantenimiento guardado correctamente.");

        System.out.println(
                "Filas afectadas: "
                        + resultado);

        System.out.println(
                "Registro insertado en la tabla Mantenimientos.");

        assertTrue(
                resultado > 0,
                "El mantenimiento debería guardarse correctamente.");
    }
}