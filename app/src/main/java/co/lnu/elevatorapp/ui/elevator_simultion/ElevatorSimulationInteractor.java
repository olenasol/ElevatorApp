package co.lnu.elevatorapp.ui.elevator_simultion;

import co.lnu.elevatorapp.builder.Building;

public interface ElevatorSimulationInteractor {

    Building getBuilding(int floorNumber, int elevatorNumber);

    void getPerson();
}
