package recipeTool;

import javax.swing.JOptionPane;

public class Dialogs {

	//NOTICE	
	public static void notice(String message){
		JOptionPane.showMessageDialog(null, message, "Notice", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//ERROR	
	public static void error(String message){
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	//WARNING_OC
	public static int warningOC(String message){
		return JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	}
	
	//WARNING_YN	
	public static int warningYN(String message){
		return JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	}

	//CHOICE_YNC
	public static int choiceYNC(String message){
		return JOptionPane.showConfirmDialog(null, message, "Attention", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}


	
}
