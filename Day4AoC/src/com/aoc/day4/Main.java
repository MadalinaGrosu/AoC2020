package com.aoc.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    private static int counterPart1 = 0;
    private static int counterPart2 = 0;
    private static PassportData currentPassport = new PassportData();

    public static void main(String[] args) {
        try (Stream<String> stream = Files.lines(Paths.get("resources/input.txt"))) {
            stream.forEach(line -> analyzeLinePart1(line));
            System.out.println("Solution Part 1: " + counterPart1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentPassport = new PassportData();
        try (Stream<String> stream = Files.lines(Paths.get("resources/input.txt"))) {
            stream.forEach(line -> analyzeLinePart2(line));
            System.out.println("Solution Part 2: " + counterPart2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void analyzeLinePart1(String line) {
        if (line.isEmpty()) {
            if (currentPassport.isValid()) {
                counterPart1++;
            }
            currentPassport = new PassportData();
        } else {
            StringTokenizer stringTokenizer = new StringTokenizer(line, ": ");
            while (stringTokenizer.hasMoreElements()) {
                currentPassport.addData(stringTokenizer.nextToken(), stringTokenizer.nextToken());
            }
        }

    }

    private static void analyzeLinePart2(String line) {
        if (line.isEmpty()) {
            if (currentPassport.isStrictlyValid()) {
                counterPart2++;
            }
            currentPassport = new PassportData();
        } else {
            StringTokenizer stringTokenizer = new StringTokenizer(line, ": ");
            while (stringTokenizer.hasMoreElements()) {
                currentPassport.addData(stringTokenizer.nextToken(), stringTokenizer.nextToken());
            }
        }

    }
}
