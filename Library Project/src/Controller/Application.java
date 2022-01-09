package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Application implements Initializable {

    @FXML
    private Button logoutBtn;
    @FXML
    private BorderPane mainPane;

    @FXML
    public void handleAddStudentBtn() {
        loadView("AddStudent");
    }

    @FXML
    public void handleAddBookBtn() {
        loadView("AddBook");
    }

    @FXML
    public void handleStatBtn() {
        loadView("Statistic");
    }

    @FXML
    public void handleIssueBookBtn() {
        loadView("IssuedBook");
    }

    @FXML
    public void handleReturnBookBtn() {
        loadView("ReturnBook");
    }

    @FXML
    public void handleSearchBookBtn() {
        loadView("SearchBook");
    }

    private void loadView(String filename) {
        Node tempNode = mainPane.getCenter();
        mainPane.getChildren().remove(tempNode);
        try {
            tempNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/" + filename + ".fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.setCenter(tempNode);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoutBtn.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to logout?");
            if (alert.showAndWait().get() != ButtonType.OK) return;
            DBUtils.changeScene(event, "/View/LoginForm.fxml");
        });
    }
}
