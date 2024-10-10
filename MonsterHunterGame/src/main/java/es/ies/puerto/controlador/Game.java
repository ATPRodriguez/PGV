package es.ies.puerto.controlador;

import es.ies.puerto.modelo.GameMap;
import es.ies.puerto.modelo.Hunter;
import es.ies.puerto.modelo.Monster;

public class Game {
    private GameMap gameMap;
    private Hunter hunter;
    private Monster monster;

    public Game() {
        gameMap = new GameMap();
        hunter = new Hunter();
        monster = new Monster();
    }

    public Game(int mapSize, Hunter hunter, Monster monster) {
        this.gameMap = new GameMap(mapSize);
        this.hunter = hunter;
        this.monster = monster;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public Hunter getHunter() {
        return hunter;
    }

    public void setHunter(Hunter hunter) {
        this.hunter = hunter;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public static void main(String[] args) {
        Hunter hunter1 = new Hunter("Hunter1");
        Monster monster1 = new Monster("Monster1");
        Game game = new Game(5, hunter1, monster1);
        generarUbicaciones(game);
        while (hunter1.getLocation().equals(monster1.getLocation())) {
            generarUbicaciones(game);
        }
        game.getGameMap().addHunter(hunter1, hunter1.getLocation());
        game.getGameMap().addMonster(monster1, monster1.getLocation());
        System.out.println(game.getGameMap());
        game.getGameMap().moveHunter(hunter1, "2,1");
        System.out.println(game.getGameMap());

    }

    public static boolean generarUbicaciones(Game game) {
        game.getHunter().setLocation(game.getGameMap().generateLocation());
        game.getMonster().setLocation(game.getGameMap().generateLocation());
        return true;
    }

    /**
     * TODO
     * Metodo rastrearMonstruo() : si el monstruo esta en una casilla adyacente se mueve directamente a esta
     */
}
