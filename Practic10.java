import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Practic10 {
    private static final String FILE_NAME = "students.txt";
    private static final String BACKUP_FILE = "students_backup.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> students = new ArrayList<>();

        System.out.println("УПРАВЛЕНИЕ СПИСКОМ СТУДЕНТОВ\n");

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("ГЛАВНОЕ МЕНЮ");
            System.out.println("───────────────────────────────────────────");
            System.out.println("1 - Создать новый список студентов");
            System.out.println("2 - Загрузить список из файла");
            System.out.println("3 - Показать список студентов");
            System.out.println("4 - Добавить студента");
            System.out.println("5 - Удалить студента");
            System.out.println("6 - Найти студента");
            System.out.println("7 - Сохранить список в файл");
            System.out.println("8 - Создать резервную копию");
            System.out.println("9 - Восстановить из резервной копии");
            System.out.println("10 - Очистить список");
            System.out.println("11 - Выйти");
            System.out.println("───────────────────────────────────────────");
            System.out.print("\nВаш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println();
                    System.out.println("СОЗДАНИЕ НОВОГО СПИСКА");
                    System.out.println("───────────────────────────────────────────");
                    students.clear();
                    System.out.print("Сколько студентов добавить? ");
                    int count = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < count; i++) {
                        System.out.print("Студент " + (i + 1) + ": ");
                        students.add(scanner.nextLine());
                    }
                    System.out.println("✓ Список создан! Добавлено " + count + " студентов.");
                }
                case 2 -> {
                    System.out.println();
                    System.out.println("ЗАГРУЗКА СПИСКА ИЗ ФАЙЛА");
                    System.out.println("───────────────────────────────────────────");
                    try {
                        List<String> loaded = loadStudentsFromFile(FILE_NAME);
                        students.clear();
                        students.addAll(loaded);
                        System.out.println("✓ Загружено " + students.size() + " студентов из файла " + FILE_NAME);
                    } catch (IOException e) {
                        System.out.println("✗ Ошибка загрузки: " + e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.println();
                    System.out.println("СПИСОК СТУДЕНТОВ");
                    System.out.println("───────────────────────────────────────────");
                    if (students.isEmpty()) {
                        System.out.println("Список пуст.");
                    } else {
                        for (int i = 0; i < students.size(); i++) {
                            System.out.println((i + 1) + ". " + students.get(i));
                        }
                        System.out.println("Всего студентов: " + students.size());
                    }
                }
                case 4 -> {
                    System.out.println();
                    System.out.println("ДОБАВЛЕНИЕ СТУДЕНТА");
                    System.out.println("───────────────────────────────────────────");
                    System.out.print("Имя студента: ");
                    String name = scanner.nextLine();
                    students.add(name);
                    System.out.println("✓ Студент \"" + name + "\" добавлен!");
                }
                case 5 -> {
                    System.out.println();
                    System.out.println("УДАЛЕНИЕ СТУДЕНТА");
                    System.out.println("───────────────────────────────────────────");
                    if (students.isEmpty()) {
                        System.out.println("Список пуст.");
                        break;
                    }

                    System.out.println("Выберите способ удаления:");
                    System.out.println("1 - По имени");
                    System.out.println("2 - По номеру");
                    System.out.print("Ваш выбор: ");
                    int deleteChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (deleteChoice == 1) {
                        System.out.print("Введите имя для удаления: ");
                        String nameToDelete = scanner.nextLine();
                        if (students.remove(nameToDelete)) {
                            System.out.println("✓ Студент \"" + nameToDelete + "\" удален!");
                        } else {
                            System.out.println("✗ Студент \"" + nameToDelete + "\" не найден.");
                        }
                    } else if (deleteChoice == 2) {
                        System.out.println("Текущий список:");
                        for (int i = 0; i < students.size(); i++) {
                            System.out.println((i + 1) + ". " + students.get(i));
                        }
                        System.out.print("Введите номер: ");
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        if (index >= 1 && index <= students.size()) {
                            String removed = students.remove(index - 1);
                            System.out.println("✓ Студент \"" + removed + "\" удален!");
                        } else {
                            System.out.println("✗ Неверный номер!");
                        }
                    }
                }
                case 6 -> {
                    System.out.println();
                    System.out.println("ПОИСК СТУДЕНТА");
                    System.out.println("───────────────────────────────────────────");
                    System.out.print("Введите имя для поиска: ");
                    String searchName = scanner.nextLine();
                    List<String> results = new ArrayList<>();

                    for (String student : students) {
                        if (student.toLowerCase().contains(searchName.toLowerCase())) {
                            results.add(student);
                        }
                    }

                    if (results.isEmpty()) {
                        System.out.println("✗ Студенты \"" + searchName + "\" не найдены.");
                    } else {
                        System.out.println("Найдено " + results.size() + " студентов:");
                        for (int i = 0; i < results.size(); i++) {
                            System.out.println((i + 1) + ". " + results.get(i));
                        }
                    }
                }
                case 7 -> {
                    System.out.println();
                    System.out.println("СОХРАНЕНИЕ СПИСКА В ФАЙЛ");
                    System.out.println("───────────────────────────────────────────");
                    try {
                        saveStudentsToFile(students, FILE_NAME);
                        System.out.println("✓ Список сохранен в файл " + FILE_NAME);
                        System.out.println("  Количество студентов: " + students.size());
                    } catch (IOException e) {
                        System.out.println("✗ Ошибка сохранения: " + e.getMessage());
                    }
                }
                case 8 -> {
                    System.out.println();
                    System.out.println("СОЗДАНИЕ РЕЗЕРВНОЙ КОПИИ");
                    System.out.println("───────────────────────────────────────────");
                    try {
                        saveStudentsToFile(students, BACKUP_FILE);
                        System.out.println("✓ Резервная копия создана в файл " + BACKUP_FILE);
                    } catch (IOException e) {
                        System.out.println("✗ Ошибка создания копии: " + e.getMessage());
                    }
                }
                case 9 -> {
                    System.out.println();
                    System.out.println("ВОССТАНОВЛЕНИЕ ИЗ РЕЗЕРВНОЙ КОПИИ");
                    System.out.println("───────────────────────────────────────────");
                    try {
                        List<String> backup = loadStudentsFromFile(BACKUP_FILE);
                        students.clear();
                        students.addAll(backup);
                        System.out.println("✓ Восстановлено " + students.size() + " студентов из резервной копии!");
                    } catch (IOException e) {
                        System.out.println("✗ Ошибка восстановления: " + e.getMessage());
                        System.out.println("  Файл резервной копии не найден.");
                    }
                }
                case 10 -> {
                    System.out.println();
                    System.out.println("ОЧИСТКА СПИСКА");
                    System.out.println("───────────────────────────────────────────");
                    System.out.print("Вы уверены? (да/нет): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("да")) {
                        students.clear();
                        System.out.println("✓ Список очищен!");
                    } else {
                        System.out.println("Операция отменена.");
                    }
                }
                case 11 -> {
                    running = false;
                    System.out.println();
                    System.out.println("Выход из программы...");
                }
                default -> System.out.println("✗ Неверный выбор!");
            }
        }

        System.out.println("═══════════════════════════════════════════");
        System.out.println("ПРОГРАММА ЗАВЕРШЕНА!");
        System.out.println("═══════════════════════════════════════════");

        scanner.close();
    }

    public static void saveStudentsToFile(List<String> students, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String student : students) {
                writer.write(student + System.lineSeparator());
            }
        }
    }

    public static List<String> loadStudentsFromFile(String fileName) throws IOException {
        java.nio.file.Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            throw new IOException("Файл не найден: " + fileName);
        }
        return Files.readAllLines(path);
    }
}