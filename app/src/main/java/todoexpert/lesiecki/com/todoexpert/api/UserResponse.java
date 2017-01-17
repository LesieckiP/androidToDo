package todoexpert.lesiecki.com.todoexpert.api;

/**
 * Created by meep_lesp on 17.01.2017.
 */
public class UserResponse {
    private String objectId;
    private String sessionToken;
    private String username;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
