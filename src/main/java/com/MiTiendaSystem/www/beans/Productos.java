package com.MiTiendaSystem.www.beans;

import java.util.ArrayList;

public class Productos {
    private String codProducto;
    private Categorias categoria;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String sexo;
    private int stock;
    private String estado;

    private ArrayList<Imagenes> imagenes;

    public Productos() {
    }

    public Productos(String codProducto, Categorias categoria, String nombre, String descripcion, Double precio, String sexo, int stock, String estado, ArrayList<Imagenes> imagenes ) {
        this.codProducto = codProducto;
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.sexo = sexo;
        this.stock = stock;
        this.estado = estado;
        this.imagenes = imagenes;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Imagenes> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<Imagenes> imagenes) {
        this.imagenes = imagenes;
    }
}
