package es.ies.puerto.ejercicio7;

import java.util.ArrayList;
import java.util.Random;

/**
 * Crea una simulación donde Superman y Batman intentan salvar diferentes zonas de la ciudad de una amenaza.
 * Cada superhéroe es un hilo que representa el esfuerzo por salvar una serie de áreas.
 * Si uno de los superhéroes salva todas sus áreas primero,
 * la amenaza será neutralizada y el otro superhéroe deberá detenerse.
 */
public class Superheroes implements Runnable {
    private String nombre;
    private static boolean threatNeutralized = false;
    private ArrayList<Area> areas;

    public Superheroes() {
        nombre = "";
        areas = new ArrayList<Area>();
    }

    public Superheroes(String nombre, ArrayList<Area> areas) {
        this.nombre = nombre;
        this.areas = areas;
    }

    @Override
    public void run() {
        int currentArea = 0;
        while (!areasSalvadas(areas) && !threatNeutralized) {

            if (!areas.get(currentArea).isSaved()) {
                System.out.println(nombre + " ha salvado el " + areas.get(currentArea).getNombre());
                areas.get(currentArea).setSaved(true);
            }

            if (areasSalvadas(areas) && !threatNeutralized) {
                threatNeutralized = true;
                System.out.println(nombre + " ha salvado la ciudad!");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentArea++;
        }
    }

    public static void main(String[] args) {
        ArrayList<Area> areasBatman = generarAreas();
        ArrayList<Area> areasSuperman = generarAreas();

        Thread batman = new Thread(new Superheroes("Batman", areasBatman));
        Thread superman = new Thread(new Superheroes("Superman", areasSuperman));

        batman.start();
        superman.start();
    }

    public boolean areasSalvadas(ArrayList<Area> areas) {
        for (Area area : areas) {
            if (!area.isSaved()) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Area> generarAreas() {
        Random random = new Random();
        ArrayList<Area> areas = new ArrayList<>();
        Area area;

        for (int i = 0; i < random.nextInt(15) + 7; i++) {
            area = new Area("area " + (i + 1));
            areas.add(area);
        }
        return areas;
    }

}