package recipeTool;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeSet;

import javax.swing.AbstractButton;
import javax.swing.CellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;
import utilities.Database;
import utilities.DatabaseCommand;
import utilities.Dialogs;
import utilities.Settings;
import utilities.Unit;

/**
 * This class holds the Graphical User Interface.
 * 
 * @author Ville Salmela
 *
 */
class GUI extends JFrame { // package-private

	private static final long serialVersionUID = -3121708348469315637L;
	

	// CREATE
	// *******************************************************************************************************************************

	// CONTAINERS
	/**
	 * This container holds two tabs: ingredients and recipes.
	 */
	private final JTabbedPane tabbedPane = new JTabbedPane();
	
	/**
	 * This panel contains ingredient related UI components.
	 */
	private final JPanel panel_1 = new JPanel();
	
	/**
	 * This panel contains recipe related UI components.
	 */
	private final JPanel panel_2 = new JPanel();
	
	/**
	 * This panel holds filtering related UI components.
	 */
	private final JPanel panel_3 = new JPanel();
	
	/**
	 * This panel holds components displayed on the bottom of the window.
	 */
	private final JPanel panel_4 = new JPanel();
	
	/**
	 * This internal frame is used to add and modify ingredients.
	 */
	private final JInternalFrame iframe1_add = new JInternalFrame();
	
	/**
	 * This internal frame is used to add and modify recipes.
	 */
	private final JInternalFrame iframe2_add = new JInternalFrame();
	
	/**
	 * This internal frame is used to display and manipulate settings.
	 */
	private final JInternalFrame iframe0_settings = new JInternalFrame("Settings");

	/**
	 * This container array holds all the panels and internal frames.
	 * It is used to ease the iterating through these containers.
	 */
	private final java.awt.Container[] containerArrayAll;
	
	/**
	 * This container array holds all the internal frames.
	 * It is used to ease the iterating through these containers.
	 */
	private final java.awt.Container[] containerArrayIframes;
	
	/**
	 * This container array holds all the panels.
	 * It is used to ease the iterating through these containers.
	 */
	private final java.awt.Container[] containerArrayPanels;

	// LISTS
	private final JList<String> list1_ingredients = new JList<>();
	private final JList<String> list2_recipes = new JList<>();
	private final JList<String> list3_mustHave = new JList<>();
	private final JList<String> list3_avoidAllergens = new JList<>();
	
	private final JScrollPane scroll_ingredients = new JScrollPane(list1_ingredients);
	private final JScrollPane scroll_recipes  = new JScrollPane(list2_recipes);
	private final JScrollPane scroll_mustHave = new JScrollPane(list3_mustHave);
	private final JScrollPane scroll_avoidAllergens = new JScrollPane(list3_avoidAllergens);

	// TEXTAREAS
	private final JTextArea text1_amount = new JTextArea();
	private final JTextArea text1_unit = new JTextArea();
	private final JTextArea text1_allergens = new JTextArea();
	private final JTextArea text1_expiration = new JTextArea();
	private final JTextArea text2_ingredients = new JTextArea();
	private final JTextArea text2_instruction = new JTextArea();
	private final JTextArea text2_allergens = new JTextArea();
	private final JTextArea text2_expiration = new JTextArea();
	private final JTextArea text2a_instruction = new JTextArea();
	
	private final JScrollPane scroll_recipe1 = new JScrollPane(text2_ingredients);
	private final JScrollPane scroll_recipe2 = new JScrollPane(text2_instruction);

	// ICONS
	
	/**
	 * This icon is enabled, when application is connected to database.
	 */
	private final ImageIcon icon_database = new ImageIcon(GUI.class.getResource("/recipeTool/images/connected.png"),"Connected to database.");
	
	/**
	 * This icon is enabled, when application is using a local disk instead of database.
	 */
	private final ImageIcon icon_local = new ImageIcon(GUI.class.getResource("/recipeTool/images/disconnected.png"));
	
	/**
	 * This icon is displayed, when preparing the recipe requires some shopping first.
	 */
	private final ImageIcon icon_cart = new ImageIcon(GUI.class.getResource("/recipeTool/images/cart.png"));
	
	/**
	 * This icon is displayed, when there is enough ingredients in storage, to prepare the recipe right now.
	 */
	private final ImageIcon icon_cutlery = new ImageIcon(GUI.class.getResource("/recipeTool/images/cutlery.png"));

	// LABELS
	private final JLabel lbl1_amount = new JLabel("Amount");
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
	private final JLabel lbl2_enough = new JLabel("");
	private final JLabel lbl2_enoughValue = new JLabel("");
	private final JLabel lbl2_filteringActive = new JLabel("Filtering OFF");
	private final JLabel lbl2a_name = new JLabel("Name");
	private final JLabel lbl2a_instruction = new JLabel("Instruction");
	private final JLabel lbl2a_ingredient = new JLabel("Ingredient");
	private final JLabel lbl2a_amount = new JLabel("Amount");
	private final JLabel lbl2a_unit = new JLabel("");
	private final JLabel lbl2a_unit_header = new JLabel("Unit");
	private final JLabel lbl3_noShopping = new JLabel("Shopping?");
	private final JLabel lbl3_sortBy = new JLabel("Sort by");
	private final JLabel lbl0_ip = new JLabel("IP-address");
	private final JLabel lbl0_port = new JLabel("Port number");
	private final JLabel lbl0_user = new JLabel("Username");
	private final JLabel lbl0_pass = new JLabel("Password");
	private final JLabel lbl_database = new JLabel(icon_database);
	private final JLabel lbl_local = new JLabel(icon_local);

	// BUTTONS
	private final JButton btn1_add = new JButton("Add ingredient");
	private final JButton btn1_delete = new JButton("Delete ingredient");
	private final JButton btn1_modify = new JButton("Modify ingredient");
	private final JButton btn2_add = new JButton("Add recipe");
	private final JButton btn2_delete = new JButton("Delete recipe");
	private final JButton btn2_modify = new JButton("Modify recipe");
	private final JButton btn2_prepare = new JButton("Prepare recipe");
	private final JButton btn1a_ok = new JButton("Add to RecipeTool.getStorage()");
	private final JButton btn1a_cancel = new JButton("Cancel");
	private final JButton btn2a_ok = new JButton("Add to RecipeTool.getBook()");
	private final JButton btn2a_cancel = new JButton("Cancel");
	private final JButton btn2a_more = new JButton("Add Row");
	private final JButton btn2a_less = new JButton("Remove Row");
	private final JToggleButton btn3_filter = new JToggleButton("Filtering Switch");
	private final JButton btn0_settings_open = new JButton("Settings");
	private final JButton btn0_settings_connect = new JButton("Connect");
	private final JButton btn0_settings_disconnect = new JButton("Disconnect");
	private final JButton btn0_settings_close = new JButton("Close settings");
	private final JButton btn0_settings_save = new JButton("Save");
	private final JButton btn0_settings_load = new JButton("Load");
	private final JButton btn0_settings_test = new JButton("Load Test Values");
	private final JButton btn0_settings_pref = new JButton("Save preferences");
	private final JButton btn0_settings_default = new JButton("Use default connection");

	// CHECKBOXES
	private final JCheckBox chckbx3_noShopping = new JCheckBox("No Shopping");
	private final JCheckBox chckbx1a_noExpiry = new JCheckBox("Does not expire");
	private final JCheckBox chckbx0_allowDB = new JCheckBox("Allow Connection to Database");

	// RADIO BUTTONS
	private final JRadioButton rdbtn3_az = new JRadioButton("A-Z");
	private final JRadioButton rdbtn3_ex = new JRadioButton("Expiration date");

	// TEXTFIELDS
	private final JTextField txtf1a_name = new JTextField("");
	private final JTextField txtf2a_name = new JTextField("");
	private final JTextField txtf0_ip = new JTextField("");
	private final JTextField txtf0_user = new JTextField("");
	private final JPasswordField txtf0_pass = new JPasswordField("");
	// TABLE
	private final JTable table1a_allergens = new JTable();

	// SPINNERS
	private final JSpinner spin1a_amount = new JSpinner();
	private final JSpinner spin1a_expiration = new JSpinner();
	private final JSpinner spin2a_amount = new JSpinner();
	private final JSpinner spin0_port = new JSpinner();

	// COMBOBOXES
	@SuppressWarnings("unchecked")
	private final JComboBox<Unit> combx1a_unit = new JComboBox(Unit.values());
	private final JComboBox<String> combx2a_ingredient = new JComboBox<>();

	// TEMPORARY UI
	@SuppressWarnings("unchecked")
	private final JComboBox<String>[] combx2a_temp = new JComboBox[10];
	private final JSpinner[] spin2a_temp = new JSpinner[10];
	private final JLabel[] lbl2a_temp = new JLabel[10];

	// STATE
	
	/**
	 * This field holds the current state of filtering.<br>
	 * True; if filtering is active<br>
	 * False; if it's not.
	 */
	private boolean state_filteringActive = false;
	
	/**
	 * This field holds the current user preference of database use.<br>
	 * True; if user allows the connection to database<br>
	 * False; if user denies the connection to database 
	 */
	private boolean state_allowDB;
	
	/**
	 * This counter holds the current number of rows in internal frame,
	 * where recipe adding/modifying takes place.
	 */
	private int state_counter2a = 0;

	// HANDLERS
	private final ButtonHandler handler_button = new ButtonHandler();
	private final ListHandler handler_list = new ListHandler();
	private final WindowHandler handler_window = new WindowHandler();
	private final TextHandler handler_text = new TextHandler();

	// CONSTRUCTOR
	// **************************************************************************************************************************
	/**
	 * This constructor will populate some fields, determine whether to use database,
	 * set up the visual layout, configure components and finally check the connection.
	 */
	GUI() {
		super("recipeTool");
		setContentPane(new JDesktopPane());

		// Create container arrays, for ease of iterating
		containerArrayAll = new java.awt.Container[] { panel_1, panel_2, panel_3, panel_4,
				iframe1_add.getContentPane(), iframe2_add.getContentPane(),
				iframe0_settings.getContentPane() };
		containerArrayIframes = new java.awt.Container[] { iframe0_settings.getContentPane(),
				iframe1_add.getContentPane(), iframe2_add.getContentPane() };
		containerArrayPanels = new java.awt.Container[] { panel_1, panel_2, panel_3, panel_4 };
		
			
			// Get saving/loading policy
			state_allowDB  = RecipeTool.getSavePolicy();

		// Setup the layout
		migLayout();
		
		// Configure components
		config();
		
		// Test the connection and adjust GUI accordingly
		testConnection();

	}// end constructor

	// MIGLAYOUT
	// ***********************************************************************************************************************
	/**
	 * This method will setup the Graphical User Interface layout.
	 */
	private void migLayout() {
		
		// ROOT
		Container root = getContentPane();
		root.setLayout(new MigLayout("fill,insets 0"));
		root.add(tabbedPane, "north, hmax 90%, hmin 90%, grow 0");
		root.add(panel_4, "south, hmin 10%, hmax 10%");
		root.add(iframe1_add, "pos 20% 20%");
		root.add(iframe2_add, "pos 20% 20%");
		root.add(iframe0_settings, "pos 20% 20%");

		Container c;
		
		// Ingredients
		c = panel_1;
		c.setLayout(new MigLayout("flowy, filly",
				"[align right,10% | align left, 20%,fill | align left, 20%,fill | align left]", "[10%, top|10%, top|10%, top|70%, top]"));
		c.add(lbl1_amount);
		c.add(lbl1_unit);
		c.add(lbl1_allergens);
		c.add(lbl1_expiration, "wrap");
		c.add(text1_amount);
		c.add(text1_unit);
		c.add(text1_allergens);
		c.add(text1_expiration, "wrap");
		c.add(scroll_ingredients, "grow, spany 4,wrap");
		c.add(btn1_add, "split 3, spany 3");
		c.add(btn1_delete);
		c.add(btn1_modify);
		
		// Recipes
		c = panel_2;
		c.setLayout(new MigLayout("flowy,fillx, align left,insets panel",
				"[align right,10% | align left,20%,fill | align left,20%,fill | align left | growprio 1000]", "[35%, top|35%, top|10%, top|10%, top|10%, top]"));
		c.add(panel_3, "dock east,wmin 30%,wmax 30%,grow");
		c.add(lbl2_ingredients);
		c.add(lbl2_insturctions);
		c.add(lbl2_enough,"aligny center");
		c.add(lbl2_allergens);
		c.add(lbl2_expiration, "wrap");
		c.add(scroll_recipe1,"grow, hmax 35%");
		c.add(scroll_recipe2,"grow, hmax 35%");
		c.add(lbl2_enoughValue,"aligny center");
		c.add(text2_allergens);
		c.add(text2_expiration, "wrap");
		c.add(scroll_recipes, "grow,spany 5,wrap");
		c.add(btn2_add, "split 4, spany 1");
		c.add(btn2_delete);
		c.add(btn2_modify);
		c.add(btn2_prepare, "wrap");

		// Filtering
		c = panel_3;
		c.setLayout(new MigLayout("flowy,insets panel", "[50%,fill]", "[35%,fill|35%,fill|||]"));
		c.setBackground(Color.WHITE);
		c.add(scroll_mustHave, "grow,spanx 2");
		c.add(scroll_avoidAllergens, "grow,spanx 2");
		c.add(lbl3_noShopping);
		c.add(lbl3_sortBy);
		c.add(btn3_filter, "span 2");
		c.add(lbl2_filteringActive, "span 2,grow 0,center,wrap");
		c.add(chckbx3_noShopping);
		c.add(rdbtn3_az, "spanx 2, split 2");
		c.add(rdbtn3_ex);

		// Bottom-panel
		c = panel_4;
		c.setLayout(new MigLayout("","",""));
		c.add(btn0_settings_open);
		c.add(btn0_settings_load);
		c.add(btn0_settings_save);
		c.add(lbl_database);
		c.add(lbl_local);

		// Settings
		c = iframe0_settings.getContentPane();
		c.setLayout(new MigLayout("", "[4cm,fill|2cm,fill|4cm,fill|4cm,fill]", "top"));
		c.add(lbl0_ip);
		c.add(lbl0_port);
		c.add(lbl0_user);
		c.add(lbl0_pass, "wrap");
		c.add(txtf0_ip);
		c.add(spin0_port);
		c.add(txtf0_user);
		c.add(txtf0_pass, "wrap");
		c.add(btn0_settings_connect, "span 4, split 4");
		c.add(btn0_settings_disconnect);
		c.add(btn0_settings_test);
		c.add(btn0_settings_close, "wrap");
		c.add(chckbx0_allowDB);
		c.add(btn0_settings_pref);
		c.add(btn0_settings_default);
		
		// Add/Modify Ingredients
		c = iframe1_add.getContentPane();
		c.setLayout(new MigLayout("", "[5cm,fill|3cm,fill|1cm,fill|5cm,fill|3cm,fill]", "top"));
		c.add(lbl1a_name);
		c.add(lbl1a_amount);
		c.add(lbl1a_unit);
		c.add(lbl1a_allergens);
		c.add(lbl1a_expiration, "wrap");
		c.add(txtf1a_name);
		c.add(spin1a_amount);
		c.add(combx1a_unit);
		c.add(table1a_allergens);
		c.add(spin1a_expiration);
		c.add(chckbx1a_noExpiry, "wrap");
		c.add(btn1a_ok, "span 2, split 2");
		c.add(btn1a_cancel);
		
		// Add/Modify Recipes
		c = iframe2_add.getContentPane();
		c.setLayout(new MigLayout("", "[5cm,grow 0|7cm,grow 0|5cm|2cm|1cm]", "top"));
		c.add(lbl2a_name);
		c.add(lbl2a_instruction);
		c.add(lbl2a_ingredient);
		c.add(lbl2a_amount);
		c.add(lbl2a_unit_header, "wrap");
		c.add(txtf2a_name, "growx");
		c.add(text2a_instruction, "growx");
		c.add(combx2a_ingredient, "growx");
		c.add(spin2a_amount, "growx");
		c.add(lbl2a_unit, "wrap");
		c.add(btn2a_more, "span 2,split 2,grow 0,left");
		c.add(btn2a_less, "wrap,grow 0");
		c.add(btn2a_ok, "span 2,split 2,grow 0,left");
		c.add(btn2a_cancel, "grow 0");

	}// end method migLayout

	// CONFIG
	// *******************************************************************************************************************************
	/**
	 * This method will perform the necessary configuration for components
	 */
	private void config() {

		/*
		 * Root
		 * 
		 * Set size and location of the window, and gain manual control of closing.
		 */
		setResizable(false);
		setBounds(0, 0, 1200, 800);
		setLocationRelativeTo(null);
		
		addWindowListener(handler_window);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		/*
		 * Tabs
		 * 
		 * Create tabs, and assign panels to them
		 */
		tabbedPane.addTab("Ingredients", null, panel_1, null);
		tabbedPane.addTab("Recipes", null, panel_2, null);

		/*
		 * Radio buttons
		 * 
		 * Set default selection state
		 */
		rdbtn3_az.setSelected(true);
		rdbtn3_ex.setSelected(false);

		/*
		 * Labels
		 * 
		 * Change font for all labels.
		 * Set tooltips for icons.
		 */
		final Font font14 = new Font("Tahoma", Font.BOLD, 14);

		for (Component c : tabbedPane.getComponents()) {
			for (Component c2 : ((JPanel) c).getComponents()) {
				if (c2 instanceof JLabel)
					((JLabel) c2).setFont(font14);
			}
		}
		lbl2_filteringActive.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		lbl_database.setToolTipText("Using database");
		lbl_local.setToolTipText("Using local filesystem");

		/*
		 * Lists
		 * 
		 * Set selection modes, borders and titles for all lists.
		 * Add listeners for ingredient and recipe lists.
		 */
		list1_ingredients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2_recipes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list3_avoidAllergens.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list3_mustHave.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		list1_ingredients.setBorder(new TitledBorder(null, "Ingredients", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("Tahoma", Font.BOLD, 18), null));
		list2_recipes.setBorder(new TitledBorder(null, "Recipes", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("Tahoma", Font.BOLD, 18), null));
		list3_mustHave.setBorder(new TitledBorder(null, "Must have ingredients", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 18), null));
		list3_avoidAllergens.setBorder(new TitledBorder(null, "Avoid allergens", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 18), null));

		list1_ingredients.addListSelectionListener(handler_list);
		list2_recipes.addListSelectionListener(handler_list);

		/*
		 * Buttons
		 * 
		 * Add listeners for all buttons and combo boxes.
		 */
		for (java.awt.Container c : containerArrayAll) {
			for (Component c2 : c.getComponents()) {
				if (c2 instanceof AbstractButton)
					((AbstractButton) c2).addActionListener(handler_button);
				else if (c2 instanceof JComboBox)
					((JComboBox<?>) c2).addActionListener(handler_button);
			}
		}

		/*
		 * Color
		 * 
		 * Color everything white, except for buttons.
		 */
		for (java.awt.Container c : containerArrayAll) {
			c.setBackground(Color.WHITE);
			for (Component c2 : c.getComponents()) {
				if (!(c2 instanceof AbstractButton) || (c2 instanceof JRadioButton) || (c2 instanceof JCheckBox))
					c2.setBackground(Color.WHITE);
			}

		}

		/*
		 * Text areas
		 * 
		 * Make internal frame text areas editable, others not editable.
		 * Configure borders and line wrapping for all.
		 * Add listeners for internal frame text areas, to handle resizing.
		 */
		for (Container c : containerArrayAll) {
			for (Component c2 : c.getComponents()) {
				if (c2 instanceof JScrollPane) c2 = ((JScrollPane)c2).getViewport().getView();
				if (c2 instanceof JTextArea) {
					((JTextArea) c2).setEditable(false);
					((JTextArea) c2).setLineWrap(true);
					((JTextArea) c2).setWrapStyleWord(true);
					((JTextArea) c2).setBorder(new LineBorder(Color.BLACK));
				}
			}
		}

		for (Container c : containerArrayIframes) {
			for (Component c2 : c.getComponents()) {
				if (c2 instanceof JTextArea) {
					((JTextArea) c2).addComponentListener(handler_text);
					((JTextArea) c2).setEditable(true);
				}
			}
		}

		/*
		 * Spinners
		 * 
		 * Configure incrementing, start, min and max values, and display format.
		 */
		
		// Start and min 0, max 100 000, increment 0.01
		spin1a_amount.setModel(new SpinnerNumberModel(0.0, 0.0, 100000.0, 0.01));
		spin1a_amount.setEditor(new JSpinner.NumberEditor(spin1a_amount, "#####0.0#"));

		// Start today, min 1.1.2000, max 31.12.3000, increment 1 day
		spin1a_expiration.setModel(new SpinnerDateModel(new Date(), new Date(946677600921L),
				new Date(32535122400921L), Calendar.DAY_OF_YEAR));
		spin1a_expiration.setEditor(new JSpinner.DateEditor(spin1a_expiration, "d.M.y"));
		
		// Start and min 0, max 100 000, increment 0.01
		spin2a_amount.setModel(new SpinnerNumberModel(0.0, 0.0, 100000.0, 0.01));
		spin2a_amount.setEditor(new JSpinner.NumberEditor(spin2a_amount, "#####0.0#"));

		// Start and min 0, max 65535, increment 1
		spin0_port.setModel(new SpinnerNumberModel(0, 0, 65535, 1));

		/*
		 * Table
		 * 
		 * Configure the table to have 10 rows and 1 column, with black borders.
		 */
		DefaultTableModel temp = new DefaultTableModel(10, 1);
		table1a_allergens.setModel(temp);
		table1a_allergens.setBorder(new LineBorder(Color.BLACK));

	}// end method config

	// GUI METHODS
	// **************************************************************************************************************************

	// TOGGLE PANELS
	/**
	 * This method will enable/disable all components on all panels found in
	 * {@link recipeTool.GUI#containerArrayPanels}, except for labels.
	 * 
	 * @param enable
	 *            {@code true} to enable all panels<br>
	 *            <blockquote>{@code false} to disable all panels</blockquote>
	 */
	private void togglePanels(boolean enable) {
		
		// For every container in array
		for (Container c : containerArrayPanels) {
			// For every component
			for (Component c2 : c.getComponents()) {
				
				// If the component is scroll pane, retrieve its content
				if (c2 instanceof JScrollPane) c2 = ((JScrollPane)c2).getViewport().getView();
				
				// If the component is not label, enable/disable it
				if(!(c2 instanceof JLabel)) c2.setEnabled(enable);
			}
		}
	}// end method togglePanels

	/**
	 * This method will test if there is a valid connection established to a database.<br>
	 * The method does the checking using {@link utilities.Database#isConnected()}
	 * <p>
	 * Connection Icon is set and connection buttons are enabled/disabled accordingly.
	 */
	private void testConnection() {
		
		// Check if database connection is open and valid
		boolean established = Database.isConnected();
		
		// If user has allowed database connections
		if (state_allowDB) {
			
			// When connected, enable disconnect-button and disable connect-button. Also vice versa.
			btn0_settings_connect.setEnabled(!established);
			btn0_settings_disconnect.setEnabled(established);
			
			// When connected, use database, else use local disk
			RecipeTool.setSavePolicy(established);
		
		// If user denied database connections
		} else {
			
			// Disable database-related buttons
			btn0_settings_connect.setEnabled(false);
			btn0_settings_disconnect.setEnabled(false);
			
			// Use local disk
			RecipeTool.setSavePolicy(false);
		}
		
		// Configure UI to match user selection
		chckbx0_allowDB.setSelected(state_allowDB);
		btn0_settings_default.setEnabled(state_allowDB);
		lbl0_ip.setEnabled(state_allowDB);
		lbl0_port.setEnabled(state_allowDB);
		lbl0_user.setEnabled(state_allowDB);
		lbl0_pass.setEnabled(state_allowDB);
		txtf0_ip.setEnabled(state_allowDB);
		spin0_port.setEnabled(state_allowDB);
		txtf0_user.setEnabled(state_allowDB);
		txtf0_pass.setEnabled(state_allowDB);
		
		// Configure icons to match connection state
		lbl_database.setEnabled(established);
		lbl_local.setEnabled(!established);
	}// end method testConnection

	/**
	 * This method will reset specified GUI components back to their original states.<br>
	 * The GUI is divided into segments, which are mostly based on containers. See: {@link recipeTool.Segment}
	 * <p>
	 * 6 different segments are supported by this method:<br>
	 * -CONTAINER_SETTINGS<br>
	 * -CONTAINER_INGREDIENTS<br>
	 * -CONTAINER_RECIPES<br>
	 * -CONTAINER_FILTER<br>
	 * -CONTAINER_ADD_INGREDIENT<br>
	 * -CONTAINER_ADD_RECIPE
	 * 
	 * @param segments GUI segments, which should be cleared.
	 */
	private void clearFields(Segment... segments) {
		
		// If invoked with no arguments, this method will do nothing
		if(segments.length == 0) return;
		
		// Prepare variables
		Container container = null;
		Dimension prefSize;
		
		// For every segment, which is passed to this method,
		// set container to a variable
		for (Segment z : segments){
			switch (z) {
	
			case CONTAINER_SETTINGS:
				container = iframe0_settings.getContentPane();
				break;
	
			case CONTAINER_INGREDIENTS:
				container = panel_1;
				break;
	
			case CONTAINER_RECIPES:
				container = panel_2;
				
				// Clear 2 labels
				lbl2_enough.setIcon(null);
				lbl2_enoughValue.setText("");
				
				break;
	
			case CONTAINER_FILTER:
				container = panel_3;
				break;
	
			case CONTAINER_ADD_INGREDIENT:
				container = iframe1_add.getContentPane();
				break;
				
			case CONTAINER_ADD_RECIPE:
				container = iframe2_add.getContentPane();
				
				// Enable more-button, disable less-button
				btn2a_more.setEnabled(true);
				btn2a_less.setEnabled(false);
				
				// Delete all temporary combo boxes
				for (JComboBox<?> i : combx2a_temp) {
					if (i != null)
						iframe2_add.remove(i);
				}
				
				// Delete all temporary spinners
				for (JSpinner j : spin2a_temp) {
					if (j != null)
						iframe2_add.remove(j);
				}
				
				// Delete all temporary labels
				for (JLabel k : lbl2a_temp) {
					if (k != null)
						iframe2_add.remove(k);
				}
				break;
			
			// If segment is not supported, ignore it
			default: break;
			} // end switch
			
			// If last segment was not supported, go back to start and try another one
			if (container == null) continue;
	
			// Clear every component in selected container
			for (Component c : container.getComponents()) {
				
				// First, if component is inside a scroll pane get it out of there
				if (c instanceof JScrollPane){
					c = ((JScrollPane)c).getViewport().getView();
				}
				
				// Clear text areas
				if (c instanceof JTextArea || c instanceof JTextField)
					((JTextComponent) c).setText("");
				
				// Clear spinners
				else if (c instanceof JSpinner) {
					
					// Numbers to zero
					if (((JSpinner) c).getModel().getClass().getSimpleName().equals("SpinnerNumberModel"))
						((JSpinner) c).setValue(0.0);
					
					// Dates to today
					else if (((JSpinner) c).getModel().getClass().getSimpleName().equals("SpinnerDateModel"))
						((JSpinner) c).setValue(new Date());
						((JSpinner)c).setEnabled(true);
				}
				
				// Clear tables
				else if (c instanceof JTable) {
					
					// If table was left in editing mode, stop it
					((JTable) c).clearSelection();
					if (((JTable) c).getCellEditor() != null)
						((JTable) c).getCellEditor().stopCellEditing();
					DefaultTableModel tableModel = (DefaultTableModel) ((JTable) c).getModel();
					
					// Mark down the right row count, then destroy all rows and build them again
					int rightRowCount = tableModel.getRowCount();
					tableModel.setRowCount(0);
					tableModel.setRowCount(rightRowCount);
				} 
				
				// Clear lists
				else if (c instanceof JList)
					((JList<?>) c).clearSelection();
				
				// Clear check boxes
				else if (c instanceof JCheckBox)
					((JCheckBox) c).setSelected(false);
				
				// Clear combo boxes
				else if (c instanceof JComboBox) ((JComboBox<?>)c).setSelectedIndex(0);
			}
	
			// If container is internal frame, then reset the size back to original
			if (z.equals(Segment.CONTAINER_SETTINGS) || z.equals(Segment.CONTAINER_ADD_INGREDIENT) || z.equals(Segment.CONTAINER_ADD_RECIPE)){
				Container iframe = container.getParent().getParent().getParent();
				prefSize = iframe.getLayout().preferredLayoutSize(rootPane);
				iframe.setPreferredSize(prefSize);
				iframe.revalidate();
			}
		}// end loop
	}// end method clearFields

	/**
	 * This method will populate specified GUI components with new updated information.
	 * The GUI is divided into segments, which are mostly based on containers. See: {@link recipeTool.Segment}
	 * <p>
	 * 5 different segments are supported by this method:<br>
	 * -CONTAINER_SETTINGS<br>
	 * -CONTAINER_INGREDIENTS<br>
	 * -CONTAINER_RECIPES<br>
	 * -CONTAINER_ADD_INGREDIENT<br>
	 * -CONTAINER_ADD_RECIPE
	 * @param segments GUI segments, which should be updated with new information
	 */
	private void refreshFields(Segment... segments) {
		
		// If invoked with no arguments, this method will do nothing
		if (segments.length == 0) return;
		
		// For every segment, which is passed to this method
		for (Segment z : segments){
			switch(z){
			
			case CONTAINER_SETTINGS:
				
				// Read settings from file to string array
				String[] temp2 = Settings.readSettings();
				
				// If the reading was successful
				if ((temp2 != null) && (temp2.length >= 5)) {
					
					// Fill IP
					txtf0_ip.setText(temp2[0]);
					
					// If port is correctly integer, fill it
					try {
						
						// Validate port
						if (Integer.valueOf(temp2[1]) < 0 || Integer.valueOf(temp2[1]) > 65535)
							throw new IllegalArgumentException();
						
						// Fill port
						spin0_port.setValue(Integer.valueOf(temp2[1]));
					
					} catch (Exception exception) {
						
						// If port settings is invalid, put 0
						spin0_port.setValue(0);
					}
					
					// Fill user name
					txtf0_user.setText(temp2[2]);
					
					// Fill password
					txtf0_pass.setText(temp2[3]);
				}
				break;
				
			case CONTAINER_INGREDIENTS:
				
				// Read selected ingredient into a string
				String iname = list1_ingredients.getSelectedValue();
				
				// Validate ingredient
				if ((iname != null) && (RecipeTool.getStorage().hasIngredient(iname))) {
					
					// Fill unit
					text1_unit.setText(RecipeTool.getStorage().getUnit(iname).toString());
					
					// Format amount (0.00) and put it
					text1_amount.setText(
							((Double) new BigDecimal(RecipeTool.getStorage().getAmount(iname)).setScale(2, RoundingMode.HALF_UP).doubleValue())
									.toString());
		
					// If there are no allergens
					if (RecipeTool.getStorage().getAllergens(iname) == null)
						
						// Display it
						text1_allergens.setText("no allergens");
					else
						
						// Otherwise join all allergens into one string and put it
						text1_allergens.setText(String.join(", ", RecipeTool.getStorage().getAllergens(iname)));
		
					// If there is no expiration date
					if (RecipeTool.getStorage().getExpiration(iname) == null)
						
						// Display it
						text1_expiration.setText("does not expire");
					else
						
						// Otherwise format date and put it
						text1_expiration.setText(DateFormat.getDateInstance().format(RecipeTool.getStorage().getExpiration(iname)));
				}
				break;
				
			case CONTAINER_RECIPES:
				
				// Read recipe name into a string
				String rname = list2_recipes.getSelectedValue();
				
				// Validate recipe
				if ((rname != null) && (RecipeTool.getBook().hasRecipe(rname))) {
					
					// Create new string builder
					StringBuilder temp = new StringBuilder();
					
					/*
					 * 0.00 KG Flour
					 * 0.00 PCS Eggs
					 * ...
					 */
					for (String iname1 : RecipeTool.getBook().listIngredients(rname)) {
						temp.append(new BigDecimal(RecipeTool.getBook().getAmount(rname, iname1)).setScale(2, RoundingMode.HALF_UP).toString());
						temp.append(" ");
						temp.append(RecipeTool.getStorage().getUnit(iname1));
						temp.append(" ");
						temp.append(iname1);
						temp.append("\n");
					}
					
					// Trim any excess white space, then put the string into the text area
					text2_ingredients.setText(temp.toString().trim());
		
					// Check if there is an instruction
					if (RecipeTool.getBook().getInsturction(rname) == null || RecipeTool.getBook().getInsturction(rname).equals(""))
						
						// If not
						text2_instruction.setText("no instruction");
					else
						
						// If yes
						text2_instruction.setText(RecipeTool.getBook().getInsturction(rname));
		
					// Check if there are allergens
					if (RecipeTool.getBook().getAllergens(rname) == null || RecipeTool.getBook().getAllergens(rname).isEmpty())
						
						// If not
						text2_allergens.setText("no allergens");
					else
						
						// If yes
						text2_allergens.setText(String.join(", ", RecipeTool.getBook().getAllergens(rname)));
		
					
					// Check if there are ingredients with expiration date
					if (RecipeTool.getBook().getExpiration(rname)[0] == null)
						
						// If not
						text2_expiration.setText("no expiring ingredients");
					else
						
						// If yes, display the ingredient with soonest expiration date
						// by joining two strings, for example: Milk 1.1.2000
						text2_expiration
								.setText(String.join("", DateFormat.getDateInstance().format(RecipeTool.getBook().getExpiration(rname)[0]), " ",
										(String) RecipeTool.getBook().getExpiration(rname)[1]));
					
					// Check if there are enough ingredients in storage, to prepare the recipe at least once
					// Change label and icon accordingly
					lbl2_enoughValue.setText(RecipeTool.getBook().isEnough(rname) ? "Ready to cook" : "Go Shopping");
					lbl2_enough.setIcon(RecipeTool.getBook().isEnough(rname) ? icon_cutlery : icon_cart);
				}
				break;
				
			case CONTAINER_MODIFY_INGREDIENT:
				
				// Set container title
				iframe1_add.setTitle("Modify Ingredient");
				
				// Set ok button text
				btn1a_ok.setText("Modify");
				
				// Read selected ingredient name into a string
				iname = list1_ingredients.getSelectedValue();
				
				// Read ingredient properties from storage and pre-populate UI components
				txtf1a_name.setText(iname);
				spin1a_amount.setValue(RecipeTool.getStorage().getAmount(iname));
				combx1a_unit.setSelectedItem(RecipeTool.getStorage().getUnit(iname));
				
				// If there is no expiration date, disable spinner and set checkbox
				if (RecipeTool.getStorage().getExpiration(iname) == null){
					chckbx1a_noExpiry.setSelected(true);
					spin1a_expiration.setEnabled(false);
				}
				
				// Else uncheck checkbox and set date to spinner
				else {
					chckbx1a_noExpiry.setSelected(false);
					spin1a_expiration.setEnabled(true);
					spin1a_expiration.setValue(RecipeTool.getStorage().getExpiration(iname));
				}
				
				// Prepare a counter
				int i=0;
				
				// If there are any allergens
				if (RecipeTool.getStorage().getAllergens(iname) != null) {
					
					// Insert each allergen into table on its own row
					for (String temp : RecipeTool.getStorage().getAllergens(iname)) {
						table1a_allergens.setValueAt(temp, i, 0);
						i++;
					} 
				}
				break;
				
			case CONTAINER_MODIFY_RECIPE:
				
				// Set container title
				iframe2_add.setTitle("Modify Recipe");
				
				// Set ok button text
				btn2a_ok.setText("Modify");
				
				// Read selected recipe name into a string
				rname = list2_recipes.getSelectedValue();
				
				// Pre-populate name and instruction UI components
				txtf2a_name.setText(rname);
				text2a_instruction.setText(RecipeTool.getBook().getInsturction(rname));
				
				// Read ingredient names into a set (LinkedHashSet preserves order)
				LinkedHashSet<String> inames = RecipeTool.getBook().listIngredients(rname);
				
				// Check how many rows we need, then add them
				int iCount = inames.size();
				rows(iCount-1);
				
				// Get the first ingredient name,
				// set it selected
				// fill in the amount
				iname = (String)inames.toArray()[0];
				combx2a_ingredient.setSelectedItem(iname);
				spin2a_amount.setValue(RecipeTool.getBook().getAmount(rname, iname));
				
				// Rest of the ingredients are going to use temporary rows.
				// For each of the remaining ingredients
				for (int p = 1; p <= iCount-1; p++){
					
					// Get the ingredient name,
					// set it selected
					// fill in the amount
					iname = (String)inames.toArray()[p];
					combx2a_temp[p].setSelectedItem(iname);
					spin2a_temp[p].setValue(RecipeTool.getBook().getAmount(rname, iname));
				}
				break;
				
				// If segment is not supported, ignore it
				default: break;
				}// end switch
		}// end loop
	}// end method refreshFields

	// REFRESH LIST
	 /**
	 * This method will populate specified GUI components with new updated information.
	 * The GUI is divided into segments, which are mostly based on containers. See: {@link recipeTool.Segment}
	 * <p>
	 * 2 different segments are supported by this method:<br>
	 * -LIST_INGREDIENTS<br>
	 * -LIST_RECIPES<br>
	 * @param segments GUI segments, which should be updated with new information
	 */
	private void refreshLists(Segment... segments) {
		
		// If invoked with no arguments, this method will do nothing
		if (segments.length == 0) return;
		
		// For every segment, which is passed to this method
		for (Segment z : segments){
			switch(z){
			case LIST_INGREDIENTS:
				
				// Refresh ingredients list with fresh data from storage
				list1_ingredients.setListData(RecipeTool.getStorage().listIngredients().toArray(new String[0]));
				
				// Clear old values from recipe adding segment
				combx2a_ingredient.removeAllItems();
				
				// Fill combo box with fresh data from storage
				for (String i : RecipeTool.getStorage().listIngredients()) {
					combx2a_ingredient.addItem(i);
				}
				
				// Insert a default option and set it selected
				combx2a_ingredient.insertItemAt("Select", 0);
				combx2a_ingredient.setSelectedIndex(0);
				
				// Refresh filtering lists with fresh data from storage
				list3_mustHave.setListData(RecipeTool.getStorage().listIngredients().toArray(new String[0]));
				list3_avoidAllergens.setListData(RecipeTool.getStorage().listAllergens().toArray(new String[0]));
				
				break;
				
			case LIST_RECIPES:
				
				// If filtering is active
				if (state_filteringActive){
					
					// Read filtering parameters from GUI
					List<String> mustHave = list3_mustHave.getSelectedValuesList();
					List<String> avoidAllergens = list3_avoidAllergens.getSelectedValuesList();
					boolean enough = chckbx3_noShopping.isSelected();
					boolean sort = rdbtn3_az.isSelected();
					
					// Do the filtering, and save suitable recipes into a string array
					String[] filterResult = RecipeTool.getBook().filterRecipes(mustHave, avoidAllergens, enough, sort);
					
					// If no recipe matches the search parameters
					if (filterResult == null) {
						
						// Clear all recipes and replace with info
						list2_recipes.setListData(new String[] { "no matches found" });
						
						// Make sure that this info-item cannot be selected
						list2_recipes.setEnabled(false);
					} else
						
						// If suitable recipes are found, show them
						list2_recipes.setListData(filterResult);
				} 
				
				// If filtering is not active, refresh list with fresh data from book
				else list2_recipes.setListData(RecipeTool.getBook().listRecipes().toArray(new String[0]));
				
				break;
		
				// If segment is not supported, ignore it
				default: break;
				}// end switch
		}// end loop
	}// end method refreshList
	
	/**
	 * This method reads ingredient data from GUI and then adds a new ingredient to storage or modifies an existing one.
	 * @param modify true if user is modifying an existing ingredient, false otherwise.
	 * @return true if operation is successful, false if it fails.
	 */
	private boolean addIngredient(boolean modify){
		
	// Read ingredient properties from GUI into variables
		String name = txtf1a_name.getText();
		double amount = ((SpinnerNumberModel) spin1a_amount.getModel()).getNumber().doubleValue();
		Unit unit = (Unit) combx1a_unit.getSelectedItem();
		
		// If there is no expiration date, set null
		Date expiration = null;
		
		// Else use the date 
		if (spin1a_expiration.isEnabled()) {
			expiration = (Date) spin1a_expiration.getValue();
		}
		
		// Prepare a set for the allergens
		TreeSet<String> allergen = new TreeSet<>();
		
		// Read every allergen from table, and add it to the set
		for (int i = 0; i < 10; i++) {
			String temp = (String) table1a_allergens.getValueAt(i, 0);
			
			// Dont handle empty strings
			if ((temp != null) && (temp.equals("") == false))
				allergen.add(temp);
		}
		
		// If table is still in editing mode, stop it so we can get the value
		if (table1a_allergens.isEditing()) {
			CellEditor editor = table1a_allergens.getCellEditor();
			editor.stopCellEditing();
			
			// Get the value
			String unfinished = (String) (editor.getCellEditorValue());
			
			// Add it to the set
			if ((unfinished != null) && (unfinished.equals("") == false))
				allergen.add(unfinished);
		}
		// If we're not modifying an ingredient
		if (modify == false && (name != null) && (name.equals("") == false)){
			
			// Check if there already exists an ingredient with same name
			if (RecipeTool.getStorage().hasIngredient(name))
				
				// If yes, ask confirmation for overwriting
				if (Dialogs.warningOC(
						"The ingredient called " + name + " already exists. Do you want to overwrite?") == 2)
					
					// Cancel
					return false;
		}
		
		try {
			// Do adding / modifying
			RecipeTool.getStorage().setIngredient(name, amount, unit, allergen, expiration);
			
			// If we are modifying, and the name is changing
			String original = list1_ingredients.getSelectedValue();
			if (modify && name.equals(original) == false){
				
				// Change the name also in recipes
				RecipeTool.getBook().renameIngredient(original, name);
				
				// Delete the ingredient with old name
				RecipeTool.getStorage().deleteIngredient(original);
			}
		} catch (Exception exception) {
			
			// If something fails, inform user with notice and return false to caller
			Dialogs.notice(exception.toString());
			exception.printStackTrace();
			return false;
		}
		
		// If success, return true
		return true;
	}// end method addIngredient
	
	/**
	 * This method reads recipe data from GUI and then adds a new recipe to book or modifies an existing one.
	 * @param modify true if user is modifying an existing recipe, false otherwise.
	 * @return true if operation is successful, false if it fails.
	 */
	private boolean addRecipe(boolean modify){
		
	// Read recipe properties from GUI into variables
		String name = txtf2a_name.getText();
		String instruction = text2a_instruction.getText();
		
		// Prepare a map, which shall hold all the ingredient names and amounts
		LinkedHashMap<String, Double> ingredients = new LinkedHashMap<>();
		
		// First row
		// if an ingredient is selected in the combo box
		if (combx2a_ingredient.getSelectedIndex() != 0)
			// Add entry to map
			ingredients.put((String) combx2a_ingredient.getSelectedItem(),
					((SpinnerNumberModel) spin2a_amount.getModel()).getNumber().doubleValue());
		
		// Temporary rows
		for (int i = 1; i <= state_counter2a - 1; i++) {
			
			// If an ingredient is selected in the combo box
			if (combx2a_temp[i].getSelectedIndex() != 0)
				
				// Add entry to map
				ingredients.put((String) combx2a_temp[i].getSelectedItem(),
						((SpinnerNumberModel) spin2a_temp[i].getModel()).getNumber().doubleValue());
		}
		
		// If we're not modifying a recipe
		if (modify == false && (name != null) && (name.equals("") == false)){
			
			// Check if there already exists a recipe with same name
			if (RecipeTool.getBook().hasRecipe(name))
				
				// If yes, ask confirmation for overwriting
				if (Dialogs
						.warningOC("The recipe called " + name + " already exists. Do you want to overwrite?") == 2)
					
					// Cancel
					return false;
		}
		
		try {
			
			// Do adding / modifying
			RecipeTool.getBook().setRecipe(name, instruction, ingredients);
			
			// If we are modifying, and the name is changing
			if (modify && name.equals(list2_recipes.getSelectedValue()) == false)
				
				// Delete the one with the old name
				RecipeTool.getBook().deleteRecipe(list2_recipes.getSelectedValue());
			
		} catch (Exception exception) {
			
			// If something fails, inform user with notice and return false to caller
			Dialogs.notice(exception.toString());
			exception.printStackTrace();
			return false;
		}
		
		// Reset row counter
		state_counter2a = 0;
		
		// If success, return true
		return true;
	}// end method addRecipe
	
	/**
	 * This method will delete one ingredient from storage and from all recipes.
	 * The method is invoked by {@link recipeTool.GUI.ButtonHandler#actionPerformed(ActionEvent)}
	 * @return true if deleted successfully<br>
	 * false if deleting failed
	 */
	private boolean deleteIngredient(){
		
		// Read selected ingredient name into a string
		String iname = list1_ingredients.getSelectedValue();
		
		// If no ingredient is selected, inform user with notice,
		// and return false to the caller
		if (isItemNull(iname)) return false;

		// Check which recipes contain this ingredient, and make a set
		TreeSet<String> temp = RecipeTool.getBook().whoHas(iname);
		
		// If any recipe has this ingredient
		if (temp.isEmpty() == false) {
			
			// Confirm that the user wants to delete
			if (Dialogs.warningOC(
					"If you delete ingredient in here, it will also be deleted from all recipes.\nThe following recipes will be affected: "
							+ temp.toString()) == 2)
				return false;
		}
		
		// Delete from storage
		RecipeTool.getStorage().deleteIngredient(iname);
		
		// Delete from all recipes
		RecipeTool.getBook().deleteAllIngredients(iname);

		// If success, return true to the caller
		return true;
	}// end method deleteIngredient
	
	/**
	 * This method will delete one recipe from book.
	 * The method is invoked by {@link recipeTool.GUI.ButtonHandler#actionPerformed(ActionEvent)}
	 * @return true if deleted successfully<br>
	 * false if deleting failed
	 */
	private boolean deleteRecipe(){
		
		// Read selected recipe name into a string
		String rname = list2_recipes.getSelectedValue();

		// If no recipe is selected, inform user with notice,
		// and return false to the caller
		if (isItemNull(rname)) return false;

		// Delete from book
		RecipeTool.getBook().deleteRecipe(rname);
		
		// If success, return true to the caller
		return true;
	}// end method deleteRecipe


	
	/**
	 * This method will add/remove input rows in {@link recipeTool.GUI#iframe2_add}
	 * @param value number of rows to be added(+) or removed(-).
	 */
	private void rows(int value){
		
		// If passed value is 0, do nothing
		if (value == 0) return;
		
		// Add or remove so many times (using absolute value)
		for (int z = Math.abs(value); z >= 1; z--){
			
			// If adding
			if (value > 0) {
				
				// Prepare counter
				if (state_counter2a <= 1)
					state_counter2a = 1;
				
				// Make it possible to remove this row we're adding
				btn2a_less.setEnabled(true);
				
				// Create a temporary combo box,
				// fill it with list of ingredients,
				// and add it to the array of combo boxes.
				combx2a_temp[state_counter2a] = new JComboBox<>(
						RecipeTool.getStorage().listIngredients().toArray(new String[0]));
				
				// Configure the combo box 
				combx2a_temp[state_counter2a].insertItemAt("Select", 0);
				combx2a_temp[state_counter2a].setSelectedIndex(0);
				combx2a_temp[state_counter2a].setBackground(Color.WHITE);
				
				// Add listener
				combx2a_temp[state_counter2a].addActionListener(handler_button);
				
				// Place this combo box below the last one.
				iframe2_add.getContentPane().add(combx2a_temp[state_counter2a],
						"growx,cell 2 " + (1 + state_counter2a));
				
				// Create a temporary spinner, add it to the array of spinners and configure it
				spin2a_temp[state_counter2a] = new JSpinner();
				spin2a_temp[state_counter2a].setModel(new SpinnerNumberModel(0.0, 0.0, 100000.0, 0.01));
				spin2a_temp[state_counter2a]
						.setEditor(new JSpinner.NumberEditor(spin2a_temp[state_counter2a], "#####0.0#"));
				
				// Place this spinner below the last one
				iframe2_add.getContentPane().add(spin2a_temp[state_counter2a],
						"growx,cell 3 " + (1 + state_counter2a));
				
				// Create a temporary label and add it to the array of labels
				lbl2a_temp[state_counter2a] = new JLabel();
				
				// Place this label below the last one
				iframe2_add.getContentPane().add(lbl2a_temp[state_counter2a],
						"growx,cell 4 " + (1 + state_counter2a));
				
				// Don't make more than 10 rows.
				if (state_counter2a < 10)
					state_counter2a++;
				if (state_counter2a == 10)
					btn2a_more.setEnabled(false);
				
				// We're going to need more space,
				// check how much space is needed,
				// then set new size.
				Dimension prefSize = iframe2_add.getLayout().preferredLayoutSize(rootPane);
				iframe2_add.setPreferredSize(prefSize);
				iframe2_add.revalidate();
			}// end adding rows
			
			// If removing rows
			if (value < 0) {
				
				// Remove the last set of temporary components
				iframe2_add.remove(combx2a_temp[state_counter2a - 1]);
				iframe2_add.remove(spin2a_temp[state_counter2a - 1]);
				iframe2_add.remove(lbl2a_temp[state_counter2a - 1]);
				state_counter2a--;
				
				// Prevent removing the last row
				if (state_counter2a <= 1)
					btn2a_less.setEnabled(false);
				
				// Now that this row is removed, make sure that it's possible to add it again
				btn2a_more.setEnabled(true);
				
				// We're going to need less space now,
				// check how much,
				// then set new size.
				Dimension prefSize = iframe2_add.getLayout().preferredLayoutSize(rootPane);
				iframe2_add.setPreferredSize(prefSize);
				iframe2_add.revalidate();
			}// end removing rows
		}
	}// end method rows
	
	
	/**
	 * This method will check, if the passed object is null.
	 * If the object is null, user is presented with the message:<br>
	 * "You must first select an item."
	 * @param object An object to be check
	 * @return true if object is null, false otherwise 
	 */
	private static boolean isItemNull(Object object){
		if (object == null){
			Dialogs.notice("You must first select an item.");
			return true;
		}else return false;
	}// end method isItemNull
	
	/**
	 * This method will read connection preferences from GUI, and save them to a file. 
	 */
	private void savePreferences(){

		// Read preferences from GUI
		state_allowDB = chckbx0_allowDB.isSelected();
		String ip = txtf0_ip.getText();
		String port = String.valueOf(spin0_port.getValue());
		String user = txtf0_user.getText();
		String pass = String.valueOf(txtf0_pass.getPassword());
		
		// Set saving policy
		RecipeTool.setSavePolicy(state_allowDB);
	
		// If user has denied database connection, disconnect now
		if (state_allowDB == false) {
			if (Database.isConnected())maria(DatabaseCommand.DISCONNECT);
		}
			
		// If user has entered something else than empty password,
		// confirm that it's ok to save it in plaintext, else overwrite it with empty string before saving
		if (pass != null && !pass.equals("")) {
			if (Dialogs.warningYN("Do you want to save your password?"
					+ "\nIf you select Yes, it will be written in plaintext next to the running JAR-file.") == 1)
				pass = "";
		}
		try {
			
			// Save settings to a file
			Settings.writeSettings(ip,port,user,pass,Boolean.toString(state_allowDB));
			Dialogs.notice("Succesfully saved preferences.");
		} catch (Exception exception) {
			
			// If it fails, inform user
			Dialogs.error(
					"The application was unable to save your preferences.");
		}
		
		// Update connection status
		testConnection();
	}// end method savePreferences
	
	/**
	 * This method will populate connection settings with default values.
	 */
	private void useDefault(){
		txtf0_ip.setText("localhost");
		txtf0_user.setText("root");
		txtf0_pass.setText("");
		spin0_port.setValue(3306);
	}
	
	/**
	 * This method will handle connecting, saving, loading and test values.<br>
	 * When user click a button, this method will receive a command from {@link recipeTool.GUI.ButtonHandler},
	 * gather inputed data from UI, pass the information onto {@link recipeTool.RecipeTool}, and finally present the user with
	 * information of success or failure.
	 * @param command a DatabaseCommand
	 */
	private void maria(DatabaseCommand command){
		switch(command){
		case CONNECT:
			
			// Fetch parameters from UI
			String ip = txtf0_ip.getText();
			String port = String.valueOf(spin0_port.getValue());
			String user = txtf0_user.getText();
			String pass = String.valueOf(txtf0_pass.getPassword());

			try {
				
				// Try connecting
				RecipeTool.prepareDatabase(ip, port, user, pass, "recipe_tool");
				Dialogs.notice("Connected Successfully!");
			} catch (Exception exception) {
				
				// If it fails, close the connection
				Database.close();
				Dialogs.error("Database setup did not succeed.\n" + exception);
				
			} finally {
				
				// Always update connection status
				testConnection();
			}
			break;
			
		case DISCONNECT:
			// Close connection
			Database.close();
			
			// Inform user
			Dialogs.notice("Disconnected.");
			
			// Update connection status
			testConnection();
			break;
			
		case SAVE:
			try {
				
				// Try saving
				RecipeTool.save();
				Dialogs.notice("Saved Successfully!");
			} catch (Exception exception) {
				
				// If it fails, close connection
				Database.close();
				Dialogs.error("Saving failed!\n" + exception);
			} finally {
				
				// Update connection status
				testConnection();
			}
			break;
			
		case LOAD:
			try {
				// Clear current values
				RecipeTool.getStorage().clear();
				RecipeTool.getBook().clear();
				
				// Load new values
				RecipeTool.load();
				Dialogs.notice("Loaded Successfully!");
			} catch (Exception exception) {
				
				// If it fails, close connection
				Database.close();
				Dialogs.error("Loading failed!\n" + exception);
				exception.printStackTrace();
			} finally {
				
				// Update connections status
				testConnection();
			}
			break;
			
		case TEST:
			try {
				// Clear old values and load test values
				RecipeTool.test();
				
				// Update UI with new values
				refreshLists(Segment.LIST_INGREDIENTS,
							Segment.LIST_RECIPES);
				
				// Infrom user
				Dialogs.notice("Test Values Loaded Successfully!");
			} catch (Exception exception) {
				
				// If loading fails, inform user
				Dialogs.error("Loading failed!\n" + exception);
				exception.printStackTrace();
			}
			break;
			
			// If command is not supported, ignore it
			default: break;
			}// end switch
	}// end method maria
	
	/**
	 * This method will handle those UI functions, that need to be done when filtering recipes.
	 * The method is invoked, each time user presses the filtering toggle button.
	 */
	private void filter(){
		
		// Update the filtering state
		state_filteringActive = (state_filteringActive ? false : true);
		
		// Disable all other buttons, if filtering is active, enable if not active.
		for (Container c : containerArrayPanels) {
			for (Component c2 : c.getComponents()) {
				if (c2 instanceof AbstractButton)
					c2.setEnabled(!state_filteringActive);
			}
		}
		// This button is always enabled
		btn3_filter.setEnabled(true);
		
		// Disable/enable rest of filtering UI
		list3_avoidAllergens.setEnabled(!state_filteringActive);
		list3_mustHave.setEnabled(!state_filteringActive);
		lbl3_noShopping.setEnabled(!state_filteringActive);
		lbl3_sortBy.setEnabled(!state_filteringActive);

		// Change informative label
		lbl2_filteringActive
				.setText((state_filteringActive ? "Filtering ON" : "Filtering OFF"));
		
		// When filtering is disabled, make sure recipes list get enabled
		// (it's disabled in other method, if no results are found)
		if (state_filteringActive == false) {
			list2_recipes.setEnabled(true);
		}
	}// end method filter
	
	/**
	 * This method controls closing the application. The method is invoked,
	 * when user tries to close the window.<br>
	 * User is asked, if he would like to save before exiting. If saving goes ok, app will exit.
	 * If saving fails, user can still go back and maybe connect to a database.
	 */
	private void exit() {
		try {
			
			// Confirm user wants to exit
			int answer = Dialogs.choiceYNC("You are exiting the application. Do you want to save changes?");
			switch (answer) {

			case 0: // Yes
				try {
					
					// Save
					RecipeTool.save();
				} catch (Exception e1) {
					
					// If saving fails, close connection and confirm if user still wants to exit
					Database.close();
					if (Dialogs.warningYN("Saving failed. Do you still want to exit?\n" + e1) == 1)
						
						return; // No
					else
						System.exit(0); // Yes
				} finally {
					testConnection();
				}
				
				// If saving is successful, exit
				System.exit(0);
				break;

			case 1: // No
				System.exit(0);
				break;

			case 2: // Cancel
				return;
			default: // this should never happen
				throw new IllegalArgumentException();
			}
		} catch (Exception e1) {
			
			// If there is an unhandled exception, just give up and exit
			System.exit(1);
		}
	}// end method exit
	
	/**
	 * This method reduce storage values, using amount from the selected recipe.
	 * If any storage value would go negative, this is prevented and user notified.
	 */
	private void prepare(){
		
		// Read the selected recipe into a string
		String rname = list2_recipes.getSelectedValue();
		
		// Validate the recipe 
		if (isItemNull(rname)) return;
		if (RecipeTool.getBook().isEnough(rname) == false) {
			Dialogs.notice("Cannot prepare this recipe, there is not enough ingredients in Storage.");
			return;
		}
		
		// Confirm with the user
		if (Dialogs.choiceOC("You are preparing a recipe, the amounts in Storage will be adjusted automatically. Proceed?") == 2) return;
		
		// For every ingredient in selected recipe
		for (String iname : RecipeTool.getBook().listIngredients(rname)){
			
			// Do the reducing
			RecipeTool.getStorage().reduce(iname, RecipeTool.getBook().getAmount(rname, iname));
		}
		
		// Announce success
		Dialogs.notice("Storage values adjusted. Bon appétit!");
	}// end method prepare
	
	/**
	 * This method will change the unit label in {@link recipeTool.Segment#CONTAINER_ADD_RECIPE},
	 * according to the ingredient which is selected in the nearby combo box.
	 * @param source A JComboBox object, where the ingredient is listed
	 */
	private void unitChanger(Object source){
		
		// If combo box is on the first row
		if (source == combx2a_ingredient){
			
			// If no ingredient is selected, return without doing anyting
			if (combx2a_ingredient.getSelectedIndex() < 1) return;
			
			// Change the unit label
			lbl2a_unit
					.setText(RecipeTool.getStorage().getUnit(combx2a_ingredient.getSelectedItem().toString()).toString());
		}

		// If combo box is on the temporary rows
		else{
			JComboBox<?> temp = (JComboBox<?>) source;
			
			// Prepare a counter
			int j = 0;
			
			// For all temporary combo boxes
			for (JComboBox<String> i : combx2a_temp) {
				if (temp.equals(i)) {
					
					// Only if some ingredient is actually selected
					if(i.getSelectedIndex() > 0)
						
						// Change the unit label
						lbl2a_temp[j].setText(RecipeTool.getStorage().getUnit(i.getSelectedItem().toString()).toString());
					break;
				}
				j++;
			}
		}
	}// end method unitChanger

	// EVENT HANDLERS
	// ***********************************************************************************************************************

	// LISTHANDLER
	/**
	 * This class handles events for lists.
	 * 
	 * @author Ville Salmela
	 *
	 */
	private class ListHandler implements ListSelectionListener {

		/**
		 * This method will run, when an item selection is changed.
		 * Refreshing method is invoked.
		 * 
		 * @see recipeTool.GUI#refreshFields(Segment...)
		 */
		@Override
		public void valueChanged(ListSelectionEvent event) {
			try {
				Object source = event.getSource();
				if (source == list1_ingredients) refreshFields(Segment.CONTAINER_INGREDIENTS);
				else if (source == list2_recipes) refreshFields(Segment.CONTAINER_RECIPES);
			} catch (Exception exception) {
				RecipeTool.mayday(exception);
			}
		}// end method valueChanged
	}// end class ListHandler

	// BUTTONHANDLER
	/**
	 * This class handles events for buttons.
	 * 
	 * @author Ville Salmela
	 *
	 */
	private class ButtonHandler implements ActionListener {

		/**
		 * This method runs, when an action occurs.<br>
		 * The method handles displaying/hiding containers and enabling/disabling buttons, when user clicks a button.
		 * It delegates any further functions to specialized methods. 
		 */
		@Override
		public void actionPerformed(ActionEvent event){
			try{
				Object source = event.getSource();
				
				// DELETE
				if (source == btn1_delete) {
					if (deleteIngredient()){
						clearFields(Segment.CONTAINER_INGREDIENTS,
									Segment.CONTAINER_RECIPES);
						refreshLists(Segment.LIST_INGREDIENTS,
									Segment.LIST_RECIPES);
					}
				}
				else if (source == btn2_delete) {
					if(deleteRecipe()){
						clearFields(Segment.CONTAINER_RECIPES);
						refreshLists(Segment.LIST_RECIPES);
					}
				}
				
				// ADD
				else if (source == btn1a_ok) {
					if(addIngredient(iframe1_add.getTitle().equals("Modify Ingredient"))){
						clearFields(Segment.CONTAINER_INGREDIENTS,
									Segment.CONTAINER_RECIPES,
									Segment.CONTAINER_ADD_INGREDIENT);
						refreshLists(Segment.LIST_INGREDIENTS);
						
						togglePanels(true);
						iframe1_add.setVisible(false);
					}
				}
	
				else if (source == btn2a_ok) {
					if (addRecipe(iframe2_add.getTitle().equals("Modify Recipe"))){
						clearFields(Segment.CONTAINER_RECIPES,
									Segment.CONTAINER_ADD_RECIPE);
						refreshLists(Segment.LIST_RECIPES);
						
						togglePanels(true);
						iframe2_add.setVisible(false);
					}
				}
	
				// TEMPORARY UI COMPONENTS
				else if (source == btn2a_more) {
					rows(1);
				}
	
				else if (source == btn2a_less) {
					rows(-1);
				}
				
				// DATABASE
				else if (source == btn0_settings_connect) {
					maria(DatabaseCommand.CONNECT);
				}
				
				else if (source == btn0_settings_disconnect) {
					maria(DatabaseCommand.DISCONNECT);
				}
	
				else if (source == btn0_settings_save) {
					maria(DatabaseCommand.SAVE);
				}
	
				else if (source == btn0_settings_load) {
					maria(DatabaseCommand.LOAD);
					refreshLists(Segment.LIST_INGREDIENTS,
							Segment.LIST_RECIPES);
					clearFields(Segment.CONTAINER_INGREDIENTS,
							Segment.CONTAINER_RECIPES,
							Segment.CONTAINER_FILTER);
				}
	
				else if (source == btn0_settings_test) {
					maria(DatabaseCommand.TEST);
				}
				
				else if (source == btn0_settings_pref) {
					savePreferences();
				}
				
				else if (source == btn0_settings_default) {
					useDefault();
				}
				
				// RADIO BUTTONS
				else if (source == rdbtn3_az) {
					rdbtn3_az.setSelected(true);
					rdbtn3_ex.setSelected(false);
				} else if (source == rdbtn3_ex) {
					rdbtn3_az.setSelected(false);
					rdbtn3_ex.setSelected(true);
				}
				
				// CLOSE
				else if (source == btn1a_cancel) {
					iframe1_add.setVisible(false);
					clearFields(Segment.CONTAINER_ADD_INGREDIENT);
					togglePanels(true);
				}
				
				else if (source == btn2a_cancel) {
					iframe2_add.setVisible(false);
					clearFields(Segment.CONTAINER_ADD_RECIPE);
					state_counter2a = 0;
					togglePanels(true);
				}
	
				else if (source == btn0_settings_close) {
					iframe0_settings.setVisible(false);
					clearFields(Segment.CONTAINER_SETTINGS);
					btn0_settings_open.setEnabled(true);
					togglePanels(true);
				}
	
				// OPEN
				else if (source == btn1_add) {
					iframe1_add.setTitle("Add Ingredient");
					btn1a_ok.setText("Add to Storage");
					
					togglePanels(false);
					iframe1_add.setVisible(true);
				}
				
				else if (source == btn1_modify) {
					if (isItemNull(list1_ingredients.getSelectedValue())) return;
					
					refreshFields(Segment.CONTAINER_MODIFY_INGREDIENT);
					
					togglePanels(false);
					iframe1_add.setVisible(true);
				}
	
				else if (source == btn2_add) {
					iframe2_add.setTitle("Add Recipe");
					btn2a_ok.setText("Add to Book");
					
					togglePanels(false);
					btn2a_less.setEnabled(false);
					iframe2_add.setVisible(true);
				}
				
				else if (source == btn2_modify) {
					String rname = list2_recipes.getSelectedValue();
					if (isItemNull(rname)) return;
	
					refreshFields(Segment.CONTAINER_MODIFY_RECIPE);
					
					togglePanels(false);
					if (RecipeTool.getBook().listIngredients(rname).size() <= 1) btn2a_less.setEnabled(false);
					iframe2_add.setVisible(true);
				}
				
				else if (source == btn2_prepare) {
					prepare();
					clearFields(Segment.CONTAINER_INGREDIENTS);
					
				}
	
				else if (source == btn0_settings_open) {
					togglePanels(false);
					testConnection();
					refreshFields(Segment.CONTAINER_SETTINGS);
					btn0_settings_open.setEnabled(false);
					iframe0_settings.setVisible(true);
				}
	
				// FILTER
				else if (source == btn3_filter) {
					filter();
					clearFields(Segment.CONTAINER_RECIPES);
					refreshLists(Segment.LIST_RECIPES);
				}
	
				// INTERACTIVE UI
				else if (source == chckbx1a_noExpiry) {
					spin1a_expiration.setEnabled(!chckbx1a_noExpiry.isSelected());
				}
				
				else if (source instanceof JComboBox){
					unitChanger(source);
				}
				
			}catch(Exception exception){RecipeTool.mayday(exception);}
		}// end method actionPerformed
	}// end class ButtonHandler

	// WINDOWHANDLER
	/**
	 * This class handles events for the main application window.<br>
	 * Only the closing event is implemented, while other inherited methods are
	 * ignored.
	 * 
	 * @author Ville Salmela
	 *
	 */
	private class WindowHandler implements WindowListener {

		/**
		 * This method runs, when user attempts to close the window.<br>
		 * Exit method is invoked.
		 * @see recipeTool.GUI#exit()
		 */
		@Override
		public void windowClosing(WindowEvent e) {
			exit();
		}// end method windowClosing

		/**
		 * Not in use.
		 */
		@Override
		public void windowOpened(WindowEvent e) {
		}

		/**
		 * Not in use.
		 */
		@Override
		public void windowClosed(WindowEvent e) {
		}

		/**
		 * Not in use.
		 */
		@Override
		public void windowIconified(WindowEvent e) {
		}

		/**
		 * Not in use.
		 */
		@Override
		public void windowDeiconified(WindowEvent e) {
		}

		/**
		 * Not in use.
		 */
		@Override
		public void windowActivated(WindowEvent e) {
		}

		/**
		 * Not in use.
		 */
		@Override
		public void windowDeactivated(WindowEvent e) {
		}
	}// end class WindowHandler

	/**
	 * This class handles events for JTextAreas.
	 * 
	 * @author Ville Salmela
	 *
	 */
	private class TextHandler extends ComponentAdapter {

		/**
		 * This method runs, when a source JTextArea resizes. It will then
		 * resize the parent JInternalFrame, so that there is neither too
		 * much nor little space.
		 */
		@Override
		public void componentResized(ComponentEvent e) {
			try {
				JTextArea t = ((JTextArea) e.getSource());
				if (t.getText().equals(""))
					return;

				JInternalFrame c = (JInternalFrame) t.getParent().getParent().getParent().getParent();
				Dimension prefSize = c.getLayout().preferredLayoutSize(rootPane);
				c.setPreferredSize(prefSize);
				c.revalidate();
			} catch (Exception exception) {
				RecipeTool.mayday(exception);
			}

		}// end method componentResized
	}// end class TextHandler
}// end class GUI
