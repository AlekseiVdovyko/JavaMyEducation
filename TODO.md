# HW_L4_T1_FileAnalyser

Интерфейс FileAnalyser предоставляет апи для анализа содержимого файла.

1. Реализуйте класс FileAnalyserImpl.

```java
ctor(String filePath); // filePath - путь к анализируемому файлу
```

2. Метод saveSummary должен сохранять результат анализа в другом файле в следующем формате

Пример анализа файла input.txt:
Hello!

```code
    FileAnalyzer analyzer = new FileAnalyserImpl("input.txt");
    analyzer.saveSummary("summary.txt");
```

Результат анализа записан в файле summary.txt:

```code
fileName: input.txt
rowsCount: 1
totalSymbols: 6
symbolsStatistics: {'H': 1, 'e': 1, 'l': 2, 'o': 1}
top3PopularSymbols: 'l', 'H', 'e'
```

# Критерии приемки

1. Класс должен быть протестирован с помощью JUnit на предмет возвращаемых значений.
2. Написать тесты на альтернативные сценарии. (когда метод выбрасывает исключение)
3. Прислать PR из ветки feature/FileAnalyserImpl в ветке feature/FileAnalyser в вашем репозитории
