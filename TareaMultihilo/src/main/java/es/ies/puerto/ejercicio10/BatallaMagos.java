package es.ies.puerto.ejercicio10;

import java.util.Random;

/**
 * Simula una batalla mágica entre Gandalf y Saruman.
 * Cada mago lanza hechizos que reducen la energía del otro mago, usando dos hilos.
 * El primer mago que agote por completo la energía del otro gana la batalla.
 * Los hechizos deben ser lanzados a intervalos de tiempo aleatorios.
 */
public class BatallaMagos implements Runnable {
    private String nombre;
    private static int energiaGandalf = 100;
    private static int energiaSaruman = 100;
    private static boolean loserDeclared = false;

    public BatallaMagos(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!loserDeclared) {
            switch (nombre) {
                case "Gandalf":
                    try {
                        Thread.sleep((random.nextInt(5)+1) * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!loserDeclared) {
                        int danioHechizoGandalf = random.nextInt(15) + 10;
                        energiaSaruman -= danioHechizoGandalf;
                        System.out.println("Gandalf ha infligido " + danioHechizoGandalf + " de danio a Saruman");
                    }
                    if (energiaSaruman <= 0 && !loserDeclared) {
                        System.out.println("Saruman ha sido derrotado");
                        loserDeclared = true;
                    }
                    break;
                case "Saruman":
                    try {
                        Thread.sleep((random.nextInt(5)+1) * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!loserDeclared) {
                        int danioHechizoSaruman = random.nextInt(15) + 10;
                        energiaGandalf -= danioHechizoSaruman;
                        System.out.println("Saruman ha infligido " + danioHechizoSaruman + " de danio a Gandalf");
                    }
                    if (energiaGandalf <= 0 && !loserDeclared) {
                        System.out.println("Gandalf ha sido derrotado");
                        loserDeclared = true;
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Thread gandalf = new Thread(new BatallaMagos("Gandalf"));
        Thread saruman = new Thread(new BatallaMagos("Saruman"));

        gandalf.start();
        saruman.start();
    }
}
