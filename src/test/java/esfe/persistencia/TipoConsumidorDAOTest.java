package esfe.persistencia;

import esfe.dominio.TipoConsumidor;

public class TipoConsumidorDAOTest {

    public static void main(String[] args) {

        TipoConsumidor tipo = new TipoConsumidor();

        tipo.setNombreEspecialidad("Ingeniería");

        TipoConsumidorDAO dao = new TipoConsumidorDAO();

        int resultado = dao.guardar(tipo);

        if (resultado > 0) {

            System.out.println("Guardado correctamente");

        } else {

            System.out.println("Error al guardar");
        }
    }
}