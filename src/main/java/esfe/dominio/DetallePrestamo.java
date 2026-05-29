package esfe.dominio;

public class DetallePrestamo {
    private int idDetallePrestamo;
    private int idPrestamo;
    private int idConsumible;
    private int cantidad;
    private String observaciones;

    public DetallePrestamo() {}

    public DetallePrestamo(int idDetallePrestamo, int idPrestamo, int idConsumible, int cantidad, String observaciones) {
        this.idDetallePrestamo = idDetallePrestamo;
        this.idPrestamo = idPrestamo;
        this.idConsumible = idConsumible;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
    }

    public int getIdDetallePrestamo() { return idDetallePrestamo; }
    public void setIdDetallePrestamo(int idDetallePrestamo) { this.idDetallePrestamo = idDetallePrestamo; }

    public int getIdPrestamo() { return idPrestamo; }
    public void setIdPrestamo(int idPrestamo) { this.idPrestamo = idPrestamo; }

    public int getIdConsumible() { return idConsumible; }
    public void setIdConsumible(int idConsumible) { this.idConsumible = idConsumible; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}
