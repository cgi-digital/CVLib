package dw.quickstarts;

/**
 * Created by callumbarnes on 31/10/2017.
 */
public class SFIASkill {

    private final long id;
    private String lang;
    private int version;
    private String category;
    private String subCategory;
    private String skill;
    private String description;
    private String code;
    private int level;
    private String description22;

    public SFIASkill(long id, String lang, int version, String category, String subCategory, String skill, String description, String code, int level, String description22) {
        this.id = id;
        this.lang = lang;
        this.version = version;
        this.category = category;
        this.subCategory = subCategory;
        this.skill = skill;
        this.description = description;
        this.code = code;
        this.level = level;
        this.description22 = description22;
    }

    public SFIASkill(long id, String skill, int level)
    {
        this.id = id;
        this.skill = skill;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public String getLang() {
        return lang;
    }

    public int getVersion() {
        return version;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getSkill() {
        return skill;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription22() {
        return description22;
    }
}
