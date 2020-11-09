package edu.c3341;

/*Statement class*/
public class Stmt {
	private int altNo;
	private Assign s1;
	private If s2;
	private Loop s3;
	private In s4;
	private Out s5;
	public Stmt() {
		altNo = 0;
		s1 = null;
		s2 = null;
		s3 = null;
		s4 = null;
		s5 = null;
	}
	
	/*Method to parse statement*/
	void ParseStmt() {
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		int tokNo = tokenizer.getToken().testDriverTokenNumber();
		
		/*Check which alternative this statement is*/
		if (tokNo == 32) { /*Assign statement*/
			altNo = 1;
			s1 = new Assign();
			s1.ParseAssign(); /*Parse assign statement*/
		}
		if (tokNo == 5) {/*If statement*/
			altNo = 2;
			s2 = new If();
			s2.ParseIf();/*Parse If statement*/
		}
		if (tokNo == 8) {/*Loop statement*/
			altNo = 3;
			s3 = new Loop();
			s3.ParseLoop();/*Parse Loop statement*/
		}
		if (tokNo == 10) {/*Input statement*/
			altNo = 4;
			s4 = new In();
			s4.ParseIn();/*Parse Input statement*/
		}
		if (tokNo == 11) {/*Output statement*/
			altNo = 5;
			s5 = new Out();
			s5.ParseOut();/*Parse Output statement*/
		}
		
	}
	
	/*Method to print statement*/
	void PrintStmt() {
		if (altNo == 1) {/*Assign statement*/
			s1.PrintAssign();/*Print assign statement*/
		}
		if (altNo == 2) {/*If statement*/
			s2.PrintIf();/*Print If statement*/
		}
		if (altNo == 3) {/*Loop statement*/
			s3.PrintLoop();/*Print loop statement*/
		}
		if (altNo == 4) {/*Input statement*/
			s4.PrintIn();/*Print Input statement*/
		}
		if (altNo == 5) {/*Output statement*/
			s5.PrintOut();/*Print output statement*/
		}
	}
	/*Method to execute statement*/
	void ExecStmt() {
		if (altNo == 1) {/*Assign statement*/
			s1.ExecAssign();/*Execute assign statement*/
		}
		if (altNo == 2) {/*If statement*/
			s2.ExecIf();/*Execute If statement*/
		}
		if (altNo == 3) {/*Loop statement*/
			s3.ExecLoop();/*Execute Loop statement*/
		}
		if (altNo == 4) {/*Input statement*/
			s4.ExecIn();/*Execute Input statement*/
		}
		if (altNo == 5) {/*Output statement*/
			s5.ExecOut();/*Execute Output statement*/
		}
	}
	
}
