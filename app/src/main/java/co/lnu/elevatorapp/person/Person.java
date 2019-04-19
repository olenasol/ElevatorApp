package co.lnu.elevatorapp.person;

public class Person  {
    private int floorNumber;
    private int intendedFloor;
    private double weight;

    public Person(int floorNumber, int intendedFloor, double weight) {
        this.floorNumber = floorNumber;
        this.intendedFloor = intendedFloor;
        this.weight = weight;

    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getIntendedFloor() {
        return intendedFloor;
    }

    public double getWeight() {
        return weight;
    }
}