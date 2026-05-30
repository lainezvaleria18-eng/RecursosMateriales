
package esfe.persistencia;

import esfe.dominio.CategoriaMaterial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoriaMaterialDAOTest {

    @Test
    void guardar() {

        CategoriaMaterial categoria = new CategoriaMaterial();
        categoria.setNombreCategoria("Prueba");
        categoria.setDescripcion("Descripcion de prueba");

        CategoriaMaterialDAO dao = new CategoriaMaterialDAO();

        int resultado = dao.guardar(categoria);

        assertEquals(1, resultado);
    }
}