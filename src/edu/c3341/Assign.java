package edu.c3341;


/*Assign class*/
public class Assign {
	private Id id;
	private Exp exp;
	public Assign () {
		id = null;
		exp = null;
	}
	
	/* Method for parsing assign*/
	void ParseAssign(){
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		id = Id.parseForStmtSeq();
		
		/*If the token after the first identifier is not "=", then print error message and exit*/
		if (tokenizer.getToken() != TokenKind.ASSIGN_OPERATOR) {
			System.err.println("Error when parsing assign: The first token after identifier is not '='");
       	 	System.exit(1);
		}
		
		/*Skip "=" token*/
		tokenizer.skipToken();
		exp = new Exp();
		exp.ParseExp();
		/*If the token after the second identifier is not ":", then print error message and exit*/
		if (tokenizer.getToken() != TokenKind.SEMICOLON) {
			System.err.println("Error when parsing assign: The first token after exp is not ';'");
       	 	System.exit(1);
		}
		
		/*Skip ";"*/
		tokenizer.skipToken();
	}
	
	/* Method for printing assign*/
	void PrintAssign() {
		System.out.println("");
		Tab t = Tab.Instance();
		String tab  = "";
		for(int i = 0; i < t.Number(); i++) {
			tab = tab + "\t";
		}
		System.out.print(tab);
		id.PrintId();
		System.out.print("=");
		exp.PrintExp();
		System.out.print("; ");
	}
	
	/* Execute assignment, call assign method in Id class*/
	void ExecAssign() {
		id.assign(exp.ExecExp());
	}
	

}
