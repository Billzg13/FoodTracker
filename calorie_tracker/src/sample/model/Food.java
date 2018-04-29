package sample.model;

public class Food {

    private String name; //name of food
    private double grams; //grams of food
    private int id; //uid of food

    public Food() {
    }

    public Food(String name, double grams, int id) {
        this.name = name;
        this.grams = grams;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
} //end of food **
