package kz.batana.homecreditloyalty.service

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.batana.homecreditloyalty.R

class ServiceListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ServiceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_service,parent,false))
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }


    class ServiceViewHolder(view: View):RecyclerView.ViewHolder(view)
}