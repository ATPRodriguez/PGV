package es.ies.puerto.ejercicio8;

import java.util.Random;

/**
 * Simula una competencia de fuerza entre Thor y Hulk.
 * Ambos héroes tienen que levantar pesas (representados por incrementos de peso levantado en cada iteración del hilo)
 * durante un tiempo limitado. Al final, el hilo que haya levantado más peso gana la competencia.
 */
public class ThorHulk implements Runnable{
    private String nombre;
    private static int pesasHulk = 0;
    private static int pesasThor = 0;
    public static boolean winnerDeclared = false;
    private long tiempoMax;
    Random random = new Random();

    public ThorHulk(String nombre, long tiempoMax) {
        this.nombre = nombre;
        this.tiempoMax = tiempoMax + System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (System.currentTimeMillis() < tiempoMax) {
            switch (nombre.toLowerCase()) {
                case "hulk":
                    if (random.nextBoolean()) {
                        pesasHulk++;
                    }
                    System.out.println("Hulk ha levantado " + pesasHulk + " pesas");
                case "thor":
                    if (random.nextBoolean()) {
                        pesasThor++;
                    }
                    System.out.println("Thor ha levantado " + pesasThor + " pesas");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!winnerDeclared) {
            if (pesasThor < pesasHulk) {
                System.out.println("Hulk ha ganado la competicion");
                winnerDeclared = true;
            } else if (pesasHulk < pesasThor) {
                System.out.println("Thor ha ganado la competicion");
                winnerDeclared = true;
            } else {
                System.out.println("Thor y Hulk han levantado las mismas pesas");
                winnerDeclared = true;
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        long tiempoCompeticion = ((random.nextInt(10) + 5) * 1000);
        Thread thor = new Thread(new ThorHulk("Thor", tiempoCompeticion));
        Thread hulk = new Thread(new ThorHulk("Hulk", tiempoCompeticion));

        thor.start();
        hulk.start();
    }
}
