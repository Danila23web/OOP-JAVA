import java.util.Scanner;

public class Practic2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name;
        int age;
        double geographyGrade;
        boolean isStudent;
        char gender;

        System.out.print("Введите имя: ");
        name = scanner.nextLine();

        System.out.print("Введите возраст: ");
        age = scanner.nextInt();

        System.out.print("Введите средний балл по географии: ");
        geographyGrade = scanner.nextDouble();

        System.out.print("Является студентом? (true/false): ");
        isStudent = scanner.nextBoolean();

        System.out.print("Введите пол (М/Ж): ");
        gender = scanner.next().charAt(0);

        System.out.println("\n=== ДАННЫЕ О СТУДЕНТЕ ===");
        System.out.println(" Name: " + name);
        System.out.println(" Age: " + age + " лет");
        System.out.println(" Средний балл по географии: " + geographyGrade);
        System.out.println(" Статус студента: " + (isStudent ? "Да" : "Нет"));
        System.out.println(" Пол: " + gender);

        scanner.close();
    }
}