package Model;

public class Book {
    private int id;
    private String title;
    private String author;
    private int publishYear;
    private String category;
    private int shelfNumber;
    private int floor;

    public Book(int id, String title, String author, int publishYear, String category, int shelfNumber, int floor) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.category = category;
        this.shelfNumber = shelfNumber;
        this.floor = floor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory_id(String category) {
        this.category = category;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
