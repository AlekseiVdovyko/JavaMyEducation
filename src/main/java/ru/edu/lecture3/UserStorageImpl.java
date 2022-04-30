package ru.edu.lecture3;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class UserStorageImpl implements UserStorage {

    Map<String, User> map = new HashMap<>();

    /**
     * Get user by login. Notice that anton & anToN are same logins.
     *
     * @throws RuntimeException         if user not found
     * @throws IllegalArgumentException if login is null, empty or blank.
     */
    @Override
    public User getUserByLogin(String login) {
        if (login == null || login.isEmpty() || StringUtils.isBlank(login)) {
            throw new IllegalArgumentException("Логин не содержит информацию");
        }
        if (!(map.containsKey(login.toLowerCase()))) {
            throw new RuntimeException("Пользователь отсутствует");
        }

        return map.get(login.toLowerCase());
    }

    /**
     * Put new user into storage.
     *
     * @param user - user for store
     * @throws RuntimeException if user has wrong data, e.g. null or empty login, names, birthdate or gender.
     */
    @Override
    public User put(User user) {
        if (StringUtils.isBlank(user.getLogin()) || StringUtils.isBlank(user.getFirstName())
                || StringUtils.isBlank(user.getLastName()) || user.getGender() == null
                || user.getBirthDate() == null) {
            throw new RuntimeException("Неправильный логин, имя, дата рождения или пол");
        } else {
            map.put(user.getLogin(), new User(user.getLogin(), user.getFirstName(), user.getLastName(),
                    user.getGender(), user.getBirthDate()));
        }
        return user;
    }

    /**
     * Remove user by login.
     *
     * @param login - login
     * @throws RuntimeException         if user not found
     * @throws IllegalArgumentException if login is null, empty or blank.
     */
    @Override
    public User remove(String login) {
        if (login == null || login.isEmpty() || StringUtils.isBlank(login)) {
            throw new IllegalArgumentException("Логин не содержит информацию");
        }
        if (!(map.containsKey(login.toLowerCase()))) {
            throw new RuntimeException("Пользователь отсутствует");
        }

        map.remove(login);
        System.out.println("Пользователь с логином " + login + " удален");

        return map.get(login);
    }

    /**
     * Get all users.
     */
    @Override
    public List<User> getAllUsers() {
        ArrayList<User> usersList = new ArrayList<>(map.values());

        return usersList;
    }

    /**
     * Get all users by gender.
     *
     * @param gender - gender
     * @throws IllegalArgumentException if gender is null
     */
    @Override
    public List<User> getAllUsersByGender(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Такой гендерной принадлежности не существует.");
        }

        List<User> usersGender = new ArrayList<>(map.values());
        for (int i = 0; i <= usersGender.size()-1; ++i) {
                if (usersGender.get(i).getGender() != gender) {
                    usersGender.remove(i);
                }
            }

        return usersGender;
    }

    /**
     * Get user age.
     *
     * @param login - login
     * @throws RuntimeException         if user not found
     * @throws IllegalArgumentException if login is null, empty or blank.
     */
    @Override
    public int getUserAge(String login) {
        if (login == null || login.isEmpty() || StringUtils.isBlank(login)) {
            throw new IllegalArgumentException("Логин не содержит информацию");
        }
        if (!(map.containsKey(login.toLowerCase()))) {
            throw new RuntimeException("Пользователь отсутствует");
        }

        return Period.between(map.get(login).getBirthDate(), LocalDate.now()).getYears();
    }
}
