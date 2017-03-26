package gg.zeogau.vg.draft;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeogau on 3/18/17.
 */
public class HeroModel {
    private String heroName;
    private Boolean newHero = false;
    private Boolean selected = false;
    private Integer priority = 0;

    private static String [] heroes = new String[] {
            "Adagio", "Alpha", "Ardan", "Baron", "Blackfeather",
            "Catherine", "Celeste", "Flicker", "Fortress", "Glaive",

            "Grumpjaw",

            "Gwen", "Idris", "Joule", "Kestrel", "Koshka",
            "Krul", "Lance", "Lyra", "Ozo", "Petal",
            "Phinn", "Ringo", "Reim", "Rona", "Samuel",
            "Saw", "Skaarf", "Skye", "Taka", "Vox"

    };
    public static List<HeroModel> getHeroes() {
        List<HeroModel> list = new ArrayList<HeroModel>();
        int i = 0;
        for (String name : heroes) {
            HeroModel h = new HeroModel(name.toLowerCase());
            if (h.getHeroName().equalsIgnoreCase("grumpjaw")) h.setNewHero(true);
            list.add(h);
        }
        return list;
    }

    public HeroModel(final String heroName){
        this.heroName = heroName;
    }


    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public Boolean getNewHero() {
        return newHero;
    }

    public void setNewHero(Boolean newHero) {
        this.newHero = newHero;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
