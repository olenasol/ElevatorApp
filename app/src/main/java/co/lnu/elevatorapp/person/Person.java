package co.lnu.elevatorapp.person;

public class Person  {
    private int floorId;
    private int intendedFloor;

    public Person(int floorId, int intendedFloor) {
        this.floorId = floorId;
        this.intendedFloor = intendedFloor;
    }

    public int getFloorId() {
        return floorId;
    }

    public int getIntendedFloor() {
        return intendedFloor;
    }
}