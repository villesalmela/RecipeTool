/*
TODO:
Improve coding practices:
	Coherent and uniform type settings
	Descriptive commenting
	Consistent naming of methods, variables, objects and classes

New features:
	Database implementation
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
		a.add("ddd");
		a.add("aaa");
		
		Storage.setIngredient(iname, amount, unit, a, e);
		
//SET INGREDIENT 2
		iname = "kaakao";
		amount = 20.0;
		unit = Unit.KG;
		e = DateFormat.getDateInstance().parse("2.2.2017");
		a = new TreeSet<String>();
		a.add("ccc");
		a.add("bbb");
		
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
		a.add("eee");
		a.add("fff");
		
		Storage.setIngredient(iname, amount, unit, a, e);
		
//SET INGREDIENT 4
		iname = "muna";
		amount = 40.0;
		unit = Unit.PCS;
		e = DateFormat.getDateInstance().parse("4.4.2017");
		a = new TreeSet<String>();
		a.add("ggg");
		a.add("hhh");
		
		Storage.setIngredient(iname, amount, unit, a, e);
		
//SET RECIPE 2
		rname = "taikina";
		instruction = "taikina-ohje";
		ingredient = new LinkedHashMap<String, Double>();
		ingredient.put("jauho", 7.1);
		ingredient.put("muna", 8.1);
		
		Book.setRecipe(rname, instruction, ingredient);

		RecipeTool.main(null);
						
	}

}
