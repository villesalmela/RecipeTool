package recipeTool;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;




public class GUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3121708348469315637L;

//CREATE *******************************************************************************************************************************
	
	//PANELS
			private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			private JPanel panel_1 = new JPanel();
			private JPanel panel_2 = new JPanel();
			private JPanel panel_3 = new JPanel();
			private JInternalFrame iframe1_add = new JInternalFrame("Add Ingredient");
			private JInternalFrame iframe2_add = new JInternalFrame("Add recipe");
			
	//LISTS
			private JList<String> list1_ingredients = new JList<String>();
			
			private JList<String> list2_recipes = new JList<String>();
			
			private JList<String> list3_mustHave = new JList<String>();
			private JList<String> list3_avoidAllergens = new JList<String>();
		
	//TEXTPANES
			private JTextPane text1_amount = new JTextPane();
			private JTextPane text1_unit = new JTextPane();
			private JTextPane text1_allergens = new JTextPane();
			private JTextPane text1_expiration = new JTextPane();
			
			private JTextPane text2_ingredients = new JTextPane();
			private JTextPane text2_instruction = new JTextPane();
			private JTextPane text2_allergens = new JTextPane();
			private JTextPane text2_expiration = new JTextPane();
			
	//LABELS
			private JLabel lbl1_amount= new JLabel("Amount");
			private JLabel lbl1_unit = new JLabel("Unit");
			private JLabel lbl1_allergens = new JLabel("Allergens");
			private JLabel lbl1_expiration = new JLabel("Expiration");
			
			private JLabel lbl1a_name = new JLabel("Name");
			private JLabel lbl1a_amount = new JLabel("Amount");
			private JLabel lbl1a_unit = new JLabel("Unit");
			private JLabel lbl1a_allergens = new JLabel("Allergens");
			private JLabel lbl1a_expiration = new JLabel("Expiration");
			
			private JLabel lbl2_ingredients = new JLabel("Ingredients");
			private JLabel lbl2_insturctions = new JLabel("Insturctions");
			private JLabel lbl2_allergens = new JLabel("Allergens");
			private JLabel lbl2_expiration = new JLabel("Expiration");
			private JLabel lbl2_enough = new JLabel("Enough");
			private JLabel lbl2_enoughValue = new JLabel("");
			private JLabel lbl2_filteringActive = new JLabel("Filtering Active");
			
			private JLabel lbl2a_name = new JLabel("Name");
			private JLabel lbl2a_instruction = new JLabel("Instruction");
			private JLabel lbl2a_ingredient = new JLabel("Ingredient");
			private JLabel lbl2a_amount = new JLabel("Amount");
			private JLabel lbl2a_unit = new JLabel("");
			
			private JLabel lbl3_mustHave = new JLabel("Must have ingredients");
			private JLabel lbl3_noShopping = new JLabel("No shopping");
			private JLabel lbl3_avoidAllergens = new JLabel("Avoid allergens");
			private JLabel lbl3_sortBy = new JLabel("Sort by");
			
	//BUTTONS
			private JButton btn1_delete = new JButton("Delete ingredient");
			private JButton btn2_delete = new JButton("Delete recipe");
			private JButton btn1_add = new JButton("Add ingredient");
			private JButton btn2_add = new JButton("Add recipe");
			private JButton btn1a_ok = new JButton("Add to Storage");
			private JButton btn2a_ok = new JButton("Add to Book");
			private JButton btn1a_cancel = new JButton("Cancel");
			private JButton btn2a_cancel = new JButton("Cancel");
			private JButton btn3_filter = new JButton("Filter");
			private JButton btn3_resetFilter = new JButton("Reset Filter");
			
	//CHECKBOXES
			private JCheckBox chckbx3_noShopping = new JCheckBox("No Shopping");
			
	//RADIO BUTTONS
			private JRadioButton rdbtn3_az = new JRadioButton("A-Z");
			private JRadioButton rdbtn3_expiration = new JRadioButton("Expiration date");
			
	//TEXTFIELD
			private JTextField txtf1a_name = new JTextField("name");
			private JTextField txtf1a_amount = new JTextField("amount");
			//private JTextField txtf1a_allergens = new JTextField("allergens");
			private JTextField txtf1a_expiration = new JTextField("expiration");
			
			private JTextField txtf2a_name = new JTextField("name");
			private JTextField txtf2a_instruction = new JTextField("instruction");
			private JTextField txtf2a_amount = new JTextField("amount");
			
	//COMBOBOXES
			private JComboBox<String> combx1a_unit = new JComboBox(Unit.values());
			private JComboBox<String> combx2a_ingredient = new JComboBox<String>(Storage.listIngredients());
			
	//FILTERING
			private boolean filteringActive = false;
			
			Object[][] tempTable = new Object[4][4];
			String[] tempArray = new String[]{""};
			JTable txtf1a_allergens = new JTable(tempTable, tempArray);

	public GUI(){
//CONFIG *******************************************************************************************************************************
			super("recipeTool");
			
			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setBounds(0,0,1000,1000);
			setLocationRelativeTo(null);
			getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
	//TABS
			getContentPane().add(tabbedPane);
			tabbedPane.addTab("Ingredients", null, panel_1, null);
			tabbedPane.addTab("Recipes", null, panel_2, null);
			tabbedPane.addTab("Filter", null, panel_3, null);
			
	//DIALOGS
			getContentPane().add(iframe1_add);
			getContentPane().add(iframe2_add);
			
			iframe1_add.setVisible(true);
			iframe2_add.setVisible(true);
		
	//LABELS
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
			lbl2_filteringActive.setFont(new Font("Tahoma", Font.BOLD, 18));
			lbl2_filteringActive.setVisible(false);
			
			lbl3_mustHave.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl3_noShopping.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl3_avoidAllergens.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbl3_sortBy.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
	//LISTS
			list1_ingredients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			populateList(1);
			list1_ingredients.addListSelectionListener(new ListHandler());
			
			list2_recipes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			populateList(2);
			list2_recipes.addListSelectionListener(new ListHandler());

			populateList(3);
		
	//BUTTONS
			btn1_delete.addActionListener(new ButtonHandler());
			btn1_add.addActionListener(new ButtonHandler());
			btn1a_ok.addActionListener(new ButtonHandler());
			btn1a_cancel.addActionListener(new ButtonHandler());
			
			btn2_delete.addActionListener(new ButtonHandler());
			btn2_add.addActionListener(new ButtonHandler());
			btn2a_ok.addActionListener(new ButtonHandler());
			btn2a_cancel.addActionListener(new ButtonHandler());
			
			btn3_filter.addActionListener(new ButtonHandler());
			btn3_resetFilter.addActionListener(new ButtonHandler());
			btn3_resetFilter.setEnabled(false);
			
	//TEXTPANES
			text1_amount.setEditable(false);
			text1_unit.setEditable(false);
			text1_allergens.setEditable(false);
			text1_expiration.setEditable(false);
			
			text2_ingredients.setEditable(false);
			text2_instruction.setEditable(false);
			text2_allergens.setEditable(false);
			text2_expiration.setEditable(false);
			

//GRIDBAG LAYOUT ***********************************************************************************************************************
	
	//PANELS
			GridBagLayout gbl_iframe1_add = new GridBagLayout();
			gbl_iframe1_add.columnWidths = new int[]{10, 100, 100, 100, 100, 100, 10};
			gbl_iframe1_add.rowHeights = new int[]{10, 20, 30, 30, 10};
			gbl_iframe1_add.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			gbl_iframe1_add.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE, 0.0};
			iframe1_add.getContentPane().setLayout(gbl_iframe1_add);
			
			GridBagLayout gbl_iframe2_add = new GridBagLayout();
			gbl_iframe2_add.columnWidths = new int[]{10, 100, 100, 100, 100, 10};
			gbl_iframe2_add.rowHeights = new int[]{10, 20, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 10};
			gbl_iframe2_add.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			gbl_iframe2_add.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			iframe2_add.getContentPane().setLayout(gbl_iframe2_add);
			
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
	
	//COMBOBOXES
			GridBagConstraints gbc_combx1a_unit = new GridBagConstraints();
			gbc_combx1a_unit.fill = GridBagConstraints.HORIZONTAL;
			gbc_combx1a_unit.insets = new Insets(0, 0, 5, 5);
			gbc_combx1a_unit.gridx = 3;
			gbc_combx1a_unit.gridy = 2;
			iframe1_add.getContentPane().add(combx1a_unit, gbc_combx1a_unit);

			GridBagConstraints gbc_combx2a_ingredient = new GridBagConstraints();
			gbc_combx2a_ingredient.fill = GridBagConstraints.HORIZONTAL;
			gbc_combx2a_ingredient.insets = new Insets(0, 0, 5, 5);
			gbc_combx2a_ingredient.gridx = 2;
			gbc_combx2a_ingredient.gridy = 2;
			iframe2_add.getContentPane().add(combx2a_ingredient, gbc_combx2a_ingredient);
			
	//TEXTFIELDS
			GridBagConstraints gbc_txtf1a_name = new GridBagConstraints();
			gbc_txtf1a_name.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtf1a_name.insets = new Insets(0, 0, 5, 5);
			gbc_txtf1a_name.gridx = 1;
			gbc_txtf1a_name.gridy = 2;
			iframe1_add.getContentPane().add(txtf1a_name, gbc_txtf1a_name);
			
			GridBagConstraints gbc_txtf1a_amount = new GridBagConstraints();
			gbc_txtf1a_amount.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtf1a_amount.insets = new Insets(0, 0, 5, 5);
			gbc_txtf1a_amount.gridx = 2;
			gbc_txtf1a_amount.gridy = 2;
			iframe1_add.getContentPane().add(txtf1a_amount, gbc_txtf1a_amount);
			
			GridBagConstraints gbc_txtf1a_allergens = new GridBagConstraints();
			gbc_txtf1a_allergens.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtf1a_allergens.insets = new Insets(0, 0, 5, 5);
			gbc_txtf1a_allergens.gridx = 4;
			gbc_txtf1a_allergens.gridy = 2;
			iframe1_add.getContentPane().add(txtf1a_allergens, gbc_txtf1a_allergens);
			
			GridBagConstraints gbc_txtf1a_expiration = new GridBagConstraints();
			gbc_txtf1a_expiration.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtf1a_expiration.insets = new Insets(0, 0, 5, 5);
			gbc_txtf1a_expiration.gridx = 5;
			gbc_txtf1a_expiration.gridy = 2;
			iframe1_add.getContentPane().add(txtf1a_expiration, gbc_txtf1a_expiration);
			
			GridBagConstraints gbc_txtf2a_name = new GridBagConstraints();
			gbc_txtf2a_name.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtf2a_name.insets = new Insets(0, 0, 5, 5);
			gbc_txtf2a_name.gridx = 1;
			gbc_txtf2a_name.gridy = 2;
			iframe2_add.getContentPane().add(txtf2a_name, gbc_txtf2a_name);
			
			GridBagConstraints gbc_txtf2a_amount = new GridBagConstraints();
			gbc_txtf2a_amount.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtf2a_amount.insets = new Insets(0, 0, 5, 5);
			gbc_txtf2a_amount.gridx = 3;
			gbc_txtf2a_amount.gridy = 2;
			iframe2_add.getContentPane().add(txtf2a_amount, gbc_txtf2a_amount);
			
			GridBagConstraints gbc_txtf2a_instruction = new GridBagConstraints();
			gbc_txtf2a_instruction.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtf2a_instruction.insets = new Insets(0, 0, 5, 5);
			gbc_txtf2a_instruction.gridx = 4;
			gbc_txtf2a_instruction.gridy = 2;
			iframe2_add.getContentPane().add(txtf2a_instruction, gbc_txtf2a_instruction);
			
	//TEXTPANES
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
			
	//LABELS
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
			
			GridBagConstraints gbc_lbl1a_name = new GridBagConstraints();
			gbc_lbl1a_name.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1a_name.gridx = 1;
			gbc_lbl1a_name.gridy = 1;
			iframe1_add.getContentPane().add(lbl1a_name, gbc_lbl1a_name);
			
			GridBagConstraints gbc_lbl1a_amount = new GridBagConstraints();
			gbc_lbl1a_amount.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1a_amount.gridx = 2;
			gbc_lbl1a_amount.gridy = 1;
			iframe1_add.getContentPane().add(lbl1a_amount, gbc_lbl1a_amount);
			
			GridBagConstraints gbc_lbl1a_unit = new GridBagConstraints();
			gbc_lbl1a_unit.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1a_unit.gridx = 3;
			gbc_lbl1a_unit.gridy = 1;
			iframe1_add.getContentPane().add(lbl1a_unit, gbc_lbl1a_unit);
			
			GridBagConstraints gbc_lbl1a_allergens = new GridBagConstraints();
			gbc_lbl1a_allergens.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1a_allergens.gridx = 4;
			gbc_lbl1a_allergens.gridy = 1;
			iframe1_add.getContentPane().add(lbl1a_allergens, gbc_lbl1a_allergens);
				
			GridBagConstraints gbc_lbl1a_expiration = new GridBagConstraints();
			gbc_lbl1a_expiration.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1a_expiration.gridx = 5;
			gbc_lbl1a_expiration.gridy = 1;
			iframe1_add.getContentPane().add(lbl1a_expiration, gbc_lbl1a_expiration);
			
			GridBagConstraints gbc_lbl2a_name = new GridBagConstraints();
			gbc_lbl2a_name.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2a_name.gridx = 1;
			gbc_lbl2a_name.gridy = 1;
			iframe2_add.getContentPane().add(lbl2a_name, gbc_lbl2a_name);
			
			GridBagConstraints gbc_lbl2a_instruction = new GridBagConstraints();
			gbc_lbl2a_instruction.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2a_instruction.gridx = 4;
			gbc_lbl2a_instruction.gridy = 1;
			iframe2_add.getContentPane().add(lbl2a_instruction, gbc_lbl2a_instruction);
			
			GridBagConstraints gbc_lbl2a_ingredient = new GridBagConstraints();
			gbc_lbl2a_ingredient.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2a_ingredient.gridx = 2;
			gbc_lbl2a_ingredient.gridy = 1;
			iframe2_add.getContentPane().add(lbl2a_ingredient, gbc_lbl2a_ingredient);
			
			GridBagConstraints gbc_lbl2a_amount = new GridBagConstraints();
			gbc_lbl2a_amount.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2a_amount.gridx = 3;
			gbc_lbl2a_amount.gridy = 1;
			iframe2_add.getContentPane().add(lbl2a_amount, gbc_lbl2a_amount);
				
			GridBagConstraints gbc_lbl2a_unit = new GridBagConstraints();
			gbc_lbl2a_unit.insets = new Insets(0, 0, 5, 0);
			gbc_lbl2a_unit.gridx = 5;
			gbc_lbl2a_unit.gridy = 1;
			iframe2_add.getContentPane().add(lbl2a_unit, gbc_lbl2a_unit);
			
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
			
			GridBagConstraints gbc_lbl2_filteringActive = new GridBagConstraints();
			gbc_lbl2_filteringActive.anchor = GridBagConstraints.NORTH;
			gbc_lbl2_filteringActive.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_filteringActive.gridx = 6;
			gbc_lbl2_filteringActive.gridy = 0;
			panel_2.add(lbl2_filteringActive, gbc_lbl2_filteringActive);
			
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
			
	//BUTTONS
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
			
			GridBagConstraints gbc_btn1_add = new GridBagConstraints();
			gbc_btn1_add.anchor = GridBagConstraints.NORTHWEST;
			gbc_btn1_add.insets = new Insets(0, 0, 5, 0);
			gbc_btn1_add.gridx = 6;
			gbc_btn1_add.gridy = 1;
			panel_1.add(btn1_add, gbc_btn1_add);
			
			GridBagConstraints gbc_btn2_add = new GridBagConstraints();
			gbc_btn2_add.insets = new Insets(0, 0, 5, 0);
			gbc_btn2_add.anchor = GridBagConstraints.NORTHWEST;
			gbc_btn2_add.gridx = 7;
			gbc_btn2_add.gridy = 1;
			panel_2.add(btn2_add, gbc_btn2_add);
			
			GridBagConstraints gbc_btn1a_ok = new GridBagConstraints();
			gbc_btn1a_ok.gridwidth = 2;
			gbc_btn1a_ok.insets = new Insets(0, 0, 5, 5);
			gbc_btn1a_ok.gridx = 6;
			gbc_btn1a_ok.gridy = 2;
			iframe1_add.getContentPane().add(btn1a_ok, gbc_btn1a_ok);

			GridBagConstraints gbc_btn1a_cancel = new GridBagConstraints();
			gbc_btn1a_cancel.insets = new Insets(0, 0, 5, 5);
			gbc_btn1a_cancel.gridx = 6;
			gbc_btn1a_cancel.gridy = 3;
			iframe1_add.getContentPane().add(btn1a_cancel, gbc_btn1a_cancel);
			
			GridBagConstraints gbc_btn2a_ok = new GridBagConstraints();
			gbc_btn2a_ok.gridwidth = 2;
			gbc_btn2a_ok.insets = new Insets(0, 0, 5, 5);
			gbc_btn2a_ok.gridx = 5;
			gbc_btn2a_ok.gridy = 2;
			iframe2_add.getContentPane().add(btn2a_ok, gbc_btn2a_ok);

			GridBagConstraints gbc_btn2a_cancel = new GridBagConstraints();
			gbc_btn2a_cancel.insets = new Insets(0, 0, 5, 5);
			gbc_btn2a_cancel.gridx = 5;
			gbc_btn2a_cancel.gridy = 3;
			iframe2_add.getContentPane().add(btn2a_cancel, gbc_btn2a_cancel);
			
			GridBagConstraints gbc_btn3_filter = new GridBagConstraints();
			gbc_btn3_filter.gridx = 5;
			gbc_btn3_filter.gridy = 1;
			panel_3.add(btn3_filter, gbc_btn3_filter);
			
			GridBagConstraints gbc_btn3_resetFilter = new GridBagConstraints();
			gbc_btn3_resetFilter.gridx = 7;
			gbc_btn3_resetFilter.gridy = 1;
			panel_2.add(btn3_resetFilter, gbc_btn3_resetFilter);
			
	//LISTS
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

			GridBagConstraints gbc_list3_mustHave = new GridBagConstraints();
			gbc_list3_mustHave.insets = new Insets(0, 0, 0, 5);
			gbc_list3_mustHave.fill = GridBagConstraints.HORIZONTAL;
			gbc_list3_mustHave.gridx = 0;
			gbc_list3_mustHave.gridy = 1;
			panel_3.add(list3_mustHave, gbc_list3_mustHave);
			
			GridBagConstraints gbc_list3_avoidAllergens = new GridBagConstraints();
			gbc_list3_avoidAllergens.insets = new Insets(0, 0, 0, 5);
			gbc_list3_avoidAllergens.fill = GridBagConstraints.HORIZONTAL;
			gbc_list3_avoidAllergens.gridx = 2;
			gbc_list3_avoidAllergens.gridy = 1;
			panel_3.add(list3_avoidAllergens, gbc_list3_avoidAllergens);
			
	//CHECKBOX
			GridBagConstraints gbc_chckbxNoShopping = new GridBagConstraints();
			gbc_chckbxNoShopping.insets = new Insets(0, 0, 0, 5);
			gbc_chckbxNoShopping.gridx = 1;
			gbc_chckbxNoShopping.gridy = 1;
			panel_3.add(chckbx3_noShopping, gbc_chckbxNoShopping);
	
	//RADIO BUTTONS
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
	}

//GUI METHODS **************************************************************************************************************************
	
	//NOTICE	
		private void notice(String title, String message){
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
		}
		
	//CONFIRM	
		private int confirm(String title, String message){
			return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION);
		}
		
	//CLEAR FIELDS
		private void clearFields(int tabNumber){
			switch (tabNumber){
				case 1:
					text1_unit.setText("");
					text1_amount.setText("");
					text1_allergens.setText("");
					text1_expiration.setText("");
					list1_ingredients.clearSelection();
					break;
				case 2:
					text2_ingredients.setText("");
					text2_instruction.setText("");
					text2_allergens.setText("");
					text2_expiration.setText("");
					lbl2_enoughValue.setText("");
					list2_recipes.clearSelection();
					break;
				case 3:
					list3_mustHave.clearSelection();
					list3_avoidAllergens.clearSelection();
					chckbx3_noShopping.setSelected(false);
					break;
			}
		}
		
	//POPULATE LIST
		private void populateList(int tabNumber){
			switch (tabNumber){
				case 1:
					list1_ingredients.setListData(Storage.listIngredients());
					break;
				case 2:
					if(filteringActive){
						List<String> mustHave = list3_mustHave.getSelectedValuesList();
						List<String> avoidAllergens = list3_avoidAllergens.getSelectedValuesList();
						boolean enough = chckbx3_noShopping.isSelected();
						list2_recipes.setListData(Book.filterRecipes(mustHave, avoidAllergens, enough));
						break;
					}
					list2_recipes.setListData(Book.listRecipes());
					break;
				case 3: 
					list3_mustHave.setListData(Storage.listIngredients());
					list3_avoidAllergens.setListData(Storage.listAllergens());
			}
		}
		
	//POPULATE FIELDS
		private void populateFields(int tabNumber){
			switch (tabNumber){
				case 1:
					String iname = list1_ingredients.getSelectedValue();
					if (iname == null) return;
					
					if(Storage.hasIngredient(iname)){
						text1_unit.setText(Storage.getUnit(iname).toString());
						text1_amount.setText(Storage.getAmount(iname).toString());
						text1_allergens.setText(Storage.getAllergens(iname).toString());
						text1_expiration.setText(DateFormat.getDateInstance().format(Storage.getExpiration(iname)));
					}
					break;
				case 2:
					String rname = list2_recipes.getSelectedValue();
					if (rname == null) return;
					
					String temp = "";
					for (String iname1 : Book.listIngredients(rname)){
						if (Book.hasIngredient(rname, iname1) == false) continue;
						temp = String.join("",temp,Book.getAmount(rname, iname1).toString()," ",Storage.getUnit(iname1).toString()," ",iname1,"\n");
					}
					temp = temp.trim();
					
					text2_ingredients.setText(temp);
					text2_instruction.setText(Book.getInsturction(rname));
					text2_allergens.setText(Book.getAllergens(rname).toString());
					text2_expiration.setText(String.join("",DateFormat.getDateInstance().format(Book.getExpiration(rname)[0])," ",(String) Book.getExpiration(rname)[1]));
					lbl2_enoughValue.setText(Book.getEnough(rname).toString());
			}
		}
	
//EVENT HANDLERS ***********************************************************************************************************************
		
	//LISTHANDLER
		private class ListHandler implements ListSelectionListener{
			
			public void valueChanged(ListSelectionEvent e) {
				Object source = e.getSource();
				
				if (source == list1_ingredients){
					populateFields(1);
				}
				if (source == list2_recipes){
					populateFields(2);
				}
			}
		}
	
	//BUTTONHANDLER
		private class ButtonHandler implements ActionListener{
	
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				if (source == btn1_delete){
					String iname = list1_ingredients.getSelectedValue();
					
					if (iname == null) {
						notice("Notice","You must first select an ingredient.");
						return;
					}
					if (confirm("Warning","If you delete ingredient in here, it will also be deleted from all recipes.") == 2) return;

					Storage.deleteIngredient(iname);
					Book.deleteAllIngredient(iname);
					
					populateList(1);
					clearFields(1);
					populateFields(2);
				}
				
				if (source == btn1_add){
					iframe1_add.setVisible(true);
				}
				
				if (source == btn2_delete){
					String rname = list2_recipes.getSelectedValue();
					
					if (rname == null){
						notice("Notice","You must first select a recipe.");
						return;
					}
					
					Book.deleteRecipe(rname);
					
					populateList(2);
					clearFields(2);
				}
				
				if (source == btn2_add){
					iframe2_add.setVisible(true);
				}
				
				if (source == btn3_filter){
					filteringActive = true;
					populateList(2);					
					clearFields(2);
					btn3_resetFilter.setEnabled(true);
					lbl2_filteringActive.setVisible(true);
					tabbedPane.setSelectedComponent(panel_2);
				}
				
				if (source == btn3_resetFilter){
					filteringActive = false;
					btn3_resetFilter.setEnabled(false);
					lbl2_filteringActive.setVisible(false);
					populateList(2);
					clearFields(2);
				}
				
				if (source == btn1a_cancel){
					iframe1_add.setVisible(false);
				}
				
				if (source == btn2a_cancel){
					iframe2_add.setVisible(false);
				}
				
				if (source == btn1a_ok){
					/*
					String name = txtf1a_name.getText();
					double amount = Double.parseDouble(txtf1a_amount.getText());
					Unit unit = (Unit) combx1a_unit.getSelectedItem();
					String temp = txtf1a_allergens.getText();
					TreeSet<String> allergen = new TreeSet<String>();
					for(String temp2 : temp.split(",")){
						allergen.add(temp2);
					}
					Date expiration = null;
					try {
						expiration = DateFormat.getDateInstance().parse(txtf1a_expiration.getText());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Storage.setIngredient(name, amount, unit, allergen, expiration);
					populateList(1);
					*/
					iframe1_add.setVisible(false);
				}
				
				if (source == btn2a_ok){
					iframe2_add.setVisible(false);
					for (int i=0; i<4; i++){
						System.out.println(txtf1a_allergens.getValueAt(i, 0));
					}
				}
			}
		}
}


