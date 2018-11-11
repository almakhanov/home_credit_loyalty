package kz.batana.homecreditloyalty.history

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.entity.History

class HistoryAdapter(private var dataset: ArrayList<History>)
    : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(dataset[position])
    }


    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(history: History) {
            itemView.findViewById<TextView>(R.id.historyTitle).text = history.title
            itemView.findViewById<TextView>(R.id.historyDate).text = history.data
            itemView.findViewById<TextView>(R.id.historyBalance).text = history.balance.toString()+" тг"
            itemView.findViewById<TextView>(R.id.historyFrom).text = "От: "+history.from_field
            itemView.findViewById<TextView>(R.id.historyTo).text = "Кому: "+history.to_field
        }
    }

}
