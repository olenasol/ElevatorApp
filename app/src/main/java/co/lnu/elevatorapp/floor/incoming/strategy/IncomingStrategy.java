package co.lnu.elevatorapp.floor.incoming.strategy;


import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.person.Person;

import java.util.List;

public interface IncomingStrategy {
    List<Person> getPeopleToComeIn(List<Person> personList, Elevator elevator);
}
