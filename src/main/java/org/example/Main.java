package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Creating united array for files
        ArrayList mass;

        // Creating new class objects
        Sorter sorter = new Sorter();
        FileConfig config = new FileConfig(sorter);

        // Program processing
        config.makeConfig(args); // Getting configuration of filter
        mass = config.readFiles(config.getFiles()); // Filling unsorted array
        sorter.sortElems(mass); // Sorting the unsorted array
        config.configImplementation(); // Implementation of all functions
    }
}