package esfe.persistencia;

import esfe.dominio.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Rol> obtenerTodos() {

        List<Rol> roles = new ArrayList<>();

        try {

            Connection conn = ConnectionManager
                    .getInstance()
                    .connect();

            String sql = "SELECT IdRol, NombreRol FROM Roles";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Rol rol = new Rol();

                rol.setIdRol(rs.getInt("IdRol"));
                rol.setNombreRol(rs.getString("NombreRol"));

                roles.add(rol);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }

        return roles;
    }
}