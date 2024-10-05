package es.ies.puerto.ejercicio5;

import java.util.Random;

/**
 * Crea una simulación donde dos exploradores Jedi,
 * representados por hilos, viajan por el espacio buscando pistas sobre el Lado Oscuro.
 * Cada hilo busca en diferentes planetas,
 * y el primer explorador que encuentre una pista debe detener al otro.
 * Implementa tiempos de búsqueda aleatorios para cada Jedi.
 */
public class Jedis implements Runnable{
    private String nombre;
    private int progreso = 0;
    private static final int GOAL = 100;
    private static boolean winnerDeclared = false;

    public Jedis(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (progreso < GOAL && !winnerDeclared) {
            int pistas = random.nextInt(15) + 10;
            progreso += pistas;
            System.out.println(nombre + " ha encontrado " + pistas + " pistas. Pistas recogidas: " + progreso);

            if (progreso >= GOAL && !winnerDeclared) {
                winnerDeclared = true;
                System.out.println(nombre + " ha encontrado todas las pistas!");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread obiwan = new Thread(new Jedis("Obi Wan Kenobi"));
        Thread anakin = new Thread(new Jedis("Anakin"));

        obiwan.start();
        anakin.start();
    }
}
