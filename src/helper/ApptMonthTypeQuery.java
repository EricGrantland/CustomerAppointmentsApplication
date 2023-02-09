package helper;

import sample.ApptMonthType;
import sample.Data;
import sample.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Month;

public class ApptMonthTypeQuery {

    /**
     * Pulls Appointments of a given Month and Type
     * @throws SQLException
     */
    public static void select() throws SQLException {
        String sql = "SELECT Type, Start FROM appointments;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ApptMonthType appt;
        while (rs.next()) {
            String type = rs.getString("Type");
            Timestamp date = rs.getTimestamp("Start");
            appt = new ApptMonthType(type, date.toLocalDateTime().toLocalDate());
            System.out.println("AMT Object:\ntype: " + appt.getType() + "\nmonth: " + appt.getMonth() + "\nyear: " + appt.getYear());
            Data.addApptMonthType(appt);
        }


    }
}
