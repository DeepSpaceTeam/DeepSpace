package ru.myitschool.nasa_bootcamp.ui.spacex.explore.launchland

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import ru.myitschool.nasa_bootcamp.data.model.LandPadModel
import ru.myitschool.nasa_bootcamp.data.model.LaunchPadModel
import ru.myitschool.nasa_bootcamp.data.repository.SpaceXRepository
import ru.myitschool.nasa_bootcamp.utils.Status
import javax.inject.Inject

@HiltViewModel
class LaunchLandViewModelImpl @Inject constructor(
    private val repository: SpaceXRepository
) : ViewModel(), LaunchLandViewModel {

    var landModels: MutableLiveData<ArrayList<LandPadModel>> = MutableLiveData<ArrayList<LandPadModel>>()
    var listLand: ArrayList<LandPadModel> = arrayListOf()
    private val status: MutableLiveData<Status> = MutableLiveData<Status>()
    var launchModels: MutableLiveData<ArrayList<LaunchPadModel>> = MutableLiveData<ArrayList<LaunchPadModel>>()
    var listLaunch: ArrayList<LaunchPadModel> = arrayListOf()


    override suspend fun getLaunchPads() {
        status.value = Status.LOADING
        val response = repository.getLaunchPads()

        if(response.isSuccessful) {
            status.value = Status.SUCCESS
            for (launch in response.body()!!) {
                listLaunch.add(launch.createLaunchPadModel())
                Log.d("LAUNCH_TAG", launch.createLaunchPadModel().name)
            }
            launchModels.value = listLaunch
        }else{
            status.value = Status.ERROR
        }
    }

    override suspend fun getLandPads() {
        val response = repository.getLandPads()

        for (land in response.body()!!) {
            listLand.add(land.createLandPadModel())
            Log.d("LAND_TAG", "Name: ${land.createLandPadModel().full_name} Lat: ${land.createLandPadModel().location.latitude} Lan : ${land.createLandPadModel().location.longitude}")
        }
        landModels.value = listLand
    }

    override fun getViewModelScope(): CoroutineScope = viewModelScope

    override fun getLandList(): MutableLiveData<ArrayList<LandPadModel>>  = landModels

    override fun getLaunchList(): MutableLiveData<ArrayList<LaunchPadModel>> = launchModels
    override fun getStatus(): MutableLiveData<Status> {
        return status
    }


}