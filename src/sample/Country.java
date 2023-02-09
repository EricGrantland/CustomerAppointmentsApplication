package sample;

/** Country */
public class Country {

    /** id of country */
    private int countryId;
    /** name of country */
    private String country;

    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /** gets country id */
    public int getCountryId() {
        return countryId;
    }

    /** sets country id */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /** gets country */
    public String getCountry() {
        return country;
    }

    /** sets country */
    public void setCountry(String country) {
        this.country = country;
    }

    /** returns country name */
    @Override
    public String toString() {
        return country;
    }
}
