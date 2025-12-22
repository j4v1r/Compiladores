package CompiladoresHoc5;
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
    public MaquinaHoc4 maqHoc;
    public int TipSimb;

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
{Digito}+(\.{Digito}+)?     {   s = new SymbolHoc("",EnumTipoSymbol.CONST_NUM, new Float(yytext()));
                                return symbol(AnalizadorSintacticoSym.NUM, s);
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
                                s = maqHoc.TabSimb.lookup(yytext());
                                if(s == null) //Se agrega como variable no inicializada
                                    s = maqHoc.TabSimb.install(yytext(),EnumTipoSymbol.UNDEF,(float)0.0);
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


