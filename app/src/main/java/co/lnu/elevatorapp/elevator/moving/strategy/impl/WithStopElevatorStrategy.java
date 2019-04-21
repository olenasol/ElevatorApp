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
//        int finalFloor = 0;
//        if (elevator.getElevatorState().equals(ElevatorState.MOVE)) {
//            finalFloor = Collections.max(elevator.getOrders());
//        } else if (elevator.getElevatorState().equals(ElevatorState.MOVE_DOWN)) {
//            finalFloor = Collections.min(elevator.getOrders());
//        }
        Integer currentFloor = elevator.getCurrentFloor();
//        for (int i = currentFloor; i <= elevator.getOrders().size() - 1; i++, currentFloor++) {
        for (Integer floor : elevator.getOrders()) {
            dispatcher.moveToFloor(elevatorId, currentFloor);
            stopMovingIfIsOrder(currentFloor, elevator, dispatcher, elevator.getElevatorState());
//            elevator.getOrders().remove(currentFloor);
            System.out.println(elevatorId + " " + currentFloor);
            currentFloor++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        elevator.setElevatorState(ElevatorState.FREE);
    }

    private void stopMovingIfIsOrder(int floorId, Elevator elevator, Dispatcher dispatcher, ElevatorState elevatorState) {
        if (elevator.getOrders().contains(floorId)) {
            elevator.setElevatorState(ElevatorState.STOP);
            dispatcher.transferFromFloorToElevator(floorId, elevator);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            elevator.setElevatorState(elevatorState);
        }
    }

    @Override
    public void addOrder(int floorId, Elevator elevator) {
        int currentFloot = elevator.getCurrentFloor();
        if(Math.abs(floorId - currentFloot) <= Math.abs(elevator.getOrders().get(0) - currentFloot)){
            elevator.getOrders().add(0, floorId);
        } else {

        }
    }
}
