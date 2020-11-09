package edu.c3341;

/* Declaration Sequence class*/
public class DS {
	private Decl decl;
	private DS ds;
	public DS() {
		decl = null;
		ds = null;
	}
	
	/* Method for parsing declaration sequence*/
	void ParseDS(){
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		decl = new Decl();
		decl.ParseDecl(); /* Call parse method for declaration*/
		
		/* Check if there are more declaration sequences */
		if (tokenizer.getToken() != TokenKind.BEGIN) {
			ds = new DS();
			ds.ParseDS();
		}
	}
	
	/* Method for printing declaration sequence*/
	void PrintDS() {
		decl.PrintDecl(); /* Call printing method for declaration*/ 
		/* If there is more declaration, recursively print them*/
		if (ds != null) {
			ds.PrintDS();
		}
	}

}
