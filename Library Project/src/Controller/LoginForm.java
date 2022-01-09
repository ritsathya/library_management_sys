package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class LoginForm {
    HashMap<String, String> loginInfo = new IDandPasswords().getLoginInfo();

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label messageLabel;

    @FXML
    private void handleLoginBtn(javafx.event.ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (loginInfo.containsKey(username)) {
            if (loginInfo.get(username).equals(password)) {
                messageLabel.setTextFill(Color.GREEN);
                login(event);
            }
            else {
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Incorrect Password");
            }
        }
        else {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Incorrect Username");
        }
    }

    private void login(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/Application.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Stylesheet/application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
