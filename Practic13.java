import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Scanner;
import java.util.Comparator;

public class Practic13 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> studentList = new ArrayList<>();
    private static Set<String> uniqueStudents = new HashSet<>();
    private static final Map<String, Integer> studentScores = new HashMap<>();
    private static final List<String> subjects = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("УПРАВЛЕНИЕ ДАННЫМИ ГРУППЫ СТУДЕНТОВ");
        System.out.println();

        initializeSubjects();
        mainMenu();
        scanner.close();
    }

    private static void initializeSubjects() {
        subjects.add("Математика");
        subjects.add("Физика");
        subjects.add("Информатика");
        subjects.add("Английский язык");
        subjects.add("История");
    }

    private static void mainMenu() {
        boolean running = true;

        while (running) {
            System.out.println("ГЛАВНОЕ МЕНЮ");
            System.out.println("1. Ввести студентов и оценки");
            System.out.println("2. Показать всех студентов");
            System.out.println("3. Добавить студента");
            System.out.println("4. Удалить студента");
            System.out.println("5. Редактировать оценку студента");
            System.out.println("6. Найти студента");
            System.out.println("7. Показать статистику");
            System.out.println("8. Сортировка студентов");
            System.out.println("9. Поиск по диапазону оценок");
            System.out.println("10. Показать предметы");
            System.out.println("11. Добавить предмет");
            System.out.println("12. Очистить все данные");
            System.out.println("13. Выйти");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> inputStudents();
                case 2 -> showAllStudents();
                case 3 -> addStudent();
                case 4 -> deleteStudent();
                case 5 -> editStudentScore();
                case 6 -> findStudent();
                case 7 -> showStatistics();
                case 8 -> sortStudents();
                case 9 -> searchByScoreRange();
                case 10 -> showSubjects();
                case 11 -> addSubject();
                case 12 -> clearAllData();
                case 13 -> {
                    running = false;
                    System.out.println("Выход из программы...");
                }
                default -> System.out.println("Неверный выбор!");
            }
            System.out.println();
        }
    }

    private static void inputStudents() {
        studentList.clear();
        uniqueStudents.clear();
        studentScores.clear();

        System.out.println("ВВОД ИМЕН СТУДЕНТОВ");
        System.out.println("(Добавьте 5 имен, одно должно повторяться)");
        System.out.println();

        for (int i = 0; i < 5; i++) {
            System.out.print("Введите имя студента " + (i + 1) + ": ");
            String name = scanner.nextLine();
            studentList.add(name);
        }

        System.out.println();
        System.out.println("СПИСОК СТУДЕНТОВ (ArrayList)");
        System.out.println("Студенты: " + studentList);
        System.out.println("Количество студентов: " + studentList.size());
        System.out.println();

        System.out.println("ПОДРОБНЫЙ СПИСОК");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println((i + 1) + ". " + studentList.get(i));
        }
        System.out.println();

        uniqueStudents = new HashSet<>(studentList);

        System.out.println("УНИКАЛЬНЫЕ СТУДЕНТЫ (HashSet)");
        System.out.println("Уникальные имена: " + uniqueStudents);
        System.out.println("Количество уникальных студентов: " + uniqueStudents.size());
        System.out.println();

        int count = 1;
        for (String name : uniqueStudents) {
            System.out.println(count + ". " + name);
            count++;
        }
        System.out.println();

        System.out.println("ВВОД ОЦЕНОК СТУДЕНТОВ");
        System.out.println("(Оценка от 1 до 100)");
        System.out.println();

        for (String name : uniqueStudents) {
            int score = inputScore(name);
            studentScores.put(name, score);
        }

        System.out.println();
        System.out.println("Данные успешно введены!");
    }

    private static int inputScore(String name) {
        int score = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Введите оценку для " + name + ": ");
            try {
                score = scanner.nextInt();
                if (score >= 1 && score <= 100) {
                    validInput = true;
                } else {
                    System.out.println("Ошибка: Оценка должна быть от 1 до 100!");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: Введите целое число!");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return score;
    }

    private static void showAllStudents() {
        if (studentScores.isEmpty()) {
            System.out.println("Нет данных о студентах!");
            return;
        }

        System.out.println("СПИСОК ВСЕХ СТУДЕНТОВ");
        System.out.println("Студент - Оценка");
        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
            String grade = getGradeLetter(entry.getValue());
            System.out.println(entry.getKey() + " - " + entry.getValue() + " (" + grade + ")");
        }
        System.out.println();
        System.out.println("Всего студентов: " + studentScores.size());
    }

    private static String getGradeLetter(int score) {
        if (score >= 86) return "A";
        else if (score >= 70) return "B";
        else if (score >= 50) return "C";
        else if (score >= 30) return "D";
        else return "F";
    }

    private static void addStudent() {
        System.out.print("Введите имя нового студента: ");
        String name = scanner.nextLine();

        if (studentScores.containsKey(name)) {
            System.out.println("Студент с таким именем уже существует!");
            return;
        }

        int score = inputScore(name);
        studentScores.put(name, score);
        studentList.add(name);
        uniqueStudents.add(name);

        System.out.println("Студент " + name + " успешно добавлен!");
    }

    private static void deleteStudent() {
        if (studentScores.isEmpty()) {
            System.out.println("Нет студентов для удаления!");
            return;
        }

        System.out.print("Введите имя студента для удаления: ");
        String name = scanner.nextLine();

        if (studentScores.containsKey(name)) {
            studentScores.remove(name);
            studentList.remove(name);
            uniqueStudents.remove(name);
            System.out.println("Студент " + name + " успешно удален!");
        } else {
            System.out.println("Студент " + name + " не найден!");
        }
    }

    private static void editStudentScore() {
        if (studentScores.isEmpty()) {
            System.out.println("Нет студентов для редактирования!");
            return;
        }

        System.out.print("Введите имя студента для редактирования: ");
        String name = scanner.nextLine();

        if (studentScores.containsKey(name)) {
            System.out.println("Текущая оценка " + name + ": " + studentScores.get(name));
            int newScore = inputScore(name);
            studentScores.put(name, newScore);
            System.out.println("Оценка успешно обновлена!");
        } else {
            System.out.println("Студент " + name + " не найден!");
        }
    }

    private static void findStudent() {
        System.out.print("Введите имя для поиска: ");
        String searchName = scanner.nextLine();

        boolean found = false;
        for (String name : studentScores.keySet()) {
            if (name.toLowerCase().contains(searchName.toLowerCase())) {
                System.out.println("Найден: " + name + " - " + studentScores.get(name) + " (" + getGradeLetter(studentScores.get(name)) + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Студенты не найдены!");
        }
    }

    private static void showStatistics() {
        if (studentScores.isEmpty()) {
            System.out.println("Нет данных для статистики!");
            return;
        }

        System.out.println("СТАТИСТИКА ГРУППЫ");
        int totalScore = 0;
        int maxScore = 0;
        int minScore = 100;
        String bestStudent = "";
        String worstStudent = "";

        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
            String name = entry.getKey();
            int score = entry.getValue();

            totalScore += score;

            if (score > maxScore) {
                maxScore = score;
                bestStudent = name;
            }

            if (score < minScore) {
                minScore = score;
                worstStudent = name;
            }
        }

        double averageScore = (double) totalScore / studentScores.size();

        System.out.println("Количество студентов: " + studentScores.size());
        System.out.println("Сумма баллов: " + totalScore);
        System.out.println("Средний балл: " + String.format("%.2f", averageScore));
        System.out.println("Лучший студент: " + bestStudent + " (" + maxScore + " баллов, " + getGradeLetter(maxScore) + ")");
        System.out.println("Худший студент: " + worstStudent + " (" + minScore + " баллов, " + getGradeLetter(minScore) + ")");
        System.out.println();

        System.out.println("РАСПРЕДЕЛЕНИЕ ОЦЕНОК");
        int excellent = 0;
        int good = 0;
        int satisfactory = 0;
        int poor = 0;
        int veryPoor = 0;

        for (int score : studentScores.values()) {
            if (score >= 86) excellent++;
            else if (score >= 70) good++;
            else if (score >= 50) satisfactory++;
            else if (score >= 30) poor++;
            else veryPoor++;
        }

        System.out.println("Отлично (86-100): " + excellent + " студентов");
        System.out.println("Хорошо (70-85): " + good + " студентов");
        System.out.println("Удовлетворительно (50-69): " + satisfactory + " студентов");
        System.out.println("Плохо (30-49): " + poor + " студентов");
        System.out.println("Очень плохо (1-29): " + veryPoor + " студентов");
        System.out.println();

        double passRate = (double) (excellent + good + satisfactory) / studentScores.size() * 100;
        System.out.println("Успеваемость: " + String.format("%.2f", passRate) + "%");
    }

    private static void sortStudents() {
        if (studentScores.isEmpty()) {
            System.out.println("Нет данных для сортировки!");
            return;
        }

        System.out.println("СОРТИРОВКА СТУДЕНТОВ");
        System.out.println("1. По имени (А-Я)");
        System.out.println("2. По имени (Я-А)");
        System.out.println("3. По оценке (по возрастанию)");
        System.out.println("4. По оценке (по убыванию)");
        System.out.print("Ваш выбор: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(studentScores.entrySet());

        switch (choice) {
            case 1 -> entries.sort(Map.Entry.comparingByKey());
            case 2 -> entries.sort(Map.Entry.comparingByKey(Comparator.reverseOrder()));
            case 3 -> entries.sort(Map.Entry.comparingByValue());
            case 4 -> entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            default -> {
                System.out.println("Неверный выбор!");
                return;
            }
        }

        System.out.println("Результат сортировки:");
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " (" + getGradeLetter(entry.getValue()) + ")");
        }
    }

    private static void searchByScoreRange() {
        if (studentScores.isEmpty()) {
            System.out.println("Нет данных для поиска!");
            return;
        }

        System.out.print("Введите минимальную оценку: ");
        int min = scanner.nextInt();
        System.out.print("Введите максимальную оценку: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        System.out.println("Студенты с оценками от " + min + " до " + max + ":");
        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
            int score = entry.getValue();
            if (score >= min && score <= max) {
                System.out.println(entry.getKey() + " - " + score + " (" + getGradeLetter(score) + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Студенты не найдены!");
        }
    }

    private static void showSubjects() {
        System.out.println("СПИСОК ПРЕДМЕТОВ");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i));
        }
        System.out.println("Всего предметов: " + subjects.size());
    }

    private static void addSubject() {
        System.out.print("Введите название нового предмета: ");
        String subject = scanner.nextLine();

        if (subjects.contains(subject)) {
            System.out.println("Такой предмет уже существует!");
        } else {
            subjects.add(subject);
            System.out.println("Предмет \"" + subject + "\" добавлен!");
        }
    }

    private static void clearAllData() {
        System.out.print("Вы уверены, что хотите очистить все данные? (да/нет): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("да")) {
            studentList.clear();
            uniqueStudents.clear();
            studentScores.clear();
            System.out.println("Все данные успешно очищены!");
        } else {
            System.out.println("Операция отменена.");
        }
    }
}