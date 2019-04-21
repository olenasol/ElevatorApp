package co.lnu.elevatorapp.elevator.moving.strategy.impl;

import co.lnu.elevatorapp.dispatcher.Dispatcher;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.elevator.ElevatorState;
import co.lnu.elevatorapp.elevator.moving.strategy.MovingStrategy;

import java.util.List;

public class WithoutStopElevatorStrategy implements MovingStrategy {

    @Override
    public void move(Elevator elevator, Dispatcher dispatcher) {
        int elevatorId = elevator.getElevatorId();
        Integer currentFloor = elevator.getCurrentFloor();
        Integer finalFloor = 0;
        if (elevator.getElevatorState().equals(ElevatorState.MOVE)) {
            finalFloor = elevator.getOrders().get(0);
        }
        boolean arrived = false;
        while (true){
            stopMovingIfIsOrder(elevator.getCurrentFloor(), elevator, dispatcher, elevator.getElevatorState());
            if (elevator.getOrders().isEmpty()) {
//                elevator.getOrders().remove(0);
                break;
            }
//            if (currentFloor < finalFloor) {
                elevator.setCurrentFloor(elevator.getOrders().get(0));
//                elevator.setCurrentFloor(++currentFloor);
//            } else {
//                elevator.setCurrentFloor(elevator.getOrders().get(0));
//                elevator.setCurrentFloor(--currentFloor);
//            }
            int duration = Math.abs(currentFloor - elevator.getOrders().get(0)) * 2000;
            dispatcher.moveToFloor(elevatorId, elevator.getOrders().get(0), duration);
//            currentFloor = elevator.getOrders().get(0);
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (elevator.getOrders().isEmpty()) {
            elevator.setElevatorState(ElevatorState.FREE);
        } else {
            move(elevator, dispatcher);
        }
    }


    private void stopMovingIfIsOrder(int floorId, Elevator elevator, Dispatcher dispatcher, ElevatorState elevatorState) {
        if (elevator.getOrders().get(0).equals(floorId)) {
            elevator.setElevatorState(ElevatorState.STOP);
            elevator.getOrders().remove(0);
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
    public void addOrder(int floorId, Elevator elevator) {
        List<Integer> orders = elevator.getOrders();
        if(orders.isEmpty() || orders.get(elevator.getOrders().size() - 1) != floorId) {
            orders.add(floorId);
        }
    }

    @Override
    public void addIntendedFloor(int floorId, Elevator elevator) {
        List<Integer> orders = elevator.getOrders();
        if(orders.isEmpty() || orders.get(0) != floorId) {
            orders.add(0, floorId);
        }
    }
}
