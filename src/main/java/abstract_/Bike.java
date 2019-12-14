package abstract_;

import java.util.Objects;

public abstract class Bike {
    protected String brand;
    protected String color;
    protected int weight;
    protected int price;
    protected boolean availabilityLights;

    public Bike(String brand, String color, int weight, int price, boolean availabilityLights) {
        this.brand = brand;
        this.color = color;
        this.weight = weight;
        this.price = price;
        this.availabilityLights = availabilityLights;
    }

    public abstract String getStringForFile();

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


