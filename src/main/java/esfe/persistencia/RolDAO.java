package esfe.persistencia;

import esfe.dominio.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RolDAO {

    public int guardar(Rol rol) {

        int resultado = 0;

        try {

            Connection conn = ConnectionManager
                    .getInstance()
                    .connect();

            String sql = "INSERT INTO Roles (NombreRol) VALUES (?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, rol.getNombreRol());

            resultado = ps.executeUpdate();

            ps.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }
}