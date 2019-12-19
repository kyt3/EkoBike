package abstract_;


import java.util.Objects;

//Abstract class bike. Contains all common parameters of bike, and some needed getters.
public abstract class Bike {
    protected String brand;
    protected String color;
    protected int weight;
    protected int price;
    protected boolean availabilityLights;

    //Constructor need to get all of parameters.
    public Bike(String brand, String color, int weight, int price, boolean availabilityLights) {
        this.brand = brand;
        this.color = color;
        this.weight = weight;
        this.price = price;
        this.availabilityLights = availabilityLights;
    }

    //String for writing file is different from string that we printing to users, so we need this method
    public abstract String getStringForFile();

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return weight == bike.weight &&
                price == bike.price &&
                availabilityLights == bike.availabilityLights &&
                Objects.equals(brand, bike.brand) &&
                Objects.equals(color, bike.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, color, weight, price, availabilityLights);
    }
}


