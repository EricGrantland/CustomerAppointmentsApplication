package Controllers;

import helper.CustomerQuery;
import helper.JDBC;
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
import sample.Customer;
import sample.Data;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableView<Customer> custTableView;

    @FXML
    private TableColumn<Customer, Integer> custIdColumn;

    @FXML
    private TableColumn<Customer, String> custNameColumn;

    @FXML
    private TableColumn<Customer, String> custAddressColumn;

    @FXML
    private TableColumn<Customer, String> custPostalCodeColumn;

    @FXML
    private TableColumn<Customer, String> custPhoneColumn;

    @FXML
    private TableColumn<Customer, Integer> divIdColumn;

    @FXML
    private Button addCustBtn;

    @FXML
    private Button updateCustBtn;

    @FXML
    private Button deleteCustBtn;

    /**
     * Opens the AddCustController
     * @param event once the add button is clicked
     * @throws IOException
     */
    @FXML
    void addCustBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Forms/addCust.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Add Customer");
        stage.show();
    }

    /**
     * Opens the update customer menu
     * @param event when the modify button is clicked
     * @throws IOException
     */
    @FXML
    void displayUpdateCustMenu(ActionEvent event) throws IOException, SQLException {

        if (!custTableView.getSelectionModel().isEmpty()) {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Forms/updateCust.fxml"));
            loader.load();

            UpdateCustController ADMcontroller = loader.getController();
            ADMcontroller.sendCust(custTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.setTitle("Update Customer info");
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a Customer");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteCustBtnClicked(ActionEvent event) throws SQLException {
        int custId = custTableView.getSelectionModel().getSelectedItem().getCustomerId();
        if (!Data.getCustomers().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Delete this Customer?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                CustomerQuery.delete(custId);
                custTableView.setItems(Data.getCustomers());
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION,  "Appointment Deleted!");
                alert2.show();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nothing to delete");
            alert.showAndWait();
        }

    }

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

    public TableView<Customer> getCustTableView() {
        return custTableView;
    }

    /**
     * Initializes Cust controller
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            custTableView.setItems(Data.getCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        custIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        custPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        custPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        divIdColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

        custTableView.getSelectionModel().select(0);

    }

}
