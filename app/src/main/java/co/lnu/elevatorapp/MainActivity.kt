package co.lnu.elevatorapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.lnu.elevatorapp.elevator_simultion.ElevatorSimulationFragment
import co.lnu.elevatorapp.number_choose.ChooseNumbersFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.mainContent,
            ChooseNumbersFragment()).commit()
    }
}
