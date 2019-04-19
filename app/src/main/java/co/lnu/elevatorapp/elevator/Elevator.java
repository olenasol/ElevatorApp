package co.lnu.elevatorapp.elevator;

import co.lnu.elevatorapp.dispatcher.Dispatcher;
import co.lnu.elevatorapp.elevator.moving.strategy.MovingStrategy;
import co.lnu.elevatorapp.person.Person;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    public static final int START_FLOOR = 1;

    // set of colls max min
    //current floor++ run
//    cols for elevator

    protected int elevatorId;
    protected Integer currentFloor;
    protected double maxWeight;
    protected ElevatorState elevatorState;
    protected List<Person> people;
    protected List<Integer> orders;
    protected Dispatcher dispatcher;
    private MovingStrategy movingStrategy;

    public Elevator() {
        elevatorState = ElevatorState.STOP;
        orders = new ArrayList<>();
        currentFloor = START_FLOOR;
        people = new ArrayList<>();
    }

    public void setElevatorId(int elevatorId) {
        this.elevatorId = elevatorId;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public Integer getElevatorId() {
        return elevatorId;
    }

    public MovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

    public void setMovingStrategy(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<Integer> getOrders() {
        return orders;
    }

    public final void closeDoor() {
        dispatcher.closeDoor(elevatorId);
    }

    public final void openDoor() {
        dispatcher.openDoor(elevatorId);
    }

    public void move(MovingDirection direction) {
        movingStrategy.move(this, dispatcher, direction);
    }

    ;

//    public abstract void addOrder(int floorId);
}
