package co.lnu.elevatorapp.ui.elevator_simultion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.floor.Floor;
import co.lnu.elevatorapp.person.Person;
import co.lnu.elevatorapp.ui.elevator_simultion.rv.ElevationAdapter;
import co.lnu.elevatorapp.ui.elevator_simultion.rv.FloorAdapter;
import co.lnu.elevatorapp.R;

import java.util.List;

public class ElevatorSimulationFragment extends Fragment implements ElevatorSimulationView {

    private ElevatorSimulationPresenter presenter;

    public static final String ARGS_FLOORNUMBER = "args_floor_number";
    public static final String ARGS_ELEVATORNUMBER = "args_elevator_number";


    private RecyclerView floorRecyclerView;
    private RecyclerView elevatorRecyclerView;
    private FloorAdapter floorAdapter;
    private ElevationAdapter elevationAdapter;

    public static ElevatorSimulationFragment newInstance(int floorNumber, int elevatorNumber) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_FLOORNUMBER,floorNumber);
        bundle.putInt(ARGS_ELEVATORNUMBER,elevatorNumber);
        ElevatorSimulationFragment fragment = new ElevatorSimulationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(co.lnu.elevatorapp.R.layout.fragment_elevator_simulation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floorRecyclerView = view.findViewById(R.id.floorRecyclerView);
        elevatorRecyclerView = view.findViewById(R.id.elevatorRecyclerView);
        presenter = new ElevatorSimulationPresenterImpl(this);
        if(getArguments() != null)
            presenter.onViewCreated(getArguments().getInt(ARGS_FLOORNUMBER,5),
                getArguments().getInt(ARGS_ELEVATORNUMBER,1));
    }
    @Override
    public void generateBuildingUI(List<Floor> floors, List<Elevator> elevators){
        initFloorRV(floors);
        initElevatorRV(elevators);
    }


    private void initElevatorRV(List<Elevator> elevators) {
        elevationAdapter = new ElevationAdapter(elevators,getArguments().getInt(ARGS_FLOORNUMBER,5));
        elevatorRecyclerView.setAdapter(elevationAdapter);
        elevatorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
    }

    private void initFloorRV(List<Floor> floors) {
        floorRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                floorRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                presenter.setFloorHeight(floorRecyclerView.getHeight());
            }
        });
        floorAdapter = new FloorAdapter(floors);
        floorRecyclerView.setAdapter(floorAdapter);
        floorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true));
//        new Handler(Looper.getMainLooper()).postDelayed(() -> moveToFloor(0,4), 5000);
//        new Handler(Looper.getMainLooper()).postDelayed(() ->moveToPersonOutOfScreen(4,2,0), 10000);
//        new Handler(Looper.getMainLooper()).postDelayed(() -> moveToFloor(0,1), 15000);
//        new Handler(Looper.getMainLooper()).postDelayed(() -> removePersonFromLift(0,0), 20000);
        //        new Handler(Looper.getMainLooper()).postDelayed(() -> moveToFloor(1,2), 15000);
//        new Handler(Looper.getMainLooper()).postDelayed(() -> moveToFloor(1,0), 25000);
//        new Handler(Looper.getMainLooper()).postDelayed(() -> moveToFloor(0,6), 35000);
        
    }
    @Override
    public void removePersonFromLift(int liftNumber,int personNumber){
        elevationAdapter.removePerson(liftNumber,personNumber);
    }
    @Override
    public void moveToFloor(int elevatorNumber,int intendedFloor) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(
                (elevatorRecyclerView.getLayoutManager()).findViewByPosition(elevatorNumber)
                        .findViewById(R.id.elevator), "translationY",
                presenter.moveToFloorYDelta(intendedFloor));
        animation.setDuration(5000);
        animation.start();
    }

    @Override
    public void moveToPersonOutOfScreen(int floor,int personNumber, int liftNumber) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(
                ((RecyclerView)(floorRecyclerView.getLayoutManager()).findViewByPosition(floor).findViewById(R.id.rvPeople)).getLayoutManager()
                .findViewByPosition(personNumber), "translationX",
                -(liftNumber+1)*200);
        animation.setDuration(1000);
        animation.start();
        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                elevationAdapter.addPerson(liftNumber,floorAdapter.removePerson(floor,personNumber));
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });
    }

}
