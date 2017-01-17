package todoexpert.lesiecki.com.todoexpert;

/**
 * Created by meep_lesp on 17.01.2017.
 */
public class ErrorResponse {
    private int code;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
