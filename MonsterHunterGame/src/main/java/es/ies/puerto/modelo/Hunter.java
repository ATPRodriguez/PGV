package es.ies.puerto.modelo;

import java.util.Objects;

public class Hunter extends Thread {
    private String hunterName;
    private String location;
    private GameMap gameMap;
    private static long TIME = 10000;


    public Hunter (){
        hunterName = "";
        location ="0,0";
        gameMap = new GameMap();
    }

    public Hunter(String hunterName, GameMap gameMap) {
        this.hunterName = hunterName;
        location = "0,0";
        this.gameMap = gameMap;
    }

    public String getHunterName() {
        return hunterName;
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
        long initialTime = System.currentTimeMillis();
        long timePassed = 0;

        boolean gameOver = false;

        gameMap.addHunter(this, this.getLocation());

        while (!gameOver && !gameMap.getMonsters().isEmpty() && timePassed < TIME) {
            gameMap.moveHunter(this);

            long endTime = System.currentTimeMillis();
            timePassed = (endTime - initialTime);

            if (timePassed >= TIME){
                System.out.println("Time's up!");
                gameOver = true;
            }


            for (Monster monster : gameMap.getMonsters()) {
                if (monster.getLocation().equals(this.getLocation()) && !monster.isCatched()) {
                    monster.setCatched(true);
                    System.out.println(getHunterName() + " caught " + monster.getMonsterName());
                    gameMap.huntMonster(gameMap.getMonsters(), this);
                    gameMap.getMonsters().remove(monster);
                    break;
                }
            }

            for (Monster monster : gameMap.getMonsters()) {
                if (!monster.isCatched() && timePassed >= 50000 && timePassed < TIME) {
                    gameMap.huntMonster(gameMap.getMonsters(), this);
                    System.out.println(monster.getMonsterName() + " has fled");
                }
            }



            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(hunterName + " interrupted");
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
        return "Hunter{" +
                "hunterName='" + hunterName + '\'' +
                ", position='" + location + '\'' +
                '}';
    }

}