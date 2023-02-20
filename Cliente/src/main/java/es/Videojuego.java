package es;

import java.io.Serializable;

public class Videojuego implements Serializable
{
    private static final long serialVersionUID = 3057545624874202352L;

    private int id;
    private String nombre;
    private String pegi;
    private String tematica;
    private int precio;
    private String plataforma;

    public Videojuego(int id, String nombre, String pegi, String tematica, int precio, String plataforma) {
        this.id = id;
        this.nombre = nombre;
        this.pegi = pegi;
        this.tematica = tematica;
        this.precio = precio;
        this.plataforma = plataforma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPegi() {
        return pegi;
    }

    public void setPegi(String pegi) {
        this.pegi = pegi;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public String toString() {
        return "Videojuego{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pegi='" + pegi + '\'' +
                ", tematica='" + tematica + '\'' +
                ", precio=" + precio +
                ", plataforma='" + plataforma + '\'' +
                '}';
    }
}
