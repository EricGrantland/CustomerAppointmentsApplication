package helper;

import sample.Country;
import sample.Data;
import sample.FirstLvlDiv;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstlvlDivQuery {

    /**
     * Pulls First-level-divisions from MySql database
     * @throws SQLException
     */
    public static void select() throws SQLException {
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        FirstLvlDiv firstLvlDiv;
        while (rs.next()) {
            int divId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");
            firstLvlDiv = new FirstLvlDiv(divId, division, countryId);
            Data.addDivision(firstLvlDiv);
        }

    }

}
