package co.lnu.elevatorapp.person;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import co.lnu.elevatorapp.dispatcher.Dispatcher;

/**
 * Created by User on 15.04.2019.
 */
public class PersonGenerator {
    private Dispatcher dispatcher;
    private static final int TIMEOUT_DELAY = 15;
    private int count = 0;

    private int buildingHeight;

    public PersonGenerator(Dispatcher dispatcher, int buildingHeight) {
        this.dispatcher = dispatcher;
        this.buildingHeight = buildingHeight;
    }

    public void startGenerating() {
        ScheduledExecutorService scheduledThreadPool = Executors.newSingleThreadScheduledExecutor();
        scheduledThreadPool.scheduleAtFixedRate(this::generatePerson, 1, TIMEOUT_DELAY, TimeUnit.SECONDS);
    }

    public void generatePerson() {
//        if(count <= 0) {
        count++;
        int floorNumber = ThreadLocalRandom.current().nextInt(buildingHeight);
        int floorNumberToGo;
        do {
            floorNumberToGo = ThreadLocalRandom.current().nextInt(buildingHeight);
        } while (floorNumber == floorNumberToGo);

        dispatcher.addPeopleToFloor(new Person(floorNumber, floorNumberToGo));
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
