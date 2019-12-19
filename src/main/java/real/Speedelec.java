package real;

import abstract_.ElectricBike;

//Speedelec class
public class Speedelec extends ElectricBike {
    public Speedelec(String brand, String color, int weight, int price, boolean availabilityLights, int maxSpeed, int capacity) {
        super(brand, color, weight, price, availabilityLights, maxSpeed, capacity);
    }

    @Override
    public String getStringForFile(){
        return String.format("SPEEDELEC %s; %d; %d; %s; %d; %s; %d;\n", brand, maxSpeed, weight, availabilityLights,
                capacity, color, price);
    }

    @Override
    public String toString() {
    	return String.format("SPEEDELEC %s with %d mAh battery and %s \n Price: %d euros.", brand, capacity, super.availabilityLights ? "head/tail light" : "no head/tail light.", price);
        
    }
}
