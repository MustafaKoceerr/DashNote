package com.mustafakoceerr.dashnote.core.common.dispatcher

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dashNoteDispatcher: DashNoteDispatchers)

enum class DashNoteDispatchers {
    Default,
    IO,
}