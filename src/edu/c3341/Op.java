package edu.c3341;

/* Operation class*/
public class Op {
	private int number;
	private Id id;
	private Exp exp;
	private int altNo;
	public Op create() {
		Op instance = new Op();
		instance.id=null;
		instance.number = 0;
		instance.exp = null;
		instance.altNo = 0;
		return instance;
	}
	
	/*Method to parse operation*/
	void ParseOp() {
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		
		/*Check alternatives for operation*/
		if (tokenizer.getToken().testDriverTokenNumber()== 31) {
			altNo = 1;
			number = tokenizer.intVal();
			tokenizer.skipToken();
		}
		else if (tokenizer.getToken().testDriverTokenNumber() == 32) {
			altNo = 2;
			id = Id.parseForStmtSeq();
		}
		else if (tokenizer.getToken().testDriverTokenNumber() == 20) { 
			altNo = 3;
			tokenizer.skipToken();
			exp = new Exp();
			exp.ParseExp();
			if (tokenizer.getToken().testDriverTokenNumber() != 21) {
				System.err.println("Error when parsing Op: The first token after exp is not ')'");
        		System.exit(1);
			}
			tokenizer.skipToken(); /*Skip the token ")"*/
		}
				
	}
	
	/*Print operation*/
	void PrintOp() {
		
		/*Check alternative of Operation*/
		if (altNo == 1) {
			System.out.print(number);
		}
		else if (altNo == 2) {
			id.PrintId();/*Call PrintId to print Id*/
		}
		else if (altNo ==3) {
			System.out.print("(");
			exp.PrintExp(); /*Call PrintExp to print expression*/
			System.out.print(")");
		}
	}
	
	/*Execute operation, return the value after execution*/
	int ExecOp() {
		
		/* Check alternative of operation*/
		if (altNo == 1) {
			return number;
		}
		else if (altNo ==2) {
			return id.value();
		}
		else {
			return exp.ExecExp();
		}
	}
	
}
