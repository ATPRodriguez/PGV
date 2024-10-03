package es.ies.puerto.ejercicio2;

import java.util.Objects;

public class Region {
    private String nombre;
    private boolean horrocrux;
    private final int distanciaHorrocrux = 100;

    public Region(){}

    public Region(String nombre) {
        this.nombre = nombre;
        this.horrocrux = false;
    }

    public Region(String nombre, boolean horrocrux) {
        this.nombre = nombre;
        this.horrocrux = horrocrux;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getHorrocrux() {
        return horrocrux;
    }

    public void setHorrocrux(boolean horrocrux) {
        this.horrocrux = horrocrux;
    }

    public int getDistanciaHorrocrux() {
        return distanciaHorrocrux;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(nombre, region.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
    
}
