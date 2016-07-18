package mohamedibrahim.networklib.services.Exception;

/**
 * Created by mohamed.ibrahim on 7/11/2016.
 */

public class NoConnectionException extends Exception {

    private int messageResId;
    private String message;

    public NoConnectionException(String message) {
        this.message = message;
    }


    public NoConnectionException(int messageResId) {
        this.messageResId = messageResId;
    }


}
