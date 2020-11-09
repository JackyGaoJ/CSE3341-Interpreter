package edu.c3341;


/* If class*/
public class If{
	private Cond c;
	private SS ss1;
	private SS ss2;
	public If () {
		c = null;
		ss1 = null;
		ss2 = null;
	}
	
	/* Method for parsing If*/
	void ParseIf() {
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		tokenizer.skipToken();/* Skip the token "if"*/
		c = new Cond();
		c.ParseCond(); /* Call parse method for the first condition*/
		if(tokenizer.getToken() != TokenKind.THEN) { /* Check if the first token after condition is "then" */
        	 System.err.println("Error when parsing if: The first token after parsing condition is not 'then'");
        	 System.exit(1);
        }
		tokenizer.skipToken(); /* Skip the token "then" */
		ss1 = new SS();
		ss1.ParseSS();  /* Call parse method for statement sequence*/
		
        if(tokenizer.getToken() == TokenKind.END) {/* Check if the following token is "end" */
        	tokenizer.skipToken(); /* Skip the token "end" */
        	tokenizer.skipToken(); /* Skip the token ":"*/
        	
        }
        else {
        	tokenizer.skipToken();/* Skip the token "else"s */
        	ss2 = new SS();
        	ss2.ParseSS(); /* Call parse method for the second statement sequence*/
        	if(tokenizer.getToken() != TokenKind.END) { /* Check if the following token is "end" */
        		System.err.println("Error when parsing if: The first token after parsing all statements in parsing if is not 'end'");
        		System.exit(1);
            }
        	tokenizer.skipToken(); /* Skip the token "end" */
        	if(tokenizer.getToken() != TokenKind.SEMICOLON) {/* Check if the following token is ";" */
        		System.err.println("Error when parsing if: The first token after parsing all statements and end in parsing if is not ';'");
        		System.exit(1);
            }
        	tokenizer.skipToken(); /* Skip the token ";" */
        }
	}
	
	/* Method for printing If statement*/
	void PrintIf() {
		System.out.println("");
		Tab t = Tab.Instance();
		String tab  = "";
		for(int i = 0; i < t.Number(); i++) {
			tab = tab + "\t";
		}
		System.out.print(tab + "if ");
		c.PrintCond(); /* Print condition by calling print method for condition*/
		System.out.print("then");
		t.IncreaseTab();
		ss1.PrintSS(); /* Print statement by calling print method for statement*/
		if (ss2 !=null) {/* Check if there is an else statement*/
			t.DecreaseTab();
			System.out.println("");
			System.out.print(tab + "else");
			t.IncreaseTab();
			ss2.PrintSS();/* Print the second statement by calling print method for statement*/
			t.DecreaseTab();
		}
		System.out.println("");
		System.out.print(tab + "end");
		t.DecreaseTab();
		
		System.out.print(";");
		
	}
	
	/* Method for executing If statement*/
	void ExecIf() {
		if (ss2 == null) {/* Check if there is an else statement*/
			if (c.EvalCond()) { /* Evaluate condition by calling EvalCond*/
				ss1.ExecSS(); /*Execute statement by calling ExecSS*/
			}
		}
		else {
			if (c.EvalCond()) {
				ss1.ExecSS();
			}
			else {
				ss2.ExecSS(); /*Execute the second statement by calling ExecSS*/
			}
		}
	}
}
