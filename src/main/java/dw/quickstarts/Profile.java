package dw.quickstarts;

import java.util.List;

/**
 * Created by koskinasm on 10/01/2017.
 */
public class Profile {

    private long id;
    private String email;
    private String baseLocation;
    private String firstName;
    private String lastName;
    private String title;
    private String summary;
    private boolean admin;
    private boolean disabled;
    private List<UserSkillView> skills;
    private List<Qualification> qualifications;
    private List<Project> projects;
    private List<PhoneNumber> phoneNumbers;

    private Profile(){}

    public static Profile getListProfile(User user)
    {
        Profile profile = new Profile();
        profile.id = user.getId();
        profile.email = user.getEmail();
        profile.firstName = user.getFirstName();
        profile.lastName = user.getLastName();
        profile.title = user.getTitle();
        profile.admin = user.isAdmin();
        profile.disabled = user.isDisabled();
        profile.baseLocation = user.getBaselocation();

        return profile;
    }

    public static Profile getFullProfile(User user, List<UserSkillView> skills, List<Qualification> qualifications, List<Project> projects, List<PhoneNumber> phoneNumbers)
    {
        Profile profile = new Profile();
        profile.id = user.getId();
        profile.email = user.getEmail();
        profile.baseLocation = user.getBaselocation();
        profile.firstName = user.getFirstName();
        profile.lastName = user.getLastName();
        profile.title = user.getTitle();
        profile.summary = user.getSummary();
        profile.admin = user.isAdmin();
        profile.disabled = user.isDisabled();
        profile.skills = skills;
        profile.qualifications = qualifications;
        profile.projects = projects;
        profile.phoneNumbers = phoneNumbers;

        return profile;
    }

    private Profile(User user)
    {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.title = user.getTitle();
        this.summary = user.getSummary();
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
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

    public List<UserSkillView> getSkills() {
        return skills;
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }
}
