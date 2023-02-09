package sample;

/** First-level-division */
public class FirstLvlDiv {

    /** id of division */
    private int divId;
    /** name of division */
    private String div;
    /** country id of division */
    private int countryId;

    public FirstLvlDiv(int divId, String div, int countryId) {
        this.divId = divId;
        this.div = div;
        this.countryId = countryId;
    }

    /** gets division id */
    public int getDivId() {
        return divId;
    }

    /** sets division id */
    public void setDivId(int divId) {
        this.divId = divId;
    }

    /** gets division */
    public String getDiv() {
        return div;
    }

    /** sets division */
    public void setDiv(String div) {
        this.div = div;
    }

    /** gets division country id */
    public int getCountryId() {
        return countryId;
    }

    /** sets division country id */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
