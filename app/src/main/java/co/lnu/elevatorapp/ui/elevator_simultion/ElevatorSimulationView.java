package co.lnu.elevatorapp.ui.elevator_simultion;

import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.floor.Floor;
import co.lnu.elevatorapp.person.Person;

import java.util.List;

public interface ElevatorSimulationView {
        void moveToPersonOutOfScreen(int floor, int personNumber, int liftNumber);
        void moveToFloor(int elevatorNumber, int intendedFloor, int duration);
        void removePersonFromLift(int liftNumber, int personNumber);
        void generateBuildingUI( List<Floor> floors, List<Elevator> elevators);
}
