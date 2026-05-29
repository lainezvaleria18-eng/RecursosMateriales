package esfe.dominio;

public class EstadosRecursos {

    private int idEstadoRecurso;
    private String nombreEstado;


    public EstadosRecursos() {
    }


    public EstadosRecursos(int idEstadoRecurso, String nombreEstado) {
        this.idEstadoRecurso = idEstadoRecurso;
        this.nombreEstado = nombreEstado;
    }


    public int getIdEstadoRecurso() {
        return idEstadoRecurso;
    }

    public void setIdEstadoRecurso(int idEstadoRecurso) {
        this.idEstadoRecurso = idEstadoRecurso;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}