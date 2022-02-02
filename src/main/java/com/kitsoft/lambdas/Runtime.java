package com.kitsoft.lambdas;

import java.util.Scanner;

public class Runtime {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Macros macros = new Macros();
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.length()!=0 && line.charAt(0) == '#'){
                macros.addMacro(line);
            }else{
                BetaReduction betaReduction = new BetaReduction(new ExpressionCalculator() {
                    @Override
                    public String calculateExpression(String s) throws Exception {
                        return new Lambda(s).calculateExpression();
                    }
                });
                System.out.println(betaReduction.calculate(macros.applyMacros(line)));
            }
        }
    }
}
