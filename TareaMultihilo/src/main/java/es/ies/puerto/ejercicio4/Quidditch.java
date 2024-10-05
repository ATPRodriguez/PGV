package es.ies.puerto.ejercicio4;

import java.util.Random;

public class Quidditch implements Runnable{
    private String nombre;
    private int puntos = 0;
    private int snitch = 0;
    private static final int GOAL = 100;
    private static boolean winnerDeclared = false;
    private boolean isFinder;

    public Quidditch() {}

    public Quidditch(String nombre, boolean isFinder) {
        this.nombre = nombre;
        this.isFinder = isFinder;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isFinder() {
        return isFinder;
    }

    public void setFinder(boolean finder) {
        isFinder = finder;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (puntos < GOAL && snitch < GOAL && !winnerDeclared) {
            if (!isFinder) {
                int incrementPoints = random.nextInt(10) + 1;
                puntos =+ incrementPoints;
                System.out.println(getNombre() + " has " + puntos + " points.");
            }

            int progressAdd = random.nextInt(10) + 1;
            snitch += progressAdd;

            System.out.println("Snitch atrapada por " + nombre + ": " + snitch + "%");

            if (puntos >= GOAL || snitch >= GOAL && !winnerDeclared) {
                winnerDeclared = true;
                System.out.println(nombre + " ha ganado");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread jugador1 = new Thread(new Quidditch("jugador1", false));
        Thread jugador2 = new Thread(new Quidditch("jugador2", false));
        Thread jugador3 = new Thread(new Quidditch("jugador3", false));
    }

}
