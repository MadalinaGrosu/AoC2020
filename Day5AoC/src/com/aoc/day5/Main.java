package com.aoc.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try (Stream<String> stream = Files.lines(Paths.get("resources/input.txt"))) {
            Optional<Integer> max = stream.map(line -> getSeatId(line))
                    .max(Comparator.naturalOrder());
            System.out.println("Solution Part 1: " + max.get());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<String> stream = Files.lines(Paths.get("resources/input.txt"))) {
            List<Integer> seats = stream.map(line -> getSeatId(line))
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());
            System.out.println("Solution Part 2: " + findMySeat(seats));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getSeatId(String line) {
        int firstRow = 0;
        int lastRow = 127;
        int middleRow = 0, middleColumn = 0, i;
        int firstColumn = 0;
        int lastColumn = 7;
        int row = 0, column = 0;

        for (i = 0; i < 7; i++) {
            middleRow = firstRow + (lastRow - firstRow) / 2;
            if (line.charAt(i) == 'F') {
                // pick first half
                lastRow = middleRow;
                if (i == 6) {
                    row = firstRow;
                }
            } else if (line.charAt(i) == 'B') {
                // pick second half
                firstRow = middleRow + 1;
                if (i == 6) {
                    row = lastRow;
                }
            }
        }

        for (; i < line.length(); i++) {
            middleColumn = firstColumn + (lastColumn - firstColumn) / 2;
            if (line.charAt(i) == 'L') {
                // pick first half
                lastColumn = middleColumn;
                if (i == 9) {
                    column = firstColumn;
                }
            } else if (line.charAt(i) == 'R') {
                // pick second half
                firstColumn = middleColumn + 1;
                if (i == 9) {
                    column = lastColumn;
                }
            }
        }
        return row * 8 + column;
    }

    private static Integer findMySeat(List<Integer> seats) {
        int i = 0;
        int n = seats.size();

        while (i < n - 1) {
            if (seats.get(i + 1) - seats.get(i) > 1) {
                return seats.get(i) + 1;
            }
            i++;
        }

        return -1;
    }
}
