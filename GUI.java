package recipeTool;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.text.DateFormat;


public class GUI extends JFrame{

//CREATE (internally) EXPOSED COMPONENTS
	private JList<String> list1_ingredients = new JList<String>();
	private JTextPane text1_amount = new JTextPane();
	private JTextPane text1_unit = new JTextPane();
	private JTextPane text1_allergens = new JTextPane();
	private JTextPane text1_expiration = new JTextPane();
	private JButton btn1_delete = new JButton("Delete ingredient");
	
	private JList<String> list2_recipes = new JList<String>();
	private JTextPane text2_ingredients = new JTextPane();
	private JTextPane text2_instruction = new JTextPane();
	private JTextPane text2_allergens = new JTextPane();
	private JTextPane text2_expiration = new JTextPane();
	private JLabel lbl2_enoughValue = new JLabel("");
	private JButton btn2_delete = new JButton("Delete recipe");
	
	private JButton btn3_filter = new JButton("Filter");
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3121708348469315637L;

	

	public GUI(){
//CREATE FRAME
			super("recipeTool");

			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setBounds(0,0,1000,1000);
			setLocationRelativeTo(null);
			getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
			
//CREATE PANELS
			JPanel panel_1 = new JPanel();
			JPanel panel_2 = new JPanel();
			JPanel panel_3 = new JPanel();
		
//LAYOUT PANELS
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{0, 68, 47, 84, 75, 93, 0, 0};
			gbl_panel_1.rowHeights = new int[]{38, 297, 0};
			gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel_1.setLayout(gbl_panel_1);
			
			GridBagLayout gbl_panel_2 = new GridBagLayout();
			gbl_panel_2.columnWidths = new int[]{1, 146, 157, 160, 109, 118, 0, 0};
			gbl_panel_2.rowHeights = new int[]{25, 190, 0};
			gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel_2.setLayout(gbl_panel_2);
			
			GridBagLayout gbl_panel_3 = new GridBagLayout();
			gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
			gbl_panel_3.rowHeights = new int[]{0, 0, 0};
			gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel_3.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel_3.setLayout(gbl_panel_3);
			
//CREATE TABS
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(tabbedPane);
			tabbedPane.addTab("Ingredients", null, panel_1, null);
			tabbedPane.addTab("Recipes", null, panel_2, null);
			tabbedPane.addTab("Filter", null, panel_3, null);
			
//CREATE CHECKBOX
			JCheckBox chckbxNoShopping = new JCheckBox("No Shopping");
			
//LAYOUT CHECKBOX
			GridBagConstraints gbc_chckbxNoShopping = new GridBagConstraints();
			gbc_chckbxNoShopping.insets = new Insets(0, 0, 0, 5);
			gbc_chckbxNoShopping.gridx = 1;
			gbc_chckbxNoShopping.gridy = 1;
			panel_3.add(chckbxNoShopping, gbc_chckbxNoShopping);
			
			
//CREATE COMBOBOXES
			JComboBox<String> cbox3_mustHave = new JComboBox<String>();
			JComboBox<String> cbox3_avoidAllergens = new JComboBox<String>();

//LAYOUT COMBOBOXES
			GridBagConstraints gbc_cbox3_mustHave = new GridBagConstraints();
			gbc_cbox3_mustHave.insets = new Insets(0, 0, 0, 5);
			gbc_cbox3_mustHave.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbox3_mustHave.gridx = 0;
			gbc_cbox3_mustHave.gridy = 1;
			panel_3.add(cbox3_mustHave, gbc_cbox3_mustHave);
			
			GridBagConstraints gbc_cbox3_avoidAllergens = new GridBagConstraints();
			gbc_cbox3_avoidAllergens.insets = new Insets(0, 0, 0, 5);
			gbc_cbox3_avoidAllergens.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbox3_avoidAllergens.gridx = 2;
			gbc_cbox3_avoidAllergens.gridy = 1;
			panel_3.add(cbox3_avoidAllergens, gbc_cbox3_avoidAllergens);
			
//CREATE RADIO BUTTONS
			JRadioButton rdbtn3_az = new JRadioButton("A-Z");
			JRadioButton rdbtn3_expiration = new JRadioButton("Expiration date");
	
//LAYOUT RADIO BUTTONS
			GridBagConstraints gbc_rdbtn3_az = new GridBagConstraints();
			gbc_rdbtn3_az.insets = new Insets(0, 0, 0, 5);
			gbc_rdbtn3_az.gridx = 3;
			gbc_rdbtn3_az.gridy = 1;
			panel_3.add(rdbtn3_az, gbc_rdbtn3_az);

			GridBagConstraints gbc_rdbtn3_expiration = new GridBagConstraints();
			gbc_rdbtn3_expiration.insets = new Insets(0, 0, 0, 5);
			gbc_rdbtn3_expiration.gridx = 4;
			gbc_rdbtn3_expiration.gridy = 1;
			panel_3.add(rdbtn3_expiration, gbc_rdbtn3_expiration);
			
//CREATE LABELS
			JLabel lbl1_amount = new JLabel("Amount");
			JLabel lbl1_unit = new JLabel("Unit");
			JLabel lbl1_allergens = new JLabel("Allergens");
			JLabel lbl1_expiration = new JLabel("Expiration");
			
			JLabel lbl2_ingredients = new JLabel("Ingredients");
			JLabel lbl2_insturctions = new JLabel("Insturctions");
			JLabel lbl2_allergens = new JLabel("Allergens");
			JLabel lbl2_expiration = new JLabel("Expiration");
			JLabel lbl2_enough = new JLabel("Enough");
			
			JLabel lbl3_mustHave = new JLabel("Must have ingredients");
			JLabel lbl3_noShopping = new JLabel("No shopping");
			JLabel lbl3_avoidAllergens = new JLabel("Avoid allergens");
			JLabel lbl3_sortBy = new JLabel("Sort by");
			
//SETUP LABELS
			lbl1_amount.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl1_unit.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl1_allergens.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl1_expiration.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			lbl2_ingredients.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl2_insturctions.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl2_allergens.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl2_expiration.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl2_enough.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl2_enoughValue.setFont(new Font("Tahoma", Font.PLAIN, 13));
			
			lbl3_mustHave.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl3_noShopping.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl3_avoidAllergens.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl3_sortBy.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
//LAYOUT LABELS
			GridBagConstraints gbc_lbl1_amount = new GridBagConstraints();
			gbc_lbl1_amount.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1_amount.gridx = 1;
			gbc_lbl1_amount.gridy = 0;
			panel_1.add(lbl1_amount, gbc_lbl1_amount);
			
			GridBagConstraints gbc_lbl1_unit = new GridBagConstraints();
			gbc_lbl1_unit.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1_unit.gridx = 2;
			gbc_lbl1_unit.gridy = 0;
			panel_1.add(lbl1_unit, gbc_lbl1_unit);
			
			GridBagConstraints gbc_lbl1_allergens = new GridBagConstraints();
			gbc_lbl1_allergens.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1_allergens.gridx = 3;
			gbc_lbl1_allergens.gridy = 0;
			panel_1.add(lbl1_allergens, gbc_lbl1_allergens);
				
			GridBagConstraints gbc_lbl1_expiration = new GridBagConstraints();
			gbc_lbl1_expiration.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1_expiration.gridx = 4;
			gbc_lbl1_expiration.gridy = 0;
			panel_1.add(lbl1_expiration, gbc_lbl1_expiration);
			
			GridBagConstraints gbc_lbl2_ingredients = new GridBagConstraints();
			gbc_lbl2_ingredients.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_ingredients.gridx = 1;
			gbc_lbl2_ingredients.gridy = 0;
			panel_2.add(lbl2_ingredients, gbc_lbl2_ingredients);
			
			GridBagConstraints gbc_lbl2_insturctions = new GridBagConstraints();
			gbc_lbl2_insturctions.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_insturctions.gridx = 2;
			gbc_lbl2_insturctions.gridy = 0;
			panel_2.add(lbl2_insturctions, gbc_lbl2_insturctions);
			
			GridBagConstraints gbc_lbl2_allergens = new GridBagConstraints();
			gbc_lbl2_allergens.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_allergens.gridx = 3;
			gbc_lbl2_allergens.gridy = 0;
			panel_2.add(lbl2_allergens, gbc_lbl2_allergens);

			GridBagConstraints gbc_lbl2_expiration = new GridBagConstraints();
			gbc_lbl2_expiration.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_expiration.gridx = 4;
			gbc_lbl2_expiration.gridy = 0;
			panel_2.add(lbl2_expiration, gbc_lbl2_expiration);
			
			GridBagConstraints gbc_lbl2_enough = new GridBagConstraints();
			gbc_lbl2_enough.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_enough.gridx = 5;
			gbc_lbl2_enough.gridy = 0;
			panel_2.add(lbl2_enough, gbc_lbl2_enough);
			
			GridBagConstraints gbc_lbl2_enoughValue = new GridBagConstraints();
			gbc_lbl2_enoughValue.anchor = GridBagConstraints.NORTH;
			gbc_lbl2_enoughValue.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_enoughValue.gridx = 5;
			gbc_lbl2_enoughValue.gridy = 1;
			panel_2.add(lbl2_enoughValue, gbc_lbl2_enoughValue);
			
			GridBagConstraints gbc_lbl3_mustHave = new GridBagConstraints();
			gbc_lbl3_mustHave.fill = GridBagConstraints.HORIZONTAL;
			gbc_lbl3_mustHave.insets = new Insets(0, 0, 5, 5);
			gbc_lbl3_mustHave.gridx = 0;
			gbc_lbl3_mustHave.gridy = 0;
			panel_3.add(lbl3_mustHave, gbc_lbl3_mustHave);
			
			GridBagConstraints gbc_lbl3_noShopping = new GridBagConstraints();
			gbc_lbl3_noShopping.insets = new Insets(0, 0, 5, 5);
			gbc_lbl3_noShopping.gridx = 1;
			gbc_lbl3_noShopping.gridy = 0;
			panel_3.add(lbl3_noShopping, gbc_lbl3_noShopping);
			
			GridBagConstraints gbc_lbl3_avoidAllergens = new GridBagConstraints();
			gbc_lbl3_avoidAllergens.insets = new Insets(0, 0, 5, 5);
			gbc_lbl3_avoidAllergens.gridx = 2;
			gbc_lbl3_avoidAllergens.gridy = 0;
			panel_3.add(lbl3_avoidAllergens, gbc_lbl3_avoidAllergens);
		
			GridBagConstraints gbc_lbl3_sortBy = new GridBagConstraints();
			gbc_lbl3_sortBy.gridwidth = 2;
			gbc_lbl3_sortBy.insets = new Insets(0, 0, 5, 5);
			gbc_lbl3_sortBy.gridx = 3;
			gbc_lbl3_sortBy.gridy = 0;
			panel_3.add(lbl3_sortBy, gbc_lbl3_sortBy);
			
//SETUP LISTS
			list1_ingredients.setListData(Storage.listIngredients());
			list1_ingredients.addListSelectionListener(new ListHandler());
			
			list2_recipes.setListData(Book.listRecipes());
			list2_recipes.addListSelectionListener(new ListHandler());
			
//LAYOUT LISTS
			GridBagConstraints gbc_list1_ingredients = new GridBagConstraints();
			gbc_list1_ingredients.anchor = GridBagConstraints.NORTH;
			gbc_list1_ingredients.fill = GridBagConstraints.HORIZONTAL;
			gbc_list1_ingredients.insets = new Insets(0, 0, 0, 5);
			gbc_list1_ingredients.gridheight = 2;
			gbc_list1_ingredients.gridx = 5;
			gbc_list1_ingredients.gridy = 1;
			panel_1.add(list1_ingredients, gbc_list1_ingredients);
			
			GridBagConstraints gbc_list2_recipes = new GridBagConstraints();
			gbc_list2_recipes.anchor = GridBagConstraints.NORTH;
			gbc_list2_recipes.gridheight = 2;
			gbc_list2_recipes.fill = GridBagConstraints.HORIZONTAL;
			gbc_list2_recipes.insets = new Insets(0, 0, 0, 5);
			gbc_list2_recipes.gridx = 6;
			gbc_list2_recipes.gridy = 1;
			panel_2.add(list2_recipes, gbc_list2_recipes);
			
//SETUP BUTTONS
			btn1_delete.addActionListener(new ButtonHandler());
			btn2_delete.addActionListener(new ButtonHandler());
			

//LAYOUT BUTTONS
			GridBagConstraints gbc_btn1_delete = new GridBagConstraints();
			gbc_btn1_delete.anchor = GridBagConstraints.SOUTHWEST;
			gbc_btn1_delete.insets = new Insets(0, 0, 5, 0);
			gbc_btn1_delete.gridx = 6;
			gbc_btn1_delete.gridy = 0;
			panel_1.add(btn1_delete, gbc_btn1_delete);
			
			GridBagConstraints gbc_btn2_delete = new GridBagConstraints();
			gbc_btn2_delete.insets = new Insets(0, 0, 5, 0);
			gbc_btn2_delete.anchor = GridBagConstraints.NORTHWEST;
			gbc_btn2_delete.gridx = 7;
			gbc_btn2_delete.gridy = 0;
			panel_2.add(btn2_delete, gbc_btn2_delete);
			
			GridBagConstraints gbc_btn3_filter = new GridBagConstraints();
			gbc_btn3_filter.gridx = 5;
			gbc_btn3_filter.gridy = 1;
			panel_3.add(btn3_filter, gbc_btn3_filter);
			
//SETUP TEXTPANES
			text1_amount.setEditable(false);
			text1_unit.setEditable(false);
			text1_allergens.setEditable(false);
			text1_expiration.setEditable(false);
			
			text2_ingredients.setEditable(false);
			
//LAYOUT TEXTPANES
			GridBagConstraints gbc_text1_amount = new GridBagConstraints();
			gbc_text1_amount.fill = GridBagConstraints.HORIZONTAL;
			gbc_text1_amount.anchor = GridBagConstraints.NORTH;
			gbc_text1_amount.insets = new Insets(0, 0, 0, 5);
			gbc_text1_amount.gridx = 1;
			gbc_text1_amount.gridy = 1;
			panel_1.add(text1_amount, gbc_text1_amount);
			
			GridBagConstraints gbc_text1_unit = new GridBagConstraints();
			gbc_text1_unit.fill = GridBagConstraints.HORIZONTAL;
			gbc_text1_unit.anchor = GridBagConstraints.NORTH;
			gbc_text1_unit.insets = new Insets(0, 0, 0, 5);
			gbc_text1_unit.gridx = 2;
			gbc_text1_unit.gridy = 1;
			panel_1.add(text1_unit, gbc_text1_unit);
			
			GridBagConstraints gbc_text1_allergens = new GridBagConstraints();
			gbc_text1_allergens.fill = GridBagConstraints.HORIZONTAL;
			gbc_text1_allergens.anchor = GridBagConstraints.NORTH;
			gbc_text1_allergens.insets = new Insets(0, 0, 0, 5);
			gbc_text1_allergens.gridx = 3;
			gbc_text1_allergens.gridy = 1;
			panel_1.add(text1_allergens, gbc_text1_allergens);
			
			GridBagConstraints gbc_text1_expiration = new GridBagConstraints();
			gbc_text1_expiration.fill = GridBagConstraints.HORIZONTAL;
			gbc_text1_expiration.anchor = GridBagConstraints.NORTH;
			gbc_text1_expiration.insets = new Insets(0, 0, 0, 5);
			gbc_text1_expiration.gridx = 4;
			gbc_text1_expiration.gridy = 1;
			panel_1.add(text1_expiration, gbc_text1_expiration);
			
			GridBagConstraints gbc_text2_ingredients = new GridBagConstraints();
			gbc_text2_ingredients.anchor = GridBagConstraints.NORTH;
			gbc_text2_ingredients.fill = GridBagConstraints.HORIZONTAL;
			gbc_text2_ingredients.insets = new Insets(0, 0, 0, 5);
			gbc_text2_ingredients.gridx = 1;
			gbc_text2_ingredients.gridy = 1;
			panel_2.add(text2_ingredients, gbc_text2_ingredients);

			GridBagConstraints gbc_text2_instruction = new GridBagConstraints();
			gbc_text2_instruction.fill = GridBagConstraints.HORIZONTAL;
			gbc_text2_instruction.anchor = GridBagConstraints.NORTH;
			gbc_text2_instruction.insets = new Insets(0, 0, 0, 5);
			gbc_text2_instruction.gridx = 2;
			gbc_text2_instruction.gridy = 1;
			panel_2.add(text2_instruction, gbc_text2_instruction);
			
			GridBagConstraints gbc_text2_allergens = new GridBagConstraints();
			gbc_text2_allergens.fill = GridBagConstraints.HORIZONTAL;
			gbc_text2_allergens.anchor = GridBagConstraints.NORTH;
			gbc_text2_allergens.insets = new Insets(0, 0, 0, 5);
			gbc_text2_allergens.gridx = 3;
			gbc_text2_allergens.gridy = 1;
			panel_2.add(text2_allergens, gbc_text2_allergens);
			
			GridBagConstraints gbc_text2_expiration = new GridBagConstraints();
			gbc_text2_expiration.fill = GridBagConstraints.HORIZONTAL;
			gbc_text2_expiration.anchor = GridBagConstraints.NORTH;
			gbc_text2_expiration.insets = new Insets(0, 0, 0, 5);
			gbc_text2_expiration.gridx = 4;
			gbc_text2_expiration.gridy = 1;
			panel_2.add(text2_expiration, gbc_text2_expiration);
		}


//NOTICE	
	private void notice(String title, String message){
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
//WARNING	
	private int confirm(String title, String message){
		return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION);
	}

//LISTHANDLER
	private class ListHandler implements ListSelectionListener{
		
		public void valueChanged(ListSelectionEvent e) {
			if (e.getSource() == list1_ingredients){
				String iname = list1_ingredients.getSelectedValue();
				if (iname == null) return;
				if(Storage.hasIngredient(iname)){
					text1_unit.setText(Storage.getUnit(iname).toString());
					text1_amount.setText(Storage.getAmount(iname).toString());
					text1_allergens.setText(Storage.getAllergens(iname).toString());
					text1_expiration.setText(DateFormat.getDateInstance().format(Storage.getExpiration(iname)));
				}
			}
			if (e.getSource() == list2_recipes){
				String temp = "";
				String rname = list2_recipes.getSelectedValue();
				if (rname == null) return;
				for (String iname : Book.listIngredients(rname)){
					if (Book.hasIngredient(rname, iname) == false) continue;
					temp = String.join("",temp,Book.getAmount(rname, iname).toString()," ",Storage.getUnit(iname).toString()," ",iname,"\n");
				}
				temp = temp.trim();
				text2_ingredients.setText(temp);
				text2_instruction.setText(Book.getInsturction(rname));
				text2_allergens.setText(Book.getAllergens(rname).toString());
				text2_expiration.setText(String.join("",DateFormat.getDateInstance().format(Book.getExpiration(rname)[0])," ",(String) Book.getExpiration(rname)[1]));
				lbl2_enoughValue.setText(Book.getEnough(rname).toString());
			}
		}//end valueChanged
		
	}//end private class ListHandler

//BUTTONHANDLER
	private class ButtonHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btn1_delete){
				String iname = list1_ingredients.getSelectedValue();
				if (iname == null) notice("Notice","You must first select an ingredient.");
				int check = confirm("Warning","If you delete ingredient in here, it will also be deleted from all recipes.");
				if (check == 2) return;
				Storage.deleteIngredient(iname);
				Book.deleteAllIngredient(iname);
				list1_ingredients.setListData(Storage.listIngredients());
				list2_recipes.setListData(Book.listRecipes());
				
				text1_unit.setText("");
				text1_amount.setText("");
				text1_allergens.setText("");
				text1_expiration.setText("");
			}
			if (e.getSource() == btn2_delete){
				String rname = list2_recipes.getSelectedValue();
				if (rname == null) notice("Notice","You must first select a recipe.");
				Book.deleteRecipe(rname);
				list2_recipes.setListData(Book.listRecipes());
				text2_ingredients.setText("");
				text2_instruction.setText("");
				text2_allergens.setText("");
				text2_expiration.setText("");
			}
		}
		
	}// end private class ButtonHandler
}//end public class


