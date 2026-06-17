import java.util.*;
import java.util.stream.Collectors;

public class Practic11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ОБРАБОТКА СПИСКА ЧИСЕЛ С ПОМОЩЬЮ STREAM API (РАСШИРЕННАЯ ВЕРСИЯ)");
        System.out.println();

        System.out.println("ВВОД ДАННЫХ");
        System.out.println("Вы можете ввести числа двумя способами:");
        System.out.println("1 - Ввести числа вручную через запятую");
        System.out.println("2 - Использовать стандартный набор чисел");
        System.out.print("Ваш выбор (1/2): ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Integer> numbers = new ArrayList<>();

        if (choice == 1) {
            System.out.println();
            System.out.print("Введите числа через запятую (например: 1, -2, 3, -4, 5): ");
            String input = scanner.nextLine();

            String[] parts = input.split(",");
            for (String part : parts) {
                try {
                    int num = Integer.parseInt(part.trim());
                    numbers.add(num);
                } catch (NumberFormatException e) {
                    System.out.println("Пропущен неверный формат: " + part.trim());
                }
            }
        } else {
            numbers = Arrays.asList(1, -2, 3, -4, 5, 6, -7, 8, -9, 10);
            System.out.println();
            System.out.println("Используется стандартный набор чисел");
        }

        System.out.println();
        System.out.println("ИСХОДНЫЙ СПИСОК");
        System.out.println("Числа: " + numbers);
        System.out.println("Количество элементов: " + numbers.size());
        System.out.println();

        System.out.println("ОСНОВНАЯ ОПЕРАЦИЯ");
        System.out.println("Фильтрация положительных чисел и возведение в квадрат");
        System.out.println();

        List<Integer> positiveSquares = numbers.stream()
                .filter(n -> n > 0)
                .map(n -> n * n)
                .toList();

        System.out.println("РЕЗУЛЬТИРУЮЩИЙ СПИСОК");
        System.out.println("Квадраты положительных чисел: " + positiveSquares);
        System.out.println("Количество элементов: " + positiveSquares.size());
        System.out.println();

        System.out.println("СРАВНЕНИЕ");
        System.out.println("Исходный:       " + numbers);
        System.out.println("Результат:      " + positiveSquares);
        System.out.println();

        System.out.println("ДОПОЛНИТЕЛЬНЫЕ ОПЕРАЦИИ STREAM API");

        System.out.println("\n1. Фильтрация отрицательных чисел:");
        List<Integer> negatives = numbers.stream()
                .filter(n -> n < 0)
                .toList();
        System.out.println("   Отрицательные числа: " + negatives);

        System.out.println("\n2. Удвоение всех чисел:");
        List<Integer> doubled = numbers.stream()
                .map(n -> n * 2)
                .toList();
        System.out.println("   Удвоенные числа: " + doubled);

        System.out.println("\n3. Сортировка по возрастанию:");
        List<Integer> sorted = numbers.stream()
                .sorted()
                .toList();
        System.out.println("   Отсортированный список: " + sorted);

        System.out.println("\n4. Сортировка по убыванию:");
        List<Integer> sortedDesc = numbers.stream()
                .sorted(Collections.reverseOrder())
                .toList();
        System.out.println("   Отсортированный (убывание): " + sortedDesc);

        System.out.println("\n5. Преобразование в строку:");
        String resultString = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("   Строковое представление: " + resultString);

        System.out.println("\n6. Проверка условий:");
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        System.out.println("   Все числа положительные? " + allPositive);

        boolean anyPositive = numbers.stream().anyMatch(n -> n > 0);
        System.out.println("   Есть хотя бы одно положительное? " + anyPositive);

        boolean noneNegative = numbers.stream().noneMatch(n -> n < 0);
        System.out.println("   Нет отрицательных чисел? " + noneNegative);

        System.out.println("\n7. Агрегатные операции:");
        if (!numbers.isEmpty()) {
            Integer max = numbers.stream().max(Integer::compareTo).get();
            System.out.println("   Максимальное число: " + max);

            Integer min = numbers.stream().min(Integer::compareTo).get();
            System.out.println("   Минимальное число: " + min);

            Integer first = numbers.stream().findFirst().get();
            System.out.println("   Первый элемент: " + first);

            Integer any = numbers.stream().findAny().get();
            System.out.println("   Любой элемент: " + any);
        } else {
            System.out.println("   Список пуст, агрегатные операции недоступны");
        }

        System.out.println("\n8. Статистика:");
        if (!numbers.isEmpty()) {
            IntSummaryStatistics stats = numbers.stream()
                    .mapToInt(Integer::intValue)
                    .summaryStatistics();
            System.out.println("   Количество: " + stats.getCount());
            System.out.println("   Сумма: " + stats.getSum());
            System.out.println("   Минимум: " + stats.getMin());
            System.out.println("   Максимум: " + stats.getMax());
            System.out.println("   Среднее: " + String.format("%.2f", stats.getAverage()));
        } else {
            System.out.println("   Список пуст, статистика недоступна");
        }

        System.out.println("\n9. Комбинированные операции:");
        if (!numbers.isEmpty()) {
            int sumOfSquares = numbers.stream()
                    .filter(n -> n > 0)
                    .map(n -> n * n)
                    .mapToInt(Integer::intValue)
                    .sum();
            System.out.println("   Сумма квадратов положительных чисел: " + sumOfSquares);

            List<Integer> processed = numbers.stream()
                    .filter(n -> n != 0)
                    .map(Math::abs)
                    .sorted()
                    .toList();
            System.out.println("   Модули чисел (без нулей, отсортировано): " + processed);

            System.out.println("\n10. Использование parallelStream:");
            List<Integer> parallelResult = numbers.parallelStream()
                    .filter(n -> n > 0)
                    .map(n -> n * n)
                    .toList();
            System.out.println("   Параллельная обработка: " + parallelResult);
        } else {
            System.out.println("   Список пуст, операции недоступны");
        }

        System.out.println();
        System.out.println("ХОТИТЕ ВЫПОЛНИТЬ ДОПОЛНИТЕЛЬНЫЕ ОПЕРАЦИИ?");
        System.out.print("Введите операцию (filter/map/sort/exit): ");
        String operation = scanner.nextLine();

        while (!operation.equalsIgnoreCase("exit")) {
            switch (operation.toLowerCase()) {
                case "filter" -> {
                    System.out.print("Введите условие для фильтрации (например: n > 0): ");
                    String condition = scanner.nextLine();
                    try {
                        // Простая обработка для демонстрации
                        List<Integer> filtered = numbers.stream()
                                .filter(n -> evaluateCondition(n, condition))
                                .toList();
                        System.out.println("Результат фильтрации: " + filtered);
                    } catch (Exception e) {
                        System.out.println("Ошибка в условии. Используйте формат: n > 0, n < 5, n >= 3 и т.д.");
                    }
                }
                case "map" -> {
                    System.out.print("Введите операцию для преобразования (например: n * 2): ");
                    String mapOperation = scanner.nextLine();
                    try {
                        List<Integer> mapped = numbers.stream()
                                .map(n -> evaluateMap(n, mapOperation))
                                .toList();
                        System.out.println("Результат преобразования: " + mapped);
                    } catch (Exception e) {
                        System.out.println("Ошибка в операции. Используйте формат: n * 2, n + 5, n - 3 и т.д.");
                    }
                }
                case "sort" -> {
                    System.out.println("Результат сортировки по возрастанию: " +
                            numbers.stream().sorted().toList());
                    System.out.println("Результат сортировки по убыванию: " +
                            numbers.stream().sorted(Collections.reverseOrder()).toList());
                }
                default -> System.out.println("Неизвестная операция. Доступно: filter, map, sort, exit");
            }

            System.out.println();
            System.out.print("Введите операцию (filter/map/sort/exit): ");
            operation = scanner.nextLine();
        }

        System.out.println();
        System.out.println("ПРОГРАММА ЗАВЕРШЕНА!");

        scanner.close();
    }

    private static boolean evaluateCondition(int n, String condition) {
        condition = condition.replaceAll("\\s+", "");
        if (condition.contains(">")) {
            String[] parts = condition.split(">");
            int threshold = Integer.parseInt(parts[1]);
            return n > threshold;
        } else if (condition.contains("<")) {
            String[] parts = condition.split("<");
            int threshold = Integer.parseInt(parts[1]);
            return n < threshold;
        } else if (condition.contains(">=")) {
            String[] parts = condition.split(">=");
            int threshold = Integer.parseInt(parts[1]);
            return n >= threshold;
        } else if (condition.contains("<=")) {
            String[] parts = condition.split("<=");
            int threshold = Integer.parseInt(parts[1]);
            return n <= threshold;
        } else if (condition.contains("==")) {
            String[] parts = condition.split("==");
            int threshold = Integer.parseInt(parts[1]);
            return n == threshold;
        } else if (condition.contains("!=")) {
            String[] parts = condition.split("!=");
            int threshold = Integer.parseInt(parts[1]);
            return n != threshold;
        }
        return false;
    }

    private static int evaluateMap(int n, String operation) {
        operation = operation.replaceAll("\\s+", "");
        if (operation.contains("*")) {
            String[] parts = operation.split("\\*");
            int multiplier = Integer.parseInt(parts[1]);
            return n * multiplier;
        } else if (operation.contains("+")) {
            String[] parts = operation.split("\\+");
            int add = Integer.parseInt(parts[1]);
            return n + add;
        } else if (operation.contains("-")) {
            String[] parts = operation.split("-");
            if (parts.length == 2) {
                int subtract = Integer.parseInt(parts[1]);
                return n - subtract;
            }
        } else if (operation.contains("/")) {
            String[] parts = operation.split("/");
            int divisor = Integer.parseInt(parts[1]);
            if (divisor != 0) {
                return n / divisor;
            }
        }
        return n;
    }
}