package recipeTool;

import javax.swing.CellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.Component;
import java.awt.FlowLayout;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;

import java.util.ArrayList;
import java.util.Calendar;

public class GUI extends JFrame{

	private static final long serialVersionUID = -3121708348469315637L;

//CREATE *******************************************************************************************************************************
	
	//PANELS
			private final JTabbedPane tabbedPane = new JTabbedPane();
			private final JPanel panel_1 = new JPanel();
			private final JPanel panel_2 = new JPanel();
			private final JPanel panel_3 = new JPanel();
			private final JInternalFrame iframe1_add = new JInternalFrame("Add Ingredient");
			private final JInternalFrame iframe2_add = new JInternalFrame("Add recipe");
			private final JInternalFrame iframe0_settings = new JInternalFrame("Settings");
			
			private final java.awt.Container[] containerArray = new java.awt.Container[]{getContentPane(),this.panel_1,this.panel_2,this.panel_3,this.iframe1_add.getContentPane(),this.iframe2_add.getContentPane(),this.iframe0_settings.getContentPane()};
			
	//LISTS
			private final JList<String> list1_ingredients = new JList<>();
			
			private final JList<String> list2_recipes = new JList<>();
			
			private final JList<String> list3_mustHave = new JList<>();
			private final JList<String> list3_avoidAllergens = new JList<>();
		
	//TEXTPANES
			private final JTextPane text1_amount = new JTextPane();
			private final JTextPane text1_unit = new JTextPane();
			private final JTextPane text1_allergens = new JTextPane();
			private final JTextPane text1_expiration = new JTextPane();
			
			private final JTextPane text2_ingredients = new JTextPane();
			private final JTextPane text2_instruction = new JTextPane();
			private final JTextPane text2_allergens = new JTextPane();
			private final JTextPane text2_expiration = new JTextPane();
			
			private final JTextPane text2a_instruction = new JTextPane();
			
	//ICONS
			private final ImageIcon connected = new ImageIcon(GUI.class.getResource("/recipeTool/images/connected.png"));
			private final ImageIcon disconnected = new ImageIcon(GUI.class.getResource("/recipeTool/images/disconnected.png"));
			
	//LABELS
			private final JLabel lbl1_amount= new JLabel("Amount");
			private final JLabel lbl1_unit = new JLabel("Unit");
			private final JLabel lbl1_allergens = new JLabel("Allergens");
			private final JLabel lbl1_expiration = new JLabel("Expiration");
			
			private final JLabel lbl1a_name = new JLabel("Name");
			private final JLabel lbl1a_amount = new JLabel("Amount");
			private final JLabel lbl1a_unit = new JLabel("Unit");
			private final JLabel lbl1a_allergens = new JLabel("Allergens");
			private final JLabel lbl1a_expiration = new JLabel("Expiration");
			
			private final JLabel lbl2_ingredients = new JLabel("Ingredients");
			private final JLabel lbl2_insturctions = new JLabel("Insturctions");
			private final JLabel lbl2_allergens = new JLabel("Allergens");
			private final JLabel lbl2_expiration = new JLabel("Expiration");
			private final JLabel lbl2_enough = new JLabel("Enough");
			private final JLabel lbl2_enoughValue = new JLabel("");
			private final JLabel lbl2_filteringActive = new JLabel("Filtering Active");
			
			private final JLabel lbl2a_name = new JLabel("Name");
			private final JLabel lbl2a_instruction = new JLabel("Instruction");
			private final JLabel lbl2a_ingredient = new JLabel("Ingredient");
			private final JLabel lbl2a_amount = new JLabel("Amount");
			private final JLabel lbl2a_unit = new JLabel("");
			private final JLabel lbl2a_unit_header = new JLabel("Unit");
			
			private final JLabel lbl3_mustHave = new JLabel("Must have ingredients");
			private final JLabel lbl3_noShopping = new JLabel("No shopping");
			private final JLabel lbl3_avoidAllergens = new JLabel("Avoid allergens");
			private final JLabel lbl3_sortBy = new JLabel("Sort by");
			
			private final JLabel lbl0_ip = new JLabel("IP-address");
			private final JLabel lbl0_port = new JLabel("Port number");
			private final JLabel lbl0_user = new JLabel("Username");
			private final JLabel lbl0_pass = new JLabel("Password");
			
			private final JLabel lbl_connection = new JLabel();
			
	//BUTTONS
			private final JButton btn1_add = new JButton("Add ingredient");
			private final JButton btn1_delete = new JButton("Delete ingredient");
			
			private final JButton btn2_add = new JButton("Add recipe");
			private final JButton btn2_delete = new JButton("Delete recipe");
			
			private final JButton btn1a_ok = new JButton("Add to Storage");
			private final JButton btn1a_cancel = new JButton("Cancel");
			
			private final JButton btn2a_ok = new JButton("Add to Book");
			private final JButton btn2a_cancel = new JButton("Cancel");
			private final JButton btn2a_more = new JButton("Add Row");
			
			private final JButton btn3_filter = new JButton("Filter");
			private final JButton btn3_resetFilter = new JButton("Reset Filter");
			
			private final JButton btn0_settings_open = new JButton("Settings");
			private final JButton btn0_settings_connect = new JButton("Connect");
			private final JButton btn0_settings_close = new JButton("Close settings");
			private final JButton btn0_settings_save = new JButton("Save");
			private final JButton btn0_settings_load = new JButton("Load");
			private final JButton btn0_settings_test = new JButton("Load Test Values");
			
	//CHECKBOXES
			private final JCheckBox chckbx3_noShopping = new JCheckBox("No Shopping");
			
	//RADIO BUTTONS
			private final JRadioButton rdbtn3_az = new JRadioButton("A-Z");
			private final JRadioButton rdbtn3_ex = new JRadioButton("Expiration date");
			
	//TEXTFIELDS
			private final JTextField txtf1a_name = new JTextField("");
			private final JTextField txtf2a_name = new JTextField("");
			private final JTextField txtf0_ip = new JTextField("");
			private final JTextField txtf0_user = new JTextField("");
			private final JPasswordField txtf0_pass = new JPasswordField("");
	//TABLE
			private final JTable table1a_allergens = new JTable();
			
	//SPINNERS
			private final JSpinner spin1a_amount = new JSpinner();
			private final JSpinner spin1a_expiration = new JSpinner();
			private final JSpinner spin2a_amount = new JSpinner();
			private final JSpinner spin0_port = new JSpinner();
			
			
	//COMBOBOXES
			@SuppressWarnings({ "unchecked", "rawtypes" })
			private final JComboBox<Unit> combx1a_unit = new JComboBox(Unit.values());
			private final JComboBox<String> combx2a_ingredient = new JComboBox<>();
			
	//TEMPORARY UI
			@SuppressWarnings("unchecked")
			private final JComboBox<String>[] combx2a_temp = new JComboBox[10];
			private final JSpinner[] spin2a_temp = new JSpinner[10];
			private final JLabel[] lbl2a_temp = new JLabel[10];
			
			private final GridBagConstraints[] gbc_combx2a_temp = new GridBagConstraints[10];
			private final GridBagConstraints[] gbc_spin2a_temp = new GridBagConstraints[10];
			private final GridBagConstraints[] gbc_lbl2a_temp = new GridBagConstraints[10];
			
	//STATE
			private boolean filteringActive = false;
			
	//COUNTER
			private int counter2a = 0;
			
	//HANDLERS
			private final ButtonHandler buttonhandler = new ButtonHandler();
			private final ListHandler listhandler = new ListHandler();
			private final WindowHandler windowhandler = new WindowHandler();
			
			
//CONSTRUCTOR **************************************************************************************************************************		
	@SuppressWarnings("synthetic-access")
	public GUI(){
			super("recipeTool");
			addWindowListener(this.windowhandler);
			setResizable(false);
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			setBounds(0,0,1220,820);
			setLocationRelativeTo(null);
			
			GridBagLayout gbl_root = new GridBagLayout();
			gbl_root.columnWidths = new int[]{10, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 10};
			gbl_root.rowHeights = new int[]{10, 100, 100, 100, 100, 100, 100, 100, 100, 10};
			gbl_root.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			gbl_root.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			getContentPane().setLayout(gbl_root);
			
			gbLayout();
			config();
			
			refreshLists();
	}//end constructor
	
//CONFIG *******************************************************************************************************************************
	@SuppressWarnings("synthetic-access")
	private void config(){
			
	//TABS
			this.tabbedPane.addTab("Ingredients", null, this.panel_1, null);
			this.tabbedPane.addTab("Recipes", null, this.panel_2, null);
			this.tabbedPane.addTab("Filter", null, this.panel_3, null);
			
	//RADIO BUTTONS
			this.rdbtn3_az.setSelected(true);
			this.rdbtn3_ex.setSelected(false);
			
	//LABELS
			final Font font14 = new Font("Tahoma", Font.BOLD, 14);
			
			for (Component c : this.tabbedPane.getComponents()){
				for (Component c2 : ((JPanel)c).getComponents()){
					if (c2 instanceof JLabel) ((JLabel)c2).setFont(font14);
				}
			}
			
			connection();
			
			this.lbl2_filteringActive.setFont(new Font("Tahoma", Font.BOLD, 18));
			this.lbl2_filteringActive.setVisible(false);
			
	//LISTS
			this.list1_ingredients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.list2_recipes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			this.list3_avoidAllergens.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			this.list3_mustHave.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			this.list1_ingredients.addListSelectionListener(this.listhandler);
			this.list2_recipes.addListSelectionListener(this.listhandler);
			
	//BUTTONS
			this.btn3_resetFilter.setEnabled(false);
			for (java.awt.Container c : this.containerArray){
				for (Component c2 : c.getComponents()){
					if (c2 instanceof JButton || c2 instanceof JRadioButton) ((javax.swing.AbstractButton)c2).addActionListener(this.buttonhandler);
					else if (c2 instanceof JComboBox) ((JComboBox<?>)c2).addActionListener(this.buttonhandler);
				}
			}
			
	//TEXTPANES
			for (Component c : this.tabbedPane.getComponents()){
				for (Component c2 : ((JPanel)c).getComponents()){
					if (c2 instanceof JTextPane) ((JTextPane)c2).setEditable(false);
				}
			}
			
	//SPINNERS
			this.spin1a_amount.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
			this.spin1a_amount.setEditor(new JSpinner.NumberEditor(this.spin1a_amount, "##0.0#"));
			
			this.spin1a_expiration.setModel(new SpinnerDateModel(new Date(), new Date(946677600921L), new Date(32535122400921L), Calendar.DAY_OF_YEAR));
			this.spin1a_expiration.setEditor(new JSpinner.DateEditor(this.spin1a_expiration, "d.M.y"));
			
			this.spin2a_amount.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
			this.spin2a_amount.setEditor(new JSpinner.NumberEditor(this.spin2a_amount, "##0.0#"));
			
			this.spin0_port.setModel(new SpinnerNumberModel(0, 0, 65535, 1));
			
		//TABLE
			DefaultTableModel temp = new DefaultTableModel(10,1);
			table1a_allergens.setModel(temp);
	}//end method config

//LAYOUT *******************************************************************************************************************************
//GRIDBAG LAYOUT ***********************************************************************************************************************
	private void gbLayout(){
		
	//INTERNAL FRAMES
		GridBagConstraints gbc_iframe1_add = new GridBagConstraints();
		gbc_iframe1_add.fill = GridBagConstraints.BOTH;
		gbc_iframe1_add.insets = new Insets(0, 0, 5, 5);
		gbc_iframe1_add.gridwidth = 7;
		gbc_iframe1_add.gridheight = 3;
		gbc_iframe1_add.gridx = 2;
		gbc_iframe1_add.gridy = 3;
		
		GridBagConstraints gbc_iframe2_add = new GridBagConstraints();
		gbc_iframe2_add.fill = GridBagConstraints.BOTH;
		gbc_iframe2_add.insets = new Insets(0, 0, 5, 5);
		gbc_iframe2_add.gridwidth = 7;
		gbc_iframe2_add.gridheight = 5;
		gbc_iframe2_add.gridx = 2;
		gbc_iframe2_add.gridy = 3;
		
		GridBagConstraints gbc_iframe0_settings = new GridBagConstraints();
		gbc_iframe0_settings.fill = GridBagConstraints.BOTH;
		gbc_iframe0_settings.insets = new Insets(0, 0, 5, 5);
		gbc_iframe0_settings.gridwidth = 6;
		gbc_iframe0_settings.gridheight = 3;
		gbc_iframe0_settings.gridx = 2;
		gbc_iframe0_settings.gridy = 3;
		
		getContentPane().add(this.iframe1_add, gbc_iframe1_add);
		getContentPane().add(this.iframe2_add, gbc_iframe2_add);
		getContentPane().add(this.iframe0_settings, gbc_iframe0_settings);
	
	//PANELS
			GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
			gbc_tabbedPane.fill = GridBagConstraints.BOTH;
			gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
			gbc_tabbedPane.gridwidth = 12;
			gbc_tabbedPane.gridheight = 7;
			gbc_tabbedPane.gridx = 1;
			gbc_tabbedPane.gridy = 1;
			getContentPane().add(this.tabbedPane, gbc_tabbedPane);
		
			GridBagLayout gbl_iframe1_add = new GridBagLayout();
			gbl_iframe1_add.columnWidths = new int[]{10, 100, 100, 100, 100, 100, 100, 10};
			gbl_iframe1_add.rowHeights = new int[]{10, 20, 30, 30, 10};
			this.iframe1_add.getContentPane().setLayout(gbl_iframe1_add);
			
			GridBagLayout gbl_iframe2_add = new GridBagLayout();
			gbl_iframe2_add.columnWidths = new int[]{10, 100, 100, 100, 30, 200, 100, 10};
			gbl_iframe2_add.rowHeights = new int[]{10, 20, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 10};
			this.iframe2_add.getContentPane().setLayout(gbl_iframe2_add);
			
			GridBagLayout gbl_iframe_settings = new GridBagLayout();
			gbl_iframe_settings.columnWidths = new int[]{10, 100, 100, 100, 100, 100, 10};
			gbl_iframe_settings.rowHeights = new int[]{10, 20, 30, 30, 30, 10};
			this.iframe0_settings.getContentPane().setLayout(gbl_iframe_settings);
			
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{10, 200, 200, 200, 200, 200, 150, 10};
			gbl_panel_1.rowHeights = new int[]{10, 100, 100, 100, 100, 250};
			this.panel_1.setLayout(gbl_panel_1);
			
			GridBagLayout gbl_panel_2 = new GridBagLayout();
			gbl_panel_2.columnWidths = new int[]{10, 200, 200, 200, 200, 200, 150, 10};
			gbl_panel_2.rowHeights = new int[]{10, 100, 100, 100, 100, 250};
			this.panel_2.setLayout(gbl_panel_2);
			
			GridBagLayout gbl_panel_3 = new GridBagLayout();
			gbl_panel_3.columnWidths = new int[]{10, 200, 200, 200, 200, 200, 150, 10};
			gbl_panel_3.rowHeights = new int[]{10, 100, 100, 450};
			this.panel_3.setLayout(gbl_panel_3);
	
	//COMBOBOXES
			GridBagConstraints gbc_combx1a_unit = new GridBagConstraints();
			gbc_combx1a_unit.fill = GridBagConstraints.HORIZONTAL;
			gbc_combx1a_unit.insets = new Insets(0, 0, 5, 5);
			gbc_combx1a_unit.gridx = 3;
			gbc_combx1a_unit.gridy = 2;
			this.iframe1_add.getContentPane().add(this.combx1a_unit, gbc_combx1a_unit);

			GridBagConstraints gbc_combx2a_ingredient = new GridBagConstraints();
			gbc_combx2a_ingredient.fill = GridBagConstraints.HORIZONTAL;
			gbc_combx2a_ingredient.insets = new Insets(0, 0, 5, 5);
			gbc_combx2a_ingredient.gridx = 2;
			gbc_combx2a_ingredient.gridy = 2;
			this.iframe2_add.getContentPane().add(this.combx2a_ingredient, gbc_combx2a_ingredient);
			
	//SPINNERS
			GridBagConstraints gbc_spin1a_amount = new GridBagConstraints();
			gbc_spin1a_amount.fill = GridBagConstraints.HORIZONTAL;
			gbc_spin1a_amount.insets = new Insets(0, 0, 5, 5);
			gbc_spin1a_amount.gridx = 2;
			gbc_spin1a_amount.gridy = 2;
			this.iframe1_add.getContentPane().add(this.spin1a_amount, gbc_spin1a_amount);
			
			GridBagConstraints gbc_spin1a_expiration = new GridBagConstraints();
			gbc_spin1a_expiration.fill = GridBagConstraints.HORIZONTAL;
			gbc_spin1a_expiration.insets = new Insets(0, 0, 5, 5);
			gbc_spin1a_expiration.gridx = 5;
			gbc_spin1a_expiration.gridy = 2;
			this.iframe1_add.getContentPane().add(this.spin1a_expiration, gbc_spin1a_expiration);
			
			GridBagConstraints gbc_spin2a_amount = new GridBagConstraints();
			gbc_spin2a_amount.fill = GridBagConstraints.HORIZONTAL;
			gbc_spin2a_amount.insets = new Insets(0, 0, 5, 5);
			gbc_spin2a_amount.gridx = 3;
			gbc_spin2a_amount.gridy = 2;
			this.iframe2_add.getContentPane().add(this.spin2a_amount, gbc_spin2a_amount);
			
			GridBagConstraints gbc_spin0_port = new GridBagConstraints();
			gbc_spin0_port.fill = GridBagConstraints.HORIZONTAL;
			gbc_spin0_port.insets = new Insets(0, 0, 5, 5);
			gbc_spin0_port.gridx = 2;
			gbc_spin0_port.gridy = 2;
			this.iframe0_settings.getContentPane().add(this.spin0_port, gbc_spin0_port);
			
	//TABLES
			GridBagConstraints gbc_table1a_allergens = new GridBagConstraints();
			gbc_table1a_allergens.gridheight = 10;
			gbc_table1a_allergens.fill = GridBagConstraints.BOTH;
			gbc_table1a_allergens.insets = new Insets(0, 0, 5, 5);
			gbc_table1a_allergens.gridx = 4;
			gbc_table1a_allergens.gridy = 2;
			this.iframe1_add.getContentPane().add(this.table1a_allergens, gbc_table1a_allergens);
			
	//TEXTFIELDS
			GridBagConstraints gbc_txtf1a_name = new GridBagConstraints();
			gbc_txtf1a_name.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtf1a_name.insets = new Insets(0, 0, 5, 5);
			gbc_txtf1a_name.gridx = 1;
			gbc_txtf1a_name.gridy = 2;
			this.iframe1_add.getContentPane().add(this.txtf1a_name, gbc_txtf1a_name);
			
			GridBagConstraints gbc_txtf2a_name = new GridBagConstraints();
			gbc_txtf2a_name.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtf2a_name.insets = new Insets(0, 0, 5, 5);
			gbc_txtf2a_name.gridx = 1;
			gbc_txtf2a_name.gridy = 2;
			this.iframe2_add.getContentPane().add(this.txtf2a_name, gbc_txtf2a_name);
			
			GridBagConstraints gbc_txtf0_ip = new GridBagConstraints();
			gbc_txtf0_ip.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtf0_ip.insets = new Insets(0, 0, 5, 5);
			gbc_txtf0_ip.gridx = 1;
			gbc_txtf0_ip.gridy = 2;
			this.iframe0_settings.getContentPane().add(this.txtf0_ip, gbc_txtf0_ip);
			
			GridBagConstraints gbc_txtf0_user = new GridBagConstraints();
			gbc_txtf0_user.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtf0_user.insets = new Insets(0, 0, 5, 5);
			gbc_txtf0_user.gridx = 3;
			gbc_txtf0_user.gridy = 2;
			this.iframe0_settings.getContentPane().add(this.txtf0_user, gbc_txtf0_user);
			
			GridBagConstraints gbc_txtf0_pass = new GridBagConstraints();
			gbc_txtf0_pass.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtf0_pass.insets = new Insets(0, 0, 5, 5);
			gbc_txtf0_pass.gridx = 4;
			gbc_txtf0_pass.gridy = 2;
			this.iframe0_settings.getContentPane().add(this.txtf0_pass, gbc_txtf0_pass);
			
	//TEXTPANES
			GridBagConstraints gbc_text1_amount = new GridBagConstraints();
			gbc_text1_amount.fill = GridBagConstraints.HORIZONTAL;
			gbc_text1_amount.anchor = GridBagConstraints.NORTH;
			gbc_text1_amount.insets = new Insets(0, 0, 0, 5);
			gbc_text1_amount.gridx = 1;
			gbc_text1_amount.gridy = 2;
			this.panel_1.add(this.text1_amount, gbc_text1_amount);
			
			GridBagConstraints gbc_text1_unit = new GridBagConstraints();
			gbc_text1_unit.fill = GridBagConstraints.HORIZONTAL;
			gbc_text1_unit.anchor = GridBagConstraints.NORTH;
			gbc_text1_unit.insets = new Insets(0, 0, 0, 5);
			gbc_text1_unit.gridx = 2;
			gbc_text1_unit.gridy = 2;
			this.panel_1.add(this.text1_unit, gbc_text1_unit);
			
			GridBagConstraints gbc_text1_allergens = new GridBagConstraints();
			gbc_text1_allergens.fill = GridBagConstraints.HORIZONTAL;
			gbc_text1_allergens.anchor = GridBagConstraints.NORTH;
			gbc_text1_allergens.insets = new Insets(0, 0, 0, 5);
			gbc_text1_allergens.gridx = 3;
			gbc_text1_allergens.gridy = 2;
			this.panel_1.add(this.text1_allergens, gbc_text1_allergens);
			
			GridBagConstraints gbc_text1_expiration = new GridBagConstraints();
			gbc_text1_expiration.fill = GridBagConstraints.HORIZONTAL;
			gbc_text1_expiration.anchor = GridBagConstraints.NORTH;
			gbc_text1_expiration.insets = new Insets(0, 0, 0, 5);
			gbc_text1_expiration.gridx = 4;
			gbc_text1_expiration.gridy = 2;
			this.panel_1.add(this.text1_expiration, gbc_text1_expiration);
			
			GridBagConstraints gbc_text2_ingredients = new GridBagConstraints();
			gbc_text2_ingredients.anchor = GridBagConstraints.NORTH;
			gbc_text2_ingredients.fill = GridBagConstraints.HORIZONTAL;
			gbc_text2_ingredients.insets = new Insets(0, 0, 0, 5);
			gbc_text2_ingredients.gridx = 1;
			gbc_text2_ingredients.gridy = 2;
			this.panel_2.add(this.text2_ingredients, gbc_text2_ingredients);

			GridBagConstraints gbc_text2_instruction = new GridBagConstraints();
			gbc_text2_instruction.fill = GridBagConstraints.HORIZONTAL;
			gbc_text2_instruction.anchor = GridBagConstraints.NORTH;
			gbc_text2_instruction.insets = new Insets(0, 0, 0, 5);
			gbc_text2_instruction.gridx = 2;
			gbc_text2_instruction.gridy = 2;
			this.panel_2.add(this.text2_instruction, gbc_text2_instruction);
			
			GridBagConstraints gbc_text2_allergens = new GridBagConstraints();
			gbc_text2_allergens.fill = GridBagConstraints.HORIZONTAL;
			gbc_text2_allergens.anchor = GridBagConstraints.NORTH;
			gbc_text2_allergens.insets = new Insets(0, 0, 0, 5);
			gbc_text2_allergens.gridx = 3;
			gbc_text2_allergens.gridy = 2;
			this.panel_2.add(this.text2_allergens, gbc_text2_allergens);
			
			GridBagConstraints gbc_text2_expiration = new GridBagConstraints();
			gbc_text2_expiration.fill = GridBagConstraints.HORIZONTAL;
			gbc_text2_expiration.anchor = GridBagConstraints.NORTH;
			gbc_text2_expiration.insets = new Insets(0, 0, 0, 5);
			gbc_text2_expiration.gridx = 4;
			gbc_text2_expiration.gridy = 2;
			this.panel_2.add(this.text2_expiration, gbc_text2_expiration);
			
			GridBagConstraints gbc_text2a_instruction = new GridBagConstraints();
			gbc_text2a_instruction.gridheight = 9;
			gbc_text2a_instruction.fill = GridBagConstraints.BOTH;
			gbc_text2a_instruction.insets = new Insets(0, 0, 5, 5);
			gbc_text2a_instruction.gridx = 5;
			gbc_text2a_instruction.gridy = 2;
			this.iframe2_add.getContentPane().add(this.text2a_instruction, gbc_text2a_instruction);
			
	//LABELS
			GridBagConstraints gbc_lbl1_amount = new GridBagConstraints();
			gbc_lbl1_amount.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1_amount.gridx = 1;
			gbc_lbl1_amount.gridy = 1;
			this.panel_1.add(this.lbl1_amount, gbc_lbl1_amount);
			
			GridBagConstraints gbc_lbl1_unit = new GridBagConstraints();
			gbc_lbl1_unit.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1_unit.gridx = 2;
			gbc_lbl1_unit.gridy = 1;
			this.panel_1.add(this.lbl1_unit, gbc_lbl1_unit);
			
			GridBagConstraints gbc_lbl1_allergens = new GridBagConstraints();
			gbc_lbl1_allergens.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1_allergens.gridx = 3;
			gbc_lbl1_allergens.gridy = 1;
			this.panel_1.add(this.lbl1_allergens, gbc_lbl1_allergens);
				
			GridBagConstraints gbc_lbl1_expiration = new GridBagConstraints();
			gbc_lbl1_expiration.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1_expiration.gridx = 4;
			gbc_lbl1_expiration.gridy = 1;
			this.panel_1.add(this.lbl1_expiration, gbc_lbl1_expiration);
			
			GridBagConstraints gbc_lbl1a_name = new GridBagConstraints();
			gbc_lbl1a_name.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1a_name.gridx = 1;
			gbc_lbl1a_name.gridy = 1;
			this.iframe1_add.getContentPane().add(this.lbl1a_name, gbc_lbl1a_name);
			
			GridBagConstraints gbc_lbl1a_amount = new GridBagConstraints();
			gbc_lbl1a_amount.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1a_amount.gridx = 2;
			gbc_lbl1a_amount.gridy = 1;
			this.iframe1_add.getContentPane().add(this.lbl1a_amount, gbc_lbl1a_amount);
			
			GridBagConstraints gbc_lbl1a_unit = new GridBagConstraints();
			gbc_lbl1a_unit.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1a_unit.gridx = 3;
			gbc_lbl1a_unit.gridy = 1;
			this.iframe1_add.getContentPane().add(this.lbl1a_unit, gbc_lbl1a_unit);
			
			GridBagConstraints gbc_lbl1a_allergens = new GridBagConstraints();
			gbc_lbl1a_allergens.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1a_allergens.gridx = 4;
			gbc_lbl1a_allergens.gridy = 1;
			this.iframe1_add.getContentPane().add(this.lbl1a_allergens, gbc_lbl1a_allergens);
				
			GridBagConstraints gbc_lbl1a_expiration = new GridBagConstraints();
			gbc_lbl1a_expiration.insets = new Insets(0, 0, 5, 5);
			gbc_lbl1a_expiration.gridx = 5;
			gbc_lbl1a_expiration.gridy = 1;
			this.iframe1_add.getContentPane().add(this.lbl1a_expiration, gbc_lbl1a_expiration);
			
			GridBagConstraints gbc_lbl2a_name = new GridBagConstraints();
			gbc_lbl2a_name.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2a_name.gridx = 1;
			gbc_lbl2a_name.gridy = 1;
			this.iframe2_add.getContentPane().add(this.lbl2a_name, gbc_lbl2a_name);
			
			GridBagConstraints gbc_lbl2a_instruction = new GridBagConstraints();
			gbc_lbl2a_instruction.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2a_instruction.gridx = 5;
			gbc_lbl2a_instruction.gridy = 1;
			this.iframe2_add.getContentPane().add(this.lbl2a_instruction, gbc_lbl2a_instruction);
			
			GridBagConstraints gbc_lbl2a_ingredient = new GridBagConstraints();
			gbc_lbl2a_ingredient.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2a_ingredient.gridx = 2;
			gbc_lbl2a_ingredient.gridy = 1;
			this.iframe2_add.getContentPane().add(this.lbl2a_ingredient, gbc_lbl2a_ingredient);
			
			GridBagConstraints gbc_lbl2a_amount = new GridBagConstraints();
			gbc_lbl2a_amount.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2a_amount.gridx = 3;
			gbc_lbl2a_amount.gridy = 1;
			this.iframe2_add.getContentPane().add(this.lbl2a_amount, gbc_lbl2a_amount);
				
			GridBagConstraints gbc_lbl2a_unit = new GridBagConstraints();
			gbc_lbl2a_unit.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2a_unit.gridx = 4;
			gbc_lbl2a_unit.gridy = 2;
			this.iframe2_add.getContentPane().add(this.lbl2a_unit, gbc_lbl2a_unit);
			
			GridBagConstraints gbc_lbl2a_unit_header = new GridBagConstraints();
			gbc_lbl2a_unit_header.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2a_unit_header.gridx = 4;
			gbc_lbl2a_unit_header.gridy = 1;
			this.iframe2_add.getContentPane().add(this.lbl2a_unit_header, gbc_lbl2a_unit_header);
			
			GridBagConstraints gbc_lbl2_ingredients = new GridBagConstraints();
			gbc_lbl2_ingredients.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_ingredients.gridx = 1;
			gbc_lbl2_ingredients.gridy = 1;
			this.panel_2.add(this.lbl2_ingredients, gbc_lbl2_ingredients);
			
			GridBagConstraints gbc_lbl2_insturctions = new GridBagConstraints();
			gbc_lbl2_insturctions.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_insturctions.gridx = 2;
			gbc_lbl2_insturctions.gridy = 1;
			this.panel_2.add(this.lbl2_insturctions, gbc_lbl2_insturctions);
			
			GridBagConstraints gbc_lbl2_allergens = new GridBagConstraints();
			gbc_lbl2_allergens.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_allergens.gridx = 3;
			gbc_lbl2_allergens.gridy = 1;
			this.panel_2.add(this.lbl2_allergens, gbc_lbl2_allergens);

			GridBagConstraints gbc_lbl2_expiration = new GridBagConstraints();
			gbc_lbl2_expiration.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_expiration.gridx = 4;
			gbc_lbl2_expiration.gridy = 1;
			this.panel_2.add(this.lbl2_expiration, gbc_lbl2_expiration);
			
			GridBagConstraints gbc_lbl2_enough = new GridBagConstraints();
			gbc_lbl2_enough.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_enough.gridx = 5;
			gbc_lbl2_enough.gridy = 1;
			this.panel_2.add(this.lbl2_enough, gbc_lbl2_enough);
			
			GridBagConstraints gbc_lbl2_enoughValue = new GridBagConstraints();
			gbc_lbl2_enoughValue.anchor = GridBagConstraints.NORTH;
			gbc_lbl2_enoughValue.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_enoughValue.gridx = 5;
			gbc_lbl2_enoughValue.gridy = 2;
			this.panel_2.add(this.lbl2_enoughValue, gbc_lbl2_enoughValue);
			
			GridBagConstraints gbc_lbl2_filteringActive = new GridBagConstraints();
			gbc_lbl2_filteringActive.anchor = GridBagConstraints.NORTH;
			gbc_lbl2_filteringActive.insets = new Insets(0, 0, 5, 5);
			gbc_lbl2_filteringActive.gridx = 6;
			gbc_lbl2_filteringActive.gridy = 0;
			this.panel_2.add(this.lbl2_filteringActive, gbc_lbl2_filteringActive);
			
			GridBagConstraints gbc_lbl3_mustHave = new GridBagConstraints();
			gbc_lbl3_mustHave.fill = GridBagConstraints.HORIZONTAL;
			gbc_lbl3_mustHave.insets = new Insets(0, 0, 5, 5);
			gbc_lbl3_mustHave.gridx = 1;
			gbc_lbl3_mustHave.gridy = 1;
			this.panel_3.add(this.lbl3_mustHave, gbc_lbl3_mustHave);
			
			GridBagConstraints gbc_lbl3_noShopping = new GridBagConstraints();
			gbc_lbl3_noShopping.insets = new Insets(0, 0, 5, 5);
			gbc_lbl3_noShopping.gridx = 2;
			gbc_lbl3_noShopping.gridy = 1;
			this.panel_3.add(this.lbl3_noShopping, gbc_lbl3_noShopping);
			
			GridBagConstraints gbc_lbl3_avoidAllergens = new GridBagConstraints();
			gbc_lbl3_avoidAllergens.insets = new Insets(0, 0, 5, 5);
			gbc_lbl3_avoidAllergens.gridx = 3;
			gbc_lbl3_avoidAllergens.gridy = 1;
			this.panel_3.add(this.lbl3_avoidAllergens, gbc_lbl3_avoidAllergens);
		
			GridBagConstraints gbc_lbl3_sortBy = new GridBagConstraints();
			gbc_lbl3_sortBy.gridwidth = 2;
			gbc_lbl3_sortBy.insets = new Insets(0, 0, 5, 5);
			gbc_lbl3_sortBy.gridx = 4;
			gbc_lbl3_sortBy.gridy = 1;
			this.panel_3.add(this.lbl3_sortBy, gbc_lbl3_sortBy);
			
			GridBagConstraints gbc_lbl0_ip = new GridBagConstraints();
			gbc_lbl0_ip.insets = new Insets(0, 0, 5, 5);
			gbc_lbl0_ip.gridx = 1;
			gbc_lbl0_ip.gridy = 1;
			this.iframe0_settings.getContentPane().add(this.lbl0_ip, gbc_lbl0_ip);
			
			GridBagConstraints gbc_lbl0_port = new GridBagConstraints();
			gbc_lbl0_port.insets = new Insets(0, 0, 5, 5);
			gbc_lbl0_port.gridx = 2;
			gbc_lbl0_port.gridy = 1;
			this.iframe0_settings.getContentPane().add(this.lbl0_port, gbc_lbl0_port);
			
			GridBagConstraints gbc_lbl0_user = new GridBagConstraints();
			gbc_lbl0_user.insets = new Insets(0, 0, 5, 5);
			gbc_lbl0_user.gridx = 3;
			gbc_lbl0_user.gridy = 1;
			this.iframe0_settings.getContentPane().add(this.lbl0_user, gbc_lbl0_user);
			
			GridBagConstraints gbc_lbl0_pass = new GridBagConstraints();
			gbc_lbl0_pass.insets = new Insets(0, 0, 5, 5);
			gbc_lbl0_pass.gridx = 4;
			gbc_lbl0_pass.gridy = 1;
			this.iframe0_settings.getContentPane().add(this.lbl0_pass, gbc_lbl0_pass);
			
			GridBagConstraints gbc_lbl_connection = new GridBagConstraints();
			gbc_lbl_connection.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_connection.gridx = 4;
			gbc_lbl_connection.gridy = 8;
			getContentPane().add(this.lbl_connection, gbc_lbl_connection);
			
	//BUTTONS
			GridBagConstraints gbc_btn1_delete = new GridBagConstraints();
			gbc_btn1_delete.insets = new Insets(0, 0, 5, 0);
			gbc_btn1_delete.gridx = 6;
			gbc_btn1_delete.gridy = 1;
			this.panel_1.add(this.btn1_delete, gbc_btn1_delete);
			
			GridBagConstraints gbc_btn1_add = new GridBagConstraints();
			gbc_btn1_add.insets = new Insets(0, 0, 5, 0);
			gbc_btn1_add.gridx = 6;
			gbc_btn1_add.gridy = 2;
			this.panel_1.add(this.btn1_add, gbc_btn1_add);
			
			GridBagConstraints gbc_btn2_delete = new GridBagConstraints();
			gbc_btn2_delete.insets = new Insets(0, 0, 5, 0);
			gbc_btn2_delete.gridx = 6;
			gbc_btn2_delete.gridy = 1;
			this.panel_2.add(this.btn2_delete, gbc_btn2_delete);
			
			GridBagConstraints gbc_btn2_add = new GridBagConstraints();
			gbc_btn2_add.insets = new Insets(0, 0, 5, 0);
			gbc_btn2_add.gridx = 6;
			gbc_btn2_add.gridy = 2;
			this.panel_2.add(this.btn2_add, gbc_btn2_add);
			
			GridBagConstraints gbc_btn1a_ok = new GridBagConstraints();
			gbc_btn1a_ok.gridwidth = 2;
			gbc_btn1a_ok.insets = new Insets(0, 0, 5, 0);
			gbc_btn1a_ok.gridx = 6;
			gbc_btn1a_ok.gridy = 2;
			this.iframe1_add.getContentPane().add(this.btn1a_ok, gbc_btn1a_ok);

			GridBagConstraints gbc_btn1a_cancel = new GridBagConstraints();
			gbc_btn1a_cancel.insets = new Insets(0, 0, 5, 5);
			gbc_btn1a_cancel.gridx = 6;
			gbc_btn1a_cancel.gridy = 3;
			this.iframe1_add.getContentPane().add(this.btn1a_cancel, gbc_btn1a_cancel);
			
			GridBagConstraints gbc_btn2a_ok = new GridBagConstraints();
			gbc_btn2a_ok.gridwidth = 2;
			gbc_btn2a_ok.insets = new Insets(0, 0, 5, 0);
			gbc_btn2a_ok.gridx = 6;
			gbc_btn2a_ok.gridy = 2;
			this.iframe2_add.getContentPane().add(this.btn2a_ok, gbc_btn2a_ok);

			GridBagConstraints gbc_btn2a_cancel = new GridBagConstraints();
			gbc_btn2a_cancel.insets = new Insets(0, 0, 5, 5);
			gbc_btn2a_cancel.gridx = 6;
			gbc_btn2a_cancel.gridy = 4;
			this.iframe2_add.getContentPane().add(this.btn2a_cancel, gbc_btn2a_cancel);
			
			GridBagConstraints gbc_btn2a_ingredient = new GridBagConstraints();
			gbc_btn2a_ingredient.insets = new Insets(0, 0, 5, 5);
			gbc_btn2a_ingredient.gridx = 6;
			gbc_btn2a_ingredient.gridy = 3;
			this.iframe2_add.getContentPane().add(this.btn2a_more, gbc_btn2a_ingredient);
			
			GridBagConstraints gbc_btn3_filter = new GridBagConstraints();
			gbc_btn3_filter.gridx = 6;
			gbc_btn3_filter.gridy = 2;
			this.panel_3.add(this.btn3_filter, gbc_btn3_filter);
			
			GridBagConstraints gbc_btn3_resetFilter = new GridBagConstraints();
			gbc_btn3_resetFilter.gridx = 6;
			gbc_btn3_resetFilter.gridy = 3;
			this.panel_2.add(this.btn3_resetFilter, gbc_btn3_resetFilter);
			
			GridBagConstraints gbc_btn0_settings_open = new GridBagConstraints();
			gbc_btn0_settings_open.gridx = 1;
			gbc_btn0_settings_open.gridy = 8;
			getContentPane().add(this.btn0_settings_open, gbc_btn0_settings_open);
			
			GridBagConstraints gbc_btn0_settings_connect = new GridBagConstraints();
			gbc_btn0_settings_connect.gridx = 5;
			gbc_btn0_settings_connect.gridy = 2;
			this.iframe0_settings.getContentPane().add(this.btn0_settings_connect, gbc_btn0_settings_connect);
			
			GridBagConstraints gbc_btn0_settings_close = new GridBagConstraints();
			gbc_btn0_settings_close.gridx = 5;
			gbc_btn0_settings_close.gridy = 4;
			this.iframe0_settings.getContentPane().add(this.btn0_settings_close, gbc_btn0_settings_close);
			
			GridBagConstraints gbc_btn0_settings_save = new GridBagConstraints();
			gbc_btn0_settings_save.gridx = 2;
			gbc_btn0_settings_save.gridy = 8;
			getContentPane().add(this.btn0_settings_save, gbc_btn0_settings_save);
			
			GridBagConstraints gbc_btn0_settings_load = new GridBagConstraints();
			gbc_btn0_settings_load.gridx = 3;
			gbc_btn0_settings_load.gridy = 8;
			getContentPane().add(this.btn0_settings_load, gbc_btn0_settings_load);
			
			GridBagConstraints gbc_btn0_settings_test = new GridBagConstraints();
			gbc_btn0_settings_test.gridx = 5;
			gbc_btn0_settings_test.gridy = 3;
			this.iframe0_settings.getContentPane().add(this.btn0_settings_test, gbc_btn0_settings_test);
			
	//LISTS
			GridBagConstraints gbc_list1_ingredients = new GridBagConstraints();
			gbc_list1_ingredients.anchor = GridBagConstraints.NORTH;
			gbc_list1_ingredients.fill = GridBagConstraints.HORIZONTAL;
			gbc_list1_ingredients.insets = new Insets(0, 0, 0, 5);
			gbc_list1_ingredients.gridheight = 2;
			gbc_list1_ingredients.gridx = 6;
			gbc_list1_ingredients.gridy = 4;
			this.panel_1.add(this.list1_ingredients, gbc_list1_ingredients);
			
			GridBagConstraints gbc_list2_recipes = new GridBagConstraints();
			gbc_list2_recipes.anchor = GridBagConstraints.NORTH;
			gbc_list2_recipes.fill = GridBagConstraints.HORIZONTAL;
			gbc_list2_recipes.insets = new Insets(0, 0, 0, 5);
			gbc_list2_recipes.gridx = 6;
			gbc_list2_recipes.gridy = 4;
			this.panel_2.add(this.list2_recipes, gbc_list2_recipes);

			GridBagConstraints gbc_list3_mustHave = new GridBagConstraints();
			gbc_list3_mustHave.insets = new Insets(0, 0, 0, 5);
			gbc_list3_mustHave.fill = GridBagConstraints.HORIZONTAL;
			gbc_list3_mustHave.gridx = 1;
			gbc_list3_mustHave.gridy = 2;
			this.panel_3.add(this.list3_mustHave, gbc_list3_mustHave);
			
			GridBagConstraints gbc_list3_avoidAllergens = new GridBagConstraints();
			gbc_list3_avoidAllergens.insets = new Insets(0, 0, 0, 5);
			gbc_list3_avoidAllergens.fill = GridBagConstraints.HORIZONTAL;
			gbc_list3_avoidAllergens.gridx = 3;
			gbc_list3_avoidAllergens.gridy = 2;
			this.panel_3.add(this.list3_avoidAllergens, gbc_list3_avoidAllergens);
			
	//CHECKBOX
			GridBagConstraints gbc_chckbxNoShopping = new GridBagConstraints();
			gbc_chckbxNoShopping.insets = new Insets(0, 0, 0, 5);
			gbc_chckbxNoShopping.gridx = 2;
			gbc_chckbxNoShopping.gridy = 2;
			this.panel_3.add(this.chckbx3_noShopping, gbc_chckbxNoShopping);
	
	//RADIO BUTTONS
			GridBagConstraints gbc_rdbtn3_az = new GridBagConstraints();
			gbc_rdbtn3_az.insets = new Insets(0, 0, 0, 5);
			gbc_rdbtn3_az.gridx = 4;
			gbc_rdbtn3_az.gridy = 2;
			this.panel_3.add(this.rdbtn3_az, gbc_rdbtn3_az);

			GridBagConstraints gbc_rdbtn3_expiration = new GridBagConstraints();
			gbc_rdbtn3_expiration.insets = new Insets(0, 0, 0, 5);
			gbc_rdbtn3_expiration.gridx = 5;
			gbc_rdbtn3_expiration.gridy = 2;
			this.panel_3.add(this.rdbtn3_ex, gbc_rdbtn3_expiration);
	}//end method gbLayout

//GUI METHODS **************************************************************************************************************************
	
	//CLEAR FIELDS
		@SuppressWarnings("boxing")
		private void clearFields(int tabNumber){
			java.awt.Container container = null;
			switch (tabNumber){
			
				case 0: //settings
					container = iframe0_settings.getContentPane();
					break;
					
				case 1: //ingredients tab
					container = panel_1;
					break;
					
				case 2: //recipes tab
					container = panel_2;
					break;
					
				case 3: //filter tab
					container = panel_3;
					break;
					
				case 11: //add ingredient internal frame
					container = iframe1_add.getContentPane();
					break;
					
				case 21: //add recipe internal frame
					container = iframe2_add.getContentPane();
					this.btn2a_more.setEnabled(true);
					for (JComboBox<String> i : this.combx2a_temp){
						if (i != null) this.iframe2_add.remove(i);
					}
					for (JSpinner j : this.spin2a_temp){
						if (j != null) this.iframe2_add.remove(j);
					}
					for (JLabel k : this.lbl2a_temp){
						if (k != null) this.iframe2_add.remove(k);
					}
					break;
			}

			for (Component c : container.getComponents()){
				if (c instanceof JTextPane || c instanceof JTextField) ((javax.swing.text.JTextComponent)c).setText("");
				else if (c instanceof JSpinner){
					if(((JSpinner)c).getModel().getClass().getSimpleName().equals("SpinnerNumberModel")) ((JSpinner)c).setValue(0.0);
					else if(((JSpinner)c).getModel().getClass().getSimpleName().equals("SpinnerDateModel")) ((JSpinner)c).setValue(new Date());
				}
				else if (c instanceof JTable){
					((JTable)c).clearSelection();
					if(((JTable)c).getCellEditor() != null) ((JTable)c).getCellEditor().stopCellEditing();
					DefaultTableModel tableModel = (DefaultTableModel) ((JTable)c).getModel();
					int rightRowCount = tableModel.getRowCount();
					tableModel.setRowCount(0);
					tableModel.setRowCount(rightRowCount);
				}
				else if (c instanceof JList) ((JList)c).clearSelection();
				else if (c instanceof JCheckBox) ((JCheckBox)c).setSelected(false);
			}
		}//end method clearFields
		
	//REFRESH LIST
		private void refreshLists(){
			this.list1_ingredients.setListData(Storage.listIngredients().toArray(new String[0]));
			
			this.combx2a_ingredient.removeAllItems();
			for (String i : Storage.listIngredients()){
				this.combx2a_ingredient.addItem(i);
			}
			this.combx2a_ingredient.insertItemAt("Select", 0);
			this.combx2a_ingredient.setSelectedIndex(0);
			
			if(this.filteringActive == true){
				List<String> mustHave = this.list3_mustHave.getSelectedValuesList();
				List<String> avoidAllergens = this.list3_avoidAllergens.getSelectedValuesList();
				boolean enough = this.chckbx3_noShopping.isSelected();
				boolean sort = this.rdbtn3_az.isSelected();
				this.list2_recipes.setListData(Book.filterRecipes(mustHave, avoidAllergens, enough, sort));
			}
			else this.list2_recipes.setListData(Book.listRecipes().toArray(new String[0]));
			
			if (filteringActive == false){
				this.list3_mustHave.setListData(Storage.listIngredients().toArray(new String[0]));
				this.list3_avoidAllergens.setListData(Storage.listAllergens().toArray(new String[0]));
			}
		}//end method refreshList
		
	//REFRESH FIELDS
		@SuppressWarnings("boxing")
		private void refreshFields(){
			
			String iname = this.list1_ingredients.getSelectedValue();
			if (iname != null){
				if(Storage.hasIngredient(iname) == true){
					this.text1_unit.setText(Storage.getUnit(iname).toString());
					this.text1_amount.setText(((Double)new BigDecimal(Storage.getAmount(iname)).setScale(2, RoundingMode.HALF_UP).doubleValue()).toString());
					this.text1_allergens.setText(String.join(", ",Storage.getAllergens(iname)));
					this.text1_expiration.setText(DateFormat.getDateInstance().format(Storage.getExpiration(iname)));
				}
			}
			
			String rname = this.list2_recipes.getSelectedValue();
			if (rname != null){
				StringBuilder temp = new StringBuilder();
				
				for (String iname1 : Book.listIngredients(rname)){
					if (Book.hasIngredient(rname, iname1) == false) continue;
					temp.append(new BigDecimal(Book.getAmount(rname, iname1)).setScale(2, RoundingMode.HALF_UP).toString());
					temp.append(" ");
					temp.append(Storage.getUnit(iname1));
					temp.append(" ");
					temp.append(iname1);
					temp.append("\n");
				}
				
				this.text2_ingredients.setText(temp.toString().trim());
				this.text2_instruction.setText(Book.getInsturction(rname));
				this.text2_allergens.setText(String.join(", ", Book.getAllergens(rname)));
				this.text2_expiration.setText(String.join("",DateFormat.getDateInstance().format(Book.getExpiration(rname)[0])," ",(String) Book.getExpiration(rname)[1]));
				this.lbl2_enoughValue.setText(Boolean.toString(Book.getEnough(rname)));
			}
		}//end method refreshFields
	
	//TOGGLE PANELS
		private void togglePanels(boolean enable){
			for (Component c : this.panel_1.getComponents()){
				c.setEnabled(enable);
			}
			
			for (Component c : this.panel_2.getComponents()){
				c.setEnabled(enable);
			}
			
			for (Component c : this.panel_3.getComponents()){
				c.setEnabled(enable);
			}
			
			if (enable == true) this.btn3_resetFilter.setEnabled(this.filteringActive);
		}//end method togglePanels
		
		private void connection(){
			boolean established = Database.isConnected();
			GUI.this.btn0_settings_load.setEnabled(established);
			GUI.this.btn0_settings_save.setEnabled(established);
			GUI.this.btn0_settings_test.setEnabled(established);
			if (established == true) lbl_connection.setIcon(connected);
			else lbl_connection.setIcon(disconnected);
		}
		

//EVENT HANDLERS ***********************************************************************************************************************
		
	//LISTHANDLER
		private class ListHandler implements ListSelectionListener{
			
			@SuppressWarnings("synthetic-access")
			@Override
			public void valueChanged(ListSelectionEvent event) {
				refreshFields();
			}//end method valueChanged
		}//end class ListHandler
	
	//BUTTONHANDLER
		private class ButtonHandler implements ActionListener{
	
			@SuppressWarnings({ "synthetic-access", "boxing" })
			@Override
			public void actionPerformed(ActionEvent event) {
				Object source = event.getSource();
				
			//CLOSE
				if (source == GUI.this.btn1a_cancel){
					GUI.this.iframe1_add.setVisible(false);
					clearFields(11);
					togglePanels(true);
				}
				
				else if (source == GUI.this.btn2a_cancel){
					GUI.this.iframe2_add.setVisible(false);
					clearFields(21);
					GUI.this.counter2a=0;
					togglePanels(true);
				}
				
				else if(source == GUI.this.btn0_settings_close){
					GUI.this.iframe0_settings.setVisible(false);
					clearFields(0);
					GUI.this.btn0_settings_open.setEnabled(true);
					togglePanels(true);
				}
			
			//OPEN
				else if (source == GUI.this.btn1_add){
					togglePanels(false);
					GUI.this.iframe1_add.setVisible(true);
				}
				
				else if (source == GUI.this.btn2_add){
					togglePanels(false);
					GUI.this.iframe2_add.setVisible(true);
				}
				
				else if(source == GUI.this.btn0_settings_open){
					togglePanels(false);
					boolean connected = Database.isConnected();
					GUI.this.btn0_settings_load.setEnabled(connected);
					GUI.this.btn0_settings_save.setEnabled(connected);
					GUI.this.btn0_settings_test.setEnabled(connected);
					GUI.this.btn0_settings_open.setEnabled(false);
					GUI.this.iframe0_settings.setVisible(true);
				}
				
			//RADIO BUTTONS
				else if (source == GUI.this.rdbtn3_az){
					GUI.this.rdbtn3_az.setSelected(true);
					GUI.this.rdbtn3_ex.setSelected(false);
				}
				else if (source == GUI.this.rdbtn3_ex){
					GUI.this.rdbtn3_az.setSelected(false);
					GUI.this.rdbtn3_ex.setSelected(true);
				}
			
			//DELETE
				else if (source == GUI.this.btn1_delete){
					String iname = GUI.this.list1_ingredients.getSelectedValue();
					
					if (iname == null) {
						Dialogs.notice("You must first select an ingredient.");
						return;
					}

					TreeSet<String> temp = Book.whoHas(iname);
					if (temp.isEmpty() == false){
						if (Dialogs.warningOC("If you delete ingredient in here, it will also be deleted from all recipes.\nThe following recipes will be affected: "+temp.toString()) == 2) return;
					}
					Storage.deleteIngredient(iname);
					Book.deleteAllIngredients(iname);
					
					clearFields(1);
					refreshLists();
					refreshFields();
				}
				
				else if (source == GUI.this.btn2_delete){
					String rname = GUI.this.list2_recipes.getSelectedValue();
					
					if (rname == null){
						Dialogs.notice("You must first select a recipe.");
						return;
					}
					
					Book.deleteRecipe(rname);
					clearFields(2);
					refreshLists();
					
				}
				
			//FILTER
				else if (source == GUI.this.btn3_filter){
					GUI.this.filteringActive = true;
					
					clearFields(2);
					refreshLists();					
					
					GUI.this.btn3_resetFilter.setEnabled(true);
					GUI.this.lbl2_filteringActive.setVisible(true);
					GUI.this.tabbedPane.setSelectedComponent(GUI.this.panel_2);
				}
				
				else if (source == GUI.this.btn3_resetFilter){
					GUI.this.filteringActive = false;
					GUI.this.btn3_resetFilter.setEnabled(false);
					GUI.this.lbl2_filteringActive.setVisible(false);
					clearFields(2);
					clearFields(3);
					refreshLists();
				}
				
			//DATABASE
				else if(source == GUI.this.btn0_settings_connect){
					String ip = GUI.this.txtf0_ip.getText();
					String port = String.valueOf(GUI.this.spin0_port.getValue());
					String user = GUI.this.txtf0_user.getText();
					String pass = String.valueOf(GUI.this.txtf0_pass.getPassword());
					ArrayList<String> settings = new ArrayList<>();
					settings.add(ip); settings.add(port); settings.add(user); settings.add(pass);
					try {
						Settings.writeSettings(settings);
					} catch (Exception exception) {
						Dialogs.error("The application was unable to save your preferences, and they will be forgotten when you exit.\n"+exception);
					}
					Database.setup(ip, port, user, pass, "recipe_tool");
					try {
						RecipeTool.prepareDatabase();
						Dialogs.notice("Connected Successfully!");
					} catch (Exception exception) {
						Database.close();
						Dialogs.error("Database setup did not succeed.\n"+exception);
					} finally {
						connection();
					}
				}
				
				else if(source == GUI.this.btn0_settings_save){
					try {
						RecipeTool.save();
						Dialogs.notice("Saved Successfully!");
					} catch (Exception exception) {
						Database.close();
						Dialogs.error("Saving failed!\n"+exception);
					} finally {
						connection();
					}
				}
				
				else if(source == GUI.this.btn0_settings_load){
					try {
						RecipeTool.load();
						refreshLists();
						Dialogs.notice("Loaded Successfully!");
					} catch (Exception exception) {
						Database.close();
						Dialogs.error("Loading failed!\n"+exception);
					} finally {
						connection();
					}
				}
				
				else if(source == GUI.this.btn0_settings_test){
					try {
						RecipeTool.test();
						refreshLists();
						Dialogs.notice("Test Values Loaded Successfully!");
					} catch (Exception exception) {
						Database.close();
						Dialogs.error("Loading failed!\n"+exception);
					} finally {
						connection();
					}
				}
				
			//ADD
				else if (source == GUI.this.btn1a_ok){
					String name = GUI.this.txtf1a_name.getText();
					if (name.equals("")){
						Dialogs.notice("The name cannot be empty.");
						return;
					}
					if (Storage.hasIngredient(name)) if(Dialogs.warningOC("The ingredient called "+name+" already exists. Do you want to overwrite?") == 2) return;
					
					SpinnerNumberModel temp8 = (SpinnerNumberModel) GUI.this.spin1a_amount.getModel();
					double amount =temp8.getNumber().doubleValue();
					Unit unit = (Unit) GUI.this.combx1a_unit.getSelectedItem();
					TreeSet<String> allergen = new TreeSet<>();
					String temp;
					
					for (int i = 0; i<10; i++){
						temp = (String)GUI.this.table1a_allergens.getValueAt(i, 0);
						if (temp != null) allergen.add(temp);
					}	
					Date expiration = null;
					expiration = (Date) GUI.this.spin1a_expiration.getValue();
					if (GUI.this.table1a_allergens.isEditing()){
						CellEditor temp2 = GUI.this.table1a_allergens.getCellEditor();
						GUI.this.table1a_allergens.getCellEditor().stopCellEditing();
						String unfinished = (String) temp2.getCellEditorValue();
						if (unfinished != null) allergen.add(unfinished);
					}
					Storage.setIngredient(name, amount, unit, allergen, expiration);
					clearFields(11);
					refreshLists();
					
					togglePanels(true);
					GUI.this.iframe1_add.setVisible(false);
				}
				
				else if (source == GUI.this.btn2a_ok){
					
					String name = GUI.this.txtf2a_name.getText();
					if (name.equals("")){
						Dialogs.notice("The name cannot be empty.");
						return;
					}
					
					if (Book.hasRecipe(name)) if(Dialogs.warningOC("The recipe called "+name+" already exists. Do you want to overwrite?") == 2) return;
					String instruction = GUI.this.text2a_instruction.getText();
					LinkedHashMap<String, Double> ingredients = new LinkedHashMap<>();
					if (GUI.this.combx2a_ingredient.getSelectedIndex() != 0) ingredients.put( (String) GUI.this.combx2a_ingredient.getSelectedItem(),((SpinnerNumberModel) GUI.this.spin2a_amount.getModel()).getNumber().doubleValue());
					for (int i=1; i<=GUI.this.counter2a-1;i++){
						if (GUI.this.combx2a_temp[i].getSelectedIndex() != 0) ingredients.put( (String) GUI.this.combx2a_temp[i].getSelectedItem(), ((SpinnerNumberModel) GUI.this.spin2a_temp[i].getModel()).getNumber().doubleValue());
					}
					if (ingredients.isEmpty()){
						Dialogs.notice("Cannot create a recipe with no ingredients.");
						return;
					}
					Book.setRecipe(name, instruction, ingredients);
					
					clearFields(21);
					refreshLists();
					togglePanels(true);
					GUI.this.counter2a=0;
					GUI.this.iframe2_add.setVisible(false);
				}
				

			//TEMPORARY UI COMPONENTS
				else if (source == GUI.this.btn2a_more){
					if (GUI.this.counter2a<2) GUI.this.counter2a = 1;
					
					GUI.this.combx2a_temp[GUI.this.counter2a] = new JComboBox<>(Storage.listIngredients().toArray(new String[0]));
					GUI.this.combx2a_temp[GUI.this.counter2a].insertItemAt("Select", 0);
					GUI.this.combx2a_temp[GUI.this.counter2a].setSelectedIndex(0);
					GUI.this.combx2a_temp[GUI.this.counter2a].addActionListener(GUI.this.buttonhandler);
					
					GUI.this.gbc_combx2a_temp[GUI.this.counter2a] = new GridBagConstraints();
					GUI.this.gbc_combx2a_temp[GUI.this.counter2a].fill = GridBagConstraints.HORIZONTAL;
					GUI.this.gbc_combx2a_temp[GUI.this.counter2a].insets = new Insets(0, 0, 5, 5);
					GUI.this.gbc_combx2a_temp[GUI.this.counter2a].gridx = 2;
					GUI.this.gbc_combx2a_temp[GUI.this.counter2a].gridy = 2+GUI.this.counter2a;
					GUI.this.iframe2_add.getContentPane().add(GUI.this.combx2a_temp[GUI.this.counter2a], GUI.this.gbc_combx2a_temp[GUI.this.counter2a]);
					
					GUI.this.spin2a_temp[GUI.this.counter2a] = new JSpinner();
					GUI.this.spin2a_temp[GUI.this.counter2a].setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
					GUI.this.spin2a_temp[GUI.this.counter2a].setEditor(new JSpinner.NumberEditor(GUI.this.spin2a_temp[GUI.this.counter2a], "##0.0#"));
					
					GUI.this.gbc_spin2a_temp[GUI.this.counter2a] = new GridBagConstraints();
					GUI.this.gbc_spin2a_temp[GUI.this.counter2a].fill = GridBagConstraints.HORIZONTAL;
					GUI.this.gbc_spin2a_temp[GUI.this.counter2a].insets = new Insets(0, 0, 5, 5);
					GUI.this.gbc_spin2a_temp[GUI.this.counter2a].gridx = 3;
					GUI.this.gbc_spin2a_temp[GUI.this.counter2a].gridy = 2+GUI.this.counter2a;
					GUI.this.iframe2_add.getContentPane().add(GUI.this.spin2a_temp[GUI.this.counter2a], GUI.this.gbc_spin2a_temp[GUI.this.counter2a]);
					
					GUI.this.lbl2a_temp[GUI.this.counter2a] = new JLabel();
					GUI.this.gbc_lbl2a_temp[GUI.this.counter2a] = new GridBagConstraints();
					GUI.this.gbc_lbl2a_temp[GUI.this.counter2a].insets = new Insets(0, 0, 5, 5);
					GUI.this.gbc_lbl2a_temp[GUI.this.counter2a].gridx = 4;
					GUI.this.gbc_lbl2a_temp[GUI.this.counter2a].gridy = 2+GUI.this.counter2a;
					GUI.this.iframe2_add.getContentPane().add(GUI.this.lbl2a_temp[GUI.this.counter2a], GUI.this.gbc_lbl2a_temp[GUI.this.counter2a]);
					
					if(GUI.this.counter2a < 10) GUI.this.counter2a++;
					if (GUI.this.counter2a == 10) GUI.this.btn2a_more.setEnabled(false);
					GUI.this.iframe2_add.validate();
				}
				
			//INTERACTIVE UNIT CHANGING
				else if ((source == GUI.this.combx2a_ingredient) && ((GUI.this.combx2a_ingredient.getSelectedIndex() > 0))){
					GUI.this.lbl2a_unit.setText(Storage.getUnit(GUI.this.combx2a_ingredient.getSelectedItem().toString()).toString());
				}
				
				else {
					@SuppressWarnings("unchecked")
					JComboBox<String> temp = (JComboBox<String>) source;
					int j=0;
					for (JComboBox<String> i : GUI.this.combx2a_temp){
						if (temp.equals(i)){
							GUI.this.lbl2a_temp[j].setText(Storage.getUnit(i.getSelectedItem().toString()).toString());
							break;
						}
						j++;
					}
				}
			}//end method actionPerformed
		}//end class ButtonHandler
		
	//WINDOWHANDLER
		private class WindowHandler implements WindowListener{
			
			@Override
			public void windowClosing(WindowEvent e) {
				int answer = Dialogs.choiceYNC("You are exiting the application. Do you want to save changes to database?");
				switch (answer){
				
					case 0: //yes
						try {
							RecipeTool.save();
						} catch (Exception e1) {
							Database.close();
							if (Dialogs.warningYN("Saving failed. Do you still want to exit?\n"+e1) == 1) return;
							else System.exit(0);
						} finally{
							connection();
						}
						System.exit(0);
						break;
							
					case 1: //no
							System.exit(0);
						break;
						
					case 2: //cancel 
						return;
				}
			}//end method windowClosing
			@Override
			public void windowOpened(WindowEvent e) {/*not used*/}
			@Override
			public void windowClosed(WindowEvent e) {/*not used*/}
			@Override
			public void windowIconified(WindowEvent e) {/*not used*/}
			@Override
			public void windowDeiconified(WindowEvent e) {/*not used*/}
			@Override
			public void windowActivated(WindowEvent e) {/*not used*/}
			@Override
			public void windowDeactivated(WindowEvent e) {/*not used*/}
		}//end class WindowHandler
}//end class GUI


