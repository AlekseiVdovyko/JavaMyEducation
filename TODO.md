#HW_L6_T1_CustomArrayImpl

Реализовать класс CustomArrayImpl<T>, который представляет динамический массив.

Класс CustomArrayImpl реализует интерфейс CustomArray<T>

Класс CustomArrayImpl может хранить объекты любого типа

Класс CustomArrayImpl может динамически расширяться

##Конструкторы

CustomArrayImpl();
CustomArrayImpl(int capacity);
CustomArrayImpl(Collection<T> c);

#Критерии приемки

1. Создать ветку feature/CustomArray
2. Добавить интерфейс CustomArray в ветку, сделать PUSH в удаленный репозиторий

3. Создать ветку feature/CustomArrayImpl от ветки feature/CustomArray

4. Написать реализацию класса CustomArrayImpl

5. Предоставить на проверку Pull Request из ветки feature/CustomArrayImpl в ветку feature/CustomArray

6. Каждый публичный метод класса CustomArrayImpl должен быть покрыт unit тестом

7. !!! Вносить правки в интерфейс CustomArray<T> нельзя

