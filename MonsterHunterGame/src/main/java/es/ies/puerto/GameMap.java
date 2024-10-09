package es.ies.puerto;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class GameMap {
    private int[][] map;
    private int size;
    private ConcurrentHashMap<String, String> locations;
    private final static int defaultSize = 10;

    public GameMap() {
        map = new int[defaultSize][defaultSize];
        size = defaultSize;
        locations = new ConcurrentHashMap<>();
    }

    public GameMap(int size) {
        this.size = size;
        this.map = new int[this.size][this.size];
        locations = new ConcurrentHashMap<>();
    }

    public GameMap(int size, ConcurrentHashMap<String, String> ubicaciones) {
        this.size = size;
        this.map = new int[this.size][this.size];
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
        int locationX = new Random().nextInt(size);
        int locationY = new Random().nextInt(size);
        return locationX+","+locationY;
    }

    public void moveHunter(Hunter hunter, String newLocation) {
        Random random = new Random();
        hunter.setLocation(newLocation);
        locations.replace(hunter.getHunterName(), hunter.getLocation(), newLocation);
    }

    public void addMonster(Monster monster, String location) {
            locations.put(monster.getMonsterName(), location);
    }

    public void removeMonster(Monster monster, String location) {
        locations.remove(monster.getMonsterName(), location);
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
        final char celdaVacia = '·';
        final char cazador = 'C';
        final char monstruo = 'M';
        String resultado = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String position = i+","+j;
            }
        }
        return resultado;
    }
}