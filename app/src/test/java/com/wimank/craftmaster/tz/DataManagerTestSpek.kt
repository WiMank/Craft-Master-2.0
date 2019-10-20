package com.wimank.craftmaster.tz

import com.wimank.craftmaster.tz.app.mvp.models.DataManager
import com.wimank.craftmaster.tz.app.room.entitys.CategoryEntity
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entitys.RecipeEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.spekframework.spek2.Spek

object DataManagerTestSpek : Spek({

    val dataManager: DataManager = mockk()
    val mainGroupEntity = mockk<MainGroupEntity>()
    val categoryEntity = mockk<List<CategoryEntity>>()
    val recipeEntity = mockk<List<RecipeEntity>>()
    val descriptionEntity = mockk<List<DescriptionEntity>>()

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

        test("Test getCategoriesFromDb() method") {
            every { dataManager.getCategoriesFromDb() } returns Single.just(categoryEntity)
            dataManager
                .getCategoriesFromDb()
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline())
                .test()
                .assertResult(categoryEntity)
        }

        test("Test getRecipesFromDb() method") {
            every { dataManager.getRecipesFromDb() } returns Single.just(recipeEntity)
            dataManager
                .getRecipesFromDb()
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline())
                .test()
                .assertResult(recipeEntity)
        }

        test("Test getDescriptionFromDb() method") {
            every { dataManager.getDescriptionFromDb() } returns Single.just(descriptionEntity)
            dataManager
                .getDescriptionFromDb()
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline())
                .test()
                .assertResult(descriptionEntity)
        }
    }
})
