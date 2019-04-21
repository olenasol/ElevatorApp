package co.lnu.elevatorapp.person;

public class Person  {
    private int floorId;
    private int intendedFloor;
    private Color color;

    public enum Color {
        GREEN,
        BLUE,
        YELLOW,
        RED
    }

    public Person(int floorId, int intendedFloor,Color color) {
        this.floorId = floorId;
        this.intendedFloor = intendedFloor;
        this.color = color;
    }

    public Color getColor(){return color;}

    public int getFloorId() {
        return floorId;
    }

    public int getIntendedFloor() {
        return intendedFloor;
    }
}