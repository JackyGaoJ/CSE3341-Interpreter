package edu.c3341;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;


/*Interpreter class, contains main function to run the program*/
public class Interpreter {
	private Interpreter() {
		
	}
	
	public static void main(String[] args) {
		Scanner in = null;
		
		try {
			in = new Scanner(Paths.get(args[0]));
		}catch (IOException e) {
			System.err.println("Error opening file: " + args[0]);
		}
		String print = "";
		
		/*Check whether argument array has 3 arguments or not*/
		if (args.length == 3) {
			String fileName = args[1];
			InputFile file = new InputFile();
			file.openFile(fileName);
			print = args[2];
			if (!print.equals("print")&& !print.equals("doNotPrint")) { /*Check whether the argument is legal or not*/
				System.err.println("The third argument should be print or doNotPrint only");
				System.exit(1);
			}
		}
		else {
			print = args[1];
			if (!print.equals("print")&& !print.equals("doNotPrint")) { /*Check whether the argument is legal or not*/
				System.err.println("The second argument should be print or doNotPrint only");
				System.exit(1);
			}
		}
		Tokenizer t = Tokenizer1.create(in);
        Prog prog = new Prog(); /* Call the parser*/
        prog.ParseProg(); /* Call the parser*/
        if (print.equals("print")) {
        	prog.PrintProg(); /* Call the Printer*/
        }
        	        
        prog.ExecProg(); /* Call the Executor*/
		in.close();
		
	}

}
