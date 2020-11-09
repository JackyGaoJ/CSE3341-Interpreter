package edu.c3341;
import java.util.ArrayList;

/* Comparison class*/
public class Comp {
	private Op op1;
	private Op op2;
	private String compop;
	private static ArrayList<Integer> CompOperation= new ArrayList<Integer>();
	private static String[] CompOperationString = {"!=", "==", "<", ">", "<=", ">="}; /*Array store all comparison operation*/
	
	public Comp (){
		op1 = null;
		op2 = null;
		compop = null;
		int[] arr = {25,26,27,28,29,30};
		/* Add token number to the ArrayList for convenient matching in parsing*/
		for (int i = 0; i<arr.length; i++) {
			CompOperation.add(arr[i]);
		}
	}
	
	/* Method for parsing comparison*/
	void ParseComp() {
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		
		/* Check if the first token in comparison is '(' */
		if (tokenizer.getToken() != TokenKind.LEFT_PARENTHESE) {
			System.err.println("Error when parsing Comparison: The first token in comparison is not '(' ");
			System.exit(1);
		}
		
		/* Skip the '(' */
		tokenizer.skipToken();
		op1 = new Op();
		op1.ParseOp();
		
		/* Check if the first token after "(" is in comparison operations */
		if (!CompOperation.contains(tokenizer.getToken().testDriverTokenNumber())){
			System.out.println("Asdasda" + tokenizer.token);
			System.err.println("Error when parsing Cmparison: The first token in comparison after the first op is not a comparison operation ");
			System.exit(1);
		}
		compop = CompOperationString[CompOperation.indexOf(tokenizer.getToken().testDriverTokenNumber())];
		
		/* Skip the comparison operation*/
		tokenizer.skipToken();
		op2 = new Op();
		op2.ParseOp();
		
		/* Check if the last token in comparison is ')' */
		if (tokenizer.getToken().testDriverTokenNumber() != 21) {
			System.err.println("Error when parsing Comparison: The last token in comparison is not ')' ");
			System.exit(1);
		}
		
		/* Skip the ")" */
		tokenizer.skipToken();
	}
	
	/* Method for printing comparison*/
	void PrintComp() {
		System.out.print("(");
		op1.PrintOp();
		System.out.print(compop);
		op2.PrintOp();
		System.out.print(") ");
	}
	
	/* Method for evaluating comparison*/
	boolean EvalComp() {
		boolean ans = false;
		if (compop == "!=") {
			ans = op1.ExecOp() != op2.ExecOp();
		}
		else if (compop == "==") {
			ans = op1.ExecOp() == op2.ExecOp();
		}
		else if (compop == "<") {
			ans = op1.ExecOp() < op2.ExecOp();
		}
		else if (compop == ">") {
			ans = op1.ExecOp() > op2.ExecOp();
		}
		else if (compop == "<=") {
			ans = op1.ExecOp() <= op2.ExecOp();
		}
		else if (compop == ">=") {
			ans = op1.ExecOp() >= op2.ExecOp();
		}
		return ans;
	}
	
}
