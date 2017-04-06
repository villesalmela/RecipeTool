package recipeTool;

import javax.swing.JOptionPane;

/**
 * This class contains methods for invoking different dialogs.<br>
 * The following dialogs are available:<br>
 * -notice    | information message<br>
 * -error     | error message<br>
 * -warningOC | warning message, OK = 0, CANCEL = 2<br>
 * -warningYN | warning message, YES = 0, NO = 1<br>
 * -choiceYNC | question message, YES = 0, NO = 1, CANCEL = 2
 * 
 * @author Ville Salmela
 */
public class Dialogs {

	//NOTICE	
	/**
	 * Information message -dialog, with title: Notice
	 * @param message The message.
	 * @return Nothing.
	 */
	public static void notice(String message){
		JOptionPane.showMessageDialog(null, message, "Notice", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//ERROR	
	/**
	 * Error message -dialog, with title: Error
	 * @param message The message.
	 * @return Nothing.
	 */
	public static void error(String message){
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	//WARNING_OC
	/**
	 * Warning message -dialog, with title: Warning
	 * @param message The message.
	 * @return 0 = OK<br>2 = CANCEL
	 */
	public static int warningOC(String message){
		return JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	}
	
	//WARNING_YN	
	/**
	 * Warning message -dialog, with title: Warning
	 * @param message The message.
	 * @return 0 = YES<br>1 = NO
	 */
	public static int warningYN(String message){
		return JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	}

	//CHOICE_YNC
	/**
	 * Question message -dialog, with title: Attention
	 * @param message The message.
	 * @return 0 = YES<br>1 = NO<br>2 = CANCEL
	 */
	public static int choiceYNC(String message){
		return JOptionPane.showConfirmDialog(null, message, "Attention", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	}


	
}
