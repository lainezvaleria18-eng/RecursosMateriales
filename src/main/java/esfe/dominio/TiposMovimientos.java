package esfe.dominio;

public class TiposMovimientos {

    private int idTipoMovimiento;
    private String nombreTipo;

    public TiposMovimientos() {
    }

    public TiposMovimientos(int idTipoMovimiento, String nombreTipo) {
        this.idTipoMovimiento = idTipoMovimiento;
        this.nombreTipo = nombreTipo;
    }

    public int getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(int idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    @Override
    public String toString() {
        return "TiposMovimientos{" +
                "idTipoMovimiento=" + idTipoMovimiento +
                ", nombreTipo='" + nombreTipo + '\'' +
                '}';
    }
}