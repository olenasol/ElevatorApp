package co.lnu.elevatorapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.lnu.elevatorapp.elevator_simultion.ElevatorSimulationFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.mainContent,
            ElevatorSimulationFragment.newInstance()).commit()
    }
}
