package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Appointment;
import sample.Contact;
import sample.Customer;
import sample.Data;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AppointmentQuery {

    /**
     * Inserts Appointment into MySQL database
     * @throws SQLException
     * @return int
     * @param title - Appt title
     * @param description - Appt description
     * @param location - Appt location
     * @param type - Appt type
     * @param start - Appt start time
     * @param end - Appt end time
     * @param custId - Appt customer id
     * @param userId - Appt user id
     * @param contactId - Appt contact id
     */
    public static int insert(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end,
                             String custId, String userId, int contactId) throws SQLException {
        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        Timestamp start1 = Timestamp.valueOf(start);
        ps.setTimestamp(5, start1);
        Timestamp end1 = Timestamp.valueOf(end);
        ps.setTimestamp(6, end1);
        ps.setString(7, custId);
        ps.setString(8, userId);
        ps.setInt(9, contactId);

        return ps.executeUpdate();
    }

    /**
     * Pull all appointments for a given Contact
     * @throws SQLException
     * @return ObservableList of Appointments
     * @param contact - Contact you would like to view Appts of
     */
    public static ObservableList<Appointment> getAppointmentsByContact(Contact contact) throws SQLException {
        ObservableList<Appointment> contactAppts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contact.getContactId());
        ResultSet rs = ps.executeQuery();
        Appointment appointment;
        while (rs.next()) {
            int apptId = rs.getInt("Appointment_ID");
            String title1 = rs.getString("Title");
            String desc = rs.getString("Description");
            String location1 = rs.getString("Location");
            String type1 = rs.getString("Type");
            Timestamp start1 = rs.getTimestamp("Start");
            LocalDateTime start2 = start1.toLocalDateTime();
            Timestamp end1 = rs.getTimestamp("End");
            LocalDateTime end2 = end1.toLocalDateTime();
            int custId1 = rs.getInt("Customer_ID");
            int userId1 = rs.getInt("User_ID");
            int contactId1 = rs.getInt("Contact_ID");

            appointment = new Appointment(apptId, title1, desc, location1, type1, start2, end2, custId1, userId1, contactId1);
            contactAppts.add(appointment);
        }

        return contactAppts;
    }

    /**
     * Pulls all appointments for a given customer
     * @throws SQLException
     * @return Observable list of Appointments
     * @param cust - customer you would like to view Appts for
     */
    public static ObservableList<Appointment> getAppointmentsByCustomer(Customer cust) throws SQLException {
        ObservableList<Appointment> custAppts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, cust.getCustomerId());
        ResultSet rs = ps.executeQuery();
        Appointment appointment;
        while (rs.next()) {
            int apptId = rs.getInt("Appointment_ID");
            String title1 = rs.getString("Title");
            String desc = rs.getString("Description");
            String location1 = rs.getString("Location");
            String type1 = rs.getString("Type");
            Timestamp start1 = rs.getTimestamp("Start");
            LocalDateTime start2 = start1.toLocalDateTime();
            Timestamp end1 = rs.getTimestamp("End");
            LocalDateTime end2 = end1.toLocalDateTime();
            int custId1 = rs.getInt("Customer_ID");
            int userId1 = rs.getInt("User_ID");
            int contactId1 = rs.getInt("Contact_ID");

            appointment = new Appointment(apptId, title1, desc, location1, type1, start2, end2, custId1, userId1, contactId1);
            custAppts.add(appointment);
        }

        return custAppts;
    }

    /**
     * Updates Appointment info in the MySQL database
     * @throws SQLException
     * @return int
     * @param title - Appt title
     * @param description - Appt description
     * @param location - Appt location
     * @param type - Appt type
     * @param start - Appt start time
     * @param end - Appt end time
     * @param custId - Appt customer id
     * @param userId - Appt user id
     * @param contactId - Appt contact id
     */
    public static int update(int apptId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end,
                             String custId, String userId, int contactId) throws SQLException {
        String sql = "UPDATE appointments SET title = ?, Description = ?, Location = ?, type = ?, " +
                "Start = ?, end = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        Timestamp start1 = Timestamp.valueOf(start);
        ps.setTimestamp(5, start1);
        Timestamp end1 = Timestamp.valueOf(end);
        ps.setTimestamp(6, end1);
        ps.setString(7, custId);
        ps.setString(8, userId);
        ps.setInt(9, contactId);
        ps.setInt(10, apptId);

        return ps.executeUpdate();
    }


    /**
     * Deletes Appointment in MySQL database
     * @throws SQLException
     * @return int
     * @param apptId - ID of Appt being deleted
     */
    public static int delete(int apptId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, apptId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Deletes all Appointments in MySQL database linked to a customer that is being deleted
     * @throws SQLException
     * @return int
     * @param custId - ID of Customer whose appointments will be deleted
     */
    public static int deleteUsingCustId(int custId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, custId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Pulls all Appointments in MySQL database
     * @throws SQLException
     * @return Observable list of Appts
     */
    public static ObservableList<Appointment> select() throws SQLException {
        String sql2 = "SELECT * FROM appointments";
        PreparedStatement ps2 = JDBC.connection.prepareStatement(sql2);
        ResultSet rs = ps2.executeQuery();
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        Appointment appointment;
        while (rs.next()) {
            int apptId = rs.getInt("Appointment_ID");
            String title1 = rs.getString("Title");
            String desc = rs.getString("Description");
            String location1 = rs.getString("Location");
            String type1 = rs.getString("Type");
            Timestamp start1 = rs.getTimestamp("Start");
            LocalDateTime start2 = start1.toLocalDateTime();
            Timestamp end1 = rs.getTimestamp("End");
            LocalDateTime end2 = end1.toLocalDateTime();
            int custId1 = rs.getInt("Customer_ID");
            int userId1 = rs.getInt("User_ID");
            int contactId1 = rs.getInt("Contact_ID");

            appointment = new Appointment(apptId, title1, desc, location1, type1, start2, end2, custId1, userId1, contactId1);
            appointments.add(appointment);

        }
        return appointments;
    }

    /**
     * Pulls Appointments for a customer with the given customer ID
     * @throws SQLException
     * @param custId - customer id for which appointments will be pulled
     */
    public static void select(int custId) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, custId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int custIdfk = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divId = rs.getInt("Division_ID");

        }
    }

}
