package sample;

/** User */
public class User {

    /** id of user */
    private int userId;
    /** name of user */
    private String userName;
    /** password of user */
    private String password;

    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /** gets user id */
    public int getUserId() {
        return userId;
    }

    /** sets user id */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** gets user name */
    public String getUserName() {
        return userName;
    }

    /** sets user name */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** gets user password */
    public String getPassword() {
        return password;
    }

    /** sets user password */
    public void setPassword(String password) {
        this.password = password;
    }
}
