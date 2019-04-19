package co.lnu.elevatorapp.elevator.moving.strategy.impl;

import co.lnu.elevatorapp.dispatcher.Dispatcher;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.elevator.ElevatorState;
import co.lnu.elevatorapp.elevator.MovingDirection;
import co.lnu.elevatorapp.elevator.moving.strategy.MovingStrategy;

import java.util.Collections;

public class WithStopElevatorStrategy implements MovingStrategy {

    @Override
    public void move(Elevator elevator, Dispatcher dispatcher, MovingDirection direction) {
        int elevatorId = elevator.getElevatorId();
        int finalFloor = 0;
        if (elevator.getElevatorState().equals(ElevatorState.MOVE_UP)) {
            finalFloor = Collections.max(elevator.getOrders());
        } else if (elevator.getElevatorState().equals(ElevatorState.MOVE_DOWN)) {
            finalFloor = Collections.min(elevator.getOrders());
        }
        Integer currentFloor = elevator.getCurrentFloor();
        for (int i = currentFloor; i <= finalFloor; i++, currentFloor++) {
            dispatcher.returnFloor(elevatorId, currentFloor);
//            stopMovingIfIsOrder(currentFloor, elevator, dispatcher, direction);
//            elevator.getOrders().remove(currentFloor);
            System.out.println(elevatorId + " " + currentFloor);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        elevator.setElevatorState(ElevatorState.STOP);
    }

    private void stopMovingIfIsOrder(int floorId, Elevator elevator, Dispatcher dispatcher, MovingDirection direction) {
        if (elevator.getOrders().contains(floorId)) {
            dispatcher.openDoor(elevator.getElevatorId());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            dispatcher.startMovingView(elevator.getElevatorId(), direction);
        }
    }

    //    @Override
    public void addOrder(int floorId) {
//        if (elevatorState.equals(ElevatorState.MOVE_UP) && currentFloor <= floorId) {
//            orders.add(floorId);
//            orders.sort(Comparator.comparingInt(o -> o));
//        } else if (elevatorState.equals(ElevatorState.MOVE_DOWN) && currentFloor >= floorId) {
//            orders.sort(Comparator.comparingInt(o -> -o));
//            orders.add(floorId);
//        }
    }
}
