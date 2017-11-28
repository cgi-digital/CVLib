package dw.quickstarts.dao.factory;

import dw.quickstarts.dao.SFIASkillDAO;
import org.glassfish.hk2.api.Factory;

/**
 * Created by callumbarnes on 31/10/2017.
 */
public class SFIASkillDAOFactory implements Factory<SFIASkillDAO> {

    private final SFIASkillDAO sfiaSkillDAO;

    public SFIASkillDAOFactory(SFIASkillDAO sfiaSkillDAO) {
        this.sfiaSkillDAO = sfiaSkillDAO;
    }

    @Override
    public SFIASkillDAO provide() {
        return sfiaSkillDAO;
    }

    @Override
    public void dispose(SFIASkillDAO sfiaSkillDAO) {
    }

}
