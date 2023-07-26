package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    TextView workingsTV;
    TextView resultsTV;

    String workings="";
    String formula="";
    String tempFormula="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews(){
        workingsTV = (TextView) findViewById(R.id.workingTextView);
        resultsTV = (TextView) findViewById(R.id.resultsTextView);
    }

    private void setWorkings(String givenValue){
        workings = workings + givenValue;
        workingsTV.setText(workings);
    }

    public void equalsOnClick(View view){
        Double result = null;
        ScriptEngine engine = new ScriptEngine().getEngineByName("rhino");
        checkPowerOf();

        try{
            result = (double)engine.eval(formula);
        }
        catch(ScriptException e){
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT.show());
        }

        if(result!=null){
            resultsTV.setText(String.valueOf(result.doubleValue()));
        }
    }

    private void checkPowerOf(){
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i=0; i<workings.lenght(); i++){
            if(workings.charAt(i)=='^'){
                indexOfPowers.add(i);
            }
        }
        formula = workings;
        tempFormula = workings;
        for (Integer index : indexOfPower){
            changeFormula(index);
        }
        formula = tempFormula;
    }
    private void changeFormula(Integer index){
        String numberLeft = "";
        String numberRight = "";

        for(int i=index+1; i<workings.length(); i++){
            if(isNumeric(workings.charAt(i))){
                numberRight = numberRight + workings.charAt(i);
            }
            else{
                break;
            }
        }
        for(int i=index-1; i>=0; i--){
            if(isNumeric(workings.charAt(i))){
                numberLeft =  workings.charAt(i) + numberLeft;
            }
            else{
                break;
            }
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original, changed);
    }

    private boolean isNumeric(char ch){
        if((ch<='9' && ch>='0') || ch=="."){
            return true;
        }
        return false;
    }

    public void clearOnClick(View view){
        workingsTV.setText("");
        workings = "";
        resultsTV.setText("");
        leftBracket = true;
    }

    boolean leftBracket = true;

    public void bracketOnClick(View view){
        if(leftBracket == true){
            setWorkings("(");
            leftBracket=false;
        }
        else{
            setWorkings(")");
            leftBracket=true;
        }
    }

    public void powerOfOnClick(View view){
        setWorkings("^");
    }
    public void divisionOfOnClick(View view){
        setWorkings("/");
    }
    public void zeroOfOnClick(View view){
        setWorkings("0");
    }
    public void oneOfOnClick(View view){
        setWorkings("1");
    }
    public void twoOfOnClick(View view){
        setWorkings("2");
    }
    public void threeOfOnClick(View view){
        setWorkings("3");
    }
    public void fourOfOnClick(View view){
        setWorkings("4");
    }
    public void fiveOfOnClick(View view){
        setWorkings("5");
    }
    public void sixOfOnClick(View view){
        setWorkings("6");
    }
    public void sevenOfOnClick(View view){
        setWorkings("7");
    }
    public void eightOfOnClick(View view){
        setWorkings("8");
    }
    public void nineOfOnClick(View view){
        setWorkings("9");
    }
    public void addOfOnClick(View view){
        setWorkings("+");
    }
    public void subtractOfOnClick(View view){
        setWorkings("-");
    }
    public void multiplyOfOnClick(View view){
        setWorkings("*");
    }

}