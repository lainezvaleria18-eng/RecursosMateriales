package esfe.dominio;

public class User {

    private int idUsuario;
    private String carnet;
    private String nombre;
    private String correo;
    private String usuario;
    private String clave;
    private int idRol;
    private String fotoPerfil;
    private byte estado;


    private String fechaCreacion;
    private String ultimaModificacionPor;
    private String fechaUltimaModificacion;


    public User() {
    }


    public User(int idUsuario, String carnet, String nombre, String correo, String usuario,
                String clave, int idRol, String fotoPerfil, byte estado,
                String fechaCreacion, String ultimaModificacionPor, String fechaUltimaModificacion) {
        this.idUsuario = idUsuario;
        this.carnet = carnet;
        this.nombre = nombre;
        this.correo = correo;
        this.usuario = usuario;
        this.clave = clave;
        this.idRol = idRol;
        this.fotoPerfil = fotoPerfil;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.ultimaModificacionPor = ultimaModificacionPor;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }



    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUltimaModificacionPor() {
        return ultimaModificacionPor;
    }

    public void setUltimaModificacionPor(String ultimaModificacionPor) {
        this.ultimaModificacionPor = ultimaModificacionPor;
    }

    public String getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(String fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }


    public String getStrEstatus() {
        String str = "";
        switch (estado) {
            case 1:
                str = "ACTIVO";
                break;
            case 0:
                str = "INACTIVO";
                break;
            default:
                str = "";
        }
        return str;
    }
}