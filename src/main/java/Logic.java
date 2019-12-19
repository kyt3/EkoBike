import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

//Logic class. Simply read users choice and call methods of ProgramActions class
public class Logic {
	private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	ProgramActions programActions;

	//It gets only file helper object and create program action instance.
	public Logic(FileHelper fileHelper) throws IOException {
		programActions = new ProgramActions(fileHelper);
	}

	//Method from that all beginning
	public void startProgram() throws IOException {
		//Prepare it is simply asked user to using default file or not.
		programActions.prepareToStart();

		//Infinite loop
		while (true) {
			try{
				//Show menu each time after choice
				programActions.showMenu();
				//Reading choice
				int choice = Integer.parseInt(bufferedReader.readLine());

				//Call correct method depends choice
				switch (choice) {
					case 1:
						programActions.showCatalog();
						break;
					case 2:
						programActions.addNewFoldingBike();
						break;
					case 3:
						programActions.addNewSpeedelec();
						break;
					case 4:
						programActions.addNewEBike();
						break;
					case 5:
						Map<String, String> params = programActions.askUserForSearch();
						programActions.findFirstBike(params);
						break;
					case 6:
						programActions.writeToFile();
						break;
					case 7:
						System.exit(0);
				}
			} catch (NumberFormatException e) {
				//Catching exception when it is not possible to parse string to integer.
				System.out.println("It seems that you try to write string extend number or simply press enter." +
						"Try to enter information again please.");
			}
		}

	}

}
