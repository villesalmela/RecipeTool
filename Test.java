/*
TODO:
Improve:
	Coherent and uniform type settings
	Descriptive commenting
	Consistent naming of methods, variables, objects and classes
	Error handling
	Better layout & visuals for GUI

New features:
	Database integration
	Modify (ingredients & recipes) in GUI
	Add recipe: show unit, iterate creating comboboxes, implement adding to book.
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
		Date e = DateFormat.getDateInstance().parse("11.11.2000");
		TreeSet<String> a = new TreeSet<String>();
		a.add("laktoosi");
		
		Storage.setIngredient(iname, amount, unit, a, e);
		
//SET INGREDIENT 2
		iname = "kaakao";
		amount = 20.0;
		unit = Unit.KG;
		e = DateFormat.getDateInstance().parse("11.11.2011");
		a = new TreeSet<String>();
		a.add("suklaa");

		
		Storage.setIngredient(iname, amount, unit, a, e);
		
//SET RECIPE 1
		String rname = "bmaitokaakao";
		String instruction = "maitokaakao-ohje";
		LinkedHashMap<String, Double> ingredient = new LinkedHashMap<String, Double>();
		ingredient.put("kaakao", 5.1);
		ingredient.put("maito", 6.1);
		
		Book.setRecipe(rname, instruction, ingredient);
		
//SET INGREDIENT 3
		iname = "jauho";
		amount = 30.0;
		unit = Unit.KG;
		e = DateFormat.getDateInstance().parse("11.11.2333");
		a = new TreeSet<String>();
		a.add("gluteeni");

		
		Storage.setIngredient(iname, amount, unit, a, e);
		
//SET INGREDIENT 4
		iname = "kananmuna";
		amount = 40.0;
		unit = Unit.PCS;
		e = DateFormat.getDateInstance().parse("11.11.2011");
		a = new TreeSet<String>();
		a.add("keltuainen");

		
		Storage.setIngredient(iname, amount, unit, a, e);
		
//SET RECIPE 2
		rname = "ataikina";
		instruction = "taikina-ohje";
		ingredient = new LinkedHashMap<String, Double>();
		ingredient.put("jauho", 700.1);
		ingredient.put("kananmuna", 8.1);
		
		Book.setRecipe(rname, instruction, ingredient);
		
		
		RecipeTool.main(null);
						
	}

}
