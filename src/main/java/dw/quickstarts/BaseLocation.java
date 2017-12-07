package dw.quickstarts;

/**
 * Created by callumbarnes on 07/12/2017.
 */
public class BaseLocation {

    private final String officecode;
    private String locationname;
    private String address;
    private String postcode;

    public BaseLocation(String officecode, String locationname, String address, String postcode) {
        this.officecode = officecode;
        this.locationname = locationname;
        this.address = address;
        this.postcode = postcode;
    }

    public String getOfficecode() {
        return officecode;
    }

    public String getLocationname() {
        return locationname;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }
}
