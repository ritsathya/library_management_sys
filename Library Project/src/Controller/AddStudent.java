package Controller;

import Model.Student;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddStudent implements Initializable {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private DatePicker birthdayPicker;
    @FXML
    private ComboBox<String> genderSelector;
    private final String[] genders = {"Male", "Female"};
    @FXML
    private TextField majorField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField searchField;
    @FXML
    private Button insertBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Integer> idCol;
    @FXML
    private TableColumn<Student, String> nameCol;
    @FXML
    private TableColumn<Student, String> birthdayCol;
    @FXML
    private TableColumn<Student, String> genderCol;
    @FXML
    private TableColumn<Student, String> majorCol;
    @FXML
    private TableColumn<Student, String> emailCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderSelector.getItems().addAll(genders);
        showStudents("SELECT * FROM student ORDER BY stu_id");
        insertBtn.setOnAction((event) -> {
            try {
                DBUtils.insertStudent(
                        idField.getText(),
                        nameField.getText(),
                        birthdayPicker.getValue().toString(),
                        genderSelector.getSelectionModel().getSelectedItem(),
                        majorField.getText(),
                        emailField.getText()
                );
                resetInput();
            } catch (NullPointerException e) {
                System.out.println("date picker can be null");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Make sure you enter correct information.");
                alert.show();
            }
        });

        updateBtn.setOnAction((event) -> {
            try {
                DBUtils.updateStudent(
                        idField.getText(),
                        nameField.getText(),
                        birthdayPicker.getValue().toString(),
                        genderSelector.getSelectionModel().getSelectedItem(),
                        majorField.getText(),
                        emailField.getText()
                );
                resetInput();
            } catch (NullPointerException e) {
                System.out.println("date picker can be null");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Make sure you enter correct information.");
                alert.show();
            }
        });

        deleteBtn.setOnAction((event) -> {
            DBUtils.deleteStudent(idField.getText());
            resetInput();
        });

        searchBtn.setOnAction((event) ->
            showStudents("SELECT * FROM student WHERE stu_name LIKE '"+ searchField.getText() + "%'")
        );

        tableView.setOnMouseClicked((event) -> {
            if (event.getClickCount() > 1) onEdit();
        });
    }

    private void onEdit() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Student selectedStudent = tableView.getSelectionModel().getSelectedItem();
            idField.setText(Integer.toString(selectedStudent.getId()));
            nameField.setText(selectedStudent.getName());
            birthdayPicker.setValue(LocalDate.parse(selectedStudent.getBirthday()));
            genderSelector.setValue(selectedStudent.getGender());
            majorField.setText(selectedStudent.getMajor());
            emailField.setText(selectedStudent.getEmail());
        }
    }

    private void showStudents(String query) {
        ObservableList<Student> studentList = DBUtils.getStudentList(query);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        majorCol.setCellValueFactory(new PropertyValueFactory<>("major"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.setItems(studentList);
    }

    private void resetInput() {
        idField.setText("");
        nameField.setText("");
        majorField.setText("");
        emailField.setText("");
        showStudents("SELECT * FROM student");
    }
}
