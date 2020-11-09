package edu.c3341;

import java.util.HashMap;
import java.util.Map;


/* Id class*/
public class Id {
    static Map<String, Id> ids = new HashMap<>();
    static int count = 0;
	private String name;
	private int val;
	private boolean assigned;
	private static Tokenizer1 tokenizer = Tokenizer1.Instance();
	public Id(String s) {
		name = s;
		ids.put(s, this);
		count++;
		assigned = false;
		
		
	}
	
	/* Method for parsing Id for declaration sequence*/
	static Id parseForDeclSeq() {
		String n = tokenizer.idName();
		Id id = null;
		if (ids.containsKey(n)) {
			id = ids.get(n);
		}
		else {
			id = new Id(n);
		}
		tokenizer.skipToken();
		return id;
	}

	static Id parseForStmtSeq() {
		String n = tokenizer.idName();
		Id id = null;
		if (ids.containsKey(n)) {
			id = ids.get(n);
		}
		else {
			System.err.println("Error: " + n+ " is not declared in parsing Id for ss sequence");
			System.exit(1);
		}
		tokenizer.skipToken();
		return id;
	}
	void assign(int a) {
		assigned = true;
		this.val= a;
		
	}


	void PrintId() {
		System.out.print(this.name);
	}

	int value() {
		if (!assigned) {
			 System.err.println("Runtime Error: " + name + " is not initialized.");
			 System.exit(1);
		}
		return val;
		
	}
}
