package dw.quickstarts.dao.factory;

import dw.quickstarts.Qualification;
import dw.quickstarts.dao.QualificationDAO;
import dw.quickstarts.dao.UserDAO;
import org.glassfish.hk2.api.Factory;

/**
 * Created by koskinasm on 11/01/2017.
 */
public class QualificationDAOFactory implements Factory<QualificationDAO> {

    private final QualificationDAO qualificationDAO;

    public QualificationDAOFactory(QualificationDAO qualificationDAO) {
        this.qualificationDAO = qualificationDAO;
    }

    @Override
    public QualificationDAO provide() {
        return qualificationDAO;
    }

    @Override
    public void dispose(QualificationDAO qualificationDAO) {
    }
}
