package real;

import abstract_.Bike;

//Folding bike class
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

    @Override
    public String toString() {
        return String.format("FOLDING BIKE %s with %d gear(s) and %s \n Price: %d euros.", brand, gears, super.availabilityLights ? "head/tail light" : "no head/tail light.", price);
        
    }
}