package esfe.persistencia;

import esfe.dominio.CategoriaMaterial;

public class CategoriaMaterialDAOTest {

    public static void main(String[] args) {

        CategoriaMaterial categoria = new CategoriaMaterial();

        categoria.setNombreCategoria("Mouse");

        categoria.setDescripcion("Mouse inalámbrico");

        CategoriaMaterialDAO dao = new CategoriaMaterialDAO();

        int resultado = dao.guardar(categoria);

        if (resultado > 0) {

            System.out.println("Guardado correctamente");

        } else {

            System.out.println("Error al guardar");
        }
    }
}