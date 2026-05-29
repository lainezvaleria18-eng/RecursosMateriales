package esfe.persistencia;

import esfe.dominio.EstadosRecursos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstadosRecursosDAO {


    public boolean insertar(EstadosRecursos estado) {
        String sql = "INSERT INTO EstadosRecursos (NombreEstado) VALUES (?)";


        try (Connection con = ConnectionManager.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado.getNombreEstado());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<EstadosRecursos> obtenerTodos() {
        List<EstadosRecursos> lista = new ArrayList<>();
        String sql = "SELECT IdEstadoRecurso, NombreEstado FROM EstadosRecursos";


        try (Connection con = ConnectionManager.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EstadosRecursos estado = new EstadosRecursos();
                estado.setIdEstadoRecurso(rs.getInt("IdEstadoRecurso"));
                estado.setNombreEstado(rs.getString("NombreEstado"));
                lista.add(estado);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
