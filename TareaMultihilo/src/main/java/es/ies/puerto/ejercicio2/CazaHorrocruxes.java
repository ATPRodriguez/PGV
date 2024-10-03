package es.ies.puerto.ejercicio2;

import java.util.Random;

/**
 * Imagina que Harry, Hermione y Ron están buscando Horrocruxes. Cada personaje está representado por un hilo
 * que busca en una ubicación diferente del mundo.
 * El primer hilo que encuentra un Horrocrux debe detener a los demás y terminar la búsqueda.
 * Elige el tiempo de búsqueda de manera aleatoria para cada hilo.
 */
public class CazaHorrocruxes implements Runnable{
    private String nombre;
    private int pasos = 0;
    private static boolean found = false;
    private boolean cansado = false;
    private Region region;

    private long timeInitiated = System.currentTimeMillis();
    private long timeMax;

    public CazaHorrocruxes() {
        nombre = "";
        region = new Region();
    }
    public CazaHorrocruxes(String nombre, Region region, long timeMax) {
        this.nombre = nombre;
        this.region = region;
        this.timeMax = timeMax;
    }

    public void run() {
        Random random = new Random();
        while (pasos < region.getDistanciaHorrocrux() && !found) {
            long tiempoBusqueda = System.currentTimeMillis() - timeInitiated;
            if (tiempoBusqueda >= timeMax) {
                System.out.println(nombre + " se ha cansado de buscar");
                break;
            }
            int pasosDados = random.nextInt(20) + 10;
            pasos += pasosDados;
            System.out.println(nombre + " ha avanzado " + pasos + " pasos, tiempo de busqueda: " + tiempoBusqueda/100 + " minutos");
            if (pasos >= region.getDistanciaHorrocrux() && !found && region.getHorrocrux()) {
                found = true;
                System.out.println(nombre + " ha encontrado un horrocrux!");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();

        Region azkhaban = new Region("Azkhaban");
        Region mansion = new Region("Mansion Malfoy");
        Region hogwarts = new Region("Hogwarts", true);


        Thread hermione = new Thread(new CazaHorrocruxes("Hermione Granger", mansion,
                (random.nextInt(10) +1) * 1000));
        Thread ron = new Thread(new CazaHorrocruxes("Ron Wisley", azkhaban,
                (random.nextInt(10) +1) * 1000));
        Thread harry = new Thread(new CazaHorrocruxes("Harry Potter", hogwarts,
                (random.nextInt(10) +1) * 1000));

        hermione.start();
        ron.start();
        harry.start();
    }
}
