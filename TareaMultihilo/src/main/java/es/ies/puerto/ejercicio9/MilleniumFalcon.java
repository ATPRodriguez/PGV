package es.ies.puerto.ejercicio9;

import java.util.Random;

/**
 * Crea una simulación del Millenium Falcon,
 * donde Han Solo y Chewbacca están controlando la nave en medio de una batalla espacial.
 * Un hilo representa a Han Solo controlando la velocidad,
 * mientras que otro hilo representa a Chewbacca manejando los escudos.
 * Si alguno de los sistemas falla, la nave podría ser destruida.
 */
public class MilleniumFalcon implements Runnable {
    public String nombre;
    public static boolean escudos = true;
    public static boolean velocidad = true;
    private int distanciaAvanzada = 0;
    private static boolean goalReached = false;
    private static int goal = 100;

    public MilleniumFalcon(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (naveFuncional() && !goalReached) {
            switch (nombre) {
                case "Han Solo":
                    if (random.nextInt(10) + 1 == 1) {
                        System.out.println("El propulsor ha sido golpeado, la nave ha sido destruida");
                        velocidad = false;
                    }
                    if (distanciaAvanzada < goal && naveFuncional()) {
                        distanciaAvanzada += random.nextInt(20) + 10;
                        System.out.println("El Halcon milenario ha avanzado " + distanciaAvanzada + " anios luz");
                    } else if (distanciaAvanzada >= goal && naveFuncional()){
                        System.out.println("El Halcon milenario ha escapado del campo de batalla");
                        goalReached = true;
                    }
                    break;
                case "Chewbacca":
                    if (random.nextInt(10) + 1 == 1) {
                        System.out.println("Los escudos han sido golpeados, la nave ha sido destruida");
                        escudos=false;
                    }
                    break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread hanSolo = new Thread(new MilleniumFalcon("Han Solo"));
        Thread chewbacca = new Thread(new MilleniumFalcon("Chewbacca"));

        hanSolo.start();
        chewbacca.start();
    }

    public boolean naveFuncional() {
        return escudos && velocidad;
    }
}