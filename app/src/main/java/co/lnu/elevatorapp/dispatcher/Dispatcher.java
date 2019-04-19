package co.lnu.elevatorapp.dispatcher;

import co.lnu.elevatorapp.builder.Building;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.elevator.ElevatorState;
import co.lnu.elevatorapp.elevator.MovingDirection;
import co.lnu.elevatorapp.mediator.Presenter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Dispatcher {
    private Presenter presenter;
    private Building building;
    ExecutorService service;

    public Dispatcher(Building building, Presenter presenter) {
        this.building = building;
        this.presenter = presenter;
        service = Executors.newFixedThreadPool(building.getElevatorList().size());
    }

    public void startMoving(int elevatorId, MovingDirection direction) {
        Elevator elevator = building.getElevatorList().stream()
                .filter(el -> el.getElevatorId() == elevatorId).findAny().orElse(null);
        ElevatorState state = direction.equals(MovingDirection.UP) ? ElevatorState.MOVE_UP : ElevatorState.MOVE_DOWN;
        if (elevator != null) {
            elevator.setElevatorState(state);
            ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutor.scheduleAtFixedRate(() -> elevator.move(direction), 0, 2, TimeUnit.SECONDS);
        }
    }

    public void startMovingView(int elevatorId, MovingDirection direction) {
        //TODO 4/19/2019 uncomment
       // presenter.startMoving(elevatorId, direction);
    }

    public void stopMovingView(int elevatorId) {
        //TODO 4/19/2019 uncomment
        //       presenter.stopMoving(elevatorId);
    }

    public void stopMoving(int elevatorId) {
    }

    public void returnFloor(int elevatorId, int floor) {
        //TODO 4/19/2019 uncomment
        //presenter.returnFloor(elevatorId, floor);
    }

    public void openDoor(int elevatorId) {

    }

    public void closeDoor(int elevatorId) {

    }

    public void removePeopleFromElevator(List<Integer> peopleIds, int elevatorId) {

    }

    public void addPeopleToElevatorOnFloor(List<Integer> peopleIds, int floorId) {

    }

    public void addFloorToQueue(int elevatorId, int floorId) {

    }

    public void addPeopleToFloor(List<Integer> peopleIds, int floorId) {

    }
}
