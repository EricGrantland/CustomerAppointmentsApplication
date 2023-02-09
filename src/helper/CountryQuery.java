package helper;

import sample.Country;
import sample.Data;
import sample.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryQuery {

    /**
     * Pulls Countries from MySql database
     * @throws SQLException
     */
    public static void select() throws SQLException {
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Country country;
        while (rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            country = new Country(countryId, countryName);
            Data.addCountry(country);
        }


    }
}
