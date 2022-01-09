package Controller;

import Model.Rental;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ReturnBook implements Initializable {
    @FXML
    private Button returnBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private TextField studentID;
    @FXML
    private TextField bookID;
    @FXML
    private TableView<Rental> tableView;
    @FXML
    private TableColumn<Rental, String> studentIDCol;
    @FXML
    private TableColumn<Rental, String> studentNameCol;
    @FXML
    private TableColumn<Rental, String> bookIDCol;
    @FXML
    private TableColumn<Rental, String> bookTitleCol;
    @FXML
    private TableColumn<Rental, String> issueDateCol;
    @FXML
    private TableColumn<Rental, String> returnedDateCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetInput();
        returnBtn.setOnAction((event) -> {
            DBUtils.returnBook(studentID.getText(), bookID.getText());
            resetInput();
        });
        resetBtn.setOnAction((event)->resetInput());

        tableView.setOnMouseClicked((event) -> {
            if (event.getClickCount() > 1) onEdit();
        });
    }

    private void onEdit() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            studentID.setText(tableView.getSelectionModel().getSelectedItem().getStudentID());
            bookID.setText(tableView.getSelectionModel().getSelectedItem().getBookID());
        }
    }

    private void resetInput() {
        studentID.setText("");
        bookID.setText("");
        showIssueList("SELECT " +
                "    s.stu_id, " +
                "    s.stu_name, " +
                "    b.book_id, " +
                "    b.book_title, " +
                "    bs.issue_date, " +
                "    bs.return_date, " +
                "    bs.returned_date " +
                "FROM " +
                "    book_issue bs, " +
                "    book b, " +
                "    student s " +
                "WHERE " +
                "    bs.book_id = b.book_id " +
                "    AND " +
                "    bs.student_id = s.stu_id ");
    }

    private void showIssueList(String query) {
        ObservableList<Rental> issueList = DBUtils.getIssueRecord(query);
        studentIDCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        studentNameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        bookIDCol.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        bookTitleCol.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        issueDateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        returnedDateCol.setCellValueFactory(new PropertyValueFactory<>("returnedDate"));

        tableView.setItems(issueList);
    }
}
