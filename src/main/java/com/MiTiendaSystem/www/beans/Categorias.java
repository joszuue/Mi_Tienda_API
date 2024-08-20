package com.MiTiendaSystem.www.beans;

public class Categorias {
    private String codCategoria;
    private String nombre;
    private String descripcion;
    private String estado;

    public Categorias() {
    }

    public Categorias(String codCategoria, String nombre, String descripcion, String estado ) {
        this.codCategoria = codCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(String codCategoria) {
        this.codCategoria = codCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
