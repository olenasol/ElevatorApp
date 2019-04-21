package co.lnu.elevatorapp.elevator.moving.strategy.impl;

import co.lnu.elevatorapp.dispatcher.Dispatcher;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.elevator.ElevatorState;
import co.lnu.elevatorapp.elevator.moving.strategy.MovingStrategy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class WithoutStopElevatorStrategy implements MovingStrategy {

    @Override
    public void move(Elevator elevator, Dispatcher dispatcher) {
        int elevatorId = elevator.getElevatorId();
        while (true) {
            if (elevator.getElevatorState().equals(ElevatorState.MOVE)) {
                stopMovingIfIsOrder(elevator.getCurrentFloor(), elevator, dispatcher, elevator.getElevatorState());
                if (elevator.getOrders().isEmpty()) {
                    elevator.setElevatorState(ElevatorState.FREE);
                    System.out.println(elevatorId + " before: " + elevator.getCurrentFloor() + " " + elevator.getElevatorState());
                    dispatcher.notifyAboutFreeState(elevator);
                    System.out.println(elevatorId + " after: " + elevator.getCurrentFloor() + " " + elevator.getElevatorState());
                }
                int duration = 0;
                List<Integer> orders = elevator.getOrders();
                if (!orders.isEmpty()) {
                    duration = Math.abs(elevator.getCurrentFloor() - elevator.getOrders().get(0)) * 1000;
                    System.out.println(elevatorId + ": " + elevator.getCurrentFloor() + " " + elevator.getElevatorState());
                    dispatcher.moveToFloor(elevatorId, orders.get(0), duration);
                    elevator.setCurrentFloor(elevator.getOrders().get(0));
                }
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void stopMovingIfIsOrder(int floorId, Elevator elevator, Dispatcher dispatcher, ElevatorState elevatorState) {
        List<Integer> orders = elevator.getOrders();
        if (!orders.isEmpty() && orders.get(0).equals(floorId)) {
            elevator.setElevatorState(ElevatorState.STOP);
            orders.remove(0);
            dispatcher.transferFromFloorToElevator(floorId, elevator);
            dispatcher.transferFromElevator(elevator);
            elevator.setElevatorState(elevatorState);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean receiveOrder(int floorId, Elevator elevator) {
        boolean received = false;
        if (elevator.getElevatorState().equals(ElevatorState.FREE)) {
            elevator.getOrders().add(floorId);
            received = true;
        }
        return received;
    }

    @Override
    public void addIntendedFloor(int floorId, Elevator elevator) {
        List<Integer> orders = elevator.getOrders();
        if (orders.isEmpty() || !orders.contains(floorId)) {
            orders.add(floorId);
            orders.sort(Comparator.comparingInt(o -> getOrder(elevator.getCurrentFloor(), orders) * o));
        }
    }

    private int getOrder(int currentFloor, List<Integer> orders) {
        int max = orders.stream().max(Comparator.comparingInt(o -> o)).get();
        int min = orders.stream().min(Comparator.comparingInt(o -> o)).get();
        return  Math.abs(max - currentFloor) - Math.abs(min - currentFloor);
    }
}
