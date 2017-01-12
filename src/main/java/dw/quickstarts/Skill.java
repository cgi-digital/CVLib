package dw.quickstarts;

/**
 * Created by koskinasm on 11/01/2017.
 */
public class Skill {

    private final long id;
    private final long userid;
    private String skill;
    private int level;


    public Skill(long id, long userid, String skill, int level) {
        this.id = id;
        this.userid = userid;
        this.skill = skill;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public long getUserid() {
        return userid;
    }

    public String getSkill() {
        return skill;
    }

    public int getLevel() {
        return level;
    }
}
