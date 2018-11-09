package kz.batana.homecreditloyalty.core.util

interface IView<out P : IPresenter<*>> {
    val presenter: P
}