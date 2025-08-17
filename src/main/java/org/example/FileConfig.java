package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileConfig {
    private final Sorter srt;

    // Creating new sorter
    FileConfig (Sorter sorter) {
        this.srt = sorter;
    }
    // Options flags
    private boolean hasFilepath = false; // Option -o
    private boolean hasPrefix = false; // Option -p
    private boolean hasAdd = false; // Option -a
    private boolean hasShortStat = false; // Option -s
    private boolean hasLongStat = false; // Option -f
    // Program variables
    private ArrayList<String> inputFiles = new ArrayList<>(); // Input files
    private ArrayList<String> mass = new ArrayList<>(); // Unsorted array
    private String prefix; // Prefix for option -p
    private String resultPath; // Resulting path for option -o
    // Getting filter configuration
    public void makeConfig(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    hasFilepath = true;
                    if (i + 1 < args.length) {
                        resultPath = args[++i]; // Next argument
                    } else {
                        System.err.println("Ошибка: после -o требуется путь файла, программа остановлена");
                        return;
                    }
                    break;
                case "-p":
                    hasPrefix = true;
                    if (i + 1 < args.length) {
                        prefix = args[++i]; // Next argument
                    } else {
                        System.err.println("Ошибка: после -p требуется префикс, программа остановлена");
                        return;
                    }
                    break;
                case "-a":
                    hasAdd = true;
                    break;
                case "-s":
                    hasShortStat = true;
                    break;
                case "-f":
                    hasLongStat = true;
                    break;
                default:
                    if (args[i].startsWith("-")) {
                        System.err.println("Неизвестная опция: " + args[i]);
                        return;
                    }
                    inputFiles.add(args[i]);
            }

        }
    }
    // Read files method
    public ArrayList readFiles (ArrayList inputFiles) {
        for (Object file : inputFiles) {
            Scanner reader = null;
            try {
                reader = new Scanner(new File(String.valueOf(file)));
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден. Программа остановлена");
            }
            // Filling an array with all the lines in the files
            while (reader.hasNextLine()) {
                mass.add(reader.nextLine());
            }
            // Scanner close
            reader.close();
        }
        return mass;
    }
    // Files getter
    public ArrayList getFiles () {
        return inputFiles;
    }
    // Implementation of the specified functions by the method
    public void configImplementation () {
        getCharacteristic(hasShortStat, hasLongStat); // Output characteristics
        createFile(createFilepath("integers.txt"), srt.getIntegersElem()); // Creating integers file
        createFile(createFilepath("floats.txt"), srt.getFloatsElem()); // Creating floats file
        createFile(createFilepath("strings.txt"), srt.getStringsElem()); // Creating strings file
    }
    // ----------Execution methods---------------
    // Get short/full characteristics
    private void getCharacteristic (boolean shortFlag, boolean fullFlag) {
        if (shortFlag && fullFlag) {
            srt.fullStat();
        } else if (shortFlag) {
            srt.shortStat();
        } else if (fullFlag) {
            srt.fullStat();
        }
    }
    // Creating filepath method
    private String createFilepath (String filename) {
        if (hasFilepath && hasPrefix) {
            return resultPath + '/' + prefix + filename;
        } else if (hasFilepath) {
            return resultPath + '/' + filename;
        } else if (hasPrefix) {
            return prefix + filename;
        } else {
            return filename;
        }
    }
    // Creating file method
    private void createFile (String fileName, ArrayList array) {
        if (array.isEmpty()) {
            System.out.printf("Файл %s не создан по причине отсутствия элементов\n", fileName);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, hasAdd))) {
            for (Object str : array) {
                writer.write((String) str);
                writer.newLine();
            }
            System.out.printf("Данные успешно сохранены в файл: %s\n", fileName);
        } catch (IOException e) {
            System.out.printf("Ошибка при записи файла: %s\n", fileName);
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
