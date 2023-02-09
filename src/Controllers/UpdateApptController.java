package Controllers;

import helper.AppointmentQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Appointment;
import sample.Contact;
import sample.Data;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class UpdateApptController {

    Stage stage;
    Parent scene;

    /** Appointment id text field */
    @FXML
    private TextField apptIdTextField;

    /** Appointment title text field */
    @FXML
    private TextField titleTextField;

    /** Appointment description text area */
    @FXML
    private TextArea descTextField;

    /** Appointment location text field */
    @FXML
    private TextField locationTextField;

    /** Appointment type text field */
    @FXML
    private TextField typeTextField;

    /** Appointment Date Picker */
    @FXML
    private DatePicker datePicker;

    /** Appointment start time combo box */
    @FXML
    private ComboBox<LocalTime> startTimeComboBox;

    /** Appointment end time combo box */
    @FXML
    private ComboBox<LocalTime> endTimeComboBox;

    /** Appointment customer id text field */
    @FXML
    private TextField custIdTextField;

    /** Appointment user id text field */
    @FXML
    private TextField userIdTextField;

    /** Appointment contacts combo box */
    @FXML
    private ComboBox<String> contactComboBox;

    /** Save button */
    @FXML
    private Button saveButton;

    /**
     * Opens Main Menu
     * @param event when cancel button is pressed
     * @throws IOException
     */
    @FXML
    void displayApptMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Forms/apptPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("");
        stage.show();
    }

    /**
     * Saves Appointment
     * @param event when save button is clicked
     * @throws IOException
     */
    @FXML
    void saveAppt(ActionEvent event) throws IOException {
        if (!(apptIdTextField.getText().isEmpty() || titleTextField.getText().isEmpty() || descTextField.getText().isEmpty() ||
                locationTextField.getText().isEmpty() || typeTextField.getText().isEmpty() || startTimeComboBox.getValue().toString().isEmpty() ||
                endTimeComboBox.getValue().toString().isEmpty() || custIdTextField.getText().isEmpty() || userIdTextField.getText().isEmpty() ||
                contactComboBox.getSelectionModel().isEmpty())) {
            if (startTimeComboBox.getValue().isBefore(endTimeComboBox.getValue())) {

                try {
                    LocalDateTime startDateTime = LocalDateTime.of(datePicker.getValue(), startTimeComboBox.getValue());
                    LocalDateTime endDateTime = LocalDateTime.of(datePicker.getValue(), endTimeComboBox.getValue());
                    Integer apptId = Integer.parseInt(apptIdTextField.getText());
                    String title = titleTextField.getText();
                    String desc = descTextField.getText();
                    String location = locationTextField.getText();
                    String type = titleTextField.getText();
                    String custId = custIdTextField.getText();
                    String userId = userIdTextField.getText();
                    String contact = contactComboBox.getValue();

                    for (Appointment appt : AppointmentQuery.getAppointmentsByCustomer(Objects.requireNonNull(Data.getCustomerById(Integer.parseInt(custId))))) {
                        if (((startDateTime.isAfter(appt.getStart()) || startDateTime.isEqual(appt.getStart())) && (startDateTime.isBefore(appt.getEnd()))) ||
                                ((endDateTime.isAfter(appt.getStart())) && (endDateTime.isBefore(appt.getEnd()) || endDateTime.isEqual(appt.getEnd()))  )) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Appointment overlaps another appointment for this customer!'");
                            alert.showAndWait();
                            return;
                        }
                    }


                    AppointmentQuery.update(apptId, title, desc, location, type, startDateTime, endDateTime, custId, userId, Data.getContactIdForContactName(contact));


                } catch (NumberFormatException | SQLException e) {
                    System.out.println("Enter valid values in text fields");
                    System.out.println("Exception " + e);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Invalid information");
                    alert.showAndWait();
                    return;
                }

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/Forms/apptPage.fxml"));
                stage.setScene(new Scene(scene));
                stage.setTitle("");
                stage.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Appointment 'End time' must be AFTER appointment 'Start time'");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("All text fields must be filled out");
            alert.showAndWait();
        }
    }

    /**
     * Opens ApptController
     * @param comboBox comboBox that is loading contacts
     * @param contactList list of contacts that will load into comboBox
     */
    public static void setContacts(ComboBox<String> comboBox, ObservableList<Contact> contactList) {
        ObservableList<String> contactStringList = FXCollections.observableArrayList();
        for (Contact contact : contactList) {
            contactStringList.add(contact.getContactName());
        }
        comboBox.setItems(contactStringList);
    }


    /**
     * Autofills text boxes with the selected appointment's info
     * @param appointment
     */
    public void sendAppt(Appointment appointment) throws SQLException {
        apptIdTextField.setText(Integer.toString(appointment.getAppointmentId()));
        titleTextField.setText(appointment.getTitle());
        descTextField.setText(appointment.getDescription());
        locationTextField.setText(appointment.getLocation());
        typeTextField.setText(appointment.getType());
        datePicker.setValue(appointment.getStart().toLocalDate());

        //
        startTimeComboBox.setItems(Data.getStartTimes());
        endTimeComboBox.setItems(Data.getEndTimes());

        startTimeComboBox.setValue(appointment.getStart().toLocalTime());
        endTimeComboBox.setValue(appointment.getEnd().toLocalTime());
        custIdTextField.setText(Integer.toString(appointment.getCustId()));
        userIdTextField.setText(Integer.toString(appointment.getUserId()));
        setContacts(contactComboBox, Data.getContacts());
        contactComboBox.setValue(Data.getContactById(appointment.getContactId()));

    }
}
