package edu.c3341;

/* Class for input*/
public class In {
	private IdList idList;
	public In() {
		idList = null;
	}
	
	/* Method for parsing input*/
	void ParseIn() {
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		tokenizer.skipToken(); /* Skip the token "read"*/
		idList = new IdList();
		idList.ParseIdListForSs(); /* Call parse IdList for Statement sequence*/
		if (tokenizer.getToken() != TokenKind.SEMICOLON) { /* Check if the token following idList is ":"*/
			System.err.println("Error when parsing In: The first token after id list is not ';'");
			System.exit(1);
		}
		tokenizer.skipToken(); /* Skip the token ";"*/
	}
	
	/*Method for printing input*/
	void PrintIn() {
		System.out.println("");
		Tab t = Tab.Instance();
		String tab  = "";
		for(int i = 0; i < t.Number(); i++) {
			tab = tab + "\t";
		}
		System.out.print(tab + "Read ");
		idList.PrintIdList(); /*Print IdList by calling PrintIdList*/
		System.out.print(";");
	}
	
	/* Execute input method*/
	void ExecIn() {
		idList.ExecIdListRead(); /* Load input data file by calling ExecIdListRead*/
		
	}
	

}
