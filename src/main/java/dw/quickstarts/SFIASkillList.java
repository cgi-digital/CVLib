package dw.quickstarts;

/**
 * Created by callumbarnes on 01/11/2017.
 */
public class SFIASkillList {

    private final long id;
    private String skill;
    private int level;

    public SFIASkillList(long id, String skill, int level) {
        this.id = id;
        this.skill = skill;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public String getSkill() {
        return skill;
    }

    public int getLevel() {
        return level;
    }
}
