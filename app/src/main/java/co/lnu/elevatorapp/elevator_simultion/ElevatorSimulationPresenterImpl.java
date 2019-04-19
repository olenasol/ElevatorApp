package co.lnu.elevatorapp.elevator_simultion;

import co.lnu.elevatorapp.Elevator;
import co.lnu.elevatorapp.Floor;
import co.lnu.elevatorapp.Person;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSimulationPresenterImpl implements ElevatorSimulationPresenter {

    private int numberOfFloor;
    private int numberOfElevators;
    private int floorHeight;
    private ElevatorSimulationView view;

    public ElevatorSimulationPresenterImpl(ElevatorSimulationView view) {
        this.view = view;
    }

    @Override
    public List<Floor> getListOfFloors() {
        List<Floor> list = new ArrayList<>();
        for(int i = 0;i<numberOfFloor;i++){
            ArrayList<Person> people = new ArrayList<>();
            if(i==4){
                people.add(new Person(8,5));
                people.add(new Person(9,2));
                people.add(new Person(9,8));
            }
            list.add(new Floor(people));
        }
        return list;
    }

    @Override
    public void setFloorHeight(int height) {
        floorHeight = height/numberOfFloor;
    }

    @Override
    public float moveToFloorYDelta(int intendedFloor) {
        return -intendedFloor*floorHeight;
    }

    @Override
    public int getFloorHeight() {
        return floorHeight;
    }

    @Override
    public void onViewCreated(int floorNumber, int elevatorNumber) {
        numberOfFloor = floorNumber;
        numberOfElevators = elevatorNumber;
    }

    @Override
    public List<Elevator> getListOfElevators() {
        List<Elevator> list = new ArrayList<>();
        for(int i = 0;i<numberOfElevators;i++){
            ArrayList<Person> people = new ArrayList<>();
//            if(i==0){
//                people.add(new Person(9,4));
//                people.add(new Person(9,4));
//                people.add(new Person(9,4));
//                people.add(new Person(9,4));
//            }
            list.add(new Elevator(1,people));
        }
        return list;
    }
}
