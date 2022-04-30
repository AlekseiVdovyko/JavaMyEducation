package ru.edu.lesson1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GreetingImpl implements IGreeting {

    private String FirstName;
    private String SecondName;
    private String LastName;
    private String BitbucketUrl;
    private String Phone;
    private String CourseExpectation;
    private String EducationInfo;


    public GreetingImpl(){
    }

    public GreetingImpl(String FirstName, String SecondName, String LastName, String BitbucketUrl,
                        String Phone, String CourseExpectation, String EducationInfo) {
        this.FirstName = FirstName;
        this.SecondName = SecondName;
        this.LastName = LastName;
        this.BitbucketUrl = BitbucketUrl;
        this.Phone = Phone;
        this.CourseExpectation = CourseExpectation;
        this.EducationInfo = EducationInfo;
    }

    /**
     * Get first name.
     */
    public String getFirstName() {
        return "Aleksei";
    }

    /**
     * Get second name
     */
    public String getSecondName() {
        return "Vdovyko";
    }

    /**
     * Get last name.
     */
    public String getLastName() {
        return "Vdovyko";
    }

    /**
     * Get hobbies.
     */
    public Collection<Hobby> getHobbies() {
        Collection<Hobby> hobby = new ArrayList<>();
        hobby.add(new Hobby("1", "Aleksei", "Student"));

        return hobby;
    }

    /**
     * Get bitbucket url to your repo.
     */
    public String getBitbucketUrl() {
        return "https://bitbucket.org/alekseivdovyko/homework";
    }

    /**
     * Get phone number.
     */
    public String getPhone() {
        return "+7(964)390-81-27";
    }

    /**
     * Your expectations about course.
     */
    public String getCourseExpectation() {
        return "I want to become a Java programmer";
    }

    /**
     * Print your university and faculty here.
     */
    public String getEducationInfo() {
        return "Saint-Petersburg forest-technical academy, Mechanical technology wood processing";
    }

    public String toString() {
        return "First Name: " + FirstName
                + ", SecondName: " + SecondName
                + ", Phone: " + Phone;
    }
}