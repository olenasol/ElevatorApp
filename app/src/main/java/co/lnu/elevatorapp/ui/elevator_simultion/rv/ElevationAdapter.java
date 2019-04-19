package co.lnu.elevatorapp.ui.elevator_simultion.rv;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.lnu.elevatorapp.R;
import co.lnu.elevatorapp.elevator.Elevator;
import co.lnu.elevatorapp.person.Person;

import java.util.List;

public class ElevationAdapter extends RecyclerView.Adapter<ElevationAdapter.ElevatorViewHolder> {

    private List<Elevator> listOfElevators;
    public static final String ARGS_PERSON = "args_person";
    private int floorHeight;
    private int floorNumber;
    private Context context;

    public ElevationAdapter(List<Elevator> listOfElevators, int floorNumber) {
        this.listOfElevators = listOfElevators;
        this.floorNumber = floorNumber;
    }

    @NonNull
    @Override
    public ElevatorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_elevator, viewGroup, false);
        floorHeight = (viewGroup.getMeasuredHeight()/floorNumber);
        context = viewGroup.getContext();
        return new ElevatorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ElevatorViewHolder elevatorViewHolder, int i) {
        elevatorViewHolder.elavator.getLayoutParams().height = floorHeight;
        elevatorViewHolder.rvPeople.setLayoutManager(new LinearLayoutManager(context));
        elevatorViewHolder.rvPeople.setAdapter(new PeopleAdapter(listOfElevators.get(i).getPeople()));
    }

    @Override
    public void onBindViewHolder(@NonNull ElevatorViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if(payloads.size() != 0){
            Bundle bundle = (Bundle) payloads.get(0);
            if(bundle.getBoolean(ARGS_PERSON))
                holder.rvPeople.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return listOfElevators.size();
    }

    public void addPerson(int liftNumber, Person person){
        listOfElevators.get(liftNumber).getPeople().add(0,person);
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARGS_PERSON,true);
        notifyItemChanged(liftNumber,bundle);
    }
    public void removePerson(int liftNumber, int personNumber){
        listOfElevators.get(liftNumber).getPeople().remove(personNumber);
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARGS_PERSON,true);
        notifyItemChanged(liftNumber,bundle);
    }

    class ElevatorViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout elavator;
        RecyclerView rvPeople;

        public ElevatorViewHolder(@NonNull View itemView) {
            super(itemView);
            elavator = itemView.findViewById(R.id.elevator);
            rvPeople = itemView.findViewById(R.id.rvPeople);
        }
    }
}
