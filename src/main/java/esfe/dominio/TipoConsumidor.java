package esfe.dominio;

public class TipoConsumidor {

    private int idTipoConsumidor;
    private String nombreEspecialidad;

    public TipoConsumidor() {
    }

    public TipoConsumidor(int idTipoConsumidor, String nombreEspecialidad) {
        this.idTipoConsumidor = idTipoConsumidor;
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public int getIdTipoConsumidor() {
        return idTipoConsumidor;
    }

    public void setIdTipoConsumidor(int idTipoConsumidor) {
        this.idTipoConsumidor = idTipoConsumidor;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }
}