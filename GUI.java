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
	private final JTabbedPane tabbedPane = new JTabbedPane();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JPanel panel_3 = new JPanel();
	private final JPanel panel_4 = new JPanel();
	private final JInternalFrame iframe1_add = new JInternalFrame();
	private final JInternalFrame iframe2_add = new JInternalFrame();
	private final JInternalFrame iframe0_settings = new JInternalFrame("Settings");

	private final java.awt.Container[] containerArrayAll;
	private final java.awt.Container[] containerArrayIframes;
	private final java.awt.Container[] containerArrayPanels;

	// LISTS
	private final JList<String> list1_ingredients = new JList<>();
	private final JList<String> list2_recipes = new JList<>();
	private final JList<String> list3_mustHave = new JList<>();
	private final JList<String> list3_avoidAllergens = new JList<>();

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

	// ICONS
	private final ImageIcon icon_connected = new ImageIcon(GUI.class.getResource("/recipeTool/images/icon_connected.png"));
	private final ImageIcon icon_disconnected = new ImageIcon(GUI.class.getResource("/recipeTool/images/icon_disconnected.png"));
	private final ImageIcon icon_cart = new ImageIcon(GUI.class.getResource("/recipeTool/images/icon_cart.png"));
	private final ImageIcon icon_cutlery = new ImageIcon(GUI.class.getResource("/recipeTool/images/icon_cutlery.png"));

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
	private final JLabel lbl3_noShopping = new JLabel("No shopping");
	private final JLabel lbl3_sortBy = new JLabel("Sort by");
	private final JLabel lbl0_ip = new JLabel("IP-address");
	private final JLabel lbl0_port = new JLabel("Port number");
	private final JLabel lbl0_user = new JLabel("Username");
	private final JLabel lbl0_pass = new JLabel("Password");
	private final JLabel lbl_connection = new JLabel();

	// BUTTONS
	private final JButton btn1_add = new JButton("Add ingredient");
	private final JButton btn1_delete = new JButton("Delete ingredient");
	private final JButton btn1_modify = new JButton("Modify ingredient");
	private final JButton btn2_add = new JButton("Add recipe");
	private final JButton btn2_delete = new JButton("Delete recipe");
	private final JButton btn2_modify = new JButton("Modify recipe");
	private final JButton btn2_prepare = new JButton("Prepare recipe");
	private final JButton btn1a_ok = new JButton("Add to Storage");
	private final JButton btn1a_cancel = new JButton("Cancel");
	private final JButton btn2a_ok = new JButton("Add to Book");
	private final JButton btn2a_cancel = new JButton("Cancel");
	private final JButton btn2a_more = new JButton("Add Row");
	private final JButton btn2a_less = new JButton("Remove Row");
	private final JToggleButton btn3_filter = new JToggleButton("Filtering Switch");
	private final JButton btn0_settings_open = new JButton("Settings");
	private final JButton btn0_settings_connect = new JButton("Connect");
	private final JButton btn0_settings_close = new JButton("Close settings");
	private final JButton btn0_settings_save = new JButton("Save");
	private final JButton btn0_settings_load = new JButton("Load");
	private final JButton btn0_settings_test = new JButton("Load Test Values");

	// CHECKBOXES
	private final JCheckBox chckbx3_noShopping = new JCheckBox("No Shopping");
	private final JCheckBox chckbx1a_noExpiry = new JCheckBox("Does not expire");

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
	private boolean state_filteringActive = false;

	// COUNTER
	private int state_counter2a = 0;

	// HANDLERS
	private final ButtonHandler handler_button = new ButtonHandler();
	private final ListHandler handler_list = new ListHandler();
	private final WindowHandler handler_window = new WindowHandler();
	private final TextHandler handler_text = new TextHandler();

	// CONSTRUCTOR
	// **************************************************************************************************************************
	public GUI() {
		super("recipeTool");
		setContentPane(new JDesktopPane());

		containerArrayAll = new java.awt.Container[] { panel_1, panel_2, panel_3, panel_4,
				iframe1_add.getContentPane(), iframe2_add.getContentPane(),
				iframe0_settings.getContentPane() };
		containerArrayIframes = new java.awt.Container[] { iframe0_settings.getContentPane(),
				iframe1_add.getContentPane(), iframe2_add.getContentPane() };
		containerArrayPanels = new java.awt.Container[] { panel_1, panel_2, panel_3, panel_4 };

		migLayout();
		config();
		testConnection();
		refreshLists();

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
		root.add(tabbedPane, "north, h 80%");
		root.add(panel_4, "south, h 20%");
		root.add(iframe1_add, "pos 20% 20%");
		root.add(iframe2_add, "pos 20% 20%");
		root.add(iframe0_settings, "pos 20% 20%");

		Container c;

		c = panel_4;
		c.setLayout(new MigLayout());
		c.add(btn0_settings_open);
		c.add(btn0_settings_load);
		c.add(btn0_settings_save);
		c.add(lbl_connection);

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
		c.add(btn0_settings_connect, "span 4, split 3");
		c.add(btn0_settings_test);
		c.add(btn0_settings_close);

		c = panel_1;
		c.setLayout(new MigLayout("flowy",
				"[align right,10% | align left, 20%,fill | align left, 20%,fill | align left]", "10%"));
		c.add(lbl1_amount);
		c.add(lbl1_unit);
		c.add(lbl1_allergens);
		c.add(lbl1_expiration, "wrap");
		c.add(text1_amount);
		c.add(text1_unit);
		c.add(text1_allergens);
		c.add(text1_expiration, "wrap");
		c.add(list1_ingredients, "grow, spany 4,wrap");
		c.add(btn1_add, "split 3");
		c.add(btn1_delete);
		c.add(btn1_modify);

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

		c = panel_2;
		c.setLayout(new MigLayout("flowy,fillx, align left,insets panel",
				"[align right,10% | align left,20%,fill | align left,20%,fill | align left | growprio 1000]", "10%"));
		c.add(panel_3, "dock east,w 30%,wmax 30%,grow");
		c.add(lbl2_ingredients);
		c.add(lbl2_insturctions);
		c.add(lbl2_enough);
		c.add(lbl2_allergens);
		c.add(lbl2_expiration, "wrap");
		c.add(text2_ingredients);
		c.add(text2_instruction);
		c.add(lbl2_enoughValue);
		c.add(text2_allergens);
		c.add(text2_expiration, "wrap");
		c.add(list2_recipes, "grow,spany 5,wrap");
		c.add(btn2_add, "split 4");
		c.add(btn2_delete);
		c.add(btn2_modify);
		c.add(btn2_prepare, "wrap");

		c = panel_3;
		c.setLayout(new MigLayout("flowy,insets panel", "[50%,fill]", "[25%,fill|25%,fill|||]"));
		c.setBackground(Color.WHITE);
		c.add(list3_mustHave, "grow,spanx 2");
		c.add(list3_avoidAllergens, "grow,spanx 2");
		c.add(lbl3_noShopping);
		c.add(lbl3_sortBy);
		c.add(btn3_filter, "span 2");
		c.add(lbl2_filteringActive, "span 2,grow 0,center,wrap");
		c.add(chckbx3_noShopping);
		c.add(rdbtn3_az, "spanx 2, split 2");
		c.add(rdbtn3_ex);

	}// end method migLayout

	// CONFIG
	// *******************************************************************************************************************************
	private void config() {

		// ROOT
		addWindowListener(handler_window);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 1200, 800);
		setLocationRelativeTo(null);

		// TABS
		tabbedPane.addTab("Ingredients", null, panel_1, null);
		tabbedPane.addTab("Recipes", null, panel_2, null);

		// RADIO BUTTONS
		rdbtn3_az.setSelected(true);
		rdbtn3_ex.setSelected(false);

		// LABELS
		final Font font14 = new Font("Tahoma", Font.BOLD, 14);

		for (Component c : tabbedPane.getComponents()) {
			for (Component c2 : ((JPanel) c).getComponents()) {
				if (c2 instanceof JLabel)
					((JLabel) c2).setFont(font14);
			}
		}

		lbl2_filteringActive.setFont(new Font("Tahoma", Font.BOLD, 18));

		// LISTS
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

		// BUTTONS
		for (java.awt.Container c : containerArrayAll) {
			for (Component c2 : c.getComponents()) {
				if (c2 instanceof AbstractButton)
					((AbstractButton) c2).addActionListener(handler_button);
				else if (c2 instanceof JComboBox)
					((JComboBox<?>) c2).addActionListener(handler_button);
			}
		}

		// COLOR
		for (java.awt.Container c : containerArrayAll) {
			c.setBackground(Color.WHITE);
			for (Component c2 : c.getComponents()) {
				if (!(c2 instanceof AbstractButton) || (c2 instanceof JRadioButton) || (c2 instanceof JCheckBox))
					c2.setBackground(Color.WHITE);
			}

		}

		// TEXTAREAS
		for (Container c : containerArrayAll) {
			for (Component c2 : c.getComponents()) {
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

		// SPINNERS
		spin1a_amount.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
		spin1a_amount.setEditor(new JSpinner.NumberEditor(spin1a_amount, "##0.0#"));

		spin1a_expiration.setModel(new SpinnerDateModel(new Date(), new Date(946677600921L),
				new Date(32535122400921L), Calendar.DAY_OF_YEAR));
		spin1a_expiration.setEditor(new JSpinner.DateEditor(spin1a_expiration, "d.M.y"));
		
		spin2a_amount.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
		spin2a_amount.setEditor(new JSpinner.NumberEditor(spin2a_amount, "##0.0#"));

		spin0_port.setModel(new SpinnerNumberModel(0, 0, 65535, 1));

		// TABLE
		DefaultTableModel temp = new DefaultTableModel(10, 1);
		table1a_allergens.setModel(temp);
		table1a_allergens.setBorder(new LineBorder(Color.BLACK));

	}// end method config

	// GUI METHODS
	// **************************************************************************************************************************

	// TOGGLE PANELS
	/**
	 * This method will enable/disable all components on all panels found in
	 * {@link recipeTool.GUI#containerArrayPanels}
	 * 
	 * @param enable
	 *            {@code true} to enable all panels<br>
	 *            <blockquote>{@code false} to disable all panels</blockquote>
	 */
	private void togglePanels(boolean enable) {
		for (Container c : containerArrayPanels) {
			for (Component c2 : c.getComponents()) {
				c2.setEnabled(enable);
			}
		}
		testConnection();
	}// end method togglePanels

	// TEST CONNECTION
	/**
	 * This method will test if there is a valid connection established to a database.<br>
	 * The method does the checking using {@link utilities.Database#isConnected()}
	 * <p>
	 * Connection Icon is set accordingly, and save/load buttons enabled or
	 * disabled.
	 * 
	 * 
	 */
	private void testConnection() {
		boolean established = Database.isConnected();
		btn0_settings_load.setEnabled(established);
		btn0_settings_save.setEnabled(established);
		btn0_settings_test.setEnabled(established);
		lbl_connection.setIcon(established ? icon_connected : icon_disconnected);
	}

	// CLEAR FIELDS
	private void clearFields(int... tabNumber) {
		if(tabNumber.length == 0) tabNumber = new int[]{0,1,2,3,11,21};
		Container container = null;
		Dimension prefSize;
		for (int z : tabNumber){
			switch (z) {
	
			case 0: // settings
				container = iframe0_settings.getContentPane();
				break;
	
			case 1: // ingredients tab
				container = panel_1;
				break;
	
			case 2: // recipes tab
				container = panel_2;
				lbl2_enough.setIcon(null);
				lbl2_enoughValue.setText("");
				break;
	
			case 3: // filter panel
				container = panel_3;
				break;
	
			case 11: // add ingredient internal frame
				container = iframe1_add.getContentPane();
				break;
				
			case 21: // add recipe internal frame
				container = iframe2_add.getContentPane();
				btn2a_more.setEnabled(true);
				btn2a_less.setEnabled(false);
				for (JComboBox<?> i : combx2a_temp) {
					if (i != null)
						iframe2_add.remove(i);
				}
				for (JSpinner j : spin2a_temp) {
					if (j != null)
						iframe2_add.remove(j);
				}
				for (JLabel k : lbl2a_temp) {
					if (k != null)
						iframe2_add.remove(k);
				}
				break;
			default: // this should not happen
				throw new IllegalArgumentException();
			}
	
			// clear every component in selected container
			for (Component c : container.getComponents()) {
				if (c instanceof JTextArea || c instanceof JTextField)
					((JTextComponent) c).setText("");
				else if (c instanceof JSpinner) {
					if (((JSpinner) c).getModel().getClass().getSimpleName().equals("SpinnerNumberModel"))
						((JSpinner) c).setValue(0.0);
					else if (((JSpinner) c).getModel().getClass().getSimpleName().equals("SpinnerDateModel"))
						((JSpinner) c).setValue(new Date());
				} else if (c instanceof JTable) {
					((JTable) c).clearSelection();
					if (((JTable) c).getCellEditor() != null)
						((JTable) c).getCellEditor().stopCellEditing();
					DefaultTableModel tableModel = (DefaultTableModel) ((JTable) c).getModel();
					int rightRowCount = tableModel.getRowCount();
					tableModel.setRowCount(0);
					tableModel.setRowCount(rightRowCount);
				} else if (c instanceof JList)
					((JList<?>) c).clearSelection();
				else if (c instanceof JCheckBox)
					((JCheckBox) c).setSelected(false);
			}
	
			// if container is internal frame, then reset the size
			if (z == 0 || z == 11 || z == 21){
				Container iframe = container.getParent().getParent().getParent();
				prefSize = iframe.getLayout().preferredLayoutSize(rootPane);
				iframe.setPreferredSize(prefSize);
				iframe.revalidate();
			}
		}
	}// end method clearFields

	// REFRESH FIELDS
	private void refreshFields(int... numbers) {
		if (numbers.length == 0) numbers = new int[]{1,2};
		for (int z : numbers){
			switch(z){
			
			case 1: // tab ingredients
				String iname = list1_ingredients.getSelectedValue();
				if ((iname != null) && (Storage.hasIngredient(iname))) {
					text1_unit.setText(Storage.getUnit(iname).toString());
					text1_amount.setText(
							((Double) new BigDecimal(Storage.getAmount(iname)).setScale(2, RoundingMode.HALF_UP).doubleValue())
									.toString());
		
					if (Storage.getAllergens(iname) == null)
						text1_allergens.setText("no allergens");
					else
						text1_allergens.setText(String.join(", ", Storage.getAllergens(iname)));
		
					if (Storage.getExpiration(iname) == null)
						text1_expiration.setText("does not expire");
					else
						text1_expiration.setText(DateFormat.getDateInstance().format(Storage.getExpiration(iname)));
				}
				break;
				
			case 2: //tab recipes
				String rname = list2_recipes.getSelectedValue();
				if ((rname != null) && (Book.hasRecipe(rname))) {
					StringBuilder temp = new StringBuilder();
					for (String iname1 : Book.listIngredients(rname)) {
						temp.append(new BigDecimal(Book.getAmount(rname, iname1)).setScale(2, RoundingMode.HALF_UP).toString());
						temp.append(" ");
						temp.append(Storage.getUnit(iname1));
						temp.append(" ");
						temp.append(iname1);
						temp.append("\n");
					}
					text2_ingredients.setText(temp.toString().trim());
		
					if (Book.getInsturction(rname) == null || Book.getInsturction(rname).equals(""))
						text2_instruction.setText("no instruction");
					else
						text2_instruction.setText(Book.getInsturction(rname));
		
					if (Book.getAllergens(rname) == null || Book.getAllergens(rname).isEmpty())
						text2_allergens.setText("no allergens");
					else
						text2_allergens.setText(String.join(", ", Book.getAllergens(rname)));
		
					if (Book.getExpiration(rname)[0] == null)
						text2_expiration.setText("no expiring ingredients");
					else
						text2_expiration
								.setText(String.join("", DateFormat.getDateInstance().format(Book.getExpiration(rname)[0]), " ",
										(String) Book.getExpiration(rname)[1]));
		
					lbl2_enoughValue.setText(Book.isEnough(rname) ? "Ready to cook" : "Go Shopping");
					lbl2_enough.setIcon(Book.isEnough(rname) ? icon_cutlery : icon_cart);
				}
				break;
				
			case 12: // internal frame modify ingredients
				iname = list1_ingredients.getSelectedValue();
				txtf1a_name.setText(iname);
				spin1a_amount.setValue(Storage.getAmount(iname));
				combx1a_unit.setSelectedItem(Storage.getUnit(iname));
				
				if (Storage.getExpiration(iname) == null){
					chckbx1a_noExpiry.setSelected(true);
					spin1a_expiration.setEnabled(false);
				}
				else {
					chckbx1a_noExpiry.setSelected(false);
					spin1a_expiration.setEnabled(true);
					spin1a_expiration.setValue(Storage.getExpiration(iname));
				}
				int i=0;
				if (Storage.getAllergens(iname) != null) {
					for (String temp : Storage.getAllergens(iname)) {
						table1a_allergens.setValueAt(temp, i, 0);
						i++;
					} 
				}
				break;
			case 22: // internal frame modify recipes
				rname = list2_recipes.getSelectedValue();
				txtf2a_name.setText(rname);
				text2a_instruction.setText(Book.getInsturction(rname));
				LinkedHashSet<String> inames = Book.listIngredients(rname);
				int iCount = inames.size();
				rows(iCount-1);
				
				iname = (String)inames.toArray()[0];
				combx2a_ingredient.setSelectedItem(iname);
				spin2a_amount.setValue(Book.getAmount(rname, iname));
				
				for (int p = 1; p <= iCount-1; p++){
					iname = (String)inames.toArray()[p];
					combx2a_temp[p].setSelectedItem(iname);
					spin2a_temp[p].setValue(Book.getAmount(rname, iname));
				}
				break;
				
			default: throw new IllegalArgumentException();
			}
		}
		
		
		
	}// end method refreshFields

	// REFRESH LIST
	private void refreshLists(int... numbers) {
		if (numbers.length == 0) numbers = new int[]{1,2,3,21};
		for (int z : numbers){
			switch(z){
			case 1:	
				list1_ingredients.setListData(Storage.listIngredients().toArray(new String[0]));
				break;
			case 2:
				if (state_filteringActive){
					List<String> mustHave = list3_mustHave.getSelectedValuesList();
					List<String> avoidAllergens = list3_avoidAllergens.getSelectedValuesList();
					boolean enough = chckbx3_noShopping.isSelected();
					boolean sort = rdbtn3_az.isSelected();
					String[] filterResult = Book.filterRecipes(mustHave, avoidAllergens, enough, sort);
					if (filterResult == null) {
						list2_recipes.setListData(new String[] { "no matches found" });
						list2_recipes.setEnabled(false);
					} else
						list2_recipes.setListData(filterResult);
				} 
				else list2_recipes.setListData(Book.listRecipes().toArray(new String[0]));
				break;
			case 21:
				combx2a_ingredient.removeAllItems();
				for (String i : Storage.listIngredients()) {
					combx2a_ingredient.addItem(i);
				}
				combx2a_ingredient.insertItemAt("Select", 0);
				combx2a_ingredient.setSelectedIndex(0);
				break;
			case 3:
				if (state_filteringActive == false) {
					list3_mustHave.setListData(Storage.listIngredients().toArray(new String[0]));
					list3_avoidAllergens.setListData(Storage.listAllergens().toArray(new String[0]));
				}
				break;
		

				default: throw new IllegalArgumentException();
			}
		}
	}// end method refreshList
	
	private void addIngredient(boolean modify){
		String name = txtf1a_name.getText();
		double amount = ((SpinnerNumberModel) spin1a_amount.getModel()).getNumber().doubleValue();
		Unit unit = (Unit) combx1a_unit.getSelectedItem();
		Date expiration = null;
		if (spin1a_expiration.isEnabled()) {
			expiration = (Date) spin1a_expiration.getValue();
		}
		
		TreeSet<String> allergen = new TreeSet<>();
		
		for (int i = 0; i < 10; i++) {
			String temp = (String) table1a_allergens.getValueAt(i, 0);
			if (temp != null)
				allergen.add(temp);
		}
		if (table1a_allergens.isEditing()) {
			table1a_allergens.getCellEditor().stopCellEditing();
			String unfinished = (String) ((CellEditor)table1a_allergens.getCellEditor()).getCellEditorValue();
			if ((unfinished != null) && (unfinished.equals("") == false))
				allergen.add(unfinished);
		}
		
		if (modify == false && (name != null) && (name.equals("") == false)){
			if (Storage.hasIngredient(name))
				if (Dialogs.warningOC(
						"The ingredient called " + name + " already exists. Do you want to overwrite?") == 2)
					return;
		}
		
		try {
			Storage.setIngredient(name, amount, unit, allergen, expiration);
			String original = list1_ingredients.getSelectedValue();
			if (name.equals(original) == false){
				Book.renameIngredient(original, name);
				Storage.deleteIngredient(original);
			}
		} catch (Exception exception) {
			Dialogs.notice(exception.toString());
			return;
		}
		clearFields(1,2,11);
		refreshLists(1,21,3);

		togglePanels(true);
		iframe1_add.setVisible(false);
	}// end method addIngredient
	
	private void addRecipe(boolean modify){
		String name = txtf2a_name.getText();
		String instruction = text2a_instruction.getText();
		LinkedHashMap<String, Double> ingredients = new LinkedHashMap<>();
		if (combx2a_ingredient.getSelectedIndex() != 0)
			ingredients.put((String) combx2a_ingredient.getSelectedItem(),
					((SpinnerNumberModel) spin2a_amount.getModel()).getNumber().doubleValue());
		for (int i = 1; i <= state_counter2a - 1; i++) {
			if (combx2a_temp[i].getSelectedIndex() != 0)
				ingredients.put((String) combx2a_temp[i].getSelectedItem(),
						((SpinnerNumberModel) spin2a_temp[i].getModel()).getNumber().doubleValue());
		}
		
		if (modify == false && (name != null) && (name.equals("") == false)){
			if (Book.hasRecipe(name))
				if (Dialogs
						.warningOC("The recipe called " + name + " already exists. Do you want to overwrite?") == 2)
					return;
		}
		
		try {
			Book.setRecipe(name, instruction, ingredients);
			if (name.equals(list2_recipes.getSelectedValue()) == false) Book.deleteRecipe(list2_recipes.getSelectedValue());
		} catch (Exception exception) {
			Dialogs.notice(exception.toString());
			return;
		}

		clearFields(2,21);
		refreshLists(2);
		togglePanels(true);
		state_counter2a = 0;
		iframe2_add.setVisible(false);
	}// end method addRecipe
	
	private void deleteIngredient(){
		String iname = list1_ingredients.getSelectedValue();
		
		if (iname == null) {
			Dialogs.notice("You must first select an ingredient.");
			return;
		}

		TreeSet<String> temp = Book.whoHas(iname);
		if (temp.isEmpty() == false) {
			if (Dialogs.warningOC(
					"If you delete ingredient in here, it will also be deleted from all recipes.\nThe following recipes will be affected: "
							+ temp.toString()) == 2)
				return;
		}
		Storage.deleteIngredient(iname);
		Book.deleteAllIngredients(iname);

		clearFields();
		refreshLists(1,2,3,21);
	}// end method deleteIngredient
	
	private void deleteRecipe(){
		String rname = list2_recipes.getSelectedValue();

		if (rname == null) {
			Dialogs.notice("You must first select a recipe.");
			return;
		}

		Book.deleteRecipe(rname);
		clearFields(2);
		refreshLists(2);
	}// end method deleteRecipe


	
	/**
	 * This method will add/remove input rows in {@link recipeTool.GUI#iframe2_add}
	 * @param value number if rows to be added(+) or removed(-).
	 */
	private void rows(int value){
		if (value == 0) return;
		for (int z = Math.abs(value); z >= 1; z--){
			if (value > 0) {
				if (state_counter2a < 2)
					state_counter2a = 1;
				btn2a_less.setEnabled(true);
				combx2a_temp[state_counter2a] = new JComboBox<>(
						Storage.listIngredients().toArray(new String[0]));
				combx2a_temp[state_counter2a].insertItemAt("Select", 0);
				combx2a_temp[state_counter2a].setSelectedIndex(0);
				combx2a_temp[state_counter2a].setBackground(Color.WHITE);
				combx2a_temp[state_counter2a].addActionListener(handler_button);
				iframe2_add.getContentPane().add(combx2a_temp[state_counter2a],
						"growx,cell 2 " + (1 + state_counter2a));
				spin2a_temp[state_counter2a] = new JSpinner();
				spin2a_temp[state_counter2a].setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
				spin2a_temp[state_counter2a]
						.setEditor(new JSpinner.NumberEditor(spin2a_temp[state_counter2a], "##0.0#"));
				iframe2_add.getContentPane().add(spin2a_temp[state_counter2a],
						"growx,cell 3 " + (1 + state_counter2a));
				lbl2a_temp[state_counter2a] = new JLabel();
				iframe2_add.getContentPane().add(lbl2a_temp[state_counter2a],
						"growx,cell 4 " + (1 + state_counter2a));
				if (state_counter2a < 10)
					state_counter2a++;
				if (state_counter2a == 10)
					btn2a_more.setEnabled(false);
				Dimension prefSize = iframe2_add.getLayout().preferredLayoutSize(rootPane);
				iframe2_add.setPreferredSize(prefSize);
				iframe2_add.revalidate();
			}
			if (value < 0) {
				iframe2_add.remove(combx2a_temp[state_counter2a - 1]);
				iframe2_add.remove(spin2a_temp[state_counter2a - 1]);
				iframe2_add.remove(lbl2a_temp[state_counter2a - 1]);
				state_counter2a--;
				if (state_counter2a < 2)
					btn2a_less.setEnabled(false);
				btn2a_more.setEnabled(true);
				Dimension prefSize = iframe2_add.getLayout().preferredLayoutSize(rootPane);
				iframe2_add.setPreferredSize(prefSize);
				iframe2_add.revalidate();
			}
		}
	}// end method rows
	
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
			String ip = txtf0_ip.getText();
			String port = String.valueOf(spin0_port.getValue());
			String user = txtf0_user.getText();
			String pass = String.valueOf(txtf0_pass.getPassword());
			try {
				Settings.writeSettings(ip,port,user,pass);
			} catch (Exception exception) {
				Dialogs.error(
						"The application was unable to save your preferences, "
						+ "and they will be forgotten when you exit.");
			}
				Database.setup(ip, port, user, pass, "recipe_tool");
			try {
				RecipeTool.prepareDatabase();
				Dialogs.notice("Connected Successfully!");
			} catch (Exception exception) {
				Database.close();
				Dialogs.error("Database setup did not succeed.\n" + exception);
			} finally {
				testConnection();
			}
			break;
			
		case SAVE:
			try {
				RecipeTool.save();
				Dialogs.notice("Saved Successfully!");
			} catch (Exception exception) {
				Database.close();
				Dialogs.error("Saving failed!\n" + exception);
			} finally {
				testConnection();
			}
			break;
			
		case LOAD:
			try {
				Storage.clear();
				Book.clear();
				refreshLists();
				clearFields();
				RecipeTool.load();
				refreshLists();
				Dialogs.notice("Loaded Successfully!");
			} catch (Exception exception) {
				Database.close();
				Dialogs.error("Loading failed!\n" + exception);
				exception.printStackTrace();
			} finally {
				testConnection();
			}
			break;
			
		case TEST:
			try {
				RecipeTool.test();
				refreshLists();
				Dialogs.notice("Test Values Loaded Successfully!");
			} catch (Exception exception) {
				Database.close();
				Dialogs.error("Loading failed!\n" + exception);
			} finally {
				testConnection();
			}
			break;
			
		default: throw new IllegalArgumentException();
		}
	}
	
	

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
		 * This method will run, when an item selection is changed. The method
		 * will invoke {@link recipeTool.GUI#refreshFields(int...)}
		 */
		@Override
		public void valueChanged(ListSelectionEvent event) {
			try {
				Object source = event.getSource();
				if (source == list1_ingredients) refreshFields(1);
				if (source == list2_recipes) refreshFields(2);
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
		 * The method will further delegate the adding, modifying and deleting
		 * of ingredients and recipes.<br>
		 * The method will also initiate recipe filtering.<br>
		 * The method will also initiate loading from and saving to database.
		 */
		@Override
		public void actionPerformed(ActionEvent event){
			try{
				Object source = event.getSource();
				
				// DELETE
				if (source == btn1_delete) {
					deleteIngredient();
				}
				else if (source == btn2_delete) {
					deleteRecipe();
				}
				
				// ADD
				else if (source == btn1a_ok) {
					addIngredient(iframe1_add.getTitle().equals("Modify Ingredient"));
				}
	
				else if (source == btn2a_ok) {
					addRecipe(iframe2_add.getTitle().equals("Modify Recipe"));
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
	
				else if (source == btn0_settings_save) {
					maria(DatabaseCommand.SAVE);
				}
	
				else if (source == btn0_settings_load) {
					maria(DatabaseCommand.LOAD);
				}
	
				else if (source == btn0_settings_test) {
					maria(DatabaseCommand.TEST);
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
					clearFields(11);
					togglePanels(true);
				}
				
				else if (source == btn2a_cancel) {
					iframe2_add.setVisible(false);
					clearFields(21);
					state_counter2a = 0;
					togglePanels(true);
				}
	
				else if (source == btn0_settings_close) {
					iframe0_settings.setVisible(false);
					clearFields(0);
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
					String iname = list1_ingredients.getSelectedValue();
	
					if (iname == null) {
						Dialogs.notice("You must first select an ingredient.");
						return;
					}
					iframe1_add.setTitle("Modify Ingredient");
					btn1a_ok.setText("Modify");
					
					refreshFields(12);
					
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
	
					if (rname == null) {
						Dialogs.notice("You must first select a recipe.");
						return;
					}
					iframe2_add.setTitle("Modify Recipe");
					btn2a_ok.setText("Modify");
					
					refreshFields(22);
					
					togglePanels(false);
					if (Book.listIngredients(rname).size() <= 1) btn2a_less.setEnabled(false);
					iframe2_add.setVisible(true);
				}
				
				else if (source == btn2_prepare) {
					String rname = list2_recipes.getSelectedValue();
					
					if (rname == null) {
						Dialogs.notice("You must first select a recipe.");
						return;
					}
					
					if (Book.isEnough(rname) == false) {
						Dialogs.notice("Cannot prepare this recipe, there is not enough ingredients in storage.");
						return;
					}
					
					if (Dialogs.choiceOC("You are preparing a recipe, the amounts in storage will be adjusted automatically. Proceed?") == 2) return;
					for (String iname : Book.listIngredients(rname)){
						Storage.reduce(iname, Book.getAmount(rname, iname));
					}
					Dialogs.notice("Storage values adjusted. Bon appétit!");
					clearFields(1);
					
				}
	
				else if (source == btn0_settings_open) {
					togglePanels(false);
					testConnection();
					String[] temp = Database.getParameters();
					txtf0_ip.setText(temp[0]);
					try {
						spin0_port.setValue(Integer.valueOf(temp[1]));
					} catch (Exception exception) {
						spin0_port.setValue(0);
					}
					txtf0_user.setText(temp[2]);
					txtf0_pass.setText(temp[3]);
					btn0_settings_open.setEnabled(false);
					iframe0_settings.setVisible(true);
				}
	
				// FILTER
				else if (source == btn3_filter) {
					state_filteringActive = (state_filteringActive ? false : true);
	
					clearFields(2);
					refreshLists(2);
					boolean able = !state_filteringActive;
	
					for (Container c : containerArrayPanels) {
						for (Component c2 : c.getComponents()) {
							if (c2 instanceof AbstractButton)
								c2.setEnabled(able);
						}
					}
					list3_avoidAllergens.setEnabled(able);
					list3_mustHave.setEnabled(able);
					lbl3_noShopping.setEnabled(able);
					lbl3_sortBy.setEnabled(able);
	
					lbl2_filteringActive
							.setText((state_filteringActive ? "Filtering ON" : "Filtering OFF"));
					if (state_filteringActive == false) {
						list2_recipes.setEnabled(true);
						testConnection();
					}
					btn3_filter.setEnabled(true);
				}
	
				// INTERACTIVE UI
				else if ((source == combx2a_ingredient)
						&& ((combx2a_ingredient.getSelectedIndex() > 0))) {
					lbl2a_unit
							.setText(Storage.getUnit(combx2a_ingredient.getSelectedItem().toString()).toString());
				}
	
				else if (source == chckbx1a_noExpiry) {
					spin1a_expiration.setEnabled(!chckbx1a_noExpiry.isSelected());
				}
				
				else if (source instanceof JComboBox<?>) {
					JComboBox<?> temp = (JComboBox<?>) source;
					int j = 0;
					for (JComboBox<String> i : combx2a_temp) {
						if (temp.equals(i)) {
							lbl2a_temp[j].setText(Storage.getUnit(i.getSelectedItem().toString()).toString());
							break;
						}
						j++;
					}
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
		 * User is asked if he would like to save changes.<br>
		 * If the saving fails, user is given a chance to cancel the exiting.
		 */
		@Override
		public void windowClosing(WindowEvent e) {
			try {
				int answer = Dialogs.choiceYNC("You are exiting the application. Do you want to save changes to database?");
				switch (answer) {

				case 0: // yes
					try {
						RecipeTool.save();
					} catch (Exception e1) {
						Database.close();
						if (Dialogs.warningYN("Saving failed. Do you still want to exit?\n" + e1) == 1)
							return;
						else
							System.exit(0);
					} finally {
						testConnection();
					}
					System.exit(0);
					break;

				case 1: // no
					System.exit(0);
					break;

				case 2: // cancel
					return;
				default: // this should not happen
					throw new IllegalArgumentException();
				}
			} catch (Exception e1) {
				System.exit(1);
			}
		}// end method windowClosing

		/**
		 * This method is inherited from superclass, but is not used in this
		 * implementation.
		 */
		@Override
		public void windowOpened(WindowEvent e) {
		}

		/**
		 * This method is inherited from superclass, but is not used in this
		 * implementation.
		 */
		@Override
		public void windowClosed(WindowEvent e) {
		}

		/**
		 * This method is inherited from superclass, but is not used in this
		 * implementation.
		 */
		@Override
		public void windowIconified(WindowEvent e) {
		}

		/**
		 * This method is inherited from superclass, but is not used in this
		 * implementation.
		 */
		@Override
		public void windowDeiconified(WindowEvent e) {
		}

		/**
		 * This method is inherited from superclass, but is not used in this
		 * implementation.
		 */
		@Override
		public void windowActivated(WindowEvent e) {
		}

		/**
		 * This method is inherited from superclass, but is not used in this
		 * implementation.
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
		 * This method runs, when a icon_connected JTextArea resizes. It will then
		 * also resize the parent JInternalFrame, so that there is neither too
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
