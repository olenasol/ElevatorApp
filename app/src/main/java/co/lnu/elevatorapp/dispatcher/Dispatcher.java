package co.lnu.elevatorapp.dispatcher;

import co.lnu.elevatorapp.builder.Building;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.elevator.ElevatorState;
import co.lnu.elevatorapp.person.Person;
import co.lnu.elevatorapp.ui.elevator_simultion.ElevatorSimulationPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher {
    private Building building;
    private ExecutorService service;
    private ElevatorSimulationPresenter presenter;
    private List<Integer> waitingFloors;

    public Dispatcher(Building building, ElevatorSimulationPresenter presenter) {
        this.building = building;
        this.presenter = presenter;
        waitingFloors = new ArrayList<>();
    }

    public void createThreads() {
        service = Executors.newFixedThreadPool(building.getElevatorList().size());
        for (Elevator elevator : building.getElevatorList()) {
            service.submit(elevator::move);
        }
    }

    public void moveToFloor(int elevatorId, int floor, int duration) {
        presenter.moveToFloor(elevatorId, floor, duration);
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
//        if (!waitingFloors.contains(person.getFloorId())) {
//            addElevatorCall(person);
//        }
        if (personListOnFloor.size() == 1) {
            addElevatorCall(person);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addElevatorCall(Person person) {
        List<Elevator> elevators = getFreeElevators();
        Elevator optimalElevator;
        optimalElevator = getOptimalElevator(person.getFloorId(), elevators);
        if (optimalElevator == null) {
            waitingFloors.add(person.getFloorId());
        } else {
            optimalElevator.getOrders().add(person.getFloorId());
            optimalElevator.setElevatorState(ElevatorState.MOVE);
//            startMoving(optimalElevator.getElevatorId());
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
        Elevator optimalElevator = null;
        for (Elevator elevator : elevators) {
            int diff = Math.abs(elevator.getCurrentFloor() - floorId);
            if (diff < minDifference && elevator.canReceiveOrder(floorId)) {
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
        if (building.getFloorList().get(floorId).getPeople().isEmpty()) {
            waitingFloors.remove((Integer) floorId);
        }
    }

    private void addToList(List<Person> peopleFrom, List<Person> peopleTo, Elevator elevator) {
        for (Person person : peopleFrom) {
            peopleTo.add(person);
            elevator.addIntendedFloor(person.getIntendedFloor());
        }
    }

    public void transferFromElevator(Elevator elevator) {
        List<Person> peopleToDelete = new ArrayList<>();
        for (Person person : elevator.getPeople()) {
            if (person.getIntendedFloor() == elevator.getCurrentFloor()) {
                peopleToDelete.add(person);
            }
        }
        elevator.getPeople().removeAll(peopleToDelete);
        elevator.getOrders().remove(elevator.getCurrentFloor());
        presenter.notifyElevator(elevator.getElevatorId());
    }

    public void notifyAboutFreeState(Elevator elevator) {
        if (!waitingFloors.isEmpty()) {
            elevator.getOrders().add(waitingFloors.get(0));
            waitingFloors.remove(0);
            elevator.setElevatorState(ElevatorState.MOVE);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
