package recipeTool;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

public class RecipeTool {

	public static void main(String[] args) throws ParseException {

//		
		ArrayList<String> l1 = new ArrayList<String>();
		Date date1 = DateFormat.getDateInstance().parse("10.3.2017");
		l1.add("laktoosi");
		Ingredient i1 = new Ingredient("maito", Unit.L, l1, date1);
		Storage.setIngredient(i1, 5.0);
//	
		ArrayList<String> l2 = new ArrayList<String>();
		Date date2 = DateFormat.getDateInstance().parse("5.3.2017");
		l2.add("rasva");
		Ingredient i2 = new Ingredient("kaakao", Unit.L, l2, date2);
		Storage.setIngredient(i2, 10.0);
//	
		Recipe r1 = new Recipe("maitokaakao", "ohjeohjeohje", Storage.getIngredient("maito"), 2.3);
		r1.setIngredient(Storage.getIngredient("kaakao"), 3.4);
		Book.setRecipe(r1);
		
		
		Storage.print();		
		Storage.getIngredient("maito").print();
		Storage.getIngredient("kaakao").print();
		
		Book.print();
		Book.getRecipe("maitokaakao").print();
		
		
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> i = new ArrayList<String>();
		a.add("silakka");
		i.add("maito");
		i.add("kaakao");
		System.out.println(Book.search(false, a, i));

		
	}

}
