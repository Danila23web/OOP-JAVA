import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Scanner;

public class Practic8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println("TO-DO LIST МЕНЕДЖЕР");
        System.out.println();

        System.out.println("Добавление начальных задач");
        System.out.println();

        for (int i = 0; i < 3; i++) {
            System.out.print("Введите задачу " + (i + 1) + ": ");
            String task = scanner.nextLine();
            tasks.add(task);
        }

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("═══════════════════════════════════════════");
            System.out.println("МЕНЮ УПРАВЛЕНИЯ СПИСКОМ ЗАДАЧ");
            System.out.println("═══════════════════════════════════════════");
            System.out.println("1. Показать все задачи");
            System.out.println("2. Добавить задачу");
            System.out.println("3. Удалить задачу");
            System.out.println("4. Найти задачу");
            System.out.println("5. Сортировать по алфавиту");
            System.out.println("6. Очистить список");
            System.out.println("7. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.println("СПИСОК ЗАДАЧ");
                    System.out.println();
                    if (tasks.isEmpty()) {
                        System.out.println("Список задач пуст.");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                        System.out.println();
                        System.out.println("Всего задач: " + tasks.size());
                    }
                    break;

                case 2:
                    System.out.println();
                    System.out.println("ДОБАВЛЕНИЕ ЗАДАЧИ");
                    System.out.println();
                    System.out.print("Введите новую задачу: ");
                    String newTask = scanner.nextLine();
                    tasks.add(newTask);
                    System.out.println("Задача \"" + newTask + "\" добавлена!");
                    break;

                case 3:
                    System.out.println();
                    System.out.println("УДАЛЕНИЕ ЗАДАЧИ");
                    System.out.println();
                    if (tasks.isEmpty()) {
                        System.out.println("Список задач пуст. Нечего удалять.");
                        break;
                    }

                    System.out.println("Текущие задачи:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }

                    System.out.print("Введите номер задачи для удаления: ");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (deleteIndex >= 1 && deleteIndex <= tasks.size()) {
                        String removed = tasks.remove(deleteIndex - 1);
                        System.out.println("Задача \"" + removed + "\" удалена!");
                    } else {
                        System.out.println("Неверный номер задачи!");
                    }
                    break;

                case 4:
                    System.out.println();
                    System.out.println("ПОИСК ЗАДАЧИ");
                    System.out.println();
                    System.out.print("Введите текст для поиска: ");
                    String search = scanner.nextLine();

                    boolean found = false;
                    System.out.println();
                    System.out.println("Результаты поиска:");
                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i).toLowerCase().contains(search.toLowerCase())) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("Задачи \"" + search + "\" не найдены.");
                    }
                    break;

                case 5:
                    System.out.println();
                    System.out.println("СОРТИРОВКА ПО АЛФАВИТУ");
                    System.out.println();
                    System.out.println("Список до сортировки:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }

                    Collections.sort(tasks);

                    System.out.println();
                    System.out.println("Список после сортировки:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println();
                    System.out.println("Список успешно отсортирован!");
                    break;

                case 6:
                    System.out.println();
                    System.out.println("ОЧИСТКА СПИСКА");
                    System.out.println();
                    System.out.print("Вы уверены, что хотите очистить список? (да/нет): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("DA")) {
                        tasks.clear();
                        System.out.println("Список очищен!");
                    } else {
                        System.out.println("Операция отменена.");
                    }
                    break;

                case 7:
                    running = false;
                    System.out.println();
                    System.out.println("Выход из программы...");
                    break;

                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }

        System.out.println();
        System.out.println("Итоговый список задач:");
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }

        System.out.println();
        System.out.println("РАБОТА ПРОГРАММЫ ЗАВЕРШЕНА!");

        scanner.close();
    }
}