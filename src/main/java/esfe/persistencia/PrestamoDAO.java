package esfe.persistencia;

import esfe.dominio.Prestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public boolean guardar(Prestamo prestamo){

        String sql="INSERT INTO Prestamos " +
                "(FechaSolicitud,FechaDevolucion,Estado,Observaciones,IdUsuario,IdTipoConsumidor) " +
                "VALUES(?,?,?,?,?,?)";

        try(Connection con=ConnectionManager.getInstance().connect();
            PreparedStatement ps=con.prepareStatement(sql)){

            ps.setString(1,prestamo.getFechaSolicitud());
            ps.setString(2,prestamo.getFechaDevolucion());
            ps.setString(3,prestamo.getEstado());
            ps.setString(4,prestamo.getObservaciones());
            ps.setInt(5,prestamo.getIdUsuario());
            ps.setInt(6,prestamo.getIdTipoConsumidor());

            return ps.executeUpdate()>0;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }

    }

    public List<Prestamo> obtenerTodos(){

        List<Prestamo> lista=new ArrayList<>();

        String sql="SELECT * FROM Prestamos";

        try(Connection con=ConnectionManager.getInstance().connect();
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery()){

            while(rs.next()){

                Prestamo p=new Prestamo();

                p.setIdPrestamo(rs.getInt("IdPrestamo"));
                p.setFechaSolicitud(rs.getString("FechaSolicitud"));
                p.setFechaDevolucion(rs.getString("FechaDevolucion"));
                p.setEstado(rs.getString("Estado"));
                p.setObservaciones(rs.getString("Observaciones"));
                p.setIdUsuario(rs.getInt("IdUsuario"));
                p.setIdTipoConsumidor(rs.getInt("IdTipoConsumidor"));

                lista.add(p);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return lista;

    }

}