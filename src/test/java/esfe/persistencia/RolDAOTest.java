package esfe.persistencia;

import esfe.dominio.Rol;

public class RolDAOTest {

    public static void main(String[] args) {

        try {

            Rol rol = new Rol();

            rol.setNombreRol("Supervisor");

            RolDAO dao = new RolDAO();

            int resultado = dao.guardar(rol);

            if (resultado > 0) {

                System.out.println("Registro guardado correctamente");

            } else {

                System.out.println("No se guardó");
            }

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}