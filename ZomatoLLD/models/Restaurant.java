package models;

import java.util.ArrayList;

public class Restaurant {

    private static int nextRestraunt;
    int restrauntId=0;
    private String name;
    private String location;
    ArrayList<MenuItem> listOfMenu = new ArrayList<>();

    public Restaurant(String name, String location) {
        this.name = name;
        this.location = location;
        this.restrauntId = ++nextRestraunt;
    }

    public String getName() {
        return name;
    }

    public void setName(String nm) {
        this.name = nm;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String nm) {
        this.location = nm;
    }

    public ArrayList<MenuItem> getMenu() {
        return listOfMenu;
    }

    public void addMenuItem(MenuItem item) {
        listOfMenu.add(item);
    }
}
