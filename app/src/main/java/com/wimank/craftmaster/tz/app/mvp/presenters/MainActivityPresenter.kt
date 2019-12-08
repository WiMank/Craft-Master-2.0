package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.common.*
import com.wimank.craftmaster.tz.app.mvp.models.DataManager
import com.wimank.craftmaster.tz.app.mvp.models.NetManager
import com.wimank.craftmaster.tz.app.mvp.views.MainActivityView
import com.wimank.craftmaster.tz.app.rest.responses.*
import com.wimank.craftmaster.tz.app.room.entity.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class MainActivityPresenter(
    private val mDataManager: DataManager,
    private val mNetManager: NetManager
) : BasePresenter<MainActivityView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateData()
    }

    private fun updateData() {
        viewState.showProgress(true)
        if (mNetManager.isConnectedToNetwork())
            loadRecipes()
        else {
            viewState.showMessage(R.string.offline_mode)
            viewState.showProgress(false)
        }
    }

    fun choseListSection(section: String, item: String) {
        when (section) {
            MC_VALUE -> viewState.showRecipeSection(item)
            IC_VALUE -> viewState.showRecipeSection(item)
            BC_VALUE -> viewState.showRecipeSection(item)
            FR_VALUE -> viewState.showRecipeSection(item)
            SEARCH_VALUE -> viewState.showRecipeSection(item)
            MOBS_VALUE -> viewState.showMobsSection(item)
            BIOMES_VALUE -> viewState.showBiomesSection(item)
            FAVORITES_VALUE -> viewState.showRecipeSectionFromFav(item)
        }
    }

    fun chooseCardViewSection(section: String) {
        when (section) {
            ACHIEVEMENTS_VALUE -> viewState.showAchievementsSection()
            BREWING_VALUE -> viewState.showBrewingSection()
            FAVORITES_VALUE -> viewState.showFavoriteSection()
            SEARCH_VALUE -> viewState.showRecipesList(section, false)
            else -> viewState.showRecipesList(section)
        }
    }

    private fun loadRecipes() {
        unsubscribeOnDestroy(
            Single.zip(
                mDataManager.getRecipes(),
                mDataManager.getDescriptionFromDb(),
                mDataManager.getRecipesFromDb(),
                Function3 { servRecipes: RecipeResponse,
                            descriptionList: List<DescriptionEntity>,
                            recipeList: List<RecipeEntity> ->
                    if (servRecipes.success.isSuccess()) {
                        mDataManager.containsData(servRecipes.descriptionList, descriptionList)
                        mDataManager.containsData(servRecipes.recipesList, recipeList)
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showMessage(R.string.recipe_loading_completed)
                        viewState.showProgress(false)
                        loadMobs()
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.recipes_list_loading_error)
                    })
        )
    }

    private fun loadMobs() {
        viewState.showProgress(true)
        unsubscribeOnDestroy(
            Single.zip(
                mDataManager.getMobs(),
                mDataManager.getMobsFromDb(),
                BiFunction { mobsResponse: MobsResponse,
                             mobsDbList: List<MobsEntity> ->
                    if (mobsResponse.success.isSuccess())
                        mDataManager.containsData(mobsResponse.mobsLost, mobsDbList)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showMessage(R.string.mobs_successfully_loaded)
                        viewState.showProgress(false)
                        loadMnDevices()
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.mobs_error_loaded)
                    })
        )
    }

    private fun loadMnDevices() {
        viewState.showProgress(true)
        unsubscribeOnDestroy(
            Single.zip(
                mDataManager.getManufacturingDevices(),
                mDataManager.getManufacturingDevicesDaoFromDb(),
                BiFunction { manufacturingResponse: DevicesResponse,
                             mbDvList: List<DeviceEntity> ->
                    if (manufacturingResponse.success.isSuccess())
                        mDataManager.containsData(manufacturingResponse.devices, mbDvList)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                onSuccess = {
                    loadAcAchievements()
                    viewState.showProgress(false)
                    viewState.showMessage(R.string.devices_load_successfully)
                },
                onError = {
                    viewState.showProgress(false)
                    viewState.showError(R.string.devices_load_error)
                })
        )
    }

    private fun loadAdditionalInfo() {
        viewState.showProgress(true)
        unsubscribeOnDestroy(
            Single.zip(
                mDataManager.getAddInfo(),
                mDataManager.getAddInfoFromDb(),
                BiFunction { response: AddInfoResponse, locList: List<AdditionalEntity> ->
                    if (response.success.isSuccess())
                        mDataManager.containsData(response.additionalInfo, locList)

                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showProgress(false)
                        viewState.showMessage(R.string.addinfo_successfully_loaded)
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.addinfo_err_loaded)
                    })
        )
    }

    private fun loadAcAchievements() {
        viewState.showProgress(true)
        unsubscribeOnDestroy(
            Single.zip(
                mDataManager.getAchievements(),
                mDataManager.getAchievementsFromDb(),
                BiFunction { response: AchievementResponse,
                             locList: List<AchievementEntity> ->
                    if (response.success.isSuccess())
                        mDataManager.containsData(response.list, locList)
                }
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showProgress(false)
                        viewState.showMessage(R.string.achievements_successfully_loaded)
                        loadBiomes()
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.achievements_successfully_error_loaded)
                    })
        )
    }

    private fun loadBiomes() {
        viewState.showProgress(true)
        unsubscribeOnDestroy(
            Single.zip(
                mDataManager.getBiomes(),
                mDataManager.getBiomesFromDb(),
                BiFunction { response: BiomesResponse, locList: List<BiomesEntity> ->
                    if (response.success.isSuccess())
                        mDataManager.containsData(response.biomesList, locList)
                }
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showProgress(false)
                        viewState.showMessage(R.string.biomes_load_successfully)
                        loadBrewing()
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.biomes_load_error)
                    }
                ))
    }

    private fun loadBrewing() {
        viewState.showProgress(true)
        unsubscribeOnDestroy(
            Single.zip(
                mDataManager.getBrewing(),
                mDataManager.getBrewingFromDb(),
                BiFunction { response: BrewingResponse, locList: List<BrewingEntity> ->
                    if (response.success.isSuccess())
                        mDataManager.containsData(response.brewingImage, locList)
                }
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showProgress(false)
                        viewState.showMessage(R.string.brewing_load_successfully)
                        loadAdditionalInfo()
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.brewing_load_error)
                    }
                ))
    }
}
