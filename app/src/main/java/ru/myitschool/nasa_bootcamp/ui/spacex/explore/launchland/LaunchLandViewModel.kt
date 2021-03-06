package ru.myitschool.nasa_bootcamp.ui.spacex.explore.launchland

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import ru.myitschool.nasa_bootcamp.data.model.HistoryModel
import ru.myitschool.nasa_bootcamp.data.model.LandPadModel
import ru.myitschool.nasa_bootcamp.data.model.LaunchPadModel
import ru.myitschool.nasa_bootcamp.utils.Status

interface LaunchLandViewModel {
    suspend fun getLaunchPads()
    suspend fun getLandPads()
    fun getViewModelScope(): CoroutineScope
    fun getLandList(): MutableLiveData<ArrayList<LandPadModel>>
    fun getLaunchList(): MutableLiveData<ArrayList<LaunchPadModel>>
    fun getStatus(): MutableLiveData<Status>
}