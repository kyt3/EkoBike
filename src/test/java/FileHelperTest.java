import abstract_.Bike;
import org.junit.Assert;
import org.junit.Test;
import real.FoldingBike;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

//Some positive tests for FileHelper class
public class FileHelperTest {
    private String pathToTestFile = "./src/main/resources/tests/FileHelperTestFile";
    private FileHelper fileHelper = new FileHelper(pathToTestFile);
    private BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(pathToTestFile)));

    public FileHelperTest() throws FileNotFoundException {
    }

    @Test
    public void readBikesFromFileTest() throws IOException {
        List<String> fileStrings = Files.readAllLines(Paths.get(pathToTestFile));
        List<Bike> bikes = fileHelper.readBikesFromFile();

        for (int i = 0; i < fileStrings.size(); i++) {
            Assert.assertEquals(fileStrings.get(i), bikes.get(i).getStringForFile());
        }
    }

    @Test
    public void writeBikesToFileTest() throws IOException {
        List<Bike> bikes = fileHelper.readBikesFromFile();
        Bike bikeForAdd = new FoldingBike("testBrand", "testcolor", 1,10,true,20, 18);
        bikes.add(bikeForAdd);
        fileHelper.writeBikesToFile(bikes);

        List<Bike> bikesAfterWritingToFile = fileHelper.readBikesFromFile();
        bikes.remove(bikeForAdd);
        fileHelper.writeBikesToFile(bikes);

        Assert.assertEquals(bikeForAdd, bikesAfterWritingToFile.get(bikesAfterWritingToFile.size() - 1));
    }


}
