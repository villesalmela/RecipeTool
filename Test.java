/*
TODO:
Improve:
	Coherent and uniform type settings
	Descriptive commenting
	Consistent naming of methods, variables, objects and classes
	Error handling
	Better layout & visuals for GUI

New features:
	Recipe enough GUI
	Database integration
	Add/modify (ingredients & recipes) in GUI
	Implement recipe filtering	
*/
package recipeTool;

import java.text.DateFormat;
import java.util.*;

public class Test {

	public static void main(String[] args) throws Exception {
//SET INGREDIENT 1
		String iname = "maito";
		double amount = 10.0;
		Unit unit = Unit.L;
		Date e = DateFormat.getDateInstance().parse("1.1.2017");
		TreeSet<String> a = new TreeSet<String>();
		a.add("laktoosi");
		
		Storage.setIngredient(iname, amount, unit, a, e);
		
//SET INGREDIENT 2
		iname = "kaakao";
		amount = 20.0;
		unit = Unit.KG;
		e = DateFormat.getDateInstance().parse("2.2.2017");
		a = new TreeSet<String>();
		a.add("suklaa");

		
		Storage.setIngredient(iname, amount, unit, a, e);
		
//SET RECIPE 1
		String rname = "maitokaakao";
		String instruction = "maitokaakao-ohje";
		LinkedHashMap<String, Double> ingredient = new LinkedHashMap<String, Double>();
		ingredient.put("kaakao", 5.1);
		ingredient.put("maito", 6.1);
		
		Book.setRecipe(rname, instruction, ingredient);
		
//SET INGREDIENT 3
		iname = "jauho";
		amount = 30.0;
		unit = Unit.KG;
		e = DateFormat.getDateInstance().parse("3.3.2017");
		a = new TreeSet<String>();
		a.add("gluteeni");

		
		Storage.setIngredient(iname, amount, unit, a, e);
		
//SET INGREDIENT 4
		iname = "kananmuna";
		amount = 40.0;
		unit = Unit.PCS;
		e = DateFormat.getDateInstance().parse("4.4.2017");
		a = new TreeSet<String>();
		a.add("keltuainen");

		
		Storage.setIngredient(iname, amount, unit, a, e);
		
//SET RECIPE 2
		rname = "taikina";
		instruction = "taikina-ohje";
		ingredient = new LinkedHashMap<String, Double>();
		ingredient.put("jauho", 700.1);
		ingredient.put("kananmuna", 8.1);
		
		Book.setRecipe(rname, instruction, ingredient);

		RecipeTool.main(null);
						
	}

}
