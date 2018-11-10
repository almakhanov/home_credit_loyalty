package kz.batana.homecreditloyalty.service

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_service.*
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.converter.ConverterActivity
import kz.batana.homecreditloyalty.onay.OnayActivity
import kz.batana.homecreditloyalty.telephone.BalanceAddActivity

class ServiceFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_service,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstService.setOnClickListener {
            val intent = Intent(activity, BalanceAddActivity::class.java)
            activity?.startActivity(intent)
        }

        secondService.setOnClickListener {
            val intent = Intent(activity, ConverterActivity::class.java)
            activity?.startActivity(intent)
        }

        thirdService.setOnClickListener {
            val intent = Intent(activity, OnayActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}