package com.aoc.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        try (Stream<String> stream = Files.lines(Paths.get("resources/input.txt"))) {
            List<String> lines = stream.collect(Collectors.toList());
            Integer arrivalTime = Integer.valueOf(lines.get(0));

            List<Integer> buses = Arrays.stream(lines.get(1).split(","))
                    .filter(e -> !"x".equals(e))
                    .map(e -> Integer.valueOf(e))
                    .collect(Collectors.toList());

            System.out.println("Solution Part 1: " + getSolutionPart1(buses, arrivalTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Integer getSolutionPart1(List<Integer> buses, Integer arrivalTime) {
        Integer timeToWait = Integer.MAX_VALUE;
        Integer chosenBus = Integer.MAX_VALUE;

        for (Integer bus : buses) {
            Integer timeToLeave = getFirstGreatestMultiple(bus, arrivalTime);
            if (timeToLeave - arrivalTime < timeToWait) {
                timeToWait = timeToLeave - arrivalTime;
                chosenBus = bus;
            }
        }

        return timeToWait * chosenBus;
    }

    private static Integer getFirstGreatestMultiple(Integer x, Integer n) {
        Integer multiple = Integer.valueOf(1);

        multiple = (n / x + 1) * x;

        return multiple;
    }
}
