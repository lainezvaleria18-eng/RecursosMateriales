package esfe.persistencia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import esfe.dominio.User;

import java.util.ArrayList;
import java.util.Random;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private UserDAO userDAO;

    @BeforeEach
    void setUp(){

        userDAO = new UserDAO();
    }

    private User create(User user) throws SQLException{

        User res = userDAO.create(user);


        assertNotNull(res, "El usuario creado no debería ser nulo.");
        assertEquals(user.getNombre(), res.getNombre(), "El nombre del usuario creado debe ser igual al original.");
        assertEquals(user.getCorreo(), res.getCorreo(), "El email del usuario creado debe ser igual al original.");
        assertEquals(user.getEstado(), res.getEstado(), "El estado del usuario creado debe ser igual al original.");


        return res;
    }

    private void update(User user) throws SQLException{

        user.setNombre(user.getNombre() + " Modificado");
        user.setCorreo("u" + user.getCorreo());
        user.setEstado((byte)1); // Establece el estado a 1 (Activo).
        user.setUltimaModificacionPor("Admin"); // Nombre de auditoría genérico


        boolean res = userDAO.update(user);


        assertTrue(res, "La actualización del usuario debería ser exitosa.");


        getById(user);
    }

    private void getById(User user) throws SQLException {
        // Llama al método 'getById' usando tu llave primaria 'idUsuario'
        User res = userDAO.getById(user.getIdUsuario());


        assertNotNull(res, "El usuario obtenido por ID no debería ser nulo.");
        assertEquals(user.getIdUsuario(), res.getIdUsuario(), "El ID del usuario obtenido debe ser igual al original.");
        assertEquals(user.getNombre(), res.getNombre(), "El nombre del usuario obtenido debe ser igual al esperado.");
        assertEquals(user.getCorreo(), res.getCorreo(), "El email del usuario obtenido debe ser igual al esperado.");
        assertEquals(user.getEstado(), res.getEstado(), "El estado del usuario obtenido debe ser igual al esperado.");
    }

    private void search(User user) throws SQLException {

        ArrayList<User> users = userDAO.search(user.getNombre());
        boolean find = false;


        for (User userItem : users) {
            if (userItem.getNombre().contains(user.getNombre())) {
                find = true;
            }
            else{
                find = false;
                break;
            }
        }

        assertTrue(find, "El nombre buscado no fue encontrado: " + user.getNombre());
    }

    private void delete(User user) throws SQLException{

        boolean res = userDAO.delete(user);

        assertTrue(res, "La eliminación del usuario debería ser exitosa.");


        User res2 = userDAO.getById(user.getIdUsuario());
        assertNull(res2, "El usuario debería haber sido eliminado y no encontrado por ID.");
    }

    private void autenticate(User user) throws SQLException {

        User res = userDAO.authenticate(user);

        assertNotNull(res, "La autenticación debería retornar un usuario no nulo si es exitosa.");
        assertEquals(res.getCorreo(), user.getCorreo(), "El correo del usuario autenticado debe coincidir.");
        assertEquals(res.getEstado(), 1, "El estado del usuario autenticado debe ser 1 (activo).");
    }

    private void autenticacionFails(User user) throws SQLException {

        User res = userDAO.authenticate(user);
        assertNull(res, "La autenticación debería fallar y retornar null para credenciales inválidas.");
    }

    private void updatePassword(User user) throws SQLException{

        boolean res = userDAO.updatePassword(user);

        assertTrue(res, "La actualización de la contraseña debería ser exitosa.");


        autenticate(user);
    }

    @Test
    void testUserDAO() throws SQLException {
        Random random = new Random();
        int num = random.nextInt(1000) + 1;

        String strEmail = "carlos" + num + "@empresa.com";
        String strUsuario = "cmartinez" + num;
        String strCarnet = "ADM-" + num;

        User user = new User(0, strCarnet, "Carlos Martínez " + num, strEmail, strUsuario,
                "12345", 1, "foto_default.png", (byte) 1,
                null, null, null);


        User testUser = create(user);


        testUser.setClave("12345");


        update(testUser);


        search(testUser);


        autenticate(testUser);


        testUser.setClave("clave_erronea_999");
        autenticacionFails(testUser);


        testUser.setClave("nuevaClave54321");
        updatePassword(testUser);


        delete(testUser);
    }

    @Test
    void createUser() throws SQLException {
        Random random = new Random();
        int num = random.nextInt(5000) + 1;

        // Prueba de inserción rápida basada en tu ejemplo de 'Juan Pérez'
        User user = new User(0, "2023" + num, "Juan Pérez", "juan.perez" + num + "@empresa.com", "jperez" + num,
                "12345", 2, "user.png", (byte) 1,
                null, null, null);

        User res = userDAO.create(user);
        assertNotNull(res, "El resultado no debe ser nulo al crear un registro válido.");


        userDAO.delete(res);
    }
}