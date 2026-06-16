import java.util.Scanner;

public class Practic3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n Вывод чисел от 1 до 10\n");

        System.out.print("Числа: ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i);
            if (i < 10) {
                System.out.print(", ");
            }
        }

        System.out.println("\n\nЧетные числа: ");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }

        System.out.println("\nНечетные числа: ");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 != 0) {
                System.out.print(i + " ");
            }
        }

        System.out.println("\nНажмите Enter для продолжения...");
        scanner.nextLine();

        System.out.println(" Сумма чисел от 1 до 100\n");

        int sum = 0;
        int counter = 1;

        while (counter <= 100) {
            sum += counter;
            counter++;
        }

        System.out.println("Сумма чисел от 1 до 100 = " + sum);

        int formulaSum = 100 * (100 + 1) / 2;
        System.out.println("Проверка по формуле: " + formulaSum);
        System.out.println("Результат верен: " + (sum == formulaSum));

        int evenSum = 0;
        int oddSum = 0;
        int num = 1;

        while (num <= 100) {
            if (num % 2 == 0) {
                evenSum += num;
            } else {
                oddSum += num;
            }
            num++;
        }

        System.out.println("\nСумма четных чисел (1-100): " + evenSum);
        System.out.println("Сумма нечетных чисел (1-100): " + oddSum);
        System.out.println("Сумма четных + нечетных = " + (evenSum + oddSum));

        System.out.println("\nНажмите Enter для продолжения...");
        scanner.nextLine();

        System.out.println(" Ввод пароля");
        //System.out.println("Пароль по умолчанию: java123");
        System.out.println("(Для выхода введите 'exit')");

        String password;
        int attempts = 0;
        final int MAX_ATTEMPTS = 5;

        do {
            System.out.print("\nВведите пароль: ");
            password = scanner.nextLine();
            attempts++;

            if (password.equalsIgnoreCase("exit")) {
                System.out.println("Выход из программы...");
                scanner.close();
                return;
            }

            if (!password.equals("java123")) {
                System.out.println("✗ Неверный пароль! Осталось попыток: " + (MAX_ATTEMPTS - attempts));

                if (attempts == 3) {
                    System.out.println("  Подсказка: пароль состоит из слова 'java' и трех цифр");
                }
                if (attempts == MAX_ATTEMPTS - 1) {
                    System.out.println("  Предупреждение! Это ваша последняя попытка!");
                }
            }

        } while (!password.equals("java123") && attempts < MAX_ATTEMPTS);

        if (password.equals("java123")) {
            System.out.println("\n✓ Доступ разрешён!");
            System.out.println("  Количество попыток: " + attempts);

            if (attempts == 1) {
                System.out.println("  Отлично! Вы запомнили пароль с первого раза!");
            } else if (attempts <= 3) {
                System.out.println("  Хороший результат!");
            } else {
                System.out.println("  Пароль был сложным для запоминания?");
            }
        } else {
            System.out.println("\n✗ Доступ запрещён! Превышено количество попыток.");
            System.out.println("  Попробуйте позже или обратитесь к администратору.");
        }

        System.out.println("\n【Таблица умножения 5x5】\n");

        for (int i = 1; i <= 5; i++) {
            System.out.printf("%4d", i);
        }

        for (int i = 1; i <= 5; i++) {
            System.out.printf("%2d |", i);
            for (int j = 1; j <= 5; j++) {
                System.out.printf("%4d", i * j);
            }
            System.out.println();
        }

        System.out.println("\n【Поиск первого числа, кратного 7, больше 50】");

        int number = 1;
        while (true) {
            if (number > 50 && number % 7 == 0) {
                System.out.println("Первое число > 50, кратное 7: " + number);
                break;
            }
            number++;
        }

        System.out.println("\n【Числа от 1 до 20, кратные 3】");

        System.out.print("Числа, кратные 3: ");
        for (int i = 1; i <= 20; i++) {
            if (i % 3 != 0) {
                continue;
            }
            System.out.print(i + " ");
        }

        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("   ПРОГРАММА ЗАВЕРШЕНА УСПЕШНО!");
        System.out.println("═══════════════════════════════════════════");

        scanner.close();
    }
}