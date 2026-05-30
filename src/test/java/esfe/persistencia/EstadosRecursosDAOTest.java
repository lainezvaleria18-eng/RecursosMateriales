package esfe.persistencia;

import esfe.dominio.EstadosRecursos;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EstadosRecursosDAOTest {

    @Test
    void insertar() {

        EstadosRecursos nuevoEstado = new EstadosRecursos();
        nuevoEstado.setNombreEstado("Mantenimiento");


        EstadosRecursosDAO dao = new EstadosRecursosDAO();


        boolean resultado = dao.insertar(nuevoEstado);


        assertTrue(resultado, "No se pudo insertar el estado del recurso.");
    }

    @Test
    void obtenerTodos() {

        EstadosRecursosDAO dao = new EstadosRecursosDAO();


        List<EstadosRecursos> lista = dao.obtenerTodos();


        assertNotNull(lista, "La lista regresó como un valor nulo.");


        System.out.println("Estados encontrados en la BD: " + lista.size());
    }
}