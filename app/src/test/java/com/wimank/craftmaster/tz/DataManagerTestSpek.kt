package com.wimank.craftmaster.tz

import com.wimank.craftmaster.tz.app.categories_screen.rest.CategoryResponse
import com.wimank.craftmaster.tz.app.categories_screen.room.Category
import com.wimank.craftmaster.tz.app.categories_screen.room.CategoryEntity
import com.wimank.craftmaster.tz.app.main_screen.mvp.models.DataManager
import com.wimank.craftmaster.tz.app.main_screen.room.MainGroupEntity
import com.wimank.craftmaster.tz.common.rest.Success
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.spekframework.spek2.Spek

object DataManagerTestSpek : Spek({

    val dataManager: DataManager = mockk()
    val mainGroupEntity = MainGroupEntity("Test", "Test", 0, 0)


    beforeGroup {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    group("Tests DB methods in DataManger") {
        test("Test insert entity") {
            every { dataManager.insertEntity(mainGroupEntity) } answers { nothing }
            dataManager.insertEntity(mainGroupEntity)
            verify { dataManager.insertEntity(mainGroupEntity) }
        }

        test("Test delete entity") {
            every { dataManager.deleteEntity(mainGroupEntity) } answers { nothing }
            dataManager.deleteEntity(mainGroupEntity)
            verify { dataManager.deleteEntity(mainGroupEntity) }
        }

        test("Test getMainGroupFromDb() method") {
            every { dataManager.getMainGroupFromDb() } returns Single.just(listOf(mainGroupEntity))
            dataManager.getMainGroupFromDb()
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline())
                .test()
                .assertResult(listOf(mainGroupEntity))
        }

        test("Test getCategories() method") {
            val categoryResponse = CategoryResponse(
                Success(200, "test message"),
                listOf(
                    CategoryEntity(
                        "g",
                        0,
                        Category(en = "en", ru = "ru"),
                        "i",
                        0
                    )
                )
            )
            every { dataManager.getCategories() } returns Single.just(categoryResponse)
            dataManager
                .getCategories()
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline())
                .test()
                .assertResult(categoryResponse)
        }
    }
})