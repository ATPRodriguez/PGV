package es.ies.puerto.ejercicio6;

import java.util.Random;

/**
 * Simula viajes en el tiempo de la TARDIS con varios hilos que representan diferentes épocas.
 * Cada hilo debe intentar llegar al destino más rápido que los demás.
 * La duración de cada viaje debe ser aleatoria y el destino final se alcanza cuando uno de los hilos termina su ejecución.
 */
public class Tardis implements Runnable {
    private String nombre;
    private int progreso = 0;
    private static int GOAL = 0;
    private static boolean winnerDeclared = false;

    public Tardis(String nombre) {
        Random random = new Random();
        this.nombre = nombre;
        this.GOAL = random.nextInt(150)+100;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (progreso < GOAL && !winnerDeclared) {
            int tiempo = random.nextInt(15) + 10;
            progreso += tiempo;
            System.out.println("La TARDIS ha avanzado " + progreso + " decadas hacia la " + nombre);

            if (progreso >= GOAL && !winnerDeclared) {
                winnerDeclared = true;
                System.out.println("La TARDIS ha llegado a la " + nombre + "!");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread epocaPrehistorica = new Thread(new Tardis("Epoca Prehistorica"));
        Thread epocaAntigua = new Thread(new Tardis("Epoca Antigua"));

        epocaPrehistorica.start();
        epocaAntigua.start();
    }
}
