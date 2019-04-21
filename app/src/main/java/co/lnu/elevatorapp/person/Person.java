package co.lnu.elevatorapp.person;

public class Person  {
    private int floorId;
    private int intendedFloor;
    private double weight;

    public Person(int floorId, int intendedFloor, double weight) {
        this.floorId = floorId;
        this.intendedFloor = intendedFloor;
        this.weight = weight;

    }

    public int getFloorId() {
        return floorId;
    }

    public int getIntendedFloor() {
        return intendedFloor;
    }

    public double getWeight() {
        return weight;
    }
}