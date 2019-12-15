import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileHelper fileHelper = new FileHelper("./src/main/resources/files/defaultFile.txt");
        Logic logic = new Logic(fileHelper);
        logic.startProgram();
    }
}
