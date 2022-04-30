import org.junit.Assert;
import org.junit.Test;
import ru.edu.lecture3.Gender;
import ru.edu.lecture3.User;
import ru.edu.lecture3.UserStorageImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserStorageTests {

    private UserStorageImpl userStorage = new UserStorageImpl();

    @Test
    public void userStorageTest() {

        Assert.assertNotNull(userStorage);

        User user1 = new User("123", "Aleksei", "Vdovyko",
                Gender.MALE, LocalDate.of(1979, 7, 7));
        User user2 = new User("321", "Marina", "Ivanova",
                Gender.FEMALE, LocalDate.of(2009, 5, 3));
        User user3 = new User("213", "Oleg", "Novikov",
                Gender.MALE, LocalDate.of(1983, 3, 4));
        User user4 = new User("312", "Natasha", "Rostova",
                Gender.FEMALE, LocalDate.of(1815, 11, 24));

        Assert.assertEquals(user1.toString(), userStorage.put(user1).toString());
        Assert.assertEquals(user2.toString(), userStorage.put(user2).toString());
        Assert.assertEquals(user3.toString(), userStorage.put(user3).toString());
        Assert.assertEquals(user4.toString(), userStorage.put(user4).toString());


        Assert.assertEquals(42, userStorage.getUserAge("123"));

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        List<User> genderTest = new ArrayList<>();
        for (User user : users) {
            if (user.getGender() == Gender.MALE) {
                genderTest.add(user);
            }
        }
        Assert.assertTrue(genderTest.size() == userStorage.getAllUsersByGender(Gender.MALE).size());
    }

    @Test
    public void getUserByLoginTest() {
        try {
            userStorage.getUserByLogin(null);
            Assert.fail("Логин не содержит информацию");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("Логин не содержит информацию", ex.getMessage());
        }

        try {
            userStorage.getUserByLogin("");
            Assert.fail("Логин не содержит информацию");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("Логин не содержит информацию", ex.getMessage());
        }

        try {
            userStorage.getUserByLogin("44");
            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("Пользователь отсутствует", ex.getMessage());
        }
    }

    @Test
    public void putTest() {
        try {
            User user = new User(null, "Nikita", "Ivanov",
                    Gender.MALE, LocalDate.of(1979, 7, 7));
            userStorage.put(user);
            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("Неправильный логин, имя, дата рождения или пол", ex.getMessage());
        }

        try {
            User user = new User("", "Nikita", "Ivanov",
                    Gender.MALE, LocalDate.of(1979, 7, 7));
            userStorage.put(user);
            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("Неправильный логин, имя, дата рождения или пол", ex.getMessage());
        }

        try {
            User user = new User("1234", "", "Ivanov",
                    Gender.MALE, LocalDate.of(1979, 7, 7));
            userStorage.put(user);
            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("Неправильный логин, имя, дата рождения или пол", ex.getMessage());
        }
    }

    @Test
    public void removeTest() {
        try {
            userStorage.remove("44");
            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("Пользователь отсутствует", ex.getMessage());
        }

        try {
            userStorage.remove(null);
            Assert.fail("Логин не содержит информацию");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("Логин не содержит информацию", ex.getMessage());
        }
    }

    @Test
    public void getAllUsersByGenderTest() {
        try {
            userStorage.getAllUsersByGender(null);
            Assert.fail("Такой гендерной принадлежности не существует.");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("Такой гендерной принадлежности не существует.", ex.getMessage());
        }
    }

    @Test
    public void getUserAgeTest() {
        try {
            userStorage.getUserAge(null);
            Assert.fail("Логин не содержит информацию");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("Логин не содержит информацию", ex.getMessage());
        }

        try {
            userStorage.getUserAge("2222");
            Assert.fail();
        } catch (RuntimeException ex) {
            Assert.assertEquals("Пользователь отсутствует", ex.getMessage());
        }
    }
}