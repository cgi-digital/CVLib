package dw.quickstarts;

import dw.quickstarts.dao.ProjectDAO;
import dw.quickstarts.dao.QualificationDAO;
import dw.quickstarts.dao.SkillDAO;
import dw.quickstarts.dao.UserDAO;
import dw.quickstarts.dao.factory.ProjectDAOFactory;
import dw.quickstarts.dao.factory.QualificationDAOFactory;
import dw.quickstarts.dao.factory.SkillDAOFactory;
import dw.quickstarts.resources.AdminConsoleResource;
import dw.quickstarts.resources.SkillResource;
import dw.quickstarts.tasks.GetHashedPasswordCommand;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.eclipse.jetty.server.session.SessionHandler;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dw.quickstarts.dao.factory.UserDAOFactory;
import dw.quickstarts.filters.LoginRequiredFeature;
import dw.quickstarts.mappers.ForbiddenExceptionMapper;
import dw.quickstarts.resources.UserResource;
import dw.quickstarts.resources.SecurityResource;
import dw.quickstarts.tasks.SetUserPassword;

public class AuthenticationQuickstartApplication extends Application<AuthenticationQuickstartConfiguration> {
    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationQuickstartApplication.class);

    public static void main(String[] args) throws Exception {
        new AuthenticationQuickstartApplication().run(args);
    }

    @Override
    public String getName() {
        return "Dropwizard Authentication Quickstart";
    }

    @Override
    public void initialize(Bootstrap<AuthenticationQuickstartConfiguration> bootstrap) {
        bootstrap.addCommand(new GetHashedPasswordCommand());
        bootstrap.addCommand(new SetUserPassword(bootstrap.getApplication()));

        bootstrap.addBundle(new MigrationsBundle<AuthenticationQuickstartConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(AuthenticationQuickstartConfiguration configuration) {
                return configuration.getDatabase();
            }
        });

        bootstrap.addBundle(new ViewBundle<>());
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(AuthenticationQuickstartConfiguration configuration, Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDatabase(), "h2");
        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        final UserDAOFactory userDAOFactory = new UserDAOFactory(userDAO);

        final SkillDAO skillDAO = jdbi.onDemand(SkillDAO.class);
        final SkillDAOFactory skillDAOFactory = new SkillDAOFactory(skillDAO);

        final QualificationDAO qualificationDAO = jdbi.onDemand(QualificationDAO.class);
        final QualificationDAOFactory qualificationDAOFactory = new QualificationDAOFactory(qualificationDAO);

        final ProjectDAO projectDAO = jdbi.onDemand(ProjectDAO.class);
        final ProjectDAOFactory projectDAOFactory = new ProjectDAOFactory(projectDAO);

        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindFactory(userDAOFactory).to(UserDAO.class)
                        .proxy(true).proxyForSameScope(false).in(RequestScoped.class);
                bindFactory(skillDAOFactory).to(SkillDAO.class)
                        .proxy(true).proxyForSameScope(false).in(RequestScoped.class);
                bindFactory(qualificationDAOFactory).to(QualificationDAO.class)
                        .proxy(true).proxyForSameScope(false).in(RequestScoped.class);
                bindFactory(projectDAOFactory).to(ProjectDAO.class)
                        .proxy(true).proxyForSameScope(false).in(RequestScoped.class);
            }
        });

        // Rename the session cookie identifier
        SessionHandler sh = new SessionHandler();
        sh.getSessionManager().getSessionCookieConfig().setName(configuration.getSessionCookieName());
        sh.getSessionManager().getSessionCookieConfig().setHttpOnly(true);
        environment.servlets().setSessionHandler(sh);

        environment.jersey().register(LoginRequiredFeature.class);
        environment.jersey().register(ForbiddenExceptionMapper.class);

        // Resources
        environment.jersey().register(new UserResource(userDAO,skillDAO,qualificationDAO,projectDAO));
        environment.jersey().register(new SecurityResource(userDAO));
        environment.jersey().register(new AdminConsoleResource(userDAO,qualificationDAO,projectDAO));
        environment.jersey().register(new SkillResource(skillDAO));

        environment.jersey().setUrlPattern("/api/*");

//        UserResource test = new UserResource(userDAO,skillDAO,qualificationDAO,projectDAO);
//        test.testArray();


    }
}
