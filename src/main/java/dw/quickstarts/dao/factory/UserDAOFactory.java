package dw.quickstarts.dao.factory;

import dw.quickstarts.dao.UserDAO;
import org.glassfish.hk2.api.Factory;

public class UserDAOFactory implements Factory<UserDAO> {
    private final UserDAO userDAO;

    public UserDAOFactory(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDAO provide() {
        return userDAO;
    }

    @Override
    public void dispose(UserDAO userDAO) {

    }
}
