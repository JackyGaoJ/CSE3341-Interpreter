package edu.c3341;

/* Condition class*/
public class Cond {
	private Comp comp;
	private Cond cond1;
	private Cond cond2;
	private int altNo;
	public Cond() {
		comp = null;
		cond1 = null;
		cond2 = null;
		altNo = 0;
	}
	/* Method for parsing condition*/
	void ParseCond() {
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		
		/* Check which alternative this condition is*/
		if (tokenizer.getToken().testDriverTokenNumber() == 15) {
			altNo = 1;
			/* Skip the token*/
			tokenizer.skipToken();
			cond1 = new Cond();
			cond1.ParseCond();
		}
		else if (tokenizer.getToken().testDriverTokenNumber() == 16) {
			altNo = 2;
			/* Skip the token*/
			tokenizer.skipToken();
			cond1 = new Cond();
			cond1.ParseCond();
			/* Check if the token after first condition is "&&" or "||" */
			if (tokenizer.getToken().testDriverTokenNumber() != 18 && tokenizer.getToken().testDriverTokenNumber() != 19) {
				System.err.println("Error when parsing Cond: The first token after the first condition is not '&&' or '||'");
				System.exit(1);
			}
			else {
				
				if (tokenizer.getToken().testDriverTokenNumber() == 18) { /* "&&" condition*/
					altNo = 3;
				}
				else { /* "||" condition*/
					altNo = 4;
				}
				
				/* Skip the token*/
				tokenizer.skipToken();
				cond2 = new Cond();
				cond2.ParseCond();
				/* Skip the token*/
				tokenizer.skipToken();
			}
		}
		else {/* comparison */
			altNo = 5;
			comp = new Comp();
			comp.ParseComp();
		}
	}
	/* Method for printing condition*/
	void PrintCond() {
		if (altNo == 1) { /* !condition*/
			System.out.print("!");
			cond1.PrintCond();
		}
		else if (altNo == 3 || altNo == 4) {
			System.out.print("[");
			cond1.PrintCond();
			if (altNo == 3) {
				System.out.print("&&");/* condition && condition */
			}
			else {
				System.out.print("||");/* condition || condition */
			}
			cond2.PrintCond();
			System.out.print("]");
		}
		else if (altNo == 5) { /* comparison */
			comp.PrintComp();
		}
	}
	
	/* Method for evaluating condition*/
	boolean EvalCond() {
		boolean ans = false;
		
		if (altNo ==1) { /* !condition*/
			ans = !cond1.EvalCond();
		}
		else if (altNo == 3) {/* condition && condition */
			ans = cond1.EvalCond() && cond2.EvalCond();
		}
		else if (altNo == 4) {/* condition || condition */
			ans = cond1.EvalCond() || cond2.EvalCond();
		}
		else if (altNo ==5) { /* comparison, call evaluate comparison method */
			ans = comp.EvalComp();
		}
		return ans;
	}
}
	

