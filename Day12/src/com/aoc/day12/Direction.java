package com.aoc.day12;

public enum Direction {
    N("N", 0, -1),
    S("S", 0, 1),
    E("E", 1, 0),
    W("W", -1, 0),
    F("F"),
    L("L"),
    R("R");

    String move;
    int x;
    int y;

    Direction(String move) {
        this.move = move;
    }

    Direction(String move, int x, int y) {
        this.move = move;
        this.x = x;
        this.y = y;
    }

    Direction rotateLeft() {
        switch (this) {
            case E: return N;
            case N: return W;
            case W: return S;
            case S: return E;
        }
        return null;
    }

    Direction rotateRight() {
        switch (this) {
            case E: return S;
            case S: return W;
            case W: return N;
            case N: return E;
        }
        return null;
    }
}
