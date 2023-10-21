package za.ac.cput.domain;

public class Snack {
    private int id;
    private String name;
    private double price;
    private String category;
    private int calories;

    public Snack() {

    }

    public Snack(String name, double price, String category, int calories) {
        this.name = name;
        this.price = price;

        this.category = category;
        this.calories = calories;
    }

    public Snack(int id, String name, double price, String category, int calories) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Snack{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", calories=" + calories +
                '}';
    }
}
