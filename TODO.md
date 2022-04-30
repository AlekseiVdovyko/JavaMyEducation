#HW_L1_T1_Greeting

Интерфейс IGreeting позволяет узнать информацию о человеке

1. Напишите класс GreetingImpl, который реализует интерфейс IGreeting.
   Реализуйте метод toString указав значащие поля

2. Метод getHobbies возвращает список. Вам необходимо реализовать класс Hobby, getters & toString

Поля:
- id: String
- name: String
- description: String

ctor(String id, String name, String description);
ctor(String id, String name);

#Критерии приемки
1. Класс должен содежать информацию о вас
2. Класс должен быть протестирован с помощью JUnit на предмет возвращаемых значений.
3. Прислать PR из ветви feature/GreetingImpl в ветке feature/Greeting в вашем репозитории
