package Tinder.models;

public class Interest {
    private String name;
    private String category;

    public Interest() {
        name = "";
        category = "";
    }

    public Interest(String n, String c) {
        name = n;
        category = c;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
