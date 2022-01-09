package Controller;

import Model.Rental;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class IssueBook implements Initializable {
    @FXML
    private Button issueBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private TextField studentID;
    @FXML
    private TextField bookID;
    @FXML
    private DatePicker issueDate;
    @FXML
    private DatePicker returnDate;
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
    private TableColumn<Rental, String> returnDateCol;
    @FXML
    private TableColumn<Rental, String> returnedDateCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetInput();
        issueBtn.setOnAction((event) -> {
            DBUtils.issueBook(
                    studentID.getText(),
                    bookID.getText(),
                    issueDate.getValue().toString(),
                    returnDate.getValue().toString()
            );
            resetInput();
        });

        resetBtn.setOnAction((event)->resetInput());
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
                "    bs.student_id = s.stu_id " +
                "    AND " +
                "    bs.returned_date IS NULL");
    }

    private void showIssueList(String query) {
        ObservableList<Rental> issueList = DBUtils.getIssueRecord(query);
        studentIDCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        studentNameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        bookIDCol.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        bookTitleCol.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        issueDateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        returnedDateCol.setCellValueFactory(new PropertyValueFactory<>("returnedDate"));

        tableView.setItems(issueList);
    }
}
