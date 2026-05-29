package esfe.persistencia;

import esfe.dominio.CategoriaMaterial;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CategoriaMaterialDAO {

    public int guardar(CategoriaMaterial categoria) {

        int resultado = 0;

        try {

            Connection conn = ConnectionManager
                    .getInstance()
                    .connect();

            String sql = """
                    INSERT INTO CategoriasMateriales
                    (NombreCategoria, Descripcion)
                    VALUES (?, ?)
                    """;

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, categoria.getNombreCategoria());
            ps.setString(2, categoria.getDescripcion());

            resultado = ps.executeUpdate();

            ps.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }

        return resultado;
    }
}