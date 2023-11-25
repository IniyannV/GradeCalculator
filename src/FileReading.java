import java.io.*;
import java.util.ArrayList;

public class FileReading {
	ArrayList<String> fileAsList = new ArrayList<String>();
	public FileReading(String FileDirectory) {
		// Arraylist to store the lines read from the text file
		//ArrayList<String> str = new ArrayList<String>();
		
		// BufferedReader Object to read the text in 'C.text'
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(new File(FileDirectory)));
		} catch (FileNotFoundException e1) {
			// Prints the exception error
			e1.printStackTrace();
		} 
		// String to store the line from the text file 
		String s = null;
		try {
			// Read the first line from the text file
			s = bf.readLine();
		}
		catch(IOException e) {
			// Prints the exception error
			e.printStackTrace();
		}
		// While there is still a line left to read in the text file, store the lines in an arraylist
		while(s != null) {
			fileAsList.add(s);
			try {
				// Reads the line from the text file
				s = bf.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
