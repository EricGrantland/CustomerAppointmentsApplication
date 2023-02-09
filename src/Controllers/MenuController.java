package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Appointment;
import sample.Data;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class MenuController {

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

    /** Manage Appointments button */
    @FXML
    private Button mngApptBtn;

    /** Manage Customers button */
    @FXML
    private Button mngCustBtn;

    /** Reports button */
    @FXML Button reportBtn;

    /**
     * Opens ApptController
     * @param event once the add button is clicked
     * @throws IOException
     */
    @FXML
    void mngApptBtnClicked(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Forms/apptPage.fxml"));
        loader.load();

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments");
        stage.show();
    }

    /**
     * Opens CustController
     * @param event once the add button is clicked
     * @throws IOException
     */
    @FXML
    void mngCustBtnClicked(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Forms/custPage.fxml"));
        loader.load();

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.setTitle("Customers");
        stage.show();
    }

    /**
     * Opens ReportController
     * @param event once the add button is clicked
     * @throws IOException
     */
    @FXML
    void reportBtnClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Forms/reportPage.fxml"));
        loader.load();

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.setTitle("Reports");
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
     * Initializes add cust controller
     */
    @FXML
    void initialize() throws SQLException {

        ObservableList<Appointment> todaysAppointments = FXCollections.observableArrayList();
        try {
            for (Appointment appointment : Data.getAppointments()) {
                if (appointment.getStart().toLocalDate().isEqual(LocalDate.now())) {
                    todaysAppointments.add(appointment);
                }
            }
            apptTableView.setItems(todaysAppointments);
            System.out.println("Appointment TableView Set");
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

        //        apptTableView.getSelectionModel().select(0);
        System.out.println("now" + LocalDateTime.now().toString());
        System.out.println("15 f: " + LocalDateTime.now().plusMinutes(15).toString());
        for (Appointment appointment : Data.getAppointments()) {
            if (appointment.getStart().isAfter(LocalDateTime.now()) &&
                    appointment.getStart().isBefore(LocalDateTime.now().plusMinutes(15))) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("There is an appointment soon!\n" + "Appointment ID: " + appointment.getAppointmentId() +
                        "\nDate: " + appointment.getStart().toLocalDate().toString() + "\nTime: " +
                        appointment.getStart().toLocalTime().toString());
                alert.showAndWait();
            }
        }
    }

}
