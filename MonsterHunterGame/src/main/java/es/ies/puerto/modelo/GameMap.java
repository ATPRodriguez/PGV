package es.ies.puerto.modelo;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class GameMap {
    private char[][] map;
    private int size;
    private ConcurrentHashMap<String, String> locations;
    private final static int DEFAULT_SIZE = 10;

    public GameMap() {
        size = DEFAULT_SIZE;
        map = new char[size][size];
        locations = new ConcurrentHashMap<>();
    }

    public GameMap(int size) {
        this.size = size;
        this.map = new char[this.size][this.size];
        locations = new ConcurrentHashMap<>();
    }

    public GameMap(int size, ConcurrentHashMap<String, String> ubicaciones) {
        this.size = size;
        this.map = new char[this.size][this.size];
        this.locations = ubicaciones;
    }

    public int getMap() {
        return map.length;
    }

    public ConcurrentHashMap<String, String> getLocations() {
        return locations;
    }

    public void setLocations(ConcurrentHashMap<String, String> locations) {
        this.locations = locations;
    }

    public String generateLocation() {
        Random random = new Random();
        int locationX = random.nextInt(size);
        int locationY = random.nextInt(size);
        return locationX+","+locationY;
    }

    public void moveHunter(Hunter hunter, String newLocation) {
        String[] posicion = hunter.getLocation().split(",");
        map[Integer.parseInt(posicion[0])][Integer.parseInt(posicion[1])] = ' ';
        hunter.setLocation(newLocation);
        locations.replace(hunter.getHunterName(), hunter.getLocation(), newLocation);
        posicion = hunter.getLocation().split(",");
        map[Integer.parseInt(posicion[0])][Integer.parseInt(posicion[1])] = 'C';
    }

    public void addHunter(Hunter hunter, String location) {
        locations.put(hunter.getHunterName(), location);
        String[] posiciones = location.split(",");
        map[Integer.parseInt(posiciones[0])][Integer.parseInt(posiciones[1])] = 'C';
    }

    public void addMonster(Monster monster, String location) {
        locations.put(monster.getMonsterName(), location);
        String[] posiciones = location.split(",");
        map[Integer.parseInt(posiciones[0])][Integer.parseInt(posiciones[1])] = 'M';
    }

    public void removeMonster(Monster monster, String location) {
        locations.remove(monster.getMonsterName(), location);
        String[] posiciones = location.split(",");
        map[Integer.parseInt(posiciones[0])][Integer.parseInt(posiciones[1])] = ' ';

    }

    public int move(int location) {
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
        } else if (random.nextBoolean()) {
            location++;
        }
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameMap gameMap = (GameMap) o;
        return Arrays.equals(map, gameMap.map) && Objects.equals(locations, gameMap.locations);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(locations);
        result = 31 * result + Arrays.hashCode(map);
        return result;
    }

    @Override
    public String toString() {
        String mensaje = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                switch (map[i][j]) {
                    case 'C':
                        mensaje += "C   ";
                        break;
                    case 'M':
                        mensaje += "M   ";
                        break;
                    default:
                        mensaje += "·   ";
                        break;
                }
            }
            mensaje += "\n";
        }
        return mensaje;
    }
}