package edu.c3341;

/*Statement sequence class*/
public class SS {
	private Stmt stmt;
	private SS ss;
	public SS() {
		stmt = null;
		ss = null;
	}
	
	/*Method to parse statement sequences*/
	void ParseSS(){
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		stmt = new Stmt();
		stmt.ParseStmt(); /*Call ParseStmt to parse a statement*/
		
		/*Check if there is another statement*/
		TokenKind token = tokenizer.getToken();
		if (token == TokenKind.IDENTIFIER ||token == TokenKind.IF ||token == TokenKind.WHILE ||token == TokenKind.READ ||token == TokenKind.WRITE) {
			ss = new SS();
			ss.ParseSS(); /*Recursively parse statement sequences*/
		}
	}
	
	/*Method to print statement sequence*/
	void PrintSS() {
		stmt.PrintStmt();/*Call PrintStmt to print a statement*/
		/*Check if there is another statement*/
		if (ss != null) {
			ss.PrintSS();/*Recursively print statement sequences*/
		}
	}
	
	/*Method to execute statement sequence*/
	void ExecSS() {
		stmt.ExecStmt();/*Call ExecStmt to execute a statement*/
		/*Check if there is another statement*/
		if (ss != null) {
			ss.ExecSS();/*Recursively execute statement sequences*/
		}
	}

}
