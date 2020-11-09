package edu.c3341;


/*Program class*/
public class Prog {
	private DS ds;
	private SS ss;
	public Prog() {
		ds = null;
		ss = null;
	}
	void ParseProg() {
		Tokenizer tokenizer = Tokenizer1.Instance();
        
		/*Check precondition*/
        if(tokenizer.getToken() != TokenKind.PROGRAM) {
        	 System.err.println("Error when parsing prog: The first token is not 'program'");
        	 System.exit(1);
        }
        tokenizer.skipToken(); /*Skip the keyword "program"*/
        ds = new DS();
        ds.ParseDS(); /*Parse declaration sequence*/
        
        if (tokenizer.getToken() != TokenKind.BEGIN) { /*Check if the token is "begin"*/
        	System.err.println("Error when parsing prog: The first token after parsing DS is not 'begin' ");
       	 System.exit(1);
        }   
        tokenizer.skipToken();/*Skip the keyword "begin"*/
        ss = new SS();
        ss.ParseSS();/*Parse statements sequence*/
       
        if (tokenizer.getToken() != TokenKind.END) { /*Check if the token is "end"*/
        	System.err.println("Error when parsing prog: The first token after parsing SS is not 'end' ");
       	 	System.exit(1);
        }
        tokenizer.skipToken();/*Skip the keyword "end"*/
	}
	
	/*Method to print program*/
	void PrintProg() {
		System.out.print("program ");
		ds.PrintDS();/*Call PrintDs to print declaration sequence*/
		System.out.println("");
		System.out.print("begin ");
		ss.PrintSS();/*Call PrintSs to print statements sequence*/
		System.out.println("");
		System.out.println("end");
	}
	
	/*Method to execute program*/
	void ExecProg() {
		ss.ExecSS();/*Call ExecSs to execute statements sequence*/
	}
}
