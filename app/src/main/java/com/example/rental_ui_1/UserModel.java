package com.example.rental_ui_1;

public class UserModel {
    private int user_id;
    private String useName;
    private String email;
    private String password;
    private String gender;
    private String phone_no;

    public UserModel(int user_id, String useName, String email, String password, String gender, String phone_no) {
        this.user_id = user_id;
        this.useName = useName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phone_no = phone_no;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
