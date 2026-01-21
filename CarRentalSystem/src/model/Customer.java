package model;

import java.io.Serializable;

public class Customer implements Serializable {

    private String name;
    private String surname;
    private String gender;
    private String email;
    private String phone;
    private String address;

    public Customer(String name, String surname, String gender,
                    String email, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getFullName() {
        return name + " " + surname;
    }
}
