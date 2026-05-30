package esfe.persistencia;

import esfe.dominio.Prestamo;

import java.sql.Connection;
import java.time.LocalDate;

public class PrestamoDAOTest {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBAS UNITARIAS ===");

        // Probamos con una conexión nula o simulada de forma segura
        Connection conexionSimulada = null;
        PrestamoDAO dao = new PrestamoDAO(conexionSimulada);

        try {
            System.out.println("Prueba 1: Instanciar dominio... OK");
            Prestamo p = new Prestamo(1L, "Juan Pérez", 350.0, LocalDate.now(), true);

            System.out.println("Prueba 2: Verificar datos del objeto...");
            if (p.getId() == 1L && p.getCliente().equals("Juan Pérez")) {
                System.out.println("   -> PASÓ: Estructura del dominio correcta.");
            } else {
                System.out.println("   -> FALLÓ: Los datos no coinciden.");
            }

            System.out.println("\nNota: Para ejecutar pruebas de inserción/búsqueda real,");
            System.out.println("necesitas proveer una conexión activa a una Base de Datos.");

        } catch (Exception e) {
            System.out.println("Error durante la prueba: " + e.getMessage());
        }
    }
}