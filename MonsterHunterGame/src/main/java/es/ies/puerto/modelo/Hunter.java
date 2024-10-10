package es.ies.puerto.modelo;

import java.util.Objects;

public class Hunter extends Thread{
    private String hunterName;
    private String location;

    public Hunter () {
        hunterName = "";
        location = "";
    }

    public Hunter (String hunterName) {
        this.hunterName = hunterName;
        location = "";
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

    @Override
    public void run() {

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
