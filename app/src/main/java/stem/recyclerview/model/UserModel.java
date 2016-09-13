package stem.recyclerview.model;

/**
 * Created by sandeep on 8/27/16.
 */

//another class to handle item's id and name
public class UserModel {

    public String emailId;
    public String password;

    // constructor
    public UserModel(String email_id, String password) {
        this.emailId = email_id;
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}