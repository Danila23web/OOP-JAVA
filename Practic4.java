import java.util.Scanner;

public class Practic4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Введите количество чисел в массиве (положительное число): ");
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                if (size > 0) {
                    validInput = true;
                } else {
                    System.out.println("Ошибка: размер должен быть больше 0!");
                }
            } else {
                System.out.println("Ошибка: введите целое число!");
                scanner.next();
            }
        }

        int[] numbers = new int[size];

        System.out.println("\nВведите " + size + " целых чисел:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("  numbers[" + i + "] = ");
            while (!scanner.hasNextInt()) {
                System.out.print("  Ошибка! Введите целое число: ");
                scanner.next();
            }
            numbers[i] = scanner.nextInt();
        }

        System.out.println("\n=== АНАЛИЗ МАССИВА ===");
        System.out.println("Размер массива: " + numbers.length);

        System.out.println("\nЭлементы массива:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("  numbers[" + i + "] = " + numbers[i]);
        }

        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("\nСумма элементов: " + sum);

        double average = (double) sum / numbers.length;
        System.out.println("Среднее арифметическое: " + String.format("%.2f", average));

        if (numbers.length > 0) {
            int max = numbers[0];
            int maxIndex = 0;
            for (int i = 1; i < numbers.length; i++) {
                if (numbers[i] > max) {
                    max = numbers[i];
                    maxIndex = i;
                }
            }
            System.out.println("Максимальное значение: " + max + " (индекс " + maxIndex + ")");
        }

        boolean found = false;
        int position = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 42) {
                found = true;
                position = i;
                break;
            }
        }

        System.out.println("\n=== Search 42 ===");
        if (found) {
            System.out.println("✓ Число 42 найдено!");
            System.out.println("  Позиция в массиве: индекс " + position);
        } else {
            System.out.println("✗ Число 42 не найдено.");
        }

        scanner.close();
    }
}