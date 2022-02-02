package com.kitsoft.lambdas;

import java.util.HashMap;
import java.util.Map;

public class Macros {
    private Map<String,String> macros;

    public Macros(){
        macros = new HashMap<>();
    }

    public void addMacro(String s) throws Exception {
        s = applyMacros(s);
        if(s.charAt(0) == '#'){
            String[] a = s.substring(1).split(":=");
            String u = a[0].trim();
            String v = a[1].trim();
            if(Character.isLowerCase(u.charAt(0))){
                throw new Exception("wrong macro definition");
            }
            macros.put(u,v);

        }
    }

    public String applyMacros(String s){
        for (var entry : macros.entrySet()) {
            try {
                s = s.replaceAll(entry.getKey(), entry.getValue().replaceAll("\\\\", "\\\\\\\\"));
            } catch (Exception e) {
                continue;
            }
        }

        return s;
    }

}
