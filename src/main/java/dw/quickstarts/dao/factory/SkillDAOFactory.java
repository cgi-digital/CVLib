package dw.quickstarts.dao.factory;

import dw.quickstarts.dao.QualificationDAO;
import dw.quickstarts.dao.SkillDAO;
import org.glassfish.hk2.api.Factory;

/**
 * Created by koskinasm on 11/01/2017.
 */
public class SkillDAOFactory implements Factory<SkillDAO> {

    private final SkillDAO skillDAO;

    public SkillDAOFactory(SkillDAO skillDAO) {
        this.skillDAO = skillDAO;
    }

    @Override
    public SkillDAO provide() {
        return skillDAO;
    }

    @Override
    public void dispose(SkillDAO skillDAO) {
    }
}
