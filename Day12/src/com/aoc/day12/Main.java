package com.aoc.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.aoc.day12.Direction.E;

public class Main {
    public static void main(String[] args) {

        try (Stream<String> stream = Files.lines(Paths.get("resources/input.txt"))) {
            List<Instruction> instructions = stream.map(l ->
                    new Instruction(Direction.valueOf(l.substring(0, 1)), Integer.valueOf(l.substring(1))))
            .collect(Collectors.toList());

            System.out.println("Solution Part 1: " + getSolutionPart1(instructions));
            System.out.println("Solution Part 2: " + getSolutionPart2(instructions));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getSolutionPart1(List<Instruction> instructions) {
        Direction currentDirection = E;
        int[] coord = new int[2];

        for (Instruction instr : instructions) {
            int value = instr.getValue();
            switch (instr.getDirection()) {
                case F: // update coordinates with value
                    coord[0] = coord[0] + value * currentDirection.x;
                    coord[1] = coord[1] + value * currentDirection.y;
                    break;
                case L: // rotate to left by value degrees
                    for (int i = 0; i < value/90; i++) {
                        currentDirection = currentDirection.rotateLeft();
                    }
                    break;
                case R: // rotate to right by value degrees
                    for (int i = 0; i < value/90; i++) {
                        currentDirection = currentDirection.rotateRight();
                    }
                    break;
                default: // it's N, E, S or W
                    coord[0] = coord[0] + value * instr.getDirection().x;
                    coord[1] = coord[1] + value * instr.getDirection().y;
            }
        }

        return Math.abs(coord[0]) + Math.abs(coord[1]);
    }

    private static int getSolutionPart2(List<Instruction> instructions) {
        int[] waypoint = new int[2];
        int[] shipCoord = new int[2];
        waypoint[0] = 10;
        waypoint[1] = -1;

        for (Instruction instr : instructions) {
            int value = instr.getValue();
            switch (instr.getDirection()) {
                case F: // update coordinates with value
                    shipCoord[0] = shipCoord[0] + value * waypoint[0];
                    shipCoord[1] = shipCoord[1] + value * waypoint[1];
                    break;
                case L: // rotate to left by value degrees
                    for (int i = 0; i < value/90; i++) {
                        int temp = waypoint[0];
                        waypoint[0] = waypoint[1];
                        waypoint[1] = -temp;
                    }
                    break;
                case R: // rotate to right by value degrees
                    for (int i = 0; i < value/90; i++) {
                        int temp = waypoint[0];
                        waypoint[0] = -waypoint[1];
                        waypoint[1] = temp;
                    }
                    break;
                default: // it's N, E, S or W
                    waypoint[0] = waypoint[0] + value * instr.getDirection().x;
                    waypoint[1] = waypoint[1] + value * instr.getDirection().y;
            }
        }

        return Math.abs(shipCoord[0]) + Math.abs(shipCoord[1]);
    }

}
