package dw.quickstarts;

/**
 * Created by callumbarnes on 07/12/2017.
 */
public class PhoneNumber {

    private String phonenumber;
    private String type;
    private long userID;

    public PhoneNumber(String phonenumber, String type, long userID) {
        this.phonenumber = phonenumber;
        this.type = type;
        this.userID = userID;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getType() {
        return type;
    }

    public long getUserID() {
        return userID;
    }
}
