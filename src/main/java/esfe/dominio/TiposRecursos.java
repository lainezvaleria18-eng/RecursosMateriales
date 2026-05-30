package esfe.dominio;

public class TiposRecursos {

    private int idTipoRecurso;
    private String nombreTipo;
    private String descripcion;

    public TiposRecursos() {
    }

    public TiposRecursos(int idTipoRecurso, String nombreTipo, String descripcion) {
        this.idTipoRecurso = idTipoRecurso;
        this.nombreTipo = nombreTipo;
        this.descripcion = descripcion;
    }

    public int getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(int idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TiposRecursos{" +
                "idTipoRecurso=" + idTipoRecurso +
                ", nombreTipo='" + nombreTipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}