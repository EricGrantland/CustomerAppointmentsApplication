package helper;

import javafx.collections.ObservableList;
import sample.Appointment;
import sample.Contact;
import sample.Data;
import sample.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactQuery {

    /**
     * Pulls Contacts from MySql database
     * @throws SQLException
     */
    public static void select() throws SQLException {
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Contact contact;
        while (rs.next()) {
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            contact = new Contact(contactId, contactName, email);
            Data.addContact(contact);
        }


    }

}
