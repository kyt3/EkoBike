package real;

import abstract_.ElectricBike;

public class Ebike extends ElectricBike {
    public Ebike(String brand, String color, int weight, int price, boolean availabilityLights, int maxSpeed, int capacity) {
        super(brand, color, weight, price, availabilityLights, maxSpeed, capacity);
    }

    @Override
    public String getStringForFile(){
        return String.format("E-BIKE %s; %d; %d; %s; %d; %s; %d;\n", brand, maxSpeed, weight, availabilityLights,
                capacity, color, price);
    }

    //E-BIKE Lankeleisi; 65; 24200; false; 10000; black; 2399
    /*An e-bike is characterized by:
- A brand 1
- The maximum speed (in km/h) 2
- The weight of the e-bike (in grams) 3
- The availability of lights at front and back (TRUE/FALSE) 4
- The battery capacity (in mAh) 5
- A color 6
- The price 7*/

/*E-BIKE Koga with 15488 mAh battery and head/tail light.
Price: 1899 euros.*/

    @Override
    public String toString() {
        return String.format("E-BIKE %s with %d mAh battery and %s \n Price: %d euros.", brand, capacity, super.availabilityLights ? "head/tail light" : "no head/tail light.", price);
        
    }
}

