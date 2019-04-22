package co.lnu.elevatorapp.ui.elevator_simultion;

import co.lnu.elevatorapp.builder.Building;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.floor.Floor;
import co.lnu.elevatorapp.person.Person;

import java.util.List;

public interface ElevatorSimulationPresenter {

    void setFloorHeight(int height);

    float moveToFloorYDelta(int intendedFloor);

    int getFloorHeight();

    void onViewCreated(int floorNumber, int elevatorNumber);

    void notifyFloor(int floorId);

    void notifyElevator(int elevatorId);

    void moveToFloor(int elevatorId, int floorId, int duration);
}
