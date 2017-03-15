package recipeTool;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;


public class RecipeTool {

	public static void main(String[] args) throws ParseException {

		String y="maito";
		Ingredient.setUnit(y, Unit.L);
		Ingredient.setExpiration(y, DateFormat.getDateInstance().parse("16.3.2017"));
		Ingredient.setAmount(y, 700);
		ArrayList<String> x = new ArrayList<String>();
		//x.add("laktoosi");
		//x.add("persikka");
		Ingredient.setAllergens(y, x);
		Ingredient.print(y);
		
		y="kaakao";
		Ingredient.setUnit(y, Unit.L);
		Ingredient.setExpiration(y, DateFormat.getDateInstance().parse("14.3.2017"));
		Ingredient.setAmount(y, 300);
		x = new ArrayList<String>();
		//x.add("silakka");
		//x.add("porkkana");
		Ingredient.setAllergens(y, x);
		Ingredient.print(y);

		Recipe.setInstruction("maitokaakao", "kaada maito lasiin, lis‰‰ kaakao, sekoita haarukalla");
		HashMap<String, Double> z = new HashMap<String, Double>();
		z.put("maito", 19.2);
		z.put("kaakao", 23.8);
		Recipe.setIngredients("maitokaakao", z);
		Recipe.update("maitokaakao");
		Recipe.print("maitokaakao");

		
	}

}
