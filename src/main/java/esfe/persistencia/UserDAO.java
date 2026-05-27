package esfe.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import esfe.dominio.User;
import esfe.utils.PasswordHasher;

public class UserDAO {
    private ConnectionManager conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserDAO(){
        conn = ConnectionManager.getInstance();
    }

    public User create(User user) throws SQLException {
        User res = null;
        try {
            ps = conn.connect().prepareStatement(
                    "INSERT INTO Usuarios (Carnet, Nombre, Correo, Usuario, Clave, IdRol, FotoPerfil, Estado, FechaCreacion) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, GETDATE())",
                    java.sql.Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, user.getCarnet());
            ps.setString(2, user.getNombre());
            ps.setString(3, user.getCorreo());
            ps.setString(4, user.getUsuario());
            ps.setString(5, PasswordHasher.hashPassword(user.getClave()));
            ps.setInt(6, user.getIdRol());
            ps.setString(7, user.getFotoPerfil());
            ps.setByte(8, user.getEstado());

            int affectedRows = ps.executeUpdate();

            if (affectedRows != 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    res = getById(idGenerado);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
                generatedKeys.close();
            }

            if (ps != null) { ps.close(); } // Cierre dentro del try como lo tiene el lic

        } catch (SQLException ex){
            throw new SQLException("Error al crear el usuario: " + ex.getMessage(), ex);
        } finally {

            if (ps != null) {
                try { ps.close(); } catch (SQLException e) { /* Ignorar */ }
            }
            ps = null;
            conn.disconnect();
        }
        return res;
    }

    public boolean update(User user) throws SQLException {
        boolean res = false;
        try {
            ps = conn.connect().prepareStatement(
                    "UPDATE Usuarios " +
                            "SET Carnet = ?, Nombre = ?, Correo = ?, Usuario = ?, IdRol = ?, FotoPerfil = ?, Estado = ?, " +
                            "UltimaModificacionPor = ?, FechaUltimaModificacion = GETDATE() " +
                            "WHERE IdUsuario = ?"
            );

            ps.setString(1, user.getCarnet());
            ps.setString(2, user.getNombre());
            ps.setString(3, user.getCorreo());
            ps.setString(4, user.getUsuario());
            ps.setInt(5, user.getIdRol());
            ps.setString(6, user.getFotoPerfil());
            ps.setByte(7, user.getEstado());
            ps.setString(8, user.getUltimaModificacionPor());
            ps.setInt(9, user.getIdUsuario());

            if(ps.executeUpdate() > 0){
                res = true;
            }

            if (ps != null) { ps.close(); }

        } catch (SQLException ex){
            throw new SQLException("Error al modificar el usuario: " + ex.getMessage(), ex);
        } finally {
            if (ps != null) {
                try { ps.close(); } catch (SQLException e) { /* Ignorar */ }
            }
            ps = null;
            conn.disconnect();
        }
        return res;
    }

    public boolean delete(User user) throws SQLException {
        boolean res = false;
        try {
            ps = conn.connect().prepareStatement("DELETE FROM Usuarios WHERE IdUsuario = ?");
            ps.setInt(1, user.getIdUsuario());

            if(ps.executeUpdate() > 0){
                res = true;
            }

            if (ps != null) { ps.close(); }

        } catch (SQLException ex){
            throw new SQLException("Error al eliminar el usuario: " + ex.getMessage(), ex);
        } finally {
            if (ps != null) {
                try { ps.close(); } catch (SQLException e) { /* Ignorar */ }
            }
            ps = null;
            conn.disconnect();
        }
        return res;
    }

    public ArrayList<User> search(String nombre) throws SQLException {
        ArrayList<User> records = new ArrayList<>();
        try {
            ps = conn.connect().prepareStatement(
                    "SELECT IdUsuario, Carnet, Nombre, Correo, Usuario, IdRol, FotoPerfil, Estado " +
                            "FROM Usuarios WHERE Nombre LIKE ?"
            );

            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();

            while (rs.next()){
                User user = new User();
                user.setIdUsuario(rs.getInt("IdUsuario"));
                user.setCarnet(rs.getString("Carnet"));
                user.setNombre(rs.getString("Nombre"));
                user.setCorreo(rs.getString("Correo"));
                user.setUsuario(rs.getString("Usuario"));
                user.setIdRol(rs.getInt("IdRol"));
                user.setFotoPerfil(rs.getString("FotoPerfil"));
                user.setEstado(rs.getByte("Estado"));
                records.add(user);
            }

            if (rs != null) { rs.close(); }
            if (ps != null) { ps.close(); }

        } catch (SQLException ex){
            throw new SQLException("Error al buscar usuarios: " + ex.getMessage(), ex);
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException e) { /* Ignorar */ }
            }
            if (ps != null) {
                try { ps.close(); } catch (SQLException e) { /* Ignorar */ }
            }
            ps = null;
            rs = null;
            conn.disconnect();
        }
        return records;
    }

    public User getById(int idUsuario) throws SQLException {
        User user = new User();
        try {
            ps = conn.connect().prepareStatement(
                    "SELECT IdUsuario, Carnet, Nombre, Correo, Usuario, IdRol, FotoPerfil, Estado " +
                            "FROM Usuarios WHERE IdUsuario = ?"
            );

            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                user.setIdUsuario(rs.getInt("IdUsuario"));
                user.setCarnet(rs.getString("Carnet"));
                user.setNombre(rs.getString("Nombre"));
                user.setCorreo(rs.getString("Correo"));
                user.setUsuario(rs.getString("Usuario"));
                user.setIdRol(rs.getInt("IdRol"));
                user.setFotoPerfil(rs.getString("FotoPerfil"));
                user.setEstado(rs.getByte("Estado"));
            } else {
                user = null;
            }

            if (rs != null) { rs.close(); }
            if (ps != null) { ps.close(); }

        } catch (SQLException ex){
            throw new SQLException("Error al obtener un usuario por id: " + ex.getMessage(), ex);
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException e) { /* Ignorar */ }
            }
            if (ps != null) {
                try { ps.close(); } catch (SQLException e) { /* Ignorar */ }
            }
            ps = null;
            rs = null;
            conn.disconnect();
        }
        return user;
    }

    public User authenticate(User user) throws SQLException {
        User userAuthenticate = new User();
        try {
            ps = conn.connect().prepareStatement(
                    "SELECT IdUsuario, Carnet, Nombre, Correo, Usuario, IdRol, FotoPerfil, Estado " +
                            "FROM Usuarios " +
                            "WHERE Usuario = ? AND Clave = ? AND Estado = 1"
            );

            ps.setString(1, user.getUsuario());
            ps.setString(2, PasswordHasher.hashPassword(user.getClave()));
            rs = ps.executeQuery();

            if (rs.next()) {
                userAuthenticate.setIdUsuario(rs.getInt("IdUsuario"));
                userAuthenticate.setCarnet(rs.getString("Carnet"));
                userAuthenticate.setNombre(rs.getString("Nombre"));
                userAuthenticate.setCorreo(rs.getString("Correo"));
                userAuthenticate.setUsuario(rs.getString("Usuario"));
                userAuthenticate.setIdRol(rs.getInt("IdRol"));
                userAuthenticate.setFotoPerfil(rs.getString("FotoPerfil"));
                userAuthenticate.setEstado(rs.getByte("Estado"));
            } else {
                userAuthenticate = null;
            }

            if (rs != null) { rs.close(); }
            if (ps != null) { ps.close(); }

        } catch (SQLException ex){
            throw new SQLException("Error al autenticar un usuario por id: " + ex.getMessage(), ex);
        } finally {
            if (rs != null) {
                try { rs.close(); } catch (SQLException e) { /* Ignorar */ }
            }
            if (ps != null) {
                try { ps.close(); } catch (SQLException e) { /* Ignorar */ }
            }
            ps = null;
            rs = null;
            conn.disconnect();
        }
        return userAuthenticate;
    }

    public boolean updatePassword(User user) throws SQLException {
        boolean res = false;
        try {
            ps = conn.connect().prepareStatement(
                    "UPDATE Usuarios SET Clave = ? WHERE IdUsuario = ?"
            );

            ps.setString(1, PasswordHasher.hashPassword(user.getClave()));
            ps.setInt(2, user.getIdUsuario());

            if(ps.executeUpdate() > 0){
                res = true;
            }

            if (ps != null) { ps.close(); }

        } catch (SQLException ex){
            throw new SQLException("Error al modificar el password del usuario: " + ex.getMessage(), ex);
        } finally {
            if (ps != null) {
                try { ps.close(); } catch (SQLException e) { /* Ignorar */ }
            }
            ps = null;
            conn.disconnect();
        }
        return res;
    }
}