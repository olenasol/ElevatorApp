package co.lnu.elevatorapp.floor.incoming.strategy;


import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.person.Person;

import java.util.List;

public interface IncomingStrategy {
    void reorderQueue(List<Person> personList, Elevator elevator);

}
