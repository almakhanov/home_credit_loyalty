package kz.batana.homecreditloyalty.task

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.core.util.Logger
import kz.batana.homecreditloyalty.entity.Task

class CurrentTasksAdapter(private var dataset: ArrayList<Task>,
                          private val listener: OnItemClickListener)
    : RecyclerView.Adapter<CurrentTasksAdapter.TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_current_task, parent, false)
        return TasksViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(dataset[position])
    }


    inner class TasksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        @SuppressLint("SetTextI18n")
        fun bind(task: Task) {
            itemView.findViewById<TextView>(R.id.itemTaskTitle).text = task.title
            itemView.findViewById<TextView>(R.id.itemTaskDate).text = task.date
            itemView.findViewById<TextView>(R.id.bonusPointsPerTask).text = "+"+task.bonus+" баллов"
            itemView.findViewById<TextView>(R.id.itemTaskDesc).text = task.description
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val course: Task = dataset[adapterPosition]
            Logger.msg("clicked course=$course")
            listener.onItemClicked(course)

        }

    }

    interface OnItemClickListener {
        fun onItemClicked(course: Task)
    }
}
