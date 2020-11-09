package edu.c3341;


/*Output class*/
public class Out {
	private IdList idList;
	public Out() {
		idList = null;
	}
	/*Method to parse output*/
	void ParseOut() {
		Tokenizer1 tokenizer = Tokenizer1.Instance();
		if (tokenizer.getToken() != TokenKind.WRITE) {/*Check precondition*/
			System.err.print("Error: The first token when parsing output is not write!");
			System.exit(1);
		}
		tokenizer.skipToken(); /*Skip the "write" keyword*/
		idList = new IdList();
		idList.ParseIdListForSs(); /*Parse IdList for output*/
		if (tokenizer.getToken() != TokenKind.SEMICOLON) { /*Check the last token*/
			System.err.println("Error when parsing Out: The first token after id list is not ';'");
			System.exit(1);
		}
		tokenizer.skipToken(); /*Skip ";"*/
	}
	
	/*Method to print output*/
	void PrintOut() {
		System.out.println("");
		Tab t = Tab.Instance();
		String tab  = "";
		for(int i = 0; i < t.Number(); i++) {
			tab = tab + "\t";
		}
		System.out.print(tab + "Write ");
		idList.PrintIdList(); /*Print IdList*/
		System.out.print(";");
	}
	void ExecOut() {
		idList.ExecIdListWrite();
		
	}
	

}

