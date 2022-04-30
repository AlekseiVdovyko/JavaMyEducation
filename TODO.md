#HW_L7_T1 Comparators

1. Написать компаратор CustomDigitComparator, который реализует интерфейс Comparator<Integer>.

Класс CustomDigitComparator определяет следующий порядок: 
 
* Сначала четные числа, затем нечетные 
* На вход подаются числа, отличные от null

2. Реализовать класс Person{name, city, age}, определить метод toString
    
Класс Person реализует интерфейс Comparable<Person>, который обеспечивает следующий порядок: 

* Сортировка сначала по полю city, а затем по полю name
* Поля name, city отличны от null

###Критерии приемки

1. Предоставить Pull Request  из ветки feature/CustomDigitСomparator в другую ветку

Предоставить Pull Request  из ветки feature/PersonComparing в другую ветку

2. Публичные методы должны быть покрыты unit тестами
