package dw.quickstarts;

import java.util.List;

/**
 * Created by koskinasm on 10/01/2017.
 */
public class Profile {

    private long id;
    private String username;
    private String firstname;
    private String lastname;
    private String title;
    private String summary;
    private boolean admin;
    private boolean disabled;
    private List<Skill> skills;
    private List<Qualification> qualifications;
    private List<Project> projects;

    private Profile(){}

    public static Profile getListProfile(User user)
    {
        Profile profile = new Profile();
        profile.id = user.getId();
        profile.username = user.getUsername();
        profile.firstname = user.getFirstname();
        profile.lastname = user.getLastname();
        profile.title = user.getTitle();
        profile.admin = user.isAdmin();
        profile.disabled = user.isDisabled();

        return profile;
    }

    public static Profile getFullProfile(User user, List<Skill> skills, List<Qualification> qualifications, List<Project> projects)
    {
        Profile profile = new Profile();
        profile.id = user.getId();
        profile.username = user.getUsername();
        profile.firstname = user.getFirstname();
        profile.lastname = user.getLastname();
        profile.title = user.getTitle();
        profile.summary = user.getSummary();
        profile.admin = user.isAdmin();
        profile.disabled = user.isDisabled();
        profile.skills = skills;
        profile.qualifications = qualifications;
        profile.projects = projects;

        return profile;
    }

    private Profile(User user)
    {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.title = user.getTitle();
        this.summary = user.getSummary();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public List<Project> getProjects() {
        return projects;
    }

}
