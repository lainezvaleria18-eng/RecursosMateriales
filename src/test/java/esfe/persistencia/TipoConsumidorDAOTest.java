
package esfe.persistencia;

import esfe.dominio.TipoConsumidor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TipoConsumidorDAOTest {

    @Test
    void guardar() {

        TipoConsumidor tipo = new TipoConsumidor();
        tipo.setNombreEspecialidad("Prueba");

        TipoConsumidorDAO dao = new TipoConsumidorDAO();

        int resultado = dao.guardar(tipo);

        assertEquals(1, resultado);
    }
}

