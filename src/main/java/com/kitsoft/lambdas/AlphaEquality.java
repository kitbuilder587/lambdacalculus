package com.kitsoft.lambdas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AlphaEquality {

    private String a;
    private String b;
    private LambdaExpressionFactory factory;

    public AlphaEquality(String a, String b, LambdaExpressionFactory factory) {
        this.a = a;
        this.b = b;
        this.factory = factory;
    }

    private Set<String> getVars(String term){
        HashSet<String> varNames = new HashSet<>();
        for(int i=0;i<term.length();i++){
            if(term.charAt(i) == '\\') {
                StringBuilder name = new StringBuilder();
                for (i = i + 1; i < term.length(); i++) {
                    if (term.charAt(i) == '.') break;
                    name.append(term.charAt(i));
                }
                varNames.add(name.toString());
            }
        }
        return varNames;
    }

    public boolean areEqual() throws Exception {
        LambdaExpression expressionA = factory.getInstance(a);
        LambdaExpression expressionB = factory.getInstance(b);

        Set<String> varsA = getVars(expressionA.term);
        Set<String> varsB = getVars(expressionB.term);

        String name = "@";
        for(String var : varsA){
            expressionA.term = expressionA.renameCollisions(var,name);
            name += "@";
        }
        name = "@";
        for(String var : varsB){
            expressionB.term = expressionB.renameCollisions(var,name);
            name += "@";
        }

        expressionA.term = expressionA.term.replaceAll(expressionA.var,name);
        expressionB.term = expressionB.term.replaceAll(expressionB.var,name);

        if(expressionA.term.equals(expressionB.term)){
            return true;
        }else{
            return false;
        }
    }
}
