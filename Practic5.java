
import java.util.Scanner;

public class Practic5 {

    public static int sum(int a, int b) {
        return a + b;
    }

    public static int sum(int a, int b, int c) {
        return a + b + c;
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printMessage("【1】Проверка метода sum(int a, int b)");
        printMessage(" ");
        System.out.print("Введите первое число: ");
        int num1 = scanner.nextInt();
        System.out.print("Введите второе число: ");
        int num2 = scanner.nextInt();
        printMessage("sum(" + num1 + ", " + num2 + ") = " + sum(num1, num2));

        printMessage("\n【2】Проверка метода sum(int a, int b, int c)");
        printMessage(" ");
        System.out.print("Введите первое число: ");
        int n1 = scanner.nextInt();
        System.out.print("Введите второе число: ");
        int n2 = scanner.nextInt();
        System.out.print("Введите третье число: ");
        int n3 = scanner.nextInt();
        printMessage("sum(" + n1 + ", " + n2 + ", " + n3 + ") = " + sum(n1, n2, n3));

        printMessage("\n【3】Проверка метода isEven(int number)");

        System.out.print("Введите число для проверки: ");
        int checkNum = scanner.nextInt();
        if (isEven(checkNum)) {
            printMessage(checkNum + " - чётное число ✓");
        } else {
            printMessage(checkNum + " - нечётное число ✗");
        }
        printMessage("\n");
        printMessage(" ПРОГРАММА ЗАВЕРШЕНА!");


        scanner.close();
    }
}