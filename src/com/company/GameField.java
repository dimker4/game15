package com.company;

import java.util.Arrays;

public class GameField {
    private final int SIZE;
    private char[][] map;

    public GameField(int SIZE) {
        this.SIZE = SIZE;
        map = new char[SIZE][SIZE];
        map[0][0] = '1';
        map[0][1] = '2';
        map[0][2] = '3';
        map[1][0] = '4';
        map[1][1] = '5';
        map[1][2] = '6';
        map[2][0] = '7';
        map[2][1] = '-';
        map[2][2] = '8';
    }

    public char[][] getField() {
        return map;
    }

    public void setField(char[][] m) {
        this.map = m;
    }

    public void printField() {
        for (int i = 0; i < SIZE; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    private void generateField() {
        // Здесь буду генерировать поле
    }
}
