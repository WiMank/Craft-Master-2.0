package com.wimank.craftmaster.tz.main_screen.mvp.models

interface IDataManager<T> {

    fun containsData(serAr: List<T>, locAr: List<T>)

    fun insertEntity(entity: T)

    fun deleteEntity(entity: T)
}