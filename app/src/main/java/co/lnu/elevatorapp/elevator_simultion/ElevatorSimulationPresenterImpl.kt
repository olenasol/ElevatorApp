package co.lnu.elevatorapp.elevator_simultion

import co.lnu.elevatorapp.Floor
import co.lnu.elevatorapp.elevator_simultion.ElevatorSimulationPresenter

class ElevatorSimulationPresenterImpl(val view:ElevatorSimulationView): ElevatorSimulationPresenter {

    private var floorHeight:Int =0
    private val NUM_OF_FLOORS = 7

    override fun setFloorHeight(height: Int) {
        floorHeight = height/NUM_OF_FLOORS
    }

    override fun moveToFloorYDelta( intendedFloor:Int ):Float{
        return -intendedFloor*floorHeight.toFloat()
    }

    override fun getListOfFloors():List<Floor>{
        return arrayListOf(
            Floor(arrayListOf()),
            Floor(arrayListOf()),
            Floor(arrayListOf()),
            Floor(arrayListOf()),
            Floor(arrayListOf()),
            Floor(arrayListOf()),
            Floor(arrayListOf())
        )
    }

}