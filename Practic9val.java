import java.util.Scanner;

public class Practic9val {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ПРОГРАММА БЕЗОПАСНОГО ДЕЛЕНИЯ (ДЕМО-ВЕРСИЯ)");
        System.out.println("Для выхода введите 'стоп' или 'exit'");
        System.out.println();
        System.out.println("Доступные команды для демонстрации ошибок:");
        System.out.println("  'null' - вызвать NullPointerException");
        System.out.println("  'array' - вызвать IndexOutOfBoundsException");
        System.out.println("  'big' - вызвать ошибку больших чисел");
        System.out.println("  'zero' - деление на ноль");
        System.out.println();

        int successCount = 0;
        int errorCount = 0;

        while (true) {
            try {
                System.out.println("\nВВОД ДАННЫХ\n");

                System.out.print("Введите первое число (делимое): ");
                String input1 = scanner.nextLine();

                if (input1.equalsIgnoreCase("стоп") || input1.equalsIgnoreCase("exit")) {
                    System.out.println("Выход из программы...");
                    break;
                }

                if (input1.equalsIgnoreCase("null")) {
                    throw new NullPointerException("Симуляция NullPointerException");
                }

                if (input1.equalsIgnoreCase("array")) {
                    throw new IndexOutOfBoundsException("Симуляция IndexOutOfBoundsException: индекс 10 вне границ массива размером 5");
                }

                int num1 = Integer.parseInt(input1);

                System.out.print("Введите второе число (делитель): ");
                String input2 = scanner.nextLine();

                if (input2.equalsIgnoreCase("стоп") || input2.equalsIgnoreCase("exit")) {
                    System.out.println("Выход из программы...");
                    break;
                }

                if (input2.equalsIgnoreCase("null")) {
                    throw new NullPointerException("Симуляция NullPointerException");
                }

                if (input2.equalsIgnoreCase("array")) {
                    throw new IndexOutOfBoundsException("Симуляция IndexOutOfBoundsException");
                }

                int num2 = Integer.parseInt(input2);

                System.out.println("\nВЫПОЛНЕНИЕ ДЕЛЕНИЯ\n");


                if (num2 == 0) {
                    throw new ArithmeticException("Деление на ноль невозможно!");
                }

                if (num1 == 0) {
                    throw new IllegalArgumentException("Деление нуля на число - проверьте корректность данных");
                }

                if (num1 > 1000 || num2 > 1000) {
                    throw new IllegalArgumentException("Число превышает допустимый лимит (1000)");
                }

                if (num1 < -1000 || num2 < -1000) {
                    throw new IllegalArgumentException("Число меньше минимального значения (-1000)");
                }

                if (num1 == 13 || num2 == 13) {
                    throw new IllegalArgumentException("Число 13 считается несчастливым!");
                }

                double result = (double) num1 / num2;

                System.out.println(num1 + " / " + num2 + " = " + result);
                System.out.println("Целая часть: " + (num1 / num2));
                System.out.println("Остаток: " + (num1 % num2));

                successCount++;

            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("⚠️ ОШИБКА: Неверный формат числа!");
                System.out.println("Введите целое число.");
                System.out.println("Тип: NumberFormatException");
                System.out.println("Сообщение: " + e.getMessage());
                errorCount++;

            } catch (ArithmeticException e) {
                System.out.println();
                System.out.println("⚠️ ОШИБКА: " + e.getMessage());
                System.out.println("Тип: ArithmeticException");
                errorCount++;

            } catch (IllegalArgumentException e) {
                System.out.println();
                System.out.println("⚠️ ОШИБКА: " + e.getMessage());
                System.out.println("Тип: IllegalArgumentException");
                errorCount++;

            } catch (NullPointerException e) {
                System.out.println();
                System.out.println("⚠️ ОШИБКА: Попытка обратиться к null!");
                System.out.println("Тип: NullPointerException");
                System.out.println("Сообщение: " + e.getMessage());
                errorCount++;

            } catch (IndexOutOfBoundsException e) {
                System.out.println();
                System.out.println("⚠️ ОШИБКА: Выход за границы массива!");
                System.out.println("Тип: IndexOutOfBoundsException");
                System.out.println("Сообщение: " + e.getMessage());
                errorCount++;

            } catch (Exception e) {
                System.out.println();
                System.out.println("⚠️ НЕИЗВЕСТНАЯ ОШИБКА: " + e.getMessage());
                System.out.println("Тип: " + e.getClass().getSimpleName());
                errorCount++;

            } finally {
                System.out.println();
                System.out.println("📊 СТАТИСТИКА\n");
                System.out.println("Успешно: " + successCount);
                System.out.println("Ошибок: " + errorCount);
                System.out.println("Всего: " + (successCount + errorCount));
            }

            System.out.println("\n");
            System.out.print("Продолжить? (да/exit): ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("нет") || answer.equalsIgnoreCase("стоп") || answer.equalsIgnoreCase("exit")) {
                System.out.println("Выход из программы...");
                break;
            }
        }

        System.out.println("ИТОГОВАЯ СТАТИСТИКА\n");
        System.out.println("Успешных операций: " + successCount);
        System.out.println("Ошибок: " + errorCount);
        System.out.println("Всего: " + (successCount + errorCount));

        if (successCount + errorCount > 0) {
            System.out.println("Процент успеха: " + String.format("%.2f%%",
                    (successCount * 100.0 / (successCount + errorCount))));
        }

        System.out.println("\nПРОГРАММА ЗАВЕРШЕНА!");

        scanner.close();
    }
}