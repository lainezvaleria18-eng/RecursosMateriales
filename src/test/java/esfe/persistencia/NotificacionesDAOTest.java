package esfe.persistencia;

import esfe.dominio.Notificaciones;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotificacionesDAOTest {

    private NotificacionesDAO notificacionDAO;

    @BeforeEach
    void setUp() {
        notificacionDAO = new NotificacionesDAO();
    }

    @Test
    public void guardarNotificacionTest() throws Exception {

        Notificaciones notificacion = new Notificaciones();

        notificacion.setMensaje("Prueba de notificación desde JUnit");
        notificacion.setFecha(new Date());
        notificacion.setLeida(false);
        notificacion.setIdUsuario(1);

        int resultado = notificacionDAO.guardar(notificacion);

        assertTrue(resultado > 0,
                "La notificación debería guardarse correctamente.");
    }
}