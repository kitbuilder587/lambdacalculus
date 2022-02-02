package com.kitsoft.lambdas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class LambdaExpression {
    public String exp;
    public String var;
    public String term;
    public String argument;


    abstract public String calculateExpression();
    abstract public String renameCollisions(String outName,String newName);
}
