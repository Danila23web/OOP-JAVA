package Practic7;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        System.out.println(" ТРАНСПОРТНЫЕ СРЕДСТВА");
        System.out.println();

        System.out.println("Создание автомобиля");
        System.out.print("Введите марку автомобиля: ");
        String carBrand = scanner.nextLine();
        System.out.print("Введите год выпуска: ");
        int carYear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите количество дверей: ");
        int doors = scanner.nextInt();
        scanner.nextLine();

        Car car = new Car(carBrand, carYear, doors);
        vehicles.add(car);

        System.out.println();
        System.out.println("Создание мотоцикла");
        System.out.print("Введите марку мотоцикла: ");
        String bikeBrand = scanner.nextLine();
        System.out.print("Введите год выпуска: ");
        int bikeYear = scanner.nextInt();
        scanner.nextLine();

        Motorcycle motorcycle = new Motorcycle(bikeBrand, bikeYear);
        vehicles.add(motorcycle);

        System.out.println();
        System.out.println("ИНФОРМАЦИЯ О ТРАНСПОРТНЫХ СРЕДСТВАХ");
        System.out.println();

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            if (v instanceof Car) {
                System.out.println("Автомобиль " + (i + 1) + ":");
            } else if (v instanceof Motorcycle) {
                System.out.println("Мотоцикл " + (i + 1) + ":");
            }
            v.displayInfo();
            System.out.println();
        }

        System.out.println("ЗАПУСК ТРАНСПОРТНЫХ СРЕДСТВ");
        System.out.println();

        for (Vehicle v : vehicles) {
            v.start();
        }

        System.out.println();
        System.out.println("ПОЛИМОРФИЗМ");
        System.out.println();

        Vehicle[] vehicleArray = {
                new Car("Toyota Camry", 2020, 4),
                new Motorcycle("Harley Davidson", 2018),
                new Car("BMW X5", 2022, 5),
                new Motorcycle("Yamaha R1", 2021)
        };

        System.out.println("Массив транспортных средств:");
        for (int i = 0; i < vehicleArray.length; i++) {
            System.out.println();
            System.out.println("Элемент " + (i + 1) + ":");
            vehicleArray[i].displayInfo();
            System.out.print("Запуск: ");
            vehicleArray[i].start();
        }

        System.out.println();
        System.out.println("ДОПОЛНИТЕЛЬНЫЕ МЕТОДЫ");
        System.out.println();

        System.out.println("Геттеры и сеттеры для автомобиля:");
        System.out.println("Марка: " + car.getBrand());
        System.out.println("Год: " + car.getYear());
        System.out.println("Двери: " + car.getNumberOfDoors());

        System.out.println();
        System.out.println("Изменение данных автомобиля через сеттеры...");
        car.setBrand("Tesla Model 3");
        car.setYear(2023);
        car.setNumberOfDoors(4);

        System.out.println("Обновленная информация об автомобиле:");
        car.displayInfo();

        System.out.println();
        System.out.println("ПРОВЕРКА ТИПОВ ТРАНСПОРТНЫХ СРЕДСТВ");
        System.out.println();

        for (Vehicle v : vehicles) {
            if (v instanceof Car) {
                Car c = (Car) v;
                System.out.println(c.getBrand() + " - это автомобиль с " + c.getNumberOfDoors() + " дверями");
            } else if (v instanceof Motorcycle) {
                Motorcycle m = (Motorcycle) v;
                System.out.println(m.getBrand() + " - это мотоцикл");
            } else {
                System.out.println(v.getBrand() + " - неизвестный тип");
            }
        }

        System.out.println();
        System.out.println("РАБОТА ПРОГРАММЫ ЗАВЕРШЕНА!");

        scanner.close();
    }
}
