package edu.c3341;

import java.util.Scanner;

public class IdList {
	private Id id;
	private IdList idList;
	private static Tokenizer1 tokenizer = Tokenizer1.Instance();
	private int altNo;
	public IdList() {
		id = null;
		idList = null;
		altNo = 0;
	}
	void ParseIdListForDs(){
		
		
		id = Id.parseForDeclSeq();
		altNo=1;

		if (tokenizer.getToken() == TokenKind.COMMA && tokenizer.getToken() != TokenKind.SEMICOLON) {
			tokenizer.skipToken();
			idList = new IdList();
			idList.ParseIdListForDs();
			altNo = 2;
		}
	}
	
	void ParseIdListForSs() {
		id = Id.parseForStmtSeq();
		altNo = 1;
		if (tokenizer.getToken() == TokenKind.COMMA && tokenizer.getToken() != TokenKind.SEMICOLON) {
			tokenizer.skipToken();
			idList = new IdList();
			idList.ParseIdListForSs();
			altNo = 2;
		}
	}
	void PrintIdList() {
		id.PrintId();
		if (idList != null) {
			System.out.print(", ");
			idList.PrintIdList();
		}
	}
	
	void ExecIdListRead() {
		Scanner inputStream = InputFile.Instance();
		
		if(!inputStream.hasNext())
        {
            System.err.println("Error: input values are not enough");
            System.exit(1);
        }
		
        String nextVal = inputStream.next();
        if (!nextVal.matches("-?(0|[1-9]\\d*)")) {
        	 System.err.println("Error: input: " + nextVal + " is not an integer");
             System.exit(1);
        }
        
        id.assign(Integer.parseInt(nextVal));
        if (altNo == 2) {
        	idList.ExecIdListRead();
        }
	}
	
	void ExecIdListWrite() {
		System.out.println();
		id.PrintId();
		System.out.print(" = ");
		System.out.print(id.value());
		if (altNo == 2) {
			idList.ExecIdListWrite();
		}
		
	}
}
