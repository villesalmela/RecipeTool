package recipeTool;

import java.util.Comparator;
import java.util.Date;

public class Expiry implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		Object[] tempA = Book.getExpiration(o1);
		Object[] tempB = Book.getExpiration(o2);
		
		Date temp1 = (Date) tempA[0];
		Date temp2 = (Date) tempB[0];
		
		if (temp1.before(temp2)) return -1;
		else if (temp1.after(temp2)) return 1;
		else return 0;
	}

}
