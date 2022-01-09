package Model;

public class Rental {
    private String studentID;
    private String studentName;
    private String bookID;
    private String bookTitle;
    private String issueDate;
    private String returnDate;
    private String returnedDate;


    public Rental(String studentID, String studentName, String bookID, String bookTitle, String issueDate, String returnDate, String returnedDate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.returnedDate = returnedDate;
    }

    public String getStudentID() {return studentID;}

    public void setStudentID(String studentID) {this.studentID = studentID;}

    public String getBookID() {return bookID;}

    public void setBookID(String bookID) {this.bookID = bookID;}

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(String returnedDate) {
        this.returnedDate = returnedDate;
    }
}
