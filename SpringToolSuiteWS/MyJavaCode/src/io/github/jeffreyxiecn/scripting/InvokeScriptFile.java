package io.github.jeffreyxiecn.scripting;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
/*www.java2s.com*/
public class InvokeScriptFile {
  public static void main(String[] args) throws Exception {
    System.out.println("Working Directory = " + System.getProperty("user.dir"));

    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("JavaScript");
    if (!(engine instanceof Invocable)) {
      System.out.println("Invoking methods is not supported.");
      return;
    }

    Invocable inv = (Invocable) engine;
    String scriptPath = "src\\io\\github\\jeffreyxiecn\\scripting\\calculator.js";
    //String scriptPath = "calculator.js";
    //URL fileUrl = getClass().getResource(scriptPath);

    //engine.eval("load('" + scriptPath + "')");
    engine.eval(Files.newBufferedReader(Paths.get(scriptPath), StandardCharsets.UTF_8));


    Object calculator = engine.get("calculator");
    //Object calculator = engine.get("nonExist");

    int x = 3;
    int y = 4;

    for (int i = 0; i < 1200; i++){
	    Object addResult = inv.invokeMethod(calculator, "add", x, y);
	    Object subResult = inv.invokeMethod(calculator, "subtract", x, y);
	    Object mulResult = inv.invokeMethod(calculator, "multiply", x, y);
	    Object divResult = inv.invokeMethod(calculator, "divide", x, y);
	    Object sumResult = inv.invokeMethod(calculator, "sum", 50000000);

	    System.out.println(addResult);
	    System.out.println(subResult);
	    System.out.println(mulResult);
	    System.out.println(divResult);
	    System.out.println(sumResult);
	    Thread.sleep(5);
    }

    //Object triggerException = inv.invokeMethod(calculator, "nonexist", x, y);
  }
}