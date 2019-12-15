package real;

import abstract_.Bike;

public class FoldingBike extends Bike {
    private int sizeWheels;
    private int gears;

    public FoldingBike(String brand, String color, int weight, int price, boolean availabilityLights, int sizeWheels, int gears) {
        super(brand, color, weight, price, availabilityLights);
        this.sizeWheels = sizeWheels;
        this.gears = gears;
    }

    @Override
    public String getStringForFile(){
        return String.format("FOLDING BIKE %s; %d; %d; %d; %s; %s; %d;\n", brand, sizeWheels, gears, weight,
                availabilityLights, color, price);
    }

    /*
    A folding bike is characterized by:
- A brand   2
- The size of the wheels (in inch) 3
- The number of gears 4
- The weight of the bike (in grams) 5
- The availability of lights at front and back (TRUE/FALSE) 6
- A color 7
- The price 8*/

    //FOLDING BIKE Benetti; 24; 27; 11400; false; rose; 1009

    //FOLDING BIKE Dahon with 1 gear(s) and no head/tail light.
// Price: 899 euros.

    @Override
    public String toString() {
        return String.format("FOLDING BIKE %s with %d gear(s) and %s \n Price: %d euros.", brand, gears, super.availabilityLights ? "head/tail light" : "no head/tail light.", price);
        
    }
}