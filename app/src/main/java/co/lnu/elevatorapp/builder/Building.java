package co.lnu.elevatorapp.builder;


import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.floor.Floor;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private List<Floor> floorList;
    private List<Elevator> elevatorList;

    public Building() {
        floorList = new ArrayList<>();
        elevatorList = new ArrayList<>();
    }

    public void setFloor(Floor floor) {
        floorList.add(floor);
    }

    public void setElevator(Elevator elevator) {
        elevatorList.add(elevator);
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }

    public void setElevatorList(List<Elevator> elevatorList) {
        this.elevatorList = elevatorList;
    }

    public List<Floor> getFloorList() {
        return floorList;
    }

    public List<Elevator> getElevatorList() {
        return elevatorList;
    }
}
