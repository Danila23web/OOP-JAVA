/**
 * Задача для вывода чисел от 1 до 5 с задержкой 500 мс
 */
class NumberTask implements Runnable {
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println(threadName + " - Число: " + i);
                Thread.sleep(500);
            }
            System.out.println(threadName + " - Завершил выполнение");
        } catch (InterruptedException e) {
            System.err.println(threadName + " был прерван");
            Thread.currentThread().interrupt();
        }
    }
}

/**
 * Задача для вывода букв от 'A' до 'E' с задержкой 700 мс
 */
class LetterTask implements Runnable {
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        try {
            for (char letter = 'A'; letter <= 'E'; letter++) {
                System.out.println(threadName + " - Буква: " + letter);
                Thread.sleep(700);
            }
            System.out.println(threadName + " - Завершил выполнение");
        } catch (InterruptedException e) {
            System.err.println(threadName + " был прерван");
            Thread.currentThread().interrupt();
        }
    }
}

/**
 * Расширенная версия с дополнительными возможностями
 */
public class practika14 {
    public static void main(String[] args) {
        // Создаем потоки с разными именами
        Thread numberThread = new Thread(new NumberTask(), "ЧисловойПоток");
        Thread letterThread = new Thread(new LetterTask(), "БуквенныйПоток");

        // Устанавливаем приоритеты (не гарантируется строгое выполнение)
        numberThread.setPriority(Thread.MIN_PRIORITY);  // Приоритет 1
        letterThread.setPriority(Thread.MAX_PRIORITY);  // Приоритет 10

        System.out.println("=== Информация о потоках ===");
        System.out.println("Имя: " + numberThread.getName() +
                ", Приоритет: " + numberThread.getPriority() +
                ", Состояние: " + numberThread.getState());
        System.out.println("Имя: " + letterThread.getName() +
                ", Приоритет: " + letterThread.getPriority() +
                ", Состояние: " + letterThread.getState());
        System.out.println();

        // Запускаем потоки
        long startTime = System.currentTimeMillis();
        numberThread.start();
        letterThread.start();

        // Мониторинг состояния потоков
        Thread monitorThread = new Thread(() -> {
            try {
                while (numberThread.isAlive() || letterThread.isAlive()) {
                    System.out.println("\n--- Мониторинг состояний ---");
                    System.out.println(numberThread.getName() + ": " + numberThread.getState());
                    System.out.println(letterThread.getName() + ": " + letterThread.getState());
                    Thread.sleep(300); // Проверяем каждые 300 мс
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Монитор");
        monitorThread.setDaemon(true); // Фоновый поток для мониторинга
        monitorThread.start();

        try {
            numberThread.join();
            letterThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("\n=== Итоговое время выполнения: " +
                (endTime - startTime) + " мс ===");
    }
}