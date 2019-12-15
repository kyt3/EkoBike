import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class LogicTest {
    FileHelper fileHelper = new FileHelper("./src/main/resources/tests/LogicTestFile");

    public LogicTest() throws IOException {
    }

    //todo remove
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new ByteArrayInputStream("1".getBytes());
        System.setIn(inputStream);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(bufferedReader.readLine());
    }

    @Test
    public void showMenuTest() throws IOException, InterruptedException {
        //todo возможно можно сделать проще и по другому
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(inputStream);

        Logic logic = new Logic(fileHelper);

        String expectedString = "Please make your choice: \n" +
                "1 - start program with default catalog file \n" +
                "2 - start program with own file \n" +
                "\n" +
                "Please make your choice: \n" +
                "1 - Show the entire EcoBike catalog \n" +
                "2 – Add a new folding bike \n" +
                "3 – Add a new speedelec \n" +
                "4 – Add a new e-bike \n" +
                "5 – Find the first item of a particular brand \n" +
                "6 – Write to file \n" +
                "7 – Stop the program \n";

        try {
            logic.startProgram();
        } catch (NumberFormatException e) {
            Assert.assertEquals(expectedString, out.toString());
        }
    }

    @Test
    public void showCatalogTest(){
        //todo implement
    }

    @Test
    public void addNewFoldingBikeTest(){
        //todo implement
    }

    @Test
    public void addNewSpeedelecTest(){
        //todo implement
    }

    @Test
    public void addNewEBikeTest(){
        //todo implement
    }

    @Test
    public void findFirstBrandTest(){
        //todo implement
    }

    @Test
    public void writeToFile(){
        //todo implement
    }
}
