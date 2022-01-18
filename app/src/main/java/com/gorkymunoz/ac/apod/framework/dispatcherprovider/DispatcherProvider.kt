package com.gorkymunoz.ac.apod.framework.dispatcherprovider

import kotlinx.coroutines.CoroutineDispatcher


/**
 * Created by Gorky Muñoz on 18/1/2022.
 *
 *
 */
interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}