package CompiladoresHoc3;
import java_cup.runtime.*;
import java.io.Reader;

%% /* inici de las declaracions JFlex*/
%class AnalizadorLexico
%line 
%column
%char
%cup

%{
    public SymbolHoc s;
    public int TipSimb;

    TablaSimbolos ListaSimb = new TablaSimbolos();
    /*ListaSimb.init();*/

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
{Digito}+(\.{Digito}+)?     {
                                return symbol(AnalizadorSintacticoSym.NUM, new Float(yytext()));
                            }
"="                         {   return symbol(AnalizadorSintacticoSym.OpAsig);}
"/"                         {   return symbol(AnalizadorSintacticoSym.OpDiv);}
"*"                         {   return symbol(AnalizadorSintacticoSym.OpProd);}
"-"                         {   return symbol(AnalizadorSintacticoSym.OpResta);}
"+"                         {   return symbol(AnalizadorSintacticoSym.OpSuma);}
")"                         {   return symbol(AnalizadorSintacticoSym.ParDer);}
"("                         {   return symbol(AnalizadorSintacticoSym.ParIzq);}
\^                          {   return symbol(AnalizadorSintacticoSym.OpPotencia);}
{Letra}({Letra}|{Digito})*  {
                                s = ListaSimb.lookup(yytext());
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


