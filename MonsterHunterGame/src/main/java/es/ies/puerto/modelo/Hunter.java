package es.ies.puerto.modelo;

import java.util.Objects;

public class Hunter extends Thread{
    private String hunterName;
    private String location;
    private GameMap gameMap;

    public Hunter () {
        hunterName = "hunter";
        location = "0,0";
        gameMap = new GameMap();
    }

    public Hunter (String hunterName) {
        this.hunterName = hunterName;
        location = "0,0";
        gameMap = new GameMap();
    }

    public Hunter (String hunterName, int size) {
        this.hunterName = hunterName;
        this.gameMap = new GameMap(size);
    }

    public String getHunterName() {
        return hunterName;
    }

    public void setHunterName(String name) {
        this.hunterName = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void run() {
        while (!GameMap.getMonsters().isEmpty()) {
            String[] locations = this.getLocation().split(",");
            int locationX = Integer.parseInt(locations[0]);
            int locationY = Integer.parseInt(locations[1]);
            locationX = gameMap.move(locationX);
            locationY = gameMap.move(locationY);
            gameMap.moveHunter(this, locationX+","+locationY);
            System.out.println(gameMap);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hunter hunter = (Hunter) o;
        return Objects.equals(hunterName, hunter.hunterName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hunterName);
    }

    @Override
    public String toString() {
        return  hunterName + " is on the " + location + "square";
    }
}
