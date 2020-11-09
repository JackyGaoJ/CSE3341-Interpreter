package edu.c3341;

import edu.c3341.TokenKind;

public interface Tokenizer {
	TokenKind getToken();
	void skipToken();
	int intVal();
	String idName();
}

