package com.MiTiendaSystem.www.beans;

public class Pedidos {
    private String codPedido;
    private Productos producto;
    private Ordenes orden;
    private Clientes cliente;
    private int cantidad;
    private Double precioUnitario;
    private String estado;

    public Pedidos() {
    }

    public Pedidos(String codPedido, Productos producto, Ordenes orden, Clientes cliente, int cantidad, Double precioUnitario, String estado ) {
        this.codPedido = codPedido;
        this.producto = producto;
        this.orden = orden;
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.estado = estado;
    }

    public String getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(String codPedido) {
        this.codPedido = codPedido;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Ordenes getOrden() {
        return orden;
    }

    public void setOrden(Ordenes orden) {
        this.orden = orden;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
