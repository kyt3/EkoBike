import abstract_.Bike;
import real.Ebike;
import real.FoldingBike;
import real.Speedelec;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//Class for work with files
public class FileHelper {
    private String pathToFile;

    //Constructor gets path to file
    public FileHelper(String pathToFile){
        this.pathToFile = pathToFile;
    }

    //Method writing bikes to file
    public void writeBikesToFile(List<Bike> bikes) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(pathToFile));

        for (Bike bike : bikes) {
            fileWriter.write(bike.getStringForFile());
        }

        fileWriter.close();
    }

    //Method reading strings from file and parse it to return a bike object.
    public List<Bike> readBikesFromFile() throws IOException {
        List<Bike> bikes = new ArrayList<>();
        List<String> fileLines = null;
        //if default file was not found - create empty file
        try {
            fileLines = Files.readAllLines(Paths.get(pathToFile));
        } catch (NoSuchFileException e) {
            System.out.println("DefaultFile was not found it will be changed by empty file");
            new File(pathToFile).createNewFile();
            return readBikesFromFile();
        }

        for (String line : fileLines) {
            bikes.add(parseLine(line));
        }

        return bikes;
    }

    //Method for parsing string of file
    private Bike parseLine(String line) {
        //Split string by symbol ';' because of condition of task, because of that type of bike and its brand are contains in
        //first string of array characteristics
        String[] characteristics = line.split(";");

        //Trim each string, because we may have some spaces after ';' symbol
        for (int i = 0; i < characteristics.length; i++) {
            characteristics[i] = characteristics[i].trim();
        }

        //Split first string by space to get brand.
        String[] bikeAndBrand = characteristics[0].split(" ");

        //If type FOLDING BIKE than we need to start writing brand from third string else from second
        //I decided to divide functionality by two method for electric and folding bikes
        if (bikeAndBrand[0].equals("FOLDING")) {
            return getFoldingBike(bikeAndBrand,characteristics);
        } else {
            return getElectricBike(bikeAndBrand, characteristics);
        }

    }

    //Parse arrays and return folding bike object
    private Bike getFoldingBike(String[] bikeAndBrand, String[] characteristics){
        //Searching brand
    	String brand = "";

    	for (int i = 2; i < bikeAndBrand.length; i++) {
                brand += bikeAndBrand[i] + " ";
            }
        //After execution of loop we have one excess space, so it need to trim.
        brand = brand.trim();

    	//Simply writing variables as they given in condition.
        int index = 0;
        int sizeWheels = Integer.parseInt(characteristics[++index]);
        int gears = Integer.parseInt(characteristics[++index]);
        int weight = Integer.parseInt(characteristics[++index]);
        boolean availabilityLights = Boolean.parseBoolean(characteristics[++index]);
        String color = characteristics[++index];
        int price = Integer.parseInt(characteristics[++index]);

        return new FoldingBike(brand, color, weight, price, availabilityLights, sizeWheels, gears);
    }

    //Analogy of getFoldingBike method. But it return one of electric bike.
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

        //By condition there is two electric bikes, so we need to choose what to return.
        if (bikeAndBrand[0].equals("SPEEDELEC")) {
            return new Speedelec(brand, color, weight, price, availabilityLights, maxSpeed, capacity);
        } else {
            return new Ebike(brand, color, weight, price, availabilityLights, maxSpeed, capacity);
        }
    }
}
