package esfe.dominio;

public class DetallePrestamo {
    private Long id;
    private Long prestamoId; // Llave foránea que conecta con el préstamo principal
    private Integer numeroCuota;
    private Double montoCuota;
    private Boolean pagado;

    // Constructor vacío
    public DetallePrestamo() {
    }

    // Constructor completo
    public DetallePrestamo(Long id, Long prestamoId, Integer numeroCuota, Double montoCuota, Boolean pagado) {
        this.id = id;
        this.prestamoId = prestamoId;
        this.numeroCuota = numeroCuota;
        this.montoCuota = montoCuota;
        this.pagado = pagado;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(Long prestamoId) {
        this.prestamoId = prestamoId;
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Double getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(Double montoCuota) {
        this.montoCuota = montoCuota;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    @Override
    public String toString() {
        return "DetallePrestamo{" +
                "id=" + id +
                ", prestamoId=" + prestamoId +
                ", numeroCuota=" + numeroCuota +
                ", montoCuota=" + montoCuota +
                ", pagado=" + pagado +
                '}';
    }
}