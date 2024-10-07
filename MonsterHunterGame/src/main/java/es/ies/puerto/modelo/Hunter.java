package es.ies.puerto.modelo;

import java.util.Random;

public class Hunter extends Thread{
    private String name;
    private int locationX;
    private int locationY;

    @Override
    public void run() {
        //Movimiento aleatorio
        locationX = moverse(locationX);
        locationY = moverse(locationY);

    }

    public int moverse (int location) {
        Random random = new Random();
        if (location > 0) {
            switch (random.nextInt(3)) {
                case 0:
                    location--;
                    break;
                case 2:
                    location++;
                    break;
                default:
                    break;

            }
        } else if (random.nextInt(2) == 1) {
                location++;
        }
        return location;
    }
}
