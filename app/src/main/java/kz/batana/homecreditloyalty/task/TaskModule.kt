package kz.batana.homecreditloyalty.task

import org.koin.dsl.module.module

val taskModule = module {
    factory { TaskPresenter(get()) as TaskContract.TaskPresenter }
    factory { TaskRepository(get(), get()) as TaskContract.TaskRepository }
}