package kz.ideas.entity;

import kz.ideas.entity.Entity;

import java.sql.Date;

public class User extends Entity {
    private String login;
    private String password;
    private String name;
    private Date dateOfBirth;
    private byte[] image;
    private String base64image;
    private int role;

    public User(String login, String password, String name, Date dateOfBirth, String phone, byte[] image, String base64image, int role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.image = image;
        this.base64image = base64image;
        this.role = role;
    }

    public User() {}

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getBase64image() {
        return base64image;
    }

    public void setBase64image(String base64image) {
        this.base64image = base64image;
    }
}
