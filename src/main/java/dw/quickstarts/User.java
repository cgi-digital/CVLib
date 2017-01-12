package dw.quickstarts;

public class User {
    private final long id;
    private final String username;

    private String firstname;
    private String lastname;
    private String address;
    private String title;
    private String summary;

    private final String salt;
    private final String password;
    private final boolean admin;
    private final boolean disabled;

    public User(long id, String username, String firstname, String lastname, String address, String title, String summary, String salt, String password, boolean admin, boolean disabled) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.title = title;
        this.summary = summary;
        this.salt = salt;
        this.password = password;
        this.admin = admin;
        this.disabled = disabled;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public boolean isDisabled() {
        return this.disabled;
    }
}
