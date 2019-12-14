package abstract_;

import java.util.Objects;

public abstract class ElectricBike extends Bike {
    protected int maxSpeed;
    protected int capacity;

    public ElectricBike(String brand, String color, int weight, int price, boolean availabilityLights, int maxSpeed, int capacity) {
        super(brand, color, weight, price, availabilityLights);
        this.maxSpeed = maxSpeed;
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectricBike that = (ElectricBike) o;
        return maxSpeed == that.maxSpeed &&
                capacity == that.capacity && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxSpeed, capacity) + super.hashCode();
    }

}


