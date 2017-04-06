package recipeTool;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
	private final JInternalFrame iframe1_add = new JInternalFrame("Add Ingredient");
	private final JInternalFrame iframe2_add = new JInternalFrame("Add recipe");
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
	private final ImageIcon connected = new ImageIcon(GUI.class.getResource("/recipeTool/images/connected.png"));
	private final ImageIcon disconnected = new ImageIcon(GUI.class.getResource("/recipeTool/images/disconnected.png"));

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
	private final JLabel lbl2_enough = new JLabel("Enough");
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
	private final JButton btn2_add = new JButton("Add recipe");
	private final JButton btn2_delete = new JButton("Delete recipe");
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
	private boolean filteringActive = false;

	// COUNTER
	private int counter2a = 0;

	// HANDLERS
	private final ButtonHandler buttonhandler = new ButtonHandler();
	private final ListHandler listhandler = new ListHandler();
	private final WindowHandler windowhandler = new WindowHandler();
	private final TextHandler texthandler = new TextHandler();

	// CONSTRUCTOR
	// **************************************************************************************************************************
	public GUI() {
		super("recipeTool");
		setContentPane(new JDesktopPane());

		this.containerArrayAll = new java.awt.Container[] { this.panel_1, this.panel_2, this.panel_3, this.panel_4,
				this.iframe1_add.getContentPane(), this.iframe2_add.getContentPane(),
				this.iframe0_settings.getContentPane() };
		this.containerArrayIframes = new java.awt.Container[] { this.iframe0_settings.getContentPane(),
				this.iframe1_add.getContentPane(), this.iframe2_add.getContentPane() };
		this.containerArrayPanels = new java.awt.Container[] { this.panel_1, this.panel_2, this.panel_3, this.panel_4 };

		migLayout();
		config();
		testConnection();
		refreshLists();

	}// end constructor

	// MIGLAYOUT
	// ***********************************************************************************************************************
	private void migLayout() {

		// ROOT

		Container root = getContentPane();
		root.setLayout(new MigLayout("fill,insets 0"));
		root.add(this.tabbedPane, "north, h 80%");
		root.add(this.panel_4, "south, h 20%");
		root.add(this.iframe1_add, "pos 20% 20%");
		root.add(this.iframe2_add, "pos 20% 20%");
		root.add(this.iframe0_settings, "pos 20% 20%");

		Container c;

		c = this.panel_4;
		c.setLayout(new MigLayout());
		c.add(this.btn0_settings_open);
		c.add(this.btn0_settings_load);
		c.add(this.btn0_settings_save);
		c.add(this.lbl_connection);

		c = this.iframe0_settings.getContentPane();
		c.setLayout(new MigLayout("", "[4cm,fill|2cm,fill|4cm,fill|4cm,fill]", "top"));
		c.add(this.lbl0_ip);
		c.add(this.lbl0_port);
		c.add(this.lbl0_user);
		c.add(this.lbl0_pass, "wrap");
		c.add(this.txtf0_ip);
		c.add(this.spin0_port);
		c.add(this.txtf0_user);
		c.add(this.txtf0_pass, "wrap");
		c.add(this.btn0_settings_connect, "span 4, split 3");
		c.add(this.btn0_settings_test);
		c.add(this.btn0_settings_close);

		c = this.panel_1;
		c.setLayout(new MigLayout("flowy",
				"[align right,10% | align left, 20%,fill | align left, 20%,fill | align left]", "10%"));
		c.add(this.lbl1_amount);
		c.add(this.lbl1_unit);
		c.add(this.lbl1_allergens);
		c.add(this.lbl1_expiration, "wrap");
		c.add(this.text1_amount);
		c.add(this.text1_unit);
		c.add(this.text1_allergens);
		c.add(this.text1_expiration, "wrap");
		c.add(this.list1_ingredients, "grow, spany 4,wrap");
		c.add(this.btn1_add, "split 2");
		c.add(this.btn1_delete);

		c = this.iframe1_add.getContentPane();
		c.setLayout(new MigLayout("", "[5cm,fill|3cm,fill|1cm,fill|5cm,fill|3cm,fill]", "top"));
		c.add(this.lbl1a_name);
		c.add(this.lbl1a_amount);
		c.add(this.lbl1a_unit);
		c.add(this.lbl1a_allergens);
		c.add(this.lbl1a_expiration, "wrap");
		c.add(this.txtf1a_name);
		c.add(this.spin1a_amount);
		c.add(this.combx1a_unit);
		c.add(this.table1a_allergens);
		c.add(this.spin1a_expiration, "wrap");
		c.add(this.btn1a_ok, "span 2, split 2");
		c.add(this.btn1a_cancel);

		c = this.iframe2_add.getContentPane();
		c.setLayout(new MigLayout("", "[5cm,grow 0|7cm,grow 0|5cm|2cm|1cm]", "top"));
		c.add(this.lbl2a_name);
		c.add(this.lbl2a_instruction);
		c.add(this.lbl2a_ingredient);
		c.add(this.lbl2a_amount);
		c.add(this.lbl2a_unit_header, "wrap");
		c.add(this.txtf2a_name, "growx");
		c.add(this.text2a_instruction, "growx");
		c.add(this.combx2a_ingredient, "growx");
		c.add(this.spin2a_amount, "growx");
		c.add(this.lbl2a_unit, "wrap");
		c.add(this.btn2a_more, "span 2,split 2,grow 0,left");
		c.add(this.btn2a_less, "wrap,grow 0");
		c.add(this.btn2a_ok, "span 2,split 2,grow 0,left");
		c.add(this.btn2a_cancel, "grow 0");

		c = this.panel_2;
		c.setLayout(new MigLayout("flowy,fillx, align left,insets panel",
				"[align right,10% | align left,20%,fill | align left,20%,fill | align left | growprio 1000]", "10%"));
		c.add(this.panel_3, "dock east,w 30%,wmax 30%,grow");
		c.add(this.lbl2_ingredients);
		c.add(this.lbl2_insturctions);
		c.add(this.lbl2_enough);
		c.add(this.lbl2_allergens);
		c.add(this.lbl2_expiration, "wrap");
		c.add(this.text2_ingredients);
		c.add(this.text2_instruction);
		c.add(this.lbl2_enoughValue);
		c.add(this.text2_allergens);
		c.add(this.text2_expiration, "wrap");
		c.add(this.list2_recipes, "grow,spany 5,wrap");
		c.add(this.btn2_add, "split 2");
		c.add(this.btn2_delete, "wrap");

		c = this.panel_3;
		c.setLayout(new MigLayout("flowy,insets panel", "[50%,fill]", "[25%,fill|25%,fill|||]"));
		c.setBackground(Color.WHITE);
		c.add(this.list3_mustHave, "grow,spanx 2");
		c.add(this.list3_avoidAllergens, "grow,spanx 2");
		c.add(this.lbl3_noShopping);
		c.add(this.lbl3_sortBy);
		c.add(this.btn3_filter, "span 2");
		c.add(this.lbl2_filteringActive, "span 2,grow 0,center,wrap");
		c.add(this.chckbx3_noShopping);
		c.add(this.rdbtn3_az, "spanx 2, split 2");
		c.add(this.rdbtn3_ex);

	}// end method migLayout

	// CONFIG
	// *******************************************************************************************************************************
	private void config() {

		// ROOT
		addWindowListener(this.windowhandler);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 1200, 800);
		setLocationRelativeTo(null);

		// TABS
		this.tabbedPane.addTab("Ingredients", null, this.panel_1, null);
		this.tabbedPane.addTab("Recipes", null, this.panel_2, null);

		// RADIO BUTTONS
		this.rdbtn3_az.setSelected(true);
		this.rdbtn3_ex.setSelected(false);

		// LABELS
		final Font font14 = new Font("Tahoma", Font.BOLD, 14);

		for (Component c : this.tabbedPane.getComponents()) {
			for (Component c2 : ((JPanel) c).getComponents()) {
				if (c2 instanceof JLabel)
					((JLabel) c2).setFont(font14);
			}
		}

		this.lbl2_filteringActive.setFont(new Font("Tahoma", Font.BOLD, 18));

		// LISTS
		this.list1_ingredients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list2_recipes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list3_avoidAllergens.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.list3_mustHave.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		this.list1_ingredients.setBorder(new TitledBorder(null, "Ingredients", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("Tahoma", Font.BOLD, 18), null));
		this.list2_recipes.setBorder(new TitledBorder(null, "Recipes", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("Tahoma", Font.BOLD, 18), null));
		this.list3_mustHave.setBorder(new TitledBorder(null, "Must have ingredients", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 18), null));
		this.list3_avoidAllergens.setBorder(new TitledBorder(null, "Avoid allergens", TitledBorder.LEADING,
				TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 18), null));

		this.list1_ingredients.addListSelectionListener(this.listhandler);
		this.list2_recipes.addListSelectionListener(this.listhandler);

		// BUTTONS
		for (java.awt.Container c : this.containerArrayAll) {
			for (Component c2 : c.getComponents()) {
				if (c2 instanceof AbstractButton)
					((AbstractButton) c2).addActionListener(this.buttonhandler);
				else if (c2 instanceof JComboBox)
					((JComboBox<?>) c2).addActionListener(this.buttonhandler);
			}
		}

		// COLOR
		for (java.awt.Container c : this.containerArrayAll) {
			c.setBackground(Color.WHITE);
			for (Component c2 : c.getComponents()) {
				if (!(c2 instanceof AbstractButton) || (c2 instanceof JRadioButton) || (c2 instanceof JCheckBox))
					c2.setBackground(Color.WHITE);
			}

		}

		// TEXTAREAS
		for (Container c : this.containerArrayAll) {
			for (Component c2 : c.getComponents()) {
				if (c2 instanceof JTextArea) {
					((JTextArea) c2).setEditable(false);
					((JTextArea) c2).setLineWrap(true);
					((JTextArea) c2).setWrapStyleWord(true);
					((JTextArea) c2).setBorder(new LineBorder(Color.BLACK));
				}
			}
		}

		for (Container c : this.containerArrayIframes) {
			for (Component c2 : c.getComponents()) {
				if (c2 instanceof JTextArea) {
					((JTextArea) c2).addComponentListener(this.texthandler);
					((JTextArea) c2).setEditable(true);
				}
			}
		}

		// SPINNERS
		this.spin1a_amount.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
		this.spin1a_amount.setEditor(new JSpinner.NumberEditor(this.spin1a_amount, "##0.0#"));

		this.spin1a_expiration.setModel(new SpinnerDateModel(new Date(), new Date(946677600921L),
				new Date(32535122400921L), Calendar.DAY_OF_YEAR));
		this.spin1a_expiration.setEditor(new JSpinner.DateEditor(this.spin1a_expiration, "d.M.y"));

		this.spin2a_amount.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
		this.spin2a_amount.setEditor(new JSpinner.NumberEditor(this.spin2a_amount, "##0.0#"));

		this.spin0_port.setModel(new SpinnerNumberModel(0, 0, 65535, 1));

		// TABLE
		DefaultTableModel temp = new DefaultTableModel(10, 1);
		this.table1a_allergens.setModel(temp);
		this.table1a_allergens.setBorder(new LineBorder(Color.BLACK));

	}// end method config

	// GUI METHODS
	// **************************************************************************************************************************

	// TOGGLE PANELS
	/**
	 * This method will enable/disable all components on all panels found in {@link recipeTool.GUI#containerArrayPanels}
	 * @param enable {@code true} to enable all panels<br>
	 * <blockquote>{@code false} to disable all panels</blockquote>
	 */
	private void togglePanels(boolean enable) {
		for (Container c : this.containerArrayPanels) {
			for (Component c2 : c.getComponents()) {
				c2.setEnabled(enable);
			}
		}
	}// end method togglePanels

	//TEST CONNECTION
	/**
	 * This method will test if there is a valid connection established to a database,<br>
	 * using {@link recipeTool.Database#isConnected()}<p>
	 * Connection Icon is set accordingly, and save/load buttons enabled or disabled.
	 * @return Nothing.
	 */
	private void testConnection() {
		boolean established = Database.isConnected();
		GUI.this.btn0_settings_load.setEnabled(established);
		GUI.this.btn0_settings_save.setEnabled(established);
		GUI.this.btn0_settings_test.setEnabled(established);
		GUI.this.lbl_connection.setIcon(established == true ? this.connected : this.disconnected);
	}
	
	// CLEAR FIELDS
	private void clearFields(int tabNumber) {
		java.awt.Container container = null;
		Dimension prefSize;
		switch (tabNumber) {

		case 0: // settings
			container = this.iframe0_settings.getContentPane();
			break;

		case 1: // ingredients tab
			container = this.panel_1;
			break;

		case 2: // recipes tab
			container = this.panel_2;
			this.lbl2_enoughValue.setText("");
			break;

		case 3: // filter panel
			container = this.panel_3;
			break;

		case 11: // add ingredient internal frame
			container = this.iframe1_add.getContentPane();
			break;

		case 21: // add recipe internal frame
			container = this.iframe2_add.getContentPane();
			this.btn2a_more.setEnabled(true);
			this.btn2a_less.setEnabled(false);
			for (JComboBox<?> i : this.combx2a_temp) {
				if (i != null)
					this.iframe2_add.remove(i);
			}
			for (JSpinner j : this.spin2a_temp) {
				if (j != null)
					this.iframe2_add.remove(j);
			}
			for (JLabel k : this.lbl2a_temp) {
				if (k != null)
					this.iframe2_add.remove(k);
			}
			break;
		default: // this should not happen
			throw new IllegalArgumentException();
		}
		
		// clear every component in selected container
		for (Component c : container.getComponents()) {
			if (c instanceof JTextArea || c instanceof JTextField) ((JTextComponent) c).setText("");
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
		Container iframe = container.getParent().getParent().getParent();
		if (iframe instanceof JInternalFrame) {
			prefSize = iframe.getLayout().preferredLayoutSize(this.rootPane);
			iframe.setPreferredSize(prefSize);
			iframe.revalidate();
		}
	}// end method clearFields

	// REFRESH FIELDS
	private void refreshFields() {

		String iname = this.list1_ingredients.getSelectedValue();
		if ((iname != null) && (Storage.hasIngredient(iname))) {
			this.text1_unit.setText(Storage.getUnit(iname).toString());
			this.text1_amount.setText(((Double) new BigDecimal(Storage.getAmount(iname))
					.setScale(2, RoundingMode.HALF_UP).doubleValue()).toString());

			if (Storage.getAllergens(iname) == null)
				this.text1_allergens.setText("no allergens");
			else
				this.text1_allergens.setText(String.join(", ", Storage.getAllergens(iname)));

			if (Storage.getExpiration(iname) == null)
				this.text1_expiration.setText("does not expire");
			else
				this.text1_expiration.setText(DateFormat.getDateInstance().format(Storage.getExpiration(iname)));
		}
		String rname = this.list2_recipes.getSelectedValue();
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
			this.text2_ingredients.setText(temp.toString().trim());

			if (Book.getInsturction(rname) == null || Book.getInsturction(rname).equals(""))
				this.text2_instruction.setText("no instruction");
			else
				this.text2_instruction.setText(Book.getInsturction(rname));

			if (Book.getAllergens(rname) == null || Book.getAllergens(rname).isEmpty())
				this.text2_allergens.setText("no allergens");
			else
				this.text2_allergens.setText(String.join(", ", Book.getAllergens(rname)));

			if (Book.getExpiration(rname)[0] == null)
				this.text2_expiration.setText("no expiring ingredients");
			else
				this.text2_expiration
						.setText(String.join("", DateFormat.getDateInstance().format(Book.getExpiration(rname)[0]), " ",
								(String) Book.getExpiration(rname)[1]));

			this.lbl2_enoughValue.setText(Boolean.toString(Book.getEnough(rname)));
		}
	}// end method refreshFields
	
	// REFRESH LIST
	private void refreshLists() {
		this.list1_ingredients.setListData(Storage.listIngredients().toArray(new String[0]));
		this.combx2a_ingredient.removeAllItems();
		for (String i : Storage.listIngredients()) {
			this.combx2a_ingredient.addItem(i);
		}
		this.combx2a_ingredient.insertItemAt("Select", 0);
		this.combx2a_ingredient.setSelectedIndex(0);
		if (this.filteringActive == false) {
			this.list3_mustHave.setListData(Storage.listIngredients().toArray(new String[0]));
			this.list3_avoidAllergens.setListData(Storage.listAllergens().toArray(new String[0]));
			this.list2_recipes.setListData(Book.listRecipes().toArray(new String[0]));
			return;
		}
	
		List<String> mustHave = this.list3_mustHave.getSelectedValuesList();
		List<String> avoidAllergens = this.list3_avoidAllergens.getSelectedValuesList();
		boolean enough = this.chckbx3_noShopping.isSelected();
		boolean sort = this.rdbtn3_az.isSelected();
		String[] filterResult = Book.filterRecipes(mustHave, avoidAllergens, enough, sort);
		if (filterResult == null){
			this.list2_recipes.setListData(new String[] { "no matches found" });
			this.list2_recipes.setEnabled(false);
		}
		else this.list2_recipes.setListData(filterResult);


	}// end method refreshList

	// EVENT HANDLERS
	// ***********************************************************************************************************************

	// LISTHANDLER
	private class ListHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			refreshFields();
		}// end method valueChanged
	}// end class ListHandler

	// BUTTONHANDLER
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();

			// CLOSE
			if (source == GUI.this.btn1a_cancel) {
				GUI.this.iframe1_add.setVisible(false);
				clearFields(11);
				togglePanels(true);
			}

			else if (source == GUI.this.btn2a_cancel) {
				GUI.this.iframe2_add.setVisible(false);
				clearFields(21);
				GUI.this.counter2a = 0;
				togglePanels(true);
			}

			else if (source == GUI.this.btn0_settings_close) {
				GUI.this.iframe0_settings.setVisible(false);
				clearFields(0);
				GUI.this.btn0_settings_open.setEnabled(true);
				togglePanels(true);
			}

			// OPEN
			else if (source == GUI.this.btn1_add) {
				togglePanels(false);
				GUI.this.iframe1_add.setVisible(true);
			}

			else if (source == GUI.this.btn2_add) {
				togglePanels(false);
				GUI.this.btn2a_less.setEnabled(false);
				GUI.this.iframe2_add.setVisible(true);
			}

			else if (source == GUI.this.btn0_settings_open) {
				togglePanels(false);
				testConnection();
				GUI.this.btn0_settings_open.setEnabled(false);
				GUI.this.iframe0_settings.setVisible(true);
			}

			// RADIO BUTTONS
			else if (source == GUI.this.rdbtn3_az) {
				GUI.this.rdbtn3_az.setSelected(true);
				GUI.this.rdbtn3_ex.setSelected(false);
			} else if (source == GUI.this.rdbtn3_ex) {
				GUI.this.rdbtn3_az.setSelected(false);
				GUI.this.rdbtn3_ex.setSelected(true);
			}

			// DELETE
			else if (source == GUI.this.btn1_delete) {
				String iname = GUI.this.list1_ingredients.getSelectedValue();

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

				clearFields(1);
				refreshLists();
				refreshFields();
			}

			else if (source == GUI.this.btn2_delete) {
				String rname = GUI.this.list2_recipes.getSelectedValue();

				if (rname == null) {
					Dialogs.notice("You must first select a recipe.");
					return;
				}

				Book.deleteRecipe(rname);
				clearFields(2);
				refreshLists();

			}

			// FILTER
			else if (source == GUI.this.btn3_filter) {
				GUI.this.filteringActive = (GUI.this.filteringActive == true ? false : true);

				clearFields(2);
				refreshLists();
				boolean able = !GUI.this.filteringActive;
				
				for (Container c : containerArrayPanels){
					for (Component c2 : c.getComponents()){
						if (c2 instanceof AbstractButton) c2.setEnabled(able);
					}
				}
				GUI.this.list3_avoidAllergens.setEnabled(able);
				GUI.this.list3_mustHave.setEnabled(able);
				GUI.this.lbl3_noShopping.setEnabled(able);
				GUI.this.lbl3_sortBy.setEnabled(able);

				GUI.this.lbl2_filteringActive
						.setText((GUI.this.filteringActive == true ? "Filtering ON" : "Filtering OFF"));
				if(filteringActive == false) {
					list2_recipes.setEnabled(true);
					testConnection();
				}
				btn3_filter.setEnabled(true);
			}

			// DATABASE
			else if (source == GUI.this.btn0_settings_connect) {
				String ip = GUI.this.txtf0_ip.getText();
				String port = String.valueOf(GUI.this.spin0_port.getValue());
				String user = GUI.this.txtf0_user.getText();
				String pass = String.valueOf(GUI.this.txtf0_pass.getPassword());
				ArrayList<String> settings = new ArrayList<>();
				settings.add(ip);
				settings.add(port);
				settings.add(user);
				settings.add(pass);
				if (Settings.writeSettings(settings) == false)
					Dialogs.error(
							"The application was unable to save your preferences, and they will be forgotten when you exit.");
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
			}

			else if (source == GUI.this.btn0_settings_save) {
				try {
					RecipeTool.save();
					Dialogs.notice("Saved Successfully!");
				} catch (Exception exception) {
					Database.close();
					Dialogs.error("Saving failed!\n" + exception);
				} finally {
					testConnection();
				}
			}

			else if (source == GUI.this.btn0_settings_load) {
				try {
					RecipeTool.load();
					refreshLists();
					Dialogs.notice("Loaded Successfully!");
				} catch (Exception exception) {
					Database.close();
					Dialogs.error("Loading failed!\n" + exception);
				} finally {
					testConnection();
				}
			}

			else if (source == GUI.this.btn0_settings_test) {
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
			}

			// ADD
			else if (source == GUI.this.btn1a_ok) {
				String name = GUI.this.txtf1a_name.getText();
				if (name.equals("")) {
					Dialogs.notice("The name cannot be empty.");
					return;
				}
				if (Storage.hasIngredient(name))
					if (Dialogs.warningOC(
							"The ingredient called " + name + " already exists. Do you want to overwrite?") == 2)
						return;

				SpinnerNumberModel temp8 = (SpinnerNumberModel) GUI.this.spin1a_amount.getModel();
				double amount = temp8.getNumber().doubleValue();
				Unit unit = (Unit) GUI.this.combx1a_unit.getSelectedItem();
				TreeSet<String> allergen = new TreeSet<>();
				String temp;

				for (int i = 0; i < 10; i++) {
					temp = (String) GUI.this.table1a_allergens.getValueAt(i, 0);
					if (temp != null)
						allergen.add(temp);
				}
				Date expiration = null;
				expiration = (Date) GUI.this.spin1a_expiration.getValue();
				if (GUI.this.table1a_allergens.isEditing()) {
					CellEditor temp2 = GUI.this.table1a_allergens.getCellEditor();
					GUI.this.table1a_allergens.getCellEditor().stopCellEditing();
					String unfinished = (String) temp2.getCellEditorValue();
					if (unfinished != null)
						allergen.add(unfinished);
				}
				Storage.setIngredient(name, amount, unit, allergen, expiration);
				clearFields(11);
				refreshLists();

				togglePanels(true);
				GUI.this.iframe1_add.setVisible(false);
			}

			else if (source == GUI.this.btn2a_ok) {

				String name = GUI.this.txtf2a_name.getText();
				if (name.equals("")) {
					Dialogs.notice("The name cannot be empty.");
					return;
				}

				if (Book.hasRecipe(name))
					if (Dialogs
							.warningOC("The recipe called " + name + " already exists. Do you want to overwrite?") == 2)
						return;
				String instruction = GUI.this.text2a_instruction.getText();
				LinkedHashMap<String, Double> ingredients = new LinkedHashMap<>();
				if (GUI.this.combx2a_ingredient.getSelectedIndex() != 0)
					ingredients.put((String) GUI.this.combx2a_ingredient.getSelectedItem(),
							((SpinnerNumberModel) GUI.this.spin2a_amount.getModel()).getNumber().doubleValue());
				for (int i = 1; i <= GUI.this.counter2a - 1; i++) {
					if (GUI.this.combx2a_temp[i].getSelectedIndex() != 0)
						ingredients.put((String) GUI.this.combx2a_temp[i].getSelectedItem(),
								((SpinnerNumberModel) GUI.this.spin2a_temp[i].getModel()).getNumber().doubleValue());
				}
				if (ingredients.isEmpty()) {
					Dialogs.notice("Cannot create a recipe with no ingredients.");
					return;
				}
				Book.setRecipe(name, instruction, ingredients);

				clearFields(21);
				refreshLists();
				togglePanels(true);
				GUI.this.counter2a = 0;
				GUI.this.iframe2_add.setVisible(false);
			}

			// TEMPORARY UI COMPONENTS
			else if (source == GUI.this.btn2a_more) {

				if (GUI.this.counter2a < 2)
					GUI.this.counter2a = 1;
				GUI.this.btn2a_less.setEnabled(true);

				GUI.this.combx2a_temp[GUI.this.counter2a] = new JComboBox<>(
						Storage.listIngredients().toArray(new String[0]));
				GUI.this.combx2a_temp[GUI.this.counter2a].insertItemAt("Select", 0);
				GUI.this.combx2a_temp[GUI.this.counter2a].setSelectedIndex(0);
				GUI.this.combx2a_temp[GUI.this.counter2a].setBackground(Color.WHITE);
				GUI.this.combx2a_temp[GUI.this.counter2a].addActionListener(GUI.this.buttonhandler);

				GUI.this.iframe2_add.getContentPane().add(GUI.this.combx2a_temp[GUI.this.counter2a],
						"growx,cell 2 " + (1 + GUI.this.counter2a));

				GUI.this.spin2a_temp[GUI.this.counter2a] = new JSpinner();
				GUI.this.spin2a_temp[GUI.this.counter2a].setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 0.01));
				GUI.this.spin2a_temp[GUI.this.counter2a]
						.setEditor(new JSpinner.NumberEditor(GUI.this.spin2a_temp[GUI.this.counter2a], "##0.0#"));

				GUI.this.iframe2_add.getContentPane().add(GUI.this.spin2a_temp[GUI.this.counter2a],
						"growx,cell 3 " + (1 + GUI.this.counter2a));

				GUI.this.lbl2a_temp[GUI.this.counter2a] = new JLabel();
				GUI.this.iframe2_add.getContentPane().add(GUI.this.lbl2a_temp[GUI.this.counter2a],
						"growx,cell 4 " + (1 + GUI.this.counter2a));

				if (GUI.this.counter2a < 10)
					GUI.this.counter2a++;
				if (GUI.this.counter2a == 10)
					GUI.this.btn2a_more.setEnabled(false);
				Dimension prefSize = GUI.this.iframe2_add.getLayout().preferredLayoutSize(GUI.this.rootPane);
				GUI.this.iframe2_add.setPreferredSize(prefSize);
				GUI.this.iframe2_add.revalidate();
			}

			else if (source == GUI.this.btn2a_less) {
				GUI.this.iframe2_add.remove(GUI.this.combx2a_temp[GUI.this.counter2a - 1]);
				GUI.this.iframe2_add.remove(GUI.this.spin2a_temp[GUI.this.counter2a - 1]);
				GUI.this.iframe2_add.remove(GUI.this.lbl2a_temp[GUI.this.counter2a - 1]);
				GUI.this.counter2a--;
				if (GUI.this.counter2a < 2)
					GUI.this.btn2a_less.setEnabled(false);
				GUI.this.btn2a_more.setEnabled(true);
				Dimension prefSize = GUI.this.iframe2_add.getLayout().preferredLayoutSize(GUI.this.rootPane);
				GUI.this.iframe2_add.setPreferredSize(prefSize);
				GUI.this.iframe2_add.revalidate();
			}

			// INTERACTIVE UNIT CHANGING
			else if ((source == GUI.this.combx2a_ingredient)
					&& ((GUI.this.combx2a_ingredient.getSelectedIndex() > 0))) {
				GUI.this.lbl2a_unit
						.setText(Storage.getUnit(GUI.this.combx2a_ingredient.getSelectedItem().toString()).toString());
			}

			else if (source instanceof JComboBox<?>) {
				JComboBox<?> temp = (JComboBox<?>) source;
				int j = 0;
				for (JComboBox<String> i : GUI.this.combx2a_temp) {
					if (temp.equals(i)) {
						GUI.this.lbl2a_temp[j].setText(Storage.getUnit(i.getSelectedItem().toString()).toString());
						break;
					}
					j++;
				}
			}
		}// end method actionPerformed
	}// end class ButtonHandler

	// WINDOWHANDLER
	/**
	 * This class handles events for the main application window.<br>
	 * Only the closing event is implemented, while other inherited methods are ignored.
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
		}// end method windowClosing

		@Override
		public void windowOpened(WindowEvent e) {
			/* not used */}

		@Override
		public void windowClosed(WindowEvent e) {
			/* not used */}

		@Override
		public void windowIconified(WindowEvent e) {
			/* not used */}

		@Override
		public void windowDeiconified(WindowEvent e) {
			/* not used */}

		@Override
		public void windowActivated(WindowEvent e) {
			/* not used */}

		@Override
		public void windowDeactivated(WindowEvent e) {
			/* not used */}
	}// end class WindowHandler

	/**
	 * This class handles events for JTextAreas.
	 * @author Ville Salmela
	 *
	 */
	private class TextHandler extends ComponentAdapter {
		
		/**
		 * This method runs, when a connected JTextArea resizes.
		 * It will then also resize the parent JInternalFrame, so that there is
		 * neither too much nor little space.
		 */
		@Override
		public void componentResized(ComponentEvent e) {
			JTextArea t = ((JTextArea) e.getSource());
			if (t.getText().equals(""))
				return;

			JInternalFrame c = (JInternalFrame) t.getParent().getParent().getParent().getParent();
			Dimension prefSize = c.getLayout().preferredLayoutSize(GUI.this.rootPane);
			c.setPreferredSize(prefSize);
			c.revalidate();

		}//end method componentResized
	}// end class TextHandler
}// end class GUI
