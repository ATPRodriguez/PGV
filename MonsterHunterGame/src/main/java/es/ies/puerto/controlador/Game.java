
package es.ies.puerto.controlador;

import es.ies.puerto.modelo.Hunter;
import es.ies.puerto.modelo.GameMap;
import es.ies.puerto.modelo.Monster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Game {

    GameMap gameMap;
    List<Monster> monstersList;
    List<Hunter> huntersList;

    public Game() {
        gameMap = new GameMap();
        monstersList = new ArrayList<>();
        huntersList = new ArrayList<>();
    }


    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public List<Monster> getMonstersList() {
        return monstersList;
    }

    public void setMonstersList(List<Monster> monstersList) {
        this.monstersList = monstersList;
    }

    public List<Hunter> getHuntersList() {
        return huntersList;
    }

    public void setHuntersList(List<Hunter> huntersList) {
        this.huntersList = huntersList;
    }


    public static void main(String[] args) {
        GameMap gameMap = new GameMap(5);

        Monster monster1 = new Monster("monster1", gameMap.generateLocations());
        Monster monster2 = new Monster("monster2", gameMap.generateLocations());

        Hunter hunter1 = new Hunter("hunter1", gameMap);
        Hunter hunter2 = new Hunter("hunter2", gameMap);

        hunter1.setGameMap(gameMap);
        hunter2.setGameMap(gameMap);

        monster1.setGameMap(gameMap);
        monster2.setGameMap(gameMap);

        List<Monster> monsterList = new ArrayList<>(List.of(monster1, monster2));
        List<Hunter> hunterList = new ArrayList<>(List.of(hunter1, hunter2));

        Game game = new Game();
        game.setGameMap(gameMap);
        game.setHuntersList(hunterList);
        game.setMonstersList(monsterList);

        hunter1.setLocation(gameMap.generateLocations());
        hunter2.setLocation(gameMap.generateLocations());

        game.getGameMap().addHunter(hunter1, hunter1.getLocation());
        game.getGameMap().addHunter(hunter2, hunter2.getLocation());

        game.getGameMap().addMonster(monster1, monster1.getLocation());
        game.getGameMap().addMonster(monster2, monster2.getLocation());

        Thread hunterThread = new Thread(hunter1);
        Thread hunter2Thread = new Thread(hunter2);

        Thread monsterThread = new Thread(monster1);
        Thread monster2Thread = new Thread(monster2);

        hunterThread.start();
        hunter2Thread.start();

        monsterThread.start();
        monster2Thread.start();

    }
}
