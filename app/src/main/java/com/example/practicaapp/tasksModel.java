package com.example.practicaapp;

public class tasksModel {

    public String titulo;
    private String descripcion;
    private String fecha;
    private String completado;
    private String key;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCompletado() {
        return completado;
    }

    public void setCompletado(String completado) {
        this.completado = completado;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public tasksModel(String key) {
        this.key = key;
    }

    public tasksModel(String titulo, String descripcion, String fecha, String completado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.completado = completado;
    }

    public tasksModel(String titulo, String descripcion, String fecha) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public tasksModel() {
    }
}
