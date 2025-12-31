package CompiladoresHoc5;
import java_cup.runtime.*;
import java.io.Reader;

%% /* inicio de las declaracions JFlex*/
%class AnalizadorLexico
%line
%column
%char
%cup

%{
    public SymbolHoc s;
    public MaquinaHoc4 maqHoc;
    public int TipSimb;

    TablaSimbolos ListaSimb = new TablaSimbolos();

    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

Letra=[a-zA-Z]
Digito=[0-9]

%% /*Ahora can las expresiones regulares*/

[ \t\n]+                    {;}
";"                         {   return symbol(AnalizadorSintacticoSym.SEMIC);}

{Digito}+(\.{Digito}+)?     {   s = new SymbolHoc("",EnumTipoSymbol.CONST_NUM,Float.parseFloat(yytext())); //la mitad de esta linea me la invente yo no se si esta bien
                                return symbol(AnalizadorSintacticoSym.NUM,s);
                            }

"&&"                        {   return symbol(AnalizadorSintacticoSym.AND);}
"||"                        {   return symbol(AnalizadorSintacticoSym.OR);}
"!"                         {   return symbol(AnalizadorSintacticoSym.NOT);}
">="                        {   return symbol(AnalizadorSintacticoSym.GE);}
">"                         {   return symbol(AnalizadorSintacticoSym.GT);}
"<="                        {   return symbol(AnalizadorSintacticoSym.LE);}
"<"                         {   return symbol(AnalizadorSintacticoSym.LT);}
"=="                        {   return symbol(AnalizadorSintacticoSym.EQ);}
"!="                        {   return symbol(AnalizadorSintacticoSym.NE);}
"="                         {   return symbol(AnalizadorSintacticoSym.OpAsig);}
"/"                         {   return symbol(AnalizadorSintacticoSym.OpDiv);}
"*"                         {   return symbol(AnalizadorSintacticoSym.OpProd);}
"-"                         {   return symbol(AnalizadorSintacticoSym.OpResta);}
"+"                         {   return symbol(AnalizadorSintacticoSym.OpSuma);}
")"                         {   return symbol(AnalizadorSintacticoSym.ParDer);}
"("                         {   return symbol(AnalizadorSintacticoSym.ParIzq);}
"^"                         {   return symbol(AnalizadorSintacticoSym.OpPotencia);}
":"                         {   return symbol(AnalizadorSintacticoSym.DOSPUNTOS);}
"{"                         {   return symbol(AnalizadorSintacticoSym.LlaveIzq);}
"}"                         {   return symbol(AnalizadorSintacticoSym.LlaveDer);}
"if"                        {   return symbol(AnalizadorSintacticoSym.IF);}
"while"                     {   return symbol(AnalizadorSintacticoSym.WHILE);}
"else"                      {   return symbol(AnalizadorSintacticoSym.ELSE);}
"print"                     {   return symbol(AnalizadorSintacticoSym.PRINT);}
"for"                       {   return symbol(AnalizadorSintacticoSym.FOR);}
"switch"                    {   return symbol(AnalizadorSintacticoSym.SWITCH);}
"case"                      {   return symbol(AnalizadorSintacticoSym.CASE);}


{Letra}({Letra}|{Digito})*  {   s = ListaSimb.lookup(yytext());
                                if(s == null) //Se agrega como variable no inicializada
                                    s = ListaSimb.install(yytext(),EnumTipoSymbol.UNDEF,(float)0.0);
                                switch(s.TipoSymbol){
                                    case UNDEF:
                                            TipSimb = AnalizadorSintacticoSym.VAR;
                                            break;
                                    case VAR:
                                            TipSimb = AnalizadorSintacticoSym.VAR;
                                            break;
                                    case BLTIN:
                                            TipSimb = AnalizadorSintacticoSym.BLTIN;
                                            break;
                                    case CONST_PREDEF:
                                            TipSimb = AnalizadorSintacticoSym.CONST_PRED;
                                            break;
                                }
                                return symbol(TipSimb,s);
                            }
. { return symbol(AnalizadorSintacticoSym.error);}





