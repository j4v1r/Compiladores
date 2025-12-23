/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CompiladoresHoc5;

/**
 *
 * @author Rogelio Colunga R
 */
public class InstrucPrograma {
    
    public EnumTipoInstr TipInstr; //INSTRUC, SYMBOLM BLTIN, JUMP
    public EnumInstrMaq Instruc;   /*EVAL, ADD, SUB, ..., ASSING, BLTIN, 
                                   VARPUSH, PRINT, STOP*/
    public EnumBLTIN Func_BLTIN;   /*SIN, COS, TAN, ..., INTEGER, ABS*/
    public SymbolHoc symbolHoc;    /*Nodo Symbol*/
    
    public int jump;               /*Posición para el PC a la que se debe saltar*/
}
