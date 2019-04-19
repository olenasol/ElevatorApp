package co.lnu.elevatorapp.person;


import co.lnu.elevatorapp.builder.Building;
import co.lnu.elevatorapp.dispatcher.Dispatcher;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 15.04.2019.
 */
public class PersonGenerator {
    private Building building;
    private Dispatcher dispatcher;
    private static final double MAX_PERSON_WEIGHT = 100;
    private static final double MIN_PERSON_WEIGHT = 15;
    private static final int TIMEOUT_DELAY = 15;

    public PersonGenerator(Building building, Dispatcher dispatcher) {
        this.building = building;
        this.dispatcher = dispatcher;
    }

    public void startGenerating() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            generatePerson();
            building.getFloorList().forEach(f ->
                    f.getPeople().forEach(p -> System.out.println(p.getFloorNumber())));
        }, 0, TIMEOUT_DELAY, TimeUnit.SECONDS);
    }


    //returns randomly generated floor number
    public int generatePerson() {
        System.out.println("Generate Person");
        int floorNumber = ThreadLocalRandom.current().nextInt(1, building.getFloorList().size());
        int floorNumberToGo = floorNumber;
        do {
            floorNumberToGo = ThreadLocalRandom.current().nextInt(building.getFloorList().size());
        } while (floorNumber == floorNumberToGo);

        double weight = ThreadLocalRandom.current().nextDouble(MIN_PERSON_WEIGHT, MAX_PERSON_WEIGHT);

        building.getFloorList().get(floorNumber).getPeople().add(new Person(floorNumber, floorNumberToGo, weight));

//        building.getFloorList().get(floorNumber).getIncomingStrategy().reorderQueue(
//                building.getFloorList().get(floorNumber).getPeople()
//        );

       return floorNumber;
    }
}
