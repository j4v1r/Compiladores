/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CompiladoresHoc5;

import java.util.Stack;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

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
    JTextArea AreaResult;
    JTable jTablePila;

    public MaquinaHoc4()
    {
        TabSimb = new TablaSimbolos();

        Prog = new InstrucPrograma[2048];
        progp = 0;
        pc = 0;

        stack = new Stack();
        stack.clear();
    }

    public void initcode()
    {
        progp = 0;
        stack.clear();
    }

    public Integer code(InstrucPrograma inst)
    {
        Integer oprogp = progp;
        Prog[progp++]=inst;
        return oprogp;
    }

    public Integer code2(InstrucPrograma inst1,InstrucPrograma inst2)
    {
        Integer oprogp = progp;
        Prog[progp++]=inst1;
        Prog[progp++]=inst2;
        return oprogp;
    }
    public Integer code3(InstrucPrograma inst1,InstrucPrograma inst2,InstrucPrograma inst3)
    {
        Integer oprogp = progp;
        Prog[progp++]=inst1;
        Prog[progp++]=inst2;
        Prog[progp++]=inst3;
        return oprogp;
    }

    public void execute(int ind)
    {
        InstrucPrograma instruc;
        Datum op1, op2;
        String CadResult = new String();

        Object os[]= new Object[5];
        String TipDatum = new String();
        String Val = new String();
        String NombSymbol = new String();
        String TypeSymbol = new String();
        String ValSymbol = new String();

        DefaultTableModel modeloTablaPila = (DefaultTableModel) jTablePila.getModel();

        pc=ind;

        while(Prog[pc].Instruc != EnumInstrMaq.STOP){
            AreaResult.append("PC = "+ Integer.toString(pc)+"\n");

            TipDatum="";
            Val="";
            NombSymbol="";
            TypeSymbol="";
            ValSymbol="";

            instruc = Prog[pc++];
            switch(instruc.Instruc){
                case ADD:
                    add();
                    break;
                case ASSIGN:
                    assign();
                    break;
                case BLTIN:
                    bltin();
                    break;
                case CONSTPUSH:
                    constpush();
                    break;
                case DIV:
                    div();
                    break;
                case EVAL:
                    eval();
                    break;
                case MUL:
                    mul();
                    break;
                case NEGATE:
                    negate();
                    break;
                case GT:
                    gt();
                    break;
                case GE:
                    ge();
                    break;
                case LT:
                    lt();
                    break;
                case LE:
                    le();
                    break;
                case AND:
                    and();
                    break;
                case OR:
                    or();
                    break;
                case NOT:
                    not();
                    break;
                case POWER:
                    power();
                    break;
                case PRINT:
                    print();
                    break;
                case STOP: // creo que apartir de estos se van a quitar y no se ocupan
                    stop();
                    break;
                case SUB:
                    sub();
                    break;
                case WHILECODE:
                    whilecode();
                    break;
                case IFCODE:
                    ifcode();
                    break;
            }
        }
    }

    public void add()
    {
        Datum op1, op2;
        op2 = stack.pop();
        op1 = stack.pop();
        op1.val += op2.val;
        stack.push(op1);

    }

    public void assign()
    {
        Datum op1, op2;
        op2 = stack.pop();
        op1 = stack.pop();
        op2.symb.val = op1.val;
        op2.symb.TipoSymbol = EnumTipoSymbol.VAR;
        stack.push(op1);
    }

    public void bltin()
    {
        Datum op1;
        InstrucPrograma instruc;
        instruc = Prog[pc++];

        switch(instruc.Func_BLTIN) {
            case ABS:
                op1= stack.pop();
                op1.val = Math.abs(op1.val);
                stack.push(op1);
                break;
            case ATAN:
                op1 = stack.pop();
                op1.val = (float) Math.atan((double) op1.val);
                stack.push(op1);
                break;
            case COS:
                op1 = stack.pop();
                op1.val = (float) Math.cos((double) op1.val);
                stack.push(op1);
                break;
            case SIN:
                op1 = stack.pop();
                op1.val = (float) Math.sin((double) op1.val);
                stack.push(op1);
                break;
            case EXP:
                op1 = stack.pop();
                op1.val =(float) Math.exp((double) op1.val);
                stack.push(op1);
                break;
            case INTEGER:
                op1 = stack.pop();
                op1.val = (float) Math.floor((double) op1.val);
                stack.push(op1);
                break;
            case LOG10:
                op1 = stack.pop();
                op1.val = (float) Math.log10((double) op1.val);
                stack.push(op1);
                break;
            case LOG:
                op1 = stack.pop();
                op1.val = (float) Math.log((double) op1.val);
                stack.push(op1);
                break;
            case SQRT:
                op1 = stack.pop();
                op1.val = (float) Math.sqrt((double) op1.val);
                stack.push(op1);
                break;
        }
    }

    public void constpush()
    {
        Datum op1;
        op1 = new Datum();
        op1.val = Prog[pc++].symbolHoc.val;
        stack.push(op1);
    }

    public void div()
    {
        Datum op1,op2;
        op2 =stack.pop();
        op1 =stack.pop();
        op1.val /= op2.val;
        stack.push(op1);
    }
    public void eval()
    {
        Datum op1,op2;
        op2 = new Datum();
        op1 =stack.pop();
        op1.val = op2.symb.val;
        stack.push(op1);
    }

    public void mul()
    {
        Datum op1,op2;
        op2 =stack.pop();
        op1 =stack.pop();
        op1.val *= op2.val;
        stack.push(op1);
    }

    public void negate()
    {
        Datum op1;
        op1 =stack.pop();
        op1.val = -op1.val;
        stack.push(op1);
    }

    public void power()
    {
        Datum op1,op2;
        op2 =stack.pop();
        op1 =stack.pop();
        op1.val = (float) Math.pow((double) op1.val,(double) op2.val);
        stack.push(op1);
    }

    public void print()
    {
        Datum op1;
        String CadResult = new String();
        op1 =stack.pop();

        CadResult = Float.toString(op1.val)+ "\n";
        AreaResult.append(CadResult);
    }

    public void sub()
    {
        Datum op1,op2;
        op2 =stack.pop();
        op1 =stack.pop();
        op1.val -= op2.val;
        stack.push(op1);
    }

    public void varpush()
    {
        Datum op1;
        op1 =stack.pop();
        op1.symb = Prog[pc++].symbolHoc;
        stack.push(op1);
    }

    public void gt()
    {
        Datum op1,op2;
        op2 =stack.pop();
        op1 =stack.pop();
        op1.val = (op1.val > op2.val)?1:0;
        stack.push(op1);
    }

    public void ge()
    {
        Datum op1, op2;
        op2 = stack.pop();
        op1 = stack.pop();
        op1.val = (op1.val >= op2.val)?1:0;
        stack.push(op1);
    }

    public void lt()
    {
        Datum op1, op2;
        op2 = stack.pop();
        op1 = stack.pop();
        op1.val = (op1.val < op2.val)?1:0;
        stack.push(op1);
    }

    public void le()
    {
        Datum op1, op2;
        op2 = stack.pop();
        op1 = stack.pop();
        op1.val = (op1.val <= op2.val)?1:0;
        stack.push(op1);
    }

    public void eq()
    {
        Datum op1, op2;
        op2 = stack.pop();
        op1 = stack.pop();
        op1.val = (op1.val == op2.val)?1:0;
        stack.push(op1);
    }

    public void ne()
    {
        Datum op1, op2;
        op2 = stack.pop();
        op1 = stack.pop();
        op1.val = (op1.val != op2.val)?1:0;
        stack.push(op1);
    }

    public void and()
    {
        Datum op1, op2;
        op2 = stack.pop();
        op1 = stack.pop();
        op1.val =op1.val*op2.val;
        stack.push(op1);
    }

    public void or()
    {
        Datum op1, op2;
        op2 = stack.pop();
        op1 = stack.pop();
        op1.val = (op1.val==1 || op2.val==1)?1:0;
        stack.push(op1);
    }

    public void not()
    {
        Datum op1;
        op1 = stack.pop();
        op1.val = (op1.val == 1)?1:0;
        stack.push(op1);
    }


    //Estos tres estan agregados para se tienen que quitar para poner esto en el java cup en jumps
    public void whilecode(){}
    public void ifcode(){}
    public void stop(){}
}
