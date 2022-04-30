# HW_L3_T1_UserStorage

Интерфейс UserStorage предоставляет апи для хранения и поиска пользователей.

1. Реализуйте класс User (getters & toString()), который содержит информацию о пользователе.

```java
ctor(String login, String firstName, String lastName, Gender gender, LocalDate birthDate);

Gender - enum, который хранит пол пользователя.
```

2. Напишите класс UserStorageImpl, который реализует интерфейс UserStorage.
3. Обработать ситуации когда в метод передаются некорректные аргументы

# Критерии приемки

1. Класс должен быть протестирован с помощью JUnit на предмет возвращаемых значений.
2. Написать тесты на альтернативные сценарии. (когда метод выбрасывает исключение)
3. Прислать PR из ветки feature/UserStorageImpl в ветке feature/UserStorage в вашем репозитории
