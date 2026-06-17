package view;

import java.util.List;
import java.util.Scanner;

public class CalculatorView {
    private Scanner scanner;

    public CalculatorView() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("=".repeat(50));
        System.out.println("         КАЛЬКУЛЯТОР (MVC Architecture)");
        System.out.println("=".repeat(50));
        System.out.println("Поддерживаемые операции:");
        System.out.println("  +  - сложение");
        System.out.println("  -  - вычитание");
        System.out.println("  *  - умножение");
        System.out.println("  /  - деление");
        System.out.println("  ** или ^ - возведение в степень");
        System.out.println("  // - деление без остатка (целочисленное)");
        System.out.println("  %  - остаток от деления");
        System.out.println("  () - скобки для группировки");
        System.out.println("=".repeat(50));
        System.out.println();
    }

    public void showMenu() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("МЕНЮ:");
        System.out.println("  1. Выполнить вычисление");
        System.out.println("  2. Показать историю вычислений");
        System.out.println("  3. Сохранить историю в файл");
        System.out.println("  4. Сохранить выбранные записи в файл");
        System.out.println("  5. Показать путь к файлу истории");
        System.out.println("  6. Выход");
        System.out.print("Выберите действие (1-6): ");
    }

    public String getExpressionInput() {
        System.out.print("\nВведите математическое выражение: ");
        return scanner.nextLine();
    }

    public void showResult(double result, String expression) {
        System.out.println("\n✅ Результат: " + expression + " = " + result);
    }

    public void showError(String errorMessage) {
        System.out.println("\n❌ Ошибка: " + errorMessage);
    }

    public void showHistory(List<String> history) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ИСТОРИЯ ВЫЧИСЛЕНИЙ:");
        System.out.println("=".repeat(50));

        if (history.isEmpty()) {
            System.out.println("  История пуста");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.printf("  %3d. %s%n", i + 1, history.get(i));
            }
        }
        System.out.println("=".repeat(50));
    }

    public void showMessage(String message) {
        System.out.println("\nℹ️ " + message);
    }

    public void showFilePath(String path) {
        System.out.println("\n📁 Текущий файл истории: " + path);
    }

    public String getFilePathInput() {
        System.out.print("Введите путь для сохранения (ENTER для сохранения в текущий файл): ");
        String input = scanner.nextLine();
        return input.trim().isEmpty() ? null : input;
    }

    public List<Integer> getSelectedEntries(List<String> history) {
        if (history.isEmpty()) {
            showMessage("История пуста, нечего сохранять");
            return null;
        }

        System.out.println("\nВыберите номера записей для сохранения (через запятую, например: 1,3,5)");
        System.out.print("Или введите 'all' для сохранения всех записей: ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("all")) {
            List<Integer> all = new java.util.ArrayList<>();
            for (int i = 0; i < history.size(); i++) {
                all.add(i);
            }
            return all;
        }

        try {
            List<Integer> indices = new java.util.ArrayList<>();
            String[] parts = input.split(",");
            for (String part : parts) {
                int index = Integer.parseInt(part.trim()) - 1;
                if (index >= 0 && index < history.size()) {
                    indices.add(index);
                } else {
                    showError("Индекс " + (index + 1) + " вне диапазона");
                    return null;
                }
            }
            return indices;
        } catch (NumberFormatException e) {
            showError("Неверный формат ввода. Используйте числа через запятую");
            return null;
        }
    }

    public boolean getConfirmation(String message) {
        System.out.print(message + " (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes") || input.equals("д") || input.equals("да");
    }

    public void close() {
        scanner.close();
    }
}