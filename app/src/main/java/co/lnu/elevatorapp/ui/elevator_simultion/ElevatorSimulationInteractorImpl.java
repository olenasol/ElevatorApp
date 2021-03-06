package co.lnu.elevatorapp.ui.elevator_simultion;

import co.lnu.elevatorapp.builder.Building;
import co.lnu.elevatorapp.builder.BuildingDirector;
import co.lnu.elevatorapp.dispatcher.Dispatcher;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.person.PersonGenerator;

public class ElevatorSimulationInteractorImpl implements ElevatorSimulationInteractor {

    private Building building;
    private ElevatorSimulationPresenter presenter;
    private int numberOfFoloors;
    private Dispatcher dispatcher;

    public ElevatorSimulationInteractorImpl(ElevatorSimulationPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Building getBuilding(int floorNumber, int elevatorNumber) {
        numberOfFoloors = floorNumber;
        BuildingDirector buildingDirector = new BuildingDirector(null);
        building = buildingDirector.construct(floorNumber, elevatorNumber);
        dispatcher = new Dispatcher(building, presenter);
        for (Elevator elevator: building.getElevatorList()){
            elevator.setDispatcher(dispatcher);
        }
        dispatcher.createThreads();
        return building;
    }

    @Override
    public void startPersonGenerating() {
        PersonGenerator personGenerator = new PersonGenerator(dispatcher, numberOfFoloors);
        personGenerator.startGenerating();
    }
}
