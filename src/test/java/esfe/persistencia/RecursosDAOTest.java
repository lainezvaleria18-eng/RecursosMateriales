package esfe.persistencia;

import esfe.dominio.Recursos;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RecursosDAOTest {

    @Test
    void guardar() {

        Recursos nuevoRecurso = new Recursos();
        nuevoRecurso.setCodigoRecurso("REQ-2026-011");
        nuevoRecurso.setNombreRecurso("Laptop Dell Latitude");
        nuevoRecurso.setMarca("Dell");
        nuevoRecurso.setModelo("Latitude 3440");
        nuevoRecurso.setNumeroSerie("DELL-987654");
        nuevoRecurso.setUbicacion("Laboratorio 3");
        nuevoRecurso.setUnidadMedida("Unidad");
        nuevoRecurso.setStock(10);
        nuevoRecurso.setPrecio(new BigDecimal("850.00"));
        nuevoRecurso.setDescripcion("Equipo asignado para desarrollo");


        nuevoRecurso.setIdCategoria(1);
        nuevoRecurso.setIdTipoRecurso(1);
        nuevoRecurso.setIdEstadoRecurso(1);


        RecursosDAO dao = new RecursosDAO();


        boolean resultado = dao.guardar(nuevoRecurso);


        assertTrue(resultado, "El recurso no se pudo guardar correctamente en la base de datos.");
    }

    @Test
    void obtenerTodos() {

        RecursosDAO dao = new RecursosDAO();


        List<Recursos> lista = dao.obtenerTodos();


        assertNotNull(lista, "La lista retornada es nula.");
        System.out.println("Cantidad de recursos encontrados en la BD: " + lista.size());
    }
}