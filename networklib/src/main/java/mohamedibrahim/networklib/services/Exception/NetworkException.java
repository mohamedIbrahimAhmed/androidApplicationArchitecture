package mohamedibrahim.networklib.services.Exception;

/**
 * Created by mohamed.ibrahim on 7/3/2016.
 */

public class NetworkException extends Exception {
    private int resId;
    private String message;

    public NetworkException(String message) {
        this.message = message;
    }

    public NetworkException(int messageResId) {
        this.resId = messageResId;
    }


    public int getMessageResId() {
        return resId;
    }

    public String getMessage() {
        return message;
    }

}
