package es.ies.puerto.ejercicio3;

import java.util.Objects;

public class Droide {
    private String nombre;
    private boolean ensamblado = false;
    private boolean activado = false;

    public Droide(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEnsamblado() {
        return ensamblado;
    }

    public void setEnsamblado(boolean ensamblado) {
        this.ensamblado = ensamblado;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Droide droide = (Droide) o;
        return Objects.equals(nombre, droide.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}
