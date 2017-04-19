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

	/**
	 * This method puts in order two recipes, according to their expiration dates.
	 * @return 0 if both have same date or no dates.<br>
	 * 1 if first recipe has no date, or has later date than second<br>
	 * -1 if second recipe has no date, or has later date than first
	 */
	@Override
	public int compare(String o1, String o2) {
		
		//Prepare objects to be compared
		Object[] tempA = RecipeTool.getBook().getExpiration(o1);
		Object[] tempB = RecipeTool.getBook().getExpiration(o2);
		Date temp1 = (Date) tempA[0];
		Date temp2 = (Date) tempB[0];
		
		// If 1 and 2 don't have expiration date, return 0
		if ((temp1 == null) && (temp2 == null)) return 0;
		
		// If only 1 doesn't have expiration date, return 1
		if (temp1 == null) return 1;
		
		// If only 2 doesn't have expiration date, return -1
		if (temp2 == null) return -1;

		// If 1 expires sooner than 2, return -1
		if (temp1.before(temp2))
			return -1;
		
		// If 2 expires sooner than 1, return 1
		else if (temp1.after(temp2))
			return 1;
		
		// If 1 and 2 expire at the same date, return 0
		else
			return 0;
	}//end method compare
}//end class Expiry