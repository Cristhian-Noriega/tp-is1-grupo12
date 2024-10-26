package is1.order_app.entities;

import jakarta.persistence.Id;

public class User {
    @Id
    private String email;
    private String password;
    private String name;
    private String surname;
    private String photoUrl;
    private int age;
    private String gender;
    private String address;

    public User(String email, String name, String surname, String password, String photoUrl, int age, String gender, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.photoUrl = photoUrl;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }
    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

}
