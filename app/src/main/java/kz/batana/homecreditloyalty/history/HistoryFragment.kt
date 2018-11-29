package kz.batana.homecreditloyalty.history


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_history.*
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.entity.History
import kz.batana.homecreditloyalty.local_storage.AppLocalDatabase
import org.koin.android.ext.android.inject
import java.util.*


class HistoryFragment : Fragment() {

    private val localStorage: AppLocalDatabase by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getHistoryLocallY().subscribe(
                {

                },{

                }
        )

        var arraylist = ArrayList<History>()
        arraylist.apply {
            add(History(1,"Автоплатеж", "11-11-2018", "Аскар Жанбатыр", "Баланс", 120))
            add(History(2,"Конвертация", "11-11-2018", "Аскар Жанбатыр", "Аскар Жанбатыр", 200))
            add(History(3,"Баланс", "11-11-2018", "Аскар Жанбатыр", "Баланс", 20))
            add(History(4,"Онай", "11-11-2018", "Аскар Жанбатыр", "Онай счет", 100))
            add(History(5,"Онай", "11-11-2018", "Аскар Жанбатыр", "Онай счет", 40))
            add(History(6,"Оставить отзыв", "11-11-2018", "Задание", "Аскар Жанбатыр", 50))
            add(History(7,"Сообщить о баге", "11-11-2018", "Задание", "Аскар Жанбатыр", 50))
            add(History(8,"Оценить приложение", "11-11-2018", "Задание", "Аскар Жанбатыр", 30))

        }

        setRecycler(arraylist)

    }


    private fun setRecycler(list: ArrayList<History>){
        recyclerHistory.layoutManager = LinearLayoutManager(context)
        recyclerHistory.adapter = HistoryAdapter(list)
    }

    private fun getHistoryLocallY() : Observable<List<History>>{
        return localStorage
                .historyDao()
                .get()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


}
