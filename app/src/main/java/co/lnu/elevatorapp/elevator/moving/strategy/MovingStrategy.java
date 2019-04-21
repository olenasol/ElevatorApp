package co.lnu.elevatorapp.elevator.moving.strategy;

import co.lnu.elevatorapp.dispatcher.Dispatcher;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.elevator.MovingDirection;

public interface MovingStrategy {
    void move(Elevator elevator, Dispatcher dispatcher);

    void addOrder(int floorId, Elevator elevator);

    void addIntendedFloor(int floorId, Elevator elevator);
}
