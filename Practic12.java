import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Practic12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        System.out.println("АНАЛИЗ ДАТЫ РОЖДЕНИЯ");
        System.out.println();

        LocalDate birthDate = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Введите дату рождения в формате дд.ММ.гггг (например, 15.03.1995): ");
            String input = scanner.nextLine();

            try {
                birthDate = LocalDate.parse(input, formatter);
                validInput = true;
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: Неверный формат даты. Используйте формат дд.ММ.гггг");
            }
        }

        LocalDate currentDate = LocalDate.now();

        System.out.println();
        System.out.println("ИНФОРМАЦИЯ О ДАТЕ РОЖДЕНИЯ");
        System.out.println("Дата рождения: " + birthDate.format(formatter));
        System.out.println("Текущая дата: " + currentDate.format(formatter));
        System.out.println();

        Period period = Period.between(birthDate, currentDate);
        int age = period.getYears();

        System.out.println("ВОЗРАСТ");
        System.out.println("Полных лет: " + age);
        System.out.println("Месяцев: " + period.getMonths());
        System.out.println("Дней: " + period.getDays());

        long totalDays = currentDate.toEpochDay() - birthDate.toEpochDay();
        System.out.println("Всего дней: " + totalDays);
        System.out.println();

        LocalDate nextBirthday = birthDate.withYear(currentDate.getYear());
        if (nextBirthday.isBefore(currentDate) || nextBirthday.isEqual(currentDate)) {
            nextBirthday = nextBirthday.plusYears(1);
        }

        long daysUntilBirthday = nextBirthday.toEpochDay() - currentDate.toEpochDay();

        System.out.println("СЛЕДУЮЩИЙ ДЕНЬ РОЖДЕНИЯ");
        System.out.println("Дата следующего дня рождения: " + nextBirthday.format(formatter));
        System.out.println("Дней до дня рождения: " + daysUntilBirthday);
        System.out.println();

        if (daysUntilBirthday == 0) {
            System.out.println("СЕГОДНЯ ДЕНЬ РОЖДЕНИЯ! ПОЗДРАВЛЯЕМ!");
        } else if (daysUntilBirthday == 1) {
            System.out.println("Завтра день рождения!");
        } else if (daysUntilBirthday <= 7) {
            System.out.println("До дня рождения осталось меньше недели!");
        }
        System.out.println();

        System.out.println("ДОПОЛНИТЕЛЬНАЯ ИНФОРМАЦИЯ");
        System.out.println("День недели рождения: " + birthDate.getDayOfWeek());
        System.out.println("Год рождения високосный? " + (birthDate.isLeapYear() ? "Да" : "Нет"));
        System.out.println("Дней в месяце рождения: " + birthDate.lengthOfMonth());
        System.out.println("День в году: " + birthDate.getDayOfYear());
        System.out.println();

        System.out.println("ВОЗРАСТ В РАЗНЫХ ЕДИНИЦАХ");
        System.out.println("Дней: " + totalDays);
        System.out.println("Часов: " + (totalDays * 24));
        System.out.println("Минут: " + (totalDays * 24 * 60));
        System.out.println("Секунд: " + (totalDays * 24 * 60 * 60));
        System.out.println();

        System.out.println("ДНИ ДО СЛЕДУЮЩЕГО ДНЯ РОЖДЕНИЯ В РАЗНЫХ ЕДИНИЦАХ");
        System.out.println("Дней: " + daysUntilBirthday);
        System.out.println("Часов: " + (daysUntilBirthday * 24));
        System.out.println("Минут: " + (daysUntilBirthday * 24 * 60));
        System.out.println("Секунд: " + (daysUntilBirthday * 24 * 60 * 60));
        System.out.println();

        if (birthDate.isAfter(currentDate)) {
            System.out.println("Внимание: Дата рождения в будущем!");
        }

        if (birthDate.equals(currentDate)) {
            System.out.println("Сегодня день рождения!");
        }

        System.out.println();
        System.out.println("ПРОГРАММА ЗАВЕРШЕНА!");

        scanner.close();
    }
}