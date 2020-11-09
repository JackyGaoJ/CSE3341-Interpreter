package edu.c3341;

import java.util.ArrayList;

import java.util.Scanner;

/*The Tokenizer1 class implementing Tokenizer interface
 * Author : Jian Gao*/

public class Tokenizer1 implements Tokenizer{
	private static Tokenizer1 tokenizer_instance = null;
	/* The instance variable that specify the type of current token*/
	public TokenKind token_kind;      
	
	/* Instance variable to store the current token*/
	public StringBuilder token = new StringBuilder();  
	
	/*Instance variable store all tokens in the ArrayList*/
	public ArrayList<StringBuilder> all_tokens = new ArrayList<StringBuilder>(); 
	
	
	/*Instance variable to be checked, which is the "next" string get by scanner*/
	public String front = ""; 
	
	/*Instance variable represent the current character that is being checked*/
	public char indexChar; 
	
	/*Instance variable that represent the scanner of the rest of the test file*/
	public static Scanner remainder; 
	
	/*Instance variable that represent the position of the current character in the front string*/
	public int position =0; 

	
	private Tokenizer1(Scanner in) {
		Tokenizer1.remainder = in;
		findTokenProcess();	
	}
	
	/*Create a instance of the Tokenizer1 class, and start to check the token kind immediately*/
	public static Tokenizer1 create(Scanner in) {
		if (tokenizer_instance == null) {
			tokenizer_instance = new Tokenizer1(in); 
		}
		return tokenizer_instance;
	}
	public static Tokenizer1 Instance() {
		return tokenizer_instance;
	}
	/* The process to find the token, and get the TokenKind by using switch statement*/
	public void findTokenProcess() {
		int state = 0;
		this.token.setLength(0);
		boolean end_of_front = false;
		if (this.front.length()<=this.position) {
			if (this.remainder.hasNext()) {
				this.front = this.remainder.next();
				this.position = 0;
			}
			else {
				/*No more string in the file*/
				this.token_kind = TokenKind.EOF;
				return;
			}
		}
		
		while (!end_of_front) {
			switch(state) {
			case 0:
				this.indexChar = this.front.charAt(this.position);
				this.position++;
				this.token.append(this.indexChar);
				if (this.indexChar == ';') { /*Go to condition when meet a semicolon*/
					state = 1;
				}
				else if (this.indexChar == '=') { /*Go to condition when meet a equal sign*/
					state = 2;
				}
				else if (this.indexChar =='|') { /*Go to condition when meet a or sing*/
					state = 3;
				}
				else if (Character.isUpperCase(this.indexChar)) {/*Go to condition when meet a upper case character*/
					state = 4;
				}
				else if (Character.isDigit(this.indexChar)) {/*Go to condition when meet a digit*/
					state = 5;
				}
				else if (Character.isLowerCase(this.indexChar)) { /*Go to condition when meet a lower case character*/
					state = 6;	
				}
				else if (this.indexChar == ',') { /*Go to condition when meet a comma sign*/
					state = 9;
				}
				else if (this.indexChar == '!') { /*Go to condition when meet a not sign*/
					state = 10;
				}
				else if (this.indexChar == '[') { /*Go to condition when meet a left bracket*/
					state = 11;
				}
				else if (this.indexChar == ']') { /*Go to condition when meet a right bracket*/
					state = 12;
				}
				else if (this.indexChar == '(') { /*Go to condition when meet a left parentheses*/
					state = 13;
				}
				else if (this.indexChar == ')') { /*Go to condition when meet a right parentheses*/
					state = 14;
				}
				else if (this.indexChar == '+') { /*Go to condition when meet a add sign*/
					state = 15;
				}
				else if (this.indexChar == '-') { /*Go to condition when meet a minus sign*/
					state = 16;
				}
				else if (this.indexChar == '*') { /*Go to condition when meet a multiply sign*/
					state = 17;
				}
				else if(this.indexChar == '<') { /*Go to condition when meet a less than sign*/
					state = 18;
				}
				else if (this.indexChar == '>') { /*Go to condition when meet a greater than sign*/
					state = 19;
				}
				else if (this.indexChar == '&') { /*Go to condition when meet a and sign*/
					state = 20;
				}
				else {   /*Go to condition when meet a symbol that doesn't meet the requirement, which is an error*/
					state = 7;
				}
				break;

			case 1:
				this.token_kind = TokenKind.SEMICOLON;
				this.all_tokens.add(this.token);
				return;
			case 2:
				if (this.front.length()>this.position) {
					this.indexChar = this.front.charAt(this.position);
					/* Double ==*/
					if (this.indexChar == '=') {
						this.token.append(this.indexChar);
						this.all_tokens.add(this.token);
						this.token_kind = TokenKind.EQUAL_OPERATOR;
						this.position++;
						return;
					}
					/*Only one =*/
					else {
						this.all_tokens.add(this.token);
						this.token_kind = TokenKind.ASSIGN_OPERATOR;
						return;
					}
				}
				/*Only one =*/
				else {
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.ASSIGN_OPERATOR;
					return;
				}
			case 3:
				if (this.front.length()>this.position) {
					this.indexChar = this.front.charAt(this.position);
					/*Or Operator*/
					if (this.indexChar == '|') {
						this.token.append(this.indexChar);
						this.all_tokens.add(this.token);
						this.token_kind = TokenKind.OR_OPERATOR;
						this.position ++;
						return;
					}
					/*Invalid Or operator*/
					else {
						this.all_tokens.add(token);
						state = 7;
						break;
					}
					/*Invalid Or Operator, only one '|'*/
				}else {
					this.all_tokens.add(token);
					this.token_kind = TokenKind.ERROR;
					return;
				}
			case 4:
				while (this.front.length()>this.position && Character.isUpperCase(this.indexChar)) {
					this.indexChar = this.front.charAt(this.position);
					if (Character.isUpperCase(this.indexChar)) {
						this.token.append(this.indexChar);
						this.position++;
					}
					
				}
				/*There are more characters after upper case letters*/
				if (this.front.length()>this.position) {
					this.indexChar = this.front.charAt(this.position);
			
					while (this.front.length()>this.position&&Character.isDigit(this.indexChar)) {
						this.token.append(this.indexChar);
						this.position ++;
						if (this.front.length()>this.position) {
							this.indexChar = this.front.charAt(this.position);
						}
					}
					/*Check whether this identifier is valid or not*/
					if (Character.isUpperCase(this.indexChar) || Character.isLowerCase(this.indexChar)) {
						this.all_tokens.add(this.token);
						state = 7;
						break;
					}
					else {
						this.all_tokens.add(this.token);
						this.token_kind = TokenKind.IDENTIFIER;
						return;
					}
				}
				/*Given front string is a valid identifier*/
				else {
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.IDENTIFIER;
					return;
				}
			case 5:
				while (this.front.length()>this.position && Character.isDigit(this.indexChar)) {
					this.indexChar = this.front.charAt(this.position);
					if (Character.isDigit(this.indexChar)) {
						this.token.append(this.indexChar);
						this.position++;
					}
					
				}
				/*There are more characters after digits*/
				if (this.front.length()>this.position) {
					this.indexChar = this.front.charAt(this.position);
					
					/*Check whether this integer is valid or not*/
					if (Character.isUpperCase(this.indexChar) || Character.isLowerCase(this.indexChar)) {
						this.all_tokens.add(this.token);
						state = 7;
						break;
					}
					else {
						this.all_tokens.add(this.token);
						this.token_kind = TokenKind.INTEGER;
						return;
					}
				}/*Given front string is a valid integer*/
				else {
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.INTEGER;
					return;
				}

			case 6:
				while (this.front.length()>this.position && Character.isLowerCase(this.indexChar)) {
					this.indexChar = this.front.charAt(this.position);
					if (Character.isLowerCase(this.indexChar)) {
						this.token.append(this.indexChar);
						this.position++;
					}
					
				}
				/*There are more characters after lower case letters*/
				if (this.front.length()>this.position) {
					this.indexChar = this.front.charAt(this.position);
					/*Check whether this lower case word is valid or not*/
					if (Character.isUpperCase(this.indexChar) || Character.isDigit(this.indexChar)) {
						this.all_tokens.add(this.token);
					    state = 7;
					    break;
					}
					else {
						this.all_tokens.add(this.token);
						state = 8;
						break;
					}
				}/*Given front string is a valid lower case word*/
				else {
					this.all_tokens.add(this.token);
					state = 8;
					break;
				}
				
			/*Invalid input symbol*/
			case 7:
				this.token_kind = TokenKind.ERROR;
				return;
			
			/*Check the what token the lower case word is  */
			case 8:
				if (this.token.toString().equals("program")) {
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.PROGRAM;
					return;
				}
				else if (this.token.toString().equals("begin")) {
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.BEGIN;
					return;
				}
				else if(this.token.toString().equals("end")){
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.END;
					return;
				}
				else if(this.token.toString().equals("int")){
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.INT;
					return;
				}
				else if(this.token.toString().equals("if")){
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.IF;
					return;
				}
				else if(this.token.toString().equals("then")){
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.THEN;
					return;
				}
				else if(this.token.toString().equals("else")){
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.ELSE;
					return;
				}
				else if(this.token.toString().equals("while")){
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.WHILE;
					return;
				}
				else if(this.token.toString().equals("loop")){
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.LOOP;
					return;
				}
				else if(this.token.toString().equals("read")){
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.READ;
					return;
				}
				else if(this.token.toString().equals("write")){
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.WRITE;
					return;
				}
				/*If the lower case word is not a token above, then this token is invalid*/
				else {
					this.all_tokens.add(this.token);
					state = 7;
				    break;
				}
			case 9:
				this.token_kind = TokenKind.COMMA;
				this.all_tokens.add(this.token);
				return;
			case 10:
				/*Check if there is any character after '!'*/
				if (this.front.length()>this.position) {
					this.indexChar = this.front.charAt(this.position);
					/*If the character follow '!' is '=', then the token is not_equal*/
					if (this.indexChar == '=') {
						this.token.append(this.indexChar);
						this.all_tokens.add(this.token);
						this.token_kind = TokenKind.NOT_EQUAL;
						this.position++;
						return;
					}
					else {
						this.all_tokens.add(this.token);
						this.token_kind = TokenKind.NOT_OPERATOR;
						return;
					}
				}
				else {
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.NOT_OPERATOR;
					return;
				}
			case 11:
				this.token_kind = TokenKind.LEFT_BRACKET;
				this.all_tokens.add(this.token);
				return;
			case 12:
				this.token_kind = TokenKind.RIGHT_BRACKET;
				this.all_tokens.add(this.token);
				return;
			case 13:
				this.token_kind = TokenKind.LEFT_PARENTHESE;
				this.all_tokens.add(this.token);
				return;
			case 14:
				this.token_kind = TokenKind.RIGHT_PARENTHESE;
				this.all_tokens.add(this.token);
				return;
			case 15:
				this.token_kind = TokenKind.ADD_OPERATOR;
				this.all_tokens.add(this.token);
				return;
			case 16:
				this.token_kind = TokenKind.MINUS_OPERATOR;
				this.all_tokens.add(this.token);
				return;
			case 17:
				this.token_kind = TokenKind.MULTIPLY_OPERATOR;
				this.all_tokens.add(this.token);
				return;
			case 18:
				/*Check if there is any character after '<'*/
				if (this.front.length()>this.position) {
					this.indexChar = this.front.charAt(this.position);
					/*If the character follow '<' is '=', then the token is less_equal_than*/
					if (this.indexChar == '=') {
						this.token.append(this.indexChar);
						this.all_tokens.add(this.token);
						this.token_kind = TokenKind.LESS_EQUAL;
						this.position++;
						return;
					}
					else {
						this.all_tokens.add(this.token);
						this.token_kind = TokenKind.LESS_THAN;
						return;
					}
				}
				else {
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.LESS_THAN;
					return;
				}
			case 19:
				/*Check if there is any character after '>'*/
				if (this.front.length()>this.position) {
					this.indexChar = this.front.charAt(this.position);
					/*If the character follow '<' is '=', then the token is greater_equal_than*/
					if (this.indexChar == '=') {
						this.token.append(this.indexChar);
						this.all_tokens.add(this.token);
						this.token_kind = TokenKind.GREATER_EQUAL;
						this.position++;
						return;
					}
					else {
						this.all_tokens.add(this.token);
						this.token_kind = TokenKind.GREATER_THAN;
						return;
					}
				}
				else {
					this.all_tokens.add(this.token);
					this.token_kind = TokenKind.GREATER_THAN;
					return;
				}
			case 20:
				/*Check if there is any character after '&'*/
				if (this.front.length()>this.position) {
					this.indexChar = this.front.charAt(this.position);
					/*If the character follow '&' is '&', then the token is and_operator. Otherwise are invalid tokens.*/
					if (this.indexChar == '&') {
						this.token.append(this.indexChar);
						this.all_tokens.add(this.token);
						this.token_kind = TokenKind.AND_OPERATOR;
						this.position++;
						return;
					}
					else {
						state = 7;
					}
				}
				else {
					state = 7;
				}
			}
		}
	}
	
	/*Get the kind of current token*/
	public TokenKind getToken() {
		return this.token_kind;
	}

	
	/*Skip the current token when it is successfully be checked*/
	public void skipToken() {
		this.findTokenProcess();
	}

	
	
	/*Method does not need to be implemented for part1*/
	public int intVal() {
		return Integer.parseInt(this.token.toString());
	}
	
	/*Method does not need to be implemented for part1*/
	public String idName() {
		return this.token.toString();
	}
}
