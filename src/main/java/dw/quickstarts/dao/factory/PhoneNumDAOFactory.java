package dw.quickstarts.dao.factory;

import dw.quickstarts.dao.PhoneNumDAO;
import org.glassfish.hk2.api.Factory;

/**
 * Created by callumbarnes on 14/12/2017.
 */
public class PhoneNumDAOFactory implements Factory<PhoneNumDAO> {

    private final PhoneNumDAO phoneNumDAO;

    public PhoneNumDAOFactory(PhoneNumDAO phoneNumDAO) {
        this.phoneNumDAO = phoneNumDAO;
    }

    @Override
    public PhoneNumDAO provide() {
        return phoneNumDAO;
    }

    @Override
    public void dispose(PhoneNumDAO phoneNumDAO) {

    }

}
