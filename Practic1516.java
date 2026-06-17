
import java.util.Arrays;
        import java.util.Random;
        import java.util.Scanner;

public class Practic1516 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("РАБОТА С МАССИВАМИ");
        System.out.println();

        boolean running = true;

        while (running) {
            System.out.println("ГЛАВНОЕ МЕНЮ");
            System.out.println("1. Одномерный массив (поиск max/min)");
            System.out.println("2. Двумерный массив (диагональная матрица)");
            System.out.println("3. Ступенчатый массив (треугольник Паскаля)");
            System.out.println("4. Все задачи сразу");
            System.out.println("5. Выйти");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> task1();
                case 2 -> task2();
                case 3 -> task3();
                case 4 -> {
                    task1();
                    System.out.println();
                    task2();
                    System.out.println();
                    task3();
                }
                case 5 -> {
                    running = false;
                    System.out.println("Выход из программы...");
                }
                default -> System.out.println("Неверный выбор!");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void task1() {
        System.out.println("ЗАДАЧА 1: ОДНОМЕРНЫЙ МАССИВ");
        System.out.println("Поиск максимального и минимального элементов");
        System.out.println();

        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        scanner.nextLine();

        int[] array = new int[size];

        System.out.print("Хотите ввести элементы вручную? (yes/no): ");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("yes")) {
            for (int i = 0; i < size; i++) {
                System.out.print("Элемент " + (i + 1) + ": ");
                array[i] = scanner.nextInt();
            }
            scanner.nextLine();
        } else {
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(201) - 100;
            }
        }

        System.out.println();
        System.out.println("Массив: " + Arrays.toString(array));

        int max = array[0];
        int min = array[0];
        int maxIndex = 0;
        int minIndex = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
            if (array[i] < min) {
                min = array[i];
                minIndex = i;
            }
        }

        System.out.println("Максимальный элемент: " + max + " (индекс " + maxIndex + ")");
        System.out.println("Минимальный элемент: " + min + " (индекс " + minIndex + ")");

        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        double average = (double) sum / array.length;
        System.out.println("Сумма элементов: " + sum);
        System.out.println("Среднее арифметическое: " + String.format("%.2f", average));

        System.out.println();
        System.out.println("Все вхождения максимального элемента (индексы):");
        for (int i = 0; i < array.length; i++) {
            if (array[i] == max) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        System.out.println("Все вхождения минимального элемента (индексы):");
        for (int i = 0; i < array.length; i++) {
            if (array[i] == min) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        System.out.println("ЗАДАЧА 1 ЗАВЕРШЕНА!");
    }

    private static void task2() {
        System.out.println("ЗАДАЧА 2: ДВУМЕРНЫЙ МАССИВ");
        System.out.println("Диагональная матрица (единицы на главной диагонали)");
        System.out.println();

        System.out.print("Введите размер квадратной матрицы: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }

        System.out.println();
        System.out.println("Единичная матрица (" + n + "x" + n + "):");
        printMatrix(matrix);

        System.out.println();
        System.out.print("Хотите заполнить побочную диагональ? (yes/no): ");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("yes")) {
            for (int i = 0; i < n; i++) {
                matrix[i][n - 1 - i] = 2;
            }
            System.out.println("Матрица с заполненной побочной диагональю (2):");
            printMatrix(matrix);
        }

        System.out.println();
        System.out.print("Хотите заполнить матрицу случайными числами? (yes/no): ");
        answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("yes")) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = random.nextInt(21) - 10;
                }
            }
            System.out.println("Матрица со случайными числами:");
            printMatrix(matrix);

            int max = matrix[0][0];
            int min = matrix[0][0];
            int maxRow = 0, maxCol = 0, minRow = 0, minCol = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] > max) {
                        max = matrix[i][j];
                        maxRow = i;
                        maxCol = j;
                    }
                    if (matrix[i][j] < min) {
                        min = matrix[i][j];
                        minRow = i;
                        minCol = j;
                    }
                }
            }

            System.out.println("Максимальный элемент: " + max + " (строка " + maxRow + ", колонка " + maxCol + ")");
            System.out.println("Минимальный элемент: " + min + " (строка " + minRow + ", колонка " + minCol + ")");
        }

        int sumDiagonal = 0;
        for (int i = 0; i < n; i++) {
            sumDiagonal += matrix[i][i];
        }
        System.out.println("Сумма элементов на главной диагонали: " + sumDiagonal);

        System.out.println("ЗАДАЧА 2 ЗАВЕРШЕНА!");
    }

    private static void task3() {
        System.out.println("ЗАДАЧА 3: СТУПЕНЧАТЫЙ МАССИВ");
        System.out.println("Треугольник Паскаля");
        System.out.println();

        System.out.print("Введите количество строк треугольника Паскаля: ");
        int rows = scanner.nextInt();
        scanner.nextLine();

        int[][] pascal = new int[rows][];

        for (int i = 0; i < rows; i++) {
            pascal[i] = new int[i + 1];
            pascal[i][0] = 1;
            pascal[i][i] = 1;

            for (int j = 1; j < i; j++) {
                pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
            }
        }

        System.out.println();
        System.out.println("Треугольник Паскаля (" + rows + " строк):");
        printPascalTriangle(pascal);

        System.out.println();
        System.out.println("Сумма чисел в каждой строке:");
        for (int i = 0; i < rows; i++) {
            int sum = 0;
            for (int j = 0; j < pascal[i].length; j++) {
                sum += pascal[i][j];
            }
            System.out.println("Строка " + (i + 1) + ": " + sum + " (2^" + i + " = " + (int) Math.pow(2, i) + ")");
        }

        System.out.println();
        System.out.print("Хотите увидеть треугольник в виде таблицы? (yes/no): ");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("yes")) {
            System.out.println("Треугольник Паскаля (таблица):");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < pascal[i].length; j++) {
                    System.out.printf("%4d", pascal[i][j]);
                }
                System.out.println();
            }
        }

        System.out.println();
        System.out.print("Хотите увидеть бинарный треугольник Паскаля? (yes/no): ");
        answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("yes")) {
            System.out.println("Бинарный треугольник Паскаля (четные/нечетные):");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < pascal[i].length; j++) {
                    if (pascal[i][j] % 2 == 0) {
                        System.out.print("  ");
                    } else {
                        System.out.print("1 ");
                    }
                }
                System.out.println();
            }
        }

        System.out.println("ЗАДАЧА 3 ЗАВЕРШЕНА!");
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%4d", value);
            }
            System.out.println();
        }
    }

    private static void printPascalTriangle(int[][] pascal) {
        for (int i = 0; i < pascal.length; i++) {
            for (int k = 0; k < pascal.length - i - 1; k++) {
                System.out.print("  ");
            }
            for (int j = 0; j < pascal[i].length; j++) {
                System.out.printf("%4d", pascal[i][j]);
            }
            System.out.println();
        }
    }
}