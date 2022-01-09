package Model;

public class Student {
    // fields
    private int id;
    private String name;
    private String birthday;
    private String gender;
    private String major;
    private String email;

    // Constructor
    public Student(int id, String name, String birthday, String gender, String major, String email) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.major = major;
        this.email = email;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
