package com.MiTiendaSystem.www.beans;

public class Ofertas {
    private String codOferta;
    private Productos producto;
    private String fechaCreacion;
    private String fechaIncio;
    private String fechaFin;
    private double precio;
    private String estado;

    public Ofertas() {
    }

    public Ofertas(String codOferta, Productos producto, String fechaCreacion, String fechaIncio, String fechaFin, double precio, String estado ) {
        this.codOferta = codOferta;
        this.producto = producto;
        this.fechaCreacion = fechaCreacion;
        this.fechaIncio = fechaIncio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.estado = estado;
    }

    public String getCodOferta() {
        return codOferta;
    }

    public void setCodOferta(String codOferta) {
        this.codOferta = codOferta;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaIncio() {
        return fechaIncio;
    }

    public void setFechaIncio(String fechaIncio) {
        this.fechaIncio = fechaIncio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
