package Controllers;

import helper.CustomerQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Country;
import sample.Data;
import sample.FirstLvlDiv;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustController implements Initializable {

    Stage stage;
    Parent scene;

    /** Customer id Text Field */
    @FXML
    private TextField idTextField;

    /** Customer name Text Field */
    @FXML
    private TextField nameTextField;

    /** Customer address Text Field */
    @FXML
    private TextField addressTextField;

    /** Customer postal code Text Field */
    @FXML
    private TextField postalCodeTextField;

    /** Customer phone number Text Field */
    @FXML
    private TextField phoneTextField;

    /** Country comboBox */
    @FXML
    private ComboBox<String> countryComboBox;

    /** Division ID comboBox */
    @FXML
    private ComboBox<String> divIdComboBox;

    /** Save Button */
    @FXML
    private Button saveButton;

    /**
     * Opens CustController
     * @param event when cancel button is pressed
     * @throws IOException
     */
    @FXML
    void displayCustMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Forms/custPage.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("");
        stage.show();
    }

    /**
     * Saves Customer
     * @param event when save button is clicked
     * @throws IOException
     */
    @FXML
    void saveCust(ActionEvent event) throws IOException {
        if (!(idTextField.getText().isEmpty() || nameTextField.getText().isEmpty() || addressTextField.getText().isEmpty() ||
                postalCodeTextField.getText().isEmpty() || phoneTextField.getText().isEmpty() ||
                divIdComboBox.getSelectionModel().isEmpty() || countryComboBox.getSelectionModel().isEmpty())) {

            try {
                String name = nameTextField.getText();
                String address = addressTextField.getText();
                String postalCode = postalCodeTextField.getText();
                String phone = phoneTextField.getText();
                int divId = Data.getDivIdByDiv(divIdComboBox.getValue());

                CustomerQuery.insert(name, address, postalCode, phone, divId);

            } catch (NumberFormatException | SQLException e) {
                System.out.println("Enter valid values in text fields");
                System.out.println("Exception " + e);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid information");
                alert.showAndWait();
                return;
            }

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/Forms/custPage.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("");
            stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("All text fields must be filled out");
            alert.showAndWait();
        }
    }

    /**
     * resets first-level-division combo box when a country is selected
     * @throws SQLException
     */
    @FXML
    void countrySelected() throws SQLException {
        divIdComboBox.setItems(Data.getDivsForCountryName(countryComboBox.getSelectionModel().getSelectedItem()));
        divIdComboBox.setValue(null);
    }


    /**
     * Initializes add cust controller
     * @param url
     * @param resourceBundle
     */
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> countries = FXCollections.observableArrayList();
        ObservableList<Country> countries1 = FXCollections.observableArrayList();
        try {
            countries1.setAll(Data.getCountries());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Country country : countries1) {
            countries.add(country.getCountry());
        }
        countryComboBox.setItems(countries);

        ObservableList<String> divisions = FXCollections.observableArrayList();
        try {
            for (FirstLvlDiv div : Data.getDivisions()) {
                divisions.add(div.getDiv());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        divIdComboBox.setItems(divisions);
    }
}
