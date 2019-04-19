package co.lnu.elevatorapp.builder;

import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.floor.Floor;

public interface BuildingBuilder {
    BuildingBuilder createNewBuilding();

    BuildingBuilder setFloor(Floor floor);

    BuildingBuilder setElevator(Elevator elevator);

    Building build();
}
