package dw.quickstarts;

/**
 * Created by koskinasm on 11/01/2017.
 */
public class Qualification {

    private final long id;
    private final long userid;
    private String qualification;


    public Qualification(long id, long userid, String qualification) {
        this.id = id;
        this.userid = userid;
        this.qualification = qualification;
    }

    public long getId() {
        return id;
    }

    public long getUserid() {
        return userid;
    }

    public String getQualification() {
        return qualification;
    }
}
