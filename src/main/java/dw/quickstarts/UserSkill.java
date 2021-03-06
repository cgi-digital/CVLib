package dw.quickstarts;

/**
 * Created by callumbarnes on 07/12/2017.
 */
public class UserSkill {

    private final long userid;
    private final long skillid;
    private int level;

    public UserSkill(long userid, long skillid, int level) {
        this.userid = userid;
        this.skillid = skillid;
        this.level = level;
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
}
