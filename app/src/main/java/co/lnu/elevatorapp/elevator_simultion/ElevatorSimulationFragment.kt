package co.lnu.elevatorapp.elevator_simultion


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_elevator_simulation.*
import co.lnu.elevatorapp.Floor
import co.lnu.elevatorapp.FloorAdapter
import android.opengl.ETC1.getHeight
import android.view.ViewTreeObserver
import android.R
import android.animation.ObjectAnimator
import android.os.Handler
import android.os.Looper
import android.support.constraint.ConstraintLayout
import android.view.animation.Animation
import android.view.animation.TranslateAnimation




/**
 * A simple [Fragment] subclass.
 *
 */
class ElevatorSimulationFragment : Fragment(), ElevatorSimulationView {

    lateinit var presenter:ElevatorSimulationPresenter

    companion object {
        fun newInstance(): ElevatorSimulationFragment {
            return ElevatorSimulationFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(co.lnu.elevatorapp.R.layout.fragment_elevator_simulation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = ElevatorSimulationPresenterImpl(this)
        floorRecyclerView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                floorRecyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                presenter.setFloorHeight(floorRecyclerView.height)
            }
        })
        floorRecyclerView.adapter = FloorAdapter(presenter.getListOfFloors())
        floorRecyclerView.layoutManager = LinearLayoutManager(context)
        Handler(Looper.getMainLooper()).postDelayed({
            moveToFloor(5)
        },5000)
        Handler(Looper.getMainLooper()).postDelayed({
            moveToFloor(2)
        },15000)
        Handler(Looper.getMainLooper()).postDelayed({
            moveToFloor(0)
        },25000)
        Handler(Looper.getMainLooper()).postDelayed({
            moveToFloor(6)
        },35000)
    }

    private fun moveToFloor( intendedFloor:Int) {
        ObjectAnimator.ofFloat(elevatorTxt, "translationY",
            presenter.moveToFloorYDelta(intendedFloor)).apply {
            duration = 5000
            start()
        }
    }
}
