/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CompiladoresHoc5;

import java.util.Stack;

/**
 *
 * @author Rogelio Colunga R
 */
public class MaquinaHoc4 {
    
    TablaSimbolos TabSimb;
    InstrucPrograma Prog[];
    int progp = 0;
    int pc;
    Stack<Datum> stack;
    
    public MaquinaHoc4(){
        TabSimb = new TablaSimbolos();
                
        Prog = new InstrucPrograma[2048];
        progp = 0;
        pc=0;
        
        stack = new Stack();
        stack.clear();
    }
    
    public void initCode(){
        progp=0;
        stack.clear();
    }
    
    public Integer code(InstrucPrograma inst){
        Integer oprogp = progp;
        Prog[progp++] = inst;
        return oprogp;
    }
    
    public Integer code2(InstrucPrograma inst1, InstrucPrograma inst2){
        Integer oprogp = progp;
        Prog[progp++] = inst1;
        Prog[progp++] = inst2;
        return oprogp;
    }
    
    public Integer code3(InstrucPrograma inst1, InstrucPrograma inst2, InstrucPrograma inst3){
        Integer oprogp = progp;
        Prog[progp++] = inst1;
        Prog[progp++] = inst2;
        Prog[progp++] = inst3;
        return oprogp;
    }
}
