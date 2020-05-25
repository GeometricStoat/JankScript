package geostoat.jankscript.shell.util;

public class IllegalTokenException extends Exception {
	private static final long serialVersionUID = 8170302573646963780L;

    public IllegalTokenException() {
        super();
    }

    public IllegalTokenException(String message) {
        super(message);
    }
    
    public IllegalTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalTokenException(Throwable cause) {
        super(cause);
    }

    protected IllegalTokenException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
