package edu.c3341;


/* Declaration class*/
public class Decl {
	private IdList idList;
	public Decl() {
		idList = null;
	}
	
	/* Method for parsing declaration */
	void ParseDecl() {
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		
		/* Skip "int" token */
		tokenizer.skipToken();
		idList = new IdList();
		idList.ParseIdListForDs(); /* Call parsing method for idList*/
		
		/* Check if the first token after idList is ";" */
		if (tokenizer.getToken() !=TokenKind.SEMICOLON) {
			System.err.println("Error when parsing Decl: The first token after id list is not ';'");
			System.exit(1);
		}
		
		/* Skip the ";" token */
		tokenizer.skipToken();
	}
	
	/* Method for printing declaration */
	void PrintDecl() {
		System.out.println("");
		Tab t = Tab.Instance();
		String tab  = "";
		for(int i = 0; i < t.Number(); i++) {
			tab = tab + "\t";
		}
		System.out.print(tab + "int ");
		idList.PrintIdList();
		System.out.print(";");
	}
}
