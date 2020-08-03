package com.example.taxcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static StringBuilder show_equation;//顯示運算式
    private static ArrayList calculate_equation;//計算式
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        show_equation=new StringBuilder();
        calculate_equation=new ArrayList<>();
        Button zero=(Button)findViewById(R.id.zero);
        Button one=(Button)findViewById(R.id.one);
        Button two=(Button)findViewById(R.id.two);
        Button three=(Button)findViewById(R.id.three);
        Button four=(Button)findViewById(R.id.four);
        Button five=(Button)findViewById(R.id.five);
        Button six=(Button)findViewById(R.id.six);
        Button seven=(Button)findViewById(R.id.seven);
        Button eight=(Button)findViewById(R.id.eight);
        Button nine=(Button)findViewById(R.id.nine);
        Button cls=(Button)findViewById(R.id.cls);
        Button div=(Button)findViewById(R.id.div);
        Button mul=(Button)findViewById(R.id.mul);
        Button backspace=(Button)findViewById(R.id.Backspace);
        Button sub=(Button)findViewById(R.id.sub);
        Button add=(Button)findViewById(R.id.add);
        final Button equal=(Button)findViewById(R.id.equal);
        final Button point=(Button)findViewById(R.id.spot);
        final EditText result=(EditText)findViewById(R.id.result);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(!(show_equation.equals("0"))){
                    show_equation.append("0");
                    result.setText(show_equation);
                }
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("1");
                result.setText(show_equation);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("2");
                result.setText(show_equation);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("3");
                result.setText(show_equation);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("4");
                result.setText(show_equation);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("5");
                result.setText(show_equation);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("6");
                result.setText(show_equation);
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("7");
                result.setText(show_equation);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("8");
                result.setText(show_equation);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("9");
                result.setText(show_equation);
            }
        });
        cls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.delete(0,show_equation.length());
                calculate_equation.clear();
                result.setText("");
            }
        });
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(show_equation.equals(""))) {
                    show_equation.deleteCharAt(show_equation.length() - 1);
                    result.setText(show_equation);
                }
                else{
                    show_equation.delete(0,show_equation.length());
                    calculate_equation.clear();
                    result.setText("");
                }
            }
        });
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append(".");
                result.setText(show_equation);
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_equation.charAt(0)=='-')
                    show_equation.insert(0,"0");
                StringBuilder temp1=new StringBuilder();
                for(int i=0;i<show_equation.length();i++){
                    if(show_equation.charAt(i)>='0'&&show_equation.charAt(i)<='9'||show_equation.charAt(i)=='.'){
                        temp1.append(String.valueOf(show_equation.charAt(i)));
                    }
                    else
                    {
                        calculate_equation.add(temp1.toString());
                        temp1.delete(0,temp1.length());
                        calculate_equation.add(String.valueOf(show_equation.charAt(i)));
                    }
                }
                calculate_equation.add(temp1.toString());
                calculate_equation.add("#");
                String temp8=calculate(calculate_equation);
                result.setText(temp8);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("+");
                result.setText(show_equation);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("-");
                result.setText(show_equation);
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("×");
                result.setText(show_equation);
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_equation.append("÷");
                result.setText(show_equation);
            }
        });
    }
    protected boolean operatorPriorityCompare(char operator1,char operator2)
    {
        int o1=0;
        int o2=0;
        switch (operator1){
            case '+':{o1=0;break;}
            case '-':{o1=0;break;}
            case '×':{o1=1;break;}
            case '÷':{o1=1;break;}
        }
        switch (operator2){
            case '+':{o2=0;break;}
            case '-':{o2=0;break;}
            case '×':{o2=1;break;}
            case '÷':{o2=1;break;}
        }
        if(o1<=o2)
        {
            return false;
        }
        else
            return true;
    }
    protected String calculate(ArrayList equation){
        Double temp2;
        Double temp3;
        Double result;
        ArrayList operator=new ArrayList();
        ArrayList operand=new ArrayList();
        for(int i=0;i<equation.size();i++)
        {
            String temp4=(String) equation.get(i);
            if(temp4.equals("+")||temp4.equals("-")||temp4.equals("×")||temp4.equals("÷"))
            {
                if(operator.size()>0)
                {
                    String temp5=operator.get(operator.size()-1).toString();
                    while(!(operatorPriorityCompare(temp4.charAt(0),temp5.charAt(0)))&&operator.size()>0)
                    {
                        operator.remove(operator.size()-1);
                        temp3=(Double.parseDouble(operand.get(operand.size()-1).toString()));
                        operand.remove(operand.size()-1);
                        temp2=(Double.parseDouble(operand.get(operand.size()-1).toString()));
                        operand.remove(operand.size()-1);
                        switch (temp5.charAt(0)){
                            case '+':{result=temp2+temp3;operand.add(String.valueOf(result));break;}
                            case '-':{result=temp2-temp3;operand.add(String.valueOf(result));break;}
                            case '×':{result=temp2*temp3;operand.add(String.valueOf(result));break;}
                            case '÷':{result=temp2/temp3;operand.add(String.valueOf(result));break;}
                        }
                        if(operator.size()>0)
                        {
                            temp5=operator.get(operator.size()-1).toString();
                        }
                        else
                            break;
                    }
                    operator.add(temp4);
                }
                else
                    operator.add(temp4);
            }
            else if(temp4.equals("#"))
            {
                while(operator.size()>0)
                {
                    String temp6=(String)operator.get(operator.size()-1);
                    operator.remove(operator.size()-1);
                    temp3=(Double.parseDouble(operand.get(operand.size()-1).toString()));
                    operand.remove(operand.size()-1);
                    temp2=(Double.parseDouble(operand.get(operand.size()-1).toString()));
                    operand.remove(operand.size()-1);
                    switch (temp6.charAt(0)){
                        case '+':{result=temp2+temp3;operand.add(String.valueOf(result));break;}
                        case '-':{result=temp2-temp3;operand.add(String.valueOf(result));break;}
                        case '×':{result=temp2*temp3;operand.add(String.valueOf(result));break;}
                        case '÷':{result=temp2/temp3;operand.add(String.valueOf(result));break;}
                    }
                }
            }
            else
            {
                operand.add(temp4);
            }
        }
        return operand.get(0).toString();

    }
}