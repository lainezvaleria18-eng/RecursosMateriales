package esfe.persistencia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import esfe.dominio.RecuperacionPassword;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
class RecuperacionPasswordDAOTest {
    private RecuperacionPasswordDAO recuperacionDAO;
    private static String codigoGenerado;

    @BeforeEach
    void setUp() {
        recuperacionDAO = new RecuperacionPasswordDAO();
    }

    @Test
    void test1_Create() throws SQLException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 2);

        RecuperacionPassword rp = new RecuperacionPassword();
        rp.setIdUsuario(1);


        codigoGenerado = "REC-" + (new Random().nextInt(9000) + 1000);
        rp.setCodigoRecuperacion(codigoGenerado);
        rp.setFechaExpiracion(cal.getTime());

        boolean resultado = recuperacionDAO.create(rp);
        assertTrue(resultado, "La solicitud de recuperación debió registrarse con éxito en Somee.");
    }

    @Test
    void test2_ValidarCodigo() throws SQLException {

        if (codigoGenerado == null) {
            fail("El caso de prueba falló porque test1 no heredó el código a test2.");
        }


        boolean esValido = recuperacionDAO.validarCodigo(codigoGenerado);

        assertTrue(esValido, "El código generado debe ser detectado como válido y no usado en Somee.");
    }
}