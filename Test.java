/*
TODO:
Improve:
	Coherent and uniform type settings
	Descriptive commenting
	Consistent naming of methods, variables, objects and classes
	Error handling
	Better layout & visuals for GUI

New features:
	Database integration (recipe part remaining)
	Modify (ingredients & recipes) in GUI
	Do you want to overwrite?
	Save/Load in GUI
	Database Settings/Load TestValues in GUI
	
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
		
		Storage.setIngredient(iname, amount, unit, a.toArray(new String[0]), e);
		
//SET INGREDIENT 2
		iname = "kaakao";
		amount = 20.0;
		unit = Unit.KG;
		e = DateFormat.getDateInstance().parse("11.11.2011");
		a = new TreeSet<String>();
		a.add("suklaa");

		
		Storage.setIngredient(iname, amount, unit, a.toArray(new String[0]), e);
		
//SET INGREDIENT 5
		iname = "aurajuusto";
		amount = 21.0;
		unit = Unit.KG;
		e = DateFormat.getDateInstance().parse("1.12.2013");
		a = new TreeSet<String>();
		a.add("jokivesi");

		
		Storage.setIngredient(iname, amount, unit, a.toArray(new String[0]), e);
		
//SET INGREDIENT 6
		iname = "mozzarella";
		amount = 13.0;
		unit = Unit.KG;
		e = DateFormat.getDateInstance().parse("11.11.2099");
		a = new TreeSet<String>();
		a.add("käsikarva");

		
		Storage.setIngredient(iname, amount, unit, a.toArray(new String[0]), e);
				
//SET INGREDIENT 7
		iname = "sulatejuusto";
		amount = 200.0;
		unit = Unit.KG;
		e = DateFormat.getDateInstance().parse("3.4.2050");
		a = new TreeSet<String>();
		a.add("juustokumina");

				
		Storage.setIngredient(iname, amount, unit, a.toArray(new String[0]), e);
		
//SET RECIPE 1
		String rname = "maitokaakao";
		String instruction = "maitokaakao-ohje";
		LinkedHashMap<String, Double> ingredient = new LinkedHashMap<String, Double>();
		ingredient.put("kaakao", 5.1);
		ingredient.put("maito", 6.1);
		
		Book.setRecipe(rname, instruction, ingredient);
		
//SET INGREDIENT 3
		iname = "jauho";
		amount = 300.0;
		unit = Unit.KG;
		e = DateFormat.getDateInstance().parse("11.11.2333");
		a = new TreeSet<String>();
		a.add("gluteeni");

		
		Storage.setIngredient(iname, amount, unit, a.toArray(new String[0]), e);
		
//SET INGREDIENT 4
		iname = "kananmuna";
		amount = 400.0;
		unit = Unit.PCS;
		e = DateFormat.getDateInstance().parse("11.11.2011");
		a = new TreeSet<String>();
		a.add("keltuainenB");

		
		Storage.setIngredient(iname, amount, unit, a.toArray(new String[0]), e);
		
//SET RECIPE 2
		rname = "taikina";
		instruction = "taikina-ohje";
		ingredient = new LinkedHashMap<String, Double>();
		ingredient.put("jauho", 700.1);
		ingredient.put("kananmuna", 8.1);
		
		Book.setRecipe(rname, instruction, ingredient);
		
//SET RECIPE 2
		rname = "pizza";
		instruction = "pizza-ohje";
		ingredient = new LinkedHashMap<String, Double>();
		ingredient.put("mozzarella", 200.1);
		ingredient.put("aurajuusto", 4.0);
		ingredient.put("jauho", 3.5);
		
		Book.setRecipe(rname, instruction, ingredient);
		
//SET RECIPE 2
		rname = "juustokeitto";
		instruction = "keitto-ohje";
		ingredient = new LinkedHashMap<String, Double>();
		ingredient.put("sulatejuusto", 15.0);
		ingredient.put("mozzarella", 250.0);
		ingredient.put("aurajuusto", 18.0);
		
		Book.setRecipe(rname, instruction, ingredient);
		
		
		RecipeTool.main(null);
						
	}

}
