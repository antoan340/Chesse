import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text file
public class FileReader {
    Scanner myReader;
    public FileReader(String file){


        try {
            File myObj = new File(file);
                myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public int readData(String SearchLine){
        while (myReader.hasNextLine()){
            String line = myReader.nextLine();
            if(line.contains(SearchLine)){
                String[] parts = line.split("=");
                return Integer.parseInt(parts[1]);
            }
        }
        return -1;
    }
}
