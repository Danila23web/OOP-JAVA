/**
 * Расширенная программа для работы с матрицами
 * Включает различные операции: сумма, среднее, максимум, минимум, диагонали
 */
public class practika18 {
    public static void main(String[] args) {
        // Инициализация матрицы
        int[][] matrix = {
                {5, 8, 3},
                {9, 2, 7},
                {4, 6, 1}
        };

        System.out.println("=== РАСШИРЕННАЯ ОБРАБОТКА МАТРИЦЫ ===\n");
        System.out.println("Исходная матрица:");
        printMatrix(matrix);
        System.out.println();

        // Базовые операции
        calculateSum(matrix);
        calculateAverage(matrix);
        findMaxMin(matrix);
        calculateDiagonalSum(matrix);
        transposeMatrix(matrix);

        // Дополнительные операции
        System.out.println("\n--- ДОПОЛНИТЕЛЬНЫЕ ОПЕРАЦИИ ---");
        multiplyMatrixByScalar(matrix, 2);
        System.out.println("Матрица после умножения на скаляр 2:");
        printMatrix(matrix);

        System.out.println("Является ли матрица симметричной? " +
                (isSymmetric(matrix) ? "ДА" : "НЕТ"));

        // Работа с подматрицами
        calculateRowSums(matrix);
        calculateColumnSums(matrix);
    }

    /**
     * Вычисление суммы всех элементов матрицы
     */
    private static void calculateSum(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
        }
        System.out.println("1. Сумма всех элементов: " + sum);
    }

    /**
     * Вычисление среднего арифметического
     */
    private static void calculateAverage(int[][] matrix) {
        int sum = 0;
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
                count++;
            }
        }
        double average = (double) sum / count;
        System.out.printf("2. Среднее арифметическое: %.2f\n", average);
    }

    /**
     * Поиск максимального и минимального элемента
     */
    private static void findMaxMin(int[][] matrix) {
        if (matrix.length == 0) return;

        int max = matrix[0][0];
        int min = matrix[0][0];
        int maxRow = 0, maxCol = 0;
        int minRow = 0, minCol = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
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

        System.out.println("3. Максимальный элемент: " + max +
                " (позиция [" + maxRow + "][" + maxCol + "])");
        System.out.println("   Минимальный элемент: " + min +
                " (позиция [" + minRow + "][" + minCol + "])");
    }

    /**
     * Вычисление суммы элементов главной и побочной диагоналей
     */
    private static void calculateDiagonalSum(int[][] matrix) {
        int mainDiagonal = 0;
        int secondaryDiagonal = 0;
        int size = matrix.length;

        for (int i = 0; i < size; i++) {
            mainDiagonal += matrix[i][i];
            secondaryDiagonal += matrix[i][size - 1 - i];
        }

        System.out.println("4. Сумма главной диагонали: " + mainDiagonal);
        System.out.println("   Сумма побочной диагонали: " + secondaryDiagonal);
    }

    /**
     * Транспонирование матрицы
     */
    private static void transposeMatrix(int[][] matrix) {
        System.out.println("\n--- ТРАНСПОНИРОВАНИЕ МАТРИЦЫ ---");
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }

        System.out.println("Транспонированная матрица:");
        printMatrix(transposed);
    }

    /**
     * Умножение матрицы на скаляр
     */
    private static void multiplyMatrixByScalar(int[][] matrix, int scalar) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] *= scalar;
            }
        }
    }

    /**
     * Проверка симметричности матрицы
     */
    private static boolean isSymmetric(int[][] matrix) {
        if (matrix.length != matrix[0].length) return false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Вычисление суммы элементов каждой строки
     */
    private static void calculateRowSums(int[][] matrix) {
        System.out.println("\n5. Сумма элементов по строкам:");
        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                rowSum += matrix[i][j];
            }
            System.out.println("   Строка " + (i + 1) + ": " + rowSum);
        }
    }

    /**
     * Вычисление суммы элементов каждого столбца
     */
    private static void calculateColumnSums(int[][] matrix) {
        System.out.println("\n6. Сумма элементов по столбцам:");
        for (int j = 0; j < matrix[0].length; j++) {
            int colSum = 0;
            for (int i = 0; i < matrix.length; i++) {
                colSum += matrix[i][j];
            }
            System.out.println("   Столбец " + (j + 1) + ": " + colSum);
        }
    }

    /**
     * Красивый вывод матрицы
     */
    private static void printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            System.out.println("Пустая матрица");
            return;
        }

        // Определяем ширину столбцов для красивого форматирования
        int cols = matrix[0].length;
        String border = "┌";
        String separator = "├";
        String bottom = "└";

        for (int j = 0; j < cols; j++) {
            border += "─────";
            separator += "─────";
            bottom += "─────";
            if (j < cols - 1) {
                border += "┬";
                separator += "┼";
                bottom += "┴";
            }
        }
        border += "┐";
        separator += "┤";
        bottom += "┘";

        System.out.println(border);
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("│");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf(" %2d │", matrix[i][j]);
            }
            System.out.println();
            if (i < matrix.length - 1) {
                System.out.println(separator);
            }
        }
        System.out.println(bottom);
    }
}