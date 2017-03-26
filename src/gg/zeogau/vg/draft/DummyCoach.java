package gg.zeogau.vg.draft;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by zeogau on 3/18/17.
 * This class is to provide functionality of coaching with randomize picks and bans.
 */
public class DummyCoach {


    public static Map<String, Integer> coachMeSenpai (final List<String> selected) {
        List<HeroModel> list =  HeroModel.getHeroes();
        Random rand = new Random(System.currentTimeMillis());
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (HeroModel h : list) {
            if (selected.contains(h.getHeroName()))
                h.setSelected(true);
            else {
                int value  = rand.nextInt(10) + 1;  //randomly assign 1-10
                map.put(h.getHeroName(), value);
            }
        }

        return map;
    }
}
