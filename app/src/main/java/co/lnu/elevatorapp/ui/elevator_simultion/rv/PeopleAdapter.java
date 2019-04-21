package co.lnu.elevatorapp.ui.elevator_simultion.rv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import co.lnu.elevatorapp.R;
import co.lnu.elevatorapp.person.Person;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private List<Person> listOfPeople;
    private Context context;

    public PeopleAdapter(List<Person> listOfPeople) {
        this.listOfPeople = listOfPeople;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new PeopleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_person, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder peopleViewHolder, int i) {
        if (listOfPeople.get(i) != null) {
            peopleViewHolder.txtPersonIntendedFloor.setText(String.valueOf(listOfPeople.get(i).getIntendedFloor()));
        }
        switch (listOfPeople.get(i).getColor()){
            case GREEN:
                peopleViewHolder.txtPersonIntendedFloor.setBackground(context.getDrawable(R.drawable.cicle_background_green));
                break;
            case BLUE:
                peopleViewHolder.txtPersonIntendedFloor.setBackground(context.getDrawable(R.drawable.circle_background_blue));
                break;
            case RED:
                peopleViewHolder.txtPersonIntendedFloor.setBackground(context.getDrawable(R.drawable.circle_background_red));
                break;
            case YELLOW:
                peopleViewHolder.txtPersonIntendedFloor.setBackground(context.getDrawable(R.drawable.circle_background_yellow));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listOfPeople.size();
    }

    class PeopleViewHolder extends RecyclerView.ViewHolder {

        TextView txtPersonIntendedFloor;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPersonIntendedFloor = itemView.findViewById(R.id.txtIntendedFloor);
        }
    }
}
