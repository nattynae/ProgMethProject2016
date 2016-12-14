/**
* @author Pornkrit Watcharasatianphan and Phanu Vajanopath
* @version 14 Dec 2016
* Project (1/2016) in 2110215 Prog Meth
*/
package utility;

public class NameFormatException extends Exception {

	private static final long serialVersionUID = 1L;
	private int errorType;

	public NameFormatException(int errorType) {
		this.errorType = errorType;
	}

	@Override
	public String getMessage() {
		if (this.errorType == 0)
			return "Same names";
		else
			return "Wrong name format";

	}

}