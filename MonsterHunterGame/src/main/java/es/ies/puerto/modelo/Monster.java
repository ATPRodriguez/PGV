package es.ies.puerto.modelo;

import java.util.Objects;

public class Monster extends Thread{
    private  String monsterName;
    private String location;
    private boolean catched;
    private GameMap gameMap;
    private static long TIME = 10000;

    public Monster() {
        monsterName = "";
        location = "";
        catched = false;
        this.gameMap = new GameMap();
    }

    public Monster(String monsterName, GameMap gameMap) {
        this.monsterName = monsterName;
        location = "";
        catched = false;
        this.gameMap = gameMap;
    }

    public Monster(String monsterName, String location) {
        this.monsterName = monsterName;
        this.location = location;
        catched = false;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
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

    public static long getTIME() {
        return TIME;
    }

    public static void setTIME(long TIME) {
        Monster.TIME = TIME;
    }

    public boolean isCatched() {
        return catched;
    }

    public void setCatched(boolean catched) {
        this.catched = catched;
    }

    @Override
    public void run() {
        long initialTime = System.currentTimeMillis();
        long timePassed = 0;

        boolean gameOver = false;

        gameMap.addMonster(this, this.getLocation());

        while (!gameOver && !gameMap.getMonsters().isEmpty() && timePassed < TIME && !isCatched()) {
            gameMap.moveMonster(this);

            long endTime = System.currentTimeMillis();
            timePassed = (endTime - initialTime);

            if (timePassed >= TIME){
                System.out.println("Time's up!");
                gameOver = true;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return Objects.equals(monsterName, monster.monsterName) && Objects.equals(location, monster.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monsterName, location);
    }

    @Override
    public String toString() {
        return "Monster{" +
                "monsterName='" + monsterName + '\'' +
                ", location='" + location + '\'' +
                ", catched=" + catched +
                '}';
    }
}
