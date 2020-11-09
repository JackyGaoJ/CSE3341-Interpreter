package edu.c3341;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;


/*InputFile singleton class, to pass the input data to Id class*/
public class InputFile {
	private static Scanner instance = null;
	
	/* Return the singleton instance*/
	public static Scanner Instance() {
		return instance;
	}
	
	
	/*Get the input file path from arguments array, and make instance be the scanner*/
	void openFile(String fileName) {
		if (instance == null) {
			Scanner in;
			try {
				in = new Scanner(Paths.get(fileName));
			}catch(IOException e) {
				System.err.println("Error when open input file: " + fileName);
				return;
			}
			
			instance = in;
		}
	}

}
