package esfe.dominio;

import java.math.BigDecimal;

public class Recursos {


    private int idRecurso;
    private String codigoRecurso;
    private String nombreRecurso;
    private String marca;
    private String modelo;
    private String numeroSerie;
    private String ubicacion;
    private String unidadMedida;
    private int stock;
    private BigDecimal precio;
    private String descripcion;
    private int idCategoria;
    private int idTipoRecurso;
    private int idEstadoRecurso;


    public Recursos() {
    }


    public Recursos(String codigoRecurso, String nombreRecurso, String marca, String modelo,
                    String numeroSerie, String ubicacion, String unidadMedida, int stock,
                    BigDecimal precio, String descripcion, int idCategoria, int idTipoRecurso,
                    int idEstadoRecurso) {
        this.codigoRecurso = codigoRecurso;
        this.nombreRecurso = nombreRecurso;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.ubicacion = ubicacion;
        this.unidadMedida = unidadMedida;
        this.stock = stock;
        this.precio = precio;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.idTipoRecurso = idTipoRecurso;
        this.idEstadoRecurso = idEstadoRecurso;
    }


    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getCodigoRecurso() {
        return codigoRecurso;
    }

    public void setCodigoRecurso(String codigoRecurso) {
        this.codigoRecurso = codigoRecurso;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdTipoRecurso() {
        return idTipoRecurso;
    }

    public void setIdTipoRecurso(int idTipoRecurso) {
        this.idTipoRecurso = idTipoRecurso;
    }

    public int getIdEstadoRecurso() {
        return idEstadoRecurso;
    }

    public void setIdEstadoRecurso(int idEstadoRecurso) {
        this.idEstadoRecurso = idEstadoRecurso;
    }
}
