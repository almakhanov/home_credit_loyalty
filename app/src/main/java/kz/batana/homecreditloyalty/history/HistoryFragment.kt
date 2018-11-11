package kz.batana.homecreditloyalty.history


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_history.*
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.entity.History


class HistoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun setRecycler(list: ArrayList<History>){
        recyclerHistory.layoutManager = LinearLayoutManager(context)
        recyclerHistory.adapter = HistoryAdapter(list)
    }


}
