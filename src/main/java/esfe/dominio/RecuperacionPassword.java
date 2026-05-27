package esfe.dominio;

import java.util.Date;

public class RecuperacionPassword {
    private int idRecuperacion;
    private int idUsuario;
    private String codigoRecuperacion;
    private Date fechaSolicitud;
    private Date fechaExpiracion;
    private boolean usado;


    public RecuperacionPassword() {}


    public RecuperacionPassword(int idRecuperacion, int idUsuario, String codigoRecuperacion,
                                Date fechaSolicitud, Date fechaExpiracion, boolean usado) {
        this.idRecuperacion = idRecuperacion;
        this.idUsuario = idUsuario;
        this.codigoRecuperacion = codigoRecuperacion;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaExpiracion = fechaExpiracion;
        this.usado = usado;
    }


    public int getIdRecuperacion() { return idRecuperacion; }
    public void setIdRecuperacion(int idRecuperacion) { this.idRecuperacion = idRecuperacion; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getCodigoRecuperacion() { return codigoRecuperacion; }
    public void setCodigoRecuperacion(String codigoRecuperacion) { this.codigoRecuperacion = codigoRecuperacion; }

    public Date getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(Date fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public Date getFechaExpiracion() { return fechaExpiracion; }
    public void setFechaExpiracion(Date fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }

    public boolean isUsado() { return usado; }
    public void setUsado(boolean usado) { this.usado = usado; }
}