/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CompiladoresHoc3;

/**
 *
 * @author Jesus
 */
//Clase equivalente a "hoc.h"
public class SymbolHoc {
    
    public String name;
    public EnumTipoSymbol TipoSymbol;
    public float val;
    public EnumBLTIN FuncPredef;
    
    public SymbolHoc()
    {
        name="";
        val=0;
    }
    
    //VAR, UNDEF, CONST_PREDEF
    public SymbolHoc(String nombre , EnumTipoSymbol TipSimbol  , float valor)
    {
        name= nombre;
        TipoSymbol = TipSimbol;
        val = valor;
    }
    
    //BLTIN
    public SymbolHoc(String nombre, EnumTipoSymbol TipSimbol,EnumBLTIN func)
    {
        name = nombre;
        TipoSymbol = TipSimbol;
        FuncPredef = func;
    }
    
    public void SetSymbol(String nombre, EnumTipoSymbol TipSimbol,float valor)
    {
        name = nombre;
        TipoSymbol = TipSimbol;
        val = valor;
    }
    
    public void SetSymbol(String nombre, EnumTipoSymbol TipSimbol,EnumBLTIN func)
    {
        name = nombre;
        TipoSymbol= TipSimbol;
        FuncPredef = func;
    }
}
