package Controller;

import Model.Rental;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Statistic implements Initializable {
    private int totalBooks;
    private int borrowedBooks;

    @FXML
    private TextField totalBook;
    @FXML
    private TextField totalStudent;
    @FXML
    private TextField borrowed;
    @FXML
    private TextField available;
    @FXML
    private TableView<Rental> tableView;
    @FXML
    private TableColumn<Rental, String> studentIDCol;
    @FXML
    private TableColumn<Rental, String> stuNameCol;
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
        totalBooks = DBUtils.totalBook();
        borrowedBooks = DBUtils.totalBorrowedBook();
        totalBook.setText(Integer.toString(totalBooks));
        totalStudent.setText(Integer.toString(DBUtils.totalStudent()));
        borrowed.setText(Integer.toString(borrowedBooks));
        available.setText(Integer.toString(totalBooks-borrowedBooks));
    }

    private void showIssueList(String query) {
        ObservableList<Rental> issueList = DBUtils.getIssueRecord(query);
        studentIDCol.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        stuNameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        bookIDCol.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        bookTitleCol.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        issueDateCol.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        returnedDateCol.setCellValueFactory(new PropertyValueFactory<>("returnedDate"));

        tableView.setItems(issueList);
    }
}
