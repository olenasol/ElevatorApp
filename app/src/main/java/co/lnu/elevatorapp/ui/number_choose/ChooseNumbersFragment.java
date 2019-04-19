package co.lnu.elevatorapp.ui.number_choose;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.NumberPicker;
import co.lnu.elevatorapp.R;
import co.lnu.elevatorapp.ui.elevator_simultion.ElevatorSimulationFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseNumbersFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_numbers, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NumberPicker elevatorNumberPicker = view.findViewById(R.id.elevatorNumberPicker);
        elevatorNumberPicker.setMinValue(1);
        elevatorNumberPicker.setMaxValue(5);
        NumberPicker floorNumberPicker = view.findViewById(R.id.floorNumberPicker);
        floorNumberPicker.setMinValue(5);
        floorNumberPicker.setMaxValue(20);
        Button submitBtn = view.findViewById(R.id.btnSubmit);
        submitBtn.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.mainContent,
                    ElevatorSimulationFragment.newInstance(floorNumberPicker.getValue(),elevatorNumberPicker.getValue()))
                    .addToBackStack(ChooseNumbersFragment.class.getSimpleName()).commit();
        });
    }
}
