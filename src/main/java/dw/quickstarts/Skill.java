package dw.quickstarts;

/**
 * Created by koskinasm on 11/01/2017.
 */
public class Skill {

    private final long id;
    private String skill;
    private int minLevel;
    private int maxLevel;
    private String sfiaCode;
    private String type;


    public Skill(long id, String skill, int minLevel, int maxLevel, String sfiaCode, String type) {
        this.id = id;
        this.skill = skill;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.sfiaCode = sfiaCode;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getSkill() {
        return skill;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public String getSfiaCode() {
        return sfiaCode;
    }

    public String getType() {
        return type;
    }
}
