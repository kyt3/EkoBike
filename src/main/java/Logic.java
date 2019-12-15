import abstract_.Bike;
import real.Ebike;
import real.FoldingBike;
import real.Speedelec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logic {
	private List<Bike> bikes;
	private FileHelper fileHelper;
	private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public Logic(FileHelper fileHelper) throws IOException {
		this.fileHelper = fileHelper;
		this.bikes = fileHelper.readBikesFromFile();
	}

	private void prepareToStart() throws IOException {
		System.out.println("Please make your choice: \n" +
				"1 - start program with default catalog file \n" +
				"2 - start program with own file \n");
		int choice = Integer.parseInt(bufferedReader.readLine());

		switch (choice){
			case 1:
				return;
			case 2:
				changeDefaultFile();
				break;
			default:
				prepareToStart();
		}
	}

	private void changeDefaultFile() throws IOException {
		System.out.println("Enter path to file");
		String pathToFile = bufferedReader.readLine();
		fileHelper = new FileHelper(pathToFile);
	}

	public void startProgram() throws IOException {
		prepareToStart();

		while(true){
			showMenu();
			int choice = Integer.parseInt(bufferedReader.readLine());

			switch(choice){
				case 1:
					showCatalog();
					break;
				case 2:
					addNewFoldingBike();
					break;
				case 3:
					addNewSpeedelec();
					break;
				case 4:
					addNewEBike();
					break;
				case 5:
					findFirstBrand();
					break;
				case 6:
					writeToFile();
					break;
				case 7:
					System.exit(0);
			}
		}
	}

    private void showMenu() {
        System.out.print("Please make your choice: \n" +
                "1 - Show the entire EcoBike catalog \n" +
                "2 – Add a new folding bike \n" +
                "3 – Add a new speedelec \n" +
                "4 – Add a new e-bike \n" +
                "5 – Find the first item of a particular brand \n" +
                "6 – Write to file \n" +
                "7 – Stop the program \n");
    }

    private void showCatalog(){
    	for (Bike bike : bikes ) {
    		System.out.println(bike);
    	}
    }

    // protected String brand;
    // protected String color;
    // protected int weight;
    // protected int price;
    // protected boolean availabilityLights;

    // private int sizeWheels;
    // private int gears;

   	private Map<String,String> askDefaultBikeParams() throws IOException {
   		Map<String,String> params = new HashMap();
   		System.out.println("Enter brand");
    	params.put("brand", bufferedReader.readLine());
    	System.out.println("Enter available colors throw spaces, for example: black red");
    	params.put("color", bufferedReader.readLine());
    	System.out.println("Enter weight");
    	params.put("weight", bufferedReader.readLine());
    	System.out.println("Enter price");
    	params.put("price", bufferedReader.readLine());
    	System.out.println("Is bike have lights? yes/no");
		params.put("availabilityLights", bufferedReader.readLine());

    	return params;
   	}

   	private Map<String, Integer> askDefaultElectricBikeParams() throws IOException {
   		Map<String,Integer> params = new HashMap();
   		System.out.println("Enter maximum speed");
		params.put("maxSpeed", Integer.parseInt(bufferedReader.readLine()));
    	System.out.println("Enter capacity");
		params.put("capacity", Integer.parseInt(bufferedReader.readLine()));

    	return params;
   	}

    private void addNewFoldingBike() throws IOException {
    	Map<String, String> defaultParams = askDefaultBikeParams();
// String brand, String color, int weight, int price, boolean availabilityLights, int sizeWheels, int gears)
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

    //protected int maxSpeed;
    // protected int capacity;

    private void addNewSpeedelec() throws IOException {
    	Map<String,String> defaultParams = askDefaultBikeParams();

    	Map<String,Integer> defaultElectricParams = askDefaultElectricBikeParams();

// String brand, String color, int weight, int price, boolean availabilityLights, int maxSpeed, int capacity) {
		bikes.add(new Speedelec(
				defaultParams.get("brand"),
				defaultParams.get("color"),
				Integer.parseInt(defaultParams.get("weight")),
				Integer.parseInt(defaultParams.get("price")),
				defaultParams.get("availabilityLights").charAt(0) == 'y' ? true : false,
				defaultElectricParams.get("maxSpeed"),
				defaultElectricParams.get("capacity")));
    }

    private void addNewEBike() throws IOException {
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

    public void findFirstBrand(){
    	//todo realization
    }

    public void writeToFile() throws IOException {
    	fileHelper.writeBikesToFile(bikes);
    }

}
