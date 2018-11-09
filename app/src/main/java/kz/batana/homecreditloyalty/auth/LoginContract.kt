package kz.batana.homecreditloyalty.auth

import kz.batana.homecreditloyalty.core.util.IPresenter
import kz.batana.homecreditloyalty.core.util.IView

interface LoginContract{

    interface LoginView: IView<LoginPresenter> {

    }

    interface LoginPresenter: IPresenter<LoginView> {

    }

    interface LoginRepository{

    }
}