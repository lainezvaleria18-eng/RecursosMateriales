package esfe.dominio;

import java.util.Date;

public class Mantenimiento {

    private int idMantenimiento;
    private int idRecurso;
    private Date fecha;
    private String tecnico;
    private String observacion;
    private String estadoMantenimiento;

    public Mantenimiento() {
    }

    public Mantenimiento(int idRecurso, Date fecha,
                         String tecnico,
                         String observacion,
                         String estadoMantenimiento) {

        this.idRecurso = idRecurso;
        this.fecha = fecha;
        this.tecnico = tecnico;
        this.observacion = observacion;
        this.estadoMantenimiento = estadoMantenimiento;
    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstadoMantenimiento() {
        return estadoMantenimiento;
    }

    public void setEstadoMantenimiento(String estadoMantenimiento) {
        this.estadoMantenimiento = estadoMantenimiento;
    }
}