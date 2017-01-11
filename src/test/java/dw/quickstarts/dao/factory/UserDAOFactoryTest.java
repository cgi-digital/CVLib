package dw.quickstarts.dao.factory;

import dw.quickstarts.dao.UserDAO;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class UserDAOFactoryTest {
    @Test
    public void factoryReturnsConstructorObject() {
        UserDAO userDAO = mock(UserDAO.class);

        final UserDAOFactory testObj = new UserDAOFactory(userDAO);

        assertSame("UserDAO should be the same object passed into the constructor",
                testObj.provide(), userDAO);
    }


}
