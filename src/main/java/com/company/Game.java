package com.company;

import java.util.Scanner;

public class Game {
    private static String[][] map;
    public static GameField gameField;
    private static int SIZE;
    private static int y;
    private static int x;

    public static void start() {
        int size;
        int countMovies = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введи размер поля: ");
        size = sc.nextInt();
        SIZE = size - 1;
        gameField = new GameField(size);
        map = gameField.getField();

        gameField.printField();

        while (!checkToWin()) {
            System.out.println("Делай свой ход");
            int number = sc.nextInt();

            // Чит на победу
            if (number == 1337) {
                cheatToWin();
                break;
            }
            getPositionNumber(number);
            makeMove(y, x);
            countMovies++;
        }
        System.out.println("Победа!");
        System.out.println("Ты справился за " + countMovies + " ходов.");

        // После окончания игры запишем статистику в базу
        DBHandler db = new DBHandler();
        db.connect();
        db.insertStat(countMovies);
        db.disconnect();
    }

    public static void makeMove(int y, int x) {
        // Сначала добавим ограничения по углам
        if (y == x && y == 0) {
            checkDown();
            checkRight();
        } else if (y == SIZE && x == 0) {
            checkUp();
            checkRight();
        } else if (y == 0 && x == SIZE) {
            checkLeft();
            checkDown();
        } else if (y == SIZE && x == SIZE) {
            checkLeft();
            checkUp();
            // Затем проверим крайнии линии
        } else if (y == 0) {
            checkRight();
            checkLeft();
            checkDown();
        } else if (y == SIZE) {
            checkLeft();
            checkRight();
            checkUp();
        } else if (x == 0){
            checkUp();
            checkDown();
            checkRight();
        } else if (x == SIZE) {
            checkUp();
            checkDown();
            checkLeft();
        } else {
            // Во всеъ остальных случаях перебираем все варианты
            checkUp();
            checkDown();
            checkLeft();
            checkRight();
        }

        gameField.printField();
    }

    private static void swap(int y1, int x1, int y2, int x2) {
        String a;
        a = map[y1][x1];
        map[y1][x1] = map[y2][x2];
        map[y2][x2] = a;
    }

    private static void checkUp() {
         if (map[y-1][x].equals("-")) swap(y, x, y-1, x);
    }

    private static void checkDown() {
        if (map[y+1][x].equals("-")) swap(y, x, y + 1, x);
    }

    private static void checkLeft() {
        if (map[y][x-1].equals("-")) swap(y, x, y, x - 1);
    }

    private static void checkRight() {
        if (map[y][x+1].equals("-")) swap(y, x, y, x + 1);
    }

    private static boolean checkToWin() {
        int k = 1;
        int cnt = 0;
        int mapLength = map.length;
        for (int i = 0; i <= SIZE; i++) {
            for (int j = 0; j <= SIZE; j++) {
                if (map[i][j].equals(String.valueOf(k))) {
                    k++;
                    cnt++;
                } else return k == mapLength * mapLength && cnt == ((mapLength * mapLength) - 1) && map[i][j].equals("-");
            }
        }
        return true;
    }

    private static void getPositionNumber(int num) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j].equals(String.valueOf(num))) {
                    y = i;
                    x = j;
                    return;
                }
            }
        }
    }

    private static void cheatToWin () {
        int k = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                k++;
                if (k == map.length * map.length) map[i][j] = "-";
                else map[i][j] = String.valueOf(k);
            }
        }
    }

}
