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



        var arraylist = ArrayList<History>()
        arraylist.apply {
            add(History("Автоплатеж", "11-11-2018", "Аскар Жанбатыр", "Баланс", 120))
            add(History("Конвертация", "11-11-2018", "Аскар Жанбатыр", "Аскар Жанбатыр", 200))
            add(History("Баланс", "11-11-2018", "Аскар Жанбатыр", "Баланс", 20))
            add(History("Онай", "11-11-2018", "Аскар Жанбатыр", "Онай счет", 100))
            add(History("Онай", "11-11-2018", "Аскар Жанбатыр", "Онай счет", 40))
            add(History("Оставить отзыв", "11-11-2018", "Задание", "Аскар Жанбатыр", 50))
            add(History("Сообщить о баге", "11-11-2018", "Задание", "Аскар Жанбатыр", 50))
            add(History("Оценить приложение", "11-11-2018", "Задание", "Аскар Жанбатыр", 30))

        }

        setRecycler(arraylist)

    }


    private fun setRecycler(list: ArrayList<History>){
        recyclerHistory.layoutManager = LinearLayoutManager(context)
        recyclerHistory.adapter = HistoryAdapter(list)
    }


}
