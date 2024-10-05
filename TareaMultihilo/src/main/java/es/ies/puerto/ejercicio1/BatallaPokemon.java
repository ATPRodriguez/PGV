package es.ies.puerto.ejercicio1;

import java.util.Random;

/**
 * Crea una simulación de batalla Pokémon en la que dos Pokémon (Pikachu y Charmander) luchan entre sí.
 * Cada hilo representa a un Pokémon que alterna ataques y recibe daño hasta que uno de ellos se queda sin puntos de vida (HP).
 * Deberás lanzar dos hilos y hacer que se detengan cuando uno de los Pokémon gane.
 */
public class BatallaPokemon implements Runnable {
    private String nombre;
    private int vida = 100;
    private static int GOAL = 0;
    private static boolean loserDeclared = false;

    public BatallaPokemon(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public void run() {
        Random random = new Random();
        while (vida > GOAL && !loserDeclared) {
            int danioRecibido = random.nextInt(20);
            vida -= danioRecibido;
            if (danioRecibido != 0) {
            System.out.println(nombre + " ha recibido " + danioRecibido + " danio, vida actual: " + vida);
            } else {
                System.out.println(nombre + " ha esquivado el ataque");
            }
            if (vida <= GOAL && !loserDeclared) {
                loserDeclared = true;
                System.out.println(nombre + " ha sido derrotado!");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread pikachu = new Thread(new BatallaPokemon("Pikachu"));
        Thread charmander = new Thread(new BatallaPokemon("Charmander"));

        pikachu.start();
        charmander.start();
    }
}