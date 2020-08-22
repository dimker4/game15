package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class GameField {
    private final int SIZE;
    private char[][] map;

    public GameField(int SIZE) {
        this.SIZE = SIZE;
        map = new char[SIZE][SIZE];
        generateField();
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
        ArrayList<Integer> values = new ArrayList<>();
        int newValue = (int) (Math.random() * (SIZE * SIZE - 1));
        values.add(newValue);
        int k = 0;
        int iterationNumber = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (newValue == 0) map[i][j] = '-';
                else map[i][j] = (char)(newValue + '0');

                while (values.contains(newValue)) {
                    k++;
                    // Если с 5 раза рандом не сгенерил уникальное значение, то подбираем перебором
                    if (k > 5) {
                       newValue = iterationNumber;
                       iterationNumber++;
                    } else newValue = (int) (Math.random() * (SIZE * SIZE));
                }
                k = 0;
                values.add(newValue);
            }
        }
    }
}
