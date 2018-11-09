package kz.batana.homecreditloyalty.task

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.tasks_fragment.*
import kz.batana.homecreditloyalty.R


@Suppress("DEPRECATION")
class TasksFragment: Fragment(){
    var progressBar: ProgressBar? = null
    private var progressBarStatus = 0
    private var progressBarHandler = Handler()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tasks_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        progressBarLevel.visibility = View.VISIBLE
        progressBarLevel.progress = 0
        progressBarLevel.max = 100
        progressBarLevel.progressDrawable = activity!!.resources.getDrawable(R.drawable.custom_progress_bar)


        progressBarStatus = 0

        Thread(Runnable {
            while (progressBarStatus < 100) {

                // process some tasks
                progressBarStatus = DoWork()

                try {
                    Thread.sleep(300)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                // Update the progress bar
                progressBarHandler.post { progressBarLevel!!.progress = progressBarStatus }
            }
        }).start()
    }

    // DoWork & Set Status Progress Bar
    fun DoWork(): Int {

        // Do some work EG: Save , Download , Insert , ..
        // **** Work
        // **** Work
        // **** Work
        // **** Work
        progressBarStatus++ // Work process and return status

        if (progressBarStatus < 100) {
            return progressBarStatus
        }

        // When Finish
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return 100

    }

}