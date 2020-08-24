grammar MicroC;

@header {
 
}

program : decls function;

/* Declarations */
decls : var_decl decls
      | str_decl decls
	 | /* empty */ ;

var_decls : var_decl var_decls 
          | /* emoty */ ;

/* Identifiers and types */		  
id : IDENTIFIER ;
		  
var_decl : 'int' id ';' ;

str_decl : 'string' id '=' STR_LITERAL ';' ;

base_type : 'int';

/* Functions */

function : base_type 'main' '(' ')' '{' statements '}' ;
		 		 
/* Statements */
		 
statements : statement statements
            | /* empty */ ;
			
statement : base_stmt ';'
		  | if_stmt
		  | while_stmt ;
		  
base_stmt : assign_stmt
          | read_stmt
		| print_stmt
		| return_stmt ;
		 
read_stmt : 'read' '(' id ')' ;

print_stmt : 'print' '(' expr ')' ;

return_stmt : 'return' expr ;

assign_stmt : id '=' expr ;

/* if_stmt rules go here */

if_stmt : /* FILL IN FOR STEP 1 */ ;
	  
while_stmt : 'while' '(' cond ')' '{' statements '}' ;
	 
/* Expressions */

primary : id
        | '(' expr ')'
        | unaryminus_expr
        | INT_LITERAL;

unaryminus_expr : '-' expr ;
		 
/* This is left recursive, but ANTLR will clean this up */ 
expr : term
     | expr addop term ;
	 
/* This is left recursive, but ANTLR will clean this up */
term : primary
     | term mulop primary ;
	   	   
cond : expr cmpop expr ;

cmpop : '<' | '<=' | '>=' | '==' | '!=' | '>' ;

mulop : '*' | '/' ;

addop : '+' | '-' ;

/* Tokens */

IDENTIFIER : LETTER (LETTER | DIGIT)* ;

INT_LITERAL : DIGIT+;

STR_LITERAL : '"' (~('"'))* '"' ;

COMMENT : '/*' .*? '*/' -> skip;

WS : [ \t\n\r]+ -> skip;

fragment LETTER : ('a'..'z' | 'A'..'Z') ;

fragment DIGIT : ('0'..'9') ;
