package fi.interjektio.dev.idea.sway.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import fi.interjektio.dev.idea.sway.lang.psi.SwayTypes;
import com.intellij.psi.TokenType;

%%

%class SwayLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

WHITE_SPACE = [ \t\n\r]+
COMMENT = "//" [^\n]* | "/*" [^*]* "*/"
NUMBER = [0-9]+(\.[0-9]+)?
IDENTIFIER = [a-zA-Z_][a-zA-Z0-9_]*
KEYWORD = "fn" | "let" | "mut" | "struct" | "enum" | "impl" | "trait" | "pub" | "mod" | "const" | "return" | "true" | "false"
OPERATOR = "=>" | "=" | "==" | "!=" | "<=" | ">=" | "+" | "-" | "*" | "/" | "%" | "&&" | "||" | "!" | "<" | ">"
STRING = \"(\\.|[^"\\])*\"

%%

<YYINITIAL> {
  {WHITE_SPACE}     { return TokenType.WHITE_SPACE; }
  {COMMENT}         { return SwayTypes.COMMENT; }
  {KEYWORD}         { return SwayTypes.KEYWORD; }
  {NUMBER}          { return SwayTypes.NUMBER; }
  {IDENTIFIER}      { return SwayTypes.IDENTIFIER; }
  {STRING}          { return SwayTypes.STRING; }
  {OPERATOR}        { return SwayTypes.OPERATOR; }
  .                 { return TokenType.BAD_CHARACTER; }
}