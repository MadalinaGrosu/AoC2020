package com.aoc.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.stream.Stream;

public class Main {
    private static int counterPart1 = 0;
    private static HashSet<Character> questionsPart1 = new HashSet<>();

    private static int counterPart2 = 0;
    private static HashSet<Character> questionsPart2 = new HashSet<>();
    private static boolean newGroup = true;

    public static void main(String[] args) {
        try (Stream<String> stream = Files.lines(Paths.get("resources/input.txt"))) {
            stream.forEach(Main::analyzeLinePart1);
            counterPart1 += questionsPart1.size();
            System.out.println("Solution Part 1: " + counterPart1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<String> stream = Files.lines(Paths.get("resources/input.txt"))) {
            stream.forEach(Main::analyzeLinePart2);
            counterPart2 += questionsPart2.size();
            System.out.println("Solution Part 2: " + counterPart2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void analyzeLinePart1(String line) {
        if (line.isEmpty()) {
            counterPart1 += questionsPart1.size();
            questionsPart1.clear();
        } else {
            for (int i = 0; i < line.length(); i++) {
                questionsPart1.add(line.charAt(i));
            }
        }
    }

    private static void analyzeLinePart2(String line) {
        if (line.isEmpty()) {
            counterPart2 += questionsPart2.size();
            questionsPart2.clear();
            newGroup = true;
        } else {
            if (newGroup) {
                for (int i = 0; i < line.length(); i++) {
                    questionsPart2.add(line.charAt(i));
                }
                newGroup = false;
            } else {
                HashSet<Character> temp = new HashSet<>();
                temp.addAll(questionsPart2);
                for (Character c : temp) {
                    if (line.indexOf(c) == -1) {
                        questionsPart2.remove(c);
                    }
                }
                temp.clear();
            }
        }
    }
}
