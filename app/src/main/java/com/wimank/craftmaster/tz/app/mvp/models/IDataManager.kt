package com.wimank.craftmaster.tz.app.mvp.models

interface IDataManager<in T> {

    fun containsData(serAr: List<T>, locAr: List<T>)

    fun insertEntity(entity: T)

    fun deleteEntity(entity: T)
}
