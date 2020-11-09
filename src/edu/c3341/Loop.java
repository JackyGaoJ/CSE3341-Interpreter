package edu.c3341;

/* Loop class */
public class Loop {
	private Cond c;
	private SS ss;
	public Loop() {
		c = null;
		ss = null;
	}
	/* Parse loop for core program*/
	void ParseLoop(){
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		tokenizer.skipToken();
		c = new Cond();
		c.ParseCond(); /*Call parse condition to parse the condition*/
		
		/*Check the precondition*/
		if (tokenizer.getToken() != TokenKind.LOOP) {
			System.err.println("Error when parsing loop: The first token after condition is not 'loop'");
    		System.exit(1);
		}
		tokenizer.skipToken(); /*Skip loop keyword*/
		ss = new SS();
		ss.ParseSS(); /* Call parse statement sequence to parse statements*/
		
		/*Check if the last token in loop is "end"*/
		if (tokenizer.getToken() != TokenKind.END) {
			System.err.println("Error when parsing lop: The first token after statements is not 'end'");
    		System.exit(1);
		}
		tokenizer.skipToken(); /*Skip the keyword "end" */
		if (tokenizer.getToken() != TokenKind.SEMICOLON) {/*Skip the keyword ";" */
			System.err.println("Error when parsing lop: The first token after statements is not ';'");
    		System.exit(1);
		}
		tokenizer.skipToken(); /*Skip the keyword ";" */
	}
	
	/* Print loop for core program*/
	void PrintLoop(){
		System.out.println("");
		Tab t = Tab.Instance();
		String tab  = "";
		for(int i = 0; i < t.Number(); i++) {
			tab = tab + "\t";
		}
		System.out.print(tab + "while ");
		c.PrintCond(); /* Call PrintCond to print condition */
		System.out.print("loop");
		System.out.println("");
		t.IncreaseTab();
		ss.PrintSS();/* Call PrintSS to print statement sequence*/
		t.DecreaseTab();
		System.out.println("");
		System.out.print(tab + "end;");
		
	}
	
	/*Execute the loop*/
	void ExecLoop(){
		while(c.EvalCond()) { 
			ss.ExecSS();/* Call ExecSS to execute statement sequence*/
		}
	}
	

}
