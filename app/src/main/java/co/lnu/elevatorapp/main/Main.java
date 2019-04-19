package co.lnu.elevatorapp.main;

import co.lnu.elevatorapp.builder.Building;
import co.lnu.elevatorapp.builder.BuildingDirector;
import co.lnu.elevatorapp.dispatcher.Dispatcher;
import co.lnu.elevatorapp.person.PersonGenerator;

public class Main {
    private static final int TIMEOUT_DELAY = 15;

    public static void main(String[] args) {
        BuildingDirector buildingDirector = new BuildingDirector(null);
        Building building = buildingDirector.construct(7, 4);

        Dispatcher dispatcher = new Dispatcher(building, null);
        PersonGenerator personGenerator = new PersonGenerator(building, dispatcher);


//            building.getElevatorList().stream().forEach(
//                    e ->{ building.getFloorList().get(e.getCurrentFloor()-1).reorderQueue(e);
//                    Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {}, 0, TIMEOUT_DELAY, TimeUnit.SECONDS);
//
//            );
//        }
//        personGenerator.startGenerating();

        System.out.println("Test");
    }
}
