package edu.c3341;

public enum TokenKind{
	PROGRAM(1),
	BEGIN(2),
	END(3),
	INT(4),
	IF(5),
	THEN(6),
	ELSE(7),
	WHILE(8),
	LOOP(9),
	READ(10),
	WRITE(11),
	SEMICOLON(12),
	COMMA(13),
	ASSIGN_OPERATOR(14),
	NOT_OPERATOR(15),
	LEFT_BRACKET(16),
	RIGHT_BRACKET(17),
	AND_OPERATOR(18),
	OR_OPERATOR(19),
	LEFT_PARENTHESE(20),
	RIGHT_PARENTHESE(21),
	ADD_OPERATOR(22),
	MINUS_OPERATOR(23),
	MULTIPLY_OPERATOR(24),
	NOT_EQUAL(25),
	EQUAL_OPERATOR(26),
	LESS_THAN(27),
	GREATER_THAN(28),
	LESS_EQUAL(29),
	GREATER_EQUAL(30),
	INTEGER(31),
	IDENTIFIER(32),
	EOF(33),
	ERROR(34);
	private int testDriverTokenNumber;
	
	private TokenKind(int number) {
		this.testDriverTokenNumber = number;
	}
	
	public int testDriverTokenNumber() {
		return this.testDriverTokenNumber;
	}
	
}
	


