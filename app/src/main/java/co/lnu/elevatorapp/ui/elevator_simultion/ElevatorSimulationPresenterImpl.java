package co.lnu.elevatorapp.ui.elevator_simultion;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import co.lnu.elevatorapp.builder.Building;
import co.lnu.elevatorapp.person.Person;

import static co.lnu.elevatorapp.ui.elevator_simultion.ElevatorSimulationFragment.UPDATE_ELEVATOR;
import static co.lnu.elevatorapp.ui.elevator_simultion.ElevatorSimulationFragment.UPDATE_FLOOR;

public class ElevatorSimulationPresenterImpl implements ElevatorSimulationPresenter {

    private int numberOfFloors;
    private int numberOfElevators;
    private int floorHeight;
    private ElevatorSimulationView view;
    private ElevatorSimulationInteractor interactor;
    private Handler handler;

    public ElevatorSimulationPresenterImpl(ElevatorSimulationView view, Handler handler) {
        this.view = view;
        this.handler = handler;
        this.interactor = new ElevatorSimulationInteractorImpl(this);
    }


    @Override
    public void setFloorHeight(int height) {
        floorHeight = height / numberOfFloors;
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
        numberOfFloors = floorNumber;
        numberOfElevators = elevatorNumber;
        Building building = interactor.getBuilding(floorNumber, elevatorNumber);
        view.generateBuildingUI(building.getFloorList(), building.getElevatorList());
        interactor.startPersonGenerating();
    }

    @Override
    public void notifyFloor(int floorId) {
        handler.sendEmptyMessage(UPDATE_FLOOR);
    }

    @Override
    public void notifyElevator(int elevatorId) {
        handler.sendEmptyMessage(UPDATE_ELEVATOR);
    }

    @Override
    public void moveToFloor(int elevatorId, int floorId, int duration) {
        view.moveToFloor(elevatorId, floorId, duration);
    }
}
