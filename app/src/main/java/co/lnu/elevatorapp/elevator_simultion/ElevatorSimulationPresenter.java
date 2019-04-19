package co.lnu.elevatorapp.elevator_simultion;

import co.lnu.elevatorapp.Elevator;
import co.lnu.elevatorapp.Floor;

import java.util.List;

public interface ElevatorSimulationPresenter {
    List<Floor> getListOfFloors();

    void setFloorHeight(int height);

    float moveToFloorYDelta(int intendedFloor);

    int getFloorHeight();

    void onViewCreated(int floorNumber, int elevatorNumber);

    List<Elevator> getListOfElevators();
}
