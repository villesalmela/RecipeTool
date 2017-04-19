package utilities;

/**
 * This exception is thrown, when invalid, or malformed data is encountered.<br>
 * If constructed using a String as parameter, this implementation will precede
 * that string with:<br>
 * "Data is invalid."<br>
 * and then append the string as a message.
 * 
 * @author Ville Salmela
 *
 */
public class InvalidDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1242000670475394218L;

	/**
	 * Constructs the exception with no message.
	 */
	public InvalidDataException() {}

	/**
	 * Constructs the exception with message.
	 * @param message The message.
	 */
	public InvalidDataException(String message) {
		super(message);
	}

	/**
	 * This method will create a string with information about the
	 * exception.<br>
	 * It consists of the exception class name, predefined message and a
	 * customizable message.<br>
	 * If the customizable message is not specified when instantiating the
	 * exception, only the class name is included.
	 * 
	 * @return if the
	 *         {@link utilities.InvalidDataException#getLocalizedMessage()} is
	 *         null: exception class name.<br>
	 *         if the
	 *         {@link utilities.InvalidDataException#getLocalizedMessage()} is
	 *         not null: "Data is invalid.\n" +
	 *         message
	 */
	@Override
	public String toString() {
		String s = getClass().getName();
		String message = getLocalizedMessage();
		return (message != null) ? (s + ": " + "Data is invalid.\n" + message) : s;
	}// end method toString
}// end class InvalidDataException
