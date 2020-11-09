Author: Jian Gao

Function files:
Interpreter.java！！Interpreter class contains main method to begin interpreter. Two scanners to handle input core program and input data file. Calling ParseProg() to begin parsing. Similarly, printing and executing.
TokenKind.java！！ Enumeration that contains all token kinds that will be needed in this program. Also contains method to return the number assigned before of those tokens.
Tokenizer.java！！ Tokenizer interface. It has defined methods that a tokenizer needs, which will be implemented in Tokenizer1.java class.
Tokenizer1.java ！！ Tokenizer1 class that implements Tokenizer interface. It is a Singleton class which ensure only one tokenizer is running when we are parsing the program. Main methods are the same as previous projects. I implemented 2 more functions to return the number of integer token and string or ID token.
InputFile.java ！！ This is a singleton class that handle with the input data file. The scanner will check and return the input data one by one, which is needed when parsing and printing Id.
Tab.java ！！ This is a singleton class that store the number of tab needed when printing each statement. It helps print statements more nicely.

Classes for each BNF:
Prog.java: Contains parse program, printing program and executing program methods. In each method, it will call the same methods in other classes.
DS.java: Declaration sequence class, contains methods to parse, print and execute a declaration sequences. Also, since it has alternatives, I checked alternatives to recursively do the same things on remainder declaration sequence.
Decl.java: Declaration class. Contain methods to parse, print and execute a single declaration.  This helped DS.java to parse, print and execute more conveniently. 
SS.java: Statement sequence class, contains methods to parse, print and execute a statement sequences. Also, since it has alternatives, I checked alternatives of statement to recursively do the same things on remainder statement sequence.
Stmt.java: Statement class. First check which statement the program has according to the tokenizer, then parse, print and execute according to which statement it is.
If.java: If statement class. It contains methods to parse, print and execute a if statement. Also, it checks whether this if statement has an alternative.
Loop.java: Loop statement class. If the program contains a token while, it will jump to this class to parse, print and execute.
In.java: Input statement class. If the program contains a token read, it will jump to this class to get input data by calling IdList.java. It has methods parse, print and execute the input statement.
Out.java: Output statement class. If the program contains a token write, it will jump to this class to write the value of the identifier according to the program. It has methods parse, print and execute the output statement.
Assign.java: Assign statement class. If the program contains a single identifier, it will jump to this class to assign value to this identifier. It has methods parse, print and execute the assign statement.
Cond.java: Condition class. It helps parse, print and evaluate a condition for if statement and while statement.
Comp.java: Comparison class. It handles all kinds of comparison between two identifiers for a condition. It has methods for parsing, printing and evaluating a comparison.
Exp.java: Expression class. It has methods for parsing, printing and executing an expression. Executing an expression will return a final value for that expression.
Op.java: Operation class. It has methods for parsing, printing and executing operations. Executing an operation will return a value of an identifier, a number or a final result of an expression.
Trm.java: Term class. It has methods for parsing, printing and executing a single term. It helps reduce loads of expression class.
idList.java: List of id class. It has methods for parsing, printing  and executing a list of identifiers. It also helps both declaration sequence and statement sequence.
Id.java: Identifier class. It has methods for parsing, printing and executing a single identifier. It helps idList to handle a single identifier, for either declaration or statement sequences.


Instruction for how to compile and run the project:
1. First, direct the the directory of the project

2. Second, direct to the directory contains all source file ！！ cd src, cd edu, cd c3341

3. Third, type in the command to compile the project: javac Tokenizer.java TokenKind.java Tokenizer1.java Interpreter.java Prog.java Assign.java Comp.java Cond.java Decl.java DS.java Exp.java Id.java IdList.java If.java In.java InputFile.java Loop.java Op.java Out.java Prog.java SS.java Stmt.java Trm.java Tab.java

4. Back to the src directory. And type: java edu.c3341.Interpreter (absolute Path to the core program) (absolute Path to the Input data file, which could be ignored if there is no input data) (print/doNotPrint). For example: java edu.c3341.Interpreter /Users/jiangao/Desktop/Interpreter/test/test2.txt /Users/jiangao/Desktop/Interpreter/test/test2data.txt print