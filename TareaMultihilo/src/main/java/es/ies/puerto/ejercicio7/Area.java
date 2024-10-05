package es.ies.puerto.ejercicio7;

import java.util.Objects;

public class Area {
    private String nombre;
    private boolean saved = false;


    public Area() {
        nombre = "";
    }

    public Area(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return Objects.equals(nombre, area.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        return "Area{" +
                "nombre='" + nombre + '\'' +
                ", saved=" + saved +
                '}';
    }
}
