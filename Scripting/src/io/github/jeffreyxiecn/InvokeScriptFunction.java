package io.github.jeffreyxiecn;

import javax.script.*;

public class InvokeScriptFunction {
public static void main(String[] args) throws Exception {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("JavaScript");

    // JavaScript code in a String
    String script1 = (String)"function hello(name) {print ('Hello, ' + name);}";
    String script2 = (String)"function getValue(a, b) { if (a===\"Number\") "
    		                                            + "return 1; "
    		                                         + "else return b;"
    		                                         + "}";
    // evaluate script
    engine.eval(script1);
    engine.eval(script2);

    Invocable inv = (Invocable) engine;

    inv.invokeFunction("hello", "Scripting!!" );  //This one works.
    double a = (Double)inv.invokeFunction("getValue", "Number" );  //This one works.
    System.out.println("\na = " + a);

    String b = (String)inv.invokeFunction("getValue", "String", "12345" );  //This one works.
    System.out.println("\nb = " + b);
 }
}