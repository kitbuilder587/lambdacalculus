package com.kitsoft.lambdas;

public class BetaReduction {
    ExpressionCalculator ec;
    private static int MAXIMUM_STEPS  = (int)10e5;
    public BetaReduction(ExpressionCalculator ec) {
        this.ec = ec;
    }

    public String calculate(String input) throws Exception {
        int stepsCount = 0;
        while (input.contains("\\") && stepsCount < MAXIMUM_STEPS){
            try {
                input = ec.calculateExpression(input);
            }catch(Exception e){
                e.printStackTrace();
                break;
            }
            stepsCount++;
        }
        if(stepsCount == MAXIMUM_STEPS) throw new Exception("Too much to calculate");
        return input;
    }
}
