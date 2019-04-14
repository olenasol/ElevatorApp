package co.lnu.elevatorapp.elevator_simultion

import co.lnu.elevatorapp.Floor

interface ElevatorSimulationPresenter
{

    fun getListOfFloors(): List<Floor>

    fun setFloorHeight(height: Int)

    fun moveToFloorYDelta(intendedFloor: Int): Float

}