package co.lnu.elevatorapp.builder.component.builder;

import co.lnu.elevatorapp.dispatcher.Dispatcher;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.elevator.ElevatorState;
import co.lnu.elevatorapp.elevator.moving.strategy.MovingStrategy;

public interface ElevatorBuilder {
    ElevatorBuilder createNewElevator();

    ElevatorBuilder setElevatorId(int elevatorId);

    ElevatorBuilder setDispatcher(Dispatcher dispatcher);

    ElevatorBuilder setMaxWeight(int maxWeight);

    ElevatorBuilder setElevatorState(ElevatorState elevatorState);

    ElevatorBuilder setMovingStrategy(MovingStrategy movingStrategy);

    Elevator build();
}
