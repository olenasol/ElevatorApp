package co.lnu.elevatorapp.ui.elevator_simultion;

import android.util.Log;
import co.lnu.elevatorapp.builder.Building;
import co.lnu.elevatorapp.builder.BuildingDirector;
import co.lnu.elevatorapp.floor.Floor;
import co.lnu.elevatorapp.person.Person;
import co.lnu.elevatorapp.person.PersonGenerator;

import java.util.List;

public class ElevatorSimulationInteractorImpl implements ElevatorSimulationInteractor {

    private Building building;
    private ElevatorSimulationPresenter presenter;

    public ElevatorSimulationInteractorImpl(ElevatorSimulationPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Building getBuilding(int floorNumber, int elevatorNumber) {
        BuildingDirector buildingDirector = new BuildingDirector(null);
        building = buildingDirector.construct(floorNumber, elevatorNumber);
        return building;
    }

    @Override
    public void getPerson(){
        PersonGenerator personGenerator = new PersonGenerator(building,null);
        int floorNumber = personGenerator.generatePerson();
        Person person = building.getFloorList().get(floorNumber).getPeople().get(building.getFloorList().get(floorNumber).getPeople().size()-1);
        //presenter.setPersonToFloor(floorNumber,person);
        Log.d("TEST","TEST");
    }

}
