package org.ronrs.ron.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static org.ronrs.ron.lang.psi.RONTypes.*;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
%%

%{
    public _RONLexer() {
        this((java.io.Reader)null);
    }
%}

%public
%class _RONLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

True = "true"
False = "false"
Identifier = [a-zA-Z\_][a-zA-Z\_0-9]*
StringLiteral = "\"" ~"\""
Comment = "//"
Some = "Some"
Enable = "enable"
UnwrapNewtypes = "unwrap_newtypes"
ImplicitSome = "implicit_some"

DecimalLit = [0-9]+
Exponent = [eE] [-+]? {DecimalLit}
FloatLit = {DecimalLit}? \. {DecimalLit} {Exponent}?

WhiteSpace = [\n\t\r ]

%state S_COMMENT
%%

<S_COMMENT> {
    .* { yybegin(YYINITIAL); return T_COMMENT; }
}

{StringLiteral} { return T_STRING_LITERAL; }
{Comment} { yypushback(yylength()); yybegin(S_COMMENT); }
{True} { return T_TRUE; }
{False} { return T_FALSE; }
{Some} { return T_SOME; }
{Enable} { return T_ENABLE; }
{UnwrapNewtypes} { return T_UNWRAP_NEWTYPES; }
{ImplicitSome} { return T_IMPLICIT_SOME; }
{Identifier} { return T_IDENT; }

"{"   { return T_LBRACE; }
"}"   { return T_RBRACE; }
"["   { return T_LBRACK; }
"]"   { return T_RBRACK; }
"("   { return T_LPAREN; }
")"   { return T_RPAREN; }
":"   { return T_COLON;  }
","   { return T_COMMA;  }
"-"   { return T_MINUS;  }
"+"   { return T_PLUS;   }
"!"   { return T_EXCLAM; }
"#"   { return T_SHARP;  }

{FloatLit} { return T_FLOAT; }
{DecimalLit} { return T_DIGIT; }

{WhiteSpace}+ { return WHITE_SPACE; }
[^] { return BAD_CHARACTER; }