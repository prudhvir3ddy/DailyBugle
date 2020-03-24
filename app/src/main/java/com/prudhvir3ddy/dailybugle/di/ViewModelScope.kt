package com.prudhvir3ddy.dailybugle.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * defining the scope of viewmodel
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelScope(val value: KClass<out ViewModel>)