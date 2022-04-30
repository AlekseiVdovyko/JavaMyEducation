package ru.edu.lecture3;

import java.time.LocalDate;

/**
 * Contains user info.
 */
public class User {

    private String login;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;

    public User() {
    }

    public User (String login, String firstName, String lastName, Gender gender, LocalDate birthDate) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;

    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String toString() {
        return "User = {login: " + login
                + "; First Name: " + firstName
                + "; Last Name: " + lastName
                + "; Birth Day: " + birthDate
                + "; Gender: " + gender + "}";
    }
}
