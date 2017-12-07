package dw.quickstarts;

/**
 * Created by callumbarnes on 07/12/2017.
 */
public class UserSkillView {

    private final long userid;
    private final long skillid;
    private int level;
    private String skillName;
    private String type;

    public UserSkillView(long userid, long skillid, int level, String skillName, String type) {
        this.userid = userid;
        this.skillid = skillid;
        this.level = level;
        this.skillName = skillName;
        this.type = type;
    }

    public long getUserid() {
        return userid;
    }

    public long getSkillid() {
        return skillid;
    }

    public int getLevel() {
        return level;
    }

    public String getSkillName() {
        return skillName;
    }

    public String getType() {
        return type;
    }
}
