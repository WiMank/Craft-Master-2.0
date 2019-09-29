package com.wimank.craftmaster.tz.main_screen.mvp.models

import android.content.Context
import android.util.Log
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.utils.checkImageExist
import com.wimank.craftmaster.tz.common.utils.writeImage
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.main_screen.rest.response.MainGroupResponse
import io.reactivex.Flowable
import io.reactivex.Single
import org.apache.commons.collections4.CollectionUtils


class MainGroupManager(
    private val mContext: Context,
    private val mainGroupApi: MainGroupApi,
    private val craftMasterDataBase: CraftMasterDataBase
) {

    fun containsData(serAr: List<MainGroupEntity>, locAr: List<MainGroupEntity>) {
        if (serAr.isNotEmpty()) {
            val disjunctionArray = CollectionUtils.disjunction(serAr, locAr)
            disjunctionArray.sortedWith(compareBy { it.vers })

            disjunctionArray.forEach {
                Log.d("TEST", "FR: $it")
            }

            disjunctionArray.forEach {
                if (locAr.contains(it)) {
                    deleteMainGroupEntity(it)
                    Log.d("TEST", "IF: $it")
                }
                else {
                    downloadImageAndInsertEntity(it)
                    Log.d("TEST", "ELSE: $it")
                }
            }
        }
    }

    private fun downloadImageAndInsertEntity(mainGroupEntity: MainGroupEntity) {
        if (!checkImageExist(mContext, mainGroupEntity.groupImage)) {
            with(mainGroupApi.getMainGroupImage(mainGroupEntity.groupImage).execute()) {
                if (isSuccessful) {
                    body()?.byteStream()?.let {
                        writeImage(
                            mContext,
                            it,
                            mainGroupEntity.groupImage
                        )
                    }
                }
            }
        }
        insertMainGroupEntity(mainGroupEntity)
    }

    fun getMainGroup(): Single<MainGroupResponse> {
        return mainGroupApi.getMainGroupList()
    }

    private fun insertMainGroupEntity(mainGroupEntity: MainGroupEntity) {
        craftMasterDataBase.mainGroupDao().insert(mainGroupEntity)
    }

    private fun deleteMainGroupEntity(mainGroupEntity: MainGroupEntity) {
        craftMasterDataBase.mainGroupDao().delete(mainGroupEntity)
    }

    fun getFlowableMainGroupFromDb(): Flowable<List<MainGroupEntity>> {
        return craftMasterDataBase.mainGroupDao().getFlowableMainGroupFromDb()
    }

    fun getMainGroupFromDb(): Single<List<MainGroupEntity>> {
        return craftMasterDataBase.mainGroupDao().getMainGroupFromDb()
    }
}