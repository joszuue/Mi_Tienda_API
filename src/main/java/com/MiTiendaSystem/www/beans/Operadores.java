package com.MiTiendaSystem.www.beans;

public class Operadores {
    private String codOperadores;
    private String contra;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String rol;
    private String estado;

    public Operadores() {
    }

    public Operadores(String codOperadores, String contra, String nombre1, String nombre2, String apellido1, String apellido2, String rol, String estado ) {
        this.codOperadores = codOperadores;
        this.contra = contra;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.rol = rol;
        this.estado = estado;
    }

    public String getCodOperadores() {
        return codOperadores;
    }

    public void setCodOperadores(String codOperadores) {
        this.codOperadores = codOperadores;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
