package gg.zeogau.vg.draft;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zeogau on 3/18/17.
 */

@ViewScoped
@ManagedBean(name = "theBean")
public class Bean implements Serializable{

    private List<HeroModel> listHero = null;
    private Map<String, HeroModel> mapHero = null;

    public List<HeroModel> getList() {
        return listHero;
    }

    private String blueBan = "glory.png";
    private String blue1 = "glory.png";
    private String blue2 = "glory.png";
    private String blue3 = "glory.png";
    private String redBan = "glory.png";
    private String red1 = "glory.png";
    private String red2 = "glory.png";
    private String red3 = "glory.png";
    private String blueBan2 = "glory.png";
    private String redBan2 = "glory.png";
    private Integer numBans = 1;
    private Integer numColumns = 4;

    private String blueOdd ;
    private String redOdd ;

    public Bean() {
        listHero = HeroModel.getHeroes();
        mapHero = new HashMap<String, HeroModel>();
        for (HeroModel h : listHero) {
            mapHero.put(h.getHeroName(), h);
        }

        coachMe();
    }

    /**
     * This is to store heroes that have been selected
     */
    private List<String> selectedNames = new ArrayList<String>();



    private void helper (final String selectedName) {
        if (mapHero.get(selectedName).getSelected()) return; //can only select once
        String imagename = selectedName + ".gif";
        setImages (selectedNames.size(), imagename);
        if (selectedNames.size() <= 7 || (numBans==2 && selectedNames.size() <= 9)) {
            selectedNames.add(selectedName);
            mapHero.get(selectedName).setSelected(true);
        }
    }


    /**
     * Set images to a certain image based on stage of drafting
     * @param numOfHeroSelected
     * @param imagename
     */
    private void setImages (final int numOfHeroSelected, final String imagename) {
        if (numBans==1)
            switch (numOfHeroSelected) {
                case 0 : blueBan = imagename; break;
                case 1 : redBan = imagename; break;
                case 2 : blue1 = imagename; break;
                case 3 : red1 = imagename; break;
                case 4 : red2 = imagename; break;
                case 5 : blue2 = imagename; break;
                case 6 : blue3 = imagename; break;
                case 7 : red3 = imagename; break;
            }
        else if (numBans==2)
            switch (numOfHeroSelected) {
                case 0 : blueBan = imagename; break;
                case 1 : redBan = imagename; break;
                case 2 : blue1 = imagename; break;
                case 3 : red1 = imagename; break;
                case 4 : redBan2 = imagename; break;
                case 5 : blueBan2 = imagename; break;
                case 6 : red2 = imagename; break;
                case 7 : blue2 = imagename; break;
                case 8 : blue3 = imagename; break;
                case 9 : red3 = imagename; break;
            }

    }

    public void pick () {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String selectedName = context.getRequestParameterMap().get("null");
        helper(selectedName);
        coachMe();
    }

    public void backMeUp() {
        int numOfHeroes = selectedNames.size();
        if (numOfHeroes==0) return;
        String lastHeroPicked = selectedNames.remove(numOfHeroes - 1);
        String defaultimage = "glory.png";
        setImages (numOfHeroes - 1, defaultimage);
        mapHero.get(lastHeroPicked).setSelected(false);
        coachMe();
    }


    /**
     * This method is to start the draft process all over.
     */
    public void reset() {
        for (String name : selectedNames) {
            mapHero.get(name).setSelected(false);
        }
        selectedNames.clear();
        blueBan = "glory.png";
        blue1 = "glory.png";
        blue2 = "glory.png";
        blue3 = "glory.png";
        redBan = "glory.png";
        red1 = "glory.png";
        red2 = "glory.png";
        red3 = "glory.png";
        blueBan2 = "glory.png";
        redBan2 = "glory.png";
        coachMe();
    }



    public Integer getStep() { return selectedNames.size();}


    private void coachMe() {
        if ( (numBans==1 && selectedNames.size() > 7) || (numBans==2 && selectedNames.size() > 9) ) return;

        Map<String, Integer> suggestion = DummyCoach.coachMeSenpai (selectedNames);
//        Map<String, Integer> suggestion = draft.MetaMainframe.coachMeSenpai(numBans==1 ? "SINGLE_BAN" : "DOUBLE_BAN", selectedNames);
        for (String name : suggestion.keySet()) {
            if (name.equalsIgnoreCase("odds")) {
                Integer odd = suggestion.get("odds");
                blueOdd = Integer.toString(odd) + "%";
                redOdd = Integer.toString(100 - odd) + "%";
                continue;
            }
            mapHero.get(name.toLowerCase()).setPriority(suggestion.get(name));
        }
    }

    //**************************** LIST OF GETTERS SETTERS GOES BELOW

    public String getBlueBan() {
        return blueBan;
    }

    public void setBlueBan(String blueBan) {
        this.blueBan = blueBan;
    }

    public String getBlue1() {
        return blue1;
    }

    public void setBlue1(String blue1) {
        this.blue1 = blue1;
    }

    public String getBlue2() {
        return blue2;
    }

    public void setBlue2(String blue2) {
        this.blue2 = blue2;
    }

    public String getBlue3() {
        return blue3;
    }

    public void setBlue3(String blue3) {
        this.blue3 = blue3;
    }

    public String getRedBan() {
        return redBan;
    }

    public void setRedBan(String redBan) {
        this.redBan = redBan;
    }

    public String getRed1() {
        return red1;
    }

    public void setRed1(String red1) {
        this.red1 = red1;
    }

    public String getRed2() {
        return red2;
    }

    public void setRed2(String red2) {
        this.red2 = red2;
    }

    public String getRed3() {
        return red3;
    }

    public void setRed3(String red3) {
        this.red3 = red3;
    }

    public Integer getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(Integer numColumns) {
        this.numColumns = numColumns;
    }

    public String getBlueBan2() {
        return blueBan2;
    }

    public void setBlueBan2(String blueBan2) {
        this.blueBan2 = blueBan2;
    }

    public String getRedBan2() {
        return redBan2;
    }

    public void setRedBan2(String redBan2) {
        this.redBan2 = redBan2;
    }

    public Integer getNumBans() {
        return numBans;
    }

    public void setNumBans(Integer numBans) {
        this.numBans = numBans;
    }

    public String getBlueOdd() {
        return blueOdd;
    }

    public void setBlueOdd(String blueOdd) {
        this.blueOdd = blueOdd;
    }

    public String getRedOdd() {
        return redOdd;
    }

    public void setRedOdd(String redOdd) {
        this.redOdd = redOdd;
    }
}

