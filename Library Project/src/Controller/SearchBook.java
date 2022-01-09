package Controller;

import Model.Book;
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

public class SearchBook implements Initializable {
    @FXML
    private TextField bookTitle;
    @FXML
    private TextField authorName;
    @FXML
    private Button resetBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, Integer> idCol;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, Integer> authorCol;
    @FXML
    private TableColumn<Book, String> typeCol;
    @FXML
    private TableColumn<Book, Integer> yearCol;
    @FXML
    private TableColumn<Book, Integer> shelfNoCol;
    @FXML
    private TableColumn<Book, Integer> floorCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchBtn.setOnAction((event) -> {
            String search = "SELECT b.book_id, b.book_title, b.book_author, b.publish_year, bc.category_name, b.shelf_no, b.floor " +
                    "FROM book b " +
                    "JOIN book_category bc " +
                    "ON b.category_id = bc.category_id " +
                    "WHERE book_title LIKE '"+ bookTitle.getText() + "%' AND " +
                    "book_author LIKE '" + authorName.getText() + "%'";
            showBooks(search);
        });

        resetBtn.setOnAction((event)->{
            bookTitle.setText("");
            authorName.setText("");
        });

    }

    private void showBooks(String query) {
        ObservableList<Book> bookList = DBUtils.getBookList(query);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("publishYear"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        shelfNoCol.setCellValueFactory(new PropertyValueFactory<>("shelfNumber"));
        floorCol.setCellValueFactory(new PropertyValueFactory<>("floor"));

        tableView.setItems(bookList);
    }
}
