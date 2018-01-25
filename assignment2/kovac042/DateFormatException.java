package assignment2;

public class DateFormatException extends Exception {
    
    /**
	 * Serial version of this exception
	 */
	private static final long serialVersionUID = 4875816801935922508L;
	
	public DateFormatException() {}
    public DateFormatException(String message) {
        super(message);
    }
}
