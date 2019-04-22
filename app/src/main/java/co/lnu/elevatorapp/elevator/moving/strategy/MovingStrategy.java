package co.lnu.elevatorapp.elevator.moving.strategy;

import co.lnu.elevatorapp.dispatcher.Dispatcher;
import co.lnu.elevatorapp.elevator.Elevator;

public interface MovingStrategy {
    void move(Elevator elevator, Dispatcher dispatcher);

    boolean canReceiveOrder(int floorId, Elevator elevator);

    void addIntendedFloor(int floorId, Elevator elevator);
}
