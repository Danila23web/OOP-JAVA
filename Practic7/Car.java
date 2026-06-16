package Practic7;

public class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String brand, int year, int numberOfDoors) {
        super(brand, year);
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public void start() {
        System.out.println("Автомобиль " + brand + " заведён");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Количество дверей: " + numberOfDoors);
    }
}