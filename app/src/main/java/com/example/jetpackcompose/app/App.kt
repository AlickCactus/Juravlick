package com.example.jetpackcompose.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
DI(Depedency Injection): передать объект одного класса в конструктор другого класса, чтобы передалось во View-модель
*/

//модуль, который собирает одни классы и импортирует в другие классы
@HiltAndroidApp
class App : Application(){
}