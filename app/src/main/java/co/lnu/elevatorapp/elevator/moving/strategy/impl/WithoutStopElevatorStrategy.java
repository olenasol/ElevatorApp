package co.lnu.elevatorapp.elevator.moving.strategy.impl;

import co.lnu.elevatorapp.dispatcher.Dispatcher;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.elevator.MovingDirection;
import co.lnu.elevatorapp.elevator.moving.strategy.MovingStrategy;

public class WithoutStopElevatorStrategy implements MovingStrategy {
    @Override
    public void move(Elevator elevator, Dispatcher dispatcher, MovingDirection direction) {

    }
}
