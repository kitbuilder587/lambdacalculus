package com.kitsoft.lambdas;
import lombok.Getter;
import java.util.ArrayList;


public class Lambda extends LambdaExpression {


    String tail = "";

    public Lambda(String exp) throws Exception {
        this.exp = exp;
        parseExpression();
    }


    @Override
    public String calculateExpression(){
        ArrayList<Integer> entries = new ArrayList<Integer>();
        for(int i=0;i<term.length();i++){
            if(term.substring(i).startsWith(var)){
                entries.add(i);
            }
        }
        StringBuilder calculatedString = new StringBuilder();
        int p = 0;
        for(int i=0;i<term.length();i++){
            if(p < entries.size() && i == entries.get(p)){
                p++;
                calculatedString.append(argument);
                i += var.length()-1;
            }else{
                calculatedString.append(term.charAt(i));
            }

        }
        return calculatedString.toString() + " " + tail;
    }

    boolean bracketWillClose(String s){
        boolean res = false;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == ')'){
                res = true;
                break;
            }
            if(s.charAt(i) == '('){
                break;
            }
        }
        return res;
    }

    public String simplify(String s){
        if(s.length() == 0) return s;
        if(s.charAt(0) == ' ') {
            return simplify(s.substring(1));
        }else if(s.charAt(0) == '(' && s.charAt(s.length()-1) == ')' && !bracketWillClose(s.substring(1,s.length()-1))) {
            return simplify(s.substring(1, s.length() - 1));
        }else {
            return s;
        }
    }

    public void parseExpression() throws Exception {
        StringBuilder var = new StringBuilder();
        StringBuilder term = new StringBuilder();
        StringBuilder argument = new StringBuilder();
        for(int i=0;i<exp.length();i++){
            if(exp.charAt(i) == '\\'){
                for(i=i+1;i<exp.length();i++){
                    if(exp.charAt(i) == '.'){
                        break;
                    }
                    var.append(exp.charAt(i));
                }
                int bracketLatency = 0;
                for(i=i+1;i<exp.length();i++){
                    if((exp.charAt(i) == ' ' || exp.charAt(i) == ')') && bracketLatency == 0){
                        break;
                    }
                    if(exp.charAt(i) == '('){
                        bracketLatency++;
                    }
                    if(exp.charAt(i) == ')'){
                        bracketLatency--;
                    }
                    term.append(exp.charAt(i));
                }

                for(i=i+1;i<exp.length();i++){
                    if(exp.charAt(i) == ' ' || exp.charAt(i) == ')'){
                        break;
                    }
                    argument.append(exp.charAt(i));
                }
                if(i+1 < exp.length())
                    tail = exp.substring(i+1);
                else
                    tail = "";

            }
        }
        if(var.length() ==0 || term.length()  == 0)
            throw new Exception("Illegal state");
        this.argument = simplify(argument.toString());
        this.var = simplify(var.toString());
        this.term = simplify(term.toString());
        this.term = renameCollisions(this.argument,argument + "@");
    }

    @Override
    public String renameCollisions(String outName,String newName){
        StringBuilder newTerm = new StringBuilder();

        for(int i=0;i<term.length();i++){
            newTerm.append(term.charAt(i));
            if(term.charAt(i) == '\\'){
                StringBuilder name = new StringBuilder();
                for(i=i+1;i<term.length();i++){
                    if(term.charAt(i) == '.') break;
                    name.append(term.charAt(i));
                }
                StringBuilder innerTerm = new StringBuilder();
                if(outName.equals(name.toString())){
                    int bracketLatency = 0;
                    for(i=i+1;i<term.length();i++){
                        if((term.charAt(i) == ' ' || term.charAt(i) == ')') && bracketLatency == 0){
                            break;
                        }
                        if(term.charAt(i) == '('){
                            bracketLatency++;
                        }
                        if(term.charAt(i) == ')'){
                            bracketLatency--;
                        }
                        innerTerm.append(term.charAt(i));
                    }
                    String newInnerTerm = innerTerm.toString().replaceAll(outName,newName);
                    newTerm.append(newName).append(".");
                    newTerm.append(newInnerTerm);
                    if(i < term.length())
                        newTerm.append(term.charAt(i));

                }else{
                    newTerm.append(name).append(".");
                }
            }

        }
        return newTerm.toString();
    }

}
