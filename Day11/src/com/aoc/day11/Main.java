package com.aoc.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        try (Stream<String> stream = Files.lines(Paths.get("resources/input.txt"))) {
            List<String> grid = stream.collect(Collectors.toList());
            List<String> gridAfterSimulation = simulate(grid);
            Long occupiedSeats = gridAfterSimulation.stream()
                    .map(row -> row.chars().filter(ch -> ch == '#').count())
                    .reduce(0L, (subtotal, element) -> subtotal + element);
            System.out.println("Solution Part 1: " + occupiedSeats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> simulate(List<String> grid) {
        boolean hasChanged;
        List<String> currentSimulation;


        do {
            hasChanged = false;
            currentSimulation = new ArrayList<>(grid);
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).length(); j++) {
                    char nextState = getNextState(grid, i, j);
                    if (nextState != grid.get(i).charAt(j)) {
                        hasChanged = true;
                        char[] currentRow = currentSimulation.get(i).toCharArray();
                        currentRow[j] = nextState;
                        currentSimulation.set(i, String.valueOf(currentRow));
                    }
                }
            }
            grid = currentSimulation;
        } while (hasChanged);

        return grid;
    }

    private static char getNextState(List<String> grid, int i, int j) {
        char currentState = grid.get(i).charAt(j);

        if (currentState == 'L') {
            if (countOccupiedSeats(grid, i, j) == 0) {
                return '#';
            }
        } else if (currentState == '#') {
            if (countOccupiedSeats(grid, i, j) >= 4) {
                return 'L';
            }
        }
        return currentState;
    }

    private static int countOccupiedSeats(List<String> grid, int i, int j) {
        int n = grid.size();
        int m = grid.get(i).length();
        int occupiedSeats = 0;

        if (i - 1 >= 0 && j - 1 >= 0 && grid.get(i - 1).charAt(j - 1) == '#') {
            occupiedSeats++;
        }
        if (i - 1 >= 0 && grid.get(i - 1).charAt(j) == '#') {
            occupiedSeats++;
        }
        if (i - 1 >= 0 && j + 1 < m && grid.get(i - 1).charAt(j + 1) == '#') {
            occupiedSeats++;
        }
        if (j - 1 >= 0 && grid.get(i).charAt(j - 1) == '#') {
            occupiedSeats++;
        }
        if (j + 1 < m && grid.get(i).charAt(j + 1) == '#') {
            occupiedSeats++;
        }
        if (i + 1 < n && j - 1 >= 0 && grid.get(i + 1).charAt(j - 1) == '#') {
            occupiedSeats++;
        }
        if (i + 1 < n && grid.get(i + 1).charAt(j) == '#') {
            occupiedSeats++;
        }
        if (i + 1 < n && j + 1 < m && grid.get(i + 1).charAt(j + 1) == '#') {
            occupiedSeats++;
        }

        return occupiedSeats;
    }
}
