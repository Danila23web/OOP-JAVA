package Model;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryManager {
    private final List<String> history; // Добавлен final
    private String currentFilePath;
    private static final String DEFAULT_FILE = "history.log";
    private static final boolean AUTO_SAVE_ENABLED = true; // Флаг автосохранения

    public HistoryManager() {
        this.history = new ArrayList<>();
        this.currentFilePath = DEFAULT_FILE;
    }

    public void addEntry(String entry) {
        history.add(entry);
        if (AUTO_SAVE_ENABLED) {
            autoSave(); // Теперь метод существует
        }
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    public void loadHistory() {
        File file = new File(currentFilePath);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    history.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке истории: " + e.getMessage());
        }
    }

    public void saveHistory() {
        saveToFile(currentFilePath);
    }

    public void saveToFile(String filePath) {
        try {
            String fullPath = resolveFilePath(filePath);
            Path path = Paths.get(fullPath);

            // Создаем директории если нужно
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath, false))) {
                for (String entry : history) {
                    writer.write(entry);
                    writer.newLine();
                }
            }

            currentFilePath = fullPath;
            System.out.println("История сохранена в: " + fullPath);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении истории: " + e.getMessage());
        }
    }

    // НОВЫЙ МЕТОД: автоматическое сохранение
    private void autoSave() {
        try {
            String fullPath = resolveFilePath(currentFilePath);
            Path path = Paths.get(fullPath);

            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath, false))) {
                for (String entry : history) {
                    writer.write(entry);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при автосохранении: " + e.getMessage());
        }
    }

    public void saveSelectedEntries(List<Integer> indices, String filePath) {
        if (indices == null || indices.isEmpty()) {
            System.out.println("Нет выбранных записей для сохранения");
            return;
        }

        try {
            String fullPath = resolveFilePath(filePath);
            Path path = Paths.get(fullPath);

            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath, false))) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                writer.write("=== Выбранные вычисления от " + timestamp + " ===");
                writer.newLine();
                writer.newLine();

                for (int index : indices) {
                    if (index >= 0 && index < history.size()) {
                        writer.write((index + 1) + ". " + history.get(index));
                        writer.newLine();
                    }
                }
            }

            System.out.println("Выбранные записи сохранены в: " + fullPath);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении выбранных записей: " + e.getMessage());
        }
    }

    private String resolveFilePath(String userPath) {
        if (userPath == null || userPath.trim().isEmpty()) {
            return DEFAULT_FILE;
        }

        userPath = userPath.trim();
        Path path = Paths.get(userPath);

        // Если это абсолютный путь с именем файла
        if (path.isAbsolute()) {
            return userPath;
        }

        // Если указано только имя файла (с расширением или без)
        if (userPath.matches("^[^/\\\\]+$")) {
            // Проверяем расширение
            if (userPath.matches(".*\\.(txt|log|md)$")) {
                return userPath;
            } else {
                return userPath + ".log";
            }
        }

        // Если указан путь без имени файла или с расширением
        if (userPath.endsWith("/") || userPath.endsWith("\\")) {
            return userPath + "log.log";
        }

        // Если указан путь с именем файла без расширения
        if (userPath.matches(".*[/\\\\].*")) {
            if (userPath.matches(".*\\.(txt|log|md)$")) {
                return userPath;
            } else {
                return userPath + ".log";
            }
        }

        return userPath;
    }

    public String getCurrentFilePath() {
        return currentFilePath;
    }
}