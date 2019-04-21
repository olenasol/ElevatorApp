package co.lnu.elevatorapp.elevator.moving.strategy.impl;

import co.lnu.elevatorapp.dispatcher.Dispatcher;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.elevator.ElevatorState;
import co.lnu.elevatorapp.elevator.MovingDirection;
import co.lnu.elevatorapp.elevator.moving.strategy.MovingStrategy;

import java.util.Collections;

public class WithStopElevatorStrategy implements MovingStrategy {

    @Override
    public void move(Elevator elevator, Dispatcher dispatcher) {
        int elevatorId = elevator.getElevatorId();
        Integer currentFloor = elevator.getCurrentFloor();
        Integer finalFloor = 0;
        if (elevator.getElevatorState().equals(ElevatorState.MOVE)) {
            finalFloor = elevator.getOrders().get(0);
        }
        do {
            stopMovingIfIsOrder(elevator.getCurrentFloor(), elevator, dispatcher, elevator.getElevatorState());
            if (elevator.getOrders().get(0).equals(elevator.getCurrentFloor())) {
                elevator.getOrders().remove(0);
                break;
            }
            if(currentFloor < finalFloor) {
                elevator.setCurrentFloor(++currentFloor);
            } else {
                elevator.setCurrentFloor(--currentFloor);
            }
//            dispatcher.moveToFloor(elevatorId, currentFloor);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!currentFloor.equals(finalFloor));
        if (elevator.getOrders().isEmpty()) {
            elevator.setElevatorState(ElevatorState.FREE);
        } else {
            move(elevator, dispatcher);
        }
    }

    private void stopMovingIfIsOrder(int floorId, Elevator elevator, Dispatcher dispatcher, ElevatorState elevatorState) {
        if (elevator.getOrders().get(0).equals(floorId)) {
            elevator.setElevatorState(ElevatorState.STOP);
            dispatcher.transferFromFloorToElevator(floorId, elevator);
            dispatcher.transferFromElevator(elevator);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            elevator.setElevatorState(elevatorState);
        }
    }

    @Override
    public boolean receiveOrder(int floorId, Elevator elevator) {
        int currentFloot = elevator.getCurrentFloor();
        if(Math.abs(floorId - currentFloot) <= Math.abs(elevator.getOrders().get(0) - currentFloot)){
            elevator.getOrders().add(0, floorId);
        } else {

        }
        return false;
    }

    @Override
    public void addIntendedFloor(int floorId, Elevator elevator) {

    }
}
