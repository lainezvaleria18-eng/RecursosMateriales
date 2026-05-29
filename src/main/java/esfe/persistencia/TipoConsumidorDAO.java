package esfe.persistencia;

import esfe.dominio.TipoConsumidor;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TipoConsumidorDAO {

    public int guardar(TipoConsumidor tipo) {

        int resultado = 0;

        try {

            Connection conn = ConnectionManager
                    .getInstance()
                    .connect();

            String sql = "INSERT INTO TiposConsumidores (NombreEspecialidad) VALUES (?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, tipo.getNombreEspecialidad());

            resultado = ps.executeUpdate();

            ps.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }
}