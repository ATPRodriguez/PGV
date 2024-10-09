package es.ies.puerto;

import java.util.Objects;

public class Monster {
    private  String monsterName;
    private String location;
    private boolean catched;

    public Monster() {
        monsterName = "";
        location = "";
        catched = false;
    }

    public Monster(String monsterName) {
        this.monsterName = monsterName;
        location = "";
        catched = false;
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

    public boolean isCatched() {
        return catched;
    }

    public void setCatched(boolean catched) {
        this.catched = catched;
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
