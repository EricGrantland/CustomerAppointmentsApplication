package Controllers;

import helper.AppointmentQuery;
import helper.CustomerQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Appointment;
import sample.Data;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

public class ApptController {

    Stage stage;
    Parent scene;

    /** Appointment table view */
    @FXML
    private TableView<Appointment> apptTableView;

    /** Appointment id column */
    @FXML
    private TableColumn<Appointment, Integer> apptIdColumn;

    /** Appointment title column */
    @FXML
    private TableColumn<Appointment, String> titleColumn;

    /** Appointment description column */
    @FXML
    private TableColumn<Appointment, String> descColumn;

    /** Appointment location column */
    @FXML
    private TableColumn<Appointment, String> locationColumn;

    /** Appointment type column */
    @FXML
    private TableColumn<Appointment, String> typeColumn;

    /** Appointment start time column */
    @FXML
    private TableColumn<Appointment, Timestamp> startColumn;

    /** Appointment end time column */
    @FXML
    private TableColumn<Appointment, Timestamp> endColumn;

    /** Appointment customer id column */
    @FXML
    private TableColumn<Appointment, Integer> custIdColumn;

    /** Appointment user id column */
    @FXML
    private TableColumn<Appointment, Integer> userIdColumn;

    /** Appointment contact id column */
    @FXML
    private TableColumn<Appointment, Integer> contactIdColumn;

    /** New Appointment button */
    @FXML
    private Button newApptBtn;

    /** update Appointment button */
    @FXML
    private Button updateApptBtn;

    /** delete Appointment button */
    @FXML
    private Button deleteApptBtn;

    /**
     * Opens the AddApptController
     * @param event once the add button is clicked
     * @throws IOException
     */
    @FXML
    void addApptBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Forms/addAppt.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Add Appointment");
        stage.show();
    }

    /**
     * Opens the UpdateApptController
     * @param event once the add button is clicked
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void displayUpdateApptMenu(ActionEvent event) throws IOException, SQLException {

        if (!apptTableView.getSelectionModel().isEmpty()) {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Forms/updateAppt.fxml"));
            loader.load();

            UpdateApptController ADMcontroller = loader.getController();
            ADMcontroller.sendAppt(apptTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.setTitle("Update Appointment info");
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an Appointment");
            alert.showAndWait();
        }
    }

    /**
     * Deletes appointment
     * @param event once the add button is clicked
     * @throws SQLException
     */
    @FXML
    void deleteApptBtnClicked(ActionEvent event) throws SQLException {
        int apptId = apptTableView.getSelectionModel().getSelectedItem().getAppointmentId();
        if (!Data.getCustomers().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Delete this Appointment?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                AppointmentQuery.delete(apptId);
                apptTableView.setItems(Data.getAppointments());
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION,  "Appointment Deleted!");
                alert2.show();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nothing to delete");
            alert.showAndWait();
        }
    }

    /**
     * Displays all appointments in the appointments table view
     * @throws SQLException
     */
    @FXML
    void allBtnClicked() throws SQLException {
        apptTableView.setItems(Data.getAppointments());
    }

    /**
     * Displays appointments within the next week in the appointments table view
     * @throws SQLException
     */
    @FXML
    void weeklyBtnClicked() throws SQLException {
        ObservableList<Appointment> filteredAppts = FXCollections.observableArrayList();
        for (Appointment appointment : Data.getAppointments()) {
            if (appointment.getStart().toLocalDate().isAfter(LocalDate.now().minusDays(1)) &&
                    appointment.getStart().toLocalDate().isBefore(LocalDate.now().plusDays(7))) {
                filteredAppts.add(appointment);
            }
        }
        apptTableView.setItems(filteredAppts);
    }

    /**
     * Displays appointments within the next montn in the appointments table view
     * @throws SQLException
     */
    @FXML
    void monthlyBtnClicked() throws SQLException {
        ObservableList<Appointment> filteredAppts = FXCollections.observableArrayList();
        for (Appointment appointment : Data.getAppointments()) {
            if (appointment.getStart().toLocalDate().isAfter(LocalDate.now().minusDays(1)) &&
                    appointment.getStart().toLocalDate().isBefore(LocalDate.now().plusDays(31))) {
                filteredAppts.add(appointment);
            }
        }
        apptTableView.setItems(filteredAppts);
    }

    /**
     * returns to MenuController
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
     * Exits application
     * @param event when exit button is clicked
     */
    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Initializes Appt controller
     */
    @FXML
    void initialize() {

        try {
            apptTableView.setItems(Data.getAppointments());
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
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        contactIdColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));

        apptTableView.getSelectionModel().select(0);

    }

}

