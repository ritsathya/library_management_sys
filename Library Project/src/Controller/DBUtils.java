package Controller;

import Model.Book;
import Model.Rental;
import Model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "@adminPass!";

    public static void changeScene(ActionEvent event, String fxmlFile) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(DBUtils.class.getResource("/Stylesheet/login.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void insertStudent(String id, String name, String birthday, String gender, String major, String email) {
        Connection connection = null;
        PreparedStatement psCheckIDExists = null;
        PreparedStatement psInsert= null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psCheckIDExists = connection.prepareStatement("SELECT stu_id FROM student WHERE stu_id = ?");
            psCheckIDExists.setString(1, id);
            resultSet = psCheckIDExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("ID already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot insert this student.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO student(stu_id, stu_name, birthday, gender, major, email) VALUES(?,?,?,?,?,?)");
                psInsert.setString(1, id);
                psInsert.setString(2, name);
                psInsert.setString(3, birthday);
                psInsert.setString(4,gender);
                psInsert.setString(5,major);
                psInsert.setString(6, email);
                psInsert.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Provided credentials are incorrect.");
            alert.show();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckIDExists != null) {
                try {
                    psCheckIDExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateStudent(String id, String name, String birthday, String gender, String major, String email) {
        Connection connection = null;
        PreparedStatement psCheckIDExists = null;
        PreparedStatement psUpdate = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psCheckIDExists = connection.prepareStatement("SELECT stu_id FROM student WHERE stu_id = ?");
            psCheckIDExists.setString(1,id);
            resultSet = psCheckIDExists.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot update this student information");
                alert.show();
            } else {
                psUpdate = connection.prepareStatement("UPDATE student SET stu_name = ?, birthday = ?, gender = ?, major = ?, email = ? WHERE stu_id = ?");
                psUpdate.setString(1,name);
                psUpdate.setString(2, birthday);
                psUpdate.setString(3,gender);
                psUpdate.setString(4, major);
                psUpdate.setString(5,email);
                psUpdate.setString(6, id);
                psUpdate.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Provided credentials are incorrect.");
            alert.show();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckIDExists != null) {
                try {
                    psCheckIDExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psUpdate != null) {
                try {
                    psUpdate.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteStudent(String id) {
        Connection connection = null;
        PreparedStatement psCheckIDExists = null;
        PreparedStatement psDelete = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psCheckIDExists = connection.prepareStatement("SELECT stu_id FROM student WHERE stu_id = ?");
            psCheckIDExists.setString(1, id);
            resultSet = psCheckIDExists.executeQuery();

            if(!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot delete this student.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Are you sure you want to delete this student?");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    psDelete = connection.prepareStatement("DELETE FROM student where stu_id = ?");
                    psDelete.setString(1, id);
                    psDelete.executeUpdate();
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckIDExists != null ) {
                try {
                    psCheckIDExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psDelete != null) {
                try {
                    psDelete.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e ) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void insertBook(String id, String title, String author, String year, int type, String shelfNo, String floor) {
        Connection connection = null;
        PreparedStatement psCheckIDExists = null;
        PreparedStatement psInsert = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psCheckIDExists = connection.prepareStatement("SELECT book_id FROM book WHERE book_id = ?");
            psCheckIDExists.setString(1, id);
            resultSet = psCheckIDExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("id already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot add this book into the library.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO book VALUES(?,?,?,?,?,?,?,2)");
                psInsert.setString(1, id);
                psInsert.setString(2, title);
                psInsert.setString(3, author);
                psInsert.setString(4, year);
                psInsert.setInt(5, type);
                psInsert.setString(6, shelfNo);
                psInsert.setString(7, floor);
                psInsert.executeUpdate();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You cannot insert this book.");
            alert.show();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckIDExists != null) {
                try {
                    psCheckIDExists.close();
                } catch (SQLException e ) {
                    e.printStackTrace();
                }
            }

            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void updateBook(String id, String title, String author, String year, int type, String shelfNo, String floor) {
        Connection connection = null;
        PreparedStatement psCheckIDExists = null;
        PreparedStatement psUpdate = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psCheckIDExists = connection.prepareStatement("SELECT book_id FROM book WHERE book_id = ?");
            psCheckIDExists.setString(1, id);
            resultSet = psCheckIDExists.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot update this book information");
                alert.show();
            } else {
                psUpdate = connection.prepareStatement("UPDATE book SET book_title = ?, book_author = ?, publish_year = ?, category_id = ?, shelf_no = ?, floor = ? WHERE book_id = ?");
                psUpdate.setString(1,title);
                psUpdate.setString(2, author);
                psUpdate.setString(3,year);
                psUpdate.setInt(4, type);
                psUpdate.setString(5,shelfNo);
                psUpdate.setString(6, floor);
                psUpdate.setString(7, id);
                psUpdate.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckIDExists != null) {
                try {
                    psCheckIDExists.close();
            } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psUpdate != null) {
                try {
                    psUpdate.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteBook(String id) {
        Connection connection = null;
        PreparedStatement psCheckIDExists = null;
        PreparedStatement psDelete = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psCheckIDExists = connection.prepareStatement("SELECT book_id FROM book WHERE book_id = ?");
            psCheckIDExists.setString(1, id);
            resultSet = psCheckIDExists.executeQuery();

            if(!resultSet.isBeforeFirst()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot delete this book.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Are you sure you want to delete this book?");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    psDelete = connection.prepareStatement("DELETE FROM book where book_id = ?");
                    psDelete.setString(1, id);
                    psDelete.executeUpdate();
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckIDExists != null ) {
                try {
                    psCheckIDExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psDelete != null) {
                try {
                    psDelete.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e ) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int totalBook() {
        Connection connection = null;
        PreparedStatement psCount = null;
        ResultSet resultSet = null;
        int totalBook = 0;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psCount = connection.prepareStatement("SELECT COUNT(*) AS total FROM book");
            resultSet = psCount.executeQuery();
            while (resultSet.next()) {
                totalBook = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCount != null) {
                try {
                    psCount.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return totalBook;
    }

    public static int totalStudent() {
        Connection connection = null;
        PreparedStatement psCount = null;
        ResultSet resultSet = null;
        int totalStudent = 0;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psCount = connection.prepareStatement("SELECT COUNT(*) AS total FROM student");
            resultSet = psCount.executeQuery();
            while (resultSet.next()) {
                totalStudent = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCount != null) {
                try {
                    psCount.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return totalStudent;
    }

    public static int totalBorrowedBook() {
        Connection connection = null;
        PreparedStatement psCount = null;
        ResultSet resultSet = null;
        int totalBorrow = 0;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psCount = connection.prepareStatement("SELECT COUNT(*) AS total FROM book WHERE book_issue_stat_id = 1");
            resultSet = psCount.executeQuery();
            while (resultSet.next()) {
                totalBorrow = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCount != null) {
                try {
                    psCount.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return totalBorrow;
    }

    public static void issueBook(String studentID, String bookID, String issueDate, String returnDate) {
        Connection connection = null;
        PreparedStatement psCheckIssueRecord = null;
        PreparedStatement psIssueBook = null;
        PreparedStatement psUpdateBookStatus = null;
        ResultSet resultSet = null;
        int totalBorrowed = 0;
        int bookStat = 0;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psCheckIssueRecord = connection.prepareStatement("SELECT COUNT(*) AS total FROM book_issue WHERE student_id = ? AND returned_date IS NULL");
            psCheckIssueRecord.setString(1,studentID);
            resultSet = psCheckIssueRecord.executeQuery();
            while (resultSet.next()) {
                totalBorrowed = resultSet.getInt("total");
            }

            psCheckIssueRecord = connection.prepareStatement("SELECT book_issue_stat_id FROM book WHERE book_id = ?");
            psCheckIssueRecord.setString(1,bookID);
            resultSet = psCheckIssueRecord.executeQuery();
            while (resultSet.next()) {
                bookStat = resultSet.getInt("book_issue_stat_id");
            }

            Alert alert = new Alert(Alert.AlertType.WARNING);
            if (totalBorrowed >= 2) {
                alert.setContentText("Student has reached borrow limitation.");
                alert.show();
            } else if (bookStat == 1) {
                alert.setContentText("Book is not available.");
                alert.show();
            } else {
                psIssueBook = connection.prepareStatement("INSERT INTO book_issue(student_id, booK_id, issue_date, return_date) VALUES (?,?,?,?)");
                psIssueBook.setString(1,studentID);
                psIssueBook.setString(2,bookID);
                psIssueBook.setString(3,issueDate);
                psIssueBook.setString(4,returnDate);
                psIssueBook.executeUpdate();

                psUpdateBookStatus = connection.prepareStatement("UPDATE book SET book_issue_stat_id = 1 WHERE book_id = ?");
                psUpdateBookStatus.setString(1,bookID);
                psUpdateBookStatus.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             if (resultSet != null) {
                 try {
                     resultSet.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }

             if (psCheckIssueRecord != null) {
                 try {
                     psCheckIssueRecord.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }

            if (psIssueBook != null) {
                try {
                    psIssueBook.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psUpdateBookStatus != null) {
                try {
                    psUpdateBookStatus.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void returnBook(String studentID, String bookID) {
        Connection connection = null;
        PreparedStatement psCheckUserExists = null;
        PreparedStatement psReturnBook = null;
        PreparedStatement psUpdateBookStat = null;
        ResultSet resultSet = null;
        String hasReturned = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psCheckUserExists = connection.prepareStatement("SELECT returned_date FROM book_issue WHERE student_id = ? AND booK_id = ? AND returned_date IS NULL");
            psCheckUserExists.setString(1, studentID);
            psCheckUserExists.setString(2, bookID);
            resultSet = psCheckUserExists.executeQuery();

            while (resultSet.next()) {
                hasReturned = resultSet.getString("returned_date");
            }


            if (resultSet.isBeforeFirst()) {
                System.out.println(hasReturned);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provide information is not correct.");
                alert.show();
            } else {
                psReturnBook = connection.prepareStatement("UPDATE book_issue SET returned_date = CURDATE() WHERE student_id = ? AND booK_id = ?");
                psReturnBook.setString(1, studentID);
                psReturnBook.setString(2, bookID);
                psReturnBook.executeUpdate();

                psUpdateBookStat = connection.prepareStatement("UPDATE book SET book_issue_stat_id = 2 WHERE book_id = ?");
                psUpdateBookStat.setString(1, bookID);
                psUpdateBookStat.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psReturnBook != null) {
                try {
                    psReturnBook.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psUpdateBookStat != null) {
                try {
                    psUpdateBookStat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static ObservableList<Book> getBookList(String query) {
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement psSelectBook = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psSelectBook = connection.prepareStatement(query);
            resultSet = psSelectBook.executeQuery();
            Book book;
            while (resultSet.next()) {
                book = new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("book_title"),
                        resultSet.getString("book_author"),
                        resultSet.getInt("publish_year"),
                        resultSet.getString("category_name"),
                        resultSet.getInt("shelf_no"),
                        resultSet.getInt("floor")
                );
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psSelectBook != null) {
                try {
                    psSelectBook.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return bookList;
    }

    public static ObservableList<Student> getStudentList(String query) {
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement psSelectStudent = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psSelectStudent = connection.prepareStatement(query);
            resultSet = psSelectStudent.executeQuery();
            Student student;
            while (resultSet.next()) {
                student = new Student(
                        resultSet.getInt("stu_id"),
                        resultSet.getString("stu_name"),
                        resultSet.getString("birthday"),
                        resultSet.getString("gender"),
                        resultSet.getString("major"),
                        resultSet.getString("email")
                );
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psSelectStudent != null) {
                try {
                    psSelectStudent.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return studentList;
    }

    public static ObservableList<Rental> getIssueRecord(String query) {
        ObservableList<Rental> issueList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement psSelectList = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            psSelectList = connection.prepareStatement(query);
            resultSet = psSelectList.executeQuery();
            Rental rental;
            while (resultSet.next()) {
                rental = new Rental(
                        resultSet.getString("stu_id"),
                        resultSet.getString("stu_name"),
                        resultSet.getString("book_id"),
                        resultSet.getString("book_title"),
                        resultSet.getString("issue_date"),
                        resultSet.getString("return_date"),
                        resultSet.getString("returned_date")
                );
                issueList.add(rental);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psSelectList != null) {
                try {
                    psSelectList.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return issueList;
    }
}
