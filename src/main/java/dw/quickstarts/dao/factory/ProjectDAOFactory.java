package dw.quickstarts.dao.factory;

import dw.quickstarts.dao.ProjectDAO;
import dw.quickstarts.dao.QualificationDAO;
import org.glassfish.hk2.api.Factory;

/**
 * Created by koskinasm on 11/01/2017.
 */
public class ProjectDAOFactory implements Factory<ProjectDAO> {

    private final ProjectDAO projectDAO;

    public ProjectDAOFactory(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    @Override
    public ProjectDAO provide() {
        return projectDAO;
    }

    @Override
    public void dispose(ProjectDAO projectDAO) {

    }
}
