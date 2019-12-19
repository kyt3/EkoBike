import abstract_.Bike;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Some positive tests for ProgramActions class
public class ProgramActionsTest {
    FileHelper fileHelper = new FileHelper("./src/main/resources/tests/TestFile");
    FileHelper fileHelperEmptyFile = new FileHelper("./src/main/resources/tests/EmptyFile");
    ProgramActions programActions = new ProgramActions(fileHelper);
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    public ProgramActionsTest() throws IOException {
    }

    @Before
    public void prepeareToTest(){
        System.setOut(new PrintStream(out));
    }

    @Test
    public void showMenuTest(){
        programActions.showMenu();

        String expectedString = "Please make your choice: \n" +
                "1 - Show the entire EcoBike catalog \n" +
                "2 – Add a new folding bike \n" +
                "3 – Add a new speedelec \n" +
                "4 – Add a new e-bike \n" +
                "5 – Find first bike by parameters \n" +
                "6 – Write to file \n" +
                "7 – Stop the program \n";

        Assert.assertEquals(expectedString, out.toString());
    }

    @Test
    public void showCatalogTest() throws IOException {
        programActions.showCatalog();

        List<Bike> bikes = fileHelper.readBikesFromFile();

        String expectedString = "";

        for (Bike bike : bikes) {
            expectedString += bike.toString() + "\n";
        }

        Assert.assertEquals(expectedString, out.toString());
    }

    @Test
    public void addNewFoldingBikeTest() throws IOException {
        String brand = "test brand";
        String color = "test color";
        int weight = 12;
        int price = 100;
        boolean availabilityLights = true;
        int sizeOfWheels = 8;
        int gears = 21;

        InputStream inputStream = new ByteArrayInputStream(String.format("%s\n%s\n%d\n%d\n%s\n%d\n%d\n"
                , brand, color, weight, price, availabilityLights ? "yes" : "no", sizeOfWheels, gears).getBytes());
        System.setIn(inputStream);
        programActions = new ProgramActions(fileHelperEmptyFile);

        programActions.addNewFoldingBike();
        out.reset();

        String expectedString = String.format("FOLDING BIKE %s with %d gear(s) and %s \n Price: %d euros.\n",
                brand, gears, availabilityLights ? "head/tail light" : "no head/tail light.", price);

        programActions.showCatalog();

        Assert.assertEquals(expectedString, out.toString());
    }

    @Test
    public void addNewSpeedelecTest() throws IOException {
        String brand = "test brand";
        String color = "test color";
        int weight = 1500;
        int price = 600;
        boolean availabilityLights = false;
        int maxSpeed = 60;
        int capacity = 2400;

        InputStream inputStream = new ByteArrayInputStream(String.format("%s\n%s\n%d\n%d\n%s\n%d\n%d\n"
                , brand, color, weight, price, availabilityLights ? "yes" : "no", maxSpeed, capacity).getBytes());
        System.setIn(inputStream);
        programActions = new ProgramActions(fileHelperEmptyFile);

        programActions.addNewSpeedelec();
        out.reset();

        String expectedString = String.format("SPEEDELEC %s with %d mAh battery and %s \n Price: %d euros.\n",
                brand, capacity, availabilityLights ? "head/tail light" : "no head/tail light.", price);

        programActions.showCatalog();

        Assert.assertEquals(expectedString, out.toString());
    }

    @Test
    public void addNewEBikeTest() throws IOException {
        String brand = "test brand";
        String color = "test color";
        int weight = 1600;
        int price = 800;
        boolean availabilityLights = true;
        int maxSpeed = 60;
        int capacity = 2400;

        InputStream inputStream = new ByteArrayInputStream(String.format("%s\n%s\n%d\n%d\n%s\n%d\n%d\n"
                , brand, color, weight, price, availabilityLights ? "yes" : "no", maxSpeed, capacity).getBytes());
        System.setIn(inputStream);
        programActions = new ProgramActions(fileHelperEmptyFile);

        programActions.addNewEBike();
        out.reset();

        String expectedString = String.format("E-BIKE %s with %d mAh battery and %s \n Price: %d euros.\n",
                brand, capacity, availabilityLights ? "head/tail light" : "no head/tail light.", price);

        programActions.showCatalog();

        Assert.assertEquals(expectedString, out.toString());
    }

    @Test
    public void findFirstBikeTest() throws IOException {
        List<Bike> bikes = fileHelper.readBikesFromFile();
        Bike expectedBike = null;

        Map<String, String> params = new HashMap<>();
        params.put("brand", "Dualtron");

        for (Bike bike : bikes) {
            if ("Dualtron".equals(bike.getBrand())) {
                expectedBike = bike;
            }
        }

        out.reset();
        programActions.findFirstBike(params);

        String expectedString = "Search by parametrs: brand Dualtron \n" +
                "First bike was found: \n" + expectedBike + "\n";

        Assert.assertEquals(expectedString, out.toString());
    }
}
