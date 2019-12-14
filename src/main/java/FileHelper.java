import abstract_.Bike;
import real.Ebike;
import real.FoldingBike;
import real.Speedelec;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    private String pathToFile;

    public FileHelper(String pathToFile) throws FileNotFoundException {
        this.pathToFile = pathToFile;
    }

    public void writeBikesToFile(List<Bike> bikes) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(pathToFile));

        for (Bike bike : bikes) {
            fileWriter.write(bike.getStringForFile());
        }

        fileWriter.close();
    }

    public List<Bike> readBikesFromFile() throws IOException {
        List<Bike> bikes = new ArrayList<>();
        List<String> fileLines = Files.readAllLines(Paths.get(pathToFile));

        for (String line : fileLines) {
            bikes.add(parseLine(line));
        }

        return bikes;
    }

    private Bike parseLine(String line) {
        String brand = "";
        String color;
        int weight;
        int price;
        boolean availabilityLights;

        String[] characteristics = line.split(";");

        for (int i = 0; i < characteristics.length; i++) {
            characteristics[i] = characteristics[i].trim();
        }

        String[] bikeAndBrand = characteristics[0].split(" ");

        int index = 0;
        if (bikeAndBrand[0].equals("FOLDING")) {
            return getFoldingBike(bikeAndBrand,characteristics);
        } else {
            return getElectricBike(bikeAndBrand, characteristics);
        }

    }

    //todo подумать, может можно переделать эти методы, чтобы было красивее
    private Bike getFoldingBike(String[] bikeAndBrand, String[] characteristics){
    	String brand = "";
        String color;
        int weight;
        int price;
        boolean availabilityLights;


    	for (int i = 2; i < bikeAndBrand.length; i++) {
                brand += bikeAndBrand[i] + " ";
            }

        brand = brand.trim();
        int index = 0;
        int sizeWheels = Integer.parseInt(characteristics[++index]);
        int gears = Integer.parseInt(characteristics[++index]);
        weight = Integer.parseInt(characteristics[++index]);
        availabilityLights = Boolean.parseBoolean(characteristics[++index]);
        color = characteristics[++index];
        price = Integer.parseInt(characteristics[++index]);

        return new FoldingBike(brand, color, weight, price, availabilityLights, sizeWheels, gears);
    }

    private Bike getElectricBike(String[] bikeAndBrand, String[] characteristics){
    	String brand = "";

    	for (int i = 1; i < bikeAndBrand.length; i++) {
                brand += bikeAndBrand[i] + " ";
            }
        brand = brand.trim();

        int index = 0;
        int maxSpeed = Integer.parseInt(characteristics[++index]);
        int weight = Integer.parseInt(characteristics[++index]);
        boolean availabilityLights = Boolean.parseBoolean(characteristics[++index]);
        int capacity = Integer.parseInt(characteristics[++index]);
        String color = characteristics[++index];
        int price = Integer.parseInt(characteristics[++index]);

        if (bikeAndBrand[0].equals("SPEEDELEC")) {
            return new Speedelec(brand, color, weight, price, availabilityLights, maxSpeed, capacity);
        } else {
            return new Ebike(brand, color, weight, price, availabilityLights, maxSpeed, capacity);
        }
    }

    /*
    A folding bike is characterized by:
- A brand   0
- The size of the wheels (in inch) 1
- The number of gears 2
- The weight of the bike (in grams) 3
- The availability of lights at front and back (TRUE/FALSE) 4
- A color 5
- The price 6

An speedelec is characterized by:
- A brand 0
- The maximum speed (in km/h) 1
- The weight of the bike (in grams) 2
- The availability of lights at front and back (TRUE/FALSE) 3
- The battery capacity (in mAh) 4
- A color 5
- The price 6

An e-bike is characterized by:
- A brand 0
- The maximum speed (in km/h) 1
- The weight of the e-bike (in grams) 2
- The availability of lights at front and back (TRUE/FALSE) 3
- The battery capacity (in mAh) 4
- A color 5
- The price 6

    FOLDING BIKE Brompton; 20; 6; 9283; TRUE; black; 1199
SPEEDELEC Peugeot; 45; 5426; TRUE; 8000; blue; 875
E-BIKE Gazelle; 49; 16455; TRUE; 16000; red; 1499
FOLDING BIKE Dahon; 16; 1; 11109; FALSE; white; 899
E-BIKE Koga; 48; 15488; TRUE; 21000; red; 1899*/
}
