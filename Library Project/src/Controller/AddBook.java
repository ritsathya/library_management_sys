package Controller;

import Model.Book;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBook implements Initializable {
    @FXML
    private TextField idField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField authorNameField;
    @FXML
    private TextField yearField;
    @FXML
    private ComboBox<String> bookTypeBox;
    private final String[] bookTypes = {"Technology", "Programming", "Art", "Mathematics", "References", "Other"};
    @FXML
    private TextField shelfNoField;
    @FXML
    private TextField floorField;
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
        bookTypeBox.getItems().addAll(bookTypes);
        String query = "SELECT b.book_id, b.book_title, b.book_author, b.publish_year, bc.category_name, b.shelf_no, b.floor " +
                "FROM book b " +
                "JOIN book_category bc " +
                "ON b.category_id = bc.category_id;";
        showBooks(query);

        insertBtn.setOnAction((event) -> {
            DBUtils.insertBook(
                    idField.getText(),
                    titleField.getText(),
                    authorNameField.getText(),
                    yearField.getText(),
                    bookTypeBox.getSelectionModel().getSelectedIndex()+1,
                    shelfNoField.getText(),
                    floorField.getText()
            );
            resetInput();
        });

        updateBtn.setOnAction((event) -> {
            DBUtils.updateBook(
                    idField.getText(),
                    titleField.getText(),
                    authorNameField.getText(),
                    yearField.getText(),
                    bookTypeBox.getSelectionModel().getSelectedIndex()+1,
                    shelfNoField.getText(),
                    floorField.getText()
            );
            resetInput();
        });

        deleteBtn.setOnAction((event) -> {
            DBUtils.deleteBook(idField.getText());
            resetInput();
        });

        searchBtn.setOnAction((event) -> {
            String search = "SELECT b.book_id, b.book_title, b.book_author, b.publish_year, bc.category_name, b.shelf_no, b.floor " +
                    "FROM book b " +
                    "JOIN book_category bc " +
                    "ON b.category_id = bc.category_id AND book_title LIKE '"+ searchField.getText() + "%'";
            showBooks(search);
        });

        tableView.setOnMouseClicked((event) -> {
            if (event.getClickCount() > 1) onEdit();
        });
    }

    private void onEdit() {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            Book selectedBook = tableView.getSelectionModel().getSelectedItem();
            idField.setText(Integer.toString(selectedBook.getId()));
            titleField.setText(selectedBook.getTitle());
            authorNameField.setText(selectedBook.getAuthor());
            yearField.setText(Integer.toString(selectedBook.getPublishYear()));
            bookTypeBox.setValue(selectedBook.getCategory());
            shelfNoField.setText(Integer.toString(selectedBook.getShelfNumber()));
            floorField.setText(Integer.toString(selectedBook.getFloor()));
        }
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


    private void resetInput() {
        idField.setText("");
        titleField.setText("");
        authorNameField.setText("");
        yearField.setText("");
        shelfNoField.setText("");
        floorField.setText("");
        String query = "SELECT b.book_id, b.book_title, b.book_author, b.publish_year, bc.category_name, b.shelf_no, b.floor " +
                "FROM book b " +
                "JOIN book_category bc " +
                "ON b.category_id = bc.category_id;";
        showBooks(query);
    }
}
