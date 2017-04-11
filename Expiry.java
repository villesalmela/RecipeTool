package recipeTool;

import java.util.Comparator;
import java.util.Date;

/**
 * This comparator is used to sort recipes according to their expiration
 * dates.<br>
 * This class uses {@link recipeTool.Book#getExpiration(String)} to fetch
 * the dates.
 * 
 * @author Ville Salmela
 *
 */
class Expiry implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		Object[] tempA = Book.getExpiration(o1);
		Object[] tempB = Book.getExpiration(o2);

		Date temp1 = (Date) tempA[0];
		Date temp2 = (Date) tempB[0];
		
		if ((temp1 == null) && (temp2 == null)) return 0;
		if (temp1 == null) return 1;
		if (temp2 == null) return -1;

		if (temp1.before(temp2))
			return -1;
		else if (temp1.after(temp2))
			return 1;
		else
			return 0;
	}//end method compare

}//end class Expiry