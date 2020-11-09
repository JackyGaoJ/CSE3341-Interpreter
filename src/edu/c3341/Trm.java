package edu.c3341;


/*Term class*/
public class Trm {
	private Op op;
	private Trm term;
	private int altNo;
	public Trm(){
		altNo = 0;
		term = null;
		op = null;
	}
	
	/*Method to parse term*/
	void ParseTrm() {
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		op = new Op();
		op.ParseOp(); /*Parse operation for term*/
		if (tokenizer.getToken().testDriverTokenNumber() == 24) {/*Multiplication alternative*/
			altNo = 1;
			tokenizer.skipToken(); /*Skip "*"*/
			term = new Trm();
			term.ParseTrm(); /*Recursively parse term*/
		}
		
	}
	
	/*Method to print term*/
	void PrintTrm() {
		op.PrintOp(); /* Print operation for term*/
		if (altNo == 1) {/*Multiplication alternative*/
			System.out.print("*");
			term.PrintTrm();/*Recursively print term*/
		}
	}
	
	/*Execute term*/
	int ExecTrm() {
		if (altNo == 0) {
			return op.ExecOp(); /*Execute operation*/
		}
		else {
			return op.ExecOp() * term.ExecTrm(); /*Multiplication alternative*/
		}
	}

}
