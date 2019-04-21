package co.lnu.elevatorapp.floor;

import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.floor.incoming.strategy.IncomingStrategy;
import co.lnu.elevatorapp.person.Person;

import java.util.ArrayList;
import java.util.List;


public class Floor {
    private int floorId;
    private List<Person> personList;
    private IncomingStrategy incomingStrategy;


    public Floor() {
        personList = new ArrayList<>();
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public IncomingStrategy getIncomingStrategy() {
        return incomingStrategy;
    }

    public void setIncomingStrategy(IncomingStrategy incomingStrategy) {
        this.incomingStrategy = incomingStrategy;
    }

    public List<Person> getPeople() {
        return personList;
    }

    public void setPerson(Person person) {
        personList.add(person);
    }

    public List<Person> getPeopleToComeIn(Elevator elevator){
        return incomingStrategy.getPeopleToComeIn(personList, elevator);
    }
}
