package esfe.dominio;

import java.util.Date;


public class Notificaciones {

    private int idNotificacion;
    private String mensaje;
    private Date fecha;

    private boolean leida;
    private int idUsuario;

    public Notificaciones() {
    }

    public Notificaciones(String mensaje, Date fecha, boolean leida, int idUsuario) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.leida = leida;
        this.idUsuario = idUsuario;
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}