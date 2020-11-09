package edu.c3341;

/* Expression class*/
public class Exp {
	private Trm term;
	private Exp exp;
	private int altNo;
	public Exp create() {
		Exp instance = new Exp();
		instance.term = null;
		instance.exp = null;
		instance.altNo = 0;
		return instance;
	}
	
	/* Method for parsing expression*/
	void ParseExp() {
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		term = new Trm();
		term.ParseTrm(); /* Call parse term method for term*/
		
		/* Check which alternative this expression is */
		if (tokenizer.getToken().testDriverTokenNumber() == 22) { /* term + expression alternative*/
			altNo = 1;
			tokenizer.skipToken();
			exp = new Exp();
			exp.ParseExp();

		}
		else if (tokenizer.getToken().testDriverTokenNumber() == 23){/* term - expression alternative*/
			altNo = 2;
			tokenizer.skipToken();
			exp = new Exp();
			exp.ParseExp();

		}
	}
	
	/* Method for printing expressions*/
	void PrintExp() {
		term.PrintTrm(); /* Call print method for term*/
		if (altNo == 1) {/* term + expression alternative*/
			System.out.print("+");
			exp.PrintExp(); /* Recursively print expression*/
		}
		else if (altNo == 2) {/* term - expression alternative*/
			System.out.print("-");
			exp.PrintExp();/* Recursively print expression*/
		}
	}
	
	/* Method for executing expression*/
	int ExecExp() {
		if (altNo == 0) { /* Only one term*/
			return term.ExecTrm();/* Call execute method for term*/
		}
		else if (altNo == 1) {/* term + expression alternative*/
			return term.ExecTrm() + exp.ExecExp();/* Recursively execute expression*/
		}
		else  {/* term - expression alternative*/
			return term.ExecTrm() - exp.ExecExp();/* Recursively execute expression*/
		}
	}

}
