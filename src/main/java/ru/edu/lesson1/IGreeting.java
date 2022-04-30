package ru.edu.lesson1;

import java.util.Collection;

public interface IGreeting {

    /**
     * Get first name.
     */
    String getFirstName();

    /**
     * Get second name
     */
    String getSecondName();

    /**
     * Get last name.
     */
    String getLastName();

    /**
     * Get hobbies.
     */
    Collection<Hobby> getHobbies();

    /**
     * Get bitbucket url to your repo.
     */
    String getBitbucketUrl();

    /**
     * Get phone number.
     */
    String getPhone();

    /**
     * Your expectations about course.
     */
    String getCourseExpectation();

    /**
     * Print your university and faculty here.
     */
    String getEducationInfo();
}
