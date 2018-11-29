package kz.batana.homecreditloyalty

import android.app.AlertDialog
import android.app.Application
import android.app.Dialog
import android.app.ProgressDialog.show
import android.content.Context
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
    }

    companion object {
        @JvmStatic var instance: App? = null
            private set

        var loadingDialog: Dialog?= null

        fun hideProgress(){
            loadingDialog?.dismiss()
        }

        fun showProgress(context: Context){
            loadingDialog?.dismiss()
            loadingDialog = Dialog(context)
            loadingDialog?.apply {
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                setContentView(R.layout.custom_progress_bar)
                show()
            }
        }
    }
}