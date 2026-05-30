package esfe.persistencia;

import esfe.dominio.Rol;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RolDAOTest {

    @Test
    void guardar() {

        Rol rol = new Rol();
        rol.setNombreRol("RolPrueba");

        RolDAO dao = new RolDAO();

        int resultado = dao.guardar(rol);

        assertEquals(1, resultado);
    }
}
