package esfe.dominio;

import java.time.LocalDate;

public class Prestamo {
    private Long id;
    private String cliente;
    private Double monto;
    private LocalDate fechaInicio;
    private Boolean activo;

    // Constructor vacío
    public Prestamo() {
    }

    // Constructor completo
    public Prestamo(Long id, String cliente, Double monto, LocalDate fechaInicio, Boolean activo) {
        this.id = id;
        this.cliente = cliente;
        this.monto = monto;
        this.fechaInicio = fechaInicio;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", monto=" + monto +
                ", fechaInicio=" + fechaInicio +
                ", activo=" + activo +
                '}';
    }
}