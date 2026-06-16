import java.util.InputMismatchException;
        import java.util.Scanner;

public class Practic9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ПРОГРАММА БЕЗОПАСНОГО ДЕЛЕНИЯ");
        System.out.println("Поддерживает целые и десятичные числа");
        System.out.println();

        double num1 = 0, num2 = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Введите первое число (делимое): ");
                num1 = scanner.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Введено не число! Пожалуйста, введите числовое значение.");
                scanner.nextLine();
            }
        }

        validInput = false;

        while (!validInput) {
            try {
                System.out.print("Введите второе число (делитель): ");
                num2 = scanner.nextDouble();

                if (num2 == 0) {
                    throw new ArithmeticException("Деление на ноль невозможно!");
                }

                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Введено не число! Пожалуйста, введите числовое значение.");
                scanner.nextLine();
            } catch (ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Пожалуйста, введите число, отличное от нуля.");
                scanner.nextLine();
            }
        }

        try {

            System.out.println("\nРЕЗУЛЬТАТ ДЕЛЕНИЯ\n");

            double result = num1 / num2;

            System.out.printf("%.2f / %.2f = %.2f%n", num1, num2, result);

            if (num1 % num2 == 0) {
                System.out.println("Деление выполнено без остатка.");
            } else {
                System.out.println("Деление выполнено с остатком.");
            }

            System.out.println("Форматированный результат: " + String.format("%.4f", result));

        } catch (ArithmeticException e) {
            System.out.println("Арифметическая ошибка: " + e.getMessage());
        } finally {
            System.out.println();
            System.out.println("ЗАВЕРШАЮЩЕЕ СООБЩЕНИЕ");
            System.out.println("Операция деления завершена.");
            System.out.println("Проверьте результат и при необходимости повторите.");

        }

        System.out.println("ДОПОЛНИТЕЛЬНАЯ ИНФОРМАЦИЯ");

        try {
            int intNum1 = (int) num1;
            int intNum2 = (int) num2;

            if (intNum2 != 0) {
                System.out.println("Целочисленное деление: " + intNum1 + " / " + intNum2 + " = " + (intNum1 / intNum2));
                System.out.println("Остаток от деления: " + (intNum1 % intNum2));
            }
        } catch (ArithmeticException e) {
            System.out.println("Невозможно выполнить целочисленное деление.");
        }

        System.out.println();
        System.out.println("ПРОГРАММА ЗАВЕРШЕНА!");

        scanner.close();
    }
}