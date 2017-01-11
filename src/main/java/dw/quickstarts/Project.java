package dw.quickstarts;

/**
 * Created by koskinasm on 11/01/2017.
 */
public class Project {


    private final long id;
    private final long userid;

    private String employer;
    private String projectName;
    private String role;
    private String summary;

    public Project(long id, long userid, String employer, String projectName, String role, String summary)
    {
        this.id = id;
        this.userid = userid;
        this.employer = employer;
        this.projectName = projectName;
        this.role = role;
        this.summary = summary;
    }

    public long getId() {
        return id;
    }

    public long getUserid() {
        return userid;
    }

    public String getEmployer() {
        return employer;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getRole() {
        return role;
    }

    public String getSummary() {
        return summary;
    }

}
