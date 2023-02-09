package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Data;
import sample.User;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginController {
    Stage stage;
    Parent scene;

    /** Welcome label */
    @FXML
    private Label welcomeLabel;

    /** Zone label */
    @FXML
    private Label zoneLabel;

    /** Username label */
    @FXML
    private Label usernameLabel;

    /** Password label */
    @FXML
    private Label passwordLabel;

    /** Login Button */
    @FXML
    private Button loginButton;

    /** Username Text Field */
    @FXML
    private TextField loginUsernameField;

    /** Password Text Field */
    @FXML
    private PasswordField loginPasswordField;

    public LoginController() {
    }

    /**
     * Opens the MenuController
     * @param event once the add button is clicked
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void loginBtnClicked(ActionEvent event) throws IOException, SQLException {
        String filename = "src/LoginFile/login_activity.txt";
        FileWriter fileWriter = new FileWriter(filename, true);
        PrintWriter outputFile = new PrintWriter(fileWriter);

        for (User user : Data.getUsers()) {
            if (!(user.getUserName().equals(loginUsernameField.getText()) && user.getPassword().equals(loginPasswordField.getText()))) {
                outputFile.println("Login failed: " + LocalDateTime.now());
                fileWriter.close();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    alert.setContentText("Informations d'identification inccorrectes");
                } else {
                    alert.setContentText("Username or Password is incorrect");
                }
                alert.showAndWait();
                return;
            } else {
                outputFile.println("Login successful!: " + LocalDateTime.now());
                fileWriter.close();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Forms/menuPage.fxml"));
                loader.load();

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.setTitle("Main Menu");
                stage.show();
                return;
            }
        }


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
     * Initializes Login controller
     */
    @FXML
    void initialize() {
        zoneLabel.setText(ZoneId.systemDefault().toString());

        if (Locale.getDefault().getLanguage().equals("fr")) {
            ResourceBundle rb = ResourceBundle.getBundle("sample/Nat", Locale.getDefault());
            usernameLabel.setText(rb.getString("username"));
            passwordLabel.setText(rb.getString("password"));
            loginButton.setText(rb.getString("login"));
            welcomeLabel.setText("Bienvenue, merci de vous connector");
        }

    }

}



