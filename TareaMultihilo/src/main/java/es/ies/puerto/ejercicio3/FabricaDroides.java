package es.ies.puerto.ejercicio3;

import java.util.ArrayList;

/**
 * Simula una fábrica de droides en la que se están ensamblando droides de batalla.
 * Un hilo se encarga de ensamblar los droides, mientras que otro hilo se encarga de activarlos.
 * Asegúrate de que los droides no se activen antes de ser completamente ensamblados,
 * usando mecanismos de sincronización entre hilos.
 */
public class FabricaDroides implements Runnable {
    private String nombre;
    static ArrayList<Droide> droides = new ArrayList<>();

    public FabricaDroides(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public synchronized void run() {
        int aux = 0;
        while (aux < droides.size()) {
            switch (nombre) {
                case "ensamblador":
                    droides.get(aux).setEnsamblado(true);
                    System.out.println("El droide " + droides.get(aux).getNombre() + " ha sido ensamblado");
                    break;
                case "activador":
                    if(droides.get(aux).isEnsamblado()) {
                        System.out.println("El droide" + droides.get(aux).getNombre() + " ha sido activado");
                        droides.get(aux).setActivado(true);
                    }
            }
            aux++;
        }
    }

    public static void main(String[] args) {
        FabricaDroides.droides.add(new Droide("1"));
        FabricaDroides.droides.add(new Droide("2"));
        FabricaDroides.droides.add(new Droide("3"));

        Thread ensamblador = new Thread(new FabricaDroides("ensamblador"));
        Thread activador = new Thread(new FabricaDroides("activador"));

        try {
            ensamblador.start();
            ensamblador.join();
            activador.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
