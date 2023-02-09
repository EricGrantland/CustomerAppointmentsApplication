package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.Customer;
import sample.Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerQuery {

    /**
     * Inserts Customer into the MySQL database
     * @throws SQLException
     * @return int
     * @param name - Customer name
     * @param address - Customer address
     * @param postalCode - Customer postal code
     * @param phone - Customer phone number
     * @param divId - Customer division id
     */
    public static int insert(String name, String address, String postalCode, String phone, int divId) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divId);

        return ps.executeUpdate();
    }

    /**
     * Updates Customer info in the MySQL database
     * @throws SQLException
     * @return int
     * @param custId - Customer id
     * @param name - Customer name
     * @param address - Customer address
     * @param postalCode - Customer postal code
     * @param phone - Customer phone number
     * @param divId - Customer division id
     */
    public static int update(int custId, String name, String address, String postalCode, String phone, int divId) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, " +
                "Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divId);
        ps.setInt(6, custId);
        for (Customer customer : Data.getCustomers()) {
            if (custId == customer.getCustomerId()) {
                customer.setCustomerName(name);
                customer.setAddress(address);
                customer.setPostalCode(postalCode);
                customer.setPhoneNumber(phone);
                customer.setDivisionId(divId);
            }
        }
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Deletes Customer from MySQL database
     * @throws SQLException
     * @return int
     * @param custId - ID of Customer being deleted
     */
    public static int delete(int custId) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, custId);
        AppointmentQuery.deleteUsingCustId(custId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Pulls all customers in MySQL database
     * @throws SQLException
     * @return Observable list of Customers
     */
    public static ObservableList<Customer> select() throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        Customer cust;
        while (rs.next()) {
            int custId = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divId = rs.getInt("Division_ID");
            cust = new Customer(custId, name, address, postalCode, phone, divId);
            customers.add(cust);

        }
        return customers;
    }

    /**
     * Pulls Customer using a given customer ID
     * @throws SQLException
     * @param custId - customer id of customer that will be selected
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
