package co.lnu.elevatorapp.ui.elevator_simultion.rv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.lnu.elevatorapp.R;
import co.lnu.elevatorapp.floor.Floor;
import co.lnu.elevatorapp.person.Person;

import java.util.List;

public class FloorAdapter extends RecyclerView.Adapter<FloorAdapter.FloorViewHolder> {

    public static final String ARGS_PERSON = "args_person";
    private List<Floor> listOfFloors;
    private Context context;

    public FloorAdapter(List<Floor> listOfFloors) {
        this.listOfFloors = listOfFloors;
    }

    @NonNull
    @Override
    public FloorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_floor, viewGroup, false);
        int h = (viewGroup.getMeasuredHeight() / listOfFloors.size());
        itemView.getLayoutParams().height = h;
        this.context = viewGroup.getContext();
        return new FloorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FloorViewHolder floorViewHolder, int i) {
        floorViewHolder.rvPeople.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        });
        floorViewHolder.rvPeople.setAdapter(new PeopleAdapter(listOfFloors.get(i).getPeople()));
    }

    @Override
    public int getItemCount() {
        return listOfFloors.size();
    }

    public Person removePerson(int floorNumber, int personNumber) {
        Person person = listOfFloors.get(floorNumber).getPeople().get(personNumber);
        listOfFloors.get(floorNumber).getPeople().remove(personNumber);
//        notifyItemChanged(floorNumber);
        notifyDataSetChanged();
        return person;
    }

    class FloorViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView rvPeople;

        public FloorViewHolder(@NonNull View itemView) {
            super(itemView);
            rvPeople = itemView.findViewById(R.id.rvPeople);
        }
    }
}
