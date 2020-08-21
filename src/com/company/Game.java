package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private static char[][] map;
    public static GameField gameField;
    private static int SIZE;

    public static void start() {
        int size;
        boolean gameInProcess = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введи размер поля: ");
        size = sc.nextInt();
        SIZE = size - 1;
        gameField = new GameField(size);
        map = gameField.getField();

        gameField.printField();

        while (!checkToWin()) {
            System.out.println("Делай свой ход");
            int y = sc.nextInt();
            int x = sc.nextInt();
            makeMove(y, x);
        }
        System.out.println("Победа!");
    }

    public static void makeMove(int y, int x) {
        if (y == x && y == 0) {
            checkDown(y, x);
            checkRight(y, x);
        } else if (y == SIZE && x == 0) {
            checkUp(y, x);
            checkRight(y, x);
        } else if (y == 0 && x == SIZE) {
            checkLeft(y, x);
            checkDown(y, x);
        } else if (y == SIZE && x == SIZE) {
            checkLeft(y, x);
            checkUp(y, x);
        } else if (y == 0) {
            checkRight(y, x);
            checkLeft(y, x);
            checkDown(y, x);
        } else if (y == SIZE) {
            checkLeft(y, x);
            checkRight(y, x);
            checkUp(y, x);
        } else if (x == 0){
            checkUp(y, x);
            checkDown(y, x);
            checkRight(y, x);
        } else if (x == SIZE) {
            checkUp(y, x);
            checkDown(y, x);
            checkLeft(y, x);
        } else {
            checkUp(y, x);
            checkDown(y, x);
            checkLeft(y, x);
            checkRight(y, x);
        }

        gameField.printField();
    }

    private static void swap(int y1, int x1, int y2, int x2) {
        char a;
        a = map[y1][x1];
        map[y1][x1] = map[y2][x2];
        map[y2][x2] = a;
    }

    private static void checkUp(int y, int x) {
         if (map[y-1][x] == '-') swap(y, x, y-1, x);
    }

    private static void checkDown(int y, int x) {
        if (map[y+1][x] == '-') swap(y, x, y + 1, x);
    }

    private static void checkLeft(int y, int x) {
        if (map[y][x-1] == '-') swap(y, x, y, x - 1);
    }

    private static void checkRight(int y, int x) {
        if (map[y][x+1] == '-') swap(y, x, y, x + 1);
    }

    private static boolean checkToWin() {
        int k = 1;
        int cnt = 0;
        int mapLength = map.length;
        for (int i = 0; i <= SIZE; i++) {
            for (int j = 0; j <= SIZE; j++) {
                if (map[i][j] == (char)(k + '0')) {
                    k++;
                    cnt++;
                } else if (k == mapLength * mapLength && cnt == ((mapLength * mapLength) - 1) && map[i][j] == '-') return true;
                else return false;
            }
        }
        return true;
    }

}
