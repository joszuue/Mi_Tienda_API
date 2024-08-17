package com.MiTiendaSystem.www.beans;

public class Imagenes {
    private int idImagen;
    private String codProducto;
    private String imagen;
    private String estado;

    public Imagenes() {
    }

    public Imagenes(int idImagen, String codProducto, String imagen, String estado) {
        this.idImagen = idImagen;
        this.codProducto = codProducto;
        this.imagen = imagen;
        this.estado = estado;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
