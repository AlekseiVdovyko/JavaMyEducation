package ru.edu.lecture3;

import java.io.*;
import java.util.*;

public class FileAnalyserImpl implements FileAnalyser {

    private String fileName;
    private int rowsCount;
    private String filePath;
    private int lettersCount;




    public FileAnalyserImpl() {
    }

    public FileAnalyserImpl(String filePath) {
        this.filePath = filePath;
    }

    Map<Character, Integer> statistics = new TreeMap<>();
    List<Character> keys = new ArrayList<>();
    List<Character> keys2 = new ArrayList<>();

    /**
     * Get file name.
     */
    @Override
    public String getFileName() {
        String tmp = filePath;
        String[] parts = tmp.split("/");
        String fileName = parts[parts.length - 1];
        return fileName;
    }

    /**
     * Get rows count
     */
    @Override
    public int getRowsCount() {
        try (BufferedReader input = new BufferedReader(new FileReader(filePath))) {
            while (input.readLine() != null) {
                rowsCount += 1;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден");
        } catch (Exception ex) {
            System.out.println("Неверно введены данные");
        }

        return rowsCount;
    }

    /**
     * Get total letters count.
     */
    @Override
    public int getLettersCount() {
        try (BufferedReader input = new BufferedReader(new FileReader(filePath))) {
            while (input.ready()) {
                for (char symbol : input.readLine().toCharArray()) {
                    lettersCount += 1;
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден");
        } catch (Exception ex) {
            System.out.println("Неверно введены данные");
        }

        return lettersCount;
    }

    /**
     * Get symbols statistics by letters entry.
     * Analyzes only digits 0-9, letters a-z and A-Z
     *
     * @return {'a': 2, 'b': 10}
     */
    @Override
    public Map<Character, Integer> getSymbolsStatistics() {

        try (FileReader input = new FileReader(filePath)) {

            char x;

            while (input.ready()) {
                if (!Character.isLetter(x = (char) input.read())) continue;
                if (statistics.containsKey(x)) {
                    statistics.put(x, statistics.get(x) + 1);
                } else statistics.put(x, 1);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден");
        } catch (Exception ex) {
            System.out.println("Неверно введены данные");
        }

        return statistics;
    }


    /**
     * Get top N popular symbols
     * Analyzes only digits 0-9, letters a-z and A-Z
     *
     * @param n - n
     */
    @Override
    public List<Character> getTopNPopularSymbols(int n) {
        if (n < 0) {
                throw new IllegalArgumentException("Введено отрицательное значение");
            }

        List<Map.Entry<Character, Integer>> entrySet2 = new LinkedList<>();


        for (Map.Entry<Character, Integer> entry : statistics.entrySet()) {
            keys.add(entry.getKey());
        }

        for (int i = 0; i < n; i++) {
            keys2.add(keys.get(i));

        }

        return keys2;
    }

    /**
     * Store summary to file.
     *
     * @param filePath - file name
     */
    @Override
    public void saveSummary(String filePath) {
        try (FileReader input = new FileReader(filePath);
            FileWriter output = new FileWriter("output.txt")) {

            int ch = input.read();
            while (ch != -1) {
                output.write(ch);
                ch = input.read();
            }

            output.write("\n\nfileName: " + getFileName() + "\n"
                    + "rowsCount: " + getRowsCount() + "\n"
                    + "lettersCount: " + getLettersCount() + "\n"
                    + "symbolsStatistics:" + getSymbolsStatistics() + "\n"
                    + "topNPopularSymbols: " + getTopNPopularSymbols(3));


        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден");
        } catch (IllegalArgumentException ex) {
            System.out.println("Введено отрицательное значение");
        } catch (Exception ex) {
            System.out.println("Неверно введены данные");
        }
    }
}
