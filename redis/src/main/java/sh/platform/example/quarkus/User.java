package sh.platform.example.quarkus;

public class User {
    public String userName;

    public String country;

    public User(String userName, String country) {
        this.userName = userName;
        this.country = country;
    }

    public User() {
    }
}