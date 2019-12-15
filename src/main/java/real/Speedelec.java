package real;

import abstract_.ElectricBike;

public class Speedelec extends ElectricBike {
    public Speedelec(String brand, String color, int weight, int price, boolean availabilityLights, int maxSpeed, int capacity) {
        super(brand, color, weight, price, availabilityLights, maxSpeed, capacity);
    }

    @Override
    public String getStringForFile(){
        return String.format("SPEEDELEC %s; %d; %d; %s; %d; %s; %d;\n", brand, maxSpeed, weight, availabilityLights,
                capacity, color, price);
    }

    //SPEEDELEC Booster; 35; 10900; false; 13200; green; 1279

    // SPEEDELEC Peugeot with 5426 mAh battery and head/tail light.
// Price: 875 euros.

    @Override
    public String toString() {
    	return String.format("SPEEDELEC %s with %d mAh battery and %s \n Price: %d euros.", brand, capacity, super.availabilityLights ? "head/tail light" : "no head/tail light.", price);
        
    }
}
