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
            List<String> gridAfterSimulationPart1 = simulatePart1(grid);
            Long occupiedSeatsPart1 = gridAfterSimulationPart1.stream()
                    .map(row -> row.chars().filter(ch -> ch == '#').count())
                    .reduce(0L, (subtotal, element) -> subtotal + element);
            System.out.println("Solution Part 1: " + occupiedSeatsPart1);

            List<String> gridAfterSimulationPart2 = simulatePart2(grid);
            Long occupiedSeatsPart2 = gridAfterSimulationPart2.stream()
                    .map(row -> row.chars().filter(ch -> ch == '#').count())
                    .reduce(0L, (subtotal, element) -> subtotal + element);
            System.out.println("Solution Part 2: " + occupiedSeatsPart2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> simulatePart1(List<String> grid) {
        boolean hasChanged;
        List<String> currentSimulation;


        do {
            hasChanged = false;
            currentSimulation = new ArrayList<>(grid);
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).length(); j++) {
                    char nextState = getNextStatePart1(grid, i, j);
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

    private static char getNextStatePart1(List<String> grid, int i, int j) {
        char currentState = grid.get(i).charAt(j);

        if (currentState == 'L') {
            if (countOccupiedSeatsPart1(grid, i, j) == 0) {
                return '#';
            }
        } else if (currentState == '#') {
            if (countOccupiedSeatsPart1(grid, i, j) >= 4) {
                return 'L';
            }
        }
        return currentState;
    }

    private static int countOccupiedSeatsPart1(List<String> grid, int i, int j) {
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

    private static int countOccupiedSeatsPart2(List<String> grid, int i, int j) {
        int n = grid.size();
        int m = grid.get(i).length();
        int occupiedSeats = 0;

        // count all occupied seats until first empty/occupied seat on the same row right
        for (int col = j + 1; col < m; col++) {
            if (col != j) {
                if (grid.get(i).charAt(col) == 'L') {
                    break;
                }
                if (grid.get(i).charAt(col) == '#') {
                    occupiedSeats++;
                    break;
                }
            }
        }

        // count all occupied seats until first empty/occupied seat on the same row left
        for (int col = j - 1; col >= 0; col--) {
            if (col != j) {
                if (grid.get(i).charAt(col) == 'L') {
                    break;
                }
                if (grid.get(i).charAt(col) == '#') {
                    occupiedSeats++;
                    break;
                }
            }
        }

        // count all occupied seats until first empty/occupied seat on the same column up
        for (int row = i - 1; row >= 0; row--) {
            if (row != i) {
                if (grid.get(row).charAt(j) == 'L') {
                    break;
                }
                if (grid.get(row).charAt(j) == '#') {
                    occupiedSeats++;
                    break;
                }
            }
        }

        // count all occupied seats until first empty/occupied seat on the same column down
        for (int row = i + 1; row < n; row++) {
            if (row != i) {
                if (grid.get(row).charAt(j) == 'L') {
                    break;
                }
                if (grid.get(row).charAt(j) == '#') {
                    occupiedSeats++;
                    break;
                }
            }
        }

        // count all occupied seats until first empty/occupied seat on the diagonal left up
        int row = i - 1;
        int col = j - 1;
        while (row >= 0 && col >= 0) {
            if (grid.get(row).charAt(col) == 'L') {
                break;
            }
            if (grid.get(row).charAt(col) == '#') {
                occupiedSeats++;
                break;
            }
            row--;
            col--;
        }

        // count all occupied seats until empty/occupied empty seat on the diagonal left down
        row = i + 1;
        col = j + 1;
        while (row < n && col < m) {
            if (grid.get(row).charAt(col) == 'L') {
                break;
            }
            if (grid.get(row).charAt(col) == '#') {
                occupiedSeats++;
                break;
            }
            row++;
            col++;
        }

        // count all occupied seats until first empty/occupied seat on the diagonal right down
        row = i + 1;
        col = j - 1;
        while (row < n && col >= 0) {
            if (grid.get(row).charAt(col) == 'L') {
                break;
            }
            if (grid.get(row).charAt(col) == '#') {
                occupiedSeats++;
                break;
            }
            row++;
            col--;
        }

        // count all occupied seats until first empty/occupied seat on the diagonal right up
        row = i - 1;
        col = j + 1;
        while (row >= 0 && col < m) {
            if (grid.get(row).charAt(col) == 'L') {
                break;
            }
            if (grid.get(row).charAt(col) == '#') {
                occupiedSeats++;
                break;
            }
            row--;
            col++;
        }

        return occupiedSeats;
    }

    private static char getNextStatePart2(List<String> grid, int i, int j) {
        char currentState = grid.get(i).charAt(j);

        if (currentState == 'L') {
            if (countOccupiedSeatsPart2(grid, i, j) == 0) {
                return '#';
            }
        } else if (currentState == '#') {
            if (countOccupiedSeatsPart2(grid, i, j) >= 5) {
                return 'L';
            }
        }
        return currentState;
    }

    private static List<String> simulatePart2(List<String> grid) {
        boolean hasChanged;
        List<String> currentSimulation;


        do {
            hasChanged = false;
            currentSimulation = new ArrayList<>(grid);
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).length(); j++) {
                    char nextState = getNextStatePart2(grid, i, j);
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
}
