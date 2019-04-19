package co.lnu.elevatorapp.ui.elevator_simultion;

import co.lnu.elevatorapp.builder.Building;
import co.lnu.elevatorapp.builder.BuildingDirector;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.floor.Floor;
import co.lnu.elevatorapp.person.Person;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSimulationPresenterImpl implements ElevatorSimulationPresenter {

    private int numberOfFloor;
    private int numberOfElevators;
    private int floorHeight;
    private ElevatorSimulationView view;
    private ElevatorSimulationInteractor interactor;

    public ElevatorSimulationPresenterImpl(ElevatorSimulationView view) {

        this.view = view;
        this.interactor = new ElevatorSimulationInteractorImpl(this);
    }


    @Override
    public void setFloorHeight(int height) {
        floorHeight = height / numberOfFloor;
    }

    @Override
    public float moveToFloorYDelta(int intendedFloor) {
        return -intendedFloor * floorHeight;
    }

    @Override
    public int getFloorHeight() {
        return floorHeight;
    }

    @Override
    public void onViewCreated(int floorNumber, int elevatorNumber) {
        numberOfFloor = floorNumber;
        numberOfElevators = elevatorNumber;
        Building building = interactor.getBuilding(floorNumber, elevatorNumber);
        view.generateBuildingUI(building.getFloorList(),building.getElevatorList());
        interactor.getPerson();
    }


}
