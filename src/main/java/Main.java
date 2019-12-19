import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //Create file helper with default file
        FileHelper fileHelper = new FileHelper("./src/main/resources/files/defaultFile.txt");
        //Create logic object
        Logic logic = new Logic(fileHelper);
        try {
            //Start program
            logic.startProgram();
        } catch (IOException e) {
            //If there is some exception that I did not foresee - I will know about that in future)
            System.out.println("Unhandled exception " + e.getMessage() + ". Call to developers.");
            e.printStackTrace();
        }
    }
}
