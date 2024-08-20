package com.MiTiendaSystem.www.beans;

public class Ordenes {
    private String codOrden;
    private String direccion;
    private String fecha;
    private Double total;
    private String estado;

    public Ordenes() {
    }

    public Ordenes(String codOrden, String direccion, String fecha, Double total, String estado ) {
        this.codOrden = codOrden;
        this.direccion = direccion;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public String getCodOrden() {
        return codOrden;
    }

    public void setCodOrden(String codOrden) {
        this.codOrden = codOrden;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
