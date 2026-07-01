import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Расширенная версия "Журнал успеваемости"
 * С возможностью интерактивного взаимодействия
 */
public class practika17 {
    private HashMap<String, Integer> grades;
    private Scanner scanner;

    // ИСПРАВЛЕНО: конструктор теперь называется practika17 (как класс)
    public practika17() {
        grades = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        // ИСПРАВЛЕНО: создаем объект класса practika17
        practika17 journal = new practika17();
        journal.run();
    }

    private void run() {
        System.out.println("=== ИНТЕРАКТИВНЫЙ ЖУРНАЛ УСПЕВАЕМОСТИ ===\n");

        // Инициализация начальными данными
        initializeJournal();

        boolean running = true;
        while (running) {
            showMenu();
            int choice = getIntInput("Выберите действие: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    getStudentGrade();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    checkStudentExists();
                    break;
                case 5:
                    printAllStudents();
                    break;
                case 6:
                    printStatistics();
                    break;
                case 0:
                    running = false;
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private void initializeJournal() {
        grades.put("Анна", 95);
        grades.put("Максим", 78);
        grades.put("Елена", 88);
        grades.put("Дмитрий", 92);
        grades.put("Ольга", 85);
        System.out.println("Журнал инициализирован 5 студентами.\n");
    }

    private void showMenu() {
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│ 1. Добавить студента                        │");
        System.out.println("│ 2. Получить оценку студента                 │");
        System.out.println("│ 3. Удалить студента                         │");
        System.out.println("│ 4. Проверить наличие студента               │");
        System.out.println("│ 5. Показать весь журнал                    │");
        System.out.println("│ 6. Показать статистику                     │");
        System.out.println("│ 0. Выход                                   │");
        System.out.println("└─────────────────────────────────────────────┘");
    }

    private void addStudent() {
        System.out.println("\n--- Добавление студента ---");
        String name = getStringInput("Введите имя студента: ");

        // Проверка на существование
        if (grades.containsKey(name)) {
            System.out.println("Студент " + name + " уже существует. Текущая оценка: " +
                    grades.get(name));
            String answer = getStringInput("Обновить оценку? (да/нет): ");
            if (!answer.equalsIgnoreCase("да")) {
                return;
            }
        }

        int grade = getIntInput("Введите оценку (0-100): ");
        while (grade < 0 || grade > 100) {
            System.out.println("Оценка должна быть от 0 до 100!");
            grade = getIntInput("Введите оценку (0-100): ");
        }

        grades.put(name, grade);
        System.out.println("Студент " + name + " успешно добавлен с оценкой " + grade);
    }

    private void getStudentGrade() {
        System.out.println("\n--- Получение оценки ---");
        String name = getStringInput("Введите имя студента: ");
        Integer grade = grades.get(name);

        if (grade != null) {
            System.out.println("Студент: " + name + ", Оценка: " + grade);
            // Вывод буквенной оценки
            System.out.println("Буквенная оценка: " + getLetterGrade(grade));
        } else {
            System.out.println("Студент " + name + " не найден в журнале");
        }
    }

    private void removeStudent() {
        System.out.println("\n--- Удаление студента ---");
        String name = getStringInput("Введите имя студента для удаления: ");
        Integer removed = grades.remove(name);

        if (removed != null) {
            System.out.println("Студент " + name + " (оценка " + removed + ") удален");
        } else {
            System.out.println("Студент " + name + " не найден в журнале");
        }
    }

    private void checkStudentExists() {
        System.out.println("\n--- Проверка наличия студента ---");
        String name = getStringInput("Введите имя студента: ");
        boolean exists = grades.containsKey(name);

        System.out.println("Студент \"" + name + "\" " +
                (exists ? "ПРИСУТСТВУЕТ" : "ОТСУТСТВУЕТ") +
                " в журнале");

        if (exists) {
            System.out.println("Оценка: " + grades.get(name));
        }
    }

    private void printAllStudents() {
        System.out.println("\n--- ПОЛНЫЙ ЖУРНАЛ ---");
        if (grades.isEmpty()) {
            System.out.println("Журнал пуст");
            return;
        }

        System.out.println("Всего студентов: " + grades.size());
        System.out.println("┌─────────────┬────────────┬─────────────┐");
        System.out.println("│ Студент     │ Оценка     │ Буквенно    │");
        System.out.println("├─────────────┼────────────┼─────────────┤");

        // Сортировка не гарантируется - выводим в порядке хранения
        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            String name = entry.getKey();
            Integer grade = entry.getValue();
            String letter = getLetterGrade(grade);
            System.out.printf("│ %-11s │ %-10d │ %-11s │\n", name, grade, letter);
        }
        System.out.println("└─────────────┴────────────┴─────────────┘");
    }

    private void printStatistics() {
        System.out.println("\n--- СТАТИСТИКА ---");
        if (grades.isEmpty()) {
            System.out.println("Журнал пуст");
            return;
        }

        int count = grades.size();
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (Integer grade : grades.values()) {
            sum += grade;
            max = Math.max(max, grade);
            min = Math.min(min, grade);
        }

        double average = (double) sum / count;

        System.out.println("Количество студентов: " + count);
        System.out.printf("Средняя оценка: %.2f\n", average);
        System.out.println("Максимальная оценка: " + max);
        System.out.println("Минимальная оценка: " + min);

        // Находим студентов с максимальной и минимальной оценкой
        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            if (entry.getValue() == max) {
                System.out.println("Отличник: " + entry.getKey() + " (" + max + ")");
            }
            if (entry.getValue() == min) {
                System.out.println("Двоечник: " + entry.getKey() + " (" + min + ")");
            }
        }
    }

    private String getLetterGrade(int grade) {
        if (grade >= 90) return "A (отлично)";
        else if (grade >= 80) return "B (хорошо)";
        else if (grade >= 70) return "C (удовл.)";
        else if (grade >= 60) return "D (неуд.)";
        else return "F (провал)";
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите число!");
            }
        }
    }
}