import java.util.Scanner;

public class Practic6 {

    static class Book {
        private String title;
        private String author;
        private int year;

        public Book(String title, String author, int year) {
            this.title = title;
            this.author = author;
            this.year = year;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public int getYear() {
            return year;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public void displayInfo() {
            System.out.println("Название: " + title);
            System.out.println("Автор: " + author);
            System.out.println("Год издания: " + year);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("РАБОТА С КЛАССОМ BOOK");
        System.out.println();

        System.out.println("Создание книг вручную");
        System.out.println();

        System.out.print("Введите название книги 1: ");
        String title1 = scanner.nextLine();
        System.out.print("Введите автора книги 1: ");
        String author1 = scanner.nextLine();
        System.out.print("Введите год издания книги 1: ");
        int year1 = scanner.nextInt();
        scanner.nextLine();

        System.out.print("\nВведите название книги 2: ");
        String title2 = scanner.nextLine();
        System.out.print("Введите автора книги 2: ");
        String author2 = scanner.nextLine();
        System.out.print("Введите год издания книги 2: ");
        int year2 = scanner.nextInt();
        scanner.nextLine();

        Book book1 = new Book(title1, author1, year1);
        Book book2 = new Book(title2, author2, year2);

        System.out.println();
        System.out.println("Книга 1");
        book1.displayInfo();

        System.out.println();
        System.out.println("Книга 2");
        book2.displayInfo();

        System.out.println();
        System.out.println("РАБОТА С ГЕТТЕРОМ / СЕТТЕРОМ");
        System.out.println();

        System.out.println("Геттеры");
        System.out.println("Название книги 1: " + book1.getTitle());
        System.out.println("Автор книги 1: " + book1.getAuthor());
        System.out.println("Год издания книги 1: " + book1.getYear());

        System.out.println();
        System.out.println("ДОБАВЛЕНИЕ НОВЫХ КНИГ");
        System.out.println();

        System.out.print("Сколько книг вы хотите добавить? ");
        int count = scanner.nextInt();
        scanner.nextLine();

        Book[] additionalBooks = new Book[count];
        for (int i = 0; i < count; i++) {
            System.out.println();
            System.out.println("Введите данные для книги " + (i + 1) + ":");
            System.out.print("Название: ");
            String t = scanner.nextLine();
            System.out.print("Автор: ");
            String a = scanner.nextLine();
            System.out.print("Год издания: ");
            int y = scanner.nextInt();
            scanner.nextLine();
            additionalBooks[i] = new Book(t, a, y);
        }

        Book[] books = new Book[2 + count];
        books[0] = book1;
        books[1] = book2;
        System.arraycopy(additionalBooks, 0, books, 2, count);

        System.out.println();
        System.out.println("ВСЕ КНИГИ В БИБЛИОТЕКЕ");
        System.out.println();

        System.out.println("Библиотека из " + books.length + " книг");
        for (int i = 0; i < books.length; i++) {
            System.out.println();
            System.out.println("Книга " + (i + 1) + ":");
            books[i].displayInfo();
        }

        System.out.println();
        System.out.println("РЕДАКТИРОВАНИЕ ЛЮБОЙ КНИГИ");
        System.out.println();

        boolean editMode = true;

        while (editMode) {
            System.out.println("Выберите действие:");
            System.out.println("1 - Редактировать книгу");
            System.out.println("2 - Показать все книги");
            System.out.println("3 - Выйти из редактирования");
            System.out.print("Ваш выбор: ");

            int editChoice = scanner.nextInt();
            scanner.nextLine();

            switch (editChoice) {
                case 1 -> {
                    System.out.print("\nВведите номер книги для редактирования (1-" + books.length + "): ");
                    int bookNumber = scanner.nextInt();
                    scanner.nextLine();

                    if (bookNumber < 1 || bookNumber > books.length) {
                        System.out.println("Неверный номер книги!");
                        System.out.println();
                        continue;
                    }

                    int index = bookNumber - 1;
                    System.out.println();
                    System.out.println("Текущая информация о книге " + bookNumber + ":");
                    books[index].displayInfo();

                    System.out.println();
                    System.out.println("Введите новые данные (оставьте пустым, чтобы не менять):");

                    System.out.print("Новое название (" + books[index].getTitle() + "): ");
                    String newTitle = scanner.nextLine();
                    if (!newTitle.trim().isEmpty()) {
                        books[index].setTitle(newTitle);
                    }

                    System.out.print("Новый автор (" + books[index].getAuthor() + "): ");
                    String newAuthor = scanner.nextLine();
                    if (!newAuthor.trim().isEmpty()) {
                        books[index].setAuthor(newAuthor);
                    }

                    System.out.print("Новый год издания (" + books[index].getYear() + "): ");
                    String yearInput = scanner.nextLine();
                    if (!yearInput.trim().isEmpty()) {
                        try {
                            int newYear = Integer.parseInt(yearInput);
                            books[index].setYear(newYear);
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный формат года. Год не изменен.");
                        }
                    }

                    System.out.println();
                    System.out.println("Книга успешно обновлена!");
                    System.out.println("Обновленная информация:");
                    books[index].displayInfo();
                    System.out.println();
                }
                case 2 -> {
                    System.out.println();
                    System.out.println("Все книги в библиотеке");
                    for (int i = 0; i < books.length; i++) {
                        System.out.println();
                        System.out.println("Книга " + (i + 1) + ":");
                        books[i].displayInfo();
                    }
                    System.out.println();
                }
                case 3 -> {
                    editMode = false;
                    System.out.println("Выход из режима редактирования.");
                    System.out.println();
                }
                default -> {
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    System.out.println();
                }
            }
        }

        System.out.println();
        System.out.println("ПОИСК КНИГ ПО АВТОРУ");
        System.out.println();

        while (true) {
            System.out.print("Введите имя автора для поиска (или 'exit' для выхода): ");
            String searchAuthor = scanner.nextLine();

            if (searchAuthor.equalsIgnoreCase("exit")) {
                System.out.println("Поиск завершен.");
                break;
            }

            if (searchAuthor.trim().isEmpty()) {
                System.out.println("Введите имя автора.");
                System.out.println();
                continue;
            }

            System.out.println();
            System.out.println("Поиск книг автора: " + searchAuthor);
            boolean found = false;

            for (Book book : books) {
                if (book.getAuthor().toLowerCase().contains(searchAuthor.toLowerCase())) {
                    System.out.println();
                    System.out.println("Найдена книга:");
                    book.displayInfo();
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Книги этого автора не найдены.");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("LIBRARY STATISTICS");
        System.out.println();

        int oldestYear = Integer.MAX_VALUE;
        int newestYear = Integer.MIN_VALUE;
        String oldestBook = "";
        String newestBook = "";

        for (Book book : books) {
            if (book.getYear() < oldestYear) {
                oldestYear = book.getYear();
                oldestBook = book.getTitle();
            }
            if (book.getYear() > newestYear) {
                newestYear = book.getYear();
                newestBook = book.getTitle();
            }
        }

        System.out.println("Самая старая книга: " + oldestBook + " (" + oldestYear + " г.)");
        System.out.println("Самая новая книга: " + newestBook + " (" + newestYear + " г.)");

        System.out.println();
        System.out.println("SORTING BOOKS BY YEAR OF PUBLICATION");
        System.out.println();

        Book[] sortedBooks = books.clone();
        for (int i = 0; i < sortedBooks.length - 1; i++) {
            for (int j = 0; j < sortedBooks.length - i - 1; j++) {
                if (sortedBooks[j].getYear() > sortedBooks[j + 1].getYear()) {
                    Book temp = sortedBooks[j];
                    sortedBooks[j] = sortedBooks[j + 1];
                    sortedBooks[j + 1] = temp;
                }
            }
        }

        System.out.println("Книги в хронологическом порядке:");
        for (Book book : sortedBooks) {
            System.out.println(book.getYear() + " - " + book.getTitle() + " (" + book.getAuthor() + ")");
        }

        System.out.println();
        System.out.println("ПРОВЕРКА РАБОТЫ КОНСТРУКТОРА");
        System.out.println();

        System.out.println("Создание книги с пустыми значениями:");
        Book emptyBook = new Book("", "", 0);
        emptyBook.displayInfo();

        System.out.println();
        System.out.println("Заполняем данные через сеттеры...");
        System.out.print("Введите название: ");
        String fillTitle = scanner.nextLine();
        System.out.print("Введите автора: ");
        String fillAuthor = scanner.nextLine();
        System.out.print("Введите год издания: ");
        int fillYear = scanner.nextInt();
        scanner.nextLine();

        emptyBook.setTitle(fillTitle);
        emptyBook.setAuthor(fillAuthor);
        emptyBook.setYear(fillYear);

        System.out.println();
        System.out.println("Обновленная книга:");
        emptyBook.displayInfo();

        System.out.println();
        System.out.println("РАСШИРЕННЫЙ ПОИСК");
        System.out.println();

        boolean searchMode = true;

        while (searchMode) {
            System.out.println("Выберите тип поиска:");
            System.out.println("1 - Поиск по автору");
            System.out.println("2 - Поиск по названию");
            System.out.println("3 - Поиск по году издания");
            System.out.println("4 - Выход");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите имя автора: ");
                    String author = scanner.nextLine();
                    System.out.println();
                    System.out.println("Результаты поиска по автору \"" + author + "\":");
                    boolean authorFound = false;
                    for (Book book : books) {
                        if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                            book.displayInfo();
                            System.out.println();
                            authorFound = true;
                        }
                    }
                    if (!authorFound) {
                        System.out.println("Книги не найдены.");
                        System.out.println();
                    }
                }
                case 2 -> {
                    System.out.print("Введите название книги: ");
                    String title = scanner.nextLine();
                    System.out.println();
                    System.out.println("Результаты поиска по названию \"" + title + "\":");
                    boolean titleFound = false;
                    for (Book book : books) {
                        if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                            book.displayInfo();
                            System.out.println();
                            titleFound = true;
                        }
                    }
                    if (!titleFound) {
                        System.out.println("Книги не найдены.");
                        System.out.println();
                    }
                }
                case 3 -> {
                    System.out.print("Введите год издания: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println();
                    System.out.println("Результаты поиска по году " + year + ":");
                    boolean yearFound = false;
                    for (Book book : books) {
                        if (book.getYear() == year) {
                            book.displayInfo();
                            System.out.println();
                            yearFound = true;
                        }
                    }
                    if (!yearFound) {
                        System.out.println("Книги не найдены.");
                        System.out.println();
                    }
                }
                case 4 -> {
                    searchMode = false;
                    System.out.println("Поиск завершен.");
                }
                default -> {
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    System.out.println();
                }
            }
        }

        System.out.println();
        System.out.println("РАБОТА ПРОГРАММЫ ЗАВЕРШЕНА!");

        scanner.close();
    }
}