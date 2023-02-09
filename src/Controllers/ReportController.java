package Controllers;

import helper.AppointmentQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Month;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;

public class ReportController implements Initializable {

    Stage stage;
    Parent scene;

    /** Back Button */
    @FXML
    private Button backButton;

    /** Appointment by Month and Type table view */
    @FXML
    private TableView<ApptMonthType> tableView1;

    /** Appointment by Month and Type, type column */
    @FXML
    private TableColumn<ApptMonthType, String> apptMonthTypeTypeTableColumn;

    /** Appointment by Month and Type month column */
    @FXML
    private TableColumn<ApptMonthType, Month> apptMonthTypeMonthTableColumn;

    /** Appointment by Month and Type year column */
    @FXML
    private TableColumn<ApptMonthType, Integer> apptMonthTypeYearTableColumn;

    /** Appointment by Month and Type count column */
    @FXML
    private TableColumn<ApptMonthType, Integer> apptMonthTypeCountTableColumn;

    /** Contacts comboBox */
    @FXML
    private ComboBox<String> contactComboBox;

    /** Appointment by Contact table view */
    @FXML
    private TableView<Appointment> tableView2;

    /** Appointment by Contact appointment id column */
    @FXML
    private TableColumn<Appointment, Integer> apptIdColumn;

    /** Appointment by Contact appointment title column */
    @FXML
    private TableColumn<Appointment, String> titleColumn;

    /** Appointment by Contact appointment description column */
    @FXML
    private TableColumn<Appointment, String> descColumn;

    /** Appointment by Contact appointment location column */
    @FXML
    private TableColumn<Appointment, String> locationColumn;

    /** Appointment by Contact appointment type column */
    @FXML
    private TableColumn<Appointment, String> typeColumn;

    /** Appointment by Contact appointment start time column */
    @FXML
    private TableColumn<Appointment, Timestamp> startColumn;

    /** Appointment by Contact appointment end time column */
    @FXML
    private TableColumn<Appointment, Timestamp> endColumn;

    /** Appointment by Contact appointment customer id column */
    @FXML
    private TableColumn<Appointment, Integer> custIdColumn;

    /** Appointment by Customer table view */
    @FXML
    private TableView<Appointment> tableView3;

    /** Appointment by Customer appointment id column */
    @FXML
    private TableColumn<Appointment, Integer> custApptIdColumn;

    /** Appointment by Customer appointment title column */
    @FXML
    private TableColumn<Appointment, String> custTitleColumn;

    /** Appointment by Customer appointment description column */
    @FXML
    private TableColumn<Appointment, String> custDescColumn;

    /** Appointment by Customer appointment location column */
    @FXML
    private TableColumn<Appointment, String> custLocationColumn;

    /** Appointment by Customer appointment type column */
    @FXML
    private TableColumn<Appointment, String> custTypeColumn;

    /** Appointment by Customer appointment start time column */
    @FXML
    private TableColumn<Appointment, Timestamp> custStartColumn;

    /** Appointment by Customer appointment end time column */
    @FXML
    private TableColumn<Appointment, Timestamp> custEndColumn;

    /** Appointment by Customer, customer id title column */
    @FXML
    private TableColumn<Appointment, Integer> custCustIdColumn;

    /** Appointment by Customer contact id column */
    @FXML
    private TableColumn<Appointment, Integer> custContactIdColumn;

    /** Customer comboBox */
    @FXML
    private ComboBox<String> customerComboBox;

    /** Search Button */
    @FXML
    private Button searchButton;


    /**
     * Opens the MenuController
     * @param event once the add button is clicked
     * @throws IOException
     */
    @FXML
    void backBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Forms/menuPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Menu");
        stage.show();

    }

    /**
     * sets combo box with contacts
     * @param contactComboBox - comboBox which will be set
     * @throws SQLException
     */
    public void setContacts(ComboBox<String> contactComboBox) throws SQLException {
        ObservableList<String> contacts = FXCollections.observableArrayList();
        for (Contact contact : Data.getContacts()) {
            contacts.add(contact.getContactName());
        }
        contactComboBox.setItems(contacts);
        if (!contacts.isEmpty()) {
            contactComboBox.setValue(contacts.get(0));
        }

    }

    /**
     * sets combo box with customers
     * @param customerComboBox - comboBox which will be set
     * @throws SQLException
     */
    public void setCustomers(ComboBox<String> customerComboBox) throws SQLException {
        ObservableList<String> customers = FXCollections.observableArrayList();
        for (Customer customer : Data.getCustomers()) {
            customers.add(customer.getCustomerName());
        }
        customerComboBox.setItems(customers);
        if (!customers.isEmpty()) {
            customerComboBox.setValue(customers.get(0));
        }

    }

    /**
     * selects a Contact whose appointments you would like to view and then sets tableview appropriately
     * @param event
     * @throws SQLException
     */
    @FXML
    void contactSelected(ActionEvent event) throws SQLException {
        tableView2.setItems(AppointmentQuery.getAppointmentsByContact(Objects.requireNonNull(Data.getContactByName(contactComboBox.getValue()))));

        apptIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIdColumn.setCellValueFactory(new PropertyValueFactory<>("custId"));
    }

    /**
     * selects a customer whose appointments you would like to view and then sets tableview appropriately
     * @param event
     * @throws SQLException
     */
    @FXML
    void customerSelected(ActionEvent event) throws SQLException {
        tableView3.setItems(AppointmentQuery.getAppointmentsByCustomer(Objects.requireNonNull(Data.getCustomerByName(customerComboBox.getValue()))));

        custApptIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        custTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        custDescColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        custLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        custTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        custStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        custEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        custCustIdColumn.setCellValueFactory(new PropertyValueFactory<>("custId"));
        custContactIdColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }

    /**
     * Initializes Report controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            tableView1.setItems(Data.getApptMonthType());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        apptMonthTypeTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptMonthTypeMonthTableColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        apptMonthTypeYearTableColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        apptMonthTypeCountTableColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        try {
            setContacts(contactComboBox);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            tableView2.setItems(AppointmentQuery.getAppointmentsByContact(Objects.requireNonNull(Data.getContactByName(contactComboBox.getValue()))));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        apptIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIdColumn.setCellValueFactory(new PropertyValueFactory<>("custId"));

        try {
            setCustomers(customerComboBox);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            tableView3.setItems(AppointmentQuery.getAppointmentsByCustomer(Objects.requireNonNull(Data.getCustomerByName(customerComboBox.getValue()))));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        custApptIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        custTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        custDescColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        custLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        custTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        custStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        custEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        custCustIdColumn.setCellValueFactory(new PropertyValueFactory<>("custId"));
        custContactIdColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }
}
