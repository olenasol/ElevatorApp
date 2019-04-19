package co.lnu.elevatorapp.builder.component.builder;

import co.lnu.elevatorapp.floor.Floor;
import co.lnu.elevatorapp.floor.incoming.strategy.IncomingStrategy;

public interface FloorBuilder {
    FloorBuilder createNewFloor();

    FloorBuilder setFloorId(int number);

    FloorBuilder setIncomingStrategy(IncomingStrategy incomingStrategy);

    Floor build();
}
