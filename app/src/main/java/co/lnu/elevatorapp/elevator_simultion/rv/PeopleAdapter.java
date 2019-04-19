package co.lnu.elevatorapp.elevator_simultion.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import co.lnu.elevatorapp.Person;
import co.lnu.elevatorapp.R;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private List<Person> listOfPeople;

    public PeopleAdapter(List<Person> listOfPeople) {
        this.listOfPeople = listOfPeople;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PeopleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_person, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder peopleViewHolder, int i) {
        peopleViewHolder.txtPersonIntendedFloor.setText(String.valueOf(listOfPeople.get(i).getIntendedFloor()));
    }

    @Override
    public int getItemCount() {
        return listOfPeople.size();
    }

    class PeopleViewHolder extends RecyclerView.ViewHolder{

        TextView txtPersonIntendedFloor;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPersonIntendedFloor = itemView.findViewById(R.id.txtIntendedFloor);
        }
    }
}
