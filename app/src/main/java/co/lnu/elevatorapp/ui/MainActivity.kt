package co.lnu.elevatorapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.lnu.elevatorapp.R
import co.lnu.elevatorapp.ui.number_choose.ChooseNumbersFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(
            R.id.mainContent,
            ChooseNumbersFragment()).commit()
    }
}
