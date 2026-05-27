package esfe.utils;

import java.nio.charset.StandardCharsets; // Define el juego de caracteres UTF-8 para codificar la contraseña antes de procesarla.
import java.security.MessageDigest;       // Proporciona el algoritmo criptográfico SHA-256 para el encriptado.
import java.security.NoSuchAlgorithmException; // Maneja excepciones si el algoritmo solicitado no está disponible.
import java.util.Base64;                  // Codifica el hash resultante en texto almacenable para la base de datos de Somee.


public class PasswordHasher {

    /**
     * Hashea la clave de un usuario utilizando el algoritmo SHA-256 y la codifica en Base64.
     * Este método se utilizará al registrar un nuevo usuario o al validar las credenciales en el Login.
     *
     * @param password La contraseña en texto plano que ingresa el usuario en la interfaz.
     * @return Una cadena de texto segura que representa la contraseña encriptada.
     * Retorna null si ocurre un fallo con el algoritmo criptográfico.
     */
    public static String hashPassword(String password) {
        try {
            // Obtiene una instancia del algoritmo de seguridad SHA-256.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Convierte la contraseña a bytes en formato UTF-8 y calcula el resumen criptográfico (hash).
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Codifica el array de bytes resultante a un formato String Base64 estándar
            // para guardarlo de forma limpia en el campo 'Clave' de la tabla Usuarios en Somee.
            return Base64.getEncoder().encodeToString(hashBytes);

        } catch (NoSuchAlgorithmException ex) {
            // Captura el error en caso de que el entorno no soporte SHA-256.
            System.err.println("Error crítico: No se encontró el algoritmo SHA-256 para el encriptado.");
            return null;
        }
    }
}