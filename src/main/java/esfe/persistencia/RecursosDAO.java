package esfe.persistencia;

import esfe.dominio.Recursos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RecursosDAO {


    public boolean guardar(Recursos recurso) {
        String sql = "INSERT INTO Recursos (CodigoRecurso, NombreRecurso, Marca, Modelo, NumeroSerie, " +
                "Ubicacion, UnidadMedida, Stock, Precio, Descripcion, IdCategoria, IdTipoRecurso, IdEstadoRecurso) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, recurso.getCodigoRecurso());
            ps.setString(2, recurso.getNombreRecurso());
            ps.setString(3, recurso.getMarca());
            ps.setString(4, recurso.getModelo());
            ps.setString(5, recurso.getNumeroSerie());
            ps.setString(6, recurso.getUbicacion());
            ps.setString(7, recurso.getUnidadMedida());
            ps.setInt(8, recurso.getStock());
            ps.setBigDecimal(9, recurso.getPrecio());
            ps.setString(10, recurso.getDescripcion());
            ps.setInt(11, recurso.getIdCategoria());
            ps.setInt(12, recurso.getIdTipoRecurso());
            ps.setInt(13, recurso.getIdEstadoRecurso());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Recursos> obtenerTodos() {
        List<Recursos> lista = new ArrayList<>();
        String sql = "SELECT IdRecurso, CodigoRecurso, NombreRecurso, Marca, Modelo, NumeroSerie, " +
                "Ubicacion, UnidadMedida, Stock, Precio, Descripcion, IdCategoria, IdTipoRecurso, IdEstadoRecurso FROM Recursos";

        try (Connection con = ConnectionManager.getInstance().connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Recursos recurso = new Recursos();
                recurso.setIdRecurso(rs.getInt("IdRecurso"));
                recurso.setCodigoRecurso(rs.getString("CodigoRecurso"));
                recurso.setNombreRecurso(rs.getString("NombreRecurso"));
                recurso.setMarca(rs.getString("Marca"));
                recurso.setModelo(rs.getString("Modelo"));
                recurso.setNumeroSerie(rs.getString("NumeroSerie"));
                recurso.setUbicacion(rs.getString("Ubicacion"));
                recurso.setUnidadMedida(rs.getString("UnidadMedida"));
                recurso.setStock(rs.getInt("Stock"));
                recurso.setPrecio(rs.getBigDecimal("Precio"));
                recurso.setDescripcion(rs.getString("Descripcion"));
                recurso.setIdCategoria(rs.getInt("IdCategoria"));
                recurso.setIdTipoRecurso(rs.getInt("IdTipoRecurso"));
                recurso.setIdEstadoRecurso(rs.getInt("IdEstadoRecurso"));

                lista.add(recurso);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}