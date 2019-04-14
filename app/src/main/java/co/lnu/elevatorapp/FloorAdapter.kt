package co.lnu.elevatorapp

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



class FloorAdapter(val list:List<Floor>):RecyclerView.Adapter<FloorAdapter.FloorViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FloorViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.viewholder_floor, p0, false)
        val h = (p0.measuredHeight/list.size)
        itemView.layoutParams.height = h
        return FloorViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: FloorViewHolder, p1: Int) {

    }

    class FloorViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }
}