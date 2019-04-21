package co.lnu.elevatorapp.floor.incoming.strategy.impl;

import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.floor.incoming.strategy.IncomingStrategy;
import co.lnu.elevatorapp.person.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderingIncomingStrategy implements IncomingStrategy {
    @Override
    public List<Person> getPeopleToComeIn(List<Person> personList, Elevator elevator) {
        if (personList.isEmpty())
            return personList;
        double peopleInElevatorAmount = elevator.getPeople().size();

        List<Person> peopleToComeIn = new ArrayList<>();
        int index = 0;
        while (peopleInElevatorAmount + peopleToComeIn.size() < elevator.getMaxCapacity()
                && peopleToComeIn.size() < personList.size()) {
            peopleToComeIn.add(personList.get(index++));
        }

        return peopleToComeIn;
    }
}