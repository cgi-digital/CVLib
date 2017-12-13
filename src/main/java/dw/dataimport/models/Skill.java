package dw.dataimport.models;

/**
 * Created by callumbarnes on 13/12/2017.
 */
public class Skill {

    private String skillName;
    private int minLevel;
    private int maxLevel;

    public Skill(String skillName, int minLevel, int maxLevel) {
        this.skillName = skillName;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }
}
