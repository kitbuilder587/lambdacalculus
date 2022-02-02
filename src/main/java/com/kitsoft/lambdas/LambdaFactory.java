package com.kitsoft.lambdas;

public class LambdaFactory implements LambdaExpressionFactory {
    @Override
    public LambdaExpression getInstance(String exp) throws Exception {
        return new Lambda(exp);
    }
}
