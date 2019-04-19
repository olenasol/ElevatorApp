package co.lnu.elevatorapp.builder.impl;

import co.lnu.elevatorapp.builder.Building;
import co.lnu.elevatorapp.builder.BuildingBuilder;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.floor.Floor;

public class BuildingBuilderImpl implements BuildingBuilder {
    private Building building;

    @Override
    public BuildingBuilder createNewBuilding() {
        building = new Building();
        return this;
    }

    @Override
    public BuildingBuilder setFloor(Floor floor) {
        building.setFloor(floor);
        return this;
    }

    @Override
    public BuildingBuilder setElevator(Elevator elevator) {
        building.setElevator(elevator);
        return this;
    }

    @Override
    public Building build() {
        return building;
    }
}