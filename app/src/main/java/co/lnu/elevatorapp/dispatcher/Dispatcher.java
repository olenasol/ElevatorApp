package co.lnu.elevatorapp.dispatcher;

import co.lnu.elevatorapp.builder.Building;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.elevator.ElevatorState;
import co.lnu.elevatorapp.elevator.MovingDirection;
import co.lnu.elevatorapp.person.Person;
import co.lnu.elevatorapp.ui.elevator_simultion.ElevatorSimulationPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher {
    private Building building;
    ExecutorService service;
    private ElevatorSimulationPresenter presenter;

    public Dispatcher(Building building, ElevatorSimulationPresenter presenter) {
        this.building = building;
        this.presenter = presenter;
        service = Executors.newFixedThreadPool(building.getElevatorList().size());
    }

    public void startMoving(int elevatorId) {
        Elevator elevator = building.getElevatorList().stream()
                .filter(el -> el.getElevatorId() == elevatorId).findAny().orElse(null);
        if (elevator != null) {
            elevator.setElevatorState(ElevatorState.MOVE);
            service.submit(elevator::move);
        }
    }

    public void moveToFloor(int elevatorId, int floor, int duration) {
        //TODO 4/19/2019 uncomment
        presenter.moveToFloor(elevatorId, floor, duration);
    }

    public void openDoor(int elevatorId) {

    }

    public void closeDoor(int elevatorId) {

    }

    public void addPeopleToFloor(Person person) {
        final List<Person> personListOnFloor = building.getFloorList().get(person.getFloorId()).getPeople();
        personListOnFloor.add(person);
        presenter.notifyFloor(person.getFloorId());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (personListOnFloor.size() == 1) {
            addElevatorCall(person);
        }
    }

    private void addElevatorCall(Person person) {
        List<Elevator> elevators = getFreeElevators();
        Elevator optimalElevator = null;
        optimalElevator = getOptimalElevator(person.getFloorId(), elevators);
        optimalElevator.addOrder(person.getFloorId());
        if (!optimalElevator.getElevatorState().equals(ElevatorState.MOVE)) {
            optimalElevator.setElevatorState(ElevatorState.MOVE);
            startMoving(optimalElevator.getElevatorId());
        }
    }

    private List<Elevator> getFreeElevators() {
        List<Elevator> freeElevators = new ArrayList<>();
        for (Elevator elevator : building.getElevatorList()) {
            if (elevator.getElevatorState().equals(ElevatorState.FREE)) {
                freeElevators.add(elevator);
            }
        }
        return freeElevators;
    }

    private Elevator getOptimalElevator(int floorId, List<Elevator> elevators) {
        int minDifference = building.getFloorList().size();
        if (elevators.isEmpty()) {
            elevators = building.getElevatorList();
        }
        Elevator optimalElevator = elevators.get(0);
        for (Elevator elevator : elevators) {
            int diff = Math.abs(elevator.getCurrentFloor() - floorId);
            if (diff < minDifference) {
                minDifference = diff;
                optimalElevator = elevator;
            }
        }
        return optimalElevator;
    }

    public synchronized void transferFromFloorToElevator(int floorId, Elevator elevator) {
        List<Person> peopleFrom = building.getFloorList().get(floorId).getPeopleToComeIn(elevator);
        if (!peopleFrom.isEmpty()) {
            addToList(peopleFrom, elevator.getPeople(), elevator);
            building.getFloorList().get(floorId).getPeople().removeAll(peopleFrom);
            presenter.notifyFloor(floorId);
            presenter.notifyElevator(elevator.getElevatorId());
        }
    }

    private void addToList(List<Person> peopleFrom, List<Person> peopleTo, Elevator elevator) {
//        peopleTo.addAll(peopleFrom);
        for(Person person: peopleFrom){
            peopleTo.add(person);
            elevator.addIntendedFloor(person.getIntendedFloor());
        }
    }

    public synchronized void transferFromElevator(Elevator elevator) {
        for (Person person : elevator.getPeople()) {
            if (person.getIntendedFloor() == elevator.getCurrentFloor()) {
                elevator.getPeople().remove(person);
            }
        }
        presenter.notifyElevator(elevator.getElevatorId());
    }
}
