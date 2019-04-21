package co.lnu.elevatorapp.floor.incoming.strategy.impl;

import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.floor.incoming.strategy.IncomingStrategy;
import co.lnu.elevatorapp.person.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomIncomingStrategy implements IncomingStrategy {
    @Override
    public List<Person> getPeopleToComeIn(List<Person> personList, Elevator elevator) {
        if (personList.isEmpty())
            return personList;

        double peopleInElevatorAmount = elevator.getPeople().size();

        List<Person> peopleToComeIn = new ArrayList<>();
        List<Integer> peopleToComeInIndexes = IntStream.range(0, personList.size())
                .boxed().collect(Collectors.toList());

        while (peopleInElevatorAmount + peopleToComeIn.size() < elevator.getMaxCapacity()
                && peopleToComeIn.size() < personList.size()) {
            final int index = ThreadLocalRandom.current().nextInt(0, peopleToComeInIndexes.size());

            peopleToComeIn.add(personList.get(peopleToComeInIndexes.get(index)));
            peopleToComeInIndexes.remove(index);
        }

        return peopleToComeIn;
    }
}