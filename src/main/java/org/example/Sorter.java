package org.example;

import java.util.ArrayList;

public class Sorter {
    private ArrayList stringsElem = new ArrayList(); // Array for Strings
    private ArrayList integersElem = new ArrayList(); // Array for Integers
    private ArrayList floatsElem = new ArrayList(); // Array for Floats
    private float maxNum; // Max num for statistic
    private float minNum; // Min num for statistic
    private float sumElem; // Sum for numbers
    private int maxString = Integer.MIN_VALUE; // The size of the longest string
    private int minString = Integer.MAX_VALUE; // The size of the shortest string
    // Sorting method
    public void sortElems (ArrayList<String> mass) {
        for (String str : mass) {
            try {
                float flt = Float.parseFloat(str);
                maxNum = Float.max(maxNum, flt);
                minNum = Float.min(minNum, flt);
                sumElem += flt;
                if (flt % 1 == 0) {
                    integersElem.add(str);
                }
                else {
                    floatsElem.add(str);
                }
            }
            catch (NumberFormatException e) {
                stringsElem.add(str);
                maxString = Integer.max(maxString, str.length());
                minString = Integer.min(minString, str.length());
            }
        }
    }
    // Statistical methods
    // Short statistic
    public void shortStat () {
        int totalElements = stringsElem.size() + integersElem.size() + floatsElem.size();
        System.out.printf("""
                Общее количество элементов - %d
                В файл с целыми числами сохранено - %d элементов/элемента
                В файл с вещественными числами сохранено - %d элементов/элемента
                В файл со строками сохранено - %d элементов/элемента\n""", totalElements, integersElem.size(), floatsElem.size(), stringsElem.size());
    }
    // Full statistic
    public void fullStat () {
        int totalElements = stringsElem.size() + integersElem.size() + floatsElem.size();
        int totalNumbers = integersElem.size() + floatsElem.size();
        System.out.printf("""
                Общее количество элементов - %d
                В файл с целыми числами сохранено - %d элементов/элемента
                В файл с вещественными числами сохранено - %d элементов/элемента
                В файл со строками сохранено - %d элементов/элемента
                
                Статистика для чисел:
                Максимальное значение - %f
                Минимальное значение - %f
                Сумма - %f
                Среднее - %f
                
                Статистика для строк:
                Размер самой короткой строки - %d
                Размер самой длинной строки - %d\n
                """, totalElements, integersElem.size(), floatsElem.size(), stringsElem.size(),
                maxNum, minNum, sumElem, sumElem / totalNumbers,
                minString, maxString);
    }
    // -------------Getters--------------
    // String getter
    public ArrayList getStringsElem () {
        return this.stringsElem;
    }
    // Float getter
    public ArrayList getFloatsElem() {
        return floatsElem;
    }
    // Integer getter
    public ArrayList getIntegersElem() {
        return integersElem;
    }
}
