package co.lnu.elevatorapp.builder.component.builder.impl;


import co.lnu.elevatorapp.builder.component.builder.FloorBuilder;
import co.lnu.elevatorapp.floor.Floor;
import co.lnu.elevatorapp.floor.incoming.strategy.IncomingStrategy;

public class FloorBuilderImpl implements FloorBuilder {

    private Floor floor;

    @Override
    public FloorBuilder createNewFloor() {
        floor = new Floor();
        return this;
    }

    @Override
    public FloorBuilder setFloorId(int number) {
        floor.setFloorId(number);
        return this;
    }

    @Override
    public FloorBuilder setIncomingStrategy(IncomingStrategy incomingStrategy) {
        floor.setIncomingStrategy(incomingStrategy);
        return this;
    }

    @Override
    public Floor build() {
        return floor;
    }
}
