package co.lnu.elevatorapp.floor.incoming.strategy.impl;

import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.floor.incoming.strategy.IncomingStrategy;
import co.lnu.elevatorapp.person.Person;

import java.util.List;

public class RandomIncomingStrategy implements IncomingStrategy {
    @Override
    public List<Person> getPeopleToComeIn(List<Person> personList, Elevator elevator) {
        return null;
    }
}
