package esfe.persistencia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import esfe.dominio.RecuperacionPassword;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

    class RecuperacionPasswordDAOTest {
        private RecuperacionPasswordDAO recuperacionDAO;
        private static String codigoGenerado; // Guardamos el código para usarlo entre pruebas

        @BeforeEach
        void setUp() {

            recuperacionDAO = new RecuperacionPasswordDAO();
        }

        @Test
        void test1_Create() throws SQLException {
            // 1. Preparar los datos tal como exige la arquitectura
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, 2); // El código expira en 2 horas

            RecuperacionPassword rp = new RecuperacionPassword();
            rp.setIdUsuario(1); // ID 1 de Carlos Martínez (Insertado por defecto en tu script)

            // Generamos un código único aleatorio (Ejemplo: REC-4829)
            codigoGenerado = "REC-" + (new Random().nextInt(9000) + 1000);
            rp.setCodigoRecuperacion(codigoGenerado);
            rp.setFechaExpiracion(cal.getTime());

            // 2. Ejecutar el método del DAO
            boolean resultado = recuperacionDAO.create(rp);

            // 3. Validar el resultado con JUnit
            assertTrue(resultado, "La solicitud de recuperación debió registrarse con éxito en Somee.");
        }

        @Test
        void test2_ValidarCodigo() throws SQLException {
            // Aseguramos que exista un código antes de validar
            if (codigoGenerado == null) {
                codigoGenerado = "REC-0000";
            }

            // 1. Ejecutar el método de lectura/validación
            boolean esValido = recuperacionDAO.validarCodigo(codigoGenerado);

            // 2. Validar que el código guardado esté activo y vigente en Somee
            assertTrue(esValido, "El código generado debe ser detectado como válido y no usado en Somee.");
        }
    }