package recipeTool;

/**
 * This exception is thrown, when invalid, or malformed data is encountered.<br>
 * If constructed using a String as parameter, this implementation will
 * precede that string with:<br>
 * "Data received from the database is invalid."<br>
 * and then append the string as a message.
 * @author Ville Salmela
 *
 */
public class InvalidDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1242000670475394218L;

	public InvalidDataException() {
	}

	public InvalidDataException(String message) {
		super(message);
	}
	
    @Override
	public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (s + ": " + "Data received from the database is invalid.\n" + message) : s;
    }

}//end class InvalidDataException
