package es.ies.puerto.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class GameMap {
    private int size;
    private String [][] map;
    private List<Hunter> hunters;
    private List<Monster> monsters;
    private List<Cave> caves;
    private ConcurrentHashMap<String, String> locations;
    private static final int DEFAULT_SIZE = 10;

    public GameMap() {
        size = DEFAULT_SIZE;
        monsters = new ArrayList<>();
        hunters = new ArrayList<>();
        caves = new ArrayList<>();
        locations = new ConcurrentHashMap<>();
        map = new String[size][size];
        fillMap();
    }

    public GameMap(int size) {
        this.size = size;
        monsters = new ArrayList<>();
        hunters = new ArrayList<>();
        caves = new ArrayList<>();
        locations = new ConcurrentHashMap<>();
        map = new String[size][size];
        fillMap();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ConcurrentHashMap<String, String> getLocations() {
        return locations;
    }

    public void setLocations(ConcurrentHashMap<String, String> locations) {
        this.locations = locations;
    }

    public String[][] getMap() {
        return map;
    }

    public void setMap(String[][] map) {
        this.map = map;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public List<Hunter> getHunters() {
        return hunters;
    }

    public void setHunters(List<Hunter> hunters) {
        this.hunters = hunters;
    }

    public List<Cave> getCaves() {
        return caves;
    }

    public void setCaves(List<Cave> caves) {
        this.caves = caves;
    }

    public String generateLocations(){
        Random random = new Random();
        int x = random.nextInt(size);
        int y = random.nextInt(size);
        return x + "," + y;
    }


    public synchronized void moveHunter(Hunter hunter){
        Random random = new Random();
        String[] positions = hunter.getLocation().split(",");
        int x = Integer.parseInt(positions[0]);
        int y = Integer.parseInt(positions[1]);
        boolean moverse = random.nextBoolean();
        boolean direccion = random.nextBoolean();

        if (x == 0 && moverse) {
            x++;
        } else if (x<(size-1) && moverse && !direccion) {
            x--;
        } else if (x== (size-1) && moverse) {
            x--;
        } else if (x<(size-1) &&  moverse && direccion){
            x++;
        }

        moverse = random.nextBoolean();
        direccion = random.nextBoolean();

        if (y == 0 && moverse) {
            y++;
        } else if (y<(size-1) && moverse && !direccion) {
            y--;
        } else if (y== (size-1) && moverse) {
            y--;
        } else if (x<(size-1) && moverse && direccion) {
            y++;
        }

        switch (map[x][y]) {
            case " · ": {
                map[x][y] = " H ";
                String[] position = hunter.getLocation().split(",");
                map[Integer.parseInt(position[0])][Integer.parseInt(position[1])] = " · ";
                hunter.setLocation(x + "," + y);
                showMap();
                break;
            }
            case " M ": {
                huntMonster(monsters, hunter);
                map[x][y] = " H ";

                String[] position = hunter.getLocation().split(",");
                map[Integer.parseInt(position[0])][Integer.parseInt(position[1])] = " · ";
                hunter.setLocation(x + "," + y);
                showMap();
                break;
            }
            case " H ":
            case " C ":
                moveHunter(hunter);
                break;
        }

    }

    public synchronized void moveMonster(Monster monster){
        Random random = new Random();
        String[] positions = monster.getLocation().split(",");
        int x = Integer.parseInt(positions[0]);
        int y = Integer.parseInt(positions[1]);
        boolean moverse = random.nextBoolean();
        boolean direccion = random.nextBoolean();

        if (x == 0 && moverse) {
            x++;
        } else if (x<(size-1) && moverse && !direccion) {
            x--;
        } else if (x== (size-1) && moverse) {
            x--;
        } else if (x<(size-1) &&  moverse && direccion){
            x++;
        }

        moverse = random.nextBoolean();
        direccion = random.nextBoolean();

        if (y == 0 && moverse) {
            y++;
        } else if (y<(size-1) && moverse && !direccion) {
            y--;
        } else if (y== (size-1) && moverse) {
            y--;
        } else if (x<(size-1) && moverse && direccion) {
            y++;
        }
        moveMonster(monster, x, y);
    }

    private void moveMonster(Monster monster, int x, int y) {
        switch (map[x][y]) {
            case " · ": {
                map[x][y] = " M ";
                String[] position = monster.getLocation().split(",");
                map[Integer.parseInt(position[0])][Integer.parseInt(position[1])] = " · ";
                monster.setLocation(x + "," + y);
                showMap();
                break;
            }
            case " M ": {
                moveMonster(monster);
                break;
            }
            case " H ":
                runAway(monster, x, y);
                break;
        }
    }

    private void runAway (Monster monster,int x, int y) {
        String[] monsterPosition = monster.getLocation().split(",");
        int xMonster = Integer.parseInt(monsterPosition[0]);
        int yMonster = Integer.parseInt(monsterPosition[1]);

        if (xMonster == (size-1) || yMonster == (size-1) || xMonster == 0 || yMonster == 0) {
            System.out.println(monster.getMonsterName() + " is cornered and couldnt move");
        } else {
            System.out.println(monster.getMonsterName() + " tries to run away from a hunter");
            moveMonster(monster, x+(xMonster-x), y+(yMonster-y));
        }
    }

    private void fillMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.map[i][j] = " · ";
            }
        }
    }

    private void showMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    public synchronized void addHunter(Hunter hunter, String location){
        if (checkPositionsOverlap(location)) {
            String[] positions = location.split(",");
            int row = Integer.parseInt(positions[0]);
            int col = Integer.parseInt(positions[1]);

            map[row][col] = " H ";

            locations.put(hunter.getHunterName(), location);
            hunters.add(hunter);
        }
    }

    public void addCave(Cave cave, String location){
        if (checkPositionsOverlap(location)) {
            String[] positions = location.split(",");
            int row = Integer.parseInt(positions[0]);
            int col = Integer.parseInt(positions[1]);

            map[row][col] = " C ";

            locations.put(cave.getId(), location);
            caves.add(cave);
        }
    }

    public boolean checkPositionsOverlap(String position){
        return !locations.containsValue(position);
    }

    public synchronized void addMonster(Monster monster, String location){
        if (checkPositionsOverlap(location)) {
            String[] positions = location.split(",");
            int row = Integer.parseInt(positions[0]);
            int col = Integer.parseInt(positions[1]);

            map[row][col] = " M ";

            locations.put(monster.getMonsterName(), location);
            monsters.add(monster);
        }
    }


    public synchronized void huntMonster(List<Monster> monsters, Hunter hunter) {
        for (Monster monster : monsters) {
            if (hunter.getLocation().equals(monster.getLocation())) {
                monster.setCatched(true);
                locations.remove(monster.getMonsterName());
                monsters.remove(monster);
                System.out.println("Remaining monsters: " + getMonsters().size());
                return;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameMap mapGame = (GameMap) o;
        return Objects.equals(locations, mapGame.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locations);
    }

    @Override
    public synchronized String toString() {
        String message = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                switch (map[i][j]) {
                    case "H":
                        message += "H";
                        break;
                    case "M":
                        message += "M";
                        break;
                    default:
                        message += ".";
                        break;
                }
            }
            message += "\n";
        }
        return message;
    }

}