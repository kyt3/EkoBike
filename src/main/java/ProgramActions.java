import abstract_.Bike;
import abstract_.ElectricBike;
import real.Ebike;
import real.FoldingBike;
import real.Speedelec;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramActions {
    private List<Bike> bikes;
    private FileHelper fileHelper;
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    //Constructor gets file helper and initialize bikes list
    public ProgramActions(FileHelper fileHelper) throws IOException {
        this.fileHelper = fileHelper;
        this.bikes = fileHelper.readBikesFromFile();
    }

    //Method asks user to use default file or users file
    public void prepareToStart() throws IOException {
        System.out.println("Please make your choice: \n" +
                "1 - start program with default catalog file \n" +
                "2 - start program with own file \n");
        int choice = 0;

        try {
            choice = Integer.parseInt(bufferedReader.readLine());

        }catch (NumberFormatException e) {
            //I didn't catch it in logic class, because after exit from method it will used default file
            System.out.println("It seems that you try to write string extend number or simply press enter." +
                "Try to enter information again please.");
        }

        switch (choice){
            case 1:
                //To use default file it simply returned
                return;
            case 2:
                //Method changes file
                changeDefaultFile();
                try {
                    bikes = fileHelper.readBikesFromFile();
                } catch (IOException e) {
                    //If file is not readable I asked user to choose another one
                    System.out.println("Program have some problems with reading of file. Choose another one");
                    prepareToStart();
                }

                break;
            default:
                prepareToStart();
        }
    }


    //Method asks user for file to choose and change it
    private void changeDefaultFile() throws IOException {
        try {
            System.out.println("Enter path to file");
            String pathToFile = bufferedReader.readLine();
            fileHelper = new FileHelper(pathToFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            prepareToStart();
        } catch (IOException e) {
            //If there is some exception that I did not foresee - I will know about that in future)
            System.out.println("Unhandled exception " + e.getMessage() + ". Call to developers.");
            e.printStackTrace();
        }

    }


    //Method simply show menu by condition
    public void showMenu() {
        System.out.print("Please make your choice: \n" +
                "1 - Show the entire EcoBike catalog \n" +
                "2 – Add a new folding bike \n" +
                "3 – Add a new speedelec \n" +
                "4 – Add a new e-bike \n" +
                "5 – Find first bike by parameters \n" +
                "6 – Write to file \n" +
                "7 – Stop the program \n");
    }

    //Method shows catalog of bikes
    public void showCatalog(){
        for (Bike bike : bikes ) {
            System.out.println(bike);
        }
    }

    //Method for adding bike. Write parameters of bike to map and returns it
    private Map<String,String> askDefaultBikeParams() throws IOException {
        Map<String,String> params = new HashMap();
        System.out.println("Enter brand");
        params.put("brand", bufferedReader.readLine());
        System.out.println("Enter color");
        params.put("color", bufferedReader.readLine());
        System.out.println("Enter weight");
        params.put("weight", bufferedReader.readLine());
        System.out.println("Enter price");
        params.put("price", bufferedReader.readLine());
        System.out.println("Is bike have lights? yes/no(or something else except y in front)");
        String availabilityLights = bufferedReader.readLine();
        params.put("availabilityLights", availabilityLights);

        //Check that all integer parameters are correctly.
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue().isEmpty()) {
                throw new NumberFormatException();
            }
        }

        return params;
    }

    //Analogy of askDefaultBikeParams method, but for electric bikes
    private Map<String, Integer> askDefaultElectricBikeParams() throws IOException {
        Map<String,Integer> params = new HashMap();
        System.out.println("Enter maximum speed");
        params.put("maxSpeed", Integer.parseInt(bufferedReader.readLine()));
        System.out.println("Enter capacity");
        params.put("capacity", Integer.parseInt(bufferedReader.readLine()));

        //We don't need to check integer parameters because map has types <String, Integer> so exception will throw.

        return params;
    }

    //Method adds new folding bike. as for me there is nothing for explain
    public void addNewFoldingBike() throws IOException {
        Map<String, String> defaultParams = askDefaultBikeParams();
        System.out.println("Enter size of wheels");
        int sizeWheels = Integer.parseInt(bufferedReader.readLine());
        System.out.println("Enter count of gears");
        int gears = Integer.parseInt(bufferedReader.readLine());

        bikes.add(new FoldingBike(
                defaultParams.get("brand"),
                defaultParams.get("color"),
                Integer.parseInt(defaultParams.get("weight")),
                Integer.parseInt(defaultParams.get("price")),
                defaultParams.get("availabilityLights").charAt(0) == 'y' ? true : false,
                sizeWheels,
                gears));

    }

    //Method adds new speedelec bike. as for me there is nothing for explain
    public void addNewSpeedelec() throws IOException {
        Map<String,String> defaultParams = askDefaultBikeParams();

        Map<String,Integer> defaultElectricParams = askDefaultElectricBikeParams();

        bikes.add(new Speedelec(
                defaultParams.get("brand"),
                defaultParams.get("color"),
                Integer.parseInt(defaultParams.get("weight")),
                Integer.parseInt(defaultParams.get("price")),
                defaultParams.get("availabilityLights").charAt(0) == 'y' ? true : false,
                defaultElectricParams.get("maxSpeed"),
                defaultElectricParams.get("capacity")));
    }

    //Method adds new e-bike. as for me there is nothing for explain
    public void addNewEBike() throws IOException {
        Map<String,String> defaultParams = askDefaultBikeParams();

        Map<String,Integer> defaultElectricParams = askDefaultElectricBikeParams();

        bikes.add(new Ebike(
                defaultParams.get("brand"),
                defaultParams.get("color"),
                Integer.parseInt(defaultParams.get("weight")),
                Integer.parseInt(defaultParams.get("price")),
                defaultParams.get("availabilityLights").charAt(0) == 'y' ? true : false,
                defaultElectricParams.get("maxSpeed"),
                defaultElectricParams.get("capacity")));
    }

    //Method asks user parameters for searching and returns it in map.
    //I choose some parameters myself.
    public Map<String,String> askUserForSearch() throws IOException {
        Map<String, String> params = new HashMap<>();

        while (true) {
            if (!params.isEmpty()) {
                System.out.println("Your parametrs for search:");
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }

            System.out.println("Please make your choise: \n" +
                    "1 - Search by given options\n" +
                    "2 - Enter/change brand\n" +
                    "3 - Enter/change weight\n" +
                    "4 - Enter/change color\n" +
                    "5 - Enter/change capacity\n");

            int choise = Integer.parseInt(bufferedReader.readLine());

            switch (choise) {
                case 1:
                    return params;
                case 2:
                    System.out.println("Enter brand");
                    params.put("brand", bufferedReader.readLine());
                    break;
                case 3:
                    System.out.println("Enter weight");
                    params.put("weight", bufferedReader.readLine());
                    break;
                case 4:
                    System.out.println("Enter color");
                    params.put("color", bufferedReader.readLine());
                    break;
                case 5:
                    System.out.println("Enter capacity");
                    params.put("capacity", bufferedReader.readLine());
                    break;
            }
        }
    }

    //Method search for fist bike in list by parameters that returns askUserForSearch method.
    public void findFirstBike(Map<String, String> params){
        //Initialize parameters for search
        String brand = params.containsKey("brand") ? params.get("brand") : null;
        int weight = params.containsKey("weight") ? Integer.parseInt(params.get("weight")) : -1;
        String color = params.containsKey("color") ? params.get("color") : null;
        int capacity = params.containsKey("capacity") ? Integer.parseInt(params.get("capacity")) : -1;

        //String that shows what parameters is using for search
        String enteredParams = "";

        if (brand != null) {
            enteredParams += "brand " + brand + " ";
        }

        if (weight > 0){
            enteredParams += "weight " + weight + " ";
        }

        if (color != null) {
            enteredParams += "color " + color + " ";
        }

        if (capacity > 0) {
            enteredParams += "capacity " + capacity + " ";
        }

        System.out.println("Search by parametrs: " + enteredParams);

        //Loop for searching bike
        for (Bike bike : bikes) {
            boolean capacityEq;
            //Need to know is bike electric to cast it.
            if (capacity > 0 && !(bike instanceof ElectricBike)) {
                continue;
            } else if (capacity < 0 && bike instanceof ElectricBike) {
                ElectricBike electricBike = (ElectricBike) bike;

                capacityEq = capacity > 0 ? capacity == electricBike.getCapacity() : true;
            } else {
                capacityEq = true;
            }

            //Initialize flags for compare parameters of bike with given parameters
            boolean brandEq = brand == null ? true : brand.equals(bike.getBrand());
            boolean weightEq = weight > 0 ? weight == bike.getWeight() : true;
            boolean colorEq = color == null ? true : color.equals(bike.getColor());

            //If all flags are true - bike is found
            if (brandEq && weightEq && colorEq && capacityEq) {
                System.out.println("First bike was found: \n" + bike);
                return;
            }
        }

        System.out.println("Bike was not found");
    }

    //Write bikes into file
    public void writeToFile() throws IOException {
        fileHelper.writeBikesToFile(bikes);
    }
}
