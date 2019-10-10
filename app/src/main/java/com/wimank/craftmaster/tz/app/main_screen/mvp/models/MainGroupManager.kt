package com.wimank.craftmaster.tz.app.main_screen.mvp.models

import android.content.Context
import com.wimank.craftmaster.tz.app.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.app.main_screen.rest.MainGroupResponse
import com.wimank.craftmaster.tz.app.main_screen.room.MainGroupEntity
import com.wimank.craftmaster.tz.common.rest.ImageApi
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.utils.checkImageExist
import com.wimank.craftmaster.tz.common.utils.writeImage
import io.reactivex.Flowable
import io.reactivex.Single
import org.apache.commons.collections4.CollectionUtils

class MainGroupManager(
    private val mContext: Context,
    private val mainGroupApi: MainGroupApi,
    private val mImageApi: ImageApi,
    private val craftMasterDataBase: CraftMasterDataBase
) {

    fun containsData(serAr: List<MainGroupEntity>, locAr: List<MainGroupEntity>) {
        if (serAr.isNotEmpty()) {
            val disjunctionArray =
                    CollectionUtils.disjunction(
                        serAr,
                        locAr
                    ).sortedWith(compareBy { it.vers })

            disjunctionArray.forEach {
                if (locAr.contains(it))
                    deleteMainGroupEntity(it)
                else
                    downloadImageAndInsertEntity(it)
            }
        }
    }

    private fun downloadImageAndInsertEntity(mainGroupEntity: MainGroupEntity) {
        if (!checkImageExist(mContext, mainGroupEntity.groupImage)) {
            with(mImageApi.downloadImage(mainGroupEntity.groupImage).execute()) {
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

    private fun insertMainGroupEntity(mainGroupEntity: MainGroupEntity) {
        craftMasterDataBase.mainGroupDao().insert(mainGroupEntity)
    }

    private fun deleteMainGroupEntity(mainGroupEntity: MainGroupEntity) {
        craftMasterDataBase.mainGroupDao().delete(mainGroupEntity)
    }

    fun getMainGroup(): Single<MainGroupResponse> {
        return mainGroupApi.getMainGroupList()
    }

    fun getFlowableMainGroupFromDb(): Flowable<List<MainGroupEntity>> {
        return craftMasterDataBase.mainGroupDao().getFlowableMainGroupFromDb()
    }

    fun getMainGroupFromDb(): Single<List<MainGroupEntity>> {
        return craftMasterDataBase.mainGroupDao().getMainGroupFromDb()
    }
}
